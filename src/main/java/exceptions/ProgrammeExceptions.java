//@@author Bev-low

package exceptions;

/**
 * Represents exceptions related to programme operations in the application.
 */
public class ProgrammeExceptions extends BuffBuddyExceptions {

    /**
     * Constructs a new ProgrammeExceptions with the specified detail message.
     *
     * @param message The detail message for this exception.
     */
    public ProgrammeExceptions(String message) {
        super(message);
    }

    /**
     * Returns a ProgrammeExceptions indicating that a programme name is missing.
     *
     * @return A {@code ProgrammeExceptions} with a message indicating a missing programme name.
     */
    public static ProgrammeExceptions programmeMissingName() {
        return new ProgrammeExceptions("Programme is missing a name.");
    }

    /**
     * Returns a ProgrammeExceptions indicating that a programme list is empty.
     *
     * @return A {@code ProgrammeExceptions} with a message indicating an empty programme list
     */
    public static ProgrammeExceptions programmeListEmpty() {
        return new ProgrammeExceptions("Programme list is empty");
    }

    /**
     * Returns a ProgrammeExceptions indicating that tha programme has already been set to active.
     *
     * @return A {@code ProgrammeExceptions} with a message indicating the programme has already started.
     */
    public static ProgrammeExceptions programmeAlreadyActive(int index) {
        return new ProgrammeExceptions("Program " + (index + 1) + " has already been started");
    }

    /**
     * Returns a ProgrammeExceptions indicating that a day name is missing.
     *
     * @return A {@code ProgrammeExceptions} with a message indicating a missing day name.
     */
    public static ProgrammeExceptions missingDayName() {
        return new ProgrammeExceptions("Missing Day Name, please provide one.");
    }

    /**
     * Returns a {@code ProgrammeExceptions} indicating that a specified object
     * does not exist.
     *
     * @param name The name of the object that does not exist.
     * @return A {@code ProgrammeExceptions} with a message indicating that the specified
     *         object does not exist.
     */
    public static ProgrammeExceptions doesNotExist(String name) {
        return new ProgrammeExceptions(name + " does not exist.");
    }
}
