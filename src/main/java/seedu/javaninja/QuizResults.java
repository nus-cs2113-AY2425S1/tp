package seedu.javaninja;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class QuizResults {
    private static final Logger logger = Logger.getLogger(QuizResults.class.getName());
    private static final String RESULTS_FILE_PATH = "./data/results.txt";
    private List<String> pastResults;
    private Storage results;


    public QuizResults() {
        pastResults = new ArrayList<>();
        results = new Storage(RESULTS_FILE_PATH);
    }

    public void loadResults() {
        try {
            pastResults = results.loadData();
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }

    public boolean isResultsSaved() {
        try {
            results.saveToFile(RESULTS_FILE_PATH, pastResults, false);
            return true;
        } catch (IOException e) {
            logger.warning(e.getMessage());
            return false;
        }
    }

    public void addResult(String topicName, int score, int questionsAttempted, String comment, int timeLimitInSeconds) {
        String resultEntry = String.format("{" +
                "\n Topic: %s," +
                "\n Score: %d%%," +
                "\n Time Limit: %d seconds," +
                "\n Questions Attempted: %d," +
                "\n Comment: %s" +
                "\n}",
            topicName, score, timeLimitInSeconds, questionsAttempted, comment);
        pastResults.add(resultEntry);
    }

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

    public String getPastResults() {
        if (pastResults.isEmpty()) {
            return "No past results available. You haven't completed any quizzes yet.";
        }

        StringBuilder results = new StringBuilder();
        pastResults.forEach(result -> results.append(result).append("\n"));
        return results.toString();
    }
}
