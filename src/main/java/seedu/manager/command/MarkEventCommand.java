package seedu.manager.command;

import seedu.manager.event.Event;

import java.util.Optional;

//@@author jemehgoh
/**
 * Represents an executable mark command for marking events.
 */
public class MarkEventCommand extends MarkCommand {
    private static final String EVENT_MARK_MESSAGE = "Event marked as done";
    private static final String EVENT_UNMARK_MESSAGE = "Event marked not done";

    /**
     * Constructs a new MarkEventCommand with the given event name.
     *
     * @param eventName the event name.
     * @param isToMark true if the event is to be marked done, false otherwise.
     */
    public MarkEventCommand(String eventName, boolean isToMark) {
        super(eventName, isToMark);
    }

    /**
     * Executes a mark command by marking the specified event as done or not done,
     * depending on the value of isToMark.
     */
    @Override
    public void execute() {
        Optional<Event> eventToMark = this.eventList.getEventByName(this.eventName);

        if (eventToMark.isPresent()) {
            eventToMark.get().setDone(this.isToMark);
            this.message = (this.isToMark) ? EVENT_MARK_MESSAGE : EVENT_UNMARK_MESSAGE;
        } else {
            this.message = INVALID_EVENT_MESSAGE;
        }
    }
}
