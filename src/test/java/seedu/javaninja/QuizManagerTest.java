package seedu.javaninja;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

class QuizManagerTest {

    private QuizManager quizManager;
    private final String RESULTS_FILE_PATH = "data/results.txt";

    @BeforeEach
    public void setUp() {
        File file = new File(RESULTS_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
        quizManager = new QuizManager();
        quizManager.addTopic(new Topic("Default Topic"));
    }

    @Test
    public void selectTopic_invalidTopicName_displaysError() {
        // Prepare a scanner with simulated user input
        ByteArrayInputStream input = new ByteArrayInputStream("InvalidTopicName\n".getBytes());
        Scanner scanner = new Scanner(input);

        // Pass the scanner to the selectTopic method
        quizManager.selectTopic("InvalidTopicName", scanner);

        scanner.close();
        // No assertion here; we expect no exceptions or crashes
    }

    @Test
    public void getPastResults_withResults_returnsCorrectResults() {
        // Add a topic with a question
        Topic topic = new Topic("Java Basics");
        topic.addQuestion(new Mcq("What is Java?", "a",
                List.of("a) A programming language", "b) A type of coffee", "c) A car brand")));

        quizManager.addTopic(topic);

        // Prepare simulated input for the quiz
        ByteArrayInputStream input = new ByteArrayInputStream("b\n".getBytes());
        Scanner scanner = new Scanner(input);

        // Start the quiz with the provided scanner
        quizManager.startQuiz(topic, scanner);

        String expectedResult = "Score: 0%, Comment: Better luck next time!\n";
        assertEquals(expectedResult, quizManager.getPastResults());

        scanner.close();
    }

    @Test
    public void saveResults_savesToFileCorrectly() throws IOException {
        // Add a topic and question
        Topic topic = new Topic("Java Basics");
        topic.addQuestion(new Mcq("What is Java?", "a",
                List.of("a) A programming language", "b) A type of coffee", "c) A car brand")));

        quizManager.addTopic(topic);

        // Simulate user input during the quiz
        ByteArrayInputStream input = new ByteArrayInputStream("b\n".getBytes());
        Scanner scanner = new Scanner(input);

        // Start the quiz and save the results
        quizManager.startQuiz(topic, scanner);

        String savedResults = Files.readString(Path.of(RESULTS_FILE_PATH));
        String expectedSavedResults = "Score: 0%, Comment: Better luck next time!\n";
        assertEquals(expectedSavedResults, savedResults);

        scanner.close();
    }

    @Test
    public void loadResultsFromFile_correctlyLoadsResults() throws IOException {
        // Simulate a previously saved result in the file
        String previousResult = "Score: 80%, Comment: Good job!\n";
        Files.writeString(Path.of(RESULTS_FILE_PATH), previousResult);

        // Reload the QuizManager to simulate restarting the program
        quizManager = new QuizManager();

        // Verify that the loaded result matches the expected value
        String loadedResults = quizManager.getPastResults();
        assertEquals(previousResult, loadedResults);
    }

    @Test
    public void startQuiz_withTrueFalseQuestion_correctlyHandlesTrueFalse() {
        // Add a topic with a TrueFalse question
        Topic topic = new Topic("General Knowledge");
        topic.addQuestion(new TrueFalse("The Earth is flat.", false));

        quizManager.addTopic(topic);

        // Prepare simulated input for the quiz (answering "false")
        ByteArrayInputStream input = new ByteArrayInputStream("false\n".getBytes());
        Scanner scanner = new Scanner(input);

        // Start the quiz and validate results
        quizManager.startQuiz(topic, scanner);

        String expectedResult = "Score: 100%, Comment: Excellent!\n";
        assertEquals(expectedResult, quizManager.getPastResults());

        scanner.close();
    }

    @Test
    public void startQuiz_withInvalidTrueFalseAnswer_throwsException() {
        // Add a topic with a TrueFalse question
        Topic topic = new Topic("General Knowledge");
        topic.addQuestion(new TrueFalse("The Earth is flat.", false));

        quizManager.addTopic(topic);

        // Prepare simulated input for the quiz (answering "yes", then "false" after invalid input)
        ByteArrayInputStream input = new ByteArrayInputStream("yes\nfalse\n".getBytes());
        Scanner scanner = new Scanner(input);

        // Start the quiz and validate the exception for invalid input
        try {
            quizManager.startQuiz(topic, scanner);
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid input! Please enter 'true' or 'false'.", e.getMessage());
        }

        scanner.close();
    }

}
