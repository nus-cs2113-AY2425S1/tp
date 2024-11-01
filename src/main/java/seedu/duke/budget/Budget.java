package seedu.duke.budget;

public class Budget {
    private double budgetAmount;
    private double balance;

    public Budget(double budgetAmount, double balance) {
        this.budgetAmount = budgetAmount;
        this.balance = balance;
    }

    public double getBudgetAmount() {
        return budgetAmount;
    }

    public void updateBudgetAmount(double newBudgetAmount) {
        this.budgetAmount = newBudgetAmount;
    }

    public double getBalance() {
        return balance;
    }

    public void updateBalance(double newBalance) {
        this.balance = newBalance;
    }
}
