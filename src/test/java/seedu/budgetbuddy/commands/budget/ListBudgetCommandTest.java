package seedu.budgetbuddy.commands.budget;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.transaction.budget.BudgetManager;
import seedu.budgetbuddy.transaction.budget.Budget;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListBudgetCommandTest {

    private BudgetManager budgetManager;
    private ListBudgetCommand listBudgetCommand;

    @BeforeEach
    public void setUp() {
        budgetManager = new BudgetManager();
    }

    @AfterEach
    public void tearDown() {
        budgetManager.reset(); // Clear data after each test
    }

    @Test
    public void isCommand_validCommand_true() {
        assertTrue(ListBudgetCommand.isCommand("list budgets"));
    }

    @Test
    public void isCommand_invalidCommand_false() {
        assertFalse(ListBudgetCommand.isCommand("add budget"));
    }

    @Test
    public void constructor_withDate_setsDateCorrectly() {
        YearMonth date = YearMonth.of(2024, 11);
        listBudgetCommand = new ListBudgetCommand(date);
        assertEquals(date, listBudgetCommand.getDate());
    }

    @Test
    public void execute_withSpecificDate_listsBudgetsForThatDate() {
        YearMonth date = YearMonth.of(2024, 11);
        Budget budget = new Budget(date);
        budgetManager.addBudget(budget);

        listBudgetCommand = new ListBudgetCommand(date);

        listBudgetCommand.execute();

        assertEquals(1, budgetManager.getNumberOfBudgets());
        assertEquals(budget, budgetManager.getBudget(date));
    }

    @Test
    public void execute_withNullDate_listsAllBudgets() {
        budgetManager.addBudget(new Budget(YearMonth.of(2024, 11)));
        budgetManager.addBudget(new Budget(YearMonth.of(2024, 10)));

        listBudgetCommand = new ListBudgetCommand(null);

        listBudgetCommand.execute();

        assertEquals(2, budgetManager.getNumberOfBudgets());
    }
}
