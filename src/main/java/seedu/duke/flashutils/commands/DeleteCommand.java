package seedu.duke.flashutils.commands;

/**
 * Removes flashcard from flashcard set.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    // Confirmation message to be displayed to user, with placeholder for flashcard details
    public static final String SUCCESS_MESSAGE = "Successfully deleted flashcard: %1$s";

}
