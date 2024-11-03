package seedu.duke.budget;

public class Budget {
    private double budgetAmount;
    private double balance;
    private boolean isBudgetSet;

    public Budget() {
        this.budgetAmount = 0;
        this.balance = 0;
        this.isBudgetSet = false;
    }

    public Budget(double budgetAmount) {
        this.budgetAmount = budgetAmount;
        this.balance = budgetAmount;
        this.isBudgetSet = true;
    }

    public double getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(double newBudgetAmount) {
        this.budgetAmount = newBudgetAmount;
        this.balance = newBudgetAmount; // Initialize balance when budget is set
        this.isBudgetSet = true;
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
