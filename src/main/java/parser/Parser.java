package parser;

import command.Command;
import command.ExitCommand;
import command.HistoryCommand;
import command.LogCommand;
import command.InvalidCommand;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static parser.ParserUtils.parseIndex;

public class Parser {
    private final ProgammeParser progParser;

    public Parser(){
        this.progParser = new ProgammeParser();
    }

    public Command parse(String fullCommand) {
        String[] inputArguments = fullCommand.trim().split(" ", 2);

        String commandString = inputArguments[0];
        String argumentString = "";

        if (inputArguments.length > 1 ){
            argumentString = inputArguments[1];
        }

        return switch (commandString) {
        case ProgammeParser.COMMAND_WORD -> progParser.parse(argumentString);
        case LogCommand.COMMAND_WORD -> prepareLogCommand(argumentString);
        case HistoryCommand.COMMAND_WORD -> new HistoryCommand();
        case ExitCommand.COMMAND_WORD -> new ExitCommand();
        default -> new InvalidCommand();
        };
    }

    private Command prepareLogCommand(String argumentString){
        int progIndex = -1;
        int dayIndex = -1;
        LocalDateTime date = LocalDateTime.now();

        String[] arguments = argumentString.split(" (?=/[tdp])");

        for (String arg : arguments) {
            String[] argParts = arg.split(" ");
            String flag = argParts[0];
            String value = argParts[1];

            switch (flag){
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


    private LocalDateTime parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDateTime.parse(dateString, formatter);
    }
}
