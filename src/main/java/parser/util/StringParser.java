package parser.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class StringParser {

    public static int parseInteger(String intString){
        assert intString != null : "intString must not be null";
        if (intString.isEmpty()){
            throw new IllegalArgumentException("intString is empty.");
        }
        try{
            return Integer.parseInt(intString);
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("intString is not an integer.");
        }
    }

    public static int parseIndex(String indexString) {
        return parseInteger(indexString) - 1;
    }

    public static LocalDate parseDate(String dateString) {
        assert dateString != null && !dateString.trim().isEmpty() : "Date string must not be null or empty";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected format: dd-MM-yyyy. " +
                    "Error: " + e.getParsedString(), e);
        }
    }
}
