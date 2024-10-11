package seedu.duke.ui;

import seedu.duke.financial.FinancialList;
import seedu.duke.parser.InputParser;

import java.util.HashMap;
import java.util.Scanner;

public class AppUi {
    public FinancialList financialList;

    public AppUi() {
        financialList = new FinancialList();
    }

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
                "1. list - Shows a list of all logged transactions\n" +
                "2. expense DESCRIPTION /a AMOUNT [/d DATE] - Adds a new expense\n" +
                "3. income DESCRIPTION /a AMOUNT [/d DATE] - Adds a new income\n" +
                "4. edit INDEX [/des DESCRIPTION] [/a AMOUNT] [/d DATE] - Edits the transaction at the specified INDEX\n" +
                "5. delete INDEX - Deletes the transaction at the specified INDEX\n" +
                "6. exit - Exits the program\n" +
                "7. help - Shows a list of all valid commands\n" +
                "--------------------------------------------\n";

        switch (command) {
            case "list":
                for (int i = 0; i < financialList.getEntryCount(); i++) {
                    System.out.println(financialList.getEntry(i).toString());
                }
                break;

            case "expense":
                System.out.println("expense");
                break;

            case "income":
                System.out.println("income");
                break;

            case "edit":
                System.out.println("edit");
                break;

            case "delete":
                System.out.println("delete");
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

    public void run() {
        displayWelcomeMessage();
        commandEntry();
    }
}
