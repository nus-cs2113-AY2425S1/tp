package seedu.duke.parser.parserutils;

import java.time.format.DateTimeFormatter;

public enum DateFormats {

    // Common numeric date formats
    DMY_SLASH(DateTimeFormatter.ofPattern("d/M/yyyy")),
    DMY_DASH(DateTimeFormatter.ofPattern("d-M-yyyy")),
    YMD_SLASH(DateTimeFormatter.ofPattern("yyyy/M/d")),
    YMD_DASH(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
    MDY_SLASH(DateTimeFormatter.ofPattern("M/d/yyyy")),
    MDY_DASH(DateTimeFormatter.ofPattern("M-d-yyyy")),
    DMY_DOT(DateTimeFormatter.ofPattern("d.M.yyyy")),
    MDY_DOT(DateTimeFormatter.ofPattern("M.d.yyyy")),
    YMD_DOT(DateTimeFormatter.ofPattern("yyyy.M.d")),

    // Additional variations with year first
    YMD_NO_SEPARATOR(DateTimeFormatter.ofPattern("yyyyMMdd")),
    DMY_NO_SEPARATOR(DateTimeFormatter.ofPattern("ddMMyyyy")),

    // Text-based formats
    DMY_TEXT(DateTimeFormatter.ofPattern("d MMM yyyy")),
    MDY_TEXT(DateTimeFormatter.ofPattern("MMM d, yyyy")),
    YMD_TEXT(DateTimeFormatter.ofPattern("yyyy MMM d")),
    FULL_TEXT(DateTimeFormatter.ofPattern("MMMM d, yyyy")),
    FULL_TEXT_WITH_DAY(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy")),
    SHORT_MONTH_TEXT(DateTimeFormatter.ofPattern("d-MMM-yyyy"));  // e.g., 23-Oct-2024

    private final DateTimeFormatter formatter;

    DateFormats(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }
}
