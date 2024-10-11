package seedu.manager.command;

import seedu.manager.event.EventList;

/**
 * Represents an executable add command
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    private static final String ADD_MESSAGE = "Event added successfully";
    protected String eventName;

    public AddCommand(String desc) {
        this.eventName = desc;
    }

    /**
     * Returns a command output with an add message
     *
     * @return The command output with an add message
     */
    public CommandOutput execute() {
        this.eventList.addEvent(this.eventName);
        //this.eventList.getList();
        return new CommandOutput(ADD_MESSAGE, false);
    }
}
