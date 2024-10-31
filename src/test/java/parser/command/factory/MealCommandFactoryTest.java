package parser.command.factory;

import command.Command;
import command.meals.AddMealCommand;
import command.meals.DeleteMealCommand;
import command.meals.ViewMealCommand;
import meal.Meal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MealCommandFactoryTest {

    private MealCommandFactory mealCommandFactory;

    @BeforeEach
    public void setUp() {
        mealCommandFactory = new MealCommandFactory();
    }

    @Test
    public void testParse_AddMealCommand() {
        String argumentString = "add /n Sample Meal /c 300 /t 31-10-2024";
        AddMealCommand expectedCommand = new AddMealCommand(new Meal("Sample Meal", 300), LocalDate.of(2024, 10, 31));

        Command result = mealCommandFactory.parse(argumentString);

        assertEquals(expectedCommand, result, "Parsed command should be equal to the expected AddMealCommand.");
    }

    @Test
    public void testParse_DeleteMealCommand() {
        String argumentString = "delete /m 1 /t 31-10-2024";
        DeleteMealCommand expectedCommand = new DeleteMealCommand(0, LocalDate.of(2024, 10, 31));

        Command result = mealCommandFactory.parse(argumentString);

        assertEquals(expectedCommand, result, "Parsed command should be equal to the expected DeleteMealCommand.");
    }

    @Test
    public void testParse_ViewMealCommand() {
        String argumentString = "view 31-10-2024";
        ViewMealCommand expectedCommand = new ViewMealCommand(LocalDate.of(2024, 10, 31));

        Command result = mealCommandFactory.parse(argumentString);

        assertEquals(expectedCommand, result, "Parsed command should be equal to the expected ViewMealCommand.");
    }

    @Test
    public void testPrepareAddCommand_MissingNameFlag() {
        String argumentString = "/c 300 /t 31-10-2024";

        assertThrows(IllegalArgumentException.class, () -> mealCommandFactory.prepareAddCommand(argumentString), "Missing required flag /n should throw IllegalArgumentException.");
    }

    @Test
    public void testPrepareAddCommand_MissingCaloriesFlag() {
        String argumentString = "/n Sample meal /t 31-10-2024";

        assertThrows(IllegalArgumentException.class, () -> mealCommandFactory.prepareAddCommand(argumentString), "Missing required flag /n should throw IllegalArgumentException.");
    }

    @Test
    public void testPrepareDeleteCommand_MissingIndexFlag() {
        String argumentString = "/t 31-10-2024";

        assertThrows(IllegalArgumentException.class, () -> mealCommandFactory.prepareDeleteCommand(argumentString), "Missing required flag /m should throw IllegalArgumentException.");
    }

    @Test
    public void testPrepareViewCommand_InvalidDate() {
        String argumentString = "invalid-date";

        assertThrows(IllegalArgumentException.class, () -> mealCommandFactory.prepareViewCommand(argumentString), "Invalid date format should throw IllegalArgumentException.");
    }
}
