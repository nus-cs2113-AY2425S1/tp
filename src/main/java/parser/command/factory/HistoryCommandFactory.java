// @@author andreusxcarvalho
package parser.command.factory;

import command.Command;
import command.history.ListPersonalBestsCommand;
import command.history.ViewHistoryCommand;
import command.history.DeleteHistoryCommand;
import command.history.ListHistoryCommand;
import command.history.ViewPersonalBestCommand;
import command.history.WeeklySummaryCommand;
import command.InvalidCommand;

import java.time.LocalDate;

import static parser.ParserUtils.parseDate;
import static parser.ParserUtils.splitArguments;

public class HistoryCommandFactory {
    public static final String COMMAND_WORD = "history";

    public Command parse(String argumentString) {
        assert argumentString != null : "Argument string must not be null";

        String[] inputArguments = splitArguments(argumentString);
        String subCommandString = inputArguments[0];
        String arguments = inputArguments[1];

        return switch (subCommandString) {
        case ViewHistoryCommand.COMMAND_WORD -> prepareViewHistoryCommand(arguments);
        case ListHistoryCommand.COMMAND_WORD -> new ListHistoryCommand();
        case ListPersonalBestsCommand.COMMAND_WORD -> preparePersonalBestCommand(arguments);
        case WeeklySummaryCommand.COMMAND_WORD -> new WeeklySummaryCommand();
        case DeleteHistoryCommand.COMMAND_WORD ->  prepareDeleteHistoryCommand(arguments);
        default -> new InvalidCommand();
        };
    }

    private Command prepareViewHistoryCommand(String argumentString) {
        LocalDate date = parseDate(argumentString);
        return new ViewHistoryCommand(date);
    }

    private Command preparePersonalBestCommand(String argumentString) {
        if (argumentString.isEmpty()) {
            return new ListPersonalBestsCommand();
        }
        return new ViewPersonalBestCommand(argumentString);
    }

    private Command prepareDeleteHistoryCommand(String argumentString) {
        LocalDate date = parseDate(argumentString);
        return new DeleteHistoryCommand(date);
    }
}

