package seedu.manager.ui;

import seedu.manager.common.Messages;

/**
 * Represents the program's user interface
 */
public class Ui {

    /**
     * Greets user upon program startup
     */
    public void greetUser() {
        System.out.println(Messages.WELCOME_MESSAGE);
    }
}
