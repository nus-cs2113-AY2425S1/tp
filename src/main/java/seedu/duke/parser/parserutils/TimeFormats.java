package seedu.duke.parser.parserutils;

import java.time.format.DateTimeFormatter;

/**
 * Enum representing various time formats commonly used in parsing time strings.
 * Each enum constant encapsulates a {@code DateTimeFormatter} for a specific time pattern.
 * Provides formatters for both 24-hour and 12-hour time formats with optional separators.
 */
public enum TimeFormats {

    // 24-hour time formats
    /** Format: HH:mm (e.g., 14:30) */
    HOUR_MINUTE(DateTimeFormatter.ofPattern("HH:mm")),
    /** Format: HHmm (e.g., 1430) */
    HOUR_MINUTE_NO_SEPARATOR(DateTimeFormatter.ofPattern("HHmm")),

    // 12-hour time formats with AM/PM
    /** Format: hh:mm a (e.g., 02:30 PM) */
    HOUR_MINUTE_AMPM(DateTimeFormatter.ofPattern("hh:mm a")),
    /** Format: hhmma (e.g., 0230PM) */
    HOUR_MINUTE_AMPM_NO_SEPARATOR(DateTimeFormatter.ofPattern("hhmma")),

    // 12-hour time with periods for AM/PM
    /** Format: hh:mm a with AM/PM as periods (e.g., 02:30 P.M.) */
    HOUR_MINUTE_AMPM_DOT(DateTimeFormatter.ofPattern("hh:mm a"));

    private final DateTimeFormatter formatter;

    /**
     * Constructs a {@code TimeFormats} enum constant with the specified time formatter.
     *
     * @param formatter the {@code DateTimeFormatter} representing the time pattern.
     */
    TimeFormats(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    /**
     * Retrieves the {@code DateTimeFormatter} associated with this time format.
     *
     * @return the {@code DateTimeFormatter} for the specific time format.
     */
    public DateTimeFormatter getFormatter() {
        return formatter;
    }
}
