package tutorlink.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tutorlink.appstate.AppState;
import tutorlink.commons.Commons;
import tutorlink.exceptions.DuplicateMatricNumberException;
import tutorlink.exceptions.IllegalValueException;
import tutorlink.exceptions.StudentNotFoundException;
import tutorlink.exceptions.TutorLinkException;
import tutorlink.lists.StudentList;
import tutorlink.result.CommandResult;
import tutorlink.student.Student;

import static tutorlink.lists.StudentList.STUDENT_NOT_FOUND;

/**
 * Command to delete a grade from a student's record based on the specified matriculation number
 * and component description.
 */
public class DeleteGradeCommand extends Command {

    public static final String[] ARGUMENT_PREFIXES = {"i/", "c/"};
    public static final String COMMAND_WORD = "delete_grade";

    /**
     * Executes the delete grade command, deleting a specific grade component for a student and updating their GPA.
     *
     * @param appState The current state of the application.
     * @param hashmap Contains the arguments passed to the command.
     * @return The result of executing the delete grade command.
     * @throws TutorLinkException If there is an error in processing the command, including invalid arguments or
     *                            student not found.
     */
    @Override
    public CommandResult execute(AppState appState, HashMap<String, String> hashmap) throws TutorLinkException {
        String matricNumber = hashmap.get(ARGUMENT_PREFIXES[0]);
        String componentDescription = hashmap.get(ARGUMENT_PREFIXES[1]);

        validateArguments(matricNumber, componentDescription);
        matricNumber = formatMatricNumber(matricNumber);

        Student student = getStudent(appState, matricNumber);
        deleteGrade(appState, matricNumber, componentDescription);

        updateStudentGPA(appState, student, matricNumber);

        return new CommandResult(String.format(Commons.DELETE_GRADE_SUCCESS, componentDescription, matricNumber));
    }

    /**
     * Validates the required arguments for the delete grade command.
     *
     * @param matricNumber The matriculation number of the student.
     * @param componentDescription The description of the grade component to be deleted.
     * @throws IllegalValueException If any required argument is null.
     */
    private void validateArguments(String matricNumber, String componentDescription) throws IllegalValueException {
        if (matricNumber == null || componentDescription == null) {
            List<String> nullParameters = new ArrayList<>();
            if (matricNumber == null) {
                nullParameters.add(ARGUMENT_PREFIXES[0]);
            }
            if (componentDescription == null) {
                nullParameters.add(ARGUMENT_PREFIXES[1]);
            }
            throw new IllegalValueException(String.format(Commons.ERROR_NULL,
                    String.join(", ", nullParameters)));
        }
    }

    /**
     * Formats and validates the matriculation number.
     *
     * @param matricNumber The matriculation number to format.
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
     * Finds a student based on their matriculation number.
     *
     * @param appState The current state of the application.
     * @param matricNumber The matriculation number of the student.
     * @return The student found with the specified matriculation number.
     * @throws StudentNotFoundException If no student with the matriculation number is found.
     * @throws DuplicateMatricNumberException If more than one student is found with the same matriculation number.
     */
    private static Student getStudent(AppState appState, String matricNumber) {
        StudentList filteredList = appState.students.findStudentByMatricNumber(matricNumber);

        if (filteredList.size() == 0) {
            throw new StudentNotFoundException(String.format(STUDENT_NOT_FOUND, matricNumber));
        } else if (filteredList.size() > 1) {
            String errorMessage = String.format(Commons.ERROR_DUPLICATE_STUDENT, matricNumber);
            throw new DuplicateMatricNumberException(errorMessage);
        }

        return filteredList.getStudentArrayList().get(0);
    }

    /**
     * Deletes the specified grade component for a student.
     *
     * @param appState The current state of the application.
     * @param matricNumber The matriculation number of the student.
     * @param componentDescription The description of the grade component to be deleted.
     */
    private void deleteGrade(AppState appState, String matricNumber, String componentDescription) {
        appState.grades.deleteGrade(matricNumber, componentDescription);
    }

    /**
     * Updates the student's GPA by calculating their new percentage score after a grade component has been deleted.
     *
     * @param appState The current state of the application.
     * @param student The student whose GPA is to be updated.
     * @param matricNumber The matriculation number of the student.
     */
    private void updateStudentGPA(AppState appState, Student student, String matricNumber) {
        double newGPA = appState.grades.calculateStudentPercentageScore(matricNumber, appState.components);
        student.setPercentageScore(newGPA);
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
