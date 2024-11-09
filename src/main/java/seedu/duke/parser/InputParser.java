package seedu.duke.parser;

import seedu.duke.exception.FinanceBuddyException;
import seedu.duke.util.Commons;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * The InputParser class is responsible for parsing user input into commands and arguments.
 * It takes a raw input string and splits it into meaningful components such as the command and its arguments.
 */
public class InputParser {
    public static final String COMMAND = "command";
    public static final String ARGUMENT = "argument";

    // Predefined list of valid arguments
    private static final Set<String> VALID_ARGUMENTS = new HashSet<>();

    static {
        VALID_ARGUMENTS.add("/des");
        VALID_ARGUMENTS.add("/a");
        VALID_ARGUMENTS.add("/d");
        VALID_ARGUMENTS.add("/c");
        VALID_ARGUMENTS.add("/from");
        VALID_ARGUMENTS.add("/to");
    }

    /**
     * Parses the user's input into a command and associated arguments.
     * The first word in the input is considered the command, and the rest is treated as arguments.
     * Arguments that start with "/" are treated as named arguments.
     *
     * @param input The raw input string from the user.
     * @return A HashMap containing the parsed command and its arguments.
     */
    public static HashMap<String, String> parseCommands(String input) throws FinanceBuddyException {
        HashMap<String, String> commandArguments = new HashMap<>();
        String[] splitInput = input.trim().split(" ");

        // Check if input is empty
        if (splitInput.length == 0 || splitInput[0].isEmpty()) {
            commandArguments.put(COMMAND, "");
            return commandArguments;
        }

        // Set first element as command
        commandArguments.put(COMMAND, splitInput[0]);

        // Parse remaining input into arguments
        parseArguments(splitInput, commandArguments);

        return commandArguments;
    }

    /**
     * Helper method to parse the arguments from the input array.
     */
    private static void parseArguments(String[] splitInput, HashMap<String, String> commandArguments)
            throws FinanceBuddyException {
        String currentKey = ARGUMENT;
        StringBuilder currentValue = new StringBuilder();

        for (int i = 1; i < splitInput.length; i++) {
            String token = splitInput[i];

            if (token.startsWith("/")) {
                // Validate the argument
                if (!VALID_ARGUMENTS.contains(token)) {
                    final String message = token + " : " + Commons.ERROR_MESSAGE_INVALID_ARGUMENT;
                    throw new FinanceBuddyException(message);
                }

                // Check if the previous argument has no description
                if (currentValue.length() == 0 && !currentKey.equals(ARGUMENT)) {
                    final String message = currentKey + " : " + Commons.ERROR_MESSAGE_ARGUMENT_NULL;
                    throw new FinanceBuddyException(message);
                }

                // If we already have a value for the previous argument, add it
                if (currentValue.length() > 0) {
                    addArgument(commandArguments, currentKey, currentValue.toString().strip());
                }

                // Update currentKey to the new argument and reset the currentValue
                currentKey = token;
                currentValue.setLength(0);
            } else {
                // Append the current token to the currentValue
                if (currentValue.length() > 0) {
                    currentValue.append(" ");
                }
                currentValue.append(token);
            }
        }

        // Add the last argument after the loop
        if (currentValue.length() == 0 && !currentKey.equals(ARGUMENT)) {
            final String message = currentKey + " : " + Commons.ERROR_MESSAGE_ARGUMENT_NULL;
            throw new FinanceBuddyException(message);
        }

        if (currentValue.length() > 0) {
            addArgument(commandArguments, currentKey, currentValue.toString().strip());
        }
    }

    /**
     * Adds an argument to the map after validating it is not empty.
     */
    private static void addArgument(HashMap<String, String> commandArguments, String key, String value)
            throws FinanceBuddyException {
        if (!value.isEmpty()) {
            commandArguments.put(key, value);
        } else {
            throw new FinanceBuddyException(Commons.ERROR_MESSAGE_ARGUMENT_NULL);
        }
    }
}
