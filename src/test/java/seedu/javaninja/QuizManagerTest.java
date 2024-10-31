package seedu.javaninja;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.javaninja.question.Mcq;
import seedu.javaninja.question.TrueFalse;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

class QuizManagerTest {

    private QuizManager quizManager;
    private final String resultsFilePath = "data/results.txt";

    @BeforeEach
    public void setUp() {
        File file = new File(resultsFilePath);
        if (file.exists()) {
            file.delete();
        }
        quizManager = new QuizManager();
    }

    @Test
    public void selectTopic_invalidTopicName_displaysError() {
        // Prepare a scanner with simulated user input
        ByteArrayInputStream input = new ByteArrayInputStream("InvalidTopicName\n".getBytes());
        Scanner scanner = new Scanner(input);

        // Pass the scanner to the selectTopic method, including a default timer of 10 seconds
        quizManager.selectQuizToAttempt("InvalidTopicName");

        scanner.close();

        // Assert that past results remain empty since no valid quiz was selected
        assertEquals("No past results available. You haven't completed any quizzes yet.", quizManager.getQuizResults().getPastResults());

    }

    @Test
    public void getPastResults_withResults_returnsCorrectResults() {
        quizManager = new QuizManager();
        // Add a topic with a question
        Topic topic = new Topic("Java Basics");
        topic.addQuestion(new Mcq("What is Java?", "a",
                List.of("a) A programming language", "b) A type of coffee", "c) A car brand")));
        quizManager.getTopicManager().addTopic(topic);

        // Prepare simulated input for the quiz
        ByteArrayInputStream input = new ByteArrayInputStream("1\n1\nb\n".getBytes());
        Scanner scanner = new Scanner(input);

        // Start the quiz with the provided scanner
        int timeLimit = quizManager.getQuizSession().getTimeLimitInSeconds(scanner);
        int questionLimit = quizManager.getQuizSession().getQuestionLimit(scanner);

        quizManager.getQuizSession().getCurrentQuiz(topic, scanner).start(timeLimit, questionLimit);
        quizManager.addResultsAndPrintScore();

        String expectedResult = "Score: 0%, Comment: Better luck next time!\n";
        assertEquals(expectedResult, quizManager.getQuizResults().getPastResults());

        scanner.close();
    }

    @Test
    public void saveResults_savesToFileCorrectly() throws IOException {
        quizManager = new QuizManager();

        // Add a topic and question
        Topic topic = new Topic("Java Basics");
        topic.addQuestion(new Mcq("What is Java?", "a",
                List.of("a) A programming language", "b) A type of coffee", "c) A car brand")));

        quizManager.getTopicManager().addTopic(topic);

        // Simulate user input during the quiz
        // ByteArrayInputStream input = new ByteArrayInputStream("b\n".getBytes());
        ByteArrayInputStream input = new ByteArrayInputStream("1\n2\nb\n".getBytes());
        Scanner scanner = new Scanner(input);

        // Start the quiz and save the results
        int timeLimit = quizManager.getQuizSession().getTimeLimitInSeconds(scanner);
        int questionLimit = quizManager.getQuizSession().getQuestionLimit(scanner);

        quizManager.getQuizSession().getCurrentQuiz(topic, scanner).start(timeLimit, questionLimit);
        quizManager.addResultsAndPrintScore();
        quizManager.saveResults();

        String expectedSavedResults = "Score: 0%, Comment: Better luck next time!";
        Path resultsPath = Path.of(System.getProperty("user.dir"), resultsFilePath);
        String savedResults = Files.readString(resultsPath).trim();
        assertEquals(expectedSavedResults, savedResults);

        scanner.close();
    }

    @Test
    public void loadResultsFromFile_correctlyLoadsResults() throws IOException {
        // Simulate a previously saved result in the file
        String previousResult = "Score: 80%, Comment: Good job!\n";
        Files.writeString(Path.of(resultsFilePath), previousResult);

        // Reload the QuizManager to simulate restarting the program
        quizManager = new QuizManager();

        // Verify that the loaded result matches the expected value
        String loadedResults = quizManager.getQuizResults().getPastResults();
        assertEquals(previousResult, loadedResults);
    }

    @Test
    public void startQuiz_withTrueFalseQuestion_correctlyHandlesTrueFalse() {
        quizManager = new QuizManager();
        // Add a topic with a TrueFalse question
        Topic topic = new Topic("General Knowledge");
        topic.addQuestion(new TrueFalse("The Earth is flat.", false));

        quizManager.getTopicManager().addTopic(topic);

        // Prepare simulated input for the quiz (answering "false")
        ByteArrayInputStream input = new ByteArrayInputStream("1\n1\nfalse\n".getBytes());
        Scanner scanner = new Scanner(input);

        // Start the quiz and validate results
        int timeLimit = quizManager.getQuizSession().getTimeLimitInSeconds(scanner);
        int questionLimit = quizManager.getQuizSession().getQuestionLimit(scanner);

        quizManager.getQuizSession().getCurrentQuiz(topic, scanner).start(timeLimit, questionLimit);
        quizManager.addResultsAndPrintScore();

        String expectedResult = "Score: 100%, Comment: Excellent!\n";
        assertEquals(expectedResult, quizManager.getQuizResults().getPastResults());

        scanner.close();
    }

    @Test
    public void startQuiz_withInvalidTrueFalseAnswer_throwsException() {
        quizManager = new QuizManager();
        // Add a topic with a TrueFalse question
        Topic topic = new Topic("General Knowledge");
        topic.addQuestion(new TrueFalse("The Earth is flat.", false));

        quizManager.getTopicManager().addTopic(topic);

        // Prepare simulated input for the quiz (answering "yes", then "false" after invalid input)
        ByteArrayInputStream input = new ByteArrayInputStream("1\n1\nyes\nfalse\n".getBytes());
        Scanner scanner = new Scanner(input);

        // Start the quiz and validate results
        int timeLimit = quizManager.getQuizSession().getTimeLimitInSeconds(scanner);
        int questionLimit = quizManager.getQuizSession().getQuestionLimit(scanner);

        try {
            quizManager.getQuizSession().getCurrentQuiz(topic, scanner).start(timeLimit, questionLimit);

        } catch (IllegalArgumentException e) {
            assertEquals("Invalid input! Please enter 'true' or 'false'.", e.getMessage());
        }

        scanner.close();
    }
}
