package parser;

import command.Command;
import command.ExitCommand;
import command.HistoryCommand;
import command.LogCommand;
import command.InvalidCommand;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Logger;
import java.util.logging.Level;

import static parser.IndexParser.parseIndex;

public class Parser {
    private final ProgCommandParser progParser;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public Parser(){
        this.progParser = new ProgCommandParser();
    }

    public Parser(ProgCommandParser progParser) {
        this.progParser = progParser;
    }

    public Command parse(String fullCommand) {
        if (fullCommand == null || fullCommand.trim().isEmpty()) {
            throw new IllegalArgumentException("Command cannot be empty. Please enter a valid command.");
        }

        String[] inputArguments = fullCommand.trim().split(" ", 2);

        String commandString = inputArguments[0];
        String argumentString = "";

        if (inputArguments.length > 1 ){
            argumentString = inputArguments[1];
        }

        logger.log(Level.INFO, "Parsed command: {0}, with arguments: {1}",
                new Object[]{commandString, argumentString});

        return switch (commandString) {
        case ProgCommandParser.COMMAND_WORD -> progParser.parse(argumentString);
        case LogCommand.COMMAND_WORD -> prepareLogCommand(argumentString);
        case HistoryCommand.COMMAND_WORD -> new HistoryCommand();
        case ExitCommand.COMMAND_WORD -> new ExitCommand();
        default -> new InvalidCommand();
        };
    }

    private Command prepareLogCommand(String argumentString) {
        assert argumentString != null : "Argument string must not be null";

        FlagParser flagParser = new FlagParser(argumentString);

        int progIndex = -1;
        int dayIndex = -1;
        LocalDate date = LocalDate.now();

        if (flagParser.hasFlag("/p")) {
            progIndex = parseIndex(flagParser.getFlagValue("/p"), "Invalid programme index.");
        }
        if (flagParser.hasFlag("/d")) {
            dayIndex = parseIndex(flagParser.getFlagValue("/d"), "Invalid day index.");
        }
        if (flagParser.hasFlag("/t")) {
            date = parseDate(flagParser.getFlagValue("/t"));
        }

        logger.log(Level.INFO, "LogCommand prepared with Programme index: {0}, Day index: {1}, Date: {2}",
                new Object[]{progIndex, dayIndex, date});
        return new LogCommand(progIndex, dayIndex, date);
    }

    private LocalDate parseDate(String dateString) {
        assert dateString != null: "Date string must not be null";

        if (dateString.trim().isEmpty()) {
            throw new IllegalArgumentException("Date cannot be empty. Please provide a valid date.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected format: dd-MM-yyyy. " +
                    "Error: " + e.getParsedString(), e);
        }
    }
}


