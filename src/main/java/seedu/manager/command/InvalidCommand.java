package seedu.manager.command;

/**
 * Represents an invalid command that cannot be executed
 */
public class InvalidCommand extends Command {
    private final String errorMessage;

    /**
     * Constructs a new invalid command with a given error message
     *
     * @param errorMessage The specified error message
     */
    public InvalidCommand(String errorMessage) {
        super(false);
        this.errorMessage = errorMessage;
    }

    /**
     * Executes the invalid command
     */
    @Override
    public void execute() {
        this.message = this.errorMessage;
    }
}
