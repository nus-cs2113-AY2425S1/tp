package seedu.manager.command;

//@@author MatchaRRR
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    private static final String LIST_MESSAGE = "There are %1$d events in your list! " +
            "Here are your scheduled events:";
    private static final String ONE_EVENT_LIST_MESSAGE = "There is %1$d event in your list! " +
            "Here is your scheduled event:";
    private static final String EMPTY_LIST_MESSAGE = "There are %1$d events in your list!";

    /**
     * Constructs a new ListCommand
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Executes the ListCommand by getting a list of all events
     */
    public void execute() {
        StringBuilder outputMessage;
        if (eventList.getListSize() == 0) {
            outputMessage = new StringBuilder(
                    String.format(EMPTY_LIST_MESSAGE, eventList.getListSize()) + "\n");
        } else if (eventList.getListSize() == 1) {
            outputMessage = new StringBuilder(
                    String.format(ONE_EVENT_LIST_MESSAGE, eventList.getListSize()) + "\n");
        } else {
            outputMessage = new StringBuilder(
                    String.format(LIST_MESSAGE, eventList.getListSize()) + "\n");
        }
        for (int i = 0; i < eventList.getListSize(); i++) {
            assert eventList.getEvent(i) != null : "Event at index " + i + " should not be null.";
            outputMessage.append(String.format("%d. %s\n", i + 1, eventList.getEvent(i).toString()));
        }

        this.message = outputMessage.toString();
    }
}
