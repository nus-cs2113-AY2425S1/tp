package seedu.manager.command;

/**
 * Represents a command to remove an event from the event list.
 * The remove command will search for an event by its name and remove it if found.
 */
public class RemoveCommand extends Command {
    public static final String COMMAND_WORD = "remove";
    private static final String REMOVE_SUCCESS = "Removed successfully";
    private static final String REMOVE_FAILURE = "Not found";
    protected String eventName;
    protected String participantName;

    /**
     * Constructs a RemoveCommand object with the specified event name.
     *
     * @param eventName The name of the event to be removed.
     */
    public RemoveCommand(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Executes the command to remove an event or a participant from an event.
     *
     * <p>
     * If no participant name is provided, this method attempts to remove the event
     * specified by the event name. If a participant name is provided, it tries to
     * remove that participant from the specified event. The result of the operation
     * is indicated by the return value.
     * </p>
     *
     * @return a {@link CommandOutput} object containing a message indicating
     * whether the removal was successful or failed.
     */
    @Override
    public CommandOutput execute() {
        boolean isRemoved;

        if (participantName == null) {
            isRemoved = this.eventList.removeEvent(this.eventName);
        } else {
            isRemoved = this.eventList.removeParticipantFromEvent(this.participantName, this.eventName);
        }

        if (isRemoved) {
            return new CommandOutput(REMOVE_SUCCESS, false);
        } else {
            return new CommandOutput(REMOVE_FAILURE, false);
        }
    }
}
