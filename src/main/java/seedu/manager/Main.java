package seedu.manager;

import seedu.manager.ui.Ui;

public class Main {
    private static final Ui ui = new Ui();

    /**
     * Main entry-point for the EventManagerCLI application.
     */
    public static void main(String[] args) {
        ui.greetUser();
        ui.getCommands();
    }
}
