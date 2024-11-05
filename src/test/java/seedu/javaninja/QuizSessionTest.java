import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.javaninja.QuizSession;
import seedu.javaninja.question.Mcq;
import seedu.javaninja.Topic;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuizSessionTest {

    private QuizSession quizSession;

    @BeforeEach
    public void setUp() {
        quizSession = new QuizSession();
    }

    // === Unit Tests ===

    @Test
    public void getTimeLimitInSeconds_withMinutesInput_returnsCorrectSeconds() {
        // Simulate user input for minutes
        ByteArrayInputStream input = new ByteArrayInputStream("2\n".getBytes());  // 2 minutes
        Scanner scanner = new Scanner(input);

        int timeLimit = quizSession.getTimeLimitInSeconds(scanner);
        assertEquals(120, timeLimit);  // Expecting 2 minutes = 120 seconds
    }

    @Test
    public void getTimeLimitInSeconds_withSecondsInput_returnsCorrectSeconds() {
        // Simulate user input for seconds directly
        ByteArrayInputStream input = new ByteArrayInputStream("0\n30\n".getBytes());  // 30 seconds
        Scanner scanner = new Scanner(input);

        int timeLimit = quizSession.getTimeLimitInSeconds(scanner);
        assertEquals(30, timeLimit);  // Expecting 30 seconds directly
    }

    @Test
    public void getQuestionLimit_withValidInput_returnsCorrectLimit() {
        // Simulate user input for number of questions
        ByteArrayInputStream input = new ByteArrayInputStream("5\n".getBytes());  // 5 questions
        Scanner scanner = new Scanner(input);

        int questionLimit = quizSession.getQuestionLimit(scanner);
        assertEquals(5, questionLimit);  // Expecting question limit of 5
    }

    // === Integration Tests ===

    @Test
    public void startQuiz_withValidTopic_initializesQuizCorrectly() {
        // Setup a topic with questions
        Topic topic = new Topic("Java Basics");
        topic.addQuestion(new Mcq("What is Java?", "a",
            List.of("a) A programming language", "b) A type of coffee", "c) A car brand")));

        // Simulate inputs for time limit and question limit
        ByteArrayInputStream input = new ByteArrayInputStream("1\n1\nb".getBytes());  // 1 minute, 1 question
        Scanner scanner = new Scanner(input);

        // Start the quiz and verify that the quiz has initialized with questions
        int timeLimit = quizSession.getTimeLimitInSeconds(scanner);
        int questionLimit = quizSession.getQuestionLimit(scanner);

        quizSession.getCurrentQuiz(topic, scanner).start(timeLimit, questionLimit);

        assertEquals(1, quizSession.getCurrentQuiz(topic, scanner).getQuestionCount());
    }

    @Test
    public void getQuizScore_afterQuizCompletion_returnsCorrectScore() {
        // Setup a topic and add it to a quiz
        Topic topic = new Topic("General Knowledge");
        topic.addQuestion(new Mcq("What is Java?", "a", List.of("a) A programming language", "b) A type of coffee")));

        // Simulate user answers to complete the quiz
        ByteArrayInputStream input = new ByteArrayInputStream("1\n2\nb".getBytes());  // Incorrect answer
        Scanner scanner = new Scanner(input);

        // Start the quiz and verify that the quiz has initialized with questions
        int timeLimit = quizSession.getTimeLimitInSeconds(scanner);
        int questionLimit = quizSession.getQuestionLimit(scanner);

        quizSession.getCurrentQuiz(topic, scanner).start(timeLimit, questionLimit);

        // Assuming a quiz scoring method that calculates based on correctness
        int expectedScore = 0;  // Incorrect answer, expect 0% score
        assertEquals(expectedScore, quizSession.getQuizScore());
    }
}

