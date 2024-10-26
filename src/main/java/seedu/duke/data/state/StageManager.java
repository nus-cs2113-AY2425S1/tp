package seedu.duke.data.state;

public class StageManager {
    private State currentState;

    public StageManager() {
        this.currentState = new State();
        assert currentState != null : "Current state should not be null.";
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public void changeState(StateType state) {
        currentState.setState(state);
    }

    public StateType getState() {
        return currentState.getState();
    }


}
