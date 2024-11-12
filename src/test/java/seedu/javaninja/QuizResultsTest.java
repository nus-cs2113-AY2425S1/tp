package seedu.javaninja;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the functionality of the QuizResults class, ensuring that results can be added,
 * filtered, sorted, and comments are generated accurately based on scores.
 */
public class QuizResultsTest {

    private QuizResults quizResults;

    /**
     * Sets up a new QuizResults instance before each test.
     */
    @BeforeEach
    public void setUp() {
        quizResults = new QuizResults(); // Use no-argument constructor
    }

    /**
     * Tests adding a quiz result to the list and verifies its properties.
     */
    @Test
    public void testAddResult() {
        quizResults.addResult("Java Basics", 80, 10, 300);
        List<QuizResults.Result> results = quizResults.getAllResults();

        assertEquals(1, results.size());
        QuizResults.Result result = results.get(0);
        assertEquals("Java Basics", result.getTopic());
        assertEquals(80, result.getScore());
        assertEquals(10, result.getQuestionsAttempted());
        assertEquals(300, result.getTimeLimitInSeconds());
    }

    /**
     * Tests the generateComment method to ensure it produces the correct feedback.
     */
    @Test
    public void testGenerateComment() {
        assertEquals("Excellent!", quizResults.generateComment(90));
        assertEquals("Good job!", quizResults.generateComment(70));
        assertEquals("Needs improvement.", quizResults.generateComment(50));
        assertEquals("Better luck next time!", quizResults.generateComment(40));
    }

    /**
     * Tests sorting quiz results by score in descending order.
     */
    @Test
    public void testSortByScore() {
        quizResults.addResult("Java Basics", 60, 10, 300);
        quizResults.addResult("OOP", 90, 8, 600);
        quizResults.addResult("Data Structures", 75, 12, 450);

        List<QuizResults.Result> sortedResults = quizResults.sortByScore(quizResults.getAllResults());

        assertEquals(90, sortedResults.get(0).getScore());
        assertEquals(75, sortedResults.get(1).getScore());
        assertEquals(60, sortedResults.get(2).getScore());
    }

    /**
     * Tests filtering quiz results by topic.
     */
    @Test
    public void testGetResultsByTopic() {
        quizResults.addResult("Java Basics", 60, 10, 300);
        quizResults.addResult("Java Basics", 80, 10, 300);
        quizResults.addResult("OOP", 90, 8, 600);

        List<QuizResults.Result> javaBasicsResults = quizResults.getResultsByTopic("Java Basics");

        assertEquals(2, javaBasicsResults.size());
        assertTrue(javaBasicsResults.stream().allMatch(result -> result.getTopic().equals("Java Basics")));
    }
}
