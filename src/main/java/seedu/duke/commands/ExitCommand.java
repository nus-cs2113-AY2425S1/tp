package seedu.duke.commands;

/**
 * Represents the command that when executed, exits the program.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting program...";

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT);
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
