package seedu.javaninja;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuizManagerTest {

    private QuizManager quizManager;

    @BeforeEach
    public void setUp() {
        quizManager = new QuizManager();
    }

    @Test
    public void selectTopic_invalidTopicName_displaysError() {
        // Test selecting a topic that doesn't exist
        quizManager.selectTopic("InvalidTopicName");
    }

    @Test
    public void addTopic_validTopic_addsSuccessfully() {
        quizManager.addTopic(new Topic("Java Basics"));

        assertEquals(2, quizManager.getTopicsCount());
    }

    @Test
    public void removeTopic_existingTopic_removesSuccessfully() {
        Topic topic = new Topic("Java Basics");
        quizManager.addTopic(topic);

        // Remove the topic
        quizManager.removeTopic(topic);

        // Check if the topic was successfully removed
        assertEquals(1, quizManager.getTopicsCount());
    }

    @Test
    public void getPastResults_withResults_returnsCorrectResults() {
        // Adding a topic and simulating a quiz
        Topic topic = new Topic("Java Basics");
        quizManager.addTopic(topic);
        quizManager.startQuiz(topic);

        // Check if the past results contain the quiz score and comment
        String pastResults = quizManager.getPastResults();
        // Assuming 0 score and corresponding comment for simplicity
        String expectedResult = "Score: 0%, Comment: Better luck next time!\n";

        assertEquals(expectedResult, pastResults);
    }

}

