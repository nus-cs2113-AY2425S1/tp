package seedu.manager.command;

//@@author glenn-chew
/**
 * Represents an executable menu command
 */
public class MenuCommand extends Command {
    public static final String COMMAND_WORD = "menu";
    private static final String MENU_MESSAGE = """
            Here are all the possible commands:
           
            menu: Displays a list of all commands.
            list: Displays a list of all events.
            add -e EVENT -t TIME -v VENUE -u PRIORITY: Adds an event to the event list.
            add -p PARTICIPANT -email EMAIL -e EVENT: Adds a participant to an event.
            add -m ITEM -e EVENT: Adds an item to an event.
            remove -e EVENT: Removes an event from the event list.
            remove -p PARTICIPANT -e EVENT: Removes a participant from an event.
            remove -m ITEM -e EVENT: Removes an item from an event.
            edit -e OLD_EVENT -name NEW_EVENT -t TIME -v VENUE -u PRIORITY: Edits an event's info.
            edit -p OLD_PARTICIPANT -name NEW_PARTICIPANT -email EMAIL -e EVENT: Edits a participant's info.
            edit -m OLD_ITEM > NEW_ITEM -e EVENT: Edits an item's info.
            view -e EVENT -y TYPE: Displays the list of participants or items of an event.
            mark -e EVENT -s STATUS: Marks an event as done or not done.
            mark -p PARTICIPANT -e EVENT -s STATUS: Marks a participant as present or absent.
            mark -m ITEM -e EVENT -s STATUS: Marks an item as accounted or unaccounted for.
            copy FROM_EVENT > TO_EVENT: Copies the participant list from one event to another.
            sort -by KEYWORD: Sorts events by name/time/priority.
            filter -e/-d/-t/-x/-u FILTER_DESCRIPTION: Filters events by name/date/time/date-time/priority.
            find -e EVENT -p NAME: Finds all participants with a specified name in an event.
            exit: Exits program.""";

    /**
     * Constructs a new MenuCommand
     */
    public MenuCommand() {
        super(false);
    }

    /**
     * Executes the menu command
     */
    @Override
    public void execute() {
        this.message = MENU_MESSAGE;
    }
}
