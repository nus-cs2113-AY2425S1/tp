//@@author Bev-low

package exceptions;

/**
 * Represents exceptions related to meal operations in the application.
 */
public class MealException extends BuffBuddyExceptions {

    /**
     * Constructs a new MealException with the specified detail message.
     *
     * @param message The detail message for this exception.
     */
    public MealException(String message) {
        super(message);
    }

    /**
     * Returns a MealException indicating that a specified meal does not exist.
     *
     * @return A {@code MealException} with a message indicating that the specified meal
     *         does not exist.
     */
    public static MealException doesNotExist() {
        return new MealException("Meal does not exist");
    }
}
