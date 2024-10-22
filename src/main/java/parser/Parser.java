package parser;

import command.Command;
import command.ExitCommand;
import command.HistoryCommand;
import command.LogCommand;
import command.InvalidCommand;
import command.WeeklySummaryCommand;
import command.PersonalBestCommand;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Logger;
import java.util.logging.Level;

import static parser.IndexParser.parseIndex;

public class Parser {
    private final ProgCommandParser progParser;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public Parser() {
        this.progParser = new ProgCommandParser();  // Using the correct name from master branch
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

        if (inputArguments.length > 1) {
            argumentString = inputArguments[1];
        }

        logger.log(Level.INFO, "Parsed command: {0}, with arguments: {1}",
                new Object[]{commandString, argumentString});

        switch (commandString) {
        case ProgCommandParser.COMMAND_WORD:
            return progParser.parse(argumentString);
        case LogCommand.COMMAND_WORD:
            return prepareLogCommand(argumentString);
        case HistoryCommand.COMMAND_WORD:
            return new HistoryCommand();
        case WeeklySummaryCommand.COMMAND_WORD:
            return new WeeklySummaryCommand();  // Support for weekly summary command
        case PersonalBestCommand.COMMAND_WORD:
            return preparePersonalBestCommand(argumentString);  // Support for personal bests command
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            return new InvalidCommand();
        }
    }

    // Personal best command with exercise name
    private Command preparePersonalBestCommand(String argumentString) {
        // Trim the argument string, which may contain the exercise name
        String exerciseName = argumentString.trim();
        // Return the PersonalBestCommand, passing in the exercise name (or empty string if none is provided)
        return new PersonalBestCommand(
                exerciseName.isEmpty() ? null : exerciseName
        );  // Handle null if exercise is not specified
    }

    private Command prepareLogCommand(String argumentString) {
        assert argumentString != null : "Argument string must not be null";

        int progIndex = -1;
        int dayIndex = -1;
        LocalDate date = LocalDate.now();

        String[] arguments = argumentString.split(" (?=/)");
        if (arguments.length < 3) {
            throw new IllegalArgumentException("Please provide all log flags.");
        }

        for (String arg : arguments) {
            String[] argParts = arg.split(" ");
            String flag = argParts[0];

            if (argParts.length < 2) {
                throw new IllegalArgumentException("Flag " + flag + " is missing a value.");
            }

            switch (flag) {
            case "/p":
                progIndex = parseIndex(argParts[1]);
                break;
            case "/d":
                dayIndex = parseIndex(argParts[1]);
                break;
            case "/t":
                date = parseDate(argParts[1]);
                break;
            default:
                throw new IllegalArgumentException("Flag command not recognized: " + flag);
            }
        }

        logger.log(Level.INFO, "LogCommand prepared with Programme index: {0}, Day index: {1}, Date: {2}",
                new Object[]{progIndex, dayIndex, date});

        return new LogCommand(progIndex, dayIndex, date);
    }

    private LocalDate parseDate(String dateString) {
        assert dateString != null && !dateString.trim().isEmpty() : "Date string must not be null or empty";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Invalid date format. Expected format: dd-MM-yyyy. Error: " + e.getParsedString(), e
            );
        }
    }
}

