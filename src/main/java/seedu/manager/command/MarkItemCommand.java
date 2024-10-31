package seedu.manager.command;

import seedu.manager.event.Event;

import java.util.Optional;

//@@author jemehgoh
/**
 * Represents an executable mark command for marking items.
 */
public class MarkItemCommand extends MarkCommand {
    private static final String ITEM_MARK_MESSAGE = "Item accounted for.";
    private static final String ITEM_UNMARK_MESSAGE = "Item unaccounted for.";
    private static final String INVALID_ITEM_MESSAGE = "Item not found!";

    private final String itemName;

    /**
     * Constructs a new MarkItemCommand with a given item name, event name and whether to mark
     * or unmark the item.
     *
     * @param itemName the name of the item.
     * @param eventName the name of the event.
     * @param toMark true if the item is to be marked present, false if he is to be marked absent.
     */
    public MarkItemCommand(String itemName, String eventName, boolean toMark) {
        super(eventName, toMark);
        this.itemName = itemName;
    }

    /**
     * Executes the mark item command, by marking the item as present or absent
     */
    @Override
    public void execute() {
        Optional<Event> event = eventList.getEventByName(eventName);

        if (event.isEmpty()) {
            message = INVALID_EVENT_MESSAGE;
            return;
        }

        boolean isMarked = event.get().markItemByName(itemName, toMark);

        if (isMarked) {
            message = (toMark) ? ITEM_MARK_MESSAGE : ITEM_UNMARK_MESSAGE;
        } else {
            message = INVALID_ITEM_MESSAGE;
        }
    }
}
