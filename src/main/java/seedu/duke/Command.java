package seedu.duke;

/**
 * The abstract base class for all command types.
 * Defines the structure of commands in the application.
 */
public abstract class Command {

    /**
     * Executes the command with the provided financial list.
     *
     * @param list The financial list on which the command will operate.
     */
    public abstract void execute(FinancialList list);

    /**
     * Determines if the command will terminate the application.
     *
     * @return false by default, as most commands do not terminate the application.
     */
    public boolean isExit() {
        return false;
    }
}
