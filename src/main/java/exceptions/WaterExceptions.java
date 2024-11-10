//@@author Bev-low

package exceptions;

/**
 * Represents exceptions related to water log operations in the application.
 */
public class WaterExceptions extends BuffBuddyExceptions {

    /**
     * Constructs a new {@code WaterExceptions} with the specified detail message.
     *
     * @param message The detail message for this exception.
     */
    public WaterExceptions(String message) {
        super(message);
    }

    /**
     * Returns a WaterExceptions indicating that a specified water log does not exist.
     *
     * @return A {@code WaterExceptions} with a message indicating that the specified water log
     *         does not exist.
     */
    public static WaterExceptions doesNotExist() {
        return new WaterExceptions("Water log does not exist");
    }
}
