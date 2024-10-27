package seedu.duke.ui;

import seedu.duke.command.AddExpenseCommand;
import seedu.duke.command.AddIncomeCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.EditEntryCommand;
import seedu.duke.command.SeeAllEntriesCommand;
import seedu.duke.command.SeeAllExpensesCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.exception.FinanceBuddyException;
import seedu.duke.financial.FinancialEntry;
import seedu.duke.financial.FinancialList;
import seedu.duke.parser.DateParser;
import seedu.duke.logic.Logic;
import seedu.duke.parser.InputParser;

import seedu.duke.storage.Storage;
import java.time.LocalDate;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Represents the user interface (UI) of the FinanceBuddy application.
 * Responsible for interacting with the user, capturing inputs,
 * and processing commands related to financial tracking.
 */
public class AppUi {
    public FinancialList financialList;
    public Storage mainStorage;

    /**
     * Constructor to initialize the AppUi object.
     * Initializes an empty FinancialList.
     */
    public AppUi() {
        financialList = new FinancialList();
    }



    /**
     * Matches a given command with its corresponding action.
     *
     * @param command          The command input by the user.
     * @param commandArguments A map of arguments parsed from the user's input.
     * @return A boolean indicating whether the command was successful.
     */
    public boolean matchCommand(String command, HashMap<String, String> commandArguments) throws FinanceBuddyException {

        final String unrecognizedCommand = "--------------------------------------------\n" +
                "Unrecognized command!\n" +
                "Use the command \"help\" for a list of valid commands\n" +
                "--------------------------------------------\n";

        Logic logic = new Logic(financialList);
        switch (command) {
        case "list":
            logic.listHelper(commandArguments);
            break;

        case "expense":
            logic.addExpense(commandArguments);
            this.mainStorage.update(financialList);
            break;

        case "income":
            logic.addIncome(commandArguments);
            this.mainStorage.update(financialList);
            break;

        case "edit":
            logic.editEntry(commandArguments);
            this.mainStorage.update(financialList);
            break;

        case "delete":
            logic.deleteEntry(commandArguments);
            this.mainStorage.update(financialList);
            break;

        case "help":
            logic.printHelpMenu();
            break;

        case "exit":
            ExitCommand exitCommand = new ExitCommand();
            exitCommand.execute(financialList);
            return exitCommand.shouldContinueLoop();

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
        HashMap<String, String> commandArguments = null;
        String input;
        Scanner scanner = new Scanner(System.in);
        String command = null;

        boolean isAcceptingInput = true;

        while (isAcceptingInput) {
            input = scanner.nextLine();
            try {
                commandArguments = InputParser.parseCommands(input);
                command = commandArguments.get(InputParser.COMMAND);
            } catch (FinanceBuddyException e) {
                System.out.println(e.getMessage());
            }

            try {
                isAcceptingInput = matchCommand(command, commandArguments);
            } catch (FinanceBuddyException e) {
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

    public void setStorage(Storage storage, boolean loadFromFile) {
        this.mainStorage = storage;
        if (loadFromFile) {
            this.financialList = mainStorage.loadFromFile();
        }
    }

    /**
     * Runs the main application logic.
     * Displays the welcome message, initializes the storage object,
     * and starts the command entry process.
     */
    public void run() {
        displayWelcomeMessage();
        commandEntry();
    }
}
