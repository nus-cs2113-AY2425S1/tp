package tutorlink.command;

import tutorlink.appstate.AppState;
import tutorlink.commons.Commons;
import tutorlink.exceptions.InvalidCommandException;
import tutorlink.exceptions.TutorLinkException;
import tutorlink.result.CommandResult;

import java.util.HashMap;

/**
 * Represents a command that is triggered when an invalid command word is provided.
 * This command, when executed, throws an {@code InvalidCommandException} indicating
 * that the command is not recognized.
 */
public class InvalidCommand extends Command {

    /**
     * Executes the invalid command, resulting in an exception to notify the user
     * that an invalid command has been entered.
     *
     * @param appState The current application state, not used by this command.
     * @param hashMap A map of argument prefixes to their values, not used by this command.
     * @return This method does not return normally, as it always throws an {@code InvalidCommandException}.
     * @throws TutorLinkException Always thrown to indicate an invalid command.
     */
    @Override
    public CommandResult execute(AppState appState, HashMap<String, String> hashMap) throws TutorLinkException {
        throw new InvalidCommandException(Commons.ERROR_INVALID_COMMAND);
    }

    /**
     * Returns the argument prefixes required by this command.
     *
     * @return {@code null} as this command does not require any argument prefixes.
     */
    @Override
    public String[] getArgumentPrefixes() {
        return null;
    }
}
