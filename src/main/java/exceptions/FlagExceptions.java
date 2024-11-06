//@@author Bev-low

package exceptions;

/**
 * Represents exceptions related to flags in the application.
 */
public class FlagExceptions extends BuffBuddyExceptions {

    /**
     * Constructs a new FlagExceptions with the specified detail message.
     *
     * @param message The detail message for this exception.
     */
    public FlagExceptions(String message) {
        super(message);
    }

    /**
     * Returns a FlagExceptions indicating that a specific flag is missing.
     *
     * @param flag The name of the missing flag.
     * @return A {@code FlagExceptions} with a message indicating that the specified flag is not present.
     */
    public static FlagExceptions missingFlag(String flag) {
        return new FlagExceptions("Flag " + flag + " not present");
    }

    /**
     * Returns a FlagExceptions indicating that arguments are missing after a flag.
     *
     * @return A {@code FlagExceptions} with a message indicating that arguments are missing
     *         after the flag.
     */
    public static FlagExceptions missingArguments() {
        return new FlagExceptions("Missing arugments after flag, please refer to User Guide");
    }
}
