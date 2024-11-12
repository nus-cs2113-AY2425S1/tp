package seedu.duke.flashutils.commands;


/**
 * Represents an executable command.
 */
public abstract class Command {
    
    /**
     * Executes the command and returns the result.
     */
    public abstract CommandResult execute();
}
