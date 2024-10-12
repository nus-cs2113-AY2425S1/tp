package seedu.exchangecoursemapper.exception;

public class Exception extends Throwable {
    public Exception(String message) {
        super(message);
    }

    public static String fileReadError() {
        return "Error reading the file.";
    }

    public static String emptyUniversityName() {
        return "Please provide a University name.";
    }
}
