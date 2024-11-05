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
}
