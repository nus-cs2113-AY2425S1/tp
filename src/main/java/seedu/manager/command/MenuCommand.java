package seedu.manager.command;

/**
 * Represents an executable menu command
 */
public class MenuCommand extends Command {
    public static final String COMMAND_WORD = "menu";
    private static final String MENU_MESSAGE = """
            Here are the possible commands:
           
            add -e EVENT_NAME: Add an event to the event list.
            list: List events.
            remove -e EVENT_NAME: Remove an event from the event list.
            add -p PARTICIPANT_NAME -e EVENT_NAME: Add a participant an event.
            view -e EVENT_NAME: View the list of participants of an event.
            remove -p PARTICIPANT_NAME -e EVENT_NAME: Remove a participant from an event.
            """;

    /**
     * Returns a command output with the menu message
     *
     * @return The command output with the menu message
     */
    @Override
    public CommandOutput execute() {
        return new CommandOutput(MENU_MESSAGE, false);
    }
}
