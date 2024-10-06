package seedu.manager.command;

/**
 * Represents an executable echo command
 */
public class EchoCommand extends Command{
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
     * Executes echo command by printing user input
     */
    @Override
    public void execute() {
        System.out.println(this.userInput);
    }
}
