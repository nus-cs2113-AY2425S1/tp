package seedu.budgetbuddy.validators.expense;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.IncorrectCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class DeleteExpenseValidatorTest {

    private String baseCommand;

    @BeforeEach
    void setUp() {
        baseCommand = "delete expense ";
    }

    @Test
    void testInvalidDeleteExpenseCommand() {
        Command command = DeleteExpenseValidator.processCommand(baseCommand + "invalid");
        assertInstanceOf(IncorrectCommand.class, command, "Expected an IncorrectCommand due to invalid input.");
        assertEquals("Invalid Index", ((IncorrectCommand) command).getFeedbackToUser());
    }

    @Test
    void testNegativeIndex() {
        Command command = DeleteExpenseValidator.processCommand(baseCommand + "-1");
        assertInstanceOf(IncorrectCommand.class, command, "Expected an IncorrectCommand due to negative index.");
        assertEquals("Invalid Index", ((IncorrectCommand) command).getFeedbackToUser());
    }

    @Test
    void testZeroIndex() {
        Command command = DeleteExpenseValidator.processCommand(baseCommand + "0");
        assertInstanceOf(IncorrectCommand.class, command, "Expected an IncorrectCommand due to zero index.");
        assertEquals("Invalid Index", ((IncorrectCommand) command).getFeedbackToUser());
    }

    @Test
    void testEmptyInput() {
        Command command = DeleteExpenseValidator.processCommand(baseCommand);
        assertInstanceOf(IncorrectCommand.class, command, "Expected an IncorrectCommand due to missing index.");
        assertEquals("Invalid Index", ((IncorrectCommand) command).getFeedbackToUser());
    }
}
