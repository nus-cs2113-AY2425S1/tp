package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.constants.Logs;

import java.util.logging.Logger;
import java.util.logging.Level;

import seedu.exchangecoursemapper.ui.UI;
import static seedu.exchangecoursemapper.constants.Messages.COMMANDS_LIST;
import static seedu.exchangecoursemapper.constants.Messages.LINE_SEPARATOR;

public class ListCommandsCommand extends CheckInformationCommand {

    private static final Logger logger = Logger.getLogger(ListCommandsCommand.class.getName());
    private static final UI ui = new UI();

    @Override
    public void execute(String userInput) {
        logger.log(Level.INFO, Logs.EXECUTING_COMMAND);
        ui.printCommandsList ();
        logger.log(Level.INFO, Logs.COMPLETE_EXECUTION);
    }
}
