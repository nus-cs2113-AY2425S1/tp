//@@author Bev-low

package exceptions;

/**
 * Represents exceptions related to history operations in the application.
 */
public class HistoryExceptions extends BuffBuddyExceptions {

    /**
     * Constructs a new HistoryExceptions with the specified detail message.
     *
     * @param message The detail message for this exception.
     */
    public HistoryExceptions(String message) {
        super(message);
    }

    /**
     * Returns a HistoryExceptions indicating that a specified day does not exist.
     *
     * @return A {@code HistoryExceptions} with a message indicating that the specified day
     *         cannot be deleted because it does not exist.
     */
    public static HistoryExceptions dayNotFound() {
        return new HistoryExceptions("Day does not exist, cannot be deleted");
    }

    /**
     * Returns a HistoryExceptions indicating that an exercise name was not provided.
     *
     * @return A {@code HistoryExceptions} with a message indicating that the exercise name
     *         is missing and prompting the user to specify it.
     */
    public static HistoryExceptions exerciseNameNotFound() {
        return new HistoryExceptions("Exercise name not provided. Please specify the exercise to " +
                "view your personal best.");
    }
}

