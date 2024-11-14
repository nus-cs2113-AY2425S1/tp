package tutorlink.command;

import java.util.HashMap;
import tutorlink.appstate.AppState;
import tutorlink.commons.Commons;
import tutorlink.exceptions.ComponentNotFoundException;
import tutorlink.exceptions.IllegalValueException;
import tutorlink.exceptions.TutorLinkException;
import tutorlink.lists.ComponentList;
import tutorlink.result.CommandResult;

/**
 * Command to delete a component from the system. It will delete the component from the component list
 * and remove associated grades. It also updates the percentage scores of all students.
 */
public class DeleteComponentCommand extends Command {

    // Prefix for the argument (component name)
    public static final String[] ARGUMENT_PREFIXES = {"c/"};

    // Command keyword to identify the delete_component command
    public static final String COMMAND_WORD = "delete_component";

    /**
     * Executes the delete component command. Deletes the component from the system's list and
     * removes all associated grades.
     *
     * @param appState The current state of the application which holds all data.
     * @param hashmap A map containing arguments passed to the command (expected to have the component name with
     *                prefix "c/").
     * @return The result of the command execution, including success message.
     * @throws TutorLinkException If the component name is invalid or not found.
     */
    @Override
    public CommandResult execute(AppState appState, HashMap<String, String> hashmap) throws TutorLinkException {
        // Retrieve the component name from the hashmap
        String componentName = hashmap.get(ARGUMENT_PREFIXES[0]);

        // Ensure the component name is provided
        if (componentName == null) {
            throw new IllegalValueException(String.format(Commons.ERROR_NULL, ARGUMENT_PREFIXES[0]));
        }

        // Search for the component in the list
        ComponentList componentsToDelete = appState.components.findComponent(componentName);

        // If no component is found, throw an exception
        if (componentsToDelete.size() == 0) {
            throw new ComponentNotFoundException(String.format(Commons.ERROR_COMPONENT_NOT_FOUND, componentName));
        }

        // Delete the component and its associated grades, then update student scores
        appState.components.deleteComponent(componentsToDelete.getComponentArrayList().get(0));
        appState.grades.deleteGradesByComponent(componentName);
        appState.updateAllStudentPercentageScores();

        // Return a success message
        return new CommandResult(String.format(Commons.DELETE_COMPONENT_SUCCESS, componentName));
    }

    /**
     * Returns the prefixes for the arguments expected by this command.
     * In this case, the prefix is "c/" for the component name.
     *
     * @return The array of argument prefixes.
     */
    @Override
    public String[] getArgumentPrefixes() {
        return ARGUMENT_PREFIXES;
    }
}
