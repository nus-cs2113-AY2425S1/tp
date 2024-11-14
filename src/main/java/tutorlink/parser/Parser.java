package tutorlink.parser;

import tutorlink.command.AddStudentCommand;
import tutorlink.command.Command;
import tutorlink.command.DeleteComponentCommand;
import tutorlink.command.DeleteGradeCommand;
import tutorlink.command.DeleteStudentCommand;
import tutorlink.command.ExitCommand;
import tutorlink.command.FindStudentCommand;
import tutorlink.command.InvalidCommand;
import tutorlink.command.ListComponentCommand;
import tutorlink.command.ListGradeCommand;
import tutorlink.command.ListStudentCommand;
import tutorlink.command.AddGradeCommand;
import tutorlink.command.AddComponentCommand;
import tutorlink.command.UpdateComponentCommand;
import tutorlink.command.HelpCommand;
import tutorlink.exceptions.IllegalValueException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code Parser} class is responsible for parsing user input into commands and arguments.
 * It interprets the first word of the input as the command and maps it to a corresponding {@code Command} object.
 * It also provides a utility for extracting command arguments based on specific prefixes.
 */
public class Parser {
    private static final Logger LOGGER = Logger.getLogger(Parser.class.getName());
    private static final String ERROR_PARSER_MULTIPLE_PREFIX = "Duplicate prefix detected: ";

    /**
     * Extracts the command word from the given input by splitting the input
     * and taking the first word.
     *
     * @param input The user input as a string.
     * @return The command word extracted from the input.
     */
    private String extractCommandWord(String input) {
        String[] words = input.trim().split("\\s+");
        return words[0]; // Return the first word
    }

    /**
     * Parses the user input and returns the corresponding {@code Command} object.
     * It identifies the command by the first word of the input and maps it to a specific command class.
     * If the command is invalid, it returns an {@code InvalidCommand}.
     *
     * @param line The user input line containing the command and arguments.
     * @return The {@code Command} object representing the parsed command.
     */
    public Command getCommand(String line) {
        String commandWord = extractCommandWord(line);

        switch (commandWord.toLowerCase()) {
        case AddStudentCommand.COMMAND_WORD:
            return new AddStudentCommand();

        case DeleteStudentCommand.COMMAND_WORD:
            return new DeleteStudentCommand();

        case FindStudentCommand.COMMAND_WORD:
            return new FindStudentCommand();

        case ListStudentCommand.COMMAND_WORD:
            return new ListStudentCommand();

        case AddGradeCommand.COMMAND_WORD:
            return new AddGradeCommand();

        case DeleteGradeCommand.COMMAND_WORD:
            return new DeleteGradeCommand();

        case AddComponentCommand.COMMAND_WORD:
            return new AddComponentCommand();

        case DeleteComponentCommand.COMMAND_WORD:
            return new DeleteComponentCommand();

        case ListComponentCommand.COMMAND_WORD:
            return new ListComponentCommand();

        case ListGradeCommand.COMMAND_WORD:
            return new ListGradeCommand();

        case UpdateComponentCommand.COMMAND_WORD:
            return new UpdateComponentCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            return new InvalidCommand();
        }
    }

    /**
     * Extracts command arguments from the input string based on the given argument prefixes.
     * Arguments are identified by specific prefixes (e.g., "n/" for name, "i/" for ID)
     * and are extracted into a map where each prefix is a key and the corresponding argument is the value.
     *
     * @param argumentPrefixes An array of valid argument prefixes (e.g., "n/", "i/").
     * @param line             The user input containing command arguments.
     * @return A {@code HashMap} where keys are prefixes (e.g., "n/", "i/") and values are the corresponding arguments.
     */
    public HashMap<String, String> getArguments(String[] argumentPrefixes, String line) throws IllegalValueException {
        HashMap<String, String> arguments = new HashMap<>();

        if (argumentPrefixes == null) {
            return arguments;
        }

        // Build regex pattern dynamically from the provided argument prefixes
        StringBuilder regexBuilder = new StringBuilder("(?i)(");
        for (String prefix : argumentPrefixes) {
            regexBuilder.append(Pattern.quote(prefix)).append("|");
        }
        regexBuilder.setLength(regexBuilder.length() - 1); // Remove the last "|"
        // Match argument that could have spaces, but ends before another prefix or end of the string
        regexBuilder.append(")([^\\s].*?)(?=(\\s+[^\\s]+/|$))");

        String regex = regexBuilder.toString();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        // Set to track prefixes we've already encountered
        Set<String> seenPrefixes = new HashSet<>();
        // Iterate through all found tags and arguments
        while (matcher.find()) {
            String tag = matcher.group(1); // Group 1 is the tag (e.g., n/, i/, etc.)
            String argument = matcher.group(2).trim(); // Group 2 is the argument after the tag
            if (seenPrefixes.contains(tag)) {
                throw new IllegalValueException(ERROR_PARSER_MULTIPLE_PREFIX + tag);
            }
            seenPrefixes.add(tag);
            arguments.put(tag, argument); // Store the tag and argument
        }

        return arguments;
    }
}
