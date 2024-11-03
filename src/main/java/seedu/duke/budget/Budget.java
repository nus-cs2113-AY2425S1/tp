package seedu.duke.budget;

import java.time.LocalDate;

public class Budget {
    private double budgetAmount;
    private double balance;
    private boolean isBudgetSet;
    private LocalDate budgetSetDate;

    public Budget() {
        this.budgetAmount = 0;
        this.balance = 0;
        this.isBudgetSet = false;
        this.budgetSetDate = null;
    }

    public double getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(double newBudgetAmount) {
        this.budgetAmount = newBudgetAmount;
        this.balance = newBudgetAmount;
        this.isBudgetSet = true;
        this.budgetSetDate = LocalDate.now();
    }

    public boolean isBudgetSet() {
        return isBudgetSet;
    }

    public double getBalance() {
        return balance;
    }

    public void updateBalance(double newBalance) {
        this.balance = newBalance;
    }
}
