package tutorlink.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tutorlink.appstate.AppState;
import tutorlink.commons.Commons;
import tutorlink.component.Component;
import tutorlink.exceptions.IllegalValueException;
import tutorlink.exceptions.InvalidWeightingException;
import tutorlink.exceptions.TutorLinkException;
import tutorlink.result.CommandResult;

/**
 * Command to add a new component to the list of grade components in the application.
 * The component includes a name, weightage, and maximum score.
 */
public class AddComponentCommand extends Command {

    public static final String[] ARGUMENT_PREFIXES = {"c/", "w/", "m/"};
    public static final String COMMAND_WORD = "add_component";
    private static final double MAX_SCORE = 10000.0;

    /**
     * Executes the add component command, creating a new component with the specified name,
     * weightage, and maximum score.
     *
     * @param appState The current state of the application.
     * @param hashmap  Contains the arguments passed to the command.
     * @return The result of executing the add component command.
     * @throws TutorLinkException If there is an error in processing the command, including invalid values.
     */
    @Override
    public CommandResult execute(AppState appState, HashMap<String, String> hashmap) throws TutorLinkException {
        String componentName = hashmap.get(ARGUMENT_PREFIXES[0]);
        String weightageNumber = hashmap.get(ARGUMENT_PREFIXES[1]);
        String maxScoreNumber = hashmap.get(ARGUMENT_PREFIXES[2]);

        validateArguments(componentName, weightageNumber, maxScoreNumber);

        int weightage = parseAndValidateWeightage(weightageNumber, appState);
        double maxScore = parseMaxScore(maxScoreNumber);

        addComponentToAppState(appState, componentName, weightage, maxScore);

        return new CommandResult(
                String.format(Commons.ADD_COMPONENT_SUCCESS, componentName, weightageNumber, maxScoreNumber));
    }

    /**
     * Validates that all required arguments are present.
     *
     * @param componentName   The name of the component.
     * @param weightageNumber The weightage of the component as a string.
     * @param maxScoreNumber  The maximum score of the component as a string.
     * @throws IllegalValueException If any argument is null.
     */
    private void validateArguments(String componentName, String weightageNumber, String maxScoreNumber)
            throws IllegalValueException {
        if (componentName == null || weightageNumber == null || maxScoreNumber == null) {
            List<String> nullParameters = new ArrayList<>();
            if (componentName == null) {
                nullParameters.add(ARGUMENT_PREFIXES[0]);
            }
            if (weightageNumber == null) {
                nullParameters.add(ARGUMENT_PREFIXES[1]);
            }
            if (maxScoreNumber == null) {
                nullParameters.add(ARGUMENT_PREFIXES[2]);
            }
            throw new IllegalValueException(String.format(Commons.ERROR_NULL,
                    String.join(", ", nullParameters)));
        }
    }

    /**
     * Parses the weightage from a string to an integer and validates it.
     * Also checks if adding this weightage would exceed the maximum allowed total weight.
     *
     * @param weightageNumber The weightage of the component as a string.
     * @param appState        The current state of the application to get total weight.
     * @return The validated weightage as an integer.
     * @throws InvalidWeightingException If the total weight exceeds the maximum allowed.
     * @throws IllegalValueException If the weightage is invalid.
     */
    private int parseAndValidateWeightage(String weightageNumber, AppState appState) throws TutorLinkException {
        int weightage = parseWeightage(weightageNumber);
        int totalWeight = weightage + appState.components.getTotalWeighting();

        if (totalWeight > Commons.MAX_WEIGHT) {
            throw new InvalidWeightingException(String.format(Commons.ERROR_INVALID_TOTAL_WEIGHTING, totalWeight));
        }

        return weightage;
    }

    /**
     * Adds the component with the specified attributes to the application's state and updates all
     * student percentage scores.
     *
     * @param appState       The current state of the application.
     * @param componentName  The name of the component.
     * @param weightage      The validated weightage of the component.
     * @param maxScore       The validated maximum score of the component.
     */
    private void addComponentToAppState(AppState appState, String componentName, int weightage, double maxScore) {
        Component component = new Component(componentName, maxScore, weightage);
        appState.components.addComponent(component);
        appState.updateAllStudentPercentageScores();
    }

    /**
     * Parses the weightage from a string to an integer and validates the value.
     *
     * @param weightageNumber The weightage of the component as a string.
     * @return The validated weightage as an integer.
     * @throws IllegalValueException If the weightage is not a valid integer within the allowed range.
     */
    private int parseWeightage(String weightageNumber) throws IllegalValueException {
        try {
            int weightage = Integer.parseInt(weightageNumber);
            if (weightage < 0 || weightage > 100) {
                throw new IllegalValueException(Commons.ERROR_INVALID_WEIGHTAGE);
            }
            return weightage;
        } catch (NumberFormatException e) {
            throw new IllegalValueException(Commons.ERROR_INVALID_WEIGHTAGE);
        }
    }

    /**
     * Parses the maximum score from a string to a double and validates the value.
     *
     * @param maxScoreNumber The maximum score of the component as a string.
     * @return The validated maximum score as a double.
     * @throws IllegalValueException If the maximum score is not a valid double or is negative.
     */
    private double parseMaxScore(String maxScoreNumber) throws IllegalValueException {
        try {
            double maxScore = Double.parseDouble(maxScoreNumber);
            if (maxScore < 0.0) {
                throw new IllegalValueException(Commons.ERROR_INVALID_MAX_SCORE);
            }

            if (maxScore > MAX_SCORE) {
                throw new IllegalValueException(Commons.ERROR_INVALID_MAX_SCORE);
            }
            
            return maxScore;
        } catch (NumberFormatException e) {
            throw new IllegalValueException(Commons.ERROR_INVALID_MAX_SCORE);
        }
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
