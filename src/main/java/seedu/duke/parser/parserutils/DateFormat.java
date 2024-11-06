package seedu.duke.parser.parserutils;

import seedu.duke.data.exception.DateParseException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.ui.Ui.showToUserException;

/**
 * Utility class for date and time parsing and validation. Converts dates, times, or date-times
 * to a standard format and supports relative terms like "today" and "tomorrow."
 */
public class DateFormat {

    public static final String MESSAGE_INVALID_DATE_INPUT = "Invalid input. " +
            "Please use the following formats instead: " +
            "\n'dd-MMM-yyyy' (e.g., 26-Oct-2024) for dates," +
            "\n'HH:mm' (e.g., 15:30) for time, or" +
            "\n'dd-MMM-yyyy HH:mm' (e.g., 26-Oct-2024 15:30) for date and time.";

    private static final Logger LOGGER = Logger.getLogger(DateFormat.class.getName());

    static {
        LOGGER.setLevel(Level.SEVERE);
    }

    private static final DateTimeFormatter STANDARD_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    private static final DateTimeFormatter STANDARD_TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter STANDARD_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");

    /**
     * Attempts to parse and validate a date, time, or date-time string to a standard format.
     * Ensures the date or date-time is not in the past.
     *
     * @param dateStr the input date, time, or date-time string
     * @return the formatted date/time string in the appropriate standard format
     * @throws DateParseException if the format is unrecognized, invalid, or in the past
     */
    public static String validateAndParseToStandardFormat(String dateStr) throws DateParseException {
        try {
            String parsedDateStr = parseToStandardFormat(dateStr);

            if (parsedDateStr.matches("\\d{2}-[A-Za-z]{3}-\\d{4}")) {  // Date-only format
                LocalDate parsedDate = LocalDate.parse(parsedDateStr, STANDARD_DATE_FORMAT);
                if (parsedDate.isBefore(LocalDate.now())) {
                    throw new DateParseException("The deadline date is in the past: " + parsedDateStr);
                }
            } else if (parsedDateStr.matches("\\d{2}-[A-Za-z]{3}-\\d{4} \\d{2}:\\d{2}")) {  // Date-time format
                LocalDateTime parsedDateTime = LocalDateTime.parse(parsedDateStr, STANDARD_DATE_TIME_FORMAT);
                if (parsedDateTime.isBefore(LocalDateTime.now())) {
                    throw new DateParseException("The deadline date-time is in the past: " + parsedDateStr);
                }
            } else {
                throw new DateParseException("Unrecognized date/time format: " + dateStr);
            }

            return parsedDateStr;

        } catch (DateTimeParseException e) {
            LOGGER.log(Level.SEVERE, "Failed to parse date/time: {0}", dateStr);
            throw new DateParseException("Invalid date/time format: " + dateStr);
        }
    }

    /**
     * Attempts to parse a date, time, or date-time string to a standard format.
     * Supports relative terms like "today", "tomorrow", "yesterday", and day names.
     *
     * @param dateStr the input date, time, or date-time string
     * @return the formatted date/time string in the appropriate standard format
     * @throws DateParseException if the format is unrecognized or invalid
     */
    public static String parseToStandardFormat(String dateStr) throws DateParseException {
        try {
            if (isRelativeOrDayOfWeek(dateStr)) {
                return parseRelativeOrDayOfWeek(dateStr);
            }

            LocalDate date = tryParseDate(dateStr);
            if (date != null) {
                return date.format(STANDARD_DATE_FORMAT) + " 23:59";
            }

            LocalTime time = tryParseTime(dateStr);
            if (time != null) {
                LocalDateTime dateTime = LocalDateTime.of(LocalDate.now(), time);
                return dateTime.format(STANDARD_DATE_TIME_FORMAT);
            }

            LocalDateTime dateTime = tryParseDateTime(dateStr);
            if (dateTime != null) {
                return dateTime.format(STANDARD_DATE_TIME_FORMAT);
            }

        } catch (DateTimeParseException e) {
            LOGGER.log(Level.SEVERE, "Failed to parse date/time: Unrecognized or invalid format for input: {0}",
                    dateStr);
            throw new DateParseException("Unrecognized or invalid date/time format: " + dateStr);
        }

        LOGGER.log(Level.SEVERE, "Failed to parse date/time: Unrecognized or invalid format for input: {0}",
                dateStr);
        throw new DateParseException("Unrecognized or invalid date/time format: " + dateStr);
    }

