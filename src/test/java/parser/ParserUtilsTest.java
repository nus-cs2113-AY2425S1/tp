package parser;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static common.Utils.NULL_INTEGER;
import static common.Utils.NULL_FLOAT;


class ParserUtilsTest {

    @Test
    void testSplitArguments_ValidInput() {
        String[] result = ParserUtils.splitArguments("command argument");
        assertEquals("command", result[0]);
        assertEquals("argument", result[1]);
    }

    @Test
    void testSplitArguments_SingleArgumentEdgeCase() {
        String[] result = ParserUtils.splitArguments("singleArgument");
        assertEquals("singleArgument", result[0]);
        assertEquals("", result[1]);
    }

    @Test
    void testSplitArguments_SingleArgumentAndEmptyStringEdgeCase() {
        String[] result = ParserUtils.splitArguments("singleArgument ");
        assertEquals("singleArgument", result[0]);
        assertEquals("", result[1]);
    }

    @Test
    void testParseInteger_ValidInput() {
        int result = ParserUtils.parseInteger("123");
        assertEquals(123, result);
    }

    @Test
    void testParseInteger_LargeNumberEdgeCase() {
        int result = ParserUtils.parseInteger("2147483647"); // Integer.MAX_VALUE
        assertEquals(2147483647, result);
    }

    @Test
    void testParseInteger_NegativeEdgeCase() {
        int result = ParserUtils.parseInteger("-1");
        assertEquals(-1, result);
    }

    @Test
    void testParseInteger_NullInputInvalid() {
        int result = ParserUtils.parseInteger(null);
        assertEquals(NULL_INTEGER, result);
    }

    @Test
    void testParseInteger_NonNumericInputInvalid() {
        assertThrows(IllegalArgumentException.class, () -> ParserUtils.parseInteger("abc"));
    }

    @Test
    void testParseFloat_ValidInput() {
        float result = ParserUtils.parseFloat("123.45");
        assertEquals(123.45f, result);
    }

    @Test
    void testParseFloat_LargeFloatEdgeCase() {
        float result = ParserUtils.parseFloat("3.4028235E38"); // Float.MAX_VALUE
        assertEquals(Float.MAX_VALUE, result);
    }

    @Test
    void testParseFloat_SmallNegativeEdgeCase() {
        float result = ParserUtils.parseFloat("-0.0001");
        assertEquals(-0.0001f, result);
    }

    @Test
    void testParseFloat_NullInputInvalid() {
        float result = ParserUtils.parseFloat(null);
        assertEquals(NULL_FLOAT, result);
    }

    @Test
    void testParseFloat_NonNumericInputInvalid() {
        assertThrows(IllegalArgumentException.class, () -> ParserUtils.parseFloat("abc"));
    }

    @Test
    void testParseDate_ValidInput() {
        LocalDate result = ParserUtils.parseDate("01-01-2023");
        assertEquals(LocalDate.of(2023, 1, 1), result);
    }

    @Test
    void testParseDate_EdgeLeapYear() {
        LocalDate result = ParserUtils.parseDate("29-02-2020");
        assertEquals(LocalDate.of(2020, 2, 29), result);
    }

    @Test
    void testParseDate_EdgeEndOfYear() {
        LocalDate result = ParserUtils.parseDate("31-12-2023");
        assertEquals(LocalDate.of(2023, 12, 31), result);
    }

    @Test
    void testParseDate_InvalidDateFormat() {
        assertThrows(IllegalArgumentException.class, () -> ParserUtils.parseDate("2023-01-01"));
    }

    //Non-existent date check
}
