package seedu.duke;

import seedu.duke.storage.Storage;
import seedu.duke.ui.AppUi;
import seedu.duke.logic.Logic;
import seedu.duke.parser.InputParser;
import seedu.duke.financial.FinancialList;
import seedu.duke.exception.FinanceBuddyException;
import seedu.duke.command.ExitCommand;

import java.util.HashMap;

public class FinanceBuddy {
    public static void main(String[] args) {
        AppUi ui = new AppUi();
        Storage storage = new Storage();

        FinancialList financialList = storage.loadFromFile();
        Logic logic = new Logic(financialList);

        ui.displayWelcomeMessage();

        boolean isRunning = true;
        while (isRunning) {
            String userInput = ui.getUserInput();
            HashMap<String, String> commandArguments;
            String command;

            try {
                commandArguments = InputParser.parseCommands(userInput);
                command = commandArguments.get(InputParser.COMMAND);

                switch (command) {
                case "list":
                    logic.listHelper(commandArguments);
                    break;
                case "expense":
                    logic.addExpense(commandArguments);
                    storage.update(financialList);
                    break;
                case "income":
                    logic.addIncome(commandArguments);
                    storage.update(financialList);
                    break;
                case "edit":
                    logic.editEntry(commandArguments);
                    storage.update(financialList);
                    break;
                case "delete":
                    logic.deleteEntry(commandArguments);
                    storage.update(financialList);
                    break;
                case "help":
                    logic.printHelpMenu();
                    break;
                case "exit":
                    ExitCommand exitCommand = new ExitCommand();
                    exitCommand.execute(financialList);
                    isRunning = exitCommand.shouldContinueLoop();
                    return;
                default:
                    ui.showUnknownCommandMessage();
                    break;
                }
            } catch (FinanceBuddyException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }
}
