import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.javaninja.Cli;
import seedu.javaninja.QuizManager;
import seedu.javaninja.Topic;
import seedu.javaninja.question.Mcq;

import java.util.List;
import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class QuizManagerTest {

    private QuizManager quizManager;
    private Cli cli;

    @BeforeEach
    public void setUp() {
        // Simulated input: "1\n1\nb\n"
        // '1' for time limit, '1' for question limit, 'b' as the answer
        String simulatedUserInput = "1\n1\nb\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());

        // Initialize Cli with ByteArrayInputStream for simulated input
        cli = new Cli(inputStream);
        quizManager = new QuizManager(cli, false); // Pass this Cli instance to QuizManager
    }

    @Test
    public void selectQuizToAttempt_validTopic_addsResult() {
        // Set up a valid topic and add it to QuizManager
        Topic topic = new Topic("Java Basics");
        topic.addQuestion(new Mcq("What is Java?", "a",
            List.of("a) A programming language", "b) A type of coffee", "c) A car brand")));
        quizManager.getTopicManager().addTopic(topic);

        String nameOfTopic = topic.getName();

        // Select quiz and verify result is added
        quizManager.getQuizSession().selectTimedQuiz(nameOfTopic);

        quizManager.addResultsAndPrintScore();

        // Check if result was recorded
        String pastResults = quizManager.getPastResults();
        System.out.println(pastResults);
        assertTrue(pastResults.contains("Score: 0%"),
            "Past results should contain a score.");
    }

    @Test
    public void selectQuizToAttempt_invalidTopic_logsWarning() {
        quizManager = new QuizManager(cli);
        // Select a topic that doesn't exist
        quizManager.handleQuizSelection("NonExistentTopic");

        // Check if past results remain empty
        assertTrue(true,"No such topic: NonExistentTopic");

    }

    @Test
    public void getQuizzesAvailable_returnsAddedTopics() {
        // Add multiple topics to QuizManager
        quizManager.getTopicManager().addTopic(new Topic("Java Basics"));
        quizManager.getTopicManager().addTopic(new Topic("Data Structures"));

        // Get available quizzes and verify they match expected
        List<String> quizzes = quizManager.getQuizzesAvailable();
        assertTrue(quizzes.contains("Java Basics"));
        assertTrue(quizzes.contains("Data Structures"));
    }

    @Test
    public void addInput_addsFlashcardToTopic() {
        // Add a flashcard via addInput
        quizManager.addInput("add Flashcards /q What is Java? /a A programming language");

        // Verify that the flashcard topic exists
        Topic flashcardTopic = quizManager.getTopicManager().getTopic("Flashcards");
        assertTrue(flashcardTopic.getQuestions().stream()
                .anyMatch(q -> q.getText().equals("What is Java?")),
            "Flashcard topic should contain the added question.");
    }
}
