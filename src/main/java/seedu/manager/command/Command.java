package seedu.manager.command;

import seedu.manager.event.EventList;

/**
 * Represents an executable command
 */
public abstract class Command {
    protected EventList eventList;

    public void setData(EventList events) {
        this.eventList = events;
    }

    /**
     * Returns the output of the executable command
     */
    public abstract CommandOutput execute();
}
