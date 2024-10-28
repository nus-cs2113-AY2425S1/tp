package seedu.duke.parser.parserutils;

import java.time.format.DateTimeFormatter;

public enum DateTimeFormats {

    // Common Date and 24-hour Time Combinations
    DMY_SLASH_TIME(DateTimeFormatter.ofPattern("d/M/yyyy HH:mm")),         // e.g., 23/10/2024 14:30
    DMY_DASH_TIME(DateTimeFormatter.ofPattern("d-M-yyyy HH:mm")),          // e.g., 23-10-2024 14:30
    YMD_SLASH_TIME(DateTimeFormatter.ofPattern("yyyy/M/d HH:mm")),         // e.g., 2024/10/23 14:30
    YMD_DASH_TIME(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),        // e.g., 2024-10-23 14:30
    MDY_SLASH_TIME(DateTimeFormatter.ofPattern("M/d/yyyy HH:mm")),         // e.g., 10/23/2024 14:30
    FULL_TEXT_WITH_24_HOUR_TIME(DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm")), // e.g., October 23, 2024 14:30

    // Common Date and 12-hour Time Combinations
    DMY_SLASH_TIME_AMPM(DateTimeFormatter.ofPattern("d/M/yyyy hh:mm a")),  // e.g., 23/10/2024 02:30 PM
    DMY_DASH_TIME_AMPM(DateTimeFormatter.ofPattern("d-M-yyyy hh:mm a")),   // e.g., 23-10-2024 02:30 PM
    YMD_SLASH_TIME_AMPM(DateTimeFormatter.ofPattern("yyyy/M/d hh:mm a")),  // e.g., 2024/10/23 02:30 PM
    YMD_DASH_TIME_AMPM(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a")), // e.g., 2024-10-23 02:30 PM
    MDY_SLASH_TIME_AMPM(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")),  // e.g., 10/23/2024 02:30 PM
    FULL_TEXT_WITH_12_HOUR_TIME(DateTimeFormatter.ofPattern("MMMM d, yyyy hh:mm a")), // e.g., October 23, 2024 02:30 PM

    // Full Text Date with Ordinal Day (e.g., October 23rd, 2024)
    FULL_TEXT_ORDINAL_DATE_TIME_AMPM(DateTimeFormatter.ofPattern("MMMM d'th', yyyy hh:mm a")),
    FULL_TEXT_ORDINAL_DATE_TIME_24HR(DateTimeFormatter.ofPattern("MMMM d'th', yyyy HH:mm"));

    private final DateTimeFormatter formatter;

    DateTimeFormats(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }
}
