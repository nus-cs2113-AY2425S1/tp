package seedu.duke.flashutils.commands;


import seedu.duke.flashutils.types.Card;
import seedu.duke.flashutils.types.FlashBook;
import seedu.duke.flashutils.types.FlashCardSet;

/**
 * Removes flashcard from flashcard set.
 */
public class DeleteCommand extends Command {

    // Confirmation message to be displayed to user, with placeholder for flashcard details
    public static final String SUCCESS_MESSAGE = "Successfully deleted flashcard(s): %1$s\n";

    public static final int INDEX_OFFSET = 1;

    private Card targetCard;
    private FlashCardSet targetSet;

    /**
     * Constructs a Delete Command with specified module and card index
     * 
     * @param module FlashCardSet to perform DeleteCommand on
     * @param cardIndex Index of card to delete
     */
    public DeleteCommand(FlashCardSet module, int cardIndex) throws IndexOutOfBoundsException {
        targetSet = module;
        if (cardIndex > 0) {
            targetCard = targetSet.getCard(cardIndex - INDEX_OFFSET);
        } else {
            targetCard = null;
        }
    }

    /**
     * Gets the target module that has flashcard to be deleted
     * 
     * @return The module having the flashcard to be deleted
     */
    public FlashCardSet getTargetSet() {
        return targetSet;
    }

    /**
     * Gets the target card to be deleted 
     * @return The card to be deleted 
     */
    public Card getTargetCard() {
        return targetCard;
    }

    /**
     * Prints result of the command, 
     * which includes the success message and the {@code Card} or {@code FlashCardSet} to be deleted
     * 
     * @return The result of the command
     */
    @Override
    public CommandResult execute() {
        CommandResult deleteResult;
        if (targetCard != null) {
            targetSet.removeCard(targetCard);
            deleteResult = new CommandResult(String.format(SUCCESS_MESSAGE, targetCard));
        } else {
            FlashBook.getInstance().deleteFlashCardSet(targetSet.getModuleName());
            deleteResult = new CommandResult(String.format(SUCCESS_MESSAGE, targetSet.toString()));
        }
        return deleteResult;
    }
}
