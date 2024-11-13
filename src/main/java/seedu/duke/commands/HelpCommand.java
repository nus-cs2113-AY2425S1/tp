package seedu.duke.commands;

import seedu.duke.common.Messages;
import seedu.duke.data.state.State;
import seedu.duke.data.state.StateType;

/**
 * Represents the command that when executed, shows the help message.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    private State state;

    public HelpCommand(State state) {
        this.state = state;
    }

    @Override
    public CommandResult execute() {
        assert state != null : "State object should not be null";

        if (state.getState() == StateType.TASK_STATE) {
            return new CommandResult(Messages.MESSAGE_HELP_TASK);
        } else {
            return new CommandResult(Messages.MESSAGE_HELP_PATIENT);
        }
    }
}
