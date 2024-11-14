package tutorlink.command;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tutorlink.appstate.AppState;
import tutorlink.commons.Commons;
import tutorlink.component.Component;
import tutorlink.exceptions.ComponentNotFoundException;
import tutorlink.exceptions.IllegalValueException;
import tutorlink.grade.Grade;
import tutorlink.result.CommandResult;
import tutorlink.student.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class DeleteComponentCommandTest {
    private AppState appState;
    private DeleteComponentCommand command = new DeleteComponentCommand();
    private HashMap<String,String> arguments;
    private CommandResult result;
    @BeforeEach
    void setup() {
        appState = new AppState();
        appState.components.addComponent(new Component("finals", 40.0, 40));
        appState.components.addComponent(new Component("iP", 20.0, 10));
        appState.components.addComponent(new Component("lectures", 10.0, 10));
        arguments = new HashMap<>();
    }

    @Test
    void deleteComponent_success() {
        arguments.put("c/", "finals");
        int initialWeighting = appState.components.getTotalWeighting();
        result = command.execute(appState, arguments);
        assertNotNull(result);
        assertEquals(appState.components.size(), 2);
        assertEquals(appState.components.getTotalWeighting(), initialWeighting - 40);
    }

    @Test
    void deleteComponent_notFound_fail() {
        arguments.put("c/", "midterms");
        int initialWeighting = appState.components.getTotalWeighting();
        try {
            command.execute(appState, arguments);
        } catch (ComponentNotFoundException e) {
            assertEquals(e.getMessage(), "Error! Component midterms does not exist in the list!");
        } catch (Exception e) {
            fail("Expected: ComponentNotFoundException, actual: " + e.getMessage());
        } finally {
            assertEquals(appState.components.getTotalWeighting(), initialWeighting);
        }
    }

    @Test
    void deleteComponent_emptyParam_fail() {
        int initialWeighting = appState.components.getTotalWeighting();
        try {
            command.execute(appState, arguments);
        } catch (IllegalValueException e) {
            assertEquals(e.getMessage(), String.format(Commons.ERROR_NULL, command.getArgumentPrefixes()[0]));
        } catch (Exception e) {
            fail("Expected: ComponentNotFoundException, actual: " + e.getMessage());
        } finally {
            assertEquals(appState.components.getTotalWeighting(), initialWeighting);
        }
    }

    @Test
    void update_gpa_accordingly () {
        appState = new AppState();
        appState.students.addStudent("A1234567X", "John Doe");
        Student student = appState.students.getStudentArrayList().get(0);
        Component component = new Component("midterm", 50, 50);
        appState.components.addComponent(component);
        appState.grades.addGrade(new Grade(component, student, 0));

        Component component2 = new Component("final", 50, 50);
        appState.components.addComponent(component2);
        appState.grades.addGrade(new Grade(component2, student, 50));
        appState.updateAllStudentPercentageScores();
        assertEquals(student.getPercentageScore(), 50);

        arguments.put("c/", "Final");

        command.execute(appState, arguments);
        assertEquals(student.getPercentageScore(), 0);
    }
}
