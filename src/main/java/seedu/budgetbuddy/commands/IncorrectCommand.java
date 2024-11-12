package seedu.budgetbuddy.commands;

import seedu.budgetbuddy.Ui;

/**
 * Represents a command that provides feedback to the user when an incorrect command is entered.
 * This command displays an appropriate message to inform the user of the error.
 */
public class IncorrectCommand extends Command {
    public final String feedbackToUser;

    /**
     * Constructs an IncorrectCommand object with the specified feedback message.
     *
     * @param feedbackToUser The message to be displayed to the user when the command is executed.
     */
    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    /**
     * Executes the incorrect command by displaying the feedback message to the user.
     */
    @Override
    public void execute () {
        Ui.displayToUser(feedbackToUser);
    }

    /**
     * Retrieves the feedback message for the user.
     *
     * @return The feedback message as a String.
     */
    public String getFeedbackToUser() {
        return feedbackToUser;
    }
}
