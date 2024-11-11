// @@author nirala-ts

package parser;

import exceptions.ParserException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static common.Utils.DATE_FORMAT;
import static common.Utils.NULL_INTEGER;
import static common.Utils.NULL_FLOAT;

/**
 * The {@code ParserUtils} class is a utility class containing common methods used across all parsing functions.
 * These methods handle tasks such as splitting arguments, parsing integers and floats, validating indices,
 * and formatting dates.
 */
public class ParserUtils {
    private static final Logger logger = Logger.getLogger(ParserUtils.class.getName());

    /**
     * Splits the argument string into the primary command and its arguments.
     *
     * @param argumentString The full argument string provided by the user.
     * @return A string array containing the command as the first element and the remaining arguments as the second.
     * @throws AssertionError if {@code argumentString} is null.
     */
    public static String[] splitArguments(String argumentString) {
        assert argumentString != null : "Argument string must not be null";

        String[] inputArguments = argumentString.split(" ", 2);
        String command = inputArguments[0].trim();
        String args = (inputArguments.length > 1) ? inputArguments[1].trim() : "";

        logger.log(Level.INFO, "Successfully split arguments. Command: {0}, Arguments: {1}",
                new Object[]{command, args});
        return new String[]{command, args};
    }

    /**
     * Trims the input string to remove leading and trailing whitespace.
     *
     * @param argumentString The string to trim.
     * @return The trimmed version of {@code argumentString}.
     * @throws ParserException if {@code argumentString} is empty after trimming.
     */
    static String trimInput(String argumentString) {
        assert argumentString != null : "Argument string must not be null";
        String trimmedString = argumentString.trim();

        if (trimmedString.isEmpty()){
            logger.log(Level.WARNING, "Trimmed input is empty");
            throw ParserException.missingArguments();
        }

        logger.log(Level.INFO, "Successfully trimmed input: {0}", trimmedString);
        return trimmedString;
    }

    /**
     * Parses a string as an integer, returning a default value if the string is null.
     *
     * @param intString The string to parse as an integer.
     * @return The parsed integer, or {@code NULL_INTEGER} if {@code intString} is null.
     * @throws ParserException if {@code intString} cannot be parsed as an integer.
     */
    public static int parseInteger(String intString){
        if (intString == null) {
            logger.log(Level.INFO, "Integer string is null. Returning default value: {0}", NULL_INTEGER);
            return NULL_INTEGER;
        }

        String trimmedIntString = trimInput(intString);
        int result;

        if (trimmedIntString.length() > 10) { // 10 digits is the maximum for Integer.MAX_VALUE (2,147,483,647)
            throw ParserException.infinityInt(trimmedIntString);
        }

        try{
            result = Integer.parseInt(trimmedIntString);
            logger.log(Level.INFO, "Successfully parsed integer: {0}", result);
        } catch (NumberFormatException e){
            logger.log(Level.WARNING, "Failed to parse integer from string: {0}", intString);
            throw ParserException.invalidInt(trimmedIntString);
        }

        if (result < 0){
            throw ParserException.invalidInt(trimmedIntString);
        }

        return result;
    }

    /**
     * Parses a string as a float, returning a default value if the string is null.
     *
     * @param floatString The string to parse as a float.
     * @return The parsed float, or {@code NULL_FLOAT} if {@code floatString} is null.
     * @throws ParserException if {@code floatString} cannot be parsed as a float.
     */
    public static float parseFloat(String floatString) {
        if (floatString == null) {
            logger.log(Level.INFO, "Float string is null. Returning default value: {0}", NULL_FLOAT);
            return NULL_FLOAT;
        }

        String trimmedFloatString = trimInput(floatString);
        float result;

        try {
            result = Float.parseFloat(trimmedFloatString);
            logger.log(Level.INFO, "Successfully parsed float: {0}", result);
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Failed to parse float from string: {0}", floatString);
            throw ParserException.invalidFloat(trimmedFloatString);
        }

        if (result == Double.POSITIVE_INFINITY) {
            throw ParserException.infinityFloat(trimmedFloatString);
        }

        if (result < 0){
            throw ParserException.invalidFloat(trimmedFloatString);
        }

        return result;
    }

    /**
     * Parses a string as an index, adjusting it to zero-based and returning a default if null.
     *
     * @param indexString The string to parse as an index.
     * @return The zero-based index, or {@code NULL_INTEGER} if {@code indexString} is null.
     * @throws ParserException if the index is less than zero.
     */
    public static int parseIndex(String indexString) {
        if (indexString == null) {
            logger.log(Level.INFO, "Index string is null. Returning default value: {0}", NULL_INTEGER);
            return NULL_INTEGER;
        }

        int index = parseInteger(indexString) - 1;
        if (index < 0){
            logger.log(Level.WARNING, "Invalid index: {0}. Index must be non-negative.", indexString);
            throw ParserException.indexOutOfBounds(indexString);
        }

        logger.log(Level.INFO, "Successfully parsed index: {0}", index);
        return index;
    }

    /**
     * Parses a string as a date using the specified date format. If null, returns the current date.
     *
     * @param dateString The string to parse as a date.
     * @return The parsed {@code LocalDate} object, or today's date if {@code dateString} is null.
     * @throws ParserException if the date format is invalid.
     */
    public static LocalDate parseDate(String dateString) {
        if (dateString == null || dateString.trim().isEmpty()) {
            LocalDate today = LocalDate.now();
            logger.log(Level.INFO, "Date string is null/empty. Returning current date: {0}", today);
            return today;
        }

        String trimmedDateString = trimInput(dateString);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

        try {
            LocalDate date = LocalDate.parse(trimmedDateString, formatter);
            
            String[] parts = trimmedDateString.split("-");
            int inputDay = Integer.parseInt(parts[0]);
            int inputMonth = Integer.parseInt(parts[1]);

            // Check if the parsed date matches the input values as LocalDate.parse
            // automatically adjusts invalid dates to the nearest valid one
            if (date.getDayOfMonth() != inputDay || date.getMonthValue() != inputMonth) {
                throw new DateTimeParseException("Invalid date: " + dateString, dateString, 0);
            }

            logger.log(Level.INFO, "Successfully parsed date: {0}", date);
            return date;
        } catch (DateTimeParseException e) {
            logger.log(Level.WARNING, "Invalid date format: {0}. Expected format: {1}",
                    new Object[]{dateString, DATE_FORMAT});
            throw ParserException.invalidDate(trimmedDateString);
        }
    }
}
