package tutorlink.command;

import tutorlink.appstate.AppState;
import tutorlink.commons.Commons;
import tutorlink.component.Component;
import tutorlink.exceptions.ComponentNotFoundException;
import tutorlink.exceptions.IllegalValueException;
import tutorlink.exceptions.InvalidWeightingException;
import tutorlink.exceptions.TutorLinkException;
import tutorlink.lists.ComponentList;
import tutorlink.result.CommandResult;

import java.util.HashMap;

/**
 * Represents a command to update an existing component's weight or maximum score.
 * The command requires the component's name and either a new weight or maximum score
 * (or both) to update the component.
 */
public class UpdateComponentCommand extends Command {
    public static final String[] ARGUMENT_PREFIXES = {"c/", "w/", "m/"};
    public static final String COMMAND_WORD = "update_component";

    /**
     * Executes the update component command, updating the specified component's
     * weight and/or maximum score in the application state.
     *
     * @param appState The current application state containing the component list.
     * @param hashmap  A map of argument prefixes to their values for this command.
     * @return A {@code CommandResult} indicating the result of the update.
     * @throws TutorLinkException If the component is not found, the new values are invalid,
     *                            or if the new weight causes the total weight to exceed the limit.
     */
    @Override
    public CommandResult execute(AppState appState, HashMap<String, String> hashmap) throws TutorLinkException {
        String name = hashmap.get(ARGUMENT_PREFIXES[0]);
        String cWeight = hashmap.get(ARGUMENT_PREFIXES[1]);
        String cMark = hashmap.get(ARGUMENT_PREFIXES[2]);

        // Validate the presence of required arguments
        if (name == null || (cWeight == null && cMark == null)) {
            StringBuilder sb = new StringBuilder("Error! ");
            if (name == null) {
                sb.append("c/ is required! ");
            }
            if (cWeight == null && cMark == null) {
                sb.append("Either w/ or m/ or both are required!");
            }
            throw new IllegalValueException(sb.toString());
        }

        Integer weight = null;
        Double mark = null;

        // Parse weight and mark values from the arguments
        try {
            if (cWeight != null) {
                weight = Integer.parseInt(cWeight);
            }
            if (cMark != null) {
                mark = Double.parseDouble(cMark);
            }
        } catch (NumberFormatException e) {
            throw new IllegalValueException("Error! weight is not an integer or mark is not a real positive number");
        }

        // Validate the parsed values for mark and weight
        if (mark != null && (mark.isNaN() || mark.isInfinite() || mark < 0)) {
            throw new IllegalValueException("Error! Mark is not a real positive number");
        }
        if (weight != null && (weight < 0 || weight > 100)) {
            throw new IllegalValueException("Error! weight must be between 0 and 100");
        }

        // Find the component by name
        ComponentList componentsToEdit = appState.components.findComponent(name);
        if (componentsToEdit.size() == 0) {
            throw new ComponentNotFoundException(String.format(Commons.ERROR_COMPONENT_NOT_FOUND, name));
        }

        Component component = componentsToEdit.getComponentArrayList().get(0);

        // Update weight if provided, ensuring total weighting remains valid
        int oldWeight = component.getWeight();
        if (weight != null) {
            component.setWeight(weight);
            int totalWeight = appState.components.getTotalWeighting();
            if (totalWeight > 100) {
                component.setWeight(oldWeight);
                throw new InvalidWeightingException(String.format(Commons.ERROR_INVALID_TOTAL_WEIGHTING, totalWeight));
            }
        }

        // Update maximum score if provided
        if (mark != null) {
            component.setMaxScore(mark);
        }

        // Update percentage scores for all students based on new component values
        appState.updateAllStudentPercentageScores();

        return new CommandResult("Here is the updated component\n" + component.toString());
    }

    /**
     * Returns the argument prefixes required by this command.
     *
     * @return An array of argument prefixes required by this command.
     */
    @Override
    public String[] getArgumentPrefixes() {
        return ARGUMENT_PREFIXES;
    }
}
