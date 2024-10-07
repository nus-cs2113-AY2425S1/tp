package seedu.manager.command;

/**
 * Represents an executable exit command
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";
    private static final String EXIT_MESSAGE = "Thank you for using EventManagerCLI. Goodbye!";

    /**
     * Returns a command output with an exit message
     *
     * @return The command output with an exit message
     */
    public CommandOutput execute() {
        return new CommandOutput(EXIT_MESSAGE, true);
    }
}
