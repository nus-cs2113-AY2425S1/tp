package seedu.budgetbuddy.validators.expense;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.IncorrectCommand;
import seedu.budgetbuddy.commands.expense.DisplayExpensesForMonthWithCategoriesGraphCommand;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DisplayExpensesForMonthWithCategoriesValidatorTest {

    @Test
    public void processCommand_validCommand_returnsDisplayExpensesCommand() {
        // Given a valid command string
        String command = "display expenses with categories m/10/2024";

        // Process the command using the validator
        Command result = DisplayExpensesForMonthWithCategoriesValidator.processCommand(command);

        // Check if the result is of type DisplayExpensesForMonthWithCategoriesGraphCommand
        assertInstanceOf(DisplayExpensesForMonthWithCategoriesGraphCommand.class, result);

        // Check if the year and month were correctly parsed
        YearMonth expectedYearMonth = YearMonth.of(2024, 10);
        DisplayExpensesForMonthWithCategoriesGraphCommand displayCommand =
                (DisplayExpensesForMonthWithCategoriesGraphCommand) result;
        assertEquals(expectedYearMonth, displayCommand.getYearMonth(), "YearMonth should match the expected value.");
    }

    @Test
    public void processCommand_invalidDate_returnsIncorrectCommand() {
        // Given a command with an invalid date format
        String command = "display expenses with categories m/invalid-date";

        // Process the command using the validator
        Command result = DisplayExpensesForMonthWithCategoriesValidator.processCommand(command);

        // Check if the result is an IncorrectCommand
        assertInstanceOf(IncorrectCommand.class, result);

        // Check if the error message is correct
        IncorrectCommand incorrectCommand = (IncorrectCommand) result;
        assertEquals("Invalid Date",
                incorrectCommand.getFeedbackToUser());
    }

    @Test
    public void processCommand_emptyDate_returnsIncorrectCommand() {
        // Given a command with no date provided
        String command = "display expenses with categories";

        // Process the command using the validator
        Command result = DisplayExpensesForMonthWithCategoriesValidator.processCommand(command);

        // Check if the result is an IncorrectCommand
        assertInstanceOf(IncorrectCommand.class, result);

        // Check if the error message is correct
        IncorrectCommand incorrectCommand = (IncorrectCommand) result;
        assertEquals("Please provide a valid month", incorrectCommand.getFeedbackToUser());
    }

    @Test
    public void processCommand_invalidMonthFormat_returnsIncorrectCommand() {
        // Given a command with an invalid month format
        String command = "display expenses with categories m/13/2024";

        // Process the command using the validator
        Command result = DisplayExpensesForMonthWithCategoriesValidator.processCommand(command);

        // Check if the result is an IncorrectCommand
        assertInstanceOf(IncorrectCommand.class, result);

        // Check if the error message is correct
        IncorrectCommand incorrectCommand = (IncorrectCommand) result;
        assertEquals("Invalid Date", incorrectCommand.getFeedbackToUser());
    }
}

