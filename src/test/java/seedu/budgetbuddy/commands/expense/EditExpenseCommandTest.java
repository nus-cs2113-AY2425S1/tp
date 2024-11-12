package seedu.budgetbuddy.commands.expense;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.transaction.expense.Expense;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EditExpenseCommandTest {
    private static Expense expense;
    private static final double EMPTY_AMOUNT = -1.0;
    private static final Category EMPTY_CATEGORY = null;
    private static final LocalDate EMPTY_DATE = null;

    @Test
    void editExpensesWithAmountOnly_validAmount_expectChangedAmount(){
        expense = new Expense(
                "New Expense",
                500.0,
                LocalDate.of(2024,2,12),
                Category.FOOD);
        double newAmount = 50;
        EditExpenseCommand expenseCommand = new EditExpenseCommand(expense);
        expenseCommand.setAmount(newAmount);
        expenseCommand.processEdit();
        assertEquals(newAmount,expense.getAmount());
    }

    @Test
    void editExpensesWithCategoryOnly_validCategory_expectChangedCategory(){
        expense = new Expense(
                "New Expense",
                500.0,
                LocalDate.of(2024,2,12),
                Category.FOOD);
        Category newCategory = Category.TRANSPORT;
        EditExpenseCommand expenseCommand = new EditExpenseCommand(expense);
        expenseCommand.setCategory(newCategory);
        expenseCommand.processEdit();
        assertEquals(newCategory,expense.getCategory());
    }

    @Test
    void editExpensesWithEmptyFields_expectUnchangedExpense(){
        expense = new Expense(
                "New Expense",
                500.0,
                LocalDate.of(2024,2,12),
                Category.FOOD);
        EditExpenseCommand expenseCommand = new EditExpenseCommand(expense);
        expenseCommand.setCategory(EMPTY_CATEGORY);
        expenseCommand.setDate(EMPTY_DATE);
        expenseCommand.setAmount(EMPTY_AMOUNT);
        expenseCommand.processEdit();
        assertEquals(Category.FOOD,expense.getCategory());
        assertEquals(LocalDate.of(2024,2,12),expense.getDate());
        assertEquals(500.0,expense.getAmount());

    }
}
