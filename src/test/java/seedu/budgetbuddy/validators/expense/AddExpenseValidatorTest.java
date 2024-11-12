package seedu.budgetbuddy.validators.expense;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.commands.expense.AddExpenseCommand;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.IncorrectCommand;
import seedu.budgetbuddy.transaction.Category;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class AddExpenseValidatorTest {

    @Test
    void processCommand_invalidAmount_returnsIncorrectCommand() {
        Command result = AddExpenseValidator.processCommand("add expense Lunch a/abc d/12/10/2024 c/FOOD");

        // Check if result is an IncorrectCommand and compare the message
        assertInstanceOf(IncorrectCommand.class, result);
        String message = ((IncorrectCommand) result).getFeedbackToUser();
        assertEquals("Amount should be a positive number with up to 2 decimal places.", message);
    }

    @Test
    void processCommand_noDescription_returnsIncorrectCommand() {
        Command result = AddExpenseValidator.processCommand("add expense a/100 d/12/10/2024 c/FOOD");

        // Check if result is an IncorrectCommand and compare the message
        assertInstanceOf(IncorrectCommand.class, result);
        String message = ((IncorrectCommand) result).getFeedbackToUser();
        assertEquals("Description cannot be empty.", message);
    }

    @Test
    void processCommand_invalidDate_returnsIncorrectCommand() {
        Command result = AddExpenseValidator.processCommand("add expense Lunch a/100 d/invalid_date c/FOOD");

        // Check if result is an IncorrectCommand and compare the message
        assertInstanceOf(IncorrectCommand.class, result);
        String message = ((IncorrectCommand) result).getFeedbackToUser();
        assertEquals("Invalid date format. Use d/DD/MM/YYYY.", message);
    }

    @Test
    void processCommand_noAmount_returnsIncorrectCommand() {
        Command result = AddExpenseValidator.processCommand("add expense Lunch d/12/10/2024 c/FOOD");

        // Check if result is an IncorrectCommand and compare the message
        assertInstanceOf(IncorrectCommand.class, result);
        String message = ((IncorrectCommand) result).getFeedbackToUser();
        assertEquals("Amount not entered.", message);
    }

    @Test
    void processCommand_negativeAmount_returnsIncorrectCommand() {
        Command result = AddExpenseValidator.processCommand("add expense Lunch a/-50 d/12/10/2024 c/FOOD");

        // Check if result is an IncorrectCommand and compare the message
        assertInstanceOf(IncorrectCommand.class, result);
        String message = ((IncorrectCommand) result).getFeedbackToUser();
        assertEquals("Amount should be a positive number with up to 2 decimal places.", message);
    }


    @Test
    void processCommand_validCommand_returnsAddExpenseCommand() {
        Command result = AddExpenseValidator.processCommand("add expense Lunch a/100 d/12/10/2024 c/FOOD");

        // Check if the result is an AddExpenseCommand (a valid command)
        assertInstanceOf(AddExpenseCommand.class, result);
        AddExpenseCommand command = (AddExpenseCommand) result;

        // Verify the details of the created command
        assertEquals("Lunch", command.getDescription());
        assertEquals(100, command.getAmount());
        assertEquals(LocalDate.of(2024, 10, 12), command.getDate());
        assertEquals(Category.FOOD, command.getCategory());
    }
}

