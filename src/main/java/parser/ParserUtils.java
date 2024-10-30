package parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static common.Utils.DATE_FORMAT;
import static common.Utils.NULL_INTEGER;
import static common.Utils.NULL_FLOAT;


/*
    ParserUtils is a utility class containing common methods used across all parsing functions
 */

public class ParserUtils {

    public static String[] splitArguments(String argumentString) {
        assert argumentString != null : "Argument string must not be null";
        String[] inputArguments = argumentString.split(" ", 2);
        String command = inputArguments[0];
        String args = (inputArguments.length > 1) ? inputArguments[1] : "";
        return new String[]{command, args};
    }

    private static String trimInput(String argumentString) {
        assert argumentString != null : "Argument string must not be null";
        String trimmedString = argumentString.trim();

        if (trimmedString.isEmpty()){
            throw new IllegalArgumentException("intString is empty.");
        }

        return trimmedString;
    }

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

    public static int parseIndex(String indexString) {
        if (indexString == null) {
            return NULL_INTEGER;
        }

        int index = parseInteger(indexString) - 1;
        if (index < 0){
            throw new IllegalArgumentException("index must not be negative.");
        }
        return index;
    }

    public static LocalDate parseDate(String dateString) {
        if (dateString == null || dateString.trim().isEmpty()) {
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
