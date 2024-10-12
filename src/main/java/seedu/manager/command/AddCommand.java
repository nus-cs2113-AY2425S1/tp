package seedu.manager.command;

/**
 * Represents a command to add an event to the event list.
 * The add command will store the event's name, time, and venue.
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    private static final String ADD_EVENT_MESSAGE = "Event added successfully";
    private static final String ADD_PARTICIPANT_MESSAGE = "Participant added successfully";
    protected String eventName;
    protected String time;
    protected String venue;
    protected String participantName;

    /**
     * Constructs an AddCommand object with the specified event details.
     *
     * @param eventName The name of the event to be added.
     * @param time The time of the event to be added.
     * @param venue The venue of the event to be added.
     */
    public AddCommand(String eventName, String time, String venue) {
        this.eventName = eventName;
        this.time = time;
        this.venue = venue;
    }

    /**
     * Constructs an {@code AddCommand} with the specified participant name and event name.
     *
     * @param participantName the name of the participant to be added to the event.
     *                        If null, a new event will be created instead.
     * @param eventName      the name of the event to which the participant will be added
     *                       or the event to be created if no participant name is provided.
     */
    public AddCommand(String participantName, String eventName) {
        this.participantName = participantName;
        this.eventName = eventName;
    }

    /**
     * Executes the command to add an event or a participant to an event.
     * <p>
     * If no participant name is provided, this method will add a new event
     * to the event list with the specified event name, time, and venue.
     * If a participant name is provided, it will add the participant to the
     * specified event in the event list.
     * </p>
     *
     * @return a {@link CommandOutput} object containing a message about the result of the execution.
     *     The message indicates whether an event or participant was successfully added.
     */
    @Override
    public CommandOutput execute() {
        if (participantName == null) {
            this.eventList.addEvent(this.eventName, this.time, this.venue);
            return new CommandOutput(ADD_EVENT_MESSAGE, false);
        } else {
            this.eventList.addParticipantToEvent(this.participantName, this.eventName);
            return new CommandOutput(ADD_PARTICIPANT_MESSAGE, false);
        }
    }
}
