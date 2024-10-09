package seedu.manager.command;

/**
 * Represents an executable add command
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    private static final String ADD_MESSAGE = "The following event has been added to the event list: %1$s";

    /**
     * Implement the adding command
     * Returns a command output with a successful adding message
     *
     * @return The command output with a successful adding message
     */
    @Override
    public CommandOutput execute() {
        return new CommandOutput(ADD_MESSAGE, false);
    }
}
