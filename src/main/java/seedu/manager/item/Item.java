package seedu.manager.item;

import seedu.manager.event.Event;

/**
 * Represents an item, in an item list of an {@link Event}.
 */
public class Item {
    protected String name;
    protected boolean isPresent;

    /**
     * Constructs a new Item with a given name and if the item is to be accounted.
     *
     * @param name the given name.
     * @param isPresent {@code true} if the item is accounted for, {@code false} otherwise.
     */
    public Item(String name, boolean isPresent) {
        this.name = name;
        this.isPresent = isPresent;
    }

    /**
     * Returns the name of the item.
     *
     * @return the name of the item.
     */
    public String getName() {
        return name;
    }

    //@@author MatchaRRR
    /**
     * Sets the name of the item.
     */
    public void setName(String itemNewName) {
        this.name = itemNewName;
    }

    //@@author jemehgoh
    /**
     * Sets the item as present or not present.
     *
     * @param isPresent whether the item is present or not present.
     */
    public void setPresent(boolean isPresent) {
        this.isPresent = isPresent;
    }

    /**
     * Returns "X" if isPresent is true, " " otherwise.
     *
     * @return "X" if isPresent is true, " " otherwise.
     */
    public String markIfPresent() {
        return (this.isPresent) ? "X" : " ";
    }

    /**
     * Returns "Y" if isPresent is true, "N" otherwise.
     * For saving the item's mark status to files.
     *
     * @return "Y" if isPresent is true, "N" otherwise.
     */
    public String markFileLineIfPresent() {
        return (this.isPresent) ? "Y" : "N";
    }

    /**
     * Returns a formatted string of the item
     *
     * @return a formatted string of the item
     */
    @Override
    public String toString() {
        return String.format("%s [%s]", this.name, this.markIfPresent());
    }
}

