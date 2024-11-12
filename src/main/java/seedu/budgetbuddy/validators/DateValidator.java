package seedu.budgetbuddy.validators;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Validates and converts the date given.
 */
public class DateValidator {
    /**
     * Validates the date given.
     *
     * @param part The part containing the date.
     * @return The parsed date or null if invalid.
     */
    public static LocalDate validateDate(String part) {
        try {
            return LocalDate.parse(part.substring(2),
                    DateTimeFormatter.ofPattern("d/M/uuuu").withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException e) {
            return null;  // Indicates invalid date
        }
    }

    /**
     * Parses the date from the command part.
     *
     * @param part The command part containing the date.
     * @return The parsed YearMonth or null if invalid.
     */
    public static YearMonth validateYearMonth(String part) {
        try {
            return YearMonth.parse(part.substring(2), DateTimeFormatter.ofPattern("M/yyyy"));
        } catch (DateTimeParseException e) {
            return null;  // Indicates invalid date
        }
    }
}
