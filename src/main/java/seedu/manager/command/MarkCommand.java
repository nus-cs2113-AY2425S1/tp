package seedu.manager.command;

//@@author jemehgoh
/**
 * Represents an executable mark command
 */
public abstract class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    protected static final String INVALID_EVENT_MESSAGE = "Event not found!";

    protected String eventName;
    protected boolean isToMark;

    /**
     * Constructs a new MarkCommand with the given event name.
     *
     * @param eventName the given event name.
     * @param isToMark true if the item is to be marked, false if it is to be unmarked.
     */
    public MarkCommand(String eventName, boolean isToMark) {
        super(false);
        this.eventName = eventName;
        this.isToMark = isToMark;
    }

    /**
     * Executes a mark command.
     */
    @Override
    public abstract void execute();
}
