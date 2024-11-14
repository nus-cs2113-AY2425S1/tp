//@@author RCPilot1604
package tutorlink.command;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tutorlink.appstate.AppState;
import tutorlink.commons.Commons;
import tutorlink.exceptions.IllegalValueException;
import tutorlink.result.CommandResult;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;


public class AddStudentCommandTest {
    private AppState appState;
    private HashMap<String, String> arguments;
    private AddStudentCommand command;

    @BeforeEach
    void setup() {
        appState = new AppState();
        arguments = new HashMap<>();
        command = new AddStudentCommand();
    }

    @Test
    void execute_addOne_expectOne() {
        arguments.put("i/","A1234567X");
        arguments.put("n/", "John");
        CommandResult result = command.execute(appState, arguments);
        assertNotNull(result);
        assertEquals(result.toString(), "Student John (A1234567X) added successfully!");
        assertEquals(appState.students.getStudentArrayList().size(), 1);
    }

    @Test
    void execute_emptyInput_exceptionThrown() {
        arguments.put("i/","A1234567X");
        try {
            CommandResult result = command.execute(appState, arguments);
            fail("Expected an exception to be thrown due to empty input");
        } catch (IllegalValueException e) {
            // Assert that the exception message matches the expected outcome
            assertEquals(String.format(Commons.ERROR_NULL, command.getArgumentPrefixes()[1]), e.getMessage());
        } catch (Exception e) {
            // If any other type of exception is thrown, fail the test
            fail("Expected IllegalArgumentException, but got: " + e.getClass().getSimpleName());
        }
    }

    @Test
    void execute_bothNullInputs_exceptionThrown() {
        try {
            CommandResult result = command.execute(appState, arguments);
            fail("Expected an exception to be thrown due to empty input");
        } catch (IllegalValueException e) {
            // Assert that the exception message matches the expected outcome
            assertEquals("Error! Null parameters i/, n/ passed!", e.getMessage());
        } catch (Exception e) {
            // If any other type of exception is thrown, fail the test
            fail("Expected IllegalArgumentException, but got: " + e.getClass().getSimpleName());
        }
    }
}
//@@author
