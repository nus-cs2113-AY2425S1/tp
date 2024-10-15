package seedu.duke.data.state;

public class State {
    private StateType state;

    public State(StateType state) {
        this.state = state;
    }

    public StateType getState() {
        return state;
    }

    public void setState(StateType state) {
        this.state = state;
    }
}

