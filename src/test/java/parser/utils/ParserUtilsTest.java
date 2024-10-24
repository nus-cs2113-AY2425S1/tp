package parser.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static parser.ParserUtils.parseIndex;

public class ParserUtilsTest {

    @Test
    public void testParseIndex_validInput() {
        int result = parseIndex("5");

        assertEquals(4, result, "Index should be parsed as 4 for input '5'");
    }

    @Test
    public void testParseIndex_inputWithSpaces() {
        int result = parseIndex("  3  ");

        assertEquals(2, result, "Index should be parsed as 2 for input '  3  '");
    }

    @Test
    public void testParseIndex_invalidNumberFormat() {
        assertThrows(IllegalArgumentException.class, () ->
                parseIndex("abc")
        );
    }

    @Test
    public void testParseIndex_negativeNumber() {
        assertThrows(IllegalArgumentException.class, () ->
                parseIndex("-1")
        );
    }

    @Test
    public void testParseIndex_zeroAsInput() {
        assertThrows(IllegalArgumentException.class, () ->
                parseIndex("0")
        );
    }

    @Test
    public void testParseIndex_emptyString() {
        assertThrows(IllegalArgumentException.class, () ->
                parseIndex("")
        );
    }
}
