package seedu.duke.ui;

import seedu.duke.financial.Expense;
import seedu.duke.financial.FinancialEntry;
import seedu.duke.financial.FinancialList;
import seedu.duke.financial.Income;
import seedu.duke.parser.InputParser;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Represents the user interface (UI) of the FinanceBuddy application.
 * Responsible for interacting with the user, capturing inputs,
 * and processing commands related to financial tracking.
 */
public class AppUi {
    public FinancialList financialList;

    /**
     * Constructor to initialize the AppUi object.
     * Initializes an empty FinancialList.
     */
    public AppUi() {
        financialList = new FinancialList();
    }

    /**
     * Adds a financial entry to the financial list.
     *
     * @param entry The financial entry to be added.
     */
    public void addEntry(FinancialEntry entry) {
        financialList.addEntry(entry);

        System.out.println("--------------------------------------------");
        System.out.println("Okay! I have added this entry: ");
        System.out.println(entry);
        System.out.printf("You currently have %d transactions logged\n", financialList.getEntryCount());
        System.out.println("--------------------------------------------");
    }

    /**
     * Adds an expense entry to the financial list using the command arguments.
     *
     * @param commandArguments The parsed command arguments containing the expense details.
     */
    public void addExpense(HashMap<String, String> commandArguments) {
        String description = commandArguments.get("argument");
        double amount = Double.parseDouble(commandArguments.get("/a"));

        // TODO: add date and exception handling

        Expense entry = new Expense(amount, description);
        addEntry(entry);
    }

    /**
     * Adds an income entry to the financial list using the command arguments.
     *
     * @param commandArguments The parsed command arguments containing the income details.
     */
    public void addIncome(HashMap<String, String> commandArguments) {
        String description = commandArguments.get("argument");
        double amount = Double.parseDouble(commandArguments.get("/a"));

        // TODO: add date and exception handling

        Income entry = new Income(amount, description);
        addEntry(entry);
    }

    /**
     * Edits an existing financial entry at a specified index with new values.
     *
     * @param commandArguments The parsed command arguments containing the new details for the entry.
     */
    public void editEntry(HashMap<String, String> commandArguments) {
        int index = Integer.parseInt(commandArguments.get("argument"));

        FinancialEntry entry = financialList.getEntry(index);

        String amountStr = commandArguments.get("/a");
        double amount = (amountStr != null) ? Double.parseDouble(amountStr) : entry.getAmount();

        String description = commandArguments.getOrDefault("/des", entry.getDescription());

        financialList.editEntry(index, amount, description);

        System.out.println("--------------------------------------------");
        System.out.println("Okay! I have edited this entry: ");
        System.out.println(entry);
        System.out.println("Edited entry: ");
        System.out.println(financialList.getEntry(index));
        System.out.println("--------------------------------------------");
    }

    /**
     * Deletes an entry from the financial list based on the specified index.
     *
     * @param commandArguments The parsed command arguments containing the index of the entry to delete.
     */
    public void deleteEntry(HashMap<String, String> commandArguments) {
        int index = Integer.parseInt(commandArguments.get("argument")) - 1;
        FinancialEntry entry = financialList.getEntry(index);

        financialList.deleteEntry(index);

        System.out.println("--------------------------------------------");
        System.out.println("Okay! The following entry has been deleted: ");
        System.out.println(entry);
        System.out.println("--------------------------------------------");
    }

    /**
     * Lists all the financial entries in the financial list.
     */
    public void listEntries() {
        System.out.println("--------------------------------------------");
        System.out.println("Here is the list of all logged transactions: ");
        for (int i = 0; i < financialList.getEntryCount(); i++) {
            System.out.println((i-1) + ". " + financialList.getEntry(i).toString());
        }
        System.out.println("--------------------------------------------");
    }

    /**
     * Matches a given command with its corresponding action.
     *
     * @param command          The command input by the user.
     * @param commandArguments A map of arguments parsed from the user's input.
     * @return A boolean indicating whether the command was successful.
     */
    public boolean matchCommand(String command, HashMap<String, String> commandArguments) {
        final String goodByeMessage = "--------------------------------------------\n" +
                "Goodbye! Hope to see you again soon :)\n" +
                "--------------------------------------------\n";

        final String unrecognizedCommand = "--------------------------------------------\n" +
                "Unrecognized command!\n" +
                "Use the command \"help\" for a list of valid commands\n" +
                "--------------------------------------------\n";

        final String helpMenu = "--------------------------------------------\n" +
                "List of commands:\n" +
                "--------------------------------------------\n" +
                "1. list\n " +
                "   - Shows a list of all logged transactions\n" +
                "2. expense DESCRIPTION /a AMOUNT [/d DATE]\n " +
                "   - Adds a new expense\n" +
                "3. income DESCRIPTION /a AMOUNT [/d DATE]\n " +
                "   - Adds a new income\n" +
                "4. edit INDEX [/des DESCRIPTION] [/a AMOUNT] [/d DATE]\n " +
                "   - Edits the transaction at the specified INDEX\n" +
                "5. delete INDEX\n " +
                "   - Deletes the transaction at the specified INDEX\n" +
                "6. exit\n " +
                "   - Exits the program\n" +
                "7. help\n " +
                "   - Shows a list of all valid commands\n" +
                "--------------------------------------------\n";

        switch (command) {
            case "list":
                listEntries();
                break;

            case "expense":
                addExpense(commandArguments);
                break;

            case "income":
                addIncome(commandArguments);
                break;

            case "edit":
                editEntry(commandArguments);
                break;

            case "delete":
                deleteEntry(commandArguments);
                break;

            case "help":
                System.out.println(helpMenu);
                break;

            case "exit":
                System.out.println(goodByeMessage);
                return false;

            default:
                System.out.println(unrecognizedCommand);
                break;
        }

        return true;
    }

    /**
     * Continuously prompts the user for input and processes commands.
     * It runs in a loop until the input signifies to stop accepting commands.
     */
    public void commandEntry() {
        HashMap<String, String> commandArguments;
        String input;
        Scanner scanner = new Scanner(System.in);

        boolean isAcceptingInput = true;

        while (isAcceptingInput) {
            input = scanner.nextLine();
            commandArguments = InputParser.parseCommands(input);
            String command = commandArguments.get(InputParser.COMMAND);

            try {
                isAcceptingInput = matchCommand(command, commandArguments);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Displays the welcome message and the application logo to the user
     * upon starting the application.
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
     * Runs the application, starting with displaying the welcome message.
     */
    public void run() {
        displayWelcomeMessage();
        commandEntry();
    }
}
