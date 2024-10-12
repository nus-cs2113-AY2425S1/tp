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

        assertEquals("cs2102 /pu university of melbourne" +
                " /coursepu INFO20003", trimmedInput);
    }

    @Test
    void trimString_commandOnly_exceptionThrown() {
        String input = "add";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> {addCoursesCommand.trimString(input);});
        assertEquals("Please provide the nus course code, " +
                "name of partner university (PU) and the PU course code.", exception.getMessage());
    }

    @Test
    void trimString_commandWithSpaces_exceptionThrown() {
        String input = "add      ";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> {addCoursesCommand.trimString(input);});
        assertEquals("Please provide the nus course code, " +
                "name of partner university (PU) and the PU course code.", exception.getMessage());
    }

    @Test
    void execute() {
    }

    @Test
    void parseAddCommand() {
    }
    
}
