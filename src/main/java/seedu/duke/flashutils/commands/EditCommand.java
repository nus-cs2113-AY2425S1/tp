package seedu.duke.flashutils.commands;

import seedu.duke.flashutils.types.Card;
import seedu.duke.flashutils.types.FlashCardSet;

import static seedu.duke.flashutils.utils.Ui.getRequest;
import static seedu.duke.flashutils.utils.Ui.displayConfirmationQuestion;
import static seedu.duke.flashutils.utils.Ui.displayOldStoredValue;
import static seedu.duke.flashutils.utils.Ui.displayGetNewPromptFromUser;

/**
 * Updates information in an existing flashcard.
 */
public class EditCommand extends Command {

    // Confirmation message to be displayed to user, with placeholder for flashcard details
    public static final String SUCCESS_MESSAGE = "Successfully edited flashcard";

    public static final int INDEX_OFFSET = 1;

    private Card cardToEdit;
    private Card newCard;
    private FlashCardSet targetSet;

    /**
     * Constructs an Edit Command with specified module, index, new question and new answer
     * 
     * @param module FlashCardSet to perform EditCommand on
     * @param cardIndex Index of card to edit
     * @param newQuestion String to replace question of Card
     * @param newAnswer String to replace answer of Card
     */
    public EditCommand(FlashCardSet module, int cardIndex, String newQuestion, String newAnswer) {
        this.targetSet = module;
        this.cardToEdit = targetSet.getCard(cardIndex  - INDEX_OFFSET);
        this.newCard = new Card(newQuestion, newAnswer);
    }

    /**
     * Construct an Edit Command with specified module and index
     * The new question and answer will be collected from user later on
     * 
     * @param module FlashCardSet to perform EditCommand on
     * @param cardIndex Index of card to edit
     */
    public EditCommand(FlashCardSet module, int cardIndex) throws IndexOutOfBoundsException {
        this.targetSet = module;
        this.cardToEdit = targetSet.getCard(cardIndex  - INDEX_OFFSET);
        this.newCard = getUpdatedQuestionAnswerFromUser(cardToEdit);
    }

    protected Card getUpdatedQuestionAnswerFromUser(Card cardToEdit) {
        this.newCard = new Card(cardToEdit.getQuestion(), cardToEdit.getAnswer());

        // Update question
        String updatedQuestion = updateField(cardToEdit.getQuestion(), "Question");
        newCard.setQuestion(updatedQuestion);

        // Update answer
        String updatedAnswer = updateField(cardToEdit.getAnswer(), "Answer");
        newCard.setAnswer(updatedAnswer);

        return newCard;
    }

    /**
     * Prompts the user to confirm if they want to update the specified field.
     * If the user confirms, it will prompt for new input until valid data is entered.
     *
     * @param oldFieldValue the current value of the field
     * @param fieldName the name of the field (e.g., "Question" or "Answer")
     * @return the updated value entered by the user
     */
    private String updateField(String oldFieldValue, String fieldName) {
        displayOldStoredValue(fieldName, oldFieldValue);
        displayConfirmationQuestion(fieldName);

        String confirmation = getRequest();

        if (confirmation.equalsIgnoreCase("y")) {
            String newFieldValue;
            do {
                displayGetNewPromptFromUser(fieldName);
                newFieldValue = getRequest();

                // Check if the new value is empty
                if (newFieldValue == null || newFieldValue.trim().isEmpty()) {
                    System.out.println(fieldName + " cannot be empty. Please try again.");
                }
            } while (newFieldValue == null || newFieldValue.trim().isEmpty());

            return newFieldValue;
        } else if (confirmation.equalsIgnoreCase("n")) {
            System.out.println("Alright, Noted! Using Old " + fieldName);
            return oldFieldValue; // Return the old value if the user opts not to change it
        } else {
            System.out.println("Sorry! Unknown option entered. Using Old " + fieldName);
            return oldFieldValue; // Return the old value in case of an unknown option
        }
    }

    /**
     * Prints result of the command, 
     * which includes the success message and the Card to be edited
     * 
     * @return The result of the command
     */
    @Override
    public CommandResult execute() {
        cardToEdit.setQuestion(newCard.getQuestion());
        cardToEdit.setAnswer(newCard.getAnswer());
        return new CommandResult(SUCCESS_MESSAGE);
    }

    /**
     * Gets the module that has the card to be edited
     * 
     * @return The module having the card to be edited
     */
    public FlashCardSet getTargetSet() {
        return targetSet;
    }

    /**
     * Gets the card to be edited
     * 
     * @return The card to be edited
     */
    public Card getCardToAdd() {
        return cardToEdit;
    }

    /**
     * Gets the updated card
     *
     * @return The updated card
     */
    public Card getNewCard() {
        return newCard;
    }
}
