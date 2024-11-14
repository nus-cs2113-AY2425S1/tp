package tutorlink.result;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private static final String LINE_SEPARATOR = "\n\t";

    public final String message;

    public CommandResult(String message) {
        this.message = message;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return the message string
     */
    @Override
    public String toString() {
        return message;
    }
}
