package tutorlink.command;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tutorlink.appstate.AppState;
import tutorlink.commons.Commons;
import tutorlink.exceptions.IllegalValueException;
import tutorlink.exceptions.TutorLinkException;
import tutorlink.result.CommandResult;

/**
 * Command to delete a student from the system. It requires the student's matriculation number,
 * validates the matriculation number format, and deletes the student and their associated grades.
 */
public class DeleteStudentCommand extends Command {

    // Prefix for the argument (matriculation number)
    public static final String[] ARGUMENT_PREFIXES = {"i/"};

    // Command word to identify the delete_student command
    public static final String COMMAND_WORD = "delete_student";

    /**
     * Executes the delete student command. It validates the matriculation number format,
     * deletes the student from the system and removes their associated grades.
     *
     * @param appState The current state of the application which holds all data.
     * @param hashmap A map containing arguments passed to the command (expected to have the matriculation number).
     * @return The result of the command execution, including a success message.
     * @throws TutorLinkException If the matriculation number is invalid or not found.
     */
    @Override
    public CommandResult execute(AppState appState, HashMap<String, String> hashmap) throws TutorLinkException {
        // Retrieve the matriculation number from the hashmap
        String matricNumber = hashmap.get(ARGUMENT_PREFIXES[0]);

        // Ensure matriculation number is provided
        if (matricNumber == null) {
            throw new IllegalValueException(String.format(Commons.ERROR_NULL, ARGUMENT_PREFIXES[0]));
        }

        // Validate the matriculation number using a regular expression
        Pattern pattern = Pattern.compile(Commons.MATRIC_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(matricNumber);
        if (!matcher.find()) {
            throw new IllegalValueException(Commons.ERROR_ILLEGAL_MATRIC_NUMBER);
        }

        // Delete the student and their associated grades
        appState.students.deleteStudent(matricNumber);
        appState.grades.deleteGradesByMatric(matricNumber);

        // Return a success message
        return new CommandResult(String.format(Commons.DELETE_STUDENT_SUCCESS, matricNumber));
    }

    /**
     * Returns the prefixes for the arguments expected by this command.
     * In this case, the prefix is "i/" for the matriculation number.
     *
     * @return The array of argument prefixes.
     */
    @Override
    public String[] getArgumentPrefixes() {
        return ARGUMENT_PREFIXES;
    }
}
