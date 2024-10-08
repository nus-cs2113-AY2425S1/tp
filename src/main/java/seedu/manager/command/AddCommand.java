package seedu.manager.command;

public class AddCommand extends Command{
    public static final String COMMAND_WORD = "add";
    private final String userInput;
    public static final String ADD_EVENT_MESSAGE = "The following event has been added to the event list:";
    /**
     * Constructs a new AddCommand with the given user input
     *
     * @param userInput The user input
     */
    public AddCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Returns the command output, with the user input as the output message
     */
    @Override
    public CommandOutput execute() {
        return new CommandOutput(ADD_EVENT_MESSAGE, false);
    }
}
