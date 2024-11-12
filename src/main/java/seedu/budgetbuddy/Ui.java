package seedu.budgetbuddy;

import java.time.YearMonth;
import java.util.Scanner;

/**
 * The Ui class handles all user interactions by displaying messages to the user and
 * receiving input from them.
 */
public class Ui {

    public static final String SEPARATOR = "========================================================";
    public static final String WELCOME_MESSAGE = "Welcome to Budget Buddy!";
    public static final String EXIT_MESSAGE = "Bye!";
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Retrieves the user's command input. This method ensures that the user
     * does not submit an empty command by repeatedly prompting until valid input is provided.
     *
     * @return The user’s command as a trimmed {@code String}.
     */
    public static String getUserCommand() {
        String inputCommand;

        do {
            System.out.print("Enter commands: ");
            inputCommand = scanner.nextLine().trim();

            if (inputCommand.isEmpty()) {
                displayToUser("Input cannot be empty. Please try again.");
            }
        } while (inputCommand.isEmpty());
        return inputCommand;
    }

    /**
     * Retrieves the user's command input.
     * If an empty command is received, it will end the command and give an
     * acknowledgment message.
     *
     * @return The user’s command as a trimmed {@code String}.
     */
    public static String getUserEditFields(){
        String inputCommand;

        do {
            System.out.print("Enter edit Field: ");
            inputCommand = scanner.nextLine().trim();

            if (inputCommand.isEmpty()) {
                displayToUser("Empty Input Detected, Exiting change menu.");
                return "";
            }
        } while (inputCommand.isEmpty());
        return inputCommand;
    }

    /**
     * Displays a given message to the user, surrounded by separators for readability.
     *
     * @param message The message to display to the user.
     */
    public static void displayToUser(String message) {
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(SEPARATOR);
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public static void displayWelcomeMessage() {
        displayToUser(WELCOME_MESSAGE);
    }

    /**
     * Displays the exit message when the application ends.
     */
    public static void displayExitMessage() {
        displayToUser(EXIT_MESSAGE);
    }

    /**
     * Displays a message regarding a budget transaction, indicating updating the amount,
     * and shows the current total number of budgets.
     *
     * @param transaction The details of the budget transaction.
     * @param count The total number of budgets after the transaction.
     */
    public static void displayBudgetTransactionMessage(String transaction, int count) {
        String result = "The following budget amount has been updated:\n"
                + transaction + '\n'
                + "You have " + count + " budget(s) in total.";
        displayToUser(result);
    }

    /**
     * Displays a message indicating that a budget has been deleted because its amount has reached zero,
     * and shows the current total number of remaining budgets.
     *
     * @param month The month of the deleted budget.
     * @param count The total number of budgets after the deletion.
     */
    public static void displayBudgetDeletedMessage(YearMonth month, int count) {
        String result = "The following budget has been deleted as its amount reached zero:\n"
                + "Date: " + month + '\n'
                + "You have " + count + " budget(s) in total.";
        displayToUser(result);
    }

    /**
     * Displays entire help message, acts as a help guide for new users.
     */
    public static void displayHelpMessage() {
        // ANSI color codes for text formatting
        String reset = "\u001B[0m";
        String bold = "\u001B[1m";
        String green = "\u001B[32m";
        String yellow = "\u001B[33m";
        String cyan = "\u001B[36m";

        String message =
                bold + cyan + "=== BudgetBuddy Help Guide ===" + reset + "\n\n" +
                        bold + yellow + "Budget Commands:" + reset + "\n" +
                        green + "- add budget a/AMOUNT [m/MONTH] [c/CATEGORY]:" + reset + " Add a budget for the " +
                        "current month or a specified month/category." + "\n" +
                        green + "- deduct budget a/AMOUNT [m/MONTH] [c/CATEGORY]:" + reset + " Deduct from a budget" +
                        " for the current month or a specified month/category." + "\n" +
                        green + "- list budgets [m/MONTH]:" + reset + " List all budgets, or budgets for a specific" +
                        " month." + "\n" +
                        green + "- list remaining budget:" + reset + " List remaining budget after expenses for each" +
                        " month and category." + "\n\n" +

                        bold + yellow + "Expense Commands:" + reset + "\n" +
                        green + "- add expense DESCRIPTION a/AMOUNT [d/DATE] [c/CATEGORY]:" + reset + " Add an" +
                        " expense with a description, amount, date, and category." + "\n" +
                        green + "- delete expense INDEX:" + reset + " Delete an expense by its index." + "\n" +
                        green + "- list expenses [m/MONTH] [c/CATEGORY]:" + reset + " List all expenses, or filter" +
                        " by month or category." + "\n" +
                        green + "- edit expense INDEX [a/AMOUNT] [c/CATEGORY] [d/DATE]:" + reset + " Edit an " +
                        "existing expense by index and modify its amount, category, or date." + "\n" +
                        green + "- display monthly expenses y/YEAR:" + reset + " Display a chart of monthly " +
                        "expenses for a given year." + "\n" +
                        green + "- display expenses with categories m/MONTH:" + reset + " Display a pie chart of " +
                        "expenses by category for a given month." + "\n" +
                        green + "- search expenses KEYWORD(S):" + reset + " Search expenses by keyword(s)." + "\n" +
                        green + "- breakdown expenses:" + reset + " Display a breakdown of expenses by " +
                        "category." + "\n\n" +

                        bold + yellow + "Income Commands:" + reset + "\n" +
                        green + "- add income DESCRIPTION a/AMOUNT [d/DATE]:" + reset + " Add an income with a " +
                        "description, amount, and date." + "\n" +
                        green + "- delete income INDEX:" + reset + " Delete an income by its index." + "\n" +
                        green + "- edit income INDEX [a/AMOUNT] [d/DATE]:" + reset + " Edit an existing income by " +
                        "index and modify its amount or date." + "\n" +
                        green + "- list incomes [m/MONTH]:" + reset + " List all incomes or filter by month." + "\n" +
                        green + "- display income spent [m/MONTH]:" + reset + " Display income spent as a " +
                        "percentage of total income for the month." + "\n\n" +

                        bold + yellow + "Savings Commands:" + reset + "\n" +
                        green + "- display savings [m/]:" + reset + " Display the savings after deducting " +
                        "expenses from incomes." + "\n\n" +

                        // Explanation for optional and user input formatting
                        reset + bold + cyan + "[ ] is optional. Commands with [ ] allow you to choose whether to " +
                        "include that parameter." + reset + "\n" +
                        bold + cyan + "User input is represented in BOLD text, such as a/AMOUNT" + reset;
        displayToUser(message);
    }


    /**
     * Prints message to user if there is no description provided in search.
     */
    public static void searchEmptyMessage(){
        String result = "Please key in a valid descriptor to search.";
        displayToUser(result);
    }

    /**
     * Displays a message to the console.
     *
     * @param message The message to be displayed.
     */
    public static void showMessage(String message) {
        System.out.println(message);
    }
}
