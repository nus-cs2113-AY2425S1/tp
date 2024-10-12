package seedu.manager.command;

/**
 * Represents an invalid command that cannot be executed
 */
public class InvalidCommand extends Command {
    private String errorMessage;

    /**
     * Constructs a new invalid command with a given error message
     *
     * @param errorMessage The specified error message
     */
    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Returns a new command output with the error message
     *
     * @return A command output with the error message
     */
    @Override
    public CommandOutput execute() {
        return new CommandOutput(this.errorMessage, false);
    }
}
