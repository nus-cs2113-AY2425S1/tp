// @@author andreusxcarvalho

package common;

import exceptions.ParserException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static final int NULL_INTEGER = -1;

    public static final float NULL_FLOAT = -1.0f;

    public static final String DATE_FORMAT = "dd-MM-yyyy";

    public static boolean isNull(int val) {
        return val == -1;
    }

    public static boolean isNull(String val) {
        return val == null || val.isEmpty();
    }

    public static String formatDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return date.format(formatter);
    }

    public static void validate(int integer) {
        if (integer < 0){
            throw ParserException.invalidInt(integer);
        }
    }

    public static void validate(float number) {
        if (number == Double.POSITIVE_INFINITY) {
            throw ParserException.infinityFloat(number);
        } else if (number < 0){
            throw ParserException.invalidFloat(number);
        }
    }

    public static void validate(String string) {
        if (string == null || string.trim().isEmpty()) {
            throw ParserException.invalidString(string);
        }
    }

    public static void validate(LocalDate localDate) {
        if(localDate == null){
            throw ParserException.invalidDate();
        }
    }
}
