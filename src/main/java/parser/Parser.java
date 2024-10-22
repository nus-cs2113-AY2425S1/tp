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

import static parser.ParserUtils.parseIndex;

public class Parser {
    private final ProgammeParser progParser;

    public Parser() {
        this.progParser = new ProgammeParser();
    }

    public Command parse(String fullCommand) {
        String[] inputArguments = fullCommand.trim().split(" ", 2);

        String commandString = inputArguments[0];
        String argumentString = "";

        if (inputArguments.length > 1) {
            argumentString = inputArguments[1];
        }

        return switch (commandString) {
            case ProgammeParser.COMMAND_WORD -> progParser.parse(argumentString);
            case LogCommand.COMMAND_WORD -> prepareLogCommand(argumentString);
            case HistoryCommand.COMMAND_WORD -> new HistoryCommand();
            case WeeklySummaryCommand.COMMAND_WORD -> new WeeklySummaryCommand();  // Support for weekly summary command
            case PersonalBestCommand.COMMAND_WORD -> preparePersonalBestCommand(argumentString);  // Support for personal bests command
            case ExitCommand.COMMAND_WORD -> new ExitCommand();
            default -> new InvalidCommand();
        };
    }

    // Personal best command with exercise name
    private Command preparePersonalBestCommand(String argumentString) {
        // Trim the argument string, which may contain the exercise name
        String exerciseName = argumentString.trim();
        // Return the PersonalBestCommand, passing in the exercise name (or empty string if none is provided)
        return new PersonalBestCommand(exerciseName.isEmpty() ? null : exerciseName);  // Handle null if exercise is not specified
    }

    private Command prepareLogCommand(String argumentString) {
        int progIndex = -1;
        int dayIndex = -1;
        LocalDate date = LocalDate.now();

        String[] arguments = argumentString.split(" (?=/[tdp])");

        for (String arg : arguments) {
            String[] argParts = arg.split(" ");
            String flag = argParts[0];
            String value = argParts[1];

            switch (flag) {
            case "/p":
                progIndex = parseIndex(value);
                break;
            case "/d":
                dayIndex = parseIndex(value);
                break;
            case "/t":
                date = parseDate(value);
                break;
            default:
                throw new IllegalArgumentException("Flag command not recognized: " + flag);
            }
        }
        return new LogCommand(progIndex, dayIndex, date);
    }

    private LocalDate parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateString, formatter);
    }
}

