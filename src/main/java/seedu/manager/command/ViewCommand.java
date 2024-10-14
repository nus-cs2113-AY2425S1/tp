package seedu.manager.command;

import seedu.manager.event.Event;

public class ViewCommand extends Command{
    public static final String COMMAND_WORD = "view";
    private static final String VIEW_MESSAGE = "There are %d participants in %s! " +
            "Here are your participants:";
    protected String eventName;

    /**
     * Constructs an ViewCommand object with the for the specified event.
     *
     * @param eventName The name of the event to be viewed.
     */
    public ViewCommand(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Returns a command output with a list message
     *
     * @return The command output with a list message
     */
    public CommandOutput execute() {
        Event event = null;
        for (int i = 0; i < eventList.getListSize(); i++) {
            if (eventList.getEvent(i).getEventName().equals(eventName)) {
                event = eventList.getEvent(i);
            }
        }

        //to handle errors in the future

        StringBuilder outputMessage = new StringBuilder(
                String.format(VIEW_MESSAGE, event.getParticipantCount(), eventName) + "\n");
        int count = 1;
        for (String participant : event.getParticipantList()) {
            outputMessage.append(String.format("%d. %s\n", count , participant));
            count++;
        }

        return new CommandOutput(outputMessage.toString(), false);
    }
}
