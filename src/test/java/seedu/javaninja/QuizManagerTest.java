package seedu.javaninja;

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
}

