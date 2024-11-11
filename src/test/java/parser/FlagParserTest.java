// @@author nirala-ts

package parser;

import exceptions.FlagException;
import exceptions.ParserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static parser.FlagDefinitions.MEAL_INDEX;
import static parser.FlagDefinitions.WATER_INDEX;

class FlagParserTest {

    private FlagParser flagParser;

    @BeforeEach
    void setUp() {
        String argumentString = "/p 1 /d Day1 /date 12-12-2023 /w 2.5 /n TestExercise /s 3 /r 10";
        flagParser = new FlagParser(argumentString);
    }

    @Test
    void testHasFlagValidCase() {
        assertTrue(flagParser.hasFlag("/p"));
        assertTrue(flagParser.hasFlag("/t"));
    }

    @Test
    void testHasFlagMissingFlag() {
        assertFalse(flagParser.hasFlag("/m"));
    }

    @Test
    void testHasFlagEmptyFlag() {
        assertThrows(AssertionError.class, () -> flagParser.hasFlag(""),
                "Expected AssertionError for empty flag");
    }

    @Test
    void testValidateRequiredFlagsValidCase() {
        assertDoesNotThrow(() -> flagParser.validateRequiredFlags("/p", "/d", "/t"),
                "Expected no exception for valid flags");
    }

    @Test
    void testValidateRequiredFlagsMissingFlag() {
        FlagException exception = assertThrows(FlagException.class,
                () -> flagParser.validateRequiredFlags("/p", MEAL_INDEX),
                "Expected MissingFlagBuffBuddyException for missing required flag");
        assertTrue(exception.getMessage().contains(MEAL_INDEX));
    }

    @Test
    void testGetStringByFlagValidCase() {
        assertEquals("Day1", flagParser.getStringByFlag("/d"),
                "Expected value 'Day1' for flag '/d'");
    }

    @Test
    void testGetStringByFlagFlagNotPresent() {
        assertNull(flagParser.getStringByFlag("/x"),
                "Expected null for non-existent flag '/x'");
    }

    @Test
    void testGetStringByFlagEmptyFlag() {
        assertThrows(AssertionError.class, () -> flagParser.getStringByFlag(""),
                "Expected AssertionError for empty flag");
    }

    @Test
    void testGetIndexByFlagValidCase() {
        assertEquals(0, flagParser.getIndexByFlag("/p"),
                "Expected zero-based index '0' for flag '/p' with value '1'");
    }

    @Test
    void testGetIndexByFlagInvalidIndex() {
        FlagParser invalidParser = new FlagParser("/p abc");
        assertThrows(ParserException.class, () -> invalidParser.getIndexByFlag("/p"),
                "Expected InvalidFormatBuffBuddyException for invalid index");
    }

    @Test
    void testGetIntegerByFlagValidCase() {
        assertEquals(3, flagParser.getIntegerByFlag("/s"),
                "Expected integer value '3' for flag '/s'");
    }

    @Test
    void testGetIntegerByFlagInvalidInteger() {
        FlagParser invalidParser = new FlagParser("/s abc");
        assertThrows(ParserException.class, () -> invalidParser.getIntegerByFlag("/s"),
                "Expected InvalidFormatBuffBuddyException for invalid integer");
    }

    @Test
    void testGetFloatByFlagValidCase() {
        assertEquals(2.5f, flagParser.getFloatByFlag("/w"),
                "Expected float value '2.5' for flag '/w'");
    }

    @Test
    void testGetFloatByFlagInvalidFloat() {
        FlagParser invalidParser = new FlagParser("/w abc");
        assertThrows(ParserException.class, () -> invalidParser.getFloatByFlag(WATER_INDEX),
                "Expected InvalidFormatBuffBuddyException for invalid float");
    }

    @Test
    void testGetDateByFlagValidCase() {
        assertEquals(LocalDate.of(2023, 12, 12), flagParser.getDateByFlag("/t"),
                "Expected date '12-12-2023' for flag '/t'");
    }

    @Test
    void testGetDateByFlagInvalidDate() {
        FlagParser invalidParser = new FlagParser("/t 32-12-2023");
        assertThrows(ParserException.class, () -> invalidParser.getDateByFlag("/t"),
                "Expected InvalidFormatBuffBuddyException for invalid date");
    }

    @Test
    void testParseNullArgumentString() {
        assertThrows(FlagException.class, () -> new FlagParser(null),
                "Expected EmptyInputBuffBuddyException for null argument string");
    }
}
