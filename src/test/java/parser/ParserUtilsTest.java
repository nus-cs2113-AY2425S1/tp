package parser;

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
    void testSplitArguments_ValidInput() {
        String input = "command arg1 arg2";
        String[] result = ParserUtils.splitArguments(input);
        assertArrayEquals(new String[]{"command", "arg1 arg2"}, result,
                "Should split command and arguments correctly.");
    }

    @Test
    void testSplitArguments_EmptyArgumentString() {
        String input = "command";
        String[] result = ParserUtils.splitArguments(input);
        assertArrayEquals(new String[]{"command", ""}, result, "Should handle missing arguments.");
    }

    // Tests for trimInput
    @Test
    void testTrimInput_ValidInput() {
        String input = "  trim this  ";
        String result = ParserUtils.trimInput(input);
        assertEquals("trim this", result, "Should trim leading and trailing whitespace.");
    }

    @Test
    void testTrimInput_EmptyString() {
        assertThrows(IllegalArgumentException.class, () -> ParserUtils.trimInput(" "),
                "Should throw exception on empty input.");
    }

    // Tests for parseInteger
    @Test
    void testParseInteger_ValidInteger() {
        String input = "42";
        int result = ParserUtils.parseInteger(input);
        assertEquals(42, result, "Should parse valid integer correctly.");
    }


    @Test
    void testParseInteger_LargeNumberEdgeCase() {
        String input = "2147483647";
        int result = ParserUtils.parseInteger(input); // Integer.MAX_VALUE
        assertEquals(2147483647, result, "Should parse valid integer correctly.");
    }

    @Test
    void testParseInteger_NegativeEdgeCase() {
        String input = "-1";
        int result = ParserUtils.parseInteger(input);
        assertEquals(-1, result, "Should throw exception on invalid integer.");
    }

    @Test
    void testParseInteger_NullInputInvalid() {
        int result = ParserUtils.parseInteger(null);
        assertEquals(NULL_INTEGER, result, "Should return default NULL_INTEGER when input is null.");
    }

    @Test
    void testParseInteger_NonNumericInputInvalid() {
        assertThrows(IllegalArgumentException.class, () -> ParserUtils.parseInteger("abc"),
                "Should throw exception on invalid integer.");
    }

    // Tests for parseFloat
    @Test
    void testParseFloat_ValidFloat() {
        String input = "3.14";
        float result = ParserUtils.parseFloat(input);
        assertEquals(3.14f, result, 0.001, "Should parse valid float correctly.");
    }

    @Test
    void testParseFloat_LargeFloatEdgeCase() {
        String input = "3.4028235E38";
        float result = ParserUtils.parseFloat(input); // Float.MAX_VALUE
        assertEquals(Float.MAX_VALUE, result, 0.001, "Should parse valid float correctly.");
    }

    @Test
    void testParseFloat_SmallNegativeEdgeCase() {
        String input = "-0.0001";
        float result = ParserUtils.parseFloat(input);
        assertEquals(-0.0001f, result, 0.001, "Should parse valid float correctly.");
    }

    @Test
    void testParseFloat_NullInputInvalid() {
        float result = ParserUtils.parseFloat(null);
        assertEquals(NULL_FLOAT, result, "Should return default NULL_FLOAT when input is null.");
    }

    @Test
    void testParseFloat_NonNumericInputInvalid() {
        assertThrows(IllegalArgumentException.class, () -> ParserUtils.parseFloat("abc"),
                "Should throw exception on invalid float.");
    }

    //Tests for parseIndex
    @Test
    void testParseIndex_ValidIndex() {
        String input = "3";
        int result = ParserUtils.parseIndex(input);
        assertEquals(2, result, "Should parse and convert valid index to zero-based.");
    }

    @Test
    void testParseIndex_LargeIndexEdgeCase() {
        String input = "2147483647"; // Integer.MAX_VALUE
        int result = ParserUtils.parseIndex(input);
        assertEquals(2147483646, result, "Should handle large index correctly and convert to zero-based.");
    }

    @Test
    void testParseIndex_ZeroIndexEdgeCase() {
        String input = "1";
        int result = ParserUtils.parseIndex(input);
        assertEquals(0, result, "Should handle zero-based conversion for index 1.");
    }

    @Test
    void testParseIndex_NegativeIndexInvalid() {
        assertThrows(IllegalArgumentException.class, () -> ParserUtils.parseIndex("-1"),
                "Should throw exception on negative index.");
    }

    @Test
    void testParseIndex_NullInputInvalid() {
        int result = ParserUtils.parseIndex(null);
        assertEquals(NULL_INTEGER, result, "Should return default NULL_INTEGER when input is null.");
    }

    @Test
    void testParseIndex_NonNumericInputInvalid() {
        assertThrows(IllegalArgumentException.class, () -> ParserUtils.parseIndex("abc"),
                "Should throw exception on non-numeric index.");
    }

    //Tests for parseDate
    @Test
    void testParseDate_ValidDate() {
        String dateString = "15-08-2023"; // assuming DATE_FORMAT = "dd-MM-yyyy"
        LocalDate expectedDate = LocalDate.of(2023, 8, 15);
        LocalDate actualDate = ParserUtils.parseDate(dateString);

        assertEquals(expectedDate, actualDate, "The parsed date should match the expected date.");
    }

    @Test
    void testParseDate_CurrentDateWhenNullInput() {
        LocalDate actualDate = ParserUtils.parseDate(null);
        LocalDate expectedDate = LocalDate.now();

        assertEquals(expectedDate, actualDate, "When the input is null, the parsed date should be today's date.");
    }

    @Test
    void testParseDate_TrimmedInput() {
        String dateString = " 15-08-2023 "; // Input with extra spaces
        LocalDate expectedDate = LocalDate.of(2023, 8, 15);
        LocalDate actualDate = ParserUtils.parseDate(dateString);

        assertEquals(expectedDate, actualDate, "The parsed date should match the expected date, ignoring extra spaces.");
    }

    @Test
    void testParseDate_ValidLeapYearDate() {
        String dateString = "29-02-2024"; // Leap year date
        LocalDate expectedDate = LocalDate.of(2024, 2, 29);
        LocalDate actualDate = ParserUtils.parseDate(dateString);

        assertEquals(expectedDate, actualDate, "The parsed date should match the expected leap year date.");
    }
}
