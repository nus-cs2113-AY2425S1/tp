package seedu.duke.parser.parserutils;

import java.time.format.DateTimeFormatter;

/**
 * Enum representing various date and time formats commonly used in parsing date-time strings.
 * Each enum constant encapsulates a {@code DateTimeFormatter} for a specific date-time pattern.
 * Provides formatters for both 24-hour and 12-hour time formats, supporting flexibility in date-time parsing.
 */
public enum DateTimeFormats {

    // Common Date and 24-hour Time Combinations
    /** Format: d/M/yyyy HH:mm (e.g., 23/10/2024 14:30) */
    DMY_SLASH_TIME(DateTimeFormatter.ofPattern("d/M/yyyy HH:mm")),
    /** Format: d-M-yyyy HH:mm (e.g., 23-10-2024 14:30) */
    DMY_DASH_TIME(DateTimeFormatter.ofPattern("d-M-yyyy HH:mm")),
    /** Format: yyyy/M/d HH:mm (e.g., 2024/10/23 14:30) */
    YMD_SLASH_TIME(DateTimeFormatter.ofPattern("yyyy/M/d HH:mm")),
    /** Format: yyyy-MM-dd HH:mm (e.g., 2024-10-23 14:30) */
    YMD_DASH_TIME(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
    /** Format: M/d/yyyy HH:mm (e.g., 10/23/2024 14:30) */
    MDY_SLASH_TIME(DateTimeFormatter.ofPattern("M/d/yyyy HH:mm")),
    /** Format: MMMM d, yyyy HH:mm (e.g., October 23, 2024 14:30) */
    FULL_TEXT_WITH_24_HOUR_TIME(DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm")),

    // Common Date and 12-hour Time Combinations
    /** Format: d/M/yyyy hh:mm a (e.g., 23/10/2024 02:30 PM) */
    DMY_SLASH_TIME_AMPM(DateTimeFormatter.ofPattern("d/M/yyyy hh:mm a")),
    /** Format: d-M-yyyy hh:mm a (e.g., 23-10-2024 02:30 PM) */
    DMY_DASH_TIME_AMPM(DateTimeFormatter.ofPattern("d-M-yyyy hh:mm a")),
    /** Format: yyyy/M/d hh:mm a (e.g., 2024/10/23 02:30 PM) */
    YMD_SLASH_TIME_AMPM(DateTimeFormatter.ofPattern("yyyy/M/d hh:mm a")),
    /** Format: yyyy-MM-dd hh:mm a (e.g., 2024-10-23 02:30 PM) */
    YMD_DASH_TIME_AMPM(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a")),
    /** Format: M/d/yyyy hh:mm a (e.g., 10/23/2024 02:30 PM) */
    MDY_SLASH_TIME_AMPM(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")),
    /** Format: MMMM d, yyyy hh:mm a (e.g., October 23, 2024 02:30 PM) */
    FULL_TEXT_WITH_12_HOUR_TIME(DateTimeFormatter.ofPattern("MMMM d, yyyy hh:mm a")),

    // Full Text Date with Ordinal Day (e.g., October 23rd, 2024)
    /** Format: MMMM d'th', yyyy hh:mm a (e.g., October 23rd, 2024 02:30 PM) */
    FULL_TEXT_ORDINAL_DATE_TIME_AMPM(DateTimeFormatter.ofPattern("MMMM d'th', yyyy hh:mm a")),
    /** Format: MMMM d'th', yyyy HH:mm (e.g., October 23rd, 2024 14:30) */
    FULL_TEXT_ORDINAL_DATE_TIME_24HR(DateTimeFormatter.ofPattern("MMMM d'th', yyyy HH:mm"));

    private final DateTimeFormatter formatter;

    /**
     * Constructs a {@code DateTimeFormats} enum constant with the specified date-time formatter.
     *
     * @param formatter the {@code DateTimeFormatter} representing the date-time pattern.
     */
    DateTimeFormats(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    /**
     * Retrieves the {@code DateTimeFormatter} associated with this date-time format.
     *
     * @return the {@code DateTimeFormatter} for the specific date-time format.
     */
    public DateTimeFormatter getFormatter() {
        return formatter;
    }
}
