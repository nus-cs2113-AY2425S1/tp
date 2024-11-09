package seedu.duke;

import seedu.duke.budget.Budget;
import seedu.duke.logic.BudgetLogic;
import seedu.duke.storage.Storage;
import seedu.duke.ui.AppUi;
import seedu.duke.logic.Logic;
import seedu.duke.parser.InputParser;
import seedu.duke.financial.FinancialList;
import seedu.duke.exception.FinanceBuddyException;
import seedu.duke.util.Commons;

import java.util.HashMap;

/**
 * Main class for the FinanceBuddy application.
 * Initializes the user interface, storage, logic, and financial list,
 * then starts the main command processing loop.
 */
public class FinanceBuddy {

    /**
     * The main entry point for the FinanceBuddy application.
     * Initializes components, loads saved data, and enters a loop to process user commands.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        AppUi ui = new AppUi();
        Storage storage = new Storage();
        Budget budget = new Budget();

        BudgetLogic budgetLogic = new BudgetLogic(budget, ui);
        FinancialList financialList = storage.loadFromFile(budgetLogic);
        Logic logic = new Logic(financialList, storage, ui, budgetLogic);
        financialList.resetLastAmendedIndex();

        ui.displayWelcomeMessage();

        storage.printInvalidLines();

        try {
            budgetLogic.setBudget(financialList);
            storage.update(financialList, budgetLogic);
        } catch (FinanceBuddyException e) {
            System.out.println(e.getMessage());
        }

        boolean isRunning = true;
        while (isRunning) {
            String userInput = ui.getUserInput();
            HashMap<String, String> commandArguments;
            String command;

            try {
                commandArguments = InputParser.parseCommands(userInput);
                command = commandArguments.get(InputParser.COMMAND);

                isRunning = logic.matchCommand(command, commandArguments);
            } catch (FinanceBuddyException e) {
                Commons.printSingleLineWithBars(e.getMessage());
            }
        }
    }
}
