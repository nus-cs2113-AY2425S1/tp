package seedu.duke.ui;

import seedu.duke.financial.FinancialList;
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
     * Matches a given command with its corresponding action.
     *
     * @param command          The command input by the user.
     * @param commandArguments A map of arguments parsed from the user's input.
     * @return A boolean indicating whether the command was successful.
     */
    public boolean matchCommand(String command, HashMap<String, String> commandArguments) {
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
    }
}
