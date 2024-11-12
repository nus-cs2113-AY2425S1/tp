package seedu.duke.flashutils.types;

import java.util.HashMap;

/**
 * Represents the complete list of flashcards
 */
public class FlashBook {

    private static FlashBook instance = new FlashBook();

    private final HashMap<String, FlashCardSet> allFlashCardSets;

    private FlashBook() {
        this.allFlashCardSets = new HashMap<>();
    }

    private FlashBook(HashMap<String, FlashCardSet> flashCards) {
        this.allFlashCardSets = flashCards;
    }

    public static FlashBook getInstance() {
        if (instance == null) {
            instance = new FlashBook();
        }
        return instance;
    }

    public static void setInstance(HashMap<String, FlashCardSet> flashCards) {
        instance = new FlashBook(flashCards);
    }

    public HashMap<String, FlashCardSet> getAllFlashCardSets() {
        return allFlashCardSets;
    }

    public void addFlashCardSet(String module) {
        allFlashCardSets.put(module, new FlashCardSet(module));
    }

    public FlashCardSet getFlashCardSet(String module) {
        if (allFlashCardSets.get(module) == null) {
            addFlashCardSet(module);
        }
        return allFlashCardSets.get(module);
    }
    public void deleteFlashCardSet(String module) {
        allFlashCardSets.remove(module);
    }

    public boolean flashCardSetExists(String module) {
        return allFlashCardSets.get(module) != null;
    }

}
