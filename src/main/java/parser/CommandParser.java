package parser;

import command.Command;
import command.ExitCommand;
import command.LogCommand;
import command.InvalidCommand;
import parser.subcommand.ProgCommandParser;
import parser.util.FlagParser;

import java.time.LocalDate;
import java.util.logging.Logger;
import java.util.logging.Level;


public class CommandParser {
    private final ProgCommandParser progParser;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public CommandParser() {
        this.progParser = new ProgCommandParser();  // Using the correct name from master branch
    }

    public Command parse(String fullCommand) {
        if (fullCommand == null || fullCommand.trim().isEmpty()) {
            throw new IllegalArgumentException("Command cannot be empty. Please enter a valid command.");
        }

        String[] inputArguments = fullCommand.trim().split(" ", 2);

        String commandString = inputArguments[0];
        String argumentString = "";

        if (inputArguments.length > 1) {
            argumentString = inputArguments[1];
        }

        logger.log(Level.INFO, "Parsed command: {0}, with arguments: {1}",
                new Object[]{commandString, argumentString});

        return switch (commandString) {
        case ProgCommandParser.COMMAND_WORD -> progParser.parse(argumentString);
        case LogCommand.COMMAND_WORD -> prepareLogCommand(argumentString);
        case ExitCommand.COMMAND_WORD -> new ExitCommand();
        default -> new InvalidCommand();
        };
    }

    private Command prepareLogCommand(String argumentString) {
        assert argumentString != null : "Argument string must not be null";
        FlagParser flagParser =  new FlagParser(argumentString);
        int progIndex = flagParser.getIndexByFlag("/p");
        int dayIndex = flagParser.getIndexByFlag("/d");
        LocalDate date = flagParser.getDateByFlag("/t");

        logger.log(Level.INFO,
                "LogCommand prepared with Programme index: {0}, Day index: {1}, Date: {2}",
                new Object[]{progIndex, dayIndex, date});

        return new LogCommand(progIndex, dayIndex, date);
    }
}
