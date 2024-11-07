//@@author glenda-1506
package seedu.spendswift;

public class UI {
    //public static final String SEPARATOR = "_".repeat(30);
    public static final String WELCOME_MESSAGE = "Hiya! How can I assist?";
    public static final String EXIT_MESSAGE = "Goodbye! :> Hope to see you again soon!";
    public static final String FILE_NOT_FOUND = "Data file not found.";
    public static final String DATA_LOADED_MESSAGE = "Data loaded successfully";
    public static final String DATA_SAVED_MESSAGE = "Data has been saved!";
    public static final String ERROR_LOADING_DATA_MESSAGE = "Error loading data: ";
    public static final String ERROR_SAVING_DATA_MESSAGE = "Error saving data: ";
    public static final String INVALID_INPUT_MESSAGE = "Please enter a valid command!";
    public static final String HELP_INTRO_MESSAGE = "Here are the commands available:";
    public static final String HELP_ADD_EXPENSE = "Add expense: add-expense n/NAME a/AMOUNT [c/CATEGORY]";
    public static final String HELP_DELETE_EXPENSE = "Delete expense: delete-expense e/INDEX";
    public static final String HELP_ADD_CATEGORY = "Add new category: add-category CATEGORY";
    public static final String HELP_TAG_EXPENSE = "Tag an expense to a category: tag-expense e/INDEX c/CATEGORY";
    public static final String HELP_VIEW_EXPENSES = "View all recorded expenses: view-expenses";
    public static final String HELP_SET_BUDGET = "Set a budget limit for a category: set-budget c/CATEGORY l/LIMIT";
    public static final String HELP_VIEW_BUDGET = "View current budget status for each category: view-budget";
    public static final String HELP_TOGGLE_RESET = "On/Off automatic budget reset: toggle-reset";
    public static final String HELP_EXIT = "Exit: bye";

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void printExitMessage() {
        System.out.println(EXIT_MESSAGE);
    }

    public void printDataLoaded() {
        System.out.println(DATA_LOADED_MESSAGE);
    }

    public void printDataSaved() {
        System.out.println(DATA_SAVED_MESSAGE);
    }

    public void printLoadingError(String errorMessage) {
        System.out.println(ERROR_LOADING_DATA_MESSAGE + errorMessage);
    }

    public void printSavingError(String errorMessage) {
        System.out.println(ERROR_SAVING_DATA_MESSAGE + errorMessage);
    }

    public void printParserInvalidInput() {
        System.out.println(INVALID_INPUT_MESSAGE);
    }

    public void printFileNotFound() {
        System.out.println(FILE_NOT_FOUND);
    }

    public void printHelpMessage() {
        System.out.println(HELP_INTRO_MESSAGE);
        System.out.println(HELP_ADD_EXPENSE);
        System.out.println(HELP_DELETE_EXPENSE);
        System.out.println(HELP_ADD_CATEGORY);
        System.out.println(HELP_TAG_EXPENSE);
        System.out.println(HELP_VIEW_EXPENSES);
        System.out.println(HELP_SET_BUDGET);
        System.out.println(HELP_VIEW_BUDGET);
        System.out.println(HELP_TOGGLE_RESET);
        System.out.println(HELP_EXIT);
    }
}
