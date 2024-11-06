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
     * Returns a ProgrammeExceptions indicating that the programme edit command
     * is missing required flags.
     *
     * @return A {@code ProgrammeExceptions} with a message indicating missing flags.
     */
    public static ProgrammeExceptions programmeEditMissingFlags() {
        return new ProgrammeExceptions("Programme edit command is missing required flags. " +
                "Please refer to the user guide for details.");
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
