package seedu.manager.command;

/**
 * Represents an executable command
 */
public abstract class Command {

    /**
     * Returns the output of the executable command
     */
    public abstract CommandOutput execute();
}
