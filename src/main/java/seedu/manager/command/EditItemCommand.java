package seedu.manager.command;

/**
 * Represents a command to edit an item for an event.
 * The edit command will store the event's name, the item to be edited.
 */
public class EditItemCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    private static final String EDIT_ITEM_MESSAGE = "The item has been updated to:\n";
    private static final String EDIT_FAILURE_MESSAGE = "Event/Item not found!";
    protected String itemName;
    protected String itemNewName;
    protected String eventName;

    //@@author MatchaRRR
    /**
     * Constructs an EditItemCommand object with the specified event name and item name,
     *
     * @param itemName The name of the original item.
     * @param itemNewName The name of the new item.
     * @param eventName The name of the event associated with the participant.
     */
    public EditItemCommand(String itemName, String itemNewName, String eventName) {
        super(false);
        this.itemName = itemName;
        this.itemNewName = itemNewName;
        this.eventName = eventName;
    }

    @Override
    public void execute() {
        String updatedName = eventList.editItem(itemName, itemNewName, eventName);

        if (updatedName.equalsIgnoreCase("")) {
            message = EDIT_FAILURE_MESSAGE;
        } else {
            message = getOutputMessage(updatedName);
        }
    }

    /**
     * Returns a success output message with the given item details.
     *
     * @param name the updated item name.
     * @return an output message with name and the event name.
     */
    private String getOutputMessage(String name) {
        StringBuilder outputMessage = new StringBuilder();
        outputMessage.append(EDIT_ITEM_MESSAGE);
        String formattedString = String.format("Item name: %s / Event name: %s", name, eventName);
        outputMessage.append(formattedString);
        return outputMessage.toString();
    }
}
