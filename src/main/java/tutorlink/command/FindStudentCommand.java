package tutorlink.command;

import java.util.HashMap;

import tutorlink.commons.Commons;
import tutorlink.exceptions.IllegalValueException;
import tutorlink.exceptions.TutorLinkException;
import tutorlink.lists.StudentList;
import tutorlink.result.CommandResult;
import tutorlink.appstate.AppState;

/**
 * Command to find a student by either their matriculation number or their name.
 * The command can search for a student by matriculation number or by name, but not both.
 * If neither is provided, an exception is thrown.
 */
public class FindStudentCommand extends Command {

    // Prefixes for the arguments (matriculation number and name)
    public static final String[] ARGUMENT_PREFIXES = {"i/", "n/"};

    // Command word to identify the find_student command
    public static final String COMMAND_WORD = "find_student";

    /**
     * Executes the find student command. It searches for a student by their matriculation number
     * or by their name, depending on which argument is provided.
     *
     * @param appstate The current state of the application which holds all data.
     * @param hashmap A map containing arguments passed to the command (either matriculation number or name).
     * @return The result of the command execution, which includes a string representation of the student(s).
     * @throws TutorLinkException If neither a matriculation number nor a name is provided,
     *          or if an invalid search is made.
     */
    @Override
    public CommandResult execute(AppState appstate, HashMap<String, String> hashmap) throws TutorLinkException {
        // Retrieve the matriculation number and name from the hashmap
        String matricNumber = hashmap.get(ARGUMENT_PREFIXES[0]);
        String name = hashmap.get(ARGUMENT_PREFIXES[1]);
        StudentList students;

        // Ensure at least one argument (matriculation number or name) is provided
        if (name == null && matricNumber == null) {
            throw new IllegalValueException(Commons.ERROR_STUDENT_BOTH_NULL);
        }

        // If a matriculation number is provided, search by matriculation number
        if (hashmap.containsKey(ARGUMENT_PREFIXES[0])) {
            students = appstate.students.findStudentByMatricNumber(matricNumber);
            assert students.getStudentArrayList().size() <= 1;
        } else {
            students = appstate.students.findStudentByName(name);
        }

        // Return the result as a string representation of the found student(s)
        return new CommandResult(students.toString());
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
