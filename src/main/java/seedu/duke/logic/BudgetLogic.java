package seedu.duke.logic;

import seedu.duke.budget.Budget;
import seedu.duke.ui.AppUi;

/**
 * Handles the logic related to setting and modifying the budget.
 */
public class BudgetLogic {
    public final Budget budget;
    private final AppUi ui;

    /**
     * Constructs a BudgetLogic instance with the specified Budget and AppUi.
     *
     * @param budget the budget instance to be managed.
     * @param ui     the user interface instance for interacting with the user.
     */
    public BudgetLogic(Budget budget, AppUi ui) {
        this.budget = budget;
        this.ui = ui;
    }

    /**
     * Sets the budget if it has not been set. If the budget is already set,
     * prompts the user to confirm whether they want to modify it.
     */
    public void setBudget() {
        if (!budget.isBudgetSet()) {
            ui.displaySetBudgetMessage();
            handleSetBudget();
        } else {
            System.out.println("Your current budget is: " + budget.getBudgetAmount());
            System.out.println("Would you like to modify your budget? (yes/no)");
            handleSetBudget();
        }
    }

    /**
     * Handles the process of setting the budget amount by receiving input from the user.
     * Validates that the input is a non-negative number before setting the budget.
     */
    public void handleSetBudget() {
        String input = ui.getUserInput();
        if (input.equalsIgnoreCase("yes")) {
            System.out.println("Please set your budget amount:");

            double amount = 0;
            boolean isValid = false;

            while (!isValid) {
                try {
                    String amountInput = ui.getUserInput();
                    amount = Double.parseDouble(amountInput);

                    if (amount >= 0) {
                        isValid = true;
                    } else {
                        System.out.println("Budget amount cannot be negative. Please enter a valid amount:");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number:");
                }
            }

            budget.setBudgetAmount(amount);
        }
    }

    /**
     * Modifies the balance of the budget by calculating the difference between
     * the specified amount and the current balance, then updating the budget balance accordingly.
     *
     * @param amount the new amount to update the balance to.
     */
    public void modifyBalance(double amount) {
        double currentBalance = budget.getBalance();
        // if difference is positive, net increase
        // if difference is negative, net decrease
        double difference = amount - currentBalance;
        double newBalance = amount + difference;
        budget.updateBalance(newBalance);
    }
}
