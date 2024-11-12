package seedu.budgetbuddy.commands;

import seedu.budgetbuddy.Ui;

/**
 * Represents the base class for all commands in the BudgetBuddy application.
 * This class provides a default execute method, which is meant to be overridden by child classes.
 */
public class Command {

    /**
     * Executes the command.
     * The default implementation displays a message indicating that the method should be implemented by child classes.
     * Child classes should override this method to define specific command behaviors.
     */
    public void execute() {
        Ui.displayToUser("to be implemented by child classes");
    }
}
