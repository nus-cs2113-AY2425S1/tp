package seedu.exchangecoursemapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.exchangecoursemapper.command.AddCoursesCommand;
import seedu.exchangecoursemapper.command.DeleteCoursesCommand;
import seedu.exchangecoursemapper.storage.Storage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteCoursesCommandTest {
    private AddCoursesCommand addCoursesCommand;
    private DeleteCoursesCommand deleteCoursesCommand;
    private Storage storage;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        deleteCoursesCommand = new DeleteCoursesCommand();
        addCoursesCommand = new AddCoursesCommand();
        storage = new Storage();
        addCoursesCommand.execute("add cs2102 /pu the university of melbourne /coursepu info20003", storage);
        // Clear the output capture after add command
        outputStreamCaptor.reset();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(System.out);
        DeleteCoursesCommand deleteCoursesCommand = new DeleteCoursesCommand();
        deleteCoursesCommand.execute("delete 1", storage);
    }

    @Test
    public void parseDeleteCommand_inputWithOneIndex_expectSeparatedInput() {
        String userInput = "delete 1";
        String[] descriptionSubstrings = deleteCoursesCommand.parseDeleteCommand(userInput);
        assertArrayEquals(new String[]{"delete", "1"}, descriptionSubstrings);
    }

    @Test
    public void parseDeleteCommand_inputWithTwoIndexes_expectException() {
        String userInput = "delete 1 12";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            deleteCoursesCommand.parseDeleteCommand(userInput);
        });
        assertEquals("Please provide just one index of the course plan you would like to delete.",
                e.getMessage());
    }

    @Test
    public void parseDeleteCommand_inputWithNoIndexes_expectException() {
        String userInput = "delete ";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            deleteCoursesCommand.parseDeleteCommand(userInput);
        });
        assertEquals("Please provide the index of the course plan you would like to delete.",
                e.getMessage());
    }

    @Test
    public void deleteCourse_inputWithValidIndex_success() {
        int listIndex = 1;
        deleteCoursesCommand.deleteCourse(listIndex, storage);
        String expectedOutput = "You have deleted the course from your plan: " +
                "cs2102 | the university of melbourne | info20003";
        String actualOutput = outputStreamCaptor.toString();
        assertEquals(normalizeLineEndings(expectedOutput), normalizeLineEndings(actualOutput));
    }

    @Test
    public void deleteCourse_inputWithPositiveInvalidIndex_expectException() {
        int listIndex = 1000;
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            deleteCoursesCommand.deleteCourse(listIndex, storage);
        });
        assertEquals("Please provide a valid index of the course plan you would like to delete.\n" +
                "Type `list mapped` to check your current list of saved plans!", e.getMessage());
    }

    @Test
    public void deleteCourse_inputWithZeroIndex_expectException() {
        int listIndex = 0;
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            deleteCoursesCommand.deleteCourse(listIndex, storage);
        });
        assertEquals("Please provide a valid index of the course plan you would like to delete.\n" +
                        "Type `list mapped` to check your current list of saved plans!", e.getMessage());
    }

    @Test
    public void deleteCourse_inputWithNegativeIndex_expectException() {
        int listIndex = -1;
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            deleteCoursesCommand.deleteCourse(listIndex, storage);
        });
        assertEquals("Please provide a valid index of the course plan you would like to delete.\n" +
                "Type `list mapped` to check your current list of saved plans!", e.getMessage());
    }

    @Test
    public void execute_inputWithValidIndex_success() {
        String userInput = "delete 1";
        deleteCoursesCommand.execute(userInput, storage);
        String expectedOutput = "You have deleted the course from your plan: " +
                "cs2102 | the university of melbourne | info20003";
        String actualOutput = outputStreamCaptor.toString();
        assertEquals(normalizeLineEndings(expectedOutput), normalizeLineEndings(actualOutput));
    }

    @Test
    public void execute_inputWithInvalidIndex_expectErrorMessage() {
        String userInput = "delete 1000";
        deleteCoursesCommand.execute(userInput, storage);
        String expectedOutput = """
                -----------------------------------------------------
                Please provide a valid index of the course plan you would like to delete.
                Type `list mapped` to check your current list of saved plans!
                -----------------------------------------------------
                """;
        String actualOutput = outputStreamCaptor.toString();
        assertEquals(normalizeLineEndings(expectedOutput),
                normalizeLineEndings(actualOutput));
    }

    @Test
    public void execute_inputWithNonIntegerIndex_expectErrorMessage() {
        String userInput = "delete one";
        deleteCoursesCommand.execute(userInput, storage);
        String expectedOutput = """
                -----------------------------------------------------
                Please provide a valid numeric index of the course plan you would like to delete.
                -----------------------------------------------------
                """;
        String actualOutput = outputStreamCaptor.toString();
        assertEquals(normalizeLineEndings(expectedOutput),
                normalizeLineEndings(actualOutput));
    }

    String normalizeLineEndings(String input) {
        return input.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n").trim();
    }
}
