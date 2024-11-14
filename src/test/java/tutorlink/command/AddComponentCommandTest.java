//@@author yeekian
package tutorlink.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tutorlink.appstate.AppState;
import tutorlink.commons.Commons;
import tutorlink.component.Component;
import tutorlink.exceptions.DuplicateComponentException;
import tutorlink.exceptions.IllegalValueException;
import tutorlink.exceptions.InvalidWeightingException;
import tutorlink.grade.Grade;
import tutorlink.result.CommandResult;
import tutorlink.student.Student;


import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddComponentCommandTest {
    private AppState appState;
    private HashMap<String, String> arguments;
    private AddComponentCommand command;

    @BeforeEach
    void setup() {
        appState = new AppState();
        arguments = new HashMap<>();
        command = new AddComponentCommand();
    }

    @Test
    void execute_validArguments_componentAddedSuccessfully() {
        arguments.put("c/", "Quiz 1");
        arguments.put("w/", "30");
        arguments.put("m/", "100");
        int initialWeighting = appState.components.getTotalWeighting();
        CommandResult result = command.execute(appState, arguments);
        assertNotNull(result);
        assertEquals("Component Quiz 1 of weight 30%, with max score 100 added successfully!", result.toString());
        assertEquals(1, appState.components.getComponentArrayList().size());
        assertEquals(initialWeighting + 30, appState.components.getTotalWeighting());
    }

    @Test
    void execute_nullArguments_throwsIllegalValueException() {
        int initialWeighting = appState.components.getTotalWeighting();
        IllegalValueException exception = assertThrows(IllegalValueException.class, () -> {
            command.execute(appState, arguments);
        });
        assertEquals("Error! Null parameters c/, w/, m/ passed!", exception.getMessage());
        assertEquals(initialWeighting, appState.components.getTotalWeighting());
    }

    @Test
    void execute_missingWeightageArgument_throwsIllegalValueException() {
        arguments.put("c/", "Quiz 1");
        arguments.put("m/", "100");
        int initialWeighting = appState.components.getTotalWeighting();
        IllegalValueException exception = assertThrows(IllegalValueException.class, () -> {
            command.execute(appState, arguments);
        });
        assertEquals("Error! Null parameters w/ passed!", exception.getMessage());
        assertEquals(initialWeighting, appState.components.getTotalWeighting());
    }

    @Test
    void execute_extraArgument_componentAddedSuccessfully() {
        arguments.put("c/", "Quiz 1");
        arguments.put("w/", "50");
        arguments.put("m/", "100");
        arguments.put("extra/", "extra value");
        int initialWeighting = appState.components.getTotalWeighting();
        CommandResult result = command.execute(appState, arguments);
        assertNotNull(result);
        assertEquals("Component Quiz 1 of weight 50%, with max score 100 added successfully!", result.toString());
        assertEquals(1, appState.components.getComponentArrayList().size());
        assertEquals(initialWeighting + 50, appState.components.getTotalWeighting());
    }

    @Test
    void execute_weightageOutOfRange_throwsIllegalValueException() {
        arguments.put("c/", "Quiz 1");
        arguments.put("w/", "1.5");
        arguments.put("m/", "100");
        int initialWeighting = appState.components.getTotalWeighting();
        IllegalValueException exception = assertThrows(IllegalValueException.class, () -> {
            command.execute(appState, arguments);
        });
        assertEquals(Commons.ERROR_INVALID_WEIGHTAGE, exception.getMessage());
        assertEquals(initialWeighting, appState.components.getTotalWeighting());
    }

    @Test
    void execute_negativeWeighting_throwsIllegalValueException() {
        arguments.put("c/", "Quiz 1");
        arguments.put("w/", "-1");
        arguments.put("m/", "100");
        int initialWeighting = appState.components.getTotalWeighting();
        IllegalValueException exception = assertThrows(IllegalValueException.class, () -> {
            command.execute(appState, arguments);
        });
        assertEquals(Commons.ERROR_INVALID_WEIGHTAGE, exception.getMessage());
        assertEquals(initialWeighting, appState.components.getTotalWeighting());
    }

    @Test
    void execute_negativeMaxScore_throwsIllegalValueException() {
        arguments.put("c/", "Quiz 1");
        arguments.put("w/", "0.4");
        arguments.put("m/", "-10");
        int initialWeighting = appState.components.getTotalWeighting();
        IllegalValueException exception = assertThrows(IllegalValueException.class, () -> {
            command.execute(appState, arguments);
        });
        assertEquals(Commons.ERROR_INVALID_WEIGHTAGE, exception.getMessage());
        assertEquals(initialWeighting, appState.components.getTotalWeighting());
    }

    @Test
    void execute_weightageNotInt_throwsIllegalValueException() {
        arguments.put("c/", "Quiz 1");
        arguments.put("w/", "one");
        arguments.put("m/", "100");
        int initialWeighting = appState.components.getTotalWeighting();
        IllegalValueException exception = assertThrows(IllegalValueException.class, () -> {
            command.execute(appState, arguments);
        });
        assertEquals(Commons.ERROR_INVALID_WEIGHTAGE, exception.getMessage());
        assertEquals(initialWeighting, appState.components.getTotalWeighting());
    }

    @Test
    void execute_maxScoreNotDouble_throwsIllegalValueException() {
        arguments.put("c/", "Quiz 1");
        arguments.put("w/", "0.4");
        arguments.put("m/", "hundred");
        int initialWeighting = appState.components.getTotalWeighting();
        IllegalValueException exception = assertThrows(IllegalValueException.class, () -> {
            command.execute(appState, arguments);
        });
        assertEquals(Commons.ERROR_INVALID_WEIGHTAGE, exception.getMessage());
        assertEquals(initialWeighting, appState.components.getTotalWeighting());
    }

    @Test
    void execute_maxWeightageExceeded_throwsInvalidWeightingException() {
        arguments.put("c/", "Quiz 1");
        arguments.put("w/", "100");
        arguments.put("m/", "100");

        CommandResult result = command.execute(appState, arguments);
        assertNotNull(result);

        int initialWeighting = appState.components.getTotalWeighting();

        arguments.clear();
        arguments.put("c/", "Quiz 2");
        arguments.put("w/", "1");
        arguments.put("m/", "100");

        InvalidWeightingException exception = assertThrows(InvalidWeightingException.class, () -> {
            command.execute(appState, arguments);
        });
        assertEquals(String.format(Commons.ERROR_INVALID_TOTAL_WEIGHTING, appState.components.getTotalWeighting() + 1),
                exception.getMessage());
        assertEquals(initialWeighting, appState.components.getTotalWeighting());
    }

    @Test
    void execute_duplicateComponents_totalWeightingUnchanged() {
        arguments.put("c/", "Quiz 1");
        arguments.put("w/", "20");
        arguments.put("m/", "100");

        assertEquals(0, appState.components.getTotalWeighting());

        command.execute(appState, arguments);

        assertEquals(20, appState.components.getTotalWeighting());

        DuplicateComponentException exception = assertThrows(DuplicateComponentException.class, () -> {
            command.execute(appState, arguments);
        });

        assertEquals(20, appState.components.getTotalWeighting());
    }

    @Test
    void execute_negativeWeighting_totalWeightingUnchanged() {
        arguments.put("c/", "Quiz 1");
        arguments.put("w/", "20");
        arguments.put("m/", "100");

        assertEquals(0, appState.components.getTotalWeighting());
        command.execute(appState, arguments);
        assertEquals(20, appState.components.getTotalWeighting());

        arguments.clear();
        arguments.put("c/", "Quiz 2");
        arguments.put("w/", "-20");
        arguments.put("m/", "100");
        IllegalValueException exception = assertThrows(IllegalValueException.class, () -> {
            command.execute(appState, arguments);
        });
        assertEquals(Commons.ERROR_INVALID_WEIGHTAGE, exception.getMessage());
        assertEquals(20, appState.components.getTotalWeighting());
    }

    @Test
    void update_gpa_accordingly () {
        appState.students.addStudent("A1234567X", "John Doe");
        Student student = appState.students.getStudentArrayList().get(0);
        Component component = new Component("midterm", 50, 50);
        appState.components.addComponent(component);
        appState.grades.addGrade(new Grade(component, student, 50));
        appState.updateAllStudentPercentageScores();

        assertEquals(student.getPercentageScore(), 100);

        arguments.put("c/", "Final");
        arguments.put("w/", "50");
        arguments.put("m/", "50");

        command.execute(appState, arguments);
        assertEquals(student.getPercentageScore(), 50);
    }

    @Test
    void execute_maxScoreLargerThanMax_exception() {
        arguments.put("c/", "Quiz 1");
        arguments.put("w/", "100");
        arguments.put("m/", "20000");
        assertThrows(IllegalValueException.class, () -> command.execute(appState, arguments));
    }
}
//@@author
