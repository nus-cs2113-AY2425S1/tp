package seedu.manager.command;

//@@author KuanHsienn
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
    protected String itemName;

    /**
     * Constructs a RemoveCommand object with the specified event name.
     *
     * @param eventName The name of the event to be removed.
     */
    public RemoveCommand(String eventName) {
        super(false);
        this.eventName = eventName;
    }

    //@@author LTK-1606
    /**
     * Constructs a RemoveCommand object with the specified event name and item name.
     *
     * @param eventName The name of the event the participant is to be removed from.
     * @param itemName The name of the item to be removed.
     * @param isParticipant true if the item is a participant, false otherwise
     */
    public RemoveCommand(String itemName, String eventName, boolean isParticipant) {
        super(false);
        this.eventName = eventName;
        if (isParticipant) {
            participantName = itemName;
        } else {
            this.itemName = itemName;
        }
    }

    //@@author KuanHsienn
    /**
     * Executes the command to remove an event or a participant from an event.
     *
     * <p>
     * If no participant name is provided, this method attempts to remove the event
     * specified by the event name. If a participant name is provided, it tries to
     * remove that participant from the specified event. The command's message is then
     * set depending on whether the removal was successful or failed.
     * </p>
     */
    @Override
    public void execute() {
        boolean isRemoved;

        if (participantName != null) {
            isRemoved = eventList.removeParticipantFromEvent(participantName, eventName);
        } else if (itemName != null) {
            isRemoved = eventList.removeItemFromEvent(itemName, eventName);
        } else {
            isRemoved = eventList.removeEvent(eventName);
        }

        if (isRemoved) {
            this.message = REMOVE_SUCCESS;
        } else {
            this.message = REMOVE_FAILURE;
        }
    }
}
