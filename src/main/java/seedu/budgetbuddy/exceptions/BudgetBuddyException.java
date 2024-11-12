package seedu.budgetbuddy.exceptions;

import seedu.budgetbuddy.Ui;

/**
 * Represents a custom exception for errors specific to BudgetBuddy application.
 */
public class BudgetBuddyException extends Exception {

    /**
     * Constructs a new BudgetBuddyException with the specified detail message.
     */
    public BudgetBuddyException(String message) {
        super(Ui.SEPARATOR + "\n" + message + "\n" + Ui.SEPARATOR);
    }
}
