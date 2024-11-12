package seedu.budgetbuddy.transaction.budget;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.transaction.expense.Expense;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;

import java.time.LocalDate;
import java.time.YearMonth;

/**
 * Test class for RemainingBudgetManager.
 */
public class RemainingBudgetManagerTest {

    private RemainingBudgetManager remainingBudgetManager;

    @BeforeEach
    public void setUp() {
        // Set up budgets and expenses
        BudgetManager.reset();
        ExpenseManager.reset();
        Budget budgetOct = new Budget(YearMonth.of(2024, 10));
        budgetOct.addAmount(Category.EDUCATION, 500);
        Budget budgetSep = new Budget(YearMonth.of(2024, 9));
        budgetSep.addAmount(Category.OTHERS, 300);
        BudgetManager.addBudget(budgetOct);
        BudgetManager.addBudget(budgetSep);

        ExpenseManager.addExpense(new Expense("Lunch", 50.0, LocalDate.of(2024, 11, 5), Category.FOOD));
        ExpenseManager.addExpense(new Expense("Groceries", 30.0, LocalDate.of(2024, 9, 25), Category.OTHERS));

        remainingBudgetManager = new RemainingBudgetManager();  // Initialize
    }

    @Test
    public void getRemainingBudgets_expenseWithoutBudget_negativeBudget() {
        String result = remainingBudgetManager.getRemainingBudgets(LocalDate.of(2024, 11, 5), Category.FOOD);
        assertEquals("The remaining budget for 2024-11 in the FOOD category is: -50.00\n" +
                "Caution! You have exceeded your budget!", result);
    }

    @Test
    public void getRemainingBudgets_expenseWithBudget_positiveBudget() {
        String result = remainingBudgetManager.getRemainingBudgets(LocalDate.of(2024, 9, 25), Category.OTHERS);
        assertEquals("The remaining budget for 2024-09 in the OTHERS category is: 270.00", result);
    }

    @Test
    public void getRemainingBudgets_addExpense_positiveBudget() {
        ExpenseManager.addExpense(new Expense("Books", 20.0, LocalDate.of(2024, 10, 5), Category.EDUCATION));
        remainingBudgetManager = new RemainingBudgetManager();

        String result = remainingBudgetManager.getRemainingBudgets(LocalDate.of(2024, 10, 5), Category.EDUCATION);
        assertEquals("The remaining budget for 2024-10 in the EDUCATION category is: 480.00", result);
    }

}
