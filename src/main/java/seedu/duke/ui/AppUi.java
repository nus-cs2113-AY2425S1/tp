package seedu.duke.ui;

import java.util.Scanner;

/**
 * The AppUi class handles all user interactions for the FinanceBuddy application.
 * It manages displaying messages and receiving user input.
 */
public class AppUi {
    private final Scanner scanner;

    /**
     * Constructs an AppUi instance and initializes the Scanner for user input.
     */
    public AppUi() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Gets the user's input from the console.
     *
     * @return A string representing the user's input.
     */
    public String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Displays the welcome message and application logo when FinanceBuddy starts.
     */
    public void displayWelcomeMessage() {
        final String logo = " ______  _                                   ____              _      _        \n" +
                "|  ____|(_)                                 |  _ \\            | |    | |       \n" +
                "| |__    _  _ __    __ _  _ __    ___   ___ | |_) | _   _   __| |  __| | _   _ \n" +
                "|  __|  | || '_ \\  / _` || '_ \\  / __| / _ \\|  _ < | | | | / _` | / _` || | | |\n" +
                "| |     | || | | || (_| || | | || (__ |  __/| |_) || |_| || (_| || (_| || |_| |\n" +
                "|_|     |_||_| |_| \\__,_||_| |_| \\___| \\___||____/  \\__,_| \\__,_| \\__,_| \\__, |\n" +
                "                                                                          __/ |\n" +
                "                                                                         |___/ \n";
        final String welcomeMessage = "--------------------------------------------\n" +
                "Welcome to FinanceBuddy!\n" +
                "Your one stop solution for financial peace of mind\n" +
                "How can I help you today? :)\n" +
                "--------------------------------------------\n";

        System.out.println(logo);
        System.out.println(welcomeMessage);
    }

    /**
     * Displays the set budget message when FinanceBuddy starts.
     * Asks if user would like to set a budget
     */
    public void displaySetBudgetMessage() {
        System.out.println("--------------------------------------------");
        System.out.println("Would you like to set a budget? (yes/no)");
        System.out.println("--------------------------------------------");
    }

    /**
     * Displays the modify budget message when user keys in the budget command.
     * Asks if user would like to modify their existing budget
     */
    public void displayModifyBudgetMessage(double amount) {
        System.out.println("--------------------------------------------");
        System.out.println("Your current budget is: " + String.format("$ %.2f", amount));
        System.out.println("Would you like to modify your budget? (yes/no)");
        System.out.println("--------------------------------------------");
    }

    /**
     * Displays the balance of the user's budget
     */
    public void displayBudgetBalanceMessage(double amount) {
        System.out.println("Your current monthly balance is: " + String.format("$ %.2f", amount));
        System.out.println("--------------------------------------------");
    }

    /**
     * Displays warning that budget has been exceeded
     */
    public void displayBudgetBalanceExceededMessage(double amount) {
        System.out.println("You have exceeded your monthly budget of: " + String.format("$ %.2f", amount) +"!");
    }

    /**
     * Displays a message indicating that an unrecognized command was entered.
     * Suggests using the "help" command to list valid commands.
     */
    public void showUnknownCommandMessage() {
        final String unrecognizedCommand = "--------------------------------------------\n" +
                "Unrecognized command!\n" +
                "Use the command \"help\" for a list of valid commands\n" +
                "--------------------------------------------\n";
        System.out.println(unrecognizedCommand);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showErrorMessage(String message) {
        System.out.println("Error: " + message);
    }
}
