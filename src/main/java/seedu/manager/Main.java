package seedu.manager;

import seedu.manager.event.EventList;
import seedu.manager.ui.Ui;

public class Main {
    private static final Ui ui = new Ui();
    private static final EventList events = new EventList();

    /**
     * Main entry-point for the EventManagerCLI application.
     */
    public static void main(String[] args) {
        ui.greetUser();
        ui.getCommands();
    }
}
