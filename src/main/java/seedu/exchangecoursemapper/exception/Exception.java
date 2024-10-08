package seedu.exchangecoursemapper.exception;

public class Exception extends Throwable {
    public static String fileReadError() {
        return "Error reading the file.";
    }
}
