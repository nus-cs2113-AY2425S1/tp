//@@author Bev-low

package exceptions;

/**
 * Represents exceptions related to programme operations in the application.
 */
public class ProgrammeException extends BuffBuddyException {

    /**
     * Constructs a new ProgrammeExceptions with the specified detail message.
     *
     * @param message The detail message for this exception.
     */
    public ProgrammeException(String message) {
        super(message);
    }

    /**
     * Returns a ProgrammeExceptions indicating that a programme name is missing.
     *
     * @return A {@code ProgrammeExceptions} with a message indicating a missing programme name.
     */
    public static ProgrammeException programmeMissingName() {
        return new ProgrammeException("Programme is missing a name.");
    }

    /**
     * Returns a ProgrammeExceptions indicating that a programme list is empty.
     *
     * @return A {@code ProgrammeExceptions} with a message indicating an empty programme list
     */
    public static ProgrammeException programmeListEmpty() {
        return new ProgrammeException("Programme list is empty");
    }

    /**
     * Returns a ProgrammeExceptions indicating that tha programme has already been set to active.
     *
     * @return A {@code ProgrammeExceptions} with a message indicating the programme has already started.
     */
    public static ProgrammeException programmeAlreadyActive(int index) {
        return new ProgrammeException("Program " + (index + 1) + " has already been started");
    }

    /**
     * Returns a ProgrammeExceptions indicating that a day name is missing.
     *
     * @return A {@code ProgrammeExceptions} with a message indicating a missing day name.
     */
    public static ProgrammeException missingDayName() {
        return new ProgrammeException("Missing Day Name, please provide one.");
    }

    /**
     * Returns a {@code ProgrammeExceptions} indicating that a specified object
     * does not exist.
     *
     * @param name The name of the object that does not exist.
     * @return A {@code ProgrammeExceptions} with a message indicating that the specified
     *         object does not exist.
     */
    public static ProgrammeException doesNotExist(String name) {
        return new ProgrammeException(name + " does not exist.");
    }
}
