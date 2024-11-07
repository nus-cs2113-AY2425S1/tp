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
    public void addResult_withValidDetails_savesResultCorrectly() {
        // Add a result with new format details
        quizResults.addResult("Loops", 100, 3, "Excellent!", 300);

        // Define expected result format
        String expectedResult = "{\n" +
            "  Topic: Loops,\n" +
            "  Score: 100%,\n" +
            "  Time Limit: 300 seconds,\n" +
            "  Questions Attempted: 3,\n" +
            "  Comment: Excellent!\n" +
            "}";

        // Check that the past results contain the correctly formatted result
        assertEquals(expectedResult.replaceAll("\\s+", " ").trim(),
            quizResults.getPastResults().replaceAll("\\s+", " ").trim());
    }

    @Test
    public void generateComment_withHighScore_returnsExcellentComment() {
        // Generate a comment for a high score
        String comment = quizResults.generateComment(95);
        assertEquals("Excellent!", comment);
    }

    @Test
    public void saveResults_writesFormattedResultsToFile() throws IOException {
        // Add a result with new format and save it
        quizResults.addResult("Loops", 60, 5, "Needs improvement.", 150);
        boolean isDataSaved = quizResults.isResultsSaved();

        // Define expected file content format
        String expectedFileContents = "{\n" +
            "  Topic: Loops,\n" +
            "  Score: 60%,\n" +
            "  Time Limit: 150 seconds,\n" +
            "  Questions Attempted: 5,\n" +
            "  Comment: Needs improvement.\n" +
            "}";

        // Read the saved result from the file and verify
        Path path = Path.of(resultsFilePath);
        String fileContents = Files.readString(path);
        assertEquals(expectedFileContents.replaceAll("\\s+", " ").trim(),
            fileContents.replaceAll("\\s+", " ").trim());
    }

    @Test
    public void loadResults_readsFormattedResultsFromFile() throws IOException {
        // Write a simulated formatted past result to the file
        String previousResult = "{\n" +
            "  Topic: Loops,\n" +
            "  Score: 75%,\n" +
            "  Time Limit: 200 seconds,\n" +
            "  Questions Attempted: 4,\n" +
            "  Comment: Good job!\n" +
            "}\n";
        Files.writeString(Path.of(resultsFilePath), previousResult);

        // Load results from file and verify
        quizResults.loadResults();
        assertEquals(previousResult.trim(), quizResults.getPastResults().trim());
    }

    @Test
    public void getPastResults_withNoResults_returnsEmptyMessage() {
        // Ensure no results are loaded initially
        assertEquals("No past results available. You haven't completed any quizzes yet.", quizResults.getPastResults());
    }
}
