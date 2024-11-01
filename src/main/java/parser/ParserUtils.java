package parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static common.Utils.DATE_FORMAT;
import static common.Utils.NULL_INTEGER;
import static common.Utils.NULL_FLOAT;

/**
 * The {@code ParserUtils} class is a utility class containing common methods used across all parsing functions.
 * These methods handle tasks such as splitting arguments, parsing integers and floats, validating indices,
 * and formatting dates.
 */
public class ParserUtils {

    /**
     * Splits the argument string into the primary command and its arguments.
     *
     * @author nirala-ts
     * @param argumentString The full argument string provided by the user.
     * @return A string array containing the command as the first element and the remaining arguments as the second.
     * @throws AssertionError if {@code argumentString} is null.
     */
    public static String[] splitArguments(String argumentString) {
        assert argumentString != null : "Argument string must not be null";

        String[] inputArguments = argumentString.split(" ", 2);
        String command = inputArguments[0];
        String args = (inputArguments.length > 1) ? inputArguments[1] : "";
        return new String[]{command, args};
    }

    /**
     * Trims the input string to remove leading and trailing whitespace.
     *
     * @author nirala-ts
     * @param argumentString The string to trim.
     * @return The trimmed version of {@code argumentString}.
     * @throws IllegalArgumentException if {@code argumentString} is empty after trimming.
     */
    private static String trimInput(String argumentString) {
        assert argumentString != null : "Argument string must not be null";
        String trimmedString = argumentString.trim();

        if (trimmedString.isEmpty()){
            throw new IllegalArgumentException("intString is empty.");
        }

        return trimmedString;
    }

    /**
     * Parses a string as an integer, returning a default value if the string is null.
     *
     * @author nirala-ts
     * @param intString The string to parse as an integer.
     * @return The parsed integer, or {@code NULL_INTEGER} if {@code intString} is null.
     * @throws IllegalArgumentException if {@code intString} cannot be parsed as an integer.
     */
    public static int parseInteger(String intString){
        if (intString == null) {
            return NULL_INTEGER;
        }

        String trimmedIntString = trimInput(intString);

        try{
            return Integer.parseInt(trimmedIntString);
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("intString is not an integer.");
        }
    }

    /**
     * Parses a string as a float, returning a default value if the string is null.
     *
     * @author nirala-ts
     * @param floatString The string to parse as a float.
     * @return The parsed float, or {@code NULL_FLOAT} if {@code floatString} is null.
     * @throws IllegalArgumentException if {@code floatString} cannot be parsed as a float.
     */
    public static float parseFloat(String floatString) {
        if (floatString == null) {
            return NULL_FLOAT;
        }

        String trimmedFloatString = trimInput(floatString);

        try {
            return Float.parseFloat(trimmedFloatString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("floatString is not a valid float.");
        }
    }

    /**
     * Parses a string as an index, adjusting it to zero-based and returning a default if null.
     *
     * @author nirala-ts
     * @param indexString The string to parse as an index.
     * @return The zero-based index, or {@code NULL_INTEGER} if {@code indexString} is null.
     * @throws IllegalArgumentException if the index is less than zero.
     */
    public static int parseIndex(String indexString) {
        if (indexString == null) {
            return NULL_INTEGER;
        }

        int index = parseInteger(indexString) - 1;
        if (index < 0){
            throw new IllegalArgumentException("Index: " + indexString +" is not a valid index.");
        }
        return index;
    }

    /**
     * Parses a string as a date using the specified date format. If null, returns the current date.
     *
     * @author nirala-ts
     * @param dateString The string to parse as a date.
     * @return The parsed {@code LocalDate} object, or today's date if {@code dateString} is null.
     * @throws IllegalArgumentException if the date format is invalid.
     */
    public static LocalDate parseDate(String dateString) {
        if (dateString == null) {
            return LocalDate.now();
        }

        String trimmedDateString = trimInput(dateString);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

        try {
            return LocalDate.parse(trimmedDateString, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected format: dd-MM-yyyy. " +
                    "Error: " + e.getParsedString(), e);
        }
    }
}
