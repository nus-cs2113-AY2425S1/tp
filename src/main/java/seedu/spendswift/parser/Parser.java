package seedu.spendswift.parser;

import seedu.spendswift.storage.Storage;
import seedu.spendswift.command.BudgetManager;
import seedu.spendswift.command.CategoryManager;
import seedu.spendswift.command.ExpenseManager;
import seedu.spendswift.command.TrackerData;
import seedu.spendswift.ui.UI;

import java.io.IOException;

public class Parser {
    private final ExpenseManager expenseManager;
    private final CategoryManager categoryManager;
    private final BudgetManager budgetManager;
    private final UI ui;
    private final Storage storage;

    public Parser(ExpenseManager expenseManager, CategoryManager categoryManager, BudgetManager budgetManager, UI ui,
                  Storage storage) {
        this.expenseManager = expenseManager;
        this.categoryManager = categoryManager;
        this.budgetManager = budgetManager;
        this.ui = ui;
        this.storage = storage;
    }

    public boolean parseCommand(String input, TrackerData trackerData) throws IOException {
        input = input.trim().toLowerCase();

        if (input.startsWith("add-expense")) {
            expenseManager.addExpenseRequest(input, trackerData);
            storage.saveData(trackerData);
        } else if (input.startsWith("delete-expense")) {
            expenseManager.deleteExpenseRequest(input, trackerData);
            storage.saveData(trackerData);
        } else if (input.startsWith("tag-expense")) {
            expenseManager.tagExpense(trackerData, input);
            storage.saveData(trackerData);
        } else if (input.startsWith("view-budget")) {
            budgetManager.viewBudget(trackerData);
        } else if (input.startsWith("add-category")) {
            categoryManager.addCategory(input, trackerData);
            storage.saveData(trackerData);
        } else if (input.startsWith("view-category")) {
            categoryManager.viewAllCategories(trackerData);
        } else if (input.startsWith("delete-category")) {
            categoryManager.deleteCategory(input, trackerData);
            storage.saveData(trackerData);
        } else if (input.startsWith("set-budget")) {
            budgetManager.setBudgetLimitRequest(input, budgetManager, trackerData);
            storage.saveData(trackerData);
        } else if (input.startsWith("view-expenses")) {
            expenseManager.viewExpensesByCategory(trackerData);
        } else if (input.startsWith("help")) {
            ui.printHelpMessage();
        } else if (input.startsWith("bye")) {
            ui.printExitMessage();
            storage.saveData(trackerData);
            return true;
        } else {
            ui.printParserInvalidInput();
        }

        return false;
    }
}