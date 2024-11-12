package seedu.javaninja;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * The `QuizResults` class manages the storage and retrieval of quiz results,
 * including loading, saving, and generating summary comments based on scores.
 */
public class QuizResults {
    private static final Logger logger = Logger.getLogger(QuizResults.class.getName());
    private List<Result> results;

    /**
     * Constructs a `QuizResults` instance and initializes the results list.
     */
    public QuizResults() {
        this.results = new ArrayList<>();
    }

    /**
     * Adds a new result to the list of results with date and time, including a comment.
     *
     * @param topic The topic of the quiz.
     * @param score The score achieved in the quiz.
     * @param questionsAttempted The number of questions attempted in the quiz.
     * @param timeLimitInSeconds The time limit for the quiz in seconds.
     */
    public void addResult(String topic, int score, int questionsAttempted, int timeLimitInSeconds) {
        String comment = generateComment(score);
        results.add(new Result(topic, score, new Date(), questionsAttempted, timeLimitInSeconds, comment));
    }

    /**
     * Loads results from storage or initializes an empty list.
     */
    public void loadResults() {
        // This method can be modified to load results from a file or database
        if (results == null) {
            results = new ArrayList<>();
        }
    }

    /**
     * Gets all stored results.
     *
     * @return A list of all results.
     */
    public List<Result> getAllResults() {
        return new ArrayList<>(results);
    }

    /**
     * Filters results by topic.
     *
     * @param topic The topic to filter by.
     * @return A list of results for the specified topic.
     */
    public List<Result> getResultsByTopic(String topic) {
        return results.stream()
                .filter(result -> result.getTopic().equalsIgnoreCase(topic))
                .collect(Collectors.toList());
    }

    /**
     * Sorts results by date, either newest or oldest.
     *
     * @param results The list of results to sort.
     * @param isNewestFirst If true, sort by newest; otherwise, sort by oldest.
     * @return A sorted list of results.
     */
    public List<Result> sortByDate(List<Result> results, boolean isNewestFirst) {
        return results.stream()
                .sorted(isNewestFirst ? Comparator.comparing(Result::getDate).reversed()
                        : Comparator.comparing(Result::getDate))
                .collect(Collectors.toList());
    }

    /**
     * Sorts results by score in descending order.
     *
     * @param results The list of results to sort.
     * @return A sorted list of results by highest score.
     */
    public List<Result> sortByScore(List<Result> results) {
        return results.stream()
                .sorted(Comparator.comparingInt(Result::getScore).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Sorts results by score in ascending order (lowest to highest).
     *
     * @param results The list of results to sort.
     * @return A sorted list of results by lowest score.
     */
    public List<Result> sortByLowestScore(List<Result> results) {
        return results.stream()
                .sorted(Comparator.comparingInt(Result::getScore))
                .collect(Collectors.toList());
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
     * Represents a single quiz result with topic, score, date, number of questions, time limit, and comment.
     */
    public static class Result {
        private final String topic;
        private final int score;
        private final Date date;
        private final int questionsAttempted;
        private final int timeLimitInSeconds;
        private final String comment;

        public Result(String topic, int score, Date date, int questionsAttempted, int timeLimitInSeconds, String comment) {
            this.topic = topic;
            this.score = score;
            this.date = date;
            this.questionsAttempted = questionsAttempted;
            this.timeLimitInSeconds = timeLimitInSeconds;
            this.comment = comment;
        }

        public String getTopic() { return topic; }
        public int getScore() { return score; }
        public Date getDate() { return date; }
        public int getQuestionsAttempted() { return questionsAttempted; }
        public int getTimeLimitInSeconds() { return timeLimitInSeconds; }
        public String getComment() { return comment; }

        @Override
        public String toString() {
            String timeLimit = (timeLimitInSeconds <= 0) ? "untimed" : timeLimitInSeconds + " seconds";
            return String.format("Topic: %s, Score: %d%%, Questions Attempted: %d, Time Limit: %s, Date: %s",
                    topic, score, questionsAttempted, timeLimit, date);
        }
    }
}
