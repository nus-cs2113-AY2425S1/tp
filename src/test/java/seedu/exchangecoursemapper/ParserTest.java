package seedu.exchangecoursemapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.exchangecoursemapper.parser.Parser;
import seedu.exchangecoursemapper.storage.Storage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.exchangecoursemapper.constants.Commands.COMMANDS;
import static seedu.exchangecoursemapper.constants.Commands.FILTER_COURSES;
import static seedu.exchangecoursemapper.constants.Commands.SET;
import static seedu.exchangecoursemapper.constants.Commands.ADD_COURSES;
import static seedu.exchangecoursemapper.constants.Commands.DELETE_COURSES;
import static seedu.exchangecoursemapper.constants.Commands.BYE;
import static seedu.exchangecoursemapper.constants.Commands.OBTAIN;
import static seedu.exchangecoursemapper.constants.Commands.LISTING_SCHOOLS;
import static seedu.exchangecoursemapper.constants.Commands.HELP;
import static seedu.exchangecoursemapper.constants.Commands.LIST_MAPPED;
import static seedu.exchangecoursemapper.constants.Commands.COMPARE_PU;
import static seedu.exchangecoursemapper.constants.Commands.FIND;

class ParserTest {

    private Parser parser;
    private Storage tempStorage;
    
    @BeforeEach
    void setUp() {
        parser = new Parser();
        tempStorage = new Storage();
    }

    @Test
    void testGetUserInput() {
        String simulatedInput = "Test Input for scanner";
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        parser = new Parser();

        String result = parser.getUserInput();

        assertEquals(simulatedInput, result);

        System.setIn(System.in);
    }

    @Test
    void testProcessUserInput_listSchoolCommand() {
        assertDoesNotThrow(() -> parser.processUserInput(LISTING_SCHOOLS, tempStorage));
    }

    @Test
    void testProcessUserInput_listCommandsCommand() {
        assertDoesNotThrow(() -> parser.processUserInput(COMMANDS, tempStorage));
    }

    @Test
    void testProcessUserInput_filterCoursesCommand() {
        assertDoesNotThrow(() -> parser.processUserInput(FILTER_COURSES, tempStorage));
    }

    @Test
    void testProcessUserInput_addCoursesCommand() {
        assertDoesNotThrow(() -> parser.processUserInput(ADD_COURSES, tempStorage));
    }

    @Test
    void testProcessUserInput_deleteCoursesCommand() {
        assertDoesNotThrow(() -> parser.processUserInput(DELETE_COURSES, tempStorage));
    }

    @Test
    void testProcessUserInput_helpCommand() {
        assertDoesNotThrow(() -> parser.processUserInput(HELP, tempStorage));
    }

    @Test
    void testProcessUserInput_obtainContactsCommand() {
        assertDoesNotThrow(() -> parser.processUserInput(OBTAIN, tempStorage));
    }

    @Test
    void testProcessUserInput_listMappedCommand() {
        assertDoesNotThrow(() -> parser.processUserInput(LIST_MAPPED, tempStorage));
    }

    @Test
    void testProcessUserInput_findCoursesCommand() {
        assertDoesNotThrow(() -> parser.processUserInput(FIND, tempStorage));
    }

    @Test
    void testProcessUserInput_setCoursesCommand() {
        assertDoesNotThrow(() -> parser.processUserInput(SET, tempStorage));
    }

    @Test
    void testProcessUserInput_compareMappedCommand() {
        assertDoesNotThrow(() -> parser.processUserInput(COMPARE_PU, tempStorage));
    }

    @Test
    void testProcessUserInput_exitMessage() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        parser.processUserInput(BYE, tempStorage);

        String expectedMessage = "All the best in planning for your exchange, hope we helped!";

        assertTrue(outputStreamCaptor.toString().trim().contains(expectedMessage));
    }

    @Test
    void testProcessUserInput_nullInput_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> parser.processUserInput(null, tempStorage));
    }

    @Test
    void testProcessUserInput_emptyStringInput() {
        assertDoesNotThrow(() -> parser.processUserInput("", tempStorage));
    }


    @Test
    void testParsePUAbbreviations_validAbbreviation() {
        String abbreviation = "uwa";
        String expected = "the university of western australia";
        String result = parser.parsePUAbbreviations(abbreviation);
        assertEquals(expected, result);

        abbreviation = "unimelb";
        expected = "the university of melbourne";
        result = parser.parsePUAbbreviations(abbreviation);
        assertEquals(expected, result);

        abbreviation = "anu";
        expected = "the australian national university";
        result = parser.parsePUAbbreviations(abbreviation);
        assertEquals(expected, result);

        abbreviation = "wgtn";
        expected = "victoria university of wellington";
        result = parser.parsePUAbbreviations(abbreviation);
        assertEquals(expected, result);
    }

    @Test
    void testParsePUAbbreviations_invalidAbbreviation() {
        String abbreviation = "xyz";
        String expected = "xyz";
        String result = parser.parsePUAbbreviations(abbreviation);
        assertEquals(expected, result);
    }

    @Test
    void testParsePUAbbreviations_caseInsensitive() {
        String abbreviation = "UWA";
        String expected = "the university of western australia";
        String result = parser.parsePUAbbreviations(abbreviation);
        assertEquals(expected, result);

        abbreviation = "Unimelb";
        expected = "the university of melbourne";
        result = parser.parsePUAbbreviations(abbreviation);
        assertEquals(expected, result);
    }

    @Test
    void testParsePUAbbreviations_blankInput() {
        String abbreviation = "";
        String expected = "";
        String result = parser.parsePUAbbreviations(abbreviation);
        assertEquals(expected, result);
    }
}
