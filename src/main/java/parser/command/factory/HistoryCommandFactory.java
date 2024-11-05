// @@author andreusxcarvalho
package parser.command.factory;

import command.Command;
import command.history.HistoryCommand;
import command.history.ViewPersonalBestCommand;
import command.history.ListPersonalBestsCommand;
import command.history.WeeklySummaryCommand;
import command.InvalidCommand;
import exceptions.IndexOutOfBoundsBuffBuddyException;
import exceptions.InvalidFormatBuffBuddyException;
//import parser.FlagParser;

import static parser.ParserUtils.splitArguments;

public class HistoryCommandFactory {
    public static final String COMMAND_WORD = "history";

    public Command parse(String argumentString) {
        assert argumentString != null : "Argument string must not be null";

        // Handle empty argumentString by defaulting to HistoryCommand
        String parsedArgument = argumentString.isEmpty() ? HistoryCommand.COMMAND_WORD : argumentString;
        String[] inputArguments = splitArguments(parsedArgument);
        String subCommandString = inputArguments[0];
        String arguments = inputArguments.length > 1 ? inputArguments[1] : "";

        return switch (subCommandString) {
        case HistoryCommand.COMMAND_WORD -> new HistoryCommand();
        case ListPersonalBestsCommand.COMMAND_WORD -> preparePersonalBestCommand(arguments);
        case WeeklySummaryCommand.COMMAND_WORD -> new WeeklySummaryCommand();
        default -> new InvalidCommand();
        };
    }

    private Command preparePersonalBestCommand(String argumentString) {
        if (argumentString == null || argumentString.isEmpty()) {
            throw new IndexOutOfBoundsBuffBuddyException("Exercise name is required for viewing personal bests.");
        }
        return new ViewPersonalBestCommand(argumentString);  // Pass exercise name to ViewPersonalBestCommand
    }
}

