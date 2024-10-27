package seedu.duke.command;


import org.junit.jupiter.api.Test;
import seedu.duke.exception.FinanceBuddyException;
import seedu.duke.parser.DateParser;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for DateParser.
 * This class contains unit tests to verify the functionality of the DateParser,
 * including parsing valid dates, handling empty dates, and throwing exceptions for invalid formats.
 */
class DateParserTest {

    /**
     * Test parsing a valid date string.
     * Verifies that the date is parsed correctly into a LocalDate object.
     */
    @Test
    void parse_validDate_expectParsedCorrectly() throws FinanceBuddyException {
        String dateStr = "14/10/24";
        LocalDate parsedDate = DateParser.parse(dateStr);

        LocalDate expectedDate = LocalDate.of(2024, 10, 14);
        assertEquals(expectedDate, parsedDate);
    }

    /**
     * Test parsing an empty date string.
     * Verifies that a FinanceBuddyException is thrown when an empty date string is provided.
     */
    @Test
    void parse_emptyDate_expectException() {
        String emptyDate = "";
        Exception exception = assertThrows(FinanceBuddyException.class, () -> {
            DateParser.parse(emptyDate);
        });

        assertEquals("Invalid date format. Please use 'dd/MM/yy'.", exception.getMessage());
    }

    /**
     * Test parsing a null date string.
     * Verifies that the current system date is returned when a null date is provided.
     */
    @Test
    void parse_nullDate_expectCurrentDate() throws FinanceBuddyException {
        LocalDate parsedDate = DateParser.parse(null);

        LocalDate currentDate = LocalDate.now();
        assertEquals(currentDate, parsedDate);
    }

    /**
     * Test parsing an invalid date string.
     * Verifies that a FinanceBuddyException is thrown when an invalid date format is provided.
     */
    @Test
    void parse_invalidDate_expectException() {
        String invalidDate = "2024-10-14";
        Exception exception = assertThrows(FinanceBuddyException.class, () -> {
            DateParser.parse(invalidDate);
        });

        assertEquals("Invalid date format. Please use 'dd/MM/yy'.", exception.getMessage());
    }

    /**
     * Test parsing another invalid date string.
     * Verifies that a FinanceBuddyException is thrown when a completely invalid string is provided.
     */
    @Test
    void parse_invalidDateString_expectException() {
        String invalidDate = "invalid/date/24";
        Exception exception = assertThrows(FinanceBuddyException.class, () -> {
            DateParser.parse(invalidDate);
        });

        assertEquals("Invalid date format. Please use 'dd/MM/yy'.", exception.getMessage());
    }
}
