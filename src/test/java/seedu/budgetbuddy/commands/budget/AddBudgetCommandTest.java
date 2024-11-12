package seedu.budgetbuddy.commands.budget;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.transaction.budget.Budget;
import seedu.budgetbuddy.transaction.budget.BudgetManager;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddBudgetCommandTest {

    @BeforeEach
    public void setUp() {
        // Reset the static state of BudgetManager before each test
        BudgetManager.reset();
    }

    @Test
    public void constructor_validInputs_initializesCorrectly() {
        AddBudgetCommand command = new AddBudgetCommand(100.0,
                YearMonth.of(2024, 10), Category.FOOD);
        assertNotNull(command);
    }

    @Test
    public void constructor_inputIsNegativeAmount_throwsAssertionError() {
        AssertionError exception = assertThrows(AssertionError.class, () -> {
            new AddBudgetCommand(-50.0, YearMonth.of(2024, 10), Category.FOOD);
        });
        assertEquals("Amount must be non-negative", exception.getMessage());
    }

    @Test
    public void constructor_inputIsNullDate_throwsAssertionError() {
        AssertionError exception = assertThrows(AssertionError.class, () -> {
            new AddBudgetCommand(100.0, null, Category.FOOD);
        });
        assertEquals("Date cannot be null", exception.getMessage());
    }

    @Test
    public void constructor_inputIsNullCategory_throwsAssertionError() {
        AssertionError exception = assertThrows(AssertionError.class, () -> {
            new AddBudgetCommand(100.0, YearMonth.of(2024, 10), null);
        });
        assertEquals("Category cannot be null", exception.getMessage());
    }

    @Test
    public void isCommand_commandStartsWithAddBudget_returnsTrue() {
        assertTrue(AddBudgetCommand.isCommand("add budget 100 for October 2024"));
    }

    @Test
    public void isCommand_commandDoesNotStartWithAddBudget_returnsFalse() {
        assertFalse(AddBudgetCommand.isCommand("remove budget 100 for October 2024"));
    }

    @Test
    public void execute_noExistingBudget_addsNewBudget() {
        AddBudgetCommand command = new AddBudgetCommand(150.0,
                YearMonth.of(2024, 10), Category.FOOD);
        command.execute();

        Budget budget = BudgetManager.getBudget(YearMonth.of(2024, 10));
        assertNotNull(budget);
        assertEquals(150.0, budget.getTotalMonthlyBudget());
    }

    @Test
    public void execute_existingBudget_updatesBudget() {
        // Add a budget
        AddBudgetCommand addCommand = new AddBudgetCommand(100.0,
                YearMonth.of(2024, 10), Category.FOOD);
        addCommand.execute();

        // Add another amount to the existing budget
        AddBudgetCommand updateCommand = new AddBudgetCommand(50.0,
                YearMonth.of(2024, 10), Category.FOOD);
        updateCommand.execute();

        Budget budget = BudgetManager.getBudget(YearMonth.of(2024, 10));
        assertNotNull(budget);
        assertEquals(150.0, budget.getTotalMonthlyBudget());
    }
}
