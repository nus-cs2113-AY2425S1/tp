package seedu.budgetbuddy.transaction.expense;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.transaction.Category;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseManagerTest {

    private static int numberOfExpenses = 0;
    private static ArrayList<Expense> expenses = new ArrayList<>();
    private static ExpenseManager expenseManager;
    private static final String EMPTY_DISPLAY_STRING =
            "No expense entry with given parameters found, try again with a different parameter.";

    void initializeTestContent(){
        expenseManager = new ExpenseManager(expenses, numberOfExpenses);
        expenseManager.reset();
        Expense newExpense = new Expense("New Food",
                12,
                LocalDate.parse("2024-02-12"),
                Category.FOOD);
        expenseManager.addExpense(newExpense);
    }
    void initializeEmptyTestContent(){
        expenseManager = new ExpenseManager(new ArrayList<>(), numberOfExpenses);
    }

    String getExpectedString(){
        String result = "";
        int counter = 1;
        for (Expense expense : ExpenseManager.getExpenses()) {
            result += counter + ". " + expense.toString() + "\n";
            counter++;
        }
        result += "Your total expense(s) for FOOD in February 2024 are $12.00";
        return result;
    }

    @Test
    void displayExpensesWithCategoryAndDate_validMonthCategory_expectOneEntry() {
        initializeTestContent();
        Category category = Category.FOOD;
        YearMonth yearMonth = YearMonth.of(2024, 2);
        assertEquals(getExpectedString(),
                ExpenseManager.listExpensesWithCategoryAndDate(category, yearMonth));
    }

    @Test
    void displayExpensesWithCategoryAndDate_incorrectCategory_expectEmptyDisplayString() {
        Category category = Category.TRANSPORT;
        YearMonth yearMonth = YearMonth.of(2024, 2);
        assertEquals(EMPTY_DISPLAY_STRING,
                ExpenseManager.listExpensesWithCategoryAndDate(category, yearMonth));
    }

    @Test
    void displayExpensesWithCategoryAndDate_incorrectMonth_expectEmptyDisplayString() {
        Category category = Category.FOOD;
        YearMonth yearMonth = YearMonth.of(2024, 1);
        assertEquals(EMPTY_DISPLAY_STRING,
                ExpenseManager.listExpensesWithCategoryAndDate(category, yearMonth));
    }

    @Test
    void searchExpense_noDescriptor_expectEmptyDisplayString(){
        initializeTestContent();
        assertEquals(EMPTY_DISPLAY_STRING, ExpenseManager.searchExpenses(""));
    }

    @Test
    void searchExpense_descriptorProvidedNoFoundExpense_expectEmptyDisplayString(){
        initializeTestContent();
        assertEquals(EMPTY_DISPLAY_STRING, ExpenseManager.searchExpenses("RANDOMMESSAGE"));
    }

    @Test
    void searchExpense_foundExpense_expectOutput(){
        initializeTestContent();
        assertEquals("1. Description: New Food  Amount: 12.00  Date: 2024-02-12  Category: FOOD\n",
                ExpenseManager.searchExpenses("New"));
    }

    @Test
    void breakdownExpensesByCategory_noExpenses_expectNoExpensesMessage(){
        initializeEmptyTestContent();
        assertEquals("Total expenses: 0. You have not indicated any expense yet.",
                ExpenseManager.breakdownExpensesByCategory());
    }

    @Test
    void breakdownExpensesByCategory_oneExpense_expectExpenseMessage(){
        initializeTestContent();
        assertEquals("Total expenses: 12.0\nFood: 12.0(100.00%)\nTransport: 0.0(0.00%)\nUtilities:" +
                " 0.0(0.00%)\nEntertainment: 0.0(0.00%)\nEducation: 0.0(0.00%)\nOthers: 0.0(0.00%)\n",
                ExpenseManager.breakdownExpensesByCategory());
    }

    @Test
    void listExpensesWithCategoryAndDate_noExpenses_expectNoExpensesMessage(){
        initializeEmptyTestContent();
        Category category = Category.FOOD;
        YearMonth yearMonth = YearMonth.now();
        assertEquals(EMPTY_DISPLAY_STRING, ExpenseManager.listExpensesWithCategoryAndDate(category, yearMonth));
    }

    @Test
    void listExpensesWithCategoryAndDate_oneExpense_expectExpenseMessage(){
        initializeTestContent();
        Category category = Category.FOOD;
        YearMonth yearMonth = YearMonth.of(2024, 2);
        assertEquals("1. Description: New Food  Amount: 12.00  Date: 2024-02-12  Category: FOOD\n" +
                "Your total expense(s) for FOOD in February 2024 are $12.00",
                ExpenseManager.listExpensesWithCategoryAndDate(category, yearMonth));
    }

    @Test
    void listExpensesWithCategoryAndDate_oneExpenseWrongCategory_expectNoExpensesFoundMessage(){
        initializeTestContent();
        Category category = Category.ENTERTAINMENT;
        YearMonth yearMonth = YearMonth.of(2024, 2);
        assertEquals(EMPTY_DISPLAY_STRING, ExpenseManager.listExpensesWithCategoryAndDate(category, yearMonth));
    }

    @Test
    void listExpensesWithCategory_noExpense_expectNoExpensesFoundMessage(){
        initializeEmptyTestContent();
        Category category = Category.FOOD;
        assertEquals(EMPTY_DISPLAY_STRING, ExpenseManager.listExpensesWithCategory(category));
    }

    @Test
    void listExpensesWithCategory_oneExpense_expectExpenseMessage(){
        initializeTestContent();
        Category category = Category.FOOD;
        assertEquals("1. Description: New Food  Amount: 12.00  Date: 2024-02-12  Category: FOOD\n" +
                        "Your total expense(s) for FOOD are $12.00",
                ExpenseManager.listExpensesWithCategory(category));
    }

    @Test
    void listExpensesWithDate_noExpense_expectNoExpensesFoundMessage(){
        initializeEmptyTestContent();
        YearMonth yearMonth = YearMonth.of(2024, 2);
        assertEquals(EMPTY_DISPLAY_STRING, ExpenseManager.listExpensesWithDate(yearMonth));
    }

    @Test
    void listExpensesWithDate_oneExpense_expectExpenseMessage(){
        initializeTestContent();
        YearMonth yearMonth = YearMonth.of(2024, 2);
        assertEquals("1. Description: New Food  Amount: 12.00  Date: 2024-02-12  Category: FOOD\n" +
                "Your total expense(s) for February 2024 are $12.00",
                ExpenseManager.listExpensesWithDate(yearMonth));
    }

    @Test
    void getMonthlyExpense_oneExpense_expectMonthlyExpense(){
        initializeTestContent();
        YearMonth yearMonth = YearMonth.of(2024, 2);
        assertEquals(12.0, ExpenseManager.getMonthlyExpense(yearMonth));
    }

    @Test
    void getMonthlyExpense_noExpense_expectZeroMonthlyExpense(){
        initializeEmptyTestContent();
        YearMonth yearMonth = YearMonth.of(2024, 2);
        assertEquals(0.0, ExpenseManager.getMonthlyExpense(yearMonth));
    }
}
