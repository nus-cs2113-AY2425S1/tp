package seedu.budgetbuddy.validators.saving;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.transaction.expense.Expense;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;
import seedu.budgetbuddy.transaction.income.Income;
import seedu.budgetbuddy.transaction.income.IncomeManager;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.validators.IndexValidator;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * JUnit test class for IndexValidator.
 */
class IndexValidatorTest {

    @BeforeEach
    void setUp() {
        // Initialize a list of expenses with Category, description, amount, and date
        ArrayList<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense("Test1", 10.50, LocalDate.of(2024, 1, 1), Category.OTHERS));
        expenses.add(new Expense("Test2", 2.80, LocalDate.of(2024, 2, 1), Category.OTHERS));
        expenses.add(new Expense("Test3", 12.00, LocalDate.of(2024, 3, 1), Category.OTHERS));

        // Initialize a list of incomes
        ArrayList<Income> incomes = new ArrayList<>();
        incomes.add(new Income("Test1", 10.50, LocalDate.of(2024, 1, 1)));
        incomes.add(new Income("Test2", 1.50, LocalDate.of(2024, 2, 1)));

        // Initialize ExpenseManager and IncomeManager with the number of expenses and incomes
        new ExpenseManager(expenses, expenses.size());
        new IncomeManager(incomes, incomes.size());
    }

    @Test
    void validateExpenseIndex_validIndexWithinRange_expectValidIndex() {
        String input = "2";
        int result = IndexValidator.validateExpenseIndex(input);
        assertEquals(2, result, "Expected valid index 2 but got " + result);
    }

    @Test
    void validateExpenseIndex_invalidIndex_expectNegativeOne() {
        String input = "invalid";
        int result = IndexValidator.validateExpenseIndex(input);
        assertEquals(-1, result, "Expected -1 for invalid input but got " + result);
    }

    @Test
    void validateExpenseIndex_indexOutOfRange_expectNegativeOne() {
        String input = "5";  // Out of range since there are only 3 expenses
        int result = IndexValidator.validateExpenseIndex(input);
        assertEquals(-1, result, "Expected -1 for out-of-range index but got " + result);
    }

    @Test
    void validateIncomeIndex_validIndexWithinRange_expectValidIndex() {
        String input = "1";
        int result = IndexValidator.validateIncomeIndex(input);
        assertEquals(1, result, "Expected valid index 1 but got " + result);
    }

    @Test
    void validateIncomeIndex_invalidIndex_expectNegativeOne() {
        String input = "invalid";
        int result = IndexValidator.validateIncomeIndex(input);
        assertEquals(-1, result, "Expected -1 for invalid input but got " + result);
    }

    @Test
    void validateIncomeIndex_indexOutOfRange_expectNegativeOne() {
        String input = "3";  // Out of range since there are only 2 incomes
        int result = IndexValidator.validateIncomeIndex(input);
        assertEquals(-1, result, "Expected -1 for out-of-range index but got " + result);
    }
}
