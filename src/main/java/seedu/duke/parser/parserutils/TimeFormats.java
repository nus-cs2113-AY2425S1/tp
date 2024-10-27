package seedu.duke.parser.parserutils;

import java.time.format.DateTimeFormatter;

public enum TimeFormats {

    // 24-hour time formats
    HOUR_MINUTE(DateTimeFormatter.ofPattern("HH:mm")),                  // e.g., 14:30
    HOUR_MINUTE_NO_SEPARATOR(DateTimeFormatter.ofPattern("HHmm")),      // e.g., 1430

    // 12-hour time formats with AM/PM
    HOUR_MINUTE_AMPM(DateTimeFormatter.ofPattern("hh:mm a")),           // e.g., 02:30 PM
    HOUR_MINUTE_AMPM_NO_SEPARATOR(DateTimeFormatter.ofPattern("hhmma")), // e.g., 0230PM

    // 12-hour time with periods for AM/PM
    HOUR_MINUTE_AMPM_DOT(DateTimeFormatter.ofPattern("hh:mm a"));       // e.g., 02:30 P.M.

    private final DateTimeFormatter formatter;

    TimeFormats(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }
}
