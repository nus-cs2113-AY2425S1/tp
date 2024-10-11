package seedu.duke.flashutils.commands;

import seedu.duke.flashutils.types.Card;
import seedu.duke.flashutils.types.FlashCardSet;

/**
 * Represents an executable command.
 */
public class Command {

    protected FlashCardSet targetSet;
    private int targetIndex = -1;

    public static final int DISPLAYED_INDEX_OFFSET = 1;

    // Constructors
    protected Command(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    protected Command() {
    }

    // Getters and Setters
    public FlashCardSet getTargetSet() {
        return targetSet;
    }

    public int getTargetIndex() {
        return targetIndex;
    }

    public void setTargetSet(FlashCardSet targetSet) {
        this.targetSet = targetSet;
    }

    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    public Card getTargetCard() throws IndexOutOfBoundsException {
       return targetSet.getFlashCardSet().get(getTargetIndex() - DISPLAYED_INDEX_OFFSET);
    }

    /**
     * Executes the command and returns the result.
     */
    public CommandResult execute() {
        // Message to be overwritten by child classes
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }

}
