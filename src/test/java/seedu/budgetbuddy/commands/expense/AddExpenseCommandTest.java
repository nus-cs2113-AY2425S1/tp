package seedu.budgetbuddy.commands.expense;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.transaction.expense.Expense;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddExpenseCommandTest {

    @BeforeEach
    public void setUp() {
        ExpenseManager.reset();
    }

    @Test
    public void constructor_validInputs_initializesCorrectly() {
        AddExpenseCommand command = new AddExpenseCommand("Lunch", 15.0, LocalDate.of(2024,
                10, 20), Category.FOOD);
        assertNotNull(command);
        assertEquals("Lunch", command.getDescription());
        assertEquals(15.0, command.getAmount());
        assertEquals(LocalDate.of(2024, 10, 20), command.getDate());
        assertEquals(Category.FOOD, command.getCategory());
    }

    @Test
    public void isCommand_commandStartsWithAddExpense_returnsTrue() {
        assertTrue(AddExpenseCommand.isCommand("add expense Lunch 15 for FOOD"));
    }

    @Test
    public void isCommand_commandDoesNotStartWithAddExpense_returnsFalse() {
        assertFalse(AddExpenseCommand.isCommand("remove expense Lunch 15 for FOOD"));
    }

    @Test
    public void execute_validExpense_addsExpense() {
        AddExpenseCommand command = new AddExpenseCommand("Dinner", 20.0, LocalDate.of(2024,
                10, 20), Category.FOOD);
        command.execute();

        Expense expense = ExpenseManager.getExpenses().get(0); // Assume this is the first added expense
        assertNotNull(expense);
        assertEquals("Dinner", expense.getDescription());
        assertEquals(20.0, expense.getAmount());
        assertEquals(LocalDate.of(2024, 10, 20), expense.getDate());
        assertEquals(Category.FOOD, expense.getCategory());
    }

    @Test
    public void execute_nullDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            new AddExpenseCommand("Groceries", 10.0, null, Category.OTHERS).execute();
        });
    }
}

