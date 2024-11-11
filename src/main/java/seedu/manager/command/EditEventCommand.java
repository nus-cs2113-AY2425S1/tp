package seedu.manager.command;

import seedu.manager.enumeration.Priority;

import java.time.LocalDateTime;

/**
 * Represents a command to edit an event to the event list.
 * The edit command will store the event's name, time, and venue.
 */
public class EditEventCommand extends Command{
    public static final String COMMAND_WORD = "edit";
    private static final String EDIT_EVENT_MESSAGE = "The following event has been updated to:\n";
    private static final String EDIT_FAILURE_MESSAGE = "Event not found!";
    protected String eventName;
    protected String eventNewName;
    protected LocalDateTime eventTime;
    protected String eventVenue;
    protected Priority eventPriority;

    //@@author MatchaRRR
    /**
     * Constructs an EditEventCommand object with the specified event name, event time, event venue and event priority.
     *
     * @param eventName The name of the event to be edited.
     * @param eventNewName The new name of the event.
     * @param eventTime The new time of the event.
     * @param eventVenue The new venue of the event.
     * @param eventPriority The new priority of the event.
     */
    public EditEventCommand(String eventName, String eventNewName, LocalDateTime eventTime, String eventVenue,
                            Priority eventPriority) {
        super(false);
        this.eventName = eventName;
        this.eventNewName = eventNewName;
        this.eventTime = eventTime;
        this.eventVenue = eventVenue;
        this.eventPriority = eventPriority;
    }

    /**
     * Executes the edit operation, updating the event's information if the event exits.
     * If successful, a confirmation message
     * is set; otherwise, an error message is set.
     */
    @Override
    public void execute() {
        String updatedName = eventList.editEvent(eventName, eventNewName, eventTime, eventVenue, eventPriority);
        if (updatedName.equalsIgnoreCase("")) {
            message = EDIT_FAILURE_MESSAGE;
        } else {
            message = getOutputMessage(updatedName, eventTime, eventVenue, eventPriority);
        }
    }

    /**
     * Returns a success output message with the given event details.
     *
     * @param name the updated event name.
     * @param time the updated event time.
     * @param venue the updated event venue.
     * @param priority the updated event priority.
     * @return an output message with name, time, venue and priority.
     */
    private String getOutputMessage(String name, LocalDateTime time, String venue, Priority priority) {
        String dateTimeString = time.toString();
        StringBuilder outputMessage = new StringBuilder();
        outputMessage.append(EDIT_EVENT_MESSAGE);
        String formattedString = String.format("Event name: %s / Event time: %s / Event venue: %s / " +
                "Event priority: %s", name, dateTimeString, venue, priority);
        outputMessage.append(formattedString);
        return outputMessage.toString();
    }
}
