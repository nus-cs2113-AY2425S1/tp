package seedu.budgetbuddy.validators.budget;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.budget.ListBudgetCommand;
import seedu.budgetbuddy.exceptions.BudgetBuddyException;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListBudgetValidatorTest {

    @Test
    public void processCommand_noDate_returnsListBudgetCommand() throws BudgetBuddyException {
        String command = "list budgets"; // No date provided
        Command result = ListBudgetValidator.processCommand(command);

        assertNotNull(result);
        assertEquals(ListBudgetCommand.class, result.getClass());
        ListBudgetCommand listBudgetCommand = (ListBudgetCommand) result;
        assertNull(listBudgetCommand.getDate()); // Expecting null for date
    }

    @Test
    public void processCommand_validDate_returnsListBudgetCommand() throws BudgetBuddyException {
        String command = "list budgets m/11/2024"; // Valid date provided
        Command result = ListBudgetValidator.processCommand(command);

        assertNotNull(result);
        assertEquals(ListBudgetCommand.class, result.getClass());
        ListBudgetCommand listBudgetCommand = (ListBudgetCommand) result;
        assertEquals(YearMonth.of(2024, 11), listBudgetCommand.getDate());
    }

    @Test
    public void processCommand_invalidDateFormat_throwsBudgetBuddyException() {
        String command = "list budgets m/invalid-date"; // Invalid date format

        BudgetBuddyException exception = assertThrows(BudgetBuddyException.class, () ->
                ListBudgetValidator.processCommand(command));

        assertTrue(exception.getMessage().contains("Invalid format. Use 'list budgets [m/MM/yyyy]'."));
    }
}
