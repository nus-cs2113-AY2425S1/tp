package tutorlink.command;

import java.util.ArrayList;
import java.util.List;
import tutorlink.appstate.AppState;
import tutorlink.commons.Commons;
import tutorlink.component.Component;
import tutorlink.exceptions.ComponentNotFoundException;
import tutorlink.exceptions.DuplicateComponentException;
import tutorlink.exceptions.DuplicateMatricNumberException;
import tutorlink.exceptions.IllegalValueException;
import tutorlink.exceptions.StudentNotFoundException;
import tutorlink.exceptions.TutorLinkException;
import tutorlink.grade.Grade;
import tutorlink.lists.ComponentList;
import tutorlink.lists.StudentList;
import tutorlink.result.CommandResult;
import tutorlink.student.Student;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static tutorlink.lists.StudentList.STUDENT_NOT_FOUND;

/**
 * Command to add a grade to a student's record based on their matriculation number,
 * the specified component description, and score.
 */
public class AddGradeCommand extends Command {
    public static final String[] ARGUMENT_PREFIXES = {"i/", "c/", "s/"};
    public static final String COMMAND_WORD = "add_grade";
    private static final String ERROR_DUPLICATE_GRADE_ON_ADD = "Error! Grade (%s, %s) already exists in the list!";

    /**
     * Executes the add grade command, creating a new grade record for the specified student
     * and updating their GPA.
     *
     * @param appState The current state of the application.
     * @param hashmap Contains the arguments passed to the command.
     * @return The result of executing the add grade command.
     * @throws TutorLinkException If there is an error in processing the command, including invalid arguments or
     *                            component/student not found.
     */
    @Override
    public CommandResult execute(AppState appState, HashMap<String, String> hashmap) throws TutorLinkException {
        String matricNumber = hashmap.get(ARGUMENT_PREFIXES[0]);
        String componentDescription = hashmap.get(ARGUMENT_PREFIXES[1]);
        String scoreNumber = hashmap.get(ARGUMENT_PREFIXES[2]);

        validateArguments(matricNumber, componentDescription, scoreNumber);

        matricNumber = formatMatricNumber(matricNumber);

        Component component = findComponent(appState, componentDescription);
        Student student = findStudent(appState, matricNumber);

        double score = parseScore(scoreNumber, component);

        addGradeAndCalculateGPA(appState, component, student, score);

        return new CommandResult(
                String.format(Commons.ADD_GRADE_SUCCESS, scoreNumber, componentDescription, matricNumber));
    }

    /**
     * Validates that all required arguments are present.
     *
     * @param matricNumber The matriculation number of the student.
     * @param componentDescription The description of the grade component.
     * @param scoreNumber The score for the grade component.
     * @throws IllegalValueException If any argument is null.
     */
    private void validateArguments(String matricNumber, String componentDescription, String scoreNumber)
            throws IllegalValueException {
        if (matricNumber == null || componentDescription == null || scoreNumber == null) {
            List<String> nullParameters = new ArrayList<>();
            if (matricNumber == null) {
                nullParameters.add(ARGUMENT_PREFIXES[0]);
            }
            if (componentDescription == null) {
                nullParameters.add(ARGUMENT_PREFIXES[1]);
            }
            if (scoreNumber == null) {
                nullParameters.add(ARGUMENT_PREFIXES[2]);
            }
            throw new IllegalValueException(String.format(Commons.ERROR_NULL,
                    String.join(", ", nullParameters)));
        }
    }

    /**
     * Formats and validates the matriculation number.
     *
     * @param matricNumber The matriculation number to format and validate.
     * @return The formatted matriculation number.
     * @throws IllegalValueException If the matriculation number does not match the required format.
     */
    private String formatMatricNumber(String matricNumber) throws IllegalValueException {
        matricNumber = matricNumber.toUpperCase();
        Pattern pattern = Pattern.compile(Commons.MATRIC_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(matricNumber);
        if (!matcher.find()) {
            throw new IllegalValueException(Commons.ERROR_ILLEGAL_MATRIC_NUMBER);
        }
        return matricNumber;
    }

    /**
     * Finds a component based on its description.
     *
     * @param appState The current state of the application.
     * @param componentDescription The description of the component to find.
     * @return The component with the specified description.
     * @throws DuplicateComponentException If multiple components are found with the same description.
     * @throws ComponentNotFoundException If no component is found with the specified description.
     */
    private Component findComponent(AppState appState, String componentDescription)
            throws DuplicateComponentException, ComponentNotFoundException {
        ComponentList componentFilteredList = appState.components.findComponent(componentDescription);
        if (componentFilteredList.size() == 1) {
            return componentFilteredList.getComponentArrayList().get(0);
        } else if (componentFilteredList.size() == 0) {
            throw new ComponentNotFoundException(
                    String.format(Commons.ERROR_COMPONENT_NOT_FOUND, componentDescription));
        } else {
            throw new DuplicateComponentException(
                    String.format(Commons.ERROR_MULTIPLE_QUERY_RESULT, componentDescription));
        }
    }

    /**
     * Finds a student based on their matriculation number.
     *
     * @param appState The current state of the application.
     * @param matricNumber The matriculation number of the student to find.
     * @return The student with the specified matriculation number.
     * @throws StudentNotFoundException If no student with the matriculation number is found.
     * @throws DuplicateMatricNumberException If more than one student is found with the same matriculation number.
     */
    private Student findStudent(AppState appState, String matricNumber)
            throws StudentNotFoundException, DuplicateMatricNumberException {
        StudentList studentFilteredList = appState.students.findStudentByMatricNumber(matricNumber);
        if (studentFilteredList.size() == 1) {
            return studentFilteredList.getStudentArrayList().get(0);
        } else if (studentFilteredList.size() == 0) {
            throw new StudentNotFoundException(String.format(STUDENT_NOT_FOUND, matricNumber));
        } else {
            throw new DuplicateMatricNumberException(String.format(Commons.ERROR_DUPLICATE_STUDENT, matricNumber));
        }
    }

    /**
     * Converts the score string to a double and validates it against the component's maximum score.
     *
     * @param scoreNumber The score in string format.
     * @param component The component associated with the score.
     * @return The score as a valid double.
     * @throws IllegalValueException If the score is invalid (e.g., non-numeric or out of range).
     */
    private double parseScore(String scoreNumber, Component component) throws IllegalValueException {
        try {
            double score = Double.parseDouble(scoreNumber);
            if (score < 0.0 || score > component.getMaxScore()) {
                throw new IllegalValueException(Commons.ERROR_INVALID_SCORE);
            }
            return score;
        } catch (NumberFormatException e) {
            throw new IllegalValueException(Commons.ERROR_INVALID_SCORE);
        }
    }

    /**
     * Adds the grade to the student's record and updates the student's GPA.
     *
     * @param appState The current state of the application.
     * @param component The component for which the grade is being added.
     * @param student The student receiving the grade.
     * @param score The score achieved by the student in the specified component.
     * @throws TutorLinkException If the grade already exists.
     */
    private void addGradeAndCalculateGPA(AppState appState, Component component, Student student, double score)
            throws TutorLinkException {
        Grade grade = new Grade(component, student, score);
        appState.grades.addGrade(grade);

        double newPercentageScore = appState.grades.calculateStudentPercentageScore(student.getMatricNumber(),
                appState.components);
        student.setPercentageScore(newPercentageScore);
    }

    /**
     * Returns the argument prefixes for this command.
     *
     * @return An array of argument prefixes.
     */
    @Override
    public String[] getArgumentPrefixes() {
        return ARGUMENT_PREFIXES;
    }
}
