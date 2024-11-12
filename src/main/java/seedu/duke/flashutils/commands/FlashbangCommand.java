package seedu.duke.flashutils.commands;

import seedu.duke.flashutils.types.FlashCardSet;

/**
 * Starts a FlashBang session, where questions for each flashcard are displayed
 * and users can choose to display answers.
 */
public class FlashbangCommand extends Command {
    // Confirmation message to be displayed to user, with placeholder for flashcard details
    public static final String SUCCESS_MESSAGE = "Successful FlashBang for flashcard set: %1$s";
    private final FlashCardSet targetSet;
    private long timerThreshold;


    /**
     * Constructs the Flashbang Command with specified target set (module)
     * 
     * @param targetSet represents the FlashCardSet to be tested on
     */
    public FlashbangCommand(FlashCardSet targetSet) {
        this.targetSet = targetSet;
    }
    
    public FlashbangCommand(FlashCardSet targetSet, long timerThreshold) {
        this.targetSet = targetSet;
        this.timerThreshold = timerThreshold;
    }

    public long getTimerThreshold() {
        return timerThreshold;
    }

    /**
     * Prints result of the command, 
     * which includes the success message and the module to be displayed
     * 
     * @return The result of the command
     */
    @Override
    public CommandResult execute() {
        targetSet.performFlashBang(timerThreshold);
        return new CommandResult(String.format(SUCCESS_MESSAGE, targetSet.getModuleName()));
    }
      
    public FlashCardSet getTargetSet() {
        return targetSet;
    }
}
