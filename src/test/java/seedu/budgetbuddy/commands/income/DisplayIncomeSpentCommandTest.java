package seedu.budgetbuddy.commands.income;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.transaction.income.Income;
import seedu.budgetbuddy.transaction.income.IncomeManager;
import seedu.budgetbuddy.transaction.expense.Expense;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.budgetbuddy.transaction.Category.OTHERS;

public class DisplayIncomeSpentCommandTest {

    private DisplayIncomeSpentCommand commandForNovember;
    private DisplayIncomeSpentCommand commandForOctober;
    private ByteArrayOutputStream outputStreamCaptor;

    @BeforeEach
    public void setUp() {
        // Set up a new ByteArrayOutputStream to capture output
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Temporarily suppress system output when adding expenses and incomes
        PrintStream originalSystemOut = System.out;
        System.setOut(new PrintStream(new ByteArrayOutputStream())); // Suppress output

        // Clear existing incomes and expenses if needed
        IncomeManager.getIncomes().clear();
        ExpenseManager.getExpenses().clear();

        // Set up dummy incomes for specific months
        IncomeManager.addIncome(new Income("Salary", 2000.0, LocalDate.of(2024, 11, 5)));
        IncomeManager.addIncome(new Income("Freelance Work", 500.0, LocalDate.of(2024, 11, 10)));
        IncomeManager.addIncome(new Income("Gift", 3000.0, LocalDate.of(2024, 10, 3)));

        // Set up dummy expenses for specific months
        ExpenseManager.addExpense(new Expense("Groceries", 1000.0, LocalDate.of(2024, 11, 6), OTHERS));
        ExpenseManager.addExpense(new Expense("Utilities", 500.0, LocalDate.of(2024, 11, 15), OTHERS));
        ExpenseManager.addExpense(new Expense("Dining Out", 900.0, LocalDate.of(2024, 10, 10), OTHERS));

        // Restore original System.out
        System.setOut(originalSystemOut);

        // Initialize commands with YearMonth to test
        commandForNovember = new DisplayIncomeSpentCommand(YearMonth.of(2024, 11));
        commandForOctober = new DisplayIncomeSpentCommand(YearMonth.of(2024, 10));
    }

    @Test
    public void isCommand_validCommand_returnsTrue() {
        assertTrue(DisplayIncomeSpentCommand.isCommand("display income spent"));
    }

    @Test
    public void isCommand_invalidCommand_returnsFalse() {
        assertFalse(DisplayIncomeSpentCommand.isCommand("deduct income spent"));
    }

    @Test
    public void execute_correctMonthPercentageDisplayed() {
        // Execute command and check display output
        commandForNovember.execute();

        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("Percentage of income spent for 2024-11: 60.0%"));
    }

    @Test
    public void execute_differentMonthPercentageDisplayed() {
        // Execute command and check display output
        commandForOctober.execute();

        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("Percentage of income spent for 2024-10: 30.0%"));
    }
}
