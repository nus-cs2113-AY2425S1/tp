package seedu.exchangecoursemapper.exception;

public class Exception extends Throwable {
    public Exception(String message) {
        super(message);
    }

    public static String fileReadError() {
        return "Error reading the file.";
    }
}
