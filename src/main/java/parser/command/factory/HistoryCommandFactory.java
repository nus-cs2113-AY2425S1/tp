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

/**
 * Factory class responsible for creating instances of history-related commands.
 * <p>
 * The {@code HistoryCommandFactory} parses the input arguments and generates
 * specific command objects based on the type of history command requested.
 * Supported commands include viewing, listing, and deleting history, as well as
 * managing personal bests.
 * </p>
 */
public class HistoryCommandFactory {
    public static final String COMMAND_WORD = "history";

    /**
     * Parses the given argument string and creates the corresponding {@link Command} object.
     * <p>
     * Based on the subcommand specified in the argument string, this method determines
     * the appropriate command type and returns an instance of that command.
     * </p>
     *
     * @param argumentString the argument string containing the subcommand and any additional arguments
     * @return the created {@link Command} object, or an {@link InvalidCommand} if the subcommand is unrecognized
     */
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
            case DeleteHistoryCommand.COMMAND_WORD -> prepareDeleteHistoryCommand(arguments);
            default -> new InvalidCommand();
        };
    }

    /**
     * Prepares a {@link ViewHistoryCommand} for a specific date.
     * <p>
     * This method parses the argument string to extract the date and creates a {@link ViewHistoryCommand}
     * for viewing the history record on that date.
     * </p>
     *
     * @param argumentString the argument string containing the date
     * @return a {@link ViewHistoryCommand} for the specified date
     */
    private Command prepareViewHistoryCommand(String argumentString) {
        LocalDate date = parseDate(argumentString);
        return new ViewHistoryCommand(date);
    }

    /**
     * Prepares a command to handle personal bests, either listing all or viewing a specific exercise.
     * <p>
     * If the argument string is empty, a {@link ListPersonalBestsCommand} is created.
     * Otherwise, a {@link ViewPersonalBestCommand} is created for the specified exercise.
     * </p>
     *
     * @param argumentString the argument string specifying an exercise name, or empty to list all personal bests
     * @return a {@link ListPersonalBestsCommand} or {@link ViewPersonalBestCommand}, depending on the argument
     */
    private Command preparePersonalBestCommand(String argumentString) {
        if (argumentString.isEmpty()) {
            return new ListPersonalBestsCommand();
        }
        return new ViewPersonalBestCommand(argumentString);
    }

    /**
     * Prepares a {@link DeleteHistoryCommand} to delete a record for a specific date.
     * <p>
     * This method parses the argument string to extract the date and creates a {@link DeleteHistoryCommand}
     * to delete the history record on that date.
     * </p>
     *
     * @param argumentString the argument string containing the date to delete
     * @return a {@link DeleteHistoryCommand} for the specified date
     */
    private Command prepareDeleteHistoryCommand(String argumentString) {
        LocalDate date = parseDate(argumentString);
        return new DeleteHistoryCommand(date);
    }
}

