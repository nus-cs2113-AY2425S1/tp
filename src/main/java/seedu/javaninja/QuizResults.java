package seedu.javaninja;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages storage and retrieval of quiz results, including filtering and sorting.
 */
public class QuizResults {
    private List<Result> results;

    public QuizResults() {
        this.results = new ArrayList<>();
    }

    /**
     * Adds a new result to the list of results with date and time.
     *
     * @param topic The topic of the quiz.
     * @param score The score achieved in the quiz.
     */
    public void addResult(String topic, int score) {
        results.add(new Result(topic, score, new Date()));
    }

    /**
     * Loads results from storage or initializes with an empty list.
     */
    public void loadResults() {
        // This method can be modified to load results from a file or database
        // For now, it will simply ensure the results list is initialized
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
     * Represents a single quiz result with topic, score, and date.
     */
    public static class Result {
        private final String topic;
        private final int score;
        private final Date date;

        public Result(String topic, int score, Date date) {
            this.topic = topic;
            this.score = score;
            this.date = date;
        }

        public String getTopic() { return topic; }
        public int getScore() { return score; }
        public Date getDate() { return date; }

        @Override
        public String toString() {
            return "Topic: " + topic + ", Score: " + score + ", Date: " + date;
        }
    }
}
