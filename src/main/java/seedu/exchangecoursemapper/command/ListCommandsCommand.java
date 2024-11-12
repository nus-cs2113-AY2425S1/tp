package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.constants.Logs;

import java.util.logging.Logger;
import java.util.logging.Level;

import seedu.exchangecoursemapper.ui.UI;

/**
 * Command to list all available commands for the application.
 * This command provides users with a list of commands they can use, helping them
 * to understand the available functionality.
 */
public class ListCommandsCommand extends CheckInformationCommand {

    private static final Logger logger = Logger.getLogger(ListCommandsCommand.class.getName());
    private static final UI ui = new UI();

    /**
     * Executes the ListCommandsCommand by displaying a list of all available commands
     * to the user through the UI. Logs the start and completion of the command execution.
     *
     * @param userInput The user input provided for the command.
     *                  This parameter is not utilized in this method.
     */
    @Override
    public void execute(String userInput) {
        logger.log(Level.INFO, Logs.EXECUTING_COMMAND);
        ui.printCommandsList();
        logger.log(Level.INFO, Logs.COMPLETE_EXECUTION);
    }
}

