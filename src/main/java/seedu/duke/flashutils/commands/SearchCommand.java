package seedu.duke.flashutils.commands;

import seedu.duke.flashutils.types.Card;
import seedu.duke.flashutils.types.FlashCardSet;

/**
 * Represents searching for flashcards which match the term
 */
public class SearchCommand extends Command {
    private String searchTerm;
    private final boolean byTopic;
    private final FlashCardSet targetSet;

    /**
     * Constructs a search command
     * @param searchTerm represents the term that all found cards must contain
     * @param byTopic represents whether the search term only checks topics
     * @param targetSet represents the module being searched from
     */
    public SearchCommand(String searchTerm, boolean byTopic, FlashCardSet targetSet) {
        this.searchTerm = searchTerm;
        this.byTopic = byTopic;
        this.targetSet = targetSet;
    }

    @Override
    public CommandResult execute() {
        StringBuilder matchingCards = new StringBuilder();
        searchTerm = searchTerm.toLowerCase();
        int counter = 0;
        for (Card card : targetSet) {
            if ((byTopic && card.getTopic().toLowerCase().contains(searchTerm))
                    || (card.getAnswer().toLowerCase().contains(searchTerm)
                    || card.getQuestion().toLowerCase().contains(searchTerm))) {
                matchingCards.append(++counter).append(". ").append(card).append("\n");
            }
        }
        if (matchingCards.isEmpty()) {
            return new CommandResult("No Cards found :(");
        } else {
            return new CommandResult(matchingCards.toString());
        }
    }
}
