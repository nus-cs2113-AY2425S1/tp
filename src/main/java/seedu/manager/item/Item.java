package seedu.manager.item;

/**
 * Represents an item to be stored in an item list for an event
 */
public class Item {
    private final String name;
    private boolean isPresent;

    /**
     * Constructs a new Item with a given name
     *
     * @param name the given name
     */
    public Item(String name) {
        this.name = name;
        this.isPresent = false;
    }

    /**
     * Returns the name of the item.
     *
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the item as present or not present.
     *
     * @param isPresent whether the item is present or not present
     */
    public void setPresent(boolean isPresent) {
        this.isPresent = isPresent;
    }

    /**
     * Returns "present" if isPresent is true, "not present" otherwise.
     *
     * @return "present" if isPresent is true, "not present" otherwise.
     */
    public String markIfPresent() {
        return (this.isPresent) ? "present" : "not present";
    }

    /**
     * Returns a formatted string of the item
     *
     * @return a formatted string of the item
     */
    @Override
    public String toString() {
        return String.format("%s (status: %s)", this.name, this.markIfPresent());
    }
}
