package seedu.budgetbuddy.transaction.saving;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.transaction.expense.Expense;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;
import seedu.budgetbuddy.transaction.income.Income;
import seedu.budgetbuddy.transaction.income.IncomeManager;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavingsManagerTest {
    private static IncomeManager incomeManager;
    private static ExpenseManager expenseManager;
    private static ArrayList<Income> incomes;
    private static ArrayList<Expense> expenses;

    void initializeTestContent(){
        incomes = new ArrayList<>();
        int numberOfIncomes = 0;
        incomeManager = new IncomeManager(incomes, numberOfIncomes);
        expenses = new ArrayList<>();
        int numberOfExpenses = 0;
        expenseManager = new ExpenseManager(expenses, numberOfExpenses);
    }

    void initializeTestContent(boolean emptyIncome, boolean emptyExpense){
        incomes = new ArrayList<>();
        int numberOfIncomes = 0;
        incomeManager = new IncomeManager(incomes, numberOfIncomes);
        if (!emptyIncome){
            Income newIncome = new Income("tuition", 1000, LocalDate.parse("2024-10-10"));
            incomeManager.addIncome(newIncome);
        }
        expenses = new ArrayList<>();
        int numberOfExpenses = 0;
        expenseManager = new ExpenseManager(expenses, numberOfExpenses);
        if (!emptyExpense){
            Expense newExpense = new Expense("Ticket", 100, LocalDate.parse("2024-10-10"),
                    Category.ENTERTAINMENT);
            expenseManager.addExpense(newExpense);
        }
    }

    @Test
    void displayTotalSavings_noIncomeNoExpense_expectZeroSavings(){
        initializeTestContent();
        assertEquals("Total Savings: 0.00\nTotal Income: 0.00\nTotal Expense: 0.00",
                SavingsManager.displayTotalSavings());
    }

    @Test
    void displayTotalSavings_noIncomeOneExpense_expectNegativeSavings(){
        initializeTestContent(true, false);
        assertEquals("Total Savings: -100.00\nTotal Income: 0.00\nTotal Expense: 100.00",
                SavingsManager.displayTotalSavings());
    }

    @Test
    void displayTotalSavingsByMonth_noIncomeNoExpense_expectZeroSavings(){
        initializeTestContent();
        assertEquals("Total savings: 0.00", SavingsManager.displayTotalSavingsByMonth());
    }

    @Test
    void displayTotalSavingsByMonth_oneIncomeOneExpense_expectSavings(){
        initializeTestContent(false, false);
        assertEquals("Savings in 2024-10: 900.00", SavingsManager.displayTotalSavingsByMonth());
    }
}