    /**
     * Checks if the input string is a relative date term or a day of the week.
     *
     * @param dateStr the input string
     * @return true if the input is a relative term or day of the week, false otherwise
     */
    private static boolean isRelativeOrDayOfWeek(String dateStr) {
        return dateStr.equalsIgnoreCase("today") ||
                dateStr.equalsIgnoreCase("tomorrow") ||
                dateStr.equalsIgnoreCase("yesterday") ||
                parseDayOfWeek(dateStr) != null;
    }

    /**
     * Parses relative date terms like "today", "tomorrow", and "yesterday" or day names.
     *
     * @param dateStr the input date string
     * @return the formatted date-time string for the relative date or day name
     */
    private static String parseRelativeOrDayOfWeek(String dateStr) {
        LocalDate date;
        switch (dateStr.toLowerCase()) {
        case "today":
            date = LocalDate.now();
            break;
        case "tomorrow":
            date = LocalDate.now().plusDays(1);
            break;
        case "yesterday":
            date = LocalDate.now().minusDays(1);
            break;
        default:
            DayOfWeek targetDay = parseDayOfWeek(dateStr);
            if (targetDay != null) {
                date = calculateNextOccurrenceOfDay(targetDay);
            } else {
                throw new IllegalArgumentException("Invalid relative date or day of the week: " + dateStr);
            }
        }
        return LocalDateTime.of(date, LocalTime.of(23, 59)).format(STANDARD_DATE_TIME_FORMAT);
    }

    /**
     * Parses a string to a DayOfWeek enum value (e.g., "Monday" to DayOfWeek.MONDAY).
     *
     * @param dayName the name of the day
     * @return the DayOfWeek enum value, or null if invalid
     */
    private static DayOfWeek parseDayOfWeek(String dayName) {
        try {
            return DayOfWeek.valueOf(dayName.toUpperCase());
        } catch (IllegalArgumentException e) {
            showToUserException(MESSAGE_INVALID_DATE_INPUT);
            return null;
        }
    }

    /**
     * Calculates the next occurrence of the specified day of the week.
     * If today is the specified day, returns today's date.
     *
     * @param targetDay the target day of the week
     * @return the next occurrence of the target day
     */
    private static LocalDate calculateNextOccurrenceOfDay(DayOfWeek targetDay) {
        LocalDate today = LocalDate.now();
        int daysUntilNext = (targetDay.getValue() - today.getDayOfWeek().getValue() + 7) % 7;
        return today.plusDays(daysUntilNext);
    }

    /**
     * Tries to parse the input string as a date using known date formats.
     *
     * @param dateStr the input date string
     * @return the parsed LocalDate, or null if parsing fails
     */
    private static LocalDate tryParseDate(String dateStr) {
        for (DateFormats format : DateFormats.values()) {
            try {
                return LocalDate.parse(dateStr, format.getFormatter());
            } catch (DateTimeParseException e) {
                // Continue to the next format if parsing fails
            }
        }
        return null;
    }

    /**
     * Tries to parse the input string as a time using known time formats.
     *
     * @param timeStr the input time string
     * @return the parsed LocalTime, or null if parsing fails
     */
    private static LocalTime tryParseTime(String timeStr) {
        for (TimeFormats format : TimeFormats.values()) {
            try {
                return LocalTime.parse(timeStr, format.getFormatter());
            } catch (DateTimeParseException e) {
                // Continue to the next format if parsing fails
            }
        }
        return null;
    }

    /**
     * Tries to parse the input string as a date-time using known date-time formats.
     *
     * @param dateTimeStr the input date-time string
     * @return the parsed LocalDateTime, or null if parsing fails
     */
    private static LocalDateTime tryParseDateTime(String dateTimeStr) {
        for (DateTimeFormats format : DateTimeFormats.values()) {
            try {
                return LocalDateTime.parse(dateTimeStr, format.getFormatter());
            } catch (DateTimeParseException e) {
                // Continue to the next format if parsing fails
            }
        }
        return null;
    }
}
