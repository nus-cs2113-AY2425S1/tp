package tutorlink.command;

import org.junit.jupiter.api.BeforeEach;

import tutorlink.appstate.AppState;

public class ListComponentCommandTest {

    private AppState appState;
    private ListComponentCommand command;

    @BeforeEach
    public void setUp() {
        appState = new AppState();
        command = new ListComponentCommand();
    }
}
