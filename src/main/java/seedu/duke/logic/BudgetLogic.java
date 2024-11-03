package seedu.duke.logic;

import seedu.duke.budget.Budget;
import seedu.duke.ui.AppUi;

public class BudgetLogic {
    public final Budget budget;
    private final AppUi ui;

    public BudgetLogic(Budget budget, AppUi ui) {
        this.budget = budget;
        this.ui = ui;
    }

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

    public void modifyBalance(double amount) {
        double currentBalance = budget.getBalance();
        // if difference is positive, net increase
        // if difference is negative, net decrease
        double difference = amount - currentBalance;
        double newBalance = amount + difference;
        budget.updateBalance(newBalance);
    }
}
