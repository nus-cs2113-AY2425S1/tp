package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.exception.Exception;
import seedu.exchangecoursemapper.constants.Assertions;
import seedu.exchangecoursemapper.constants.Logs;

import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.exchangecoursemapper.constants.HelpMessages.COMMAND_SET;
import static seedu.exchangecoursemapper.constants.HelpMessages.COMMAND_FILTER;
import static seedu.exchangecoursemapper.constants.HelpMessages.COMMAND_LIST_SCHOOLS;
import static seedu.exchangecoursemapper.constants.HelpMessages.COMMAND_COMMANDS;
import static seedu.exchangecoursemapper.constants.HelpMessages.COMMAND_ADD;
import static seedu.exchangecoursemapper.constants.HelpMessages.COMMAND_BYE;
import static seedu.exchangecoursemapper.constants.Messages.LINE_SEPARATOR;

public class HelpCommand extends Command {
    private static final Logger logger = Logger.getLogger(HelpCommand.class.getName());

    @Override
    public void execute (String input) {
        logger.log(Level.INFO, Logs.EXECUTING_COMMAND);
        assert input != null: Assertions.EMPTY_USER_INPUT;
        try {
            String command = getCommand(input);
            logger.log(Level.INFO, Logs.COMMAND_PARSED);
            printHelp(command);
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, Logs.INVALID_COMMAND);
            System.out.println(Exception.invalidCommand());
        } catch (java.lang.Exception e) {
            logger.log(Level.SEVERE, Logs.EXECUTION_FAILED, e);
            throw new RuntimeException(e);
        }
    }

    public String getCommand (String input) {
        assert input != null: Assertions.NULL_INPUT;
        String command = input.replaceFirst("help", "").trim().toLowerCase();

        switch (command) {
        case "set":
        case "filter":
        case "list schools":
        case "add":
        case "commands":
        case "bye":
            return command;
        default:
            logger.log(Level.WARNING, Logs.INVALID_COMMAND + command);
            throw new IllegalArgumentException("Invalid command.");
        }
    }

    public void printHelp (String command) {
        assert command != null: Assertions.NULL_INPUT;

        switch (command) {
        case "set":
            System.out.println(COMMAND_SET);
            break;
        case "filter":
            System.out.println(COMMAND_FILTER);
            break;
        case "list schools":
            System.out.println(COMMAND_LIST_SCHOOLS);
            break;
        case "commands":
            System.out.println(COMMAND_COMMANDS);
            break;
        case "add":
            System.out.println(COMMAND_ADD);
            break;
        case "bye":
            System.out.println(COMMAND_BYE);
            break;
        default:
            logger.log(Level.SEVERE, Logs.INVALID_COMMAND + command);
            throw new IllegalArgumentException("Invalid command");
        }
        System.out.println(LINE_SEPARATOR);
    }
}
