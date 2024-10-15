package seedu.duke.commands;

import seedu.duke.data.state.State;
import seedu.duke.data.state.StateType;

public class BackCommand extends Command {
    private State state;

    public BackCommand(State state) {
        this.state = state;
    }

    @Override
    public CommandResult execute() {
        // Change state to MAIN_STATE if currently in TASK_STATE
        if (state.getState() == StateType.TASK_STATE) {
            state.setState(StateType.MAIN_STATE);
            System.out.println("Switched back to MAIN_STATE.");
            return new CommandResult("Switched back to MAIN_STATE.");
        } else {
            System.out.println("Already in MAIN_STATE.");
            return new CommandResult("Already in MAIN_STATE.");
        }
    }
}
