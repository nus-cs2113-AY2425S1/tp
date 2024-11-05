package seedu.duke;

public class Parser {
    private final ExpenseManager expenseManager;
    private final CategoryManager categoryManager;
    private final BudgetManager budgetManager;

    public Parser(ExpenseManager expenseManager, CategoryManager categoryManager, BudgetManager budgetManager) {
        this.expenseManager = expenseManager;
        this.categoryManager = categoryManager;
        this.budgetManager = budgetManager;
    }

    public boolean parseCommand(String input, TrackerData trackerData) {
        input = input.trim();

        if (input.startsWith("add-expense")) {
            expenseManager.addExpenseRequest(input, expenseManager, trackerData);
        } else if (input.startsWith("add-category")) {
            categoryManager.addCategory(trackerData, input);
        } else if (input.startsWith("delete-expense")) {
            expenseManager.deleteExpenseRequest(input, expenseManager, trackerData);
        } else if (input.startsWith("tag-expense")) {
            expenseManager.tagExpense(trackerData, input);
        } else if (input.equalsIgnoreCase("view-budget")) {
            budgetManager.viewBudget(trackerData);
        } else if (input.startsWith("set-budget")) {
            budgetManager.setBudgetLimitRequest(input, budgetManager, trackerData);
        } else if (input.startsWith("view-expenses")) {
            expenseManager.viewExpensesByCategory(trackerData);
        } else if (input.equalsIgnoreCase("toggle-reset")) {
            budgetManager.toggleAutoReset();
        } else if (input.equalsIgnoreCase("bye")) {
            System.out.println("Goodbye! :> Hope to see you again soon!");
            return true;
        } else {
            System.out.println("Invalid input! Try again.");
        }

        return false;
    }
}
