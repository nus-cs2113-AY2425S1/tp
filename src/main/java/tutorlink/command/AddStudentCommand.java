package tutorlink.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tutorlink.appstate.AppState;
import tutorlink.commons.Commons;
import tutorlink.exceptions.IllegalValueException;
import tutorlink.exceptions.TutorLinkException;
import tutorlink.result.CommandResult;

/**
 * Command to add a student to the system. It requires the student's matriculation number and name.
 * The matriculation number is validated using a regular expression.
 */
public class AddStudentCommand extends Command {

    // Prefixes for the arguments (matriculation number and name)
    public static final String[] ARGUMENT_PREFIXES = {"i/", "n/"};

    // Command word to identify the add_student command
    public static final String COMMAND_WORD = "add_student";

    /**
     * Executes the add student command. It validates the matriculation number and adds the student
     * to the system if valid. If the matriculation number or name is missing, an exception is thrown.
     *
     * @param appState The current state of the application which holds all data.
     * @param hashmap A map containing arguments passed to the command (expected to have matriculation number and name).
     * @return The result of the command execution, including a success message.
     * @throws TutorLinkException If there is an error with the matriculation number or if required parameters are
     *                missing.
     */
    @Override
    public CommandResult execute(AppState appState, HashMap<String, String> hashmap) throws TutorLinkException {
        // Retrieve the matriculation number and name from the hashmap
        String matricNumber = hashmap.get(ARGUMENT_PREFIXES[0]);
        String name = hashmap.get(ARGUMENT_PREFIXES[1]);

        // Ensure both matric number and name are provided
        if (matricNumber == null || name == null) {
            List<String> nullParameters = new ArrayList<>();
            if (matricNumber == null) {
                nullParameters.add(ARGUMENT_PREFIXES[0]);
            }
            if (name == null) {
                nullParameters.add(ARGUMENT_PREFIXES[1]);
            }
            throw new IllegalValueException(String.format(Commons.ERROR_NULL, String.join(", ", nullParameters)));
        }

        // Validate the matriculation number using a regular expression
        Pattern pattern = Pattern.compile(Commons.MATRIC_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(matricNumber);
        if (!matcher.find()) {
            throw new IllegalValueException(Commons.ERROR_ILLEGAL_MATRIC_NUMBER);
        }

        // Add the student to the system
        appState.students.addStudent(matricNumber, name);

        // Return a success message
        return new CommandResult(String.format(Commons.ADD_STUDENT_SUCCESS, name, matricNumber));
    }

    /**
     * Returns the prefixes for the arguments expected by this command.
     * In this case, the prefixes are "i/" for the matriculation number and "n/" for the name.
     *
     * @return The array of argument prefixes.
     */
    @Override
    public String[] getArgumentPrefixes() {
        return ARGUMENT_PREFIXES;
    }
}
