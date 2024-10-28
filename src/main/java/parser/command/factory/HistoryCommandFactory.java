package parser.command.factory;

import command.Command;
import command.history.HistoryCommand;
import command.history.ViewPersonalBestCommand;
import command.history.ListPersonalBestsCommand;
import command.history.WeeklySummaryCommand;
import command.history.DeleteRecordCommand;
import command.InvalidCommand;
import parser.FlagParser;

import java.time.LocalDate;

import static parser.ParserUtils.parseDate;
import static parser.ParserUtils.splitArguments;

public class HistoryCommandFactory {
    public static final String COMMAND_WORD = "history";

    public Command parse(String argumentString) {
        assert argumentString != null : "Argument string must not be null";

        // If argumentString is empty, set subCommandString to HistoryCommand.COMMAND_WORD
        String defaultCommand = argumentString.isEmpty() ? HistoryCommand.COMMAND_WORD : argumentString;
        String[] inputArguments = splitArguments(defaultCommand);
        String subCommandString = inputArguments[0];
        String arguments = inputArguments.length > 1 ? inputArguments[1] : "";

        return switch (subCommandString) {
        case HistoryCommand.COMMAND_WORD -> new HistoryCommand();
        case ListPersonalBestsCommand.COMMAND_WORD -> prepareListPersonalBestsCommand();
        case WeeklySummaryCommand.COMMAND_WORD -> prepareWeeklySummaryCommand();
        case ViewPersonalBestCommand.COMMAND_WORD -> prepareViewPersonalBestCommand(arguments);
        case DeleteRecordCommand.COMMAND_WORD -> prepareDeleteRecordCommand(arguments);
        default -> new InvalidCommand();
        };
    }

    private Command prepareWeeklySummaryCommand() {
        return new WeeklySummaryCommand();
    }

    private Command prepareViewPersonalBestCommand(String argumentString) {
        FlagParser flagParser = new FlagParser(argumentString);
        flagParser.validateRequiredFlags("/e");

        String exerciseName = flagParser.getStringByFlag("/e");

        return new ViewPersonalBestCommand(exerciseName);
    }

    private Command prepareListPersonalBestsCommand() {
        return new ListPersonalBestsCommand();
    }

    private Command prepareDeleteRecordCommand(String argumentString) {
        LocalDate toDelete = parseDate(argumentString);
        return new DeleteRecordCommand(toDelete);
    }
}

