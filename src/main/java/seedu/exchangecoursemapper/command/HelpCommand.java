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
import static seedu.exchangecoursemapper.constants.HelpMessages.COMMAND_OBTAIN;
import static seedu.exchangecoursemapper.constants.HelpMessages.COMMAND_DELETE;
import static seedu.exchangecoursemapper.constants.HelpMessages.COMMAND_LIST_MAPPED;
import static seedu.exchangecoursemapper.constants.HelpMessages.COMMAND_COMPARE_PU;
import static seedu.exchangecoursemapper.constants.HelpMessages.COMMAND_FIND;
import static seedu.exchangecoursemapper.constants.Messages.LINE_SEPARATOR;

public class HelpCommand extends CheckInformationCommand {
    private static final Logger logger = Logger.getLogger(HelpCommand.class.getName());

    /**
     * Executes the help command by processing the user input and
     * printing the relevant help messages.
     * This method first logs the start of execution and asserts that the input
     * is not null. Then it parses the input and extract the valid command
     * and printing relevant help messages.
     *
     * @param input The user input containing help command.
     */
    @Override
    public void execute (String input) {
        logger.log(Level.INFO, Logs.EXECUTING_COMMAND);
        assert input != null: Assertions.EMPTY_USER_INPUT;
        try {
            String command = getCommand(input);
            logger.log(Level.INFO, Logs.COMMAND_PARSED);
            printHelp(command);
        } catch (IllegalArgumentException e) {
            handleIllegalArgumentError(e);
        } catch (java.lang.Exception e) {
            logger.log(Level.SEVERE, Logs.EXECUTION_FAILED, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Parses the user input and extracts the command following the "help" keyword.
     * This method remove the "help" keyword and trims the input to get the command.
     * Then using switch statements, the input is checked whether it matches one of the valid input.
     * If is matches, the command is returned, if not, an exception will be thrown.
     *
     * @param input The user input containing the help command.
     * @return The extracted command if it contains valid commands.
     */
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
        case "obtain":
        case "delete":
        case "list mapped":
        case "compare":
        case "find":
            // Fallthrough is intentional between the cases
            return command;
        default:
            logger.log(Level.WARNING, Logs.INVALID_COMMAND + command);
            throw new IllegalArgumentException(Exception.invalidCommand());
        }
    }

    /**
     * Prints the help message corresponding to the provided command.
     * This method uses switch statements to check and print specific help messages
     * for each command. If the command is invalid, an exception will be thrown.
     *
     * @param command The command for which the help message is required.
     */
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
        case "obtain":
            System.out.println(COMMAND_OBTAIN);
            break;
        case "delete":
            System.out.println(COMMAND_DELETE);
            break;
        case "list mapped":
            System.out.println(COMMAND_LIST_MAPPED);
            break;
        case "compare":
            System.out.println(COMMAND_COMPARE_PU);
            break;
        case "find":
            System.out.println(COMMAND_FIND);
            break;
        default:
            logger.log(Level.SEVERE, Logs.INVALID_COMMAND + command);
            throw new IllegalArgumentException(Exception.invalidCommand());
        }
        System.out.println(LINE_SEPARATOR);
    }

    private static void handleIllegalArgumentError(IllegalArgumentException e) {
        logger.log(Level.WARNING, Logs.INVALID_COMMAND);
        System.out.println(e.getMessage());
        System.out.println(LINE_SEPARATOR);
    }
}
