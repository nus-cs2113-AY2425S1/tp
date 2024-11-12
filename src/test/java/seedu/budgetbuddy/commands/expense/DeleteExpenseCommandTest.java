package seedu.budgetbuddy.commands.expense;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.transaction.expense.Expense;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteExpenseCommandTest {

    @BeforeEach
    public void setUp() {
        ExpenseManager.reset();
        //Add Sample Data to test with
        ExpenseManager.addExpense(new Expense("Lunch", 10.0, LocalDate.now(), Category.FOOD));
        ExpenseManager.addExpense(new Expense("Bus fare", 2.5, LocalDate.now(), Category.TRANSPORT));
    }

    @Test
    public void execute_validIndex_deletesExpense() {
        // Assert initial size of expense list
        assertEquals(2, ExpenseManager.getExpenses().size());

        // Delete the first expense
        DeleteExpenseCommand deleteCommand = new DeleteExpenseCommand(0);
        deleteCommand.execute();

        // Verify the size of expense list after deletion
        assertEquals(1, ExpenseManager.getExpenses().size());
        assertEquals("Bus fare", ExpenseManager.getExpenses().get(0).getDescription());
    }

    @Test
    public void execute_invalidIndex_throwsIndexOutOfBoundsException() {
        DeleteExpenseCommand deleteCommand = new DeleteExpenseCommand(5);

        assertThrows(IndexOutOfBoundsException.class, deleteCommand::execute);
    }

    @Test
    public void isCommand_commandStartsWithDeleteExpense_returnsTrue() {
        assertTrue(DeleteExpenseCommand.isCommand("delete expense 1"));
    }

    @Test
    public void isCommand_commandDoesNotStartWithDeleteExpense_returnsFalse() {
        assertFalse(DeleteExpenseCommand.isCommand("remove expense 1"));
    }
}
