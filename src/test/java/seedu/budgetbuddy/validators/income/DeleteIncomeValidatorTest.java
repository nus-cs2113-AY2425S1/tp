package seedu.budgetbuddy.validators.income;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.income.DeleteIncomeCommand;
import seedu.budgetbuddy.commands.IncorrectCommand;
import seedu.budgetbuddy.transaction.income.Income;
import seedu.budgetbuddy.transaction.income.IncomeManager;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeleteIncomeValidatorTest {

    @Test
    void processCommand_outOfRangeIndex_returnsIncorrectIncomeCommand() {
        String command = "delete income 10";
        Command result = DeleteIncomeValidator.processCommand(command.trim());

        assertTrue(result instanceof IncorrectCommand);
        assertEquals("Invalid Index", ((IncorrectCommand) result).getFeedbackToUser());
    }

    @Test
    void processCommand_noIndexProvided_returnsIncorrectCommandIndexNotGiven() {
        String command = "delete income";
        Command result = DeleteIncomeValidator.processCommand(command);

        assertTrue(result instanceof IncorrectCommand);
        assertEquals("Index not given", ((IncorrectCommand) result).getFeedbackToUser());
    }

    @Test
    void processCommand_invalidIndex_returnsIncorrectCommandInvalidIndex() {
        String command = "delete income abc";
        Command result = DeleteIncomeValidator.processCommand(command);

        assertTrue(result instanceof IncorrectCommand);
        assertEquals("Invalid Index", ((IncorrectCommand) result).getFeedbackToUser());
    }

    @Test
    void processCommand_validIndex_returnsDeleteIncomeCommand() {
        IncomeManager.addIncome(new Income("Salary", 5000.00, LocalDate.of(2024, 11, 1)));
        IncomeManager.addIncome(new Income("Freelance", 1500.00, LocalDate.of(2024, 11, 5)));
        IncomeManager.addIncome(new Income("Bonus", 2000.00, LocalDate.of(2024, 11, 10)));

        String command = "delete income 3";
        Command result = DeleteIncomeValidator.processCommand(command);

        assertTrue(result instanceof DeleteIncomeCommand);
        assertEquals(2, ((DeleteIncomeCommand) result).getIndex());  // Expecting index to be 2 due to 0-based indexing
    }

    @Test
    void processCommand_negativeIndex_returnsIncorrectCommandInvalidIndex() {
        String command = "delete income -1";
        Command result = DeleteIncomeValidator.processCommand(command);

        assertTrue(result instanceof IncorrectCommand);
        assertEquals("Invalid Index", ((IncorrectCommand) result).getFeedbackToUser());
    }

    @Test
    void processCommand_extraSpacesAfterIndex_returnsDeleteIncomeCommand() {
        IncomeManager.addIncome(new Income("Salary", 5000.00, LocalDate.of(2024, 11, 1)));
        IncomeManager.addIncome(new Income("Freelance", 1500.00, LocalDate.of(2024, 11, 5)));
        IncomeManager.addIncome(new Income("Bonus", 2000.00, LocalDate.of(2024, 11, 10)));

        String command = "delete income 3   ";
        Command result = DeleteIncomeValidator.processCommand(command.trim());

        assertTrue(result instanceof DeleteIncomeCommand);
        assertEquals(2, ((DeleteIncomeCommand) result).getIndex());
    }
}
