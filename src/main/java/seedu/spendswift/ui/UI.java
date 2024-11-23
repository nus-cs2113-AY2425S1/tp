//@@author glenda-1506
package seedu.spendswift.ui;

public class UI {
    public static final String SEPARATOR = "_".repeat(120);
    private static final String WELCOME_MESSAGE = "Hiya! How can I assist?";
    private static final String EXIT_MESSAGE = "Goodbye! :> Hope to see you again soon!";
    private static final String EXPENSE_FILE_NOT_FOUND = "Expense data file not found.";
    private static final String CATEGORY_FILE_NOT_FOUND = "Category data file not found.";
    private static final String ERROR_LOADING_DATA_MESSAGE = "Error loading data: ";
    private static final String INVALID_INPUT_MESSAGE = "Please enter a valid command! " +
            "Use the help command to find valid command.";
    private static final String HELP_INTRO_MESSAGE = "Here are the commands available:";
    private static final String HELP_ADD_EXPENSE = "Add expense: add-expense n/NAME a/AMOUNT c/CATEGORY";
    private static final String HELP_DELETE_EXPENSE = "Delete expense: delete-expense e/INDEX";
    private static final String HELP_ADD_CATEGORY = "Add new category: add-category c/CATEGORY";
    private static final String HELP_DELETE_CATEGORY = "Delete new category: delete-category c/CATEGORY";
    private static final String HELP_TAG_EXPENSE = "Tag an expense to a category: tag-expense e/INDEX c/CATEGORY";
    private static final String HELP_VIEW_EXPENSES = "View all recorded expenses: view-expenses";
    private static final String HELP_VIEW_CATEGORY = "View all categories: view-category";
    private static final String HELP_SET_BUDGET = "Set a budget limit for a category: set-budget c/CATEGORY l/LIMIT";
    private static final String HELP_VIEW_BUDGET = "View current budget status for each category: view-budget";
    private static final String HELP_EXIT = "Exit: bye";

    public void printWelcomeMessage() {
        System.out.println(SEPARATOR);
        System.out.println(WELCOME_MESSAGE);
        System.out.println(SEPARATOR);
    }

    public void printExitMessage() {
        System.out.println(SEPARATOR);
        System.out.println(EXIT_MESSAGE);
        System.out.println(SEPARATOR);
    }

    public void printLoadingError(String errorMessage) {
        System.out.println(SEPARATOR);
        System.out.println(ERROR_LOADING_DATA_MESSAGE + errorMessage);
    }

    public void printParserInvalidInput() {
        System.out.println(SEPARATOR);
        System.out.println(INVALID_INPUT_MESSAGE);
        System.out.println(SEPARATOR);
    }

    public void printExpenseFileNotFound() {
        System.out.println(SEPARATOR);
        System.out.println(EXPENSE_FILE_NOT_FOUND);
    }

    public void printCategoryFileNotFound() {
        System.out.println(SEPARATOR);
        System.out.println(CATEGORY_FILE_NOT_FOUND);
    }

    public void printInvalidExpenseLineLoad(String line) {
        System.out.println(SEPARATOR);
        System.out.println("Invalid expense format, skipping line: " + line);
    }

    public  void printInvalidCategoryLineLoad(String line) {
        System.out.println(SEPARATOR);
        System.out.println("Invalid budget format in category file, skipping line: " + line);
    }

    public void printUndefinedCategory(String categoryName) {
        System.out.println(SEPARATOR);
        System.out.println("Warning: Expense has an undefined category. Adding category: " +
                categoryName);
    }

    public void printHelpMessage() {
        System.out.println(SEPARATOR);
        System.out.println(HELP_INTRO_MESSAGE);
        System.out.println(HELP_ADD_EXPENSE);
        System.out.println(HELP_DELETE_EXPENSE);
        System.out.println(HELP_ADD_CATEGORY);
        System.out.println(HELP_DELETE_CATEGORY);
        System.out.println(HELP_TAG_EXPENSE);
        System.out.println(HELP_VIEW_EXPENSES);
        System.out.println(HELP_VIEW_CATEGORY);
        System.out.println(HELP_SET_BUDGET);
        System.out.println(HELP_VIEW_BUDGET);
        System.out.println(HELP_EXIT);
        System.out.println(SEPARATOR);
    }
}
