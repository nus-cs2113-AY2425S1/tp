package seedu.manager.command;

/**
 * Represents a command to remove an event from the event list.
 * The remove command will search for an event by its name and remove it if found.
 */
public class RemoveCommand extends Command {
    public static final String COMMAND_WORD = "remove";
    private static final String REMOVE_SUCCESS = "Event removed successfully";
    private static final String REMOVE_FAILURE = "Event not found";
    protected String eventName;

    /**
     * Constructs a RemoveCommand object with the specified event name.
     *
     * @param eventName The name of the event to be removed.
     */
    public RemoveCommand(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Executes the command to remove the event from the event list.
     * If the event is successfully removed, a success message is returned.
     * Otherwise, a failure message is returned.
     *
     * @return The command output with a remove message.
     */
    @Override
    public CommandOutput execute() {
        boolean isRemoved = this.eventList.removeEvent(eventName);
        if (isRemoved) {
            return new CommandOutput(REMOVE_SUCCESS, false);
        } else {
            return new CommandOutput(REMOVE_FAILURE, false);
        }
    }
}
