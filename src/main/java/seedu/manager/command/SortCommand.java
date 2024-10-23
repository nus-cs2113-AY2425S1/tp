package seedu.manager.command;

import seedu.manager.event.Event;
import seedu.manager.event.EventList;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command to sort the events in different ways.
 * The sort command will store the sorting keyword.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    private static final String SORT_BY_NAME_MESSAGE = "Successfully sort events by name!";

    protected String keyword;

    public SortCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    @Override
    public void execute() {
        StringBuilder outputMessage = new StringBuilder();
        EventList sortedEventList = new EventList(eventList);  // deep copy

        switch(keyword){
        case "name":
            sortedEventList.sortByName();
            outputMessage.append(SORT_BY_NAME_MESSAGE + "\n");
        case "time", "priority":
            break;
        }

        // print out the sorted new list.
        for (int i = 0; i < sortedEventList.getListSize(); i++) {
            outputMessage.append(String.format("%d. %s\n", i + 1, sortedEventList.getEvent(i).toString()));
        }
        this.message = outputMessage.toString();
    }
}
