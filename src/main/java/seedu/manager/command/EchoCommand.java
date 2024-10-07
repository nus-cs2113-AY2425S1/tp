package seedu.manager.command;

/**
 * Represents an executable echo command
 */
public class EchoCommand extends Command {
    private final String userInput;

    /**
     * Constructs a new EchoCommand with the given user input
     *
     * @param userInput The user input
     */
    public EchoCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Returns the command output, with the user input as the output message
     */
    @Override
    public CommandOutput execute() {
        return new CommandOutput(this.userInput, false);
    }
}
