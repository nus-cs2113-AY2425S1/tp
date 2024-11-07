// @@author andreusxcarvalho
package parser.command.factory;

import command.Command;
import command.history.HistoryCommand;
import command.history.ViewPersonalBestCommand;
import command.history.ListPersonalBestsCommand;
import command.history.WeeklySummaryCommand;
import command.InvalidCommand;
import exceptions.HistoryExceptions;

//import parser.FlagParser;

import static parser.ParserUtils.splitArguments;

public class HistoryCommandFactory {
    public static final String COMMAND_WORD = "history";

    public Command parse(String argumentString) {
        assert argumentString != null : "Argument string must not be null";

        String parsedArgument = argumentString.isEmpty() ? HistoryCommand.COMMAND_WORD : argumentString;
        String[] inputArguments = splitArguments(parsedArgument);
        String subCommandString = inputArguments[0];
        String arguments = inputArguments.length > 1 ? inputArguments[1] : "";

        return switch (subCommandString) {
            case HistoryCommand.COMMAND_WORD -> new HistoryCommand();
            case ListPersonalBestsCommand.COMMAND_WORD -> arguments.isEmpty() ?
                    new ListPersonalBestsCommand() : new ViewPersonalBestCommand(arguments);
            case WeeklySummaryCommand.COMMAND_WORD -> new WeeklySummaryCommand();
            default -> new InvalidCommand();
        };
    }

}