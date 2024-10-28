package seedu.manager.command;

import seedu.manager.enumeration.Priority;
import seedu.manager.event.EventList;

public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "find";

    private static final String FILTER_BY_PRIORITY_MESSAGE = "Events successfully filtered by priority!";

    protected String keyword;
    protected String filterWord;

    public FilterCommand(String keyword, String filterWord) {
        super(false);
        this.keyword = keyword;
        this.filterWord = filterWord;
    }

    @Override
    public void execute() {
        StringBuilder outputMessage = new StringBuilder();
        EventList filteredEvents = new EventList();

        switch (keyword) {
        case "-e":
            break;
        case "-t":
            break;
        case "-u":
            Priority priority = Priority.valueOf(filterWord.trim().toUpperCase());
            filteredEvents = eventList.filterByPriority(priority);
            outputMessage.append(FILTER_BY_PRIORITY_MESSAGE + "\n");
            break;
        default:
        }

        for (int i = 0; i < filteredEvents.getListSize(); i++) {
            outputMessage.append(String.format("%d. %s\n", i + 1, filteredEvents.getEvent(i).toString()));
        }
        this.message = outputMessage.toString();
    }
}
