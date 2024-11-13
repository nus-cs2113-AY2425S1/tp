package seedu.duke.parser.parserutils;

import java.time.format.DateTimeFormatter;

/**
 * Enum representing various date formats commonly used in parsing dates.
 * Each enum constant encapsulates a {@code DateTimeFormatter} for a specific date pattern.
 * Provides formatters for different numeric and text-based date representations,
 * supporting flexibility in date parsing.
 */
public enum DateFormats {

    // Common numeric date formats
    /** Format: d/M/yyyy (e.g., 23/10/2024) */
    DMY_SLASH(DateTimeFormatter.ofPattern("d/M/yyyy")),
    /** Format: d-M-yyyy (e.g., 23-10-2024) */
    DMY_DASH(DateTimeFormatter.ofPattern("d-M-yyyy")),
    /** Format: yyyy/M/d (e.g., 2024/10/23) */
    YMD_SLASH(DateTimeFormatter.ofPattern("yyyy/M/d")),
    /** Format: yyyy-MM-dd (e.g., 2024-10-23) */
    YMD_DASH(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
    /** Format: M/d/yyyy (e.g., 10/23/2024) */
    MDY_SLASH(DateTimeFormatter.ofPattern("M/d/yyyy")),
    /** Format: M-d-yyyy (e.g., 10-23-2024) */
    MDY_DASH(DateTimeFormatter.ofPattern("M-d-yyyy")),
    /** Format: d.M.yyyy (e.g., 23.10.2024) */
    DMY_DOT(DateTimeFormatter.ofPattern("d.M.yyyy")),
    /** Format: M.d.yyyy (e.g., 10.23.2024) */
    MDY_DOT(DateTimeFormatter.ofPattern("M.d.yyyy")),
    /** Format: yyyy.M.d (e.g., 2024.10.23) */
    YMD_DOT(DateTimeFormatter.ofPattern("yyyy.M.d")),

    // Additional variations with year first
    /** Format: yyyyMMdd (e.g., 20241023) */
    YMD_NO_SEPARATOR(DateTimeFormatter.ofPattern("yyyyMMdd")),
    /** Format: ddMMyyyy (e.g., 23102024) */
    DMY_NO_SEPARATOR(DateTimeFormatter.ofPattern("ddMMyyyy")),

    // Text-based formats
    /** Format: d MMM yyyy (e.g., 23 Oct 2024) */
    DMY_TEXT(DateTimeFormatter.ofPattern("d MMM yyyy")),
    /** Format: MMM d, yyyy (e.g., Oct 23, 2024) */
    MDY_TEXT(DateTimeFormatter.ofPattern("MMM d, yyyy")),
    /** Format: yyyy MMM d (e.g., 2024 Oct 23) */
    YMD_TEXT(DateTimeFormatter.ofPattern("yyyy MMM d")),
    /** Format: MMMM d, yyyy (e.g., October 23, 2024) */
    FULL_TEXT(DateTimeFormatter.ofPattern("MMMM d, yyyy")),
    /** Format: EEEE, MMMM d, yyyy (e.g., Wednesday, October 23, 2024) */
    FULL_TEXT_WITH_DAY(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy")),
    /** Format: d-MMM-yyyy (e.g., 23-Oct-2024) */
    SHORT_MONTH_TEXT(DateTimeFormatter.ofPattern("d-MMM-yyyy"));

    private final DateTimeFormatter formatter;

    /**
     * Constructs a {@code DateFormats} enum constant with the specified date formatter.
     *
     * @param formatter the {@code DateTimeFormatter} representing the date pattern.
     */
    DateFormats(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    /**
     * Retrieves the {@code DateTimeFormatter} associated with this date format.
     *
     * @return the {@code DateTimeFormatter} for the specific date format.
     */
    public DateTimeFormatter getFormatter() {
        return formatter;
    }
}
