package seedu.budgetbuddy.validators.budget;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.budget.DeductBudgetCommand;
import seedu.budgetbuddy.exceptions.BudgetBuddyException;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.transaction.budget.Budget;
import seedu.budgetbuddy.transaction.budget.BudgetManager;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeductBudgetValidatorTest {

    @BeforeEach
    public void setUp() {
        // Reset the static state of BudgetManager before each test
        BudgetManager.reset();
    }

    @Test
    public void processCommand_validCommand_returnsDeductBudgetCommand() throws BudgetBuddyException {
        // Assuming a budget exists for November 2024
        Budget budget = new Budget(YearMonth.of(2024, 11));
        budget.addAmount(Category.FOOD, 1000);
        BudgetManager.addBudget(budget);

        String command = "deduct budget a/100.0 m/11/2024 c/Food"; // Valid command
        Command result = DeductBudgetValidator.processCommand(command);

        assertNotNull(result);
        assertTrue(result instanceof DeductBudgetCommand);
        DeductBudgetCommand deductBudgetCommand = (DeductBudgetCommand) result;
        assertEquals(100.0, deductBudgetCommand.getAmount());
        assertEquals(YearMonth.of(2024, 11), deductBudgetCommand.getDate());
        assertEquals(Category.FOOD, deductBudgetCommand.getCategory());
    }

    @Test
    public void processCommand_missingAmount_throwsBudgetBuddyException() {
        String command = "deduct budget"; // Missing amount

        BudgetBuddyException exception = assertThrows(BudgetBuddyException.class, () ->
                DeductBudgetValidator.processCommand(command));

        assertTrue(exception.getMessage().contains("No amount provided."));
    }

    @Test
    public void processCommand_invalidAmountFormat_throwsBudgetBuddyException() {
        String command = "deduct budget a/invalid m/2024-11 c/Food"; // Invalid amount format

        BudgetBuddyException exception = assertThrows(BudgetBuddyException.class, () ->
                DeductBudgetValidator.processCommand(command));

        assertTrue(exception.getMessage().contains("Invalid amount format."));
    }

    @Test
    public void processCommand_negativeAmount_throwsBudgetBuddyException() {
        String command = "deduct budget a/-100.0 m/11/2024 c/Food"; // Negative amount

        BudgetBuddyException exception = assertThrows(BudgetBuddyException.class, () ->
                DeductBudgetValidator.processCommand(command));

        assertTrue(exception.getMessage().contains("Invalid amount: -100.0. Must be a positive value."));
    }

    @Test
    public void processCommand_invalidDateFormat_throwsBudgetBuddyException() {
        String command = "deduct budget a/100.0 m/invalid-date c/Food"; // Invalid date format

        BudgetBuddyException exception = assertThrows(BudgetBuddyException.class, () ->
                DeductBudgetValidator.processCommand(command));

        assertTrue(exception.getMessage().contains("Invalid date format. Use m/MM/yyyy."));
    }

    @Test
    public void processCommand_budgetDoesNotExist_throwsBudgetBuddyException() {
        String command = "deduct budget a/100.0 m/11/2024 c/Food"; // Budget does not exist for the date

        BudgetBuddyException exception = assertThrows(BudgetBuddyException.class, () ->
                DeductBudgetValidator.processCommand(command));

        assertTrue(exception.getMessage().contains("Budget does not exist for the specified date: 2024-11"));
    }

    @Test
    public void processCommand_noCategory_usesDefaultCategory() throws BudgetBuddyException {
        // Assuming a budget exists for November 2024
        Budget budget = new Budget(YearMonth.of(2024, 11));
        budget.addAmount(Category.OTHERS, 1000);
        BudgetManager.addBudget(budget);

        String command = "deduct budget a/100.0 m/11/2024"; // No category provided

        Command result = DeductBudgetValidator.processCommand(command);

        assertNotNull(result);
        assertTrue(result instanceof DeductBudgetCommand);
        DeductBudgetCommand deductBudgetCommand = (DeductBudgetCommand) result;
        assertEquals(Category.OTHERS, deductBudgetCommand.getCategory());
    }
}
