package seedu.duke.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.budget.Budget;
import seedu.duke.ui.AppUi;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the {@link BudgetLogic} class.
 */
class BudgetLogicTest {
    private Budget budget;
    private TestAppUi ui;
    private BudgetLogic budgetLogic;

    /**
     * Sets up a new {@link Budget}, {@link TestAppUi}, and {@link BudgetLogic} instance before each test.
     */
    @BeforeEach
    void setUp() {
        budget = new Budget();
        ui = new TestAppUi();
        budgetLogic = new BudgetLogic(budget, ui);
    }

    /**
     * Tests setting the budget when it is not already set. Verifies that the budget amount,
     * balance, and budget status are updated correctly, and that the budget message was displayed.
     */
    @Test
    void testSetBudgetWhenBudgetNotSet() {
        ui.setInputs("yes", "1000");

        budgetLogic.setBudget();

        assertTrue(budget.isBudgetSet());
        assertEquals(1000, budget.getBudgetAmount());
        assertEquals(1000, budget.getBalance());
        assertTrue(ui.wasSetBudgetMessageDisplayed());
    }

    /**
     * Tests setting the budget when it is already set. Verifies that the budget amount
     * and balance are updated to the new value.
     */
    @Test
    void testSetBudgetWhenBudgetAlreadySet() {
        budget.setBudgetAmount(1000);
        ui.setInputs("yes", "1500");

        budgetLogic.setBudget();

        assertTrue(budget.isBudgetSet());
        assertEquals(1500, budget.getBudgetAmount());
        assertEquals(1500, budget.getBalance());
    }

    /**
     * Tests handling of invalid input during budget setting. Ensures the budget is set
     * correctly after the invalid input is corrected.
     */
    @Test
    void testHandleSetBudgetWithInvalidInput() {
        ui.setInputs("yes", "invalid", "1000");

        budgetLogic.handleSetBudget();

        assertEquals(1000, budget.getBudgetAmount());
    }

    /**
     * Tests modifying the balance to a higher amount. Ensures the balance reflects the new amount.
     */
    @Test
    void testModifyBalance() {
        budget.setBudgetAmount(1000);
        budgetLogic.modifyBalance(200);

        assertEquals(1200, budget.getBalance());
    }

    /**
     * Tests modifying the balance to a lower amount. Ensures the balance reflects the decreased amount.
     */
    @Test
    void testModifyBalanceNegative() {
        budget.setBudgetAmount(1000);
        budgetLogic.modifyBalance(-200);

        assertEquals(800, budget.getBalance());
    }

    /**
     * A simple implementation of {@link AppUi} for testing purposes.
     * It provides a list of predefined inputs and tracks if a message was displayed.
     */
    private static class TestAppUi extends AppUi {
        private String[] inputs;
        private int inputIndex = 0;
        private boolean setBudgetMessageDisplayed = false;

        /**
         * Sets the sequence of inputs to simulate user interaction.
         *
         * @param inputs the inputs to be returned by {@code getUserInput}.
         */
        void setInputs(String... inputs) {
            this.inputs = inputs;
            this.inputIndex = 0;
        }

        /**
         * Returns the next user input in the predefined sequence.
         *
         * @return the next input string.
         */
        @Override
        public String getUserInput() {
            return inputs[inputIndex++];
        }

        /**
         * Simulates displaying the set budget message.
         */
        @Override
        public void displaySetBudgetMessage() {
            setBudgetMessageDisplayed = true;
        }

        /**
         * Checks if the set budget message was displayed.
         *
         * @return true if the message was displayed, false otherwise.
         */
        boolean wasSetBudgetMessageDisplayed() {
            return setBudgetMessageDisplayed;
        }
    }
}
