package parser;

import command.Command;
import command.ExitCommand;
import command.HistoryCommand;
import command.LogCommand;
import command.InvalidCommand;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static parser.ParserUtils.parseIndex;

public class Parser {
    private final ProgrammeParser progParser;

    public Parser(){
        this.progParser = new ProgrammeParser();
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

        return switch (commandString) {
        case ProgrammeParser.COMMAND_WORD -> progParser.parse(argumentString);
        case LogCommand.COMMAND_WORD -> prepareLogCommand(argumentString);
        case HistoryCommand.COMMAND_WORD -> new HistoryCommand();
        case ExitCommand.COMMAND_WORD -> new ExitCommand();
        default -> new InvalidCommand();
        };
    }

    private Command prepareLogCommand(String argumentString){
        int progIndex = -1;
        int dayIndex = -1;
        LocalDateTime dateTime = LocalDateTime.now();

        String[] arguments = argumentString.split(" (?=/)");
        if (arguments.length < 3) {
            throw new IllegalArgumentException("Please provide all log flags.");
        }

        for (String arg : arguments) {
            String[] argParts = arg.split(" ");
            String flag = argParts[0];

            switch (flag){
            case "/p":
                if (argParts[1] == null || argParts[1].trim().isEmpty()) {
                    throw new IllegalArgumentException("Programme index cannot be empty. Please enter valid index.");
                }

                progIndex = parseIndex(argParts[1]);
                break;
            case "/d":
                if (argParts[1] == null || argParts[1].trim().isEmpty()) {
                    throw new IllegalArgumentException("Day index cannot be empty. Please enter valid index.");
                }

                dayIndex = parseIndex(argParts[1]);
                break;
            case "/t":
                if (argParts[1] == null || argParts[1].trim().isEmpty()) {
                    throw new IllegalArgumentException("Date cannot be empty. Please enter valid date.");
                }

                dateTime = parseDateTime(argParts[1]);
                break;
            default:
                throw new IllegalArgumentException("Flag command not recognized: " + flag);
            }
        }
        return new LogCommand(progIndex, dayIndex, dateTime);
    }


    private LocalDateTime parseDateTime(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        LocalTime currentTime = LocalTime.now();

        return LocalDateTime.of(localDate, currentTime);
    }
}
