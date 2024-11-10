package seedu.manager.item;

import seedu.manager.event.Event;


/**
 * Represents an event participant, in the participant list of an {@link Event}.
 */
public class Participant extends Item {
    private String email;

    /**
     * Constructs a new Participant with a given name.
     *
     * @param name the given name.
     */
    public Participant(String name, String email, boolean isPresent) {
        super(name, isPresent);
        this.email = email;
    }

    //@@author jemehgoh
    /**
     * Returns the name of the participant.
     *
     * @return the name of the participant.
     */
    public String getName() {
        return name;
    }

    //@@author KuanHsienn
    /**
     * Returns the contact email of the participant.
     *
     * @return the contact email of the participant.
     */
    public String getEmail() {
        return this.email;
    }

    //@@author KuanHsienn
    /**
     * Sets the contact email of the participant.
     *
     * @param newEmail the new contact email of the participant.
     */
    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    //@@author glenn-chew
    /**
     * Returns a formatted string of the participant.
     *
     * @return a formatted string of the participant.
     */
    @Override
    public String toString() {
        return String.format("Name: %s / Email: %s [%s]",
                this.name, this.email, this.markIfPresent());
    }
}
