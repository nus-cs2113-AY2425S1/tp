package seedu.exchangecoursemapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.exchangecoursemapper.command.AddCoursesCommand;
import seedu.exchangecoursemapper.command.DeleteCoursesCommand;
import seedu.exchangecoursemapper.command.FindCoursesCommand;
import seedu.exchangecoursemapper.storage.Storage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.exchangecoursemapper.constants.Messages.LINE_SEPARATOR;

public class FindCoursesCommandTest {

    private FindCoursesCommand findCoursesCommand;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private Storage storage;


    @BeforeEach
    void setUp() {
        outputStreamCaptor.reset();
        System.setOut(new PrintStream(outputStreamCaptor));
        System.setErr(new PrintStream(outputStreamCaptor));

        //off all logs
        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(Level.OFF);

        storage = new Storage();
        findCoursesCommand = new FindCoursesCommand(storage);
        AddCoursesCommand addCoursesCommand = new AddCoursesCommand();
        addCoursesCommand.execute("add cs2102 /pu the university of melbourne /coursepu info20003", storage);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(System.out);
        DeleteCoursesCommand deleteCoursesCommand = new DeleteCoursesCommand();
        deleteCoursesCommand.execute("delete 1", storage);
    }

    @Test
    public void getKeyword_withValidInput_expectParseKeyword() {
        String userInput = "find cs2102";
        String output = findCoursesCommand.getKeyword(userInput);
        assertEquals("cs2102", output);
    }

    @Test
    public void getKeyword_withNullInput_expectException() {
        String userInput = "find";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> findCoursesCommand.getKeyword(userInput));
        assertEquals("Keyword to search for is empty.", exception.getMessage());
    }

    @Test
    public void findCommand_validKeyword_expectPrintFound() {
        String keyword = "cs2102";
        findCoursesCommand.findCommand(keyword);

        String expectedOutput = """
                cs2102 | the university of melbourne | info20003
                -----------------------------------------------------
                """;

        String actualOutput = normalizeLineEndings(outputStreamCaptor.toString());
        String normalizeExpectedOutput = normalizeLineEndings(expectedOutput);
        assertTrue(actualOutput.contains(normalizeExpectedOutput));
    }

    @Test
    public void findCommand_nonMatchingKeys_expectException() {
        String keyword = "cs2113";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> findCoursesCommand.findCommand(keyword));
        assertEquals("No match found.", exception.getMessage());
    }

    @Test
    public void execute_validUserInput_expectPrintFound() {
        String keyword = "find cs2102";
        findCoursesCommand.execute(keyword, storage);

        String expectedOutput = """
                cs2102 | the university of melbourne | info20003
                -----------------------------------------------------
                """;

        String actualOutput = normalizeLineEndings(outputStreamCaptor.toString());
        String normalizeExpectedOutput = normalizeLineEndings(expectedOutput);
        assertTrue(actualOutput.contains(normalizeExpectedOutput));
    }

    @Test
    public void execute_nonMatchingKeys_expectMessage() {
        String keyword = "find invalid";
        findCoursesCommand.execute(keyword, storage);
        String expectedOutput = "No match found.\n" + LINE_SEPARATOR;
        String actualOutput = normalizeLineEndings(outputStreamCaptor.toString()).trim();
        String normalizeExpectedOutput = normalizeLineEndings(expectedOutput);
        assertTrue(actualOutput.contains(normalizeExpectedOutput));
    }

    String normalizeLineEndings(String input) {
        return input.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n").trim();
    }
}
