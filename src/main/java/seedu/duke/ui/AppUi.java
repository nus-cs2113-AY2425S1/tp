package seedu.duke.ui;

import seedu.duke.util.Commons;

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
        final String welcomeMessage = Commons.LINE_SEPARATOR + "\n" +
                "Welcome to FinanceBuddy!\n" +
                "Your one stop solution for financial peace of mind\n" +
                "How can I help you today? :)\n" +
                Commons.LINE_SEPARATOR + "\n";

        System.out.println(logo);
        System.out.println(welcomeMessage);
    }

    /**
     * Displays the set budget message when FinanceBuddy starts.
     * Asks if user would like to set a budget
     */
    public void displaySetBudgetMessage() {
        System.out.println(Commons.LINE_SEPARATOR);
        System.out.println("Would you like to set a new budget? (yes/no)");
        System.out.println(Commons.LINE_SEPARATOR);
    }

    /**
     * Displays the modify budget message when user keys in the budget command.
     * Asks if user would like to modify their existing budget
     */
    public void displayBudgetResetMessage() {
        System.out.println(Commons.LINE_SEPARATOR);
        System.out.println("Budget has been deleted.");
        System.out.println(Commons.LINE_SEPARATOR);
    }

    /**
     * Displays the balance of the user's budget
     */
    public void displayBudgetBalanceMessage(double amount) {
        System.out.println("Your current monthly balance is: " + String.format("$ %.2f", amount));
        System.out.println(Commons.LINE_SEPARATOR);
    }

    /**
     * Displays number of entries deleted by using delete all
     */
    public void displayDeleteAllMessage(int totalEntries) {
        System.out.println(Commons.LINE_SEPARATOR);
        if (totalEntries > 1) {
            System.out.println("Okay! A total of " + totalEntries + " entries have been deleted.");
        } else {
            System.out.println("Okay! 1 entry has been deleted.");
        }
        System.out.println(Commons.LINE_SEPARATOR);
    }

    /**
     * Displays number of entries deleted by using delete all
     */
    public void displayEmptyListMessage() {
        System.out.println(Commons.LINE_SEPARATOR);
        System.out.println("The list is already empty.");
        System.out.println(Commons.LINE_SEPARATOR);
    }


    /**
     * Displays warning that budget has been exceeded
     */
    public void displayBudgetBalanceExceededMessage(double amount) {
        System.out.println("You have exceeded your monthly budget of: " + String.format("$ %.2f", amount) +"!");
    }

    /**
     * Displays a message indicating the budget has been successfully set.
     */
    public void displayBudgetSetMessage(double budget, double balance) {
        System.out.println(Commons.LINE_SEPARATOR);
        System.out.println("Your budget has successfully been set to: " +
                String.format("$ %.2f", budget));
        System.out.println("Your current monthly balance is: " +
                String.format("$ %.2f", balance));
        System.out.println(Commons.LINE_SEPARATOR);
    }

    /**
     * Displays a message indicating that an unrecognized command was entered.
     * Suggests using the "help" command to list valid commands.
     */
    public void showUnknownCommandMessage() {
        final String unrecognizedCommand = Commons.LINE_SEPARATOR + "\n" +
                "Unrecognized command!\n" +
                "Use the command \"help\" for a list of valid commands\n" +
                Commons.LINE_SEPARATOR + "\n";
        System.out.println(unrecognizedCommand);
    }
}
