// @@author nirala-ts

package parser;

import exceptions.ParserException;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static common.Utils.NULL_INTEGER;
import static common.Utils.NULL_FLOAT;

class ParserUtilsTest {

    // Tests for splitArguments
    @Test
    void testSplitArgumentsValidInput() {
        String input = "command arg1 arg2";
        String[] result = ParserUtils.splitArguments(input);
        assertArrayEquals(new String[]{"command", "arg1 arg2"}, result,
                "Should split command and arguments correctly.");
    }

    @Test
    void testSplitArgumentsEmptyArgumentString() {
        String input = "command";
        String[] result = ParserUtils.splitArguments(input);
        assertArrayEquals(new String[]{"command", ""}, result, "Should handle missing arguments.");
    }

    // Tests for trimInput
    @Test
    void testTrimInputValidInput() {
        String input = "  trim this  ";
        String result = ParserUtils.trimInput(input);
        assertEquals("trim this", result, "Should trim leading and trailing whitespace.");
    }

    @Test
    void testTrimInputEmptyString() {
        assertThrows(ParserException.class, () -> ParserUtils.trimInput(" "),
                "Should throw PaserExceptions on empty input.");
    }

    // Tests for parseInteger
    @Test
    void testParseIntegerValidInteger() {
        String input = "42";
        int result = ParserUtils.parseInteger(input);
        assertEquals(42, result, "Should parse valid integer correctly.");
    }

    @Test
    void testParseIntegerLargeNumberEdgeCase() {
        String input = "2147483647";
        int result = ParserUtils.parseInteger(input); // Integer.MAX_VALUE
        assertEquals(2147483647, result, "Should parse valid integer correctly.");
    }

    @Test
    void testParseIntegerNegativeEdgeCase() {
        String input = "-1";
        assertThrows(ParserException.class, () -> ParserUtils.parseInteger(input));
    }

    @Test
    void testParseIntegerNullInputInvalid() {
        int result = ParserUtils.parseInteger(null);
        assertEquals(NULL_INTEGER, result, "Should return default NULL_INTEGER when input is null.");
    }

    @Test
    void testParseIntegerNonNumericInputInvalid() {
        assertThrows(ParserException.class, () -> ParserUtils.parseInteger("abc"),
                "Should throw PaserExceptions on invalid integer.");
    }

    // Tests for parseFloat
    @Test
    void testParseFloatValidFloat() {
        String input = "3.14";
        float result = ParserUtils.parseFloat(input);
        assertEquals(3.14f, result, 0.001, "Should parse valid float correctly.");
    }

    @Test
    void testParseFloatLargeFloatEdgeCase() {
        String input = "3.4028235E38";
        float result = ParserUtils.parseFloat(input); // Float.MAX_VALUE
        assertEquals(Float.MAX_VALUE, result, 0.001, "Should parse valid float correctly.");
    }

    @Test
    void testParseFloatSmallNegativeEdgeCase() {
        String input = "-0.0001";
        assertThrows(ParserException.class, () -> ParserUtils.parseFloat(input));
    }

    @Test
    void testParseFloatNullInputInvalid() {
        float result = ParserUtils.parseFloat(null);
        assertEquals(NULL_FLOAT, result, "Should return default NULL_FLOAT when input is null.");
    }

    @Test
    void testParseFloatNonNumericInputInvalid() {
        assertThrows(ParserException.class, () -> ParserUtils.parseFloat("abc"),
                "Should throw PaserExceptions on invalid float.");
    }

    // Tests for parseIndex
    @Test
    void testParseIndexValidIndex() {
        String input = "3";
        int result = ParserUtils.parseIndex(input);
        assertEquals(2, result, "Should parse and convert valid index to zero-based.");
    }

    @Test
    void testParseIndexLargeIndexEdgeCase() {
        String input = "2147483647"; // Integer.MAX_VALUE
        int result = ParserUtils.parseIndex(input);
        assertEquals(2147483646, result, "Should handle large index correctly and convert to zero-based.");
    }

    @Test
    void testParseIndexZeroIndexEdgeCase() {
        String input = "1";
        int result = ParserUtils.parseIndex(input);
        assertEquals(0, result, "Should handle zero-based conversion for index 1.");
    }

    @Test
    void testParseIndexNegativeIndexInvalid() {
        assertThrows(ParserException.class, () -> ParserUtils.parseIndex("-1"),
                "Should throw PaserExceptions on negative index.");
    }

    @Test
    void testParseIndexNullInputInvalid() {
        int result = ParserUtils.parseIndex(null);
        assertEquals(NULL_INTEGER, result, "Should return default NULL_INTEGER when input is null.");
    }

    @Test
    void testParseIndexNonNumericInputInvalid() {
        assertThrows(ParserException.class, () -> ParserUtils.parseIndex("abc"),
                "Should throw PaserExceptions on non-numeric index.");
    }

    // Tests for parseDate
    @Test
    void testParseDateValidDate() {
        String dateString = "15-08-2023"; // assuming DATE_FORMAT = "dd-MM-yyyy"
        LocalDate expectedDate = LocalDate.of(2023, 8, 15);
        LocalDate actualDate = ParserUtils.parseDate(dateString);

        assertEquals(expectedDate, actualDate, "The parsed date should match the expected date.");
    }

    @Test
    void testParseDateCurrentDateWhenNullInput() {
        LocalDate actualDate = ParserUtils.parseDate(null);
        LocalDate expectedDate = LocalDate.now();

        assertEquals(expectedDate, actualDate, "When the input is null, the parsed date should be today's date.");
    }

    @Test
    void testParseDateTrimmedInput() {
        String dateString = " 15-08-2023 "; // Input with extra spaces
        LocalDate expectedDate = LocalDate.of(2023, 8, 15);
        LocalDate actualDate = ParserUtils.parseDate(dateString);

        assertEquals(expectedDate, actualDate,
                "The parsed date should match the expected date, ignoring extra spaces.");
    }

    @Test
    void testParseDateValidLeapYearDate() {
        String dateString = "29-02-2024"; // Leap year date
        LocalDate expectedDate = LocalDate.of(2024, 2, 29);
        LocalDate actualDate = ParserUtils.parseDate(dateString);

        assertEquals(expectedDate, actualDate, "The parsed date should match the expected leap year date.");
    }

    @Test
    void testParseDateInvalidDayInMonth() {
        assertThrows(ParserException.class, () -> ParserUtils.parseDate("31-02-2023"),
                "Should throw exception on invalid date (February 31st).");
    }

    @Test
    void testParseDateInvalidMonth() {
        assertThrows(ParserException.class, () -> ParserUtils.parseDate("31-13-2023"),
                "Should throw exception on invalid month (13).");
    }
}

