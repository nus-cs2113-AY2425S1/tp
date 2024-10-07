package seedu.manager.command;

/**
 * Represents an executable menu command
 */
public class MenuCommand extends Command {
    private static final String COMMAND_WORD = "menu";
    private static final String MENU_MESSAGE = "Here are the possible commands:\n\n"
            + "add -e EVENT_NAME -st START_TIME -et END_TIME -v VENUE -d DESCRIPTION: "
            + "Add an event to the event list.\n"
            + "list: List events.\n"
            + "remove -e EVENT_NAME: Remove an event from the event list.\n"
            + "add -p PARTICIPANT_NAME -e EVENT_NAME: Add a participant an event.\n"
            + "view -e EVENT_NAME: View the list of participants of an event.\n"
            + "remove -p PARTICIPANT_NAME -e EVENT_NAME: Remove a participant from an event. \n";

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