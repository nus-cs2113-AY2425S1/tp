package seedu.budgetbuddy.validators.budget;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.budget.AddBudgetCommand;
import seedu.budgetbuddy.exceptions.BudgetBuddyException;
import seedu.budgetbuddy.transaction.Category;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddBudgetValidatorTest {

    @Test
    public void processCommand_validCommand_returnsAddBudgetCommand() throws BudgetBuddyException {
        String command = "add budget a/100.0 m/11/2024 c/Food"; // Valid command
        Command result = AddBudgetValidator.processCommand(command);

        assertNotNull(result);
        assertTrue(result instanceof AddBudgetCommand);
        AddBudgetCommand addBudgetCommand = (AddBudgetCommand) result;
        assertEquals(100.0, addBudgetCommand.getAmount());
        assertEquals(YearMonth.of(2024, 11), addBudgetCommand.getDate());
        assertEquals(Category.FOOD, addBudgetCommand.getCategory());
    }

    @Test
    public void processCommand_missingAmount_throwsBudgetBuddyException() {
        String command = "add budget"; // Missing amount

        BudgetBuddyException exception = assertThrows(BudgetBuddyException.class, () ->
                AddBudgetValidator.processCommand(command));

        assertTrue(exception.getMessage().contains("No amount provided."));
    }

    @Test
    public void processCommand_invalidAmountFormat_throwsBudgetBuddyException() {
        String command = "add budget a/invalid m/2024-11 c/Food"; // Invalid amount format

        BudgetBuddyException exception = assertThrows(BudgetBuddyException.class, () ->
                AddBudgetValidator.processCommand(command));

        assertTrue(exception.getMessage().contains("Invalid amount format."));
    }

    @Test
    public void processCommand_negativeAmount_throwsBudgetBuddyException() {
        String command = "add budget a/-100.0 m/2024-11 c/Food"; // Negative amount

        BudgetBuddyException exception = assertThrows(BudgetBuddyException.class, () ->
                AddBudgetValidator.processCommand(command));


        assertTrue(exception.getMessage().contains("Invalid amount: -100.0. Amount must be a positive value."));
    }

    @Test
    public void processCommand_invalidDateFormat_throwsBudgetBuddyException() {
        String command = "add budget a/100.0 m/invalid-date c/Food"; // Invalid date format

        BudgetBuddyException exception = assertThrows(BudgetBuddyException.class, () ->
                AddBudgetValidator.processCommand(command));

        assertTrue(exception.getMessage().contains("Invalid date format. Use m/MM/yyyy."));
    }

    @Test
    public void processCommand_noCategory_usesDefaultCategory() throws BudgetBuddyException {
        String command = "add budget a/100.0 m/11/2024"; // No category provided

        Command result = AddBudgetValidator.processCommand(command);

        assertNotNull(result);
        assertTrue(result instanceof AddBudgetCommand);
        AddBudgetCommand addBudgetCommand = (AddBudgetCommand) result;
        assertEquals(Category.OTHERS, addBudgetCommand.getCategory());
    }
}
