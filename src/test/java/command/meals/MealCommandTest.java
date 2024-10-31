package command.meals;

import command.CommandResult;
import history.History;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import programme.ProgrammeList;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

public class MealCommandTest {

    private MealCommand mealCommand;
    private LocalDate date;

    @Mock
    private History mockHistory;

    @Mock
    private ProgrammeList mockProgrammeList;

    @BeforeEach
    public void setUp() {
        //initializes the @Mock annotated fields in the test class
        MockitoAnnotations.openMocks(this);
        date = LocalDate.now();
        mealCommand = new TestMealCommand(date);  // Using a concrete subclass for testing
    }

    @Test
    public void testConstructor_HappyPath() {
        assertEquals(date, mealCommand.date, "Date should be initialized correctly in MealCommand.");
    }

    @Test
    public void testConstructor_EdgeCase_NullDate() {
        assertThrows(AssertionError.class, () -> new TestMealCommand(null), "Creating MealCommand with null date should throw an AssertionError.");
    }

    @Test
    public void testExecute_WithProgrammeListAndHistory_HappyPath() {
        // Arrange
        CommandResult expected = new CommandResult("Executed with history");

        // Act
        CommandResult result = mealCommand.execute(mockProgrammeList, mockHistory);

        // Assert
        assertEquals(expected, result, "execute should return a CommandResult with the correct message.");
        verify(mockHistory).getRecordByDate(date); // Assuming some method interaction with mockHistory in the subclass
    }

    // Concrete subclass of MealCommand for testing purposes
    private static class TestMealCommand extends MealCommand {
        public TestMealCommand(LocalDate date) {
            super(date);
        }

        @Override
        public CommandResult execute(History history) {
            // Ensure getRecordByDate is called as part of the execution
            history.getRecordByDate(date);
            return new CommandResult("Executed with history");
        }
    }
}
