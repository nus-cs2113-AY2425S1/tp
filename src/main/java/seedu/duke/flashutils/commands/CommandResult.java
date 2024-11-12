package seedu.duke.flashutils.commands;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    // The feedback message displayed to user after command is executed
    public String feedbackToUser;

    /**
     * Construct a Command Result with specified feedback to user
     * 
     * @param feedbackToUser
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    /**
     * Gets feedbackToUser
     *
     * @return feedbackToUser
     */
    public String getFeedbackToUser() {
        return feedbackToUser;
    }

}
