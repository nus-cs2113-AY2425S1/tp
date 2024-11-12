package seedu.main;

import seedu.command.Command;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class {@code Parser} is responsible for parsing user commands into {@code Command} objects.
 * It manages the registration of commands and the extraction of arguments for each command.
 *
 * @@author DanLinhHuynh-Niwashi-reuse
 * with minimal changes
 */
public class Parser {
    /** Map that associates command words with their corresponding {@code Command} objects */
    private static final Logger logger = Logger.getLogger("Parser");
    private Map<String, Command> commands = new LinkedHashMap<>();

    /**
     * Getter for the map of registered commands.
     *
     * @return A map of command words to {@code Command} objects.
     */
    public Map<String, Command> getCommands() {
        return commands;
    }

    /**
     * Registers a {@code Command} with its corresponding command word.
     *
     * @param command The {@code Command} object to be registered.
     */
    public void registerCommands(Command command) {
        String commandWord = null;
        try {
            commandWord = (String) command.getClass().getField("COMMAND_WORD").get(null);

            assert commandWord != null : "Command word should not be null.";

            logger.log(Level.INFO, "Adding..." + commandWord);
            commands.put(commandWord, command);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            // Ignored
        }
    }

    /**
     * Parses a command string into a {@code Command} and assign its arguments.
     *
     * @param commandPart The first word of the command string entered by the user.
     * @return The {@code Command} associated with the command string.
     */
    public Command parseCommand(String commandPart) {
        assert commandPart != null && !commandPart.trim().isEmpty() : "Command string should not be null or empty.";

        return commands.get(commandPart);
    }


    public Map<String, String> extractArguments (Command command, String argumentString) {
        assert command != null: "Command should not be null or empty.";

        Map<String, String> arguments = new HashMap<>();

        String[] keywords = command.getArgumentKeys();
        logger.log(Level.INFO, "Extracting arguments...");

        // Recursively split the command arguments
        splitCommandRecursively(argumentString.trim(), keywords, arguments, "");

        // Iterating through the map using entrySet()
        for (Map.Entry<String, String> entry : arguments.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            logger.log(Level.INFO, key + ": " + value);
        }
        return arguments;
    }

    /**
     * Recursively splits the argument string based on the provided keywords.
     *
     * @param argumentString The argument string to be split.
     * @param keywords The keywords to search for in the argument string.
     * @param arguments A map to store the extracted arguments.
     * @param prevKeyword The keyword found in the previous recursive call.
     */
    private static void splitCommandRecursively(String argumentString, String[] keywords,
                                                Map<String, String> arguments, String prevKeyword) {
        if (argumentString.isEmpty()) {
            return; // Base case: no more arguments to split
        }

        int keywordIndex = -1;
        String keywordFound = null;

        // Find the first keyword in the argument string
        for (String keyword : keywords) {
            if (keyword.isEmpty()) {
                continue;
            }
            int index = argumentString.indexOf(keyword + " ");
            if (index != -1 && (keywordIndex == -1 || index < keywordIndex)) {
                keywordIndex = index;
                keywordFound = keyword;
            }
        }

        if (keywordIndex != -1) {
            // If a keyword is found, extract the text before it
            String beforeKeyword = argumentString.substring(0, keywordIndex).trim();
            if (!beforeKeyword.isEmpty()) {
                arguments.put(prevKeyword, beforeKeyword);
            }

            // Get the remaining argument string after the keyword
            String afterKeyword = getAfterKeyword(argumentString, keywordIndex);

            // Create a new keywords array without the found keyword
            String finalKeywordFound = keywordFound;
            String[] updatedKeywords = Arrays.stream(keywords)
                    .filter(k -> !k.equals(finalKeywordFound))
                    .toArray(String[]::new);

            // Continue splitting recursively with the updated keywords
            splitCommandRecursively(afterKeyword, updatedKeywords, arguments, keywordFound);
        } else {
            // If no more keywords are found, assign the remaining argument
            arguments.put(prevKeyword, argumentString);
        }
    }

    /**
     * Retrieves the substring of the argument string that appears after the specified keyword index.
     *
     * @param argumentString The original argument string.
     * @param keywordIndex The index of the keyword found in the argument string.
     * @return The substring after the keyword.
     */
    private static String getAfterKeyword(String argumentString, int keywordIndex) {
        String afterKeyword = argumentString.substring(keywordIndex).trim();

        int nextSpaceIndex = afterKeyword.indexOf(' ');
        if (nextSpaceIndex != -1) {
            afterKeyword = afterKeyword.substring(nextSpaceIndex).trim();
        } else {
            afterKeyword = "";
        }

        return afterKeyword;
    }



}
