package seedu.duke.flashutils.commands;

import seedu.duke.flashutils.types.FlashCardSet;

/**
 * Displays flashcards for specified flashcard set.
 */
public class ViewCommand extends Command {

    // Confirmation message to be displayed to user, with placeholder for flashCardSet details
    public static final String SUCCESS_MESSAGE = "All flashcards have been displayed for set: %1$s";
    public String currentModule;

    private FlashCardSet targetSet;

    /**
     * Constructs a ViewCommand with a specified module
     * @param module FlashCardSet to view
     */
    public ViewCommand(FlashCardSet module) {
        this.targetSet = module;
    }

    /**
     * Gets the module whose flashcards will be displayed
     */
    public void getModuleToView() {
        currentModule =  targetSet.getModuleName();
    }

    /**
     * Prints the command result, 
     * which includes the success message and the flashcards under that module
     */
    @Override
    public CommandResult execute() {
        getModuleToView(); 
        targetSet.viewFlashCards(currentModule);
        return new CommandResult(String.format(SUCCESS_MESSAGE, targetSet.getModuleName()));
    }

    /**
     * Gets the module to be displayed
     * @return The module to be displayed
     */
    public FlashCardSet getTargetSet() {
        return targetSet;
    }
}
