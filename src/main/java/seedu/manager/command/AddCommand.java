package seedu.manager.command;

/**
 * Represents a command to add an event to the event list.
 * The add command will store the event's name, time, and venue.
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    private static final String ADD_MESSAGE = "Event added successfully";
    protected String eventName;
    protected String time;
    protected String venue;

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
     * Executes the command to add an event to the event list.
     * The event is added with the provided name, time, and venue.
     *
     * @return The command output indicating that the event was added successfully.
     */
    @Override
    public CommandOutput execute() {
        this.eventList.addEvent(this.eventName, this.time, this.venue);
        return new CommandOutput(ADD_MESSAGE, false);
    }
}
