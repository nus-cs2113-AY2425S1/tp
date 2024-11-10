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

    private static final String FILTER_BY_NAME_MESSAGE = "Events successfully filtered by name!";
    private static final String FILTER_BY_DATE_MESSAGE = "Events successfully filtered by date!";
    private static final String FILTER_BY_TIME_MESSAGE = "Events successfully filtered by time!";
    private static final String FILTER_BY_DATE_TIME_MESSAGE = "Events successfully filtered by date-time!";
    private static final String FILTER_BY_PRIORITY_MESSAGE = "Events successfully filtered by priority!";
    private static final String FILTER_RESULT_EMPTY_MESSAGE = "No events found!";

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
    private static final String INVALID_PRIORITY_FORMAT_MESSAGE = """
            Invalid priority format!
            Please use the following format for priority: high/medium/low
            """;

    protected String flag;
    protected String filterWord;
    private EventList filteredEvents;

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
        filteredEvents = new EventList();

        switch (flag) {
        case "-e":
            outputMessage.append(filterEventsByName());
            break;
        case "-d":
            outputMessage.append(filterEventsByDate());
            break;
        case "-t":
            outputMessage.append(filterEventsByTime());
            break;
        case "-x":
            outputMessage.append(filterEventsByDateTime());
            break;
        case "-u":
            outputMessage.append(filterEventsByPriority());
            break;
        default:
            break;
        }

        for (int i = 0; i < filteredEvents.getListSize(); i++) {
            outputMessage.append(String.format("%d. %s\n", i + 1, filteredEvents.getEvent(i).toString()));
        }
        this.message = outputMessage.toString();
    }

    /**
     * Filters the even list by name and updates {@code filteredEvents} with the filtered list.
     *
     * @return successful message for filter by name.
     */
    private String filterEventsByName() {
        String lowerCaseKeyword = filterWord.toLowerCase();
        filteredEvents = eventList.filterByName(lowerCaseKeyword);
        if (filteredEvents.getListSize() == 0) {
            return FILTER_RESULT_EMPTY_MESSAGE;
        }
        return FILTER_BY_NAME_MESSAGE + "\n";
    }

    /**
     * Filters the even list by date and updates {@code filteredEvents} with the filtered list.
     *
     * @return successful message for filter by date or an error message if date is invalid.
     */
    private String filterEventsByDate() {
        try {
            LocalDate eventDate = LocalDate.parse(filterWord,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            filteredEvents = eventList.filterByDate(eventDate);
            if (filteredEvents.getListSize() == 0) {
                return FILTER_RESULT_EMPTY_MESSAGE;
            }
            return FILTER_BY_DATE_MESSAGE + "\n";
        } catch (DateTimeException exception) {
            return INVALID_DATE_FORMAT_MESSAGE;
        }
    }

    /**
     * Filters the even list by time and updates {@code filteredEvents} with the filtered list.
     *
     * @return successful message for filter by time or an error message if time is invalid.
     */
    private String filterEventsByTime() {
        try {
            LocalTime eventTime = LocalTime.parse(filterWord,
                    DateTimeFormatter.ofPattern("HH:mm"));
            filteredEvents = eventList.filterByTime(eventTime);
            if (filteredEvents.getListSize() == 0) {
                return FILTER_RESULT_EMPTY_MESSAGE;
            }
            return FILTER_BY_TIME_MESSAGE + "\n";
        } catch (DateTimeException exception) {
            return INVALID_TIME_FORMAT_MESSAGE;
        }
    }

    /**
     * Filters the even list by date-time and updates {@code filteredEvents} with the filtered list.
     *
     * @return successful message for filter by date-time or an error message if date is invalid.
     */
    private String filterEventsByDateTime() {
        try {
            LocalDateTime eventDateTime = LocalDateTime.parse(filterWord,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            filteredEvents = eventList.filterByDateTime(eventDateTime);
            if (filteredEvents.getListSize() == 0) {
                return FILTER_RESULT_EMPTY_MESSAGE;
            }
            return FILTER_BY_DATE_TIME_MESSAGE + "\n";
        } catch (DateTimeException exception) {
            return INVALID_DATE_TIME_FORMAT_MESSAGE;
        }
    }

    /**
     * Filters the even list by priority and updates {@code filteredEvents} with the filtered list.
     *
     * @return successful message for filter by priority
     */
    private String filterEventsByPriority() {
        try {
            Priority priority = Priority.valueOf(filterWord.trim().toUpperCase());
            filteredEvents = eventList.filterByPriority(priority);
            if (filteredEvents.getListSize() == 0) {
                return FILTER_RESULT_EMPTY_MESSAGE;
            }
            return FILTER_BY_PRIORITY_MESSAGE + "\n";
        } catch(IllegalArgumentException exception) {
            return INVALID_PRIORITY_FORMAT_MESSAGE;
        }
    }
}
