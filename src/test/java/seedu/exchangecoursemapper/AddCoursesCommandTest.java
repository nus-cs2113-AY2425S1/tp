package seedu.exchangecoursemapper;

import org.junit.jupiter.api.Test;
import seedu.exchangecoursemapper.command.AddCoursesCommand;

import static org.junit.jupiter.api.Assertions.*;

public class AddCoursesCommandTest {

    private final AddCoursesCommand addCoursesCommand = new AddCoursesCommand();

    @Test
    void trimString_properInput_success() {
        String input = "add cs2102 /pu university of melbourne /coursepu INFO20003";
        String trimmedInput = addCoursesCommand.trimString(input);

        assertEquals("cs2102 /pu university of melbourne" + " /coursepu INFO20003", trimmedInput);
    }

    @Test
    void trimString_commandOnly_exceptionThrown() {
        String input = "add";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            addCoursesCommand.trimString(input);
        });
        assertEquals("Please provide the nus course code, " +
                "name of partner university (PU) and the PU course code.", exception.getMessage());
    }

    @Test
    void trimString_commandWithSpaces_exceptionThrown() {
        String input = "add      ";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            addCoursesCommand.trimString(input);
        });
        assertEquals("Please provide the nus course code, " +
                "name of partner university (PU) and the PU course code.", exception.getMessage());
    }

    @Test
    void parseAddCommand_oneCommandOnly_exceptionThrown() {
        String input = "/pu";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            addCoursesCommand.parseAddCommand(input);
        });
        assertEquals("Please provide all of the valid commands: /pu, /coursepu!", exception.getMessage());
    }

    @Test
    void parseAddCommand_oneCommandOnlyWithSpaces_exceptionThrown() {
        String input = "/pu           ";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            addCoursesCommand.parseAddCommand(input);
        });
        assertEquals("Please provide all of the valid commands: /pu, /coursepu!", exception.getMessage());
    }

    @Test
    void parseAddCommand_mergeCommands_exceptionThrown() {
        String input = "/pu/coursepu";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            addCoursesCommand.parseAddCommand(input);
        });
        assertEquals("Commands shouldn't be adjacent to one another!", exception.getMessage());
    }

    @Test
    void parseAddCommands_missingNUSCourseCode_exceptionThrown() {
        String input = "/pu university of melbourne /coursepu INFO20003 ";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            addCoursesCommand.parseAddCommand(input);
        });
        assertEquals("Please provide a valid NUS course code or PU or PU's course code!", exception.getMessage());
    }

    @Test
    void parseAddCommands_missingPU_exceptionThrown() {
        String input = "cs2102 /pu /coursepu INFO20003";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            addCoursesCommand.parseAddCommand(input);
        });
        assertEquals("Please provide a valid NUS course code or PU or PU's course code!", exception.getMessage());
    }

    @Test
    void parseAddCommands_missingPUCourseCode_exceptionThrown() {
        String input = "cs2102 /pu university of melbourne /coursepu";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            addCoursesCommand.parseAddCommand(input);
        });
        assertEquals("Please provide a valid NUS course code or PU or PU's course code!", exception.getMessage());
    }

    @Test
    void parseAddCommand_properInput_success() {
        String input = "cs2102 /pu university of melbourne /coursepu INFO20003";
        String[] inputSubstrings = addCoursesCommand.parseAddCommand(input);
        String[] expected = {"cs2102", "university of melbourne", "INFO20003"};
        assertArrayEquals(expected, inputSubstrings);
    }
}
