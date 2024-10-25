package seedu.duke;

import seedu.duke.ui.AppUi;

import seedu.duke.storage.Storage;

public class FinanceBuddy {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        AppUi ui = new AppUi();
        Storage storage = new Storage();
        ui.setStorage(storage);
        ui.run();
    }
}
