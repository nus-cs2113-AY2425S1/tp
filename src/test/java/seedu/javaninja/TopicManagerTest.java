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

class TopicManagerTest {

    private TopicManager topicManager;
    private Cli cli;

    @BeforeEach
    public void setUp() {
        String simulatedUserInput = "Sample input\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());

        cli = new Cli(inputStream);
        topicManager = new TopicManager(cli);
    }

    @Test
    public void getOrCreateTopic_createsNewTopicIfNotExists() {
        Topic topic = topicManager.getOrCreateTopic("New Topic");
        assertNotNull(topic, "Topic should be created if it does not exist.");
        assertEquals("New Topic", topic.getName(), "Topic name should match the input name.");
    }

    @Test
    public void getOrCreateTopic_returnsExistingTopic() {
        Topic topic = new Topic("Existing Topic");
        topicManager.addTopic(topic);

        Topic retrievedTopic = topicManager.getOrCreateTopic("Existing Topic");
        assertEquals(topic, retrievedTopic, "getOrCreateTopic should return the existing topic.");
    }

    @Test
    public void getTopic_returnsNullIfNotExists() {
        Topic topic = topicManager.getTopic("NonExistentTopic");
        assertEquals(null, topic, "getTopic should return null for a non-existent topic.");
    }

    @Test
    public void addTopic_addsNewTopic() {
        Topic topic = new Topic("Java Basics");
        topicManager.addTopic(topic);

        List<String> topicNames = topicManager.getTopicNames();
        assertTrue(topicNames.contains("Java Basics"), "TopicManager should contain the newly added topic.");
    }

    @Test
    public void addTopic_doesNotAddDuplicateTopic() {
        Topic topic = new Topic("Duplicate Topic");
        topicManager.addTopic(topic);
        topicManager.addTopic(topic); // Add the same topic again

        long topicCount = topicManager.getTopicNames().stream().filter(name -> name.equals("Duplicate Topic")).count();
        assertEquals(1, topicCount, "Duplicate topics should not be added.");
    }

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

    @Test
    public void addFlashcardByUser_invalidInput_doesNotAddFlashcard() {
        String input = "add Flashcards /q What is Java?";
        topicManager.addFlashcardByUser(input); // Missing "/a" for the answer

        Topic flashcardTopic = topicManager.getTopic("Flashcards");
        if (flashcardTopic != null) {
            assertFalse(flashcardTopic.getQuestions().stream()
                            .anyMatch(q -> q.getText().equals("What is Java?")),
                    "Flashcards topic should not contain the incomplete question.");
        }
    }

    @Test
    public void loadQuestions_parsesAndAddsQuestions() {
        topicManager.loadQuestions();
        List<String> topicNames = topicManager.getTopicNames();
        assertFalse(topicNames.isEmpty(), "Topics should be loaded and added.");
    }

    @Test
    public void loadFlashcards_parsesAndAddsFlashcards() {
        topicManager.loadFlashcards();
        Topic flashcardTopic = topicManager.getTopic("Flashcards");
        assertNotNull(flashcardTopic, "Flashcards topic should be loaded and added.");
    }
}
