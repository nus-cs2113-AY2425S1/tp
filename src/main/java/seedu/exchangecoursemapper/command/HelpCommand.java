package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.exception.Exception;

import static seedu.exchangecoursemapper.constants.HelpMessages.COMMAND_SET;
import static seedu.exchangecoursemapper.constants.HelpMessages.COMMAND_FILTER;
import static seedu.exchangecoursemapper.constants.HelpMessages.COMMAND_LIST_SCHOOLS;
import static seedu.exchangecoursemapper.constants.HelpMessages.COMMAND_COMMANDS;
import static seedu.exchangecoursemapper.constants.HelpMessages.COMMAND_ADD;
import static seedu.exchangecoursemapper.constants.HelpMessages.COMMAND_BYE;
import static seedu.exchangecoursemapper.constants.Messages.LINE_SEPARATOR;

public class HelpCommand extends Command {
    @Override
    public void execute (String input) {
        try {
            String command = getCommand(input);
            printHelp(command);
        } catch (IllegalArgumentException e) {
            System.out.println(Exception.invalidCommand());
        }
    }

    public String getCommand (String input) {
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
            throw new IllegalArgumentException("Invalid command.");
        }
    }

    public void printHelp (String command) {
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
            throw new IllegalArgumentException("Invalid command");
        }
        System.out.println(LINE_SEPARATOR);
    }
}
