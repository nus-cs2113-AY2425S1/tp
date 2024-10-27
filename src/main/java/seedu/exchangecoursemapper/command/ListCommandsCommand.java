package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.constants.Logs;

import java.util.logging.Logger;
import java.util.logging.Level;

import static seedu.exchangecoursemapper.constants.Messages.COMMANDS_LIST;
import static seedu.exchangecoursemapper.constants.Messages.LINE_SEPARATOR;

public class ListCommandsCommand extends Command {

    private static final Logger logger = Logger.getLogger(ListCommandsCommand.class.getName());

    @Override
    public void execute(String userInput) {
        logger.log(Level.INFO, Logs.EXECUTING_COMMAND);
        System.out.println(LINE_SEPARATOR);
        System.out.println(COMMANDS_LIST);
        System.out.println(LINE_SEPARATOR);
        logger.log(Level.INFO, Logs.COMPLETE_EXECUTION);
    }
}
