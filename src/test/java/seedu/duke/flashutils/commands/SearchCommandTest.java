
package seedu.duke.flashutils.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.flashutils.types.Card;
import seedu.duke.flashutils.types.FlashCardSet;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchCommandTest {
    private FlashCardSet targetSet;

    @BeforeEach
    public void setUp() {
        targetSet = new FlashCardSet("TestModule", new ArrayList<Card>());
        // Adding sample cards to the target set
        targetSet.addCard(new Card("What is Java?", "A programming language", "Programming"));
        targetSet.addCard(new Card("What is Python?", "A programming language", "Programming"));
        targetSet.addCard(new Card("What is OOP?", "A programming paradigm"));
        targetSet.addCard(new Card("What is AI?", "Artificial Intelligence", "Technology"));
    }

    @Test
    public void testSearchCommand_noMatchingCards() {
        // Search for a term that does not exist in any card
        SearchCommand command = new SearchCommand("Blockchain", false, targetSet);
        CommandResult result = command.execute();

        assertEquals("No Cards found :(", result.feedbackToUser);
    }

    @Test
    public void testSearchCommand_someMatchingCards() {
        // Search for a term that matches answers and questions
        SearchCommand command = new SearchCommand("programming", false, targetSet);
        CommandResult result = command.execute();

        String expectedResult = """
                1. What is Java? : A programming language (Topic: Programming)
                2. What is Python? : A programming language (Topic: Programming)
                3. What is OOP? : A programming paradigm
                """;
        assertEquals(expectedResult, result.feedbackToUser);
    }

    @Test
    public void testSearchCommand_matchesByTopic() {
        // Search by topic
        SearchCommand command = new SearchCommand("Technology", true, targetSet);
        CommandResult result = command.execute();

        String expectedResult = "1. What is AI? : Artificial Intelligence (Topic: Technology)\n";
        assertEquals(expectedResult, result.feedbackToUser);
    }

    @Test
    public void testSearchCommand_caseInsensitiveSearch() {
        // Search should be case-insensitive
        SearchCommand command = new SearchCommand("pRogramming", false, targetSet);
        CommandResult result = command.execute();

        String expectedResult = """
                1. What is Java? : A programming language (Topic: Programming)
                2. What is Python? : A programming language (Topic: Programming)
                3. What is OOP? : A programming paradigm
                """;
        assertEquals(expectedResult, result.feedbackToUser);
    }

    @Test
    public void testSearchCommand_emptyTargetSet() {
        // Search in an empty set
        FlashCardSet emptySet = new FlashCardSet("empty");  // Empty the target set
        SearchCommand command = new SearchCommand("Java", false, emptySet);
        CommandResult result = command.execute();

        assertEquals("No Cards found :(", result.feedbackToUser);
    }
}
