package wheresmymoney;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import wheresmymoney.exception.WheresMyMoneyException;

public class DateUtils {
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    
    public static boolean isInDateFormat(String dateAsString) {
        try {
            LocalDate.parse(dateAsString, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }
    public static LocalDate stringToDateFormat(String dateAsString) throws WheresMyMoneyException {
        try {
            return LocalDate.parse(dateAsString, formatter);
        } catch (DateTimeParseException e) {
            throw new WheresMyMoneyException("Date string is not in the correct format: " + DATE_FORMAT);
        }
    }
    public static String dateFormatToString(LocalDate date) {
        return date.format(formatter);
    }
}
