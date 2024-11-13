package seedu.duke.data.state;

public class State {
    private StateType currentStage;

    public State() {
        this.currentStage = StateType.MAIN_STATE;
    }

    public State(StateType state) {
        this.currentStage = state;
    }

    public StateType getState() {
        return currentStage;
    }

    public void setState(StateType state) {
        this.currentStage = state;
    }
}
