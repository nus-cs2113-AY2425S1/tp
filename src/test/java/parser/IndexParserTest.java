//package parser;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//public class IndexParserTest {
//
//    @Test
//    public void testParseIndex_validInput() {
//        int result = IndexParser.parseIndex("5");
//
//        assertEquals(4, result, "Index should be parsed as 4 for input '5'");
//    }
//
//    @Test
//    public void testParseIndex_inputWithSpaces() {
//        int result = IndexParser.parseIndex("  3  ");
//
//        assertEquals(2, result, "Index should be parsed as 2 for input '  3  '");
//    }
//
//    @Test
//    public void testParseIndex_invalidNumberFormat() {
//        Exception exception = assertThrows(IllegalArgumentException.class, () ->
//                IndexParser.parseIndex("abc")
//        );
//
//        assertEquals("Invalid index. Please provide a valid number.", exception.getMessage());
//    }
//
//    @Test
//    public void testParseIndex_negativeNumber() {
//        Exception exception = assertThrows(IllegalArgumentException.class, () ->
//                IndexParser.parseIndex("-1")
//        );
//
//        assertEquals("Index must be a positive number.", exception.getMessage());
//    }
//
//    @Test
//    public void testParseIndex_zeroAsInput() {
//        Exception exception = assertThrows(IllegalArgumentException.class, () ->
//                IndexParser.parseIndex("0")
//        );
//
//        assertEquals("Index must be a positive number.", exception.getMessage());
//    }
//
//    @Test
//    public void testParseIndex_emptyString() {
//        Exception exception = assertThrows(IllegalArgumentException.class, () ->
//                IndexParser.parseIndex("")
//        );
//
//        assertEquals("Index was not provided.", exception.getMessage());
//    }
//}
