package wheresmymoney;

import wheresmymoney.exception.WheresMyMoneyException;

import java.util.Scanner;

public class Ui {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Displays a message.
     *
     * @param message The message to display.
     */
    public static void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Reads the user input.
     *
     * @return The user input as a String.
     */
    public static String getUserInput() {
        if (!scanner.hasNextLine()) {
            System.exit(0);
        }
        return scanner.nextLine();
    }

    public static void close(){
        scanner.close();
    }

    /**
     * Displays an Introductory message to the user
     */
    public static void displayIntroText() {
        displayMessage("WheresMyMoney");
        displayMessage("What can I do for you?");
    }

    /**
     * Displays the text prompting for a command
     */
    public static void displayCommandPrompt() {
        System.out.print("> ");
    }

    /**
     * Gets a command from the user
     */
    public static String getCommand(){
        Ui.displayCommandPrompt();
        return Ui.getUserInput();
    }

    /**
     * Display index, category, description, price, and date added of the expense passed to it
     *
     * @param expenseList Main expense list to retrieve expense indices
     * @param expense Expense to be displayed
     */
    public static void displayExpense(ExpenseList expenseList, Expense expense) throws WheresMyMoneyException {
        String index = expenseList.getIndexOf(expense) + 1 + ". ";
        String category = "CATEGORY: " + expense.getCategory();
        String description = "DESCRIPTION: " + expense.getDescription();
        String price = "PRICE: " + String.format("%.2f", expense.getPrice());
        String dateAdded = "DATE ADDED: " + DateUtils.dateFormatToString(expense.getDateAdded());
        Ui.displayMessage(index + category + ", " + description + ", " + price + ", " + dateAdded);
    }

    public static void displayRecurringExpense(RecurringExpenseList recurringExpenseList, 
            RecurringExpense recurringExpense) throws WheresMyMoneyException {
        String index = recurringExpenseList.getIndexOf(recurringExpense) + 1 + ". ";
        String category = "CATEGORY: " + recurringExpense.getCategory();
        String description = "   DESCRIPTION: " + recurringExpense.getDescription();
        String price = "   PRICE: " + String.format("%.2f", recurringExpense.getPrice());;
        String dateAdded = "   DATE ADDED: " + DateUtils.dateFormatToString(recurringExpense.getDateAdded());
        String lastAddedDate = "   LAST ADDED DATE: " + recurringExpense.getLastAddedDate();
        String frequency = "   FREQUENCY: " + recurringExpense.getFrequency();
        Ui.displayMessage(index + category + description + price + dateAdded + lastAddedDate + frequency);
    }
}
