package seedu.duke.data.state;
import static seedu.duke.data.state.StateType.MAIN_STATE;

import static seedu.duke.data.state.StateType.MAIN_STATE;

public class State {
    private StateType state;

    public State(StateType state) {
        this.state = MAIN_STATE;
    }

    public StateType getState() {
        return state;
    }

    public void setState(StateType state) {
        this.state = state;
    }
}
