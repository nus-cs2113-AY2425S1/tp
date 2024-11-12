import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.javaninja.Cli;
import seedu.javaninja.QuizGenerator;
import seedu.javaninja.Topic;
import seedu.javaninja.question.Mcq;
import seedu.javaninja.TopicManager;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuizGeneratorTest {

    private QuizGenerator quizSession;
    private TopicManager topicManager;
    private Cli cli;

    @BeforeEach
    public void setUp() {
        // Initialize with empty input; each test will set its own input for `Cli`.
        cli = new Cli(new ByteArrayInputStream("".getBytes()));
        topicManager = new TopicManager(cli);
        quizSession = new QuizGenerator(topicManager, cli);
    }

    // === Unit Tests ===

    @Test
    public void getTimeLimitInSeconds_withMinutesInput_returnsCorrectSeconds() {
        // Simulate user input for minutes (2 minutes)
        ByteArrayInputStream input = new ByteArrayInputStream("2\n".getBytes());
        cli = new Cli(input);  // Reinitialize Cli with specific input
        quizSession = new QuizGenerator(topicManager, cli); // Reinitialize QuizSession with updated Cli

        int timeLimit = quizSession.getTimeLimitInSecondsFromUser();
        assertEquals(120, timeLimit);  // 2 minutes should equal 120 seconds
    }

    @Test
    public void getTimeLimitInSeconds_withSecondsInput_returnsCorrectSeconds() {
        // Simulate user input for seconds (0 for minutes, 30 for seconds)
        ByteArrayInputStream input = new ByteArrayInputStream("0\n30\n".getBytes());
        cli = new Cli(input);  // Reinitialize Cli with specific input
        quizSession = new QuizGenerator(topicManager, cli); // Reinitialize QuizSession with updated Cli

        int timeLimit = quizSession.getTimeLimitInSecondsFromUser();
        assertEquals(30, timeLimit);  // Expecting 30 seconds directly
    }

    // === Integration Tests ===

    @Test
    public void startQuiz_withValidTopic_initializesQuizCorrectly() {
        // Setup a topic with questions
        Topic topic = new Topic("Java Basics");
        topic.addQuestion(new Mcq("What is Java?", "a",
            List.of("a) A programming language", "b) A type of coffee", "c) A car brand")));
        topicManager.addTopic(topic);

        // Simulate inputs for time limit and question limit
        // 1 minute, 1 question, answer 'b'
        ByteArrayInputStream input = new ByteArrayInputStream("1\n1\nb\n".getBytes());

        cli = new Cli(input);  // Reinitialize Cli with specific input
        quizSession = new QuizGenerator(topicManager, cli); // Reinitialize QuizSession with updated Cli
        String nameOfTopic = topic.getName();

        // Start quiz and verify initialization with questions
        quizSession.selectTimedQuiz(nameOfTopic);

        // Since startQuiz relies on topic's question count, check if the quiz initialized with 1 question
        // assertEquals(1, quizSession.getCurrentQuiz(topic, cli).getQuestionCount());
    }

    @Test
    public void getQuizScore_afterQuizCompletion_returnsCorrectScore() {
        // Setup a topic with a question
        Topic topic = new Topic("General Knowledge");
        topic.addQuestion(new Mcq("What is Java?", "a", List.of("a) A programming language", "b) A type of coffee")));
        topicManager.addTopic(topic);

        // Simulate user answers to complete the quiz (answer 'b', which is incorrect)
        // 1 minute, 1 question, answer 'b'
        ByteArrayInputStream input = new ByteArrayInputStream("1\n1\nb\n".getBytes());
        cli = new Cli(input);  // Reinitialize Cli with specific input
        quizSession = new QuizGenerator(topicManager, cli); // Reinitialize QuizSession with updated Cli

        // Start the quiz and verify score
        String nameOfTopic = topic.getName();
        quizSession.selectTimedQuiz(nameOfTopic);

        int expectedScore = 0;  // Incorrect answer, expect 0% score
        assertEquals(expectedScore, quizSession.getQuizScore());
    }
}
