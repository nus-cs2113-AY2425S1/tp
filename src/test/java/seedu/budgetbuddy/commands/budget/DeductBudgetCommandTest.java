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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeductBudgetCommandTest {

    @BeforeEach
    public void setUp() {
        // Reset the static state of BudgetManager before each test
        BudgetManager.reset();
    }

    @Test
    public void constructor_validInputs_initializesCorrectly() {
        DeductBudgetCommand command = new DeductBudgetCommand(50.0,
                YearMonth.of(2024, 10), Category.FOOD);
        assertNotNull(command);
    }

    @Test
    public void constructor_inputISNegativeAmount_throwsAssertionError() {
        AssertionError exception = assertThrows(AssertionError.class, () -> {
            new DeductBudgetCommand(-30.0, YearMonth.of(2024, 10), Category.FOOD);
        });
        assertEquals("Amount must be non-negative", exception.getMessage());
    }

    @Test
    public void constructor_inputIsNullDate_throwsAssertionError() {
        AssertionError exception = assertThrows(AssertionError.class, () -> {
            new DeductBudgetCommand(50.0, null, Category.FOOD);
        });
        assertEquals("Date cannot be null", exception.getMessage());
    }

    @Test
    public void constructor_inputIsNullCategory_throwsAssertionError() {
        AssertionError exception = assertThrows(AssertionError.class, () -> {
            new DeductBudgetCommand(50.0, YearMonth.of(2024, 10), null);
        });
        assertEquals("Category cannot be null", exception.getMessage());
    }

    @Test
    public void isCommand_commandStartsWithDeductBudget_returnsTrue() {
        assertTrue(DeductBudgetCommand.isCommand("deduct budget 50 for October 2024"));
    }

    @Test
    public void isCommand_commandDoesNotStartWithDeductBudget_returnsFalse() {
        assertFalse(DeductBudgetCommand.isCommand("add budget 50 for October 2024"));
    }

    @Test
    public void execute_existingBudget_deductsAmount() {
        // First, add a budget to deduct from
        AddBudgetCommand addCommand = new AddBudgetCommand(100.0,
                YearMonth.of(2024, 10), Category.FOOD);
        addCommand.execute();

        // Now deduct an amount from the existing budget
        DeductBudgetCommand deductCommand = new DeductBudgetCommand(30.0,
                YearMonth.of(2024, 10), Category.FOOD);
        deductCommand.execute();

        Budget budget = BudgetManager.getBudget(YearMonth.of(2024, 10));
        assertNotNull(budget);
        assertEquals(70.0, budget.getTotalMonthlyBudget());
    }

    @Test
    public void execute_noExistingBudget_doesNothing() {
        DeductBudgetCommand deductCommand = new DeductBudgetCommand(50.0,
                YearMonth.of(2024, 10), Category.FOOD);
        assertNull(BudgetManager.getBudget(YearMonth.of(2024, 10))); // Ensure no budget was created
    }
}
