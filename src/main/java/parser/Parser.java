package parser;

import command.Command;
import command.ExitCommand;
import command.HistoryCommand;
import command.LogCommand;
import command.InvalidCommand;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;
import java.util.logging.Level;

import static parser.IndexParser.parseIndex;

public class Parser {
    private final ProgCommandParser progParser;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public Parser(){
        this.progParser = new ProgCommandParser();
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

    private Command prepareLogCommand(String argumentString){
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
        return LocalDate.parse(dateString, formatter);
    }
}

