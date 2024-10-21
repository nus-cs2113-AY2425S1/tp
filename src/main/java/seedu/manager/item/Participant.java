package seedu.manager.item;

import seedu.manager.event.Event;

/**
 * Represents an event participant, in the participant list of an {@link Event}.
 */
public class Participant extends Item {

    /**
     * Constructs a new Item with a given name
     *
     * @param name the given name
     */
    public Participant(String name) {
        super(name);
    }
}
