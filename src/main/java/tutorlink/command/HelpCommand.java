package tutorlink.command;

import tutorlink.appstate.AppState;
import tutorlink.commons.Commons;
import tutorlink.exceptions.TutorLinkException;
import tutorlink.result.CommandResult;

import java.util.HashMap;

/**
 * Represents a help command that displays usage information.
 * This command shows a list of all available commands and their proper usage.
 * This command is triggered using the "help" keyword.
 */
public class HelpCommand extends Command {
    /**
     * Command word that triggers this command
     */
    public static final String COMMAND_WORD = "help";

    /**
     * Creates a new HelpCommand instance.
     */
    public HelpCommand() {
    }

    /**
     * Executes the help command.
     * Returns a command result containing the help message that lists
     * all available commands and their usage.
     *
     * @param state     The current state of the application
     * @param arguments Command arguments (not used for help command)
     * @return CommandResult containing the help message
     * @throws TutorLinkException if there's an error executing the command
     */
    @Override
    public CommandResult execute(AppState state, HashMap<String, String> arguments) throws TutorLinkException {
        return new CommandResult(Commons.HELP_MESSAGE);
    }

    /**
     * Gets the argument prefixes that this command accepts.
     * Help command doesn't accept any arguments.
     *
     * @return null since this command takes no arguments
     */
    @Override
    public String[] getArgumentPrefixes() {
        return null;
    }
}
