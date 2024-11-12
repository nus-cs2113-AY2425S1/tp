package seedu.javaninja;

/**
 * Custom exception class for the Java Ninja application.
 * This class is used to handle application-specific exceptions and provides a consistent
 * way to generate error messages across various components of the application.
 */
public class JavaNinjaException extends Exception {

    /**
     * Constructs a new JavaNinjaException with a specified error message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public JavaNinjaException(String message) {
        super(message);
    }
}
