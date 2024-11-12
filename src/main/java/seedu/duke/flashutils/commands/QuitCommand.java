package seedu.duke.flashutils.commands;

/**
 * Terminates the program.
 */
public class QuitCommand extends Command {
    
    /**
     * Prints result of the command, 
     * which includes the success message
     * 
     * @return The result of the command
     */
    @Override
    public CommandResult execute() {
        return new CommandResult("Quit Flash Session");
    }
}
