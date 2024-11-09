import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.javaninja.Cli;
import seedu.javaninja.Topic;
import seedu.javaninja.TopicManager;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Unit tests for the `TopicManager` class.
 * Tests various methods for managing topics, adding flashcards, and loading questions.
 */
class TopicManagerTest {

    private TopicManager topicManager;  // Instance of `TopicManager` for testing
    private Cli cli;  // CLI instance to simulate user interaction

    /**
     * Sets up the test environment by initializing `TopicManager` and a simulated `Cli` instance.
     */
    @BeforeEach
    public void setUp() {
        String simulatedUserInput = "Sample input\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());

        cli = new Cli(inputStream);
        topicManager = new TopicManager(cli);
    }

    /**
     * Tests if `getOrCreateTopic()` creates a new topic if it doesn't exist.
     */
    @Test
    public void getOrCreateTopic_createsNewTopicIfNotExists() {
        Topic topic = topicManager.getOrCreateTopic("New Topic");
        assertNotNull(topic, "Topic should be created if it does not exist.");
        assertEquals("New Topic", topic.getName(), "Topic name should match the input name.");
    }

    /**
     * Tests if `getOrCreateTopic()` returns an existing topic when requested.
     */
    @Test
    public void getOrCreateTopic_returnsExistingTopic() {
        Topic topic = new Topic("Existing Topic");
        topicManager.addTopic(topic);

        Topic retrievedTopic = topicManager.getOrCreateTopic("Existing Topic");
        assertEquals(topic, retrievedTopic, "getOrCreateTopic should return the existing topic.");
    }

    /**
     * Tests if `getTopic()` returns null when a topic does not exist.
     */
    @Test
    public void getTopic_returnsNullIfNotExists() {
        Topic topic = topicManager.getTopic("NonExistentTopic");
        assertEquals(null, topic, "getTopic should return null for a non-existent topic.");
    }

    /**
     * Tests if `addTopic()` successfully adds a new topic.
     */
    @Test
    public void addTopic_addsNewTopic() {
        Topic topic = new Topic("Java Basics");
        topicManager.addTopic(topic);

        List<String> topicNames = topicManager.getTopicNames();
        assertTrue(topicNames.contains("Java Basics"), "TopicManager should contain the newly added topic.");
    }

    /**
     * Tests if `addTopic()` does not add a duplicate topic.
     */
    @Test
    public void addTopic_doesNotAddDuplicateTopic() {
        Topic topic = new Topic("Duplicate Topic");
        topicManager.addTopic(topic);
        topicManager.addTopic(topic); // Attempt to add the same topic again

        long topicCount = topicManager.getTopicNames().stream().filter(name -> name.equals("Duplicate Topic")).count();
        assertEquals(1, topicCount, "Duplicate topics should not be added.");
    }

    /**
     * Tests if `addFlashcardByUser()` successfully adds a flashcard with valid input.
     */
    @Test
    public void addFlashcardByUser_validInput_addsFlashcard() {
        String input = "add Flashcards /q What is Java? /a A programming language";
        topicManager.addFlashcardByUser(input);

        Topic flashcardTopic = topicManager.getTopic("Flashcards");
        assertNotNull(flashcardTopic, "Flashcards topic should exist after adding a flashcard.");
        assertTrue(flashcardTopic.getQuestions().stream()
                        .anyMatch(q -> q.getText().equals("What is Java?")),
                "Flashcards topic should contain the added question.");
    }

    /**
     * Tests if `addFlashcardByUser()` does not add an incomplete flashcard with invalid input.
     */
    @Test
    public void addFlashcardByUser_invalidInput_doesNotAddFlashcard() {
        String input = "add Flashcards /q What is Java?"; // Missing answer part
        topicManager.addFlashcardByUser(input);

        Topic flashcardTopic = topicManager.getTopic("Flashcards");
        if (flashcardTopic != null) {
            assertFalse(flashcardTopic.getQuestions().stream()
                            .anyMatch(q -> q.getText().equals("What is Java?")),
                    "Flashcards topic should not contain the incomplete question.");
        }
    }

    /**
     * Tests if `loadQuestions()` parses and adds questions from storage.
     */
    @Test
    public void loadQuestions_parsesAndAddsQuestions() {
        topicManager.loadQuestions();
        List<String> topicNames = topicManager.getTopicNames();
        assertFalse(topicNames.isEmpty(), "Topics should be loaded and added.");
    }

    /**
     * Tests if `loadFlashcards()` parses and adds flashcards from storage.
     */
    @Test
    public void loadFlashcards_parsesAndAddsFlashcards() {
        topicManager.loadFlashcards();
        Topic flashcardTopic = topicManager.getTopic("Flashcards");
        assertNotNull(flashcardTopic, "Flashcards topic should be loaded and added.");
    }
}
