package seedu.duke.parser;

import seedu.duke.exception.FinanceBuddyException;
import seedu.duke.util.Commons;

import java.util.HashMap;
import java.util.Set;

/**
 * Responsible for parsing user input into commands and arguments.
 */
public class InputParser {

    public static final String COMMAND = "command";
    public static final String ARGUMENT = "argument";

    // Predefined valid arguments
    private static final Set<String> VALID_ARGUMENTS = Set.of(
            Commons.FLAG_DESCRIPTION,
            Commons.FLAG_AMOUNT,
            Commons.FLAG_DATE,
            Commons.FLAG_CATEGORY,
            Commons.FLAG_START_POINT,
            Commons.FLAG_END_POINT
    );

    /**
     * Parses the user's input into a command and associated arguments.
     *
     * @param input The raw input string from the user.
     * @return A HashMap containing the parsed command and its arguments.
     * @throws FinanceBuddyException if the input contains invalid arguments or missing values.
     */
    public static HashMap<String, String> parseCommands(String input) throws FinanceBuddyException {
        HashMap<String, String> commandArguments = new HashMap<>();
        String[] tokens = input.trim().split("\\s+");

        if (tokens.length == 0) {
            commandArguments.put(COMMAND, "");
            return commandArguments;
        }

        commandArguments.put(COMMAND, tokens[0]); // First word is the command
        processArguments(tokens, commandArguments);
        return commandArguments;
    }

    /**
     * Processes arguments from the input tokens and populates the commandArguments map.
     *
     * @param tokens           The input split into tokens.
     * @param commandArguments The map to store parsed arguments.
     * @throws FinanceBuddyException if invalid or missing arguments are detected.
     */
    private static void processArguments(String[] tokens, HashMap<String, String> commandArguments)
            throws FinanceBuddyException {

        String currentKey = ARGUMENT;
        StringBuilder currentValue = new StringBuilder();

        for (int i = 1; i < tokens.length; i++) {
            String token = tokens[i];

            if (isArgument(token)) {
                handleNewArgument(commandArguments, currentKey, currentValue);
                currentKey = validateArgument(token);
            } else {
                appendValue(currentValue, token);
            }
        }

        // Add the last argument
        handleNewArgument(commandArguments, currentKey, currentValue);
    }

    /**
     * Validates and processes a new argument.
     *
     * @param token The token to validate.
     * @return The validated argument token.
     * @throws FinanceBuddyException if the argument is invalid.
     */
    private static String validateArgument(String token) throws FinanceBuddyException {
        if (!VALID_ARGUMENTS.contains(token)) {
            throw new FinanceBuddyException(token + " : " + Commons.ERROR_MESSAGE_INVALID_ARGUMENT);
        }
        return token;
    }

    /**
     * Handles the end of an argument-value pair and adds it to the map.
     *
     * @param commandArguments The map to store parsed arguments.
     * @param key              The current argument key.
     * @param value            The current argument value.
     * @throws FinanceBuddyException if the value is empty for a named argument.
     */
    private static void handleNewArgument(HashMap<String, String> commandArguments, String key,
                                          StringBuilder value) throws FinanceBuddyException {
        if (value.length() == 0 && !key.equals(ARGUMENT)) {
            throw new FinanceBuddyException(key + " : " + Commons.ERROR_MESSAGE_ARGUMENT_NULL);
        }
        if (value.length() > 0) {
            commandArguments.put(key, value.toString().strip());
            value.setLength(0);
        }
    }

    /**
     * Appends a token to the current value being built.
     *
     * @param value The current argument value.
     * @param token The token to append.
     */
    private static void appendValue(StringBuilder value, String token) {
        if (value.length() > 0) {
            value.append(" ");
        }
        value.append(token);
    }

    /**
     * Checks if a token is an argument key (starts with "/").
     *
     * @param token The token to check.
     * @return True if the token is an argument key, false otherwise.
     */
    private static boolean isArgument(String token) {
        return token.startsWith("/");
    }
}
