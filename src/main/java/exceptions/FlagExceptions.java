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
     * Returns a FlagExceptions indicating that arguments are missing
     *
     * @return A {@code FlagExceptions} with a message indicating that arguments are missing
     *         after the flag.
     */
    public static FlagExceptions missingArguments() {
        return new FlagExceptions("Missing arguments.");
    }

    /**
     * Returns a FlagExceptions indicating that arguments are missing after a flag.
     *
     * @return A {@code FlagExceptions} with a message indicating that arguments are missing
     *         after the flag.
     */
    public static FlagExceptions missingRequiredArguments(String flag) {
        return new FlagExceptions("Missing required arguments after flag: " + flag + ".");
    }

    /**
     * Returns a FlagException indicating that a specific flag has been provided more than once
     * @param flag The name of the duplicate flag.
     * @return A {@code FlagExceptions} with a message indicating that the specified flag has been duplicated.
     */

    public static FlagExceptions duplicateFlag(String flag) {
        return new FlagExceptions("Flag " + flag + " was provided more than once.");
    }

    /**
     * Returns a FlagException indicating that a specific flag has been provided more than once
     * @param flag The name of the duplicate flag.
     * @return A {@code FlagExceptions} with a message indicating that the specified flag has been duplicated.
     */

    public static FlagExceptions invalidFlag(String flag) {
        return new FlagExceptions("Flag " + flag + " is not recognized.");
    }

    /**
     * Returns a FlagException indicating that more than one unique flag was provided
     * @param flags The list of clashing unique flags.
     * @return A {@code FlagExceptions} with a message indicating that more than one unique flag was provided.
     */
    public static FlagExceptions nonUniqueFlag(String flags) {
        return new FlagExceptions("Flags " + flags + "cannot be provided in the same command.");
    }
}
