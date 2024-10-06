package seedu.manager.command;

/**
 *
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";

    public CommandOutput execute() {
        return new CommandOutput("Thank you for using EventManagerCLI. Goodbye!", true);
    }
}
