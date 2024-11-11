package seedu.javaninja;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * The `QuizResults` class is responsible for managing and storing quiz results, including loading,
 * saving, and generating summary comments based on scores.
 */
public class QuizResults {
    private static final Logger logger = Logger.getLogger(QuizResults.class.getName());
    private static final String RESULTS_FILE_PATH = "./data/results.txt"; // File path for saving results
    private List<String> pastResults;      // List to store past quiz results
    private Storage results;               // Storage instance for handling file operations

    /**
     * Constructs a `QuizResults` instance and initializes storage and past results.
     */
    public QuizResults() {
        pastResults = new ArrayList<>();
        results = new Storage(RESULTS_FILE_PATH);
    }

    /**
     * Loads past quiz results from a file into the `pastResults` list.
     * Logs any `IOException` that occurs during the file read operation.
     */
    public void loadResults() {
        try {
            pastResults = results.loadData();
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }

    /**
     * Saves the current quiz results to a file.
     *
     * @return `true` if results were saved successfully; `false` otherwise.
     */
    public boolean isResultsSaved() {
        try {
            results.saveToFile(RESULTS_FILE_PATH, pastResults, false);
            return true;
        } catch (IOException e) {
            logger.warning(e.getMessage());
            return false;
        }
    }

    /**
     * Adds a new result entry to `pastResults` for a completed quiz session.
     *
     * @param topicName           The topic name of the quiz.
     * @param score               The score achieved in percentage.
     * @param questionsAttempted  The number of questions attempted in the quiz.
     * @param comment             The comment generated based on the score.
     * @param timeLimitInSeconds  The time limit for the quiz in seconds.
     */
    public void addResult(String topicName, int score, int questionsAttempted, String comment, int timeLimitInSeconds) {
        String timeLimit = (timeLimitInSeconds <= 0) ?
            "untimed" : (String.valueOf(timeLimitInSeconds) + " seconds");
        String resultEntry = String.format("{" +
                        "\n Topic: %s," +
                        "\n Score: %d%%," +
                        "\n Time Limit: %s, " +
                        "\n Questions Attempted: %d," +
                        "\n Comment: %s" +
                        "\n}",
                topicName, score, timeLimit, questionsAttempted, comment);
        pastResults.add(resultEntry);
    }

    /**
     * Generates a summary comment based on the score achieved in the quiz.
     *
     * @param score The score achieved in percentage.
     * @return A comment that provides feedback on the user's performance.
     */
    public String generateComment(int score) {
        if (score >= 90) {
            return "Excellent!";
        } else if (score >= 70) {
            return "Good job!";
        } else if (score >= 50) {
            return "Needs improvement.";
        } else {
            return "Better luck next time!";
        }
    }

    /**
     * Retrieves a formatted string of all past quiz results.
     *
     * @return A string containing all past results, or a message if no results are available.
     */
    public String getPastResults() {
        if (pastResults.isEmpty()) {
            return "No past results available. You haven't completed any quizzes yet.";
        }

        StringBuilder results = new StringBuilder();
        pastResults.forEach(result -> results.append(result).append("\n"));
        return results.toString();
    }
}
