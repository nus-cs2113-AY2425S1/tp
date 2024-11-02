package parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FlagParserTest {

    private FlagParser flagParser;

    @BeforeEach
    void setUp() {
        String argumentString = "/p 1 /d Day1 /t 12-12-2023 /w 2.5 /n TestExercise /s 3 /r 10";
        flagParser = new FlagParser(argumentString);
    }

    @Test
    void testHasFlag_ValidCase() {
        assertTrue(flagParser.hasFlag("/p"), "Expected flag '/p' to be present");
    }

    @Test
    void testHasFlag_MissingFlag() {
        assertFalse(flagParser.hasFlag("/m"), "Expected flag '/m' to be absent");
    }

    @Test
    void testHasFlag_EmptyFlag() {
        assertThrows(AssertionError.class, () -> flagParser.hasFlag(""), "Expected AssertionError for empty flag");
    }

    @Test
    void testValidateRequiredFlags_ValidCase() {
        assertDoesNotThrow(() -> flagParser.validateRequiredFlags("/p", "/d", "/t"), "Expected no exception for valid flags");
    }

    @Test
    void testValidateRequiredFlags_MissingFlag() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> flagParser.validateRequiredFlags("/p", "/m"),
                "Expected IllegalArgumentException for missing required flag");
        assertTrue(exception.getMessage().contains("/m"));
    }

    @Test
    void testValidateRequiredFlags_NullFlags() {
        assertThrows(AssertionError.class, () -> flagParser.validateRequiredFlags((String[]) null), "Expected AssertionError for null flags array");
    }

    @Test
    void testGetStringByFlag_ValidCase() {
        assertEquals("Day1", flagParser.getStringByFlag("/d"), "Expected value 'Day1' for flag '/d'");
    }

    @Test
    void testGetStringByFlag_FlagNotPresent() {
        assertNull(flagParser.getStringByFlag("/x"), "Expected null for non-existent flag '/x'");
    }

    @Test
    void testGetStringByFlag_EmptyFlag() {
        assertThrows(AssertionError.class, () -> flagParser.getStringByFlag(""), "Expected AssertionError for empty flag");
    }

    @Test
    void testGetIndexByFlag_ValidCase() {
        assertEquals(0, flagParser.getIndexByFlag("/p"), "Expected zero-based index '0' for flag '/p' with value '1'");
    }

    @Test
    void testGetIndexByFlag_InvalidIndex() {
        FlagParser invalidParser = new FlagParser("/p abc");
        assertThrows(IllegalArgumentException.class, () -> invalidParser.getIndexByFlag("/p"), "Expected IllegalArgumentException for invalid index");
    }

    @Test
    void testGetIntegerByFlag_ValidCase() {
        assertEquals(3, flagParser.getIntegerByFlag("/s"), "Expected integer value '3' for flag '/s'");
    }

    @Test
    void testGetIntegerByFlag_InvalidInteger() {
        FlagParser invalidParser = new FlagParser("/s abc");
        assertThrows(IllegalArgumentException.class, () -> invalidParser.getIntegerByFlag("/s"), "Expected IllegalArgumentException for invalid integer");
    }

    @Test
    void testGetFloatByFlag_ValidCase() {
        assertEquals(2.5f, flagParser.getFloatByFlag("/w"), "Expected float value '2.5' for flag '/w'");
    }

    @Test
    void testGetFloatByFlag_InvalidFloat() {
        FlagParser invalidParser = new FlagParser("/w abc");
        assertThrows(IllegalArgumentException.class, () -> invalidParser.getFloatByFlag("/w"), "Expected IllegalArgumentException for invalid float");
    }

    @Test
    void testGetDateByFlag_ValidCase() {
        assertEquals(LocalDate.of(2023, 12, 12), flagParser.getDateByFlag("/t"), "Expected date '12-12-2023' for flag '/t'");
    }

    @Test
    void testGetDateByFlag_InvalidDate() {
        FlagParser invalidParser = new FlagParser("/t 32-12-2023");
        assertThrows(IllegalArgumentException.class, () -> invalidParser.getDateByFlag("/t"), "Expected IllegalArgumentException for invalid date");
    }
}
