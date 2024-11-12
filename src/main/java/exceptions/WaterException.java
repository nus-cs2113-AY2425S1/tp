//@@author Bev-low

package exceptions;

/**
 * Represents exceptions related to water log operations in the application.
 */
public class WaterException extends BuffBuddyException {

    /**
     * Constructs a new {@code WaterExceptions} with the specified detail message.
     *
     * @param message The detail message for this exception.
     */
    public WaterException(String message) {
        super(message);
    }

    /**
     * Returns a WaterExceptions indicating that a specified water log does not exist.
     *
     * @return A {@code WaterExceptions} with a message indicating that the specified water log
     *         does not exist.
     */
    public static WaterException doesNotExist() {
        return new WaterException("Water log does not exist");
    }

    /**
     * Returns a WaterExceptions indicating that the water volume is less than 0.
     *
     * @return A {@code WaterExceptions} with a message indicating that the water volume \
     *                                  is less than 0.
     */
    public static WaterException volumeOutOfBounds() {
        return new WaterException("Water amount cannot be negative");
    }
}
