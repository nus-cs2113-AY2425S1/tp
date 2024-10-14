package seedu.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for handling date-time parsing.
 */
public class DateTimeUtils {
    // Static error messages
    public static final String MESSAGE_INVALID_DATE_FORMAT = "Your date and/or time is invalid!";
    // DateTimeFormatter for reading date-time strings in the specified format.
    private static final DateTimeFormatter DATETIME_READ_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    // DateTimeFormatter for printing date-time strings in a user-friendly format.
    private static final DateTimeFormatter DATETIME_PRINT_FORMAT
            = DateTimeFormatter.ofPattern("EEEE, yyyy-MM-dd hh.mm a");

    /**
     * Parses a date-time string into a LocalDateTime object.
     *
     * @param datetime The date-time string to be parsed.
     * @return A LocalDateTime representing the parsed date and time.
     * @throws RuntimeException If the date-time format is invalid.
     */
    public static LocalDateTime parseDateTime(String datetime) throws Exception {
        String[] datetimeParts = datetime.trim().split(" ", 2);

        // If only the date is provided, append time as "2359" (11:59 PM)
        if (datetimeParts.length == 1) {
            datetime += " 2359";
        }

        LocalDateTime result;
        try {
            result = LocalDateTime.parse(datetime, DATETIME_READ_FORMAT); // Parse the date-time string
        } catch (DateTimeParseException e) {
            throw new Exception(MESSAGE_INVALID_DATE_FORMAT); // Throw custom exception
        }

        return result;
    }

    /**
     * Formats a LocalDateTime object into a user-friendly string.
     *
     * @param dateTime The LocalDateTime to format.
     * @return A formatted string representing the date and time.
     */
    public static String getDateTimeString(LocalDateTime dateTime) {
        return dateTime.format(DATETIME_PRINT_FORMAT); // Format and return the date-time string
    }
}
