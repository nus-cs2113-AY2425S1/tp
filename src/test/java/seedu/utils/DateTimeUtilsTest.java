package seedu.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class DateTimeUtilsTest {

    @Test
    void parseDateTime_validDateWithoutTime_successParseWithTimeAs2359() {
        // Expected datetime
        LocalDateTime expectedDateTime =
                LocalDateTime.of(2024, 10, 23, 23, 59);

        // Actual parsed datetime
        LocalDateTime dateTime = DateTimeUtils.parseDateTime("2024-10-23");

        assertEquals(expectedDateTime, dateTime);
    }

    @Test
    void parseDateTime_validDateWithTime_successParse() {
        // Expected datetime
        LocalDateTime expectedDateTime =
                LocalDateTime.of(2024, 10, 23, 10, 11);

        // Actual parsed datetime
        LocalDateTime dateTime = DateTimeUtils.parseDateTime("2024-10-23 1011");

        assertEquals(expectedDateTime, dateTime);
    }

    @Test
    public void parseDateTime_invalidDateTimeFormat_exceptionThrown() {
        try {
            DateTimeUtils.parseDateTime("2024/10/23 1011");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals(DateTimeUtils.MESSAGE_INVALID_DATE_FORMAT, e.getMessage());
        }
    }

    @Test
    public void parseDateTime_invalidDateTimeContent_exceptionThrown() {
        try {
            DateTimeUtils.parseDateTime("2024-12-32 1011");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals(DateTimeUtils.MESSAGE_INVALID_DATE_FORMAT, e.getMessage());
        }
    }

    @Test
    void getDateTimeString_validDateTime_validString() {
        // Input datetime
        LocalDateTime inputDateTime =
                LocalDateTime.of(2024, 10, 12, 16, 11);

        // Expected string
        String expectedDateTimeString = "Saturday, 2024-10-12 04.11 PM";

        // Actual string
        String actualDateTimeString = DateTimeUtils.getDateTimeString(inputDateTime);

        assertEquals(expectedDateTimeString, actualDateTimeString);
    }
}
