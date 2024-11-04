package seedu.manager.command;

import seedu.manager.enumeration.Priority;
import seedu.manager.event.EventList;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

//@@author LTK-1606
/**
 * Represents a command to filter out events from the event list.
 * The filter command will filter out and display all events with the specified details
 */
public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "filter";

    private static final String FILTER_BY_KEYWORD_MESSAGE = "Events successfully filtered by name!";
    private static final String FILTER_BY_DATE_MESSAGE = "Events successfully filtered by date!";
    private static final String FILTER_BY_TIME_MESSAGE = "Events successfully filtered by time!";
    private static final String FILTER_BY_DATE_TIME_MESSAGE = "Events successfully filtered by date-time!";
    private static final String FILTER_BY_PRIORITY_MESSAGE = "Events successfully filtered by priority!";

    private static final String INVALID_DATE_FORMAT_MESSAGE = """
            Invalid date format!
            Please use the following format for date: YYYY-MM-DD
            """;
    private static final String INVALID_TIME_FORMAT_MESSAGE = """
            Invalid time format!
            Please use the following format for time: HH:mm
            """;
    private static final String INVALID_DATE_TIME_FORMAT_MESSAGE = """
            Invalid date-time format!
            Please use the following format for date-time: YYYY-MM-DD HH:mm
            """;

    protected String flag;
    protected String filterWord;

    /**
     * Constructs a {@code FilterCommand} with the specified flag and filter word.
     *
     * @param flag       the filter flag that determines the type of filtering to be applied.
     * @param filterWord the word to filter by, based on the specified flag.
     */
    public FilterCommand(String flag, String filterWord) {
        super(false);
        this.flag = flag;
        this.filterWord = filterWord;
    }

    /**
     * Executes a filter command by filtering events in different ways,
     * depending on the flag.
     */
    @Override
    public void execute() {
        StringBuilder outputMessage = new StringBuilder();
        EventList filteredEvents = new EventList();

        switch (flag) {
        case "-e":
            String lowerCaseKeyword = filterWord.toLowerCase();
            filteredEvents = eventList.filterByKeyword(lowerCaseKeyword);
            outputMessage.append(FILTER_BY_KEYWORD_MESSAGE + "\n");
            break;
        case "-d":
            try {
                LocalDate eventDate = LocalDate.parse(filterWord,
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                filteredEvents = eventList.filterByDate(eventDate);
                outputMessage.append(FILTER_BY_DATE_MESSAGE + "\n");
            } catch (DateTimeException exception) {
                outputMessage.append(INVALID_DATE_FORMAT_MESSAGE);
            }
            break;
        case "-t":
            try {
                LocalTime eventTime = LocalTime.parse(filterWord,
                        DateTimeFormatter.ofPattern("HH:mm"));
                filteredEvents = eventList.filterByTime(eventTime);
                outputMessage.append(FILTER_BY_TIME_MESSAGE + "\n");
            } catch (DateTimeException exception) {
                outputMessage.append(INVALID_TIME_FORMAT_MESSAGE);
            }
            break;
        case "-x":
            try {
                LocalDateTime eventDateTime = LocalDateTime.parse(filterWord,
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                filteredEvents = eventList.filterByDateTime(eventDateTime);
                outputMessage.append(FILTER_BY_DATE_TIME_MESSAGE + "\n");
            } catch (DateTimeException exception) {
                outputMessage.append(INVALID_DATE_TIME_FORMAT_MESSAGE);
            }
            break;
        case "-u":
            Priority priority = Priority.valueOf(filterWord.trim().toUpperCase());
            filteredEvents = eventList.filterByPriority(priority);
            outputMessage.append(FILTER_BY_PRIORITY_MESSAGE + "\n");
            break;
        default:
            break;
        }

        for (int i = 0; i < filteredEvents.getListSize(); i++) {
            outputMessage.append(String.format("%d. %s\n", i + 1, filteredEvents.getEvent(i).toString()));
        }
        this.message = outputMessage.toString();
    }
}
