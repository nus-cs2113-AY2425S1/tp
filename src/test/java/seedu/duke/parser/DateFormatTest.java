package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.data.exception.DateParseException;
import seedu.duke.parser.parserutils.DateFormat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Test class for the DateFormat utility, testing date parsing and validation.
 */
public class DateFormatTest {

    private static final DateTimeFormatter STANDARD_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    private static final DateTimeFormatter STANDARD_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");

    @Test
    public void parseDateFormat_validDate() throws DateParseException {
        String dateStr = LocalDate.now().format(STANDARD_DATE_FORMAT);
        String result = DateFormat.validateAndParseToStandardFormat(dateStr);
        assertEquals(dateStr + " 23:59", result);
    }

    @Test
    public void parseDateFormat_relativeToday() throws DateParseException {
        String result = DateFormat.validateAndParseToStandardFormat("today");
        String expected = LocalDate.now().format(STANDARD_DATE_FORMAT) + " 23:59";
        assertEquals(expected, result);
    }

    @Test
    public void parseDateFormat_relativeTomorrow() throws DateParseException {
        String result = DateFormat.validateAndParseToStandardFormat("tomorrow");
        String expected = LocalDate.now().plusDays(1).format(STANDARD_DATE_FORMAT) + " 23:59";
        assertEquals(expected, result);
    }

    @Test
    public void parseDateFormat_relativeYesterday_shouldThrowException() {
        assertThrows(DateParseException.class, () ->
                DateFormat.validateAndParseToStandardFormat("yesterday")
        );
    }

    @Test
    public void parseDateFormat_validDayName() throws DateParseException {
        String result = DateFormat.validateAndParseToStandardFormat("Monday");

        LocalDate today = LocalDate.now();
        int daysUntilMonday = (DayOfWeek.MONDAY.getValue() - today.getDayOfWeek().getValue() + 7) % 7;
        LocalDate nextOrTodayMonday = today.plusDays(daysUntilMonday);

        String expected = nextOrTodayMonday.format(STANDARD_DATE_FORMAT) + " 23:59";
        assertEquals(expected, result);
    }

    @Test
    public void parseDateFormat_invalidDateFormat_shouldThrowException() {
        assertThrows(DateParseException.class, () ->
                DateFormat.validateAndParseToStandardFormat("32-Feb-2024")
        );
    }

    @Test
    public void parseDateFormat_invalidTimeFormat_shouldThrowException() {
        assertThrows(DateParseException.class, () ->
                DateFormat.validateAndParseToStandardFormat("23:99")
        );
    }

    @Test
    public void parseDateFormat_unrecognizedDateTime_shouldThrowException() {
        assertThrows(DateParseException.class, () ->
                DateFormat.validateAndParseToStandardFormat("not a date")
        );
    }
}
