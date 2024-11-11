//@@author Bev-low

package exceptions;

import programme.Day;

/**
 * Represents exceptions related to history operations in the application.
 */
public class HistoryException extends BuffBuddyException {

    /**
     * Constructs a new HistoryExceptions with the specified detail message.
     *
     * @param message The detail message for this exception.
     */
    public HistoryException(String message) {
        super(message);
    }

    /**
     * Returns a HistoryExceptions indicating that a specified day does not exist.
     *
     * @return A {@code HistoryExceptions} with a message indicating that the specified day
     *         cannot be deleted because it does not exist.
     */
    public static HistoryException dayNotFound() {
        return new HistoryException("Day does not exist, cannot be deleted");
    }

    /**
     * Returns a HistoryExceptions indicating that an exercise name was not provided.
     *
     * @return A {@code HistoryExceptions} with a message indicating that the exercise name
     *         is missing and prompting the user to specify it.
     */
    public static HistoryException exerciseNameNotFound() {
        return new HistoryException("Exercise name not provided. Please specify the exercise to " +
                "view your personal best.");
    }

    public static HistoryException existingDay(Day existingDay) {
        return new HistoryException("A record already exists for this date. Please delete the current day entry if " +
                "you wish to make any changes.");
    }
}

