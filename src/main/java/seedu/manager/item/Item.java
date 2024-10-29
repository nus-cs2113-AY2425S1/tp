package seedu.manager.item;

import seedu.manager.event.Event;

/**
 * Represents an item, in an item list of an {@link Event}.
 */
public class Item {
    protected final String name;
    protected boolean isPresent;

    /**
     * Constructs a new Item with a given name.
     *
     * @param name the given name.
     */
    public Item(String name) {
        this.name = name;
        this.isPresent = false;
    }

    /**
     * Returns the name of the item.
     *
     * @return the name of the item.
     */
    public String getName() {
        return name;
    }

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
     * Returns a formatted string of the item
     *
     * @return a formatted string of the item
     */
    @Override
    public String toString() {
        return String.format("%s [%s]", this.name, this.markIfPresent());
    }
}

