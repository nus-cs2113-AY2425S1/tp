package seedu.duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import seedu.duke.exception.FinanceBuddyException;

/**
 * Utility class for parsing date and time strings.
 */
public class DateParser {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Parses a string into a LocalDateTime object using the format "dd/MM/yyyy HH:mm".
     *
     * @param dateStr The date and time string to parse.
     * @return The parsed LocalDateTime object.
     * @throws FinanceBuddyException if the string is not in the expected format.
     */
    public static LocalDate parse(String dateStr) throws FinanceBuddyException {
        if (dateStr == null) {
            return LocalDate.now();  // Use current date if no date is provided
        }
        try {
            LocalDate parsedDate = LocalDate.parse(dateStr, FORMATTER);
            String reformattedDate = parsedDate.format(FORMATTER);
            if (!reformattedDate.equals(dateStr)) {
                throw new FinanceBuddyException(
                        "Invalid date: " + dateStr + ". The date does not exist."
                );
            }

            return parsedDate;
        } catch (DateTimeParseException e) {
            throw new FinanceBuddyException("Invalid date format. Please use 'dd/MM/yyyy'.");
        }
    }
}
