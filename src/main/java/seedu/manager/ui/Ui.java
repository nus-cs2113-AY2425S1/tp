package seedu.manager.ui;

/**
 * Represents the program's user interface
 */
public class Ui {
    public static final String WELCOME_MESSAGE = "Welcome to EventManagerCLI.";
    /**
     * Greets user upon program startup
     */
    public void greetUser() {
        System.out.println(WELCOME_MESSAGE);
    }
}
