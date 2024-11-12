package seedu.javaninja;

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
    private final List<Result> results; // Made final
    private final Storage storage; // Made final

    /**
     * Constructs a `QuizResults` instance and initializes the results list and storage.
     */
    public QuizResults() {
        this.results = new ArrayList<>();
        this.storage = new Storage("./data/results.txt");
        loadResults();
    }

    /**
     * Adds a new result to the list of results with date and time, including a comment,
     * and automatically saves the results to storage.
     *
     * @param topic The topic of the quiz.
     * @param score The score achieved in the quiz.
     * @param questionsAttempted The number of questions attempted in the quiz.
     * @param timeLimitInSeconds The time limit for the quiz in seconds.
     */
    public void addResult(String topic, int score, int questionsAttempted, int timeLimitInSeconds) {
        String comment = generateComment(score);
        Result newResult = new Result(topic, score, new Date(), questionsAttempted, timeLimitInSeconds, comment);
        results.add(newResult);
        saveResults(); // Automatically save results whenever a new result is added
    }

    /**
     * Loads results from storage and handles potential data corruption.
     */
    public void loadResults() {
        List<String> loadedData = storage.loadData();
        for (String line : loadedData) {
            try {
                String[] parts = line.split("\\|");
                if (parts.length >= 6) {
                    String topic = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    Date date = new Date(Long.parseLong(parts[2]));
                    int questionsAttempted = Integer.parseInt(parts[3]);
                    int timeLimitInSeconds = Integer.parseInt(parts[4]);
                    String comment = parts[5];

                    // Check if the result already exists
                    boolean resultExists = results.stream()
                            .anyMatch(r -> r.getTopic().equals(topic) &&
                                    r.getDate().equals(date) &&
                                    r.getScore() == score);

                    if (!resultExists) {
                        results.add(new Result(topic, score, date, questionsAttempted, timeLimitInSeconds, comment));
                    }
                }
            } catch (Exception e) {
                logger.warning("Corrupted data detected. Skipping line: " + line);
            }
        }
    }


    /**
     * Saves the current results to storage.
     */
    private void saveResults() {
        List<String> lines = results.stream()
                .map(result -> String.join("|",
                        result.getTopic(),
                        String.valueOf(result.getScore()),
                        String.valueOf(result.getDate().getTime()),
                        String.valueOf(result.getQuestionsAttempted()),
                        String.valueOf(result.getTimeLimitInSeconds()),
                        result.getComment()))
                .collect(Collectors.toList());

        storage.saveToFile(lines, false); // IOException is now caught internally in Storage
    }

    /**
     * Retrieves all stored results.
     *
     * @return A list of all results.
     */
    public List<Result> getAllResults() {
        return new ArrayList<>(results);
    }

    /**
     * Filters results by a given topic.
     *
     * @param topic The topic to filter results by.
     * @return A list of results for the specified topic.
     */
    public List<Result> getResultsByTopic(String topic) {
        return results.stream()
                .filter(result -> result.getTopic().equalsIgnoreCase(topic))
                .collect(Collectors.toList());
    }

    /**
     * Sorts results by date, in either ascending or descending order.
     *
     * @param results The list of results to sort.
     * @param isNewestFirst True to sort by newest first; false for oldest first.
     * @return A sorted list of results by date.
     */
    public List<Result> sortByDate(List<Result> results, boolean isNewestFirst) {
        return results.stream()
                .sorted(isNewestFirst ? Comparator.comparing(Result::getDate).reversed()
                        : Comparator.comparing(Result::getDate))
                .collect(Collectors.toList());
    }

    /**
     * Sorts results by score in descending order (highest to lowest).
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
     * Generates a summary comment based on the given score.
     *
     * @param score The score to generate a comment for.
     * @return A comment on the performance.
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
     * Represents a single quiz result with various attributes.
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
            String timeLimit;
            if (timeLimitInSeconds <= 0) {
                timeLimit = "untimed";
            } else if (timeLimitInSeconds % 60 == 0) {
                // Display in minutes if it is a multiple of 60
                timeLimit = (timeLimitInSeconds / 60) + " minutes";
            } else {
                timeLimit = timeLimitInSeconds + " seconds";
            }
            return String.format("Topic: %s, Score: %d%%, Questions Limit: %d, Time Limit: %s, Date: %s",
                    topic, score, questionsAttempted, timeLimit, date);
        }

    }
}
