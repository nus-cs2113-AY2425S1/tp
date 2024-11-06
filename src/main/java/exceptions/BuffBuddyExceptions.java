// @@author andreusxcarvalho

package exceptions;

/**
 * BuffBuddyException serves as the base exception class for BuffBuddy.
 * All custom exceptions in BuffBuddy should inherit from this class to
 * allow unified handling of exceptions.
 */
public class BuffBuddyExceptions extends RuntimeException {

    /**
     * Default constructor with a generic error message.
     */
    public BuffBuddyExceptions() {
        super("An error occurred in BuffBuddy.");
    }

    /**
     * Constructor that accepts a custom error message.
     * @param message Custom error message describing the exception.
     */
    public BuffBuddyExceptions(String message) {
        super(message);
    }

    
    
    /**
     * Constructor that accepts a custom error message and a throwable cause.
     * This is useful for wrapping other exceptions in BuffBuddy-specific exceptions.
     * @param message Custom error message describing the exception.
     * @param cause The cause of the exception. 
     */
    public BuffBuddyExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}


