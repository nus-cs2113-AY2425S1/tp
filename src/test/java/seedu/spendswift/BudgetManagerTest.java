package seedu.spendswift;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.api.function.Executable;

import seedu.spendswift.command.BudgetManager;
import seedu.spendswift.command.CategoryManager;
import seedu.spendswift.command.ExpenseManager;
import seedu.spendswift.command.TrackerData;
import seedu.spendswift.command.Budget;
import seedu.spendswift.command.Category;
import seedu.spendswift.command.Expense;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;
import java.util.Calendar;
import java.util.stream.Stream;

// Static imports for specific assertion methods
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BudgetManagerTest {

    private static BudgetManager budgetManager;
    private static TrackerData trackerData;

    /**
     * Helper method to find a category by name (case-insensitive).
     */
    private static Category findCategory(TrackerData trackerData, String categoryName) {
        for (Category category : trackerData.getCategories()) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                return category;
            }
        }
        return null;
    }

    /**
     * Helper method to simulate a month change by manipulating the lastResetMonth field.
     */
    private static void simulateMonthChange(BudgetManager budgetManager) {
        try {
            Field lastResetMonthField = BudgetManager.class.getDeclaredField("lastResetMonth");
            lastResetMonthField.setAccessible(true);
            Calendar calendar = Calendar.getInstance();
            int currentMonth = calendar.get(Calendar.MONTH);
            // Set lastResetMonth to previous month to simulate a month change
            int previousMonth = (currentMonth + 11) % 12;
            lastResetMonthField.set(budgetManager, previousMonth);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Failed to simulate month change: " + e.getMessage());
        }
    }

    /**
     * Helper method to get the current status of auto-reset.
     */
    private static boolean getAutoResetStatus(BudgetManager budgetManager) {
        try {
            Field autoResetField = BudgetManager.class.getDeclaredField("isAutoResetEnabled");
            autoResetField.setAccessible(true);
            return autoResetField.getBoolean(budgetManager);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Failed to access isAutoResetEnabled field: " + e.getMessage());
            return false;
        }
    }

    /**
     * Helper method to get the last reset month.
     */
    private static int getLastResetMonth(BudgetManager budgetManager) {
        try {
            Field lastResetMonthField = BudgetManager.class.getDeclaredField("lastResetMonth");
            lastResetMonthField.setAccessible(true);
            return lastResetMonthField.getInt(budgetManager);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Failed to access lastResetMonth field: " + e.getMessage());
            return -1;
        }
    }

    @BeforeEach
    void setUp() {
        budgetManager = new BudgetManager();
        trackerData = new TrackerData();
    }

    /**
     * Provides one test case per equivalence class with randomized inputs.
     */
    static Stream<Object> provideTestCases() {
        ArrayList<Object> testCases = new ArrayList<>();
        Random random = new Random();


        // EC1: Set budget for an existing category with a valid limit.
        testCases.add(Arguments.of("EC3_SetBudgetValid", (Executable) () -> {
            String categoryName = "Category_ValidBudget";
            CategoryManager.addCategory(trackerData, categoryName);
            double limit = 100 + (1000 - 100) * random.nextDouble(); // $100 to $1000
            budgetManager.setBudgetLimit(trackerData, categoryName, limit);
            // Assert budget is set
            Category category = findCategory(trackerData, categoryName);
            Assertions.assertNotNull(category, "Category should exist.");
            Assertions.assertTrue(trackerData.getBudgets().containsKey(category),
                    "Budget should be set for the existing category.");
            Assertions.assertEquals(limit, trackerData.getBudgets().get(category).getLimit(),
                    0.001, "Budget limit should be set correctly.");
        }));

        // EC2: Set budget for a non-existing category (category should be created automatically).
        testCases.add(Arguments.of("EC4_SetBudgetNonExisting", (Executable) () -> {
            String categoryName = "Category_NonExisting";
            double limit = 100 + (1000 - 100) * random.nextDouble(); // $100 to $1000
            budgetManager.setBudgetLimit(trackerData, categoryName, limit);
            // Assert budget is set and category is created
            Category category = findCategory(trackerData, categoryName);
            Assertions.assertNotNull(category, "Category should be created when setting budget.");
            Assertions.assertTrue(trackerData.getBudgets().containsKey(category),
                    "Budget should be set for the new category.");
            Assertions.assertEquals(limit, trackerData.getBudgets().get(category).getLimit(),
                    0.001, "Budget limit should be set correctly.");
        }));

        // EC3: Set budget with an invalid limit.
        testCases.add(Arguments.of("EC5_SetBudgetInvalidLimit", (Executable) () -> {
            String categoryName = "Category_InvalidLimit";
            CategoryManager.addCategory(trackerData, categoryName);
            double invalidLimit = -50.0; // Negative limit
            budgetManager.setBudgetLimit(trackerData, categoryName, invalidLimit);
            // Depending on implementation, negative limits might be rejected or accepted
            // Here, we check if the limit is set as provided
            Category category = findCategory(trackerData, categoryName);
            Assertions.assertNotNull(category, "Category should exist.");
            if (trackerData.getBudgets().containsKey(category)) {
                Assertions.assertEquals(invalidLimit, trackerData.getBudgets().get(category).getLimit(),
                        0.001, "Budget limit should be set even if invalid.");
            } else {
                Assertions.fail("Budget should be set even with invalid limit.");
            }
        }));

        // EC4: Add expense within the budget.
        testCases.add(Arguments.of("EC6_AddExpenseWithinBudget", (Executable) () -> {
            String categoryName = "Category_ExpenseWithin";
            String name = "Random_ExpenseWithin";
            CategoryManager.addCategory(trackerData, categoryName);
            double budgetLimit = 200 + (800 - 200) * random.nextDouble(); // $200 to $800
            budgetManager.setBudgetLimit(trackerData, categoryName, budgetLimit);
            double expense = 50 + (budgetLimit - 50) * random.nextDouble(); // $50 to (limit - $50)
            Category category = findCategory(trackerData, categoryName);
            Assertions.assertNotNull(category, "Category should exist.");
            ExpenseManager.addExpense(trackerData, name, expense, categoryName);
            // Assert expense is added
            Assertions.assertTrue(trackerData.getExpenses().stream()
                            .anyMatch(e -> e.getCategory().equals(category) && e.getAmount() == expense),
                    "Expense should be added within the budget.");
        }));

        // EC5: Add expense exceeding the budget.
        testCases.add(Arguments.of("EC7_AddExpenseExceedingBudget", (Executable) () -> {
            String categoryName = "Category_ExpenseExceeding";
            String name = "Random_ExpenseExceeding";
            CategoryManager.addCategory(trackerData, categoryName);
            double budgetLimit = 100 + (500 - 100) * random.nextDouble(); // $100 to $500
            budgetManager.setBudgetLimit(trackerData, categoryName, budgetLimit);
            double expense = budgetLimit + (500 - budgetLimit) * random.nextDouble(); // Above limit
            Category category = findCategory(trackerData, categoryName);
            Assertions.assertNotNull(category, "Category should exist.");
            ExpenseManager.addExpense(trackerData, name, expense, categoryName);
            // Assert expense is added
            Assertions.assertTrue(trackerData.getExpenses().stream()
                            .anyMatch(e -> e.getCategory().equals(category) && e.getAmount() == expense),
                    "Expense should be added exceeding the budget.");
        }));


        // EC6: Toggle auto-reset functionality (Enable).
        testCases.add(Arguments.of("EC9_ToggleAutoReset_Enable", (Executable) () -> {
            budgetManager.toggleAutoReset(); // Enable
            // Assert auto-reset is enabled
            Assertions.assertTrue(getAutoResetStatus(budgetManager), "Auto-reset should be enabled.");
        }));

        // EC7: Simulate month change with auto-reset enabled.
        testCases.add(Arguments.of("EC10_SimulateMonthChange_AutoResetEnabled", (Executable) () -> {
            // Enable auto-reset
            budgetManager.toggleAutoReset();
            // Add a category and set budget
            String categoryName = "Category_AutoResetEnabled";

            double budgetLimit = 300 + (700 - 300) * random.nextDouble(); // $300 to $700
            budgetManager.setBudgetLimit(trackerData, categoryName, budgetLimit);
            // Add an expense
            Category category = findCategory(trackerData, categoryName);
            double expense = 100 + (budgetLimit - 100) * random.nextDouble(); // Within budget
            ExpenseManager.addExpense(trackerData, name, expense, categoryName);
            // Simulate month change
            simulateMonthChange(budgetManager);
            // Perform budget check and reset
            budgetManager.checkAndResetBudgets(trackerData);
            // Assert that budgets are reset (currently, resetBudgets maintains the same limits)
            // Depending on reset logic, adjust assertions accordingly
            Assertions.assertEquals(Calendar.getInstance().get(Calendar.MONTH),
                    getLastResetMonth(budgetManager),
                    "Last reset month should be updated to current month.");
        }));

        // EC8: Simulate month change with auto-reset disabled.
        testCases.add(Arguments.of("EC11_SimulateMonthChange_AutoResetDisabled", (Executable) () -> {
            // Ensure auto-reset is disabled
            if (getAutoResetStatus(budgetManager)) {
                budgetManager.toggleAutoReset();
            }
            // Add a category and set budget
            String categoryName = "Category_AutoResetDisabled";
            CategoryManager.addCategory(trackerData, categoryName);
            double budgetLimit = 300 + (8000 - 300) * random.nextDouble(); // $300 to $8000
            budgetManager.setBudgetLimit(trackerData, categoryName, budgetLimit);
            // Add an expense
            Category category = findCategory(trackerData, categoryName);
            double expense = 100 + (budgetLimit - 100) * random.nextDouble(); // Within budget
            ExpenseManager.addExpense(trackerData, name, expense, categoryName);
            // Simulate month change
            simulateMonthChange(budgetManager);
            // Perform budget check and reset
            budgetManager.checkAndResetBudgets(trackerData);
            // Assert that budgets are NOT reset (lastResetMonth should not be updated)
            Assertions.assertNotEquals(Calendar.getInstance().get(Calendar.MONTH),
                    getLastResetMonth(budgetManager),
                    "Last reset month should not be updated when auto-reset is disabled.");
        }));

        // EC9: View budget when budgets are set.
        testCases.add(Arguments.of("EC12_ViewBudget_BudgetsSet", (Executable) () -> {
            String categoryName = "Category_ViewBudgetSet";
            CategoryManager.addCategory(trackerData, categoryName);
            double budgetLimit = 300 + (8000 - 300) * random.nextDouble(); // $300 to $8000
            budgetManager.setBudgetLimit(trackerData, categoryName, budgetLimit);
            double expense = 200 + (budgetLimit - 200) * random.nextDouble(); // Within budget
            Category category = findCategory(trackerData, categoryName);
            ExpenseManager.addExpense(trackerData, name, expense, categoryName);
            // Instead of capturing output, we check the internal state
            Budget budget = trackerData.getBudgets().get(category);
            Assertions.assertNotNull(budget, "Budget should be set for the category.");
            double totalSpent = trackerData.getExpenses().stream()
                    .filter(e -> e.getCategory().equals(category))
                    .mapToDouble(Expense::getAmount)
                    .sum();
            Assertions.assertEquals(expense, totalSpent, 0.001, "Total spent should match the expense added.");
            Assertions.assertEquals(budgetLimit - expense, budget.getRemainingLimit(),
                    0.001, "Remaining budget should be calculated correctly.");
        }));

        // EC10: View budget when no budgets are set.
        testCases.add(Arguments.of("EC13_ViewBudget_NoBudgetsSet", (Executable) () -> {
            // Add some expenses without setting budgets
            String categoryName1 = "Category_NoBudget1";
            String categoryName2 = "Category_NoBudget2";
            CategoryManager.addCategory(trackerData,categoryName1);
            CategoryManager.addCategory(trackerData,categoryName2);
            Category category1 = findCategory(trackerData, categoryName1);
            Category category2 = findCategory(trackerData, categoryName2);
            ExpenseManager.addExpense(trackerData, name, (6 - 0.75) * random.nextDouble(), categoryName1);
            ExpenseManager.addExpense(trackerData, name, (8001 - 1300) * random.nextDouble(), categoryName2);
            // Instead of capturing output, we check that budgets are not set
            Assertions.assertFalse(trackerData.getBudgets().containsKey(category1),
                    "No budget should be set for " + categoryName1 + ".");
            Assertions.assertFalse(trackerData.getBudgets().containsKey(category2),
                    "No budget should be set for " + categoryName2 + ".");

        }));

        return testCases.stream();

    }

    /**
     * Executes each test case corresponding to an equivalence class.
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideTestCases")
    void testEquivalenceClasses(String testCaseName, Executable testCase) {
        try {
            try {
                testCase.execute();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            Assertions.fail("Test case '" + testCaseName + "' failed with exception: " + e.getMessage());
        }
    }
}


