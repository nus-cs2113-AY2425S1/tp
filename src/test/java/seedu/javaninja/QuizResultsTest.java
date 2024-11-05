import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.javaninja.QuizResults;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuizResultsTest {

    private QuizResults quizResults;
    private final String resultsFilePath = "./data/results.txt";

    @BeforeEach
    public void setUp() {
        // Clear the results file before each test
        File file = new File(resultsFilePath);
        if (file.exists()) {
            file.delete();
        }
        quizResults = new QuizResults();
    }

    @Test
    public void addResult_withValidScoreAndComment_savesResultCorrectly() {
        // Add a result
        quizResults.addResult(85, "Good job!");

        // Check that the past results contain the added result
        String expectedResult = "Score: 85%, Comment: Good job!\n";
        assertEquals(expectedResult, quizResults.getPastResults());
    }

    @Test
    public void generateComment_withHighScore_returnsExcellentComment() {
        // Generate a comment for a high score
        String comment = quizResults.generateComment(95);
        assertEquals("Excellent!", comment);
    }

    @Test
    public void saveResults_writesResultsToFile() throws IOException {
        // Add a result and save it
        quizResults.addResult(60, "Needs improvement.");
        quizResults.saveResults();

        // Read the saved result from the file and verify
        Path path = Path.of(resultsFilePath);
        String fileContents = Files.readString(path).trim();
        assertEquals("Score: 60%, Comment: Needs improvement.", fileContents);
    }

    @Test
    public void loadResults_readsResultsFromFile() throws IOException {
        // Write a simulated past result to the file
        String previousResult = "Score: 75%, Comment: Good job!\n";
        Files.writeString(Path.of(resultsFilePath), previousResult);

        // Load results from file and verify
        quizResults.loadResults();
        assertEquals(previousResult, quizResults.getPastResults());
    }

    @Test
    public void getPastResults_withNoResults_returnsEmptyMessage() {
        // Ensure no results are loaded initially
        assertEquals("No past results available. You haven't completed any quizzes yet.", quizResults.getPastResults());
    }
}

