package seedu.duke;

import seedu.duke.ui.Ui;

public class Duke {
    private static final Ui ui = new Ui();

    /**
     * Main entry-point for the EventManagerCLI application.
     */
    public static void main(String[] args) {
        ui.greetUser();
    }
}
