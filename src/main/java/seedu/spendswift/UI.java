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
}
