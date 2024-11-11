package seedu.javaninja;

import java.util.List;
import java.util.logging.Logger;

/**
 * The `QuizManager` class is responsible for managing quiz sessions, results, and topics.
 * It acts as a central controller that coordinates interactions between the CLI, quiz sessions,
 * quiz results, and topic management.
 */
public class QuizManager {
    private static final Logger logger = Logger.getLogger(QuizManager.class.getName());
    private QuizGenerator quizSession;       // Manages each quiz session
    private QuizResults quizResults;         // Stores and retrieves quiz results
    private TopicManager topicManager;       // Manages topics and flashcards
    private Cli cli;                         // CLI instance for user interaction

    /**
     * Constructs a `QuizManager` instance and initializes the necessary components.
     *
     * @param cli The CLI instance for user interaction.
     */
    public QuizManager(Cli cli) {
        this.cli = cli;
        topicManager = new TopicManager(cli);
        quizResults = new QuizResults();
        quizSession = new QuizGenerator(topicManager, cli);

        loadDataFromFile(); // Load questions, flashcards, and previous results from files
    }

    /* For testing */
    public QuizManager(Cli cli, boolean loadFromStorage) {
        this.cli = cli;
        topicManager = new TopicManager(cli);
        quizResults = new QuizResults();
        quizSession = new QuizGenerator(topicManager, cli);

        if (loadFromStorage) {
            loadDataFromFile();
        }
    }

    /**
     * Initiates a quiz session on the specified topic.
     *
     * @param input The name of the topic for the quiz session.
     */
    public void handleQuizSelection(String input) {
        QuizType quizType = QuizType.UNTIMED; // Default quiz type
        String topicName = null;

        // Split input by "/d" and "/t" to determine quiz type and topic name
        String[] typeParts = input.split("/d", 2);

        if (typeParts.length < 2 || typeParts[1].trim().isEmpty()) {
            cli.printMessage("Please specify '/d timed' or '/d untimed' for quiz type.");
            return;
        }

        String[] topicParts = typeParts[1].split("/t", 2);
        String quizTypeStr = topicParts[0].trim().toLowerCase();

        // Validate quiz type input
        if (QuizType.isValidType(quizTypeStr)) {
            quizType = QuizType.valueOf(quizTypeStr.toUpperCase());
        } else {
            cli.printMessage("Invalid quiz type. Use '/d timed' or '/d untimed'.");
            return;
        }

        // Check for topic name
        if (topicParts.length > 1 && !topicParts[1].trim().isEmpty()) {
            topicName = topicParts[1].trim();
        } else {
            cli.printMessage("Please provide a topic name after '/t'.");
            return;
        }

        // Start quiz based on the quiz type and topic
        boolean quizStarted = false;
        if (topicName.equalsIgnoreCase("random")) {
            quizStarted = quizSession.selectRandomTopicsQuiz(quizType == QuizType.TIMED);
        } else if (quizType == QuizType.TIMED) {
            quizStarted = quizSession.selectTimedQuiz(topicName);
        } else {
            quizStarted = quizSession.selectUntimedQuiz(topicName);
        }

        // If quiz started, add results and print score
        if (quizStarted) {
            addResultsAndPrintScore();
        }
    }

    private enum QuizType {
        TIMED, UNTIMED;

        public static boolean isValidType(String type) {
            return type.equalsIgnoreCase("timed") || type.equalsIgnoreCase("untimed");
        }
    }

    /**
     * Adds the completed quiz session's results to the record and displays the final score to the user.
     */
    public void addResultsAndPrintScore() {
        String topicName = quizSession.getTopicName();
        int completedQuizScore = quizSession.getQuizScore();
        quizResults.addResult(topicName, completedQuizScore);
        cli.printMessage("Quiz finished. Your score is: " + completedQuizScore + "%");
    }

    /**
     * Loads data from files, including topics, flashcards, and past quiz results.
     */
    private void loadDataFromFile() {
        topicManager.loadQuestions();
        topicManager.loadFlashcards();
        quizResults.loadResults(); // Initialize results from any available storage
    }

    /**
     * Retrieves a list of available quiz topics.
     *
     * @return A list of topic names.
     */
    public List<String> getQuizzesAvailable() {
        return topicManager.getTopicNames();
    }

    /**
     * Reviews quiz results for a specific topic, with options to sort by date or score.
     *
     * @param topic The topic to filter results by.
     * @param isNewestFirst Whether to sort by newest date first.
     * @param sortByScore Whether to sort by highest score.
     */
    public void reviewResultsByTopic(String topic, boolean isNewestFirst, boolean sortByScore) {
        List<QuizResults.Result> filteredResults = quizResults.getResultsByTopic(topic);

        if (sortByScore) {
            filteredResults = quizResults.sortByScore(filteredResults);
        } else {
            filteredResults = quizResults.sortByDate(filteredResults, isNewestFirst);
        }

        for (QuizResults.Result result : filteredResults) {
            cli.printMessage(result.toString());
        }
    }

    /**
     * Reviews all quiz results, with options to sort by date or score.
     *
     * @param isNewestFirst Whether to sort by newest date first.
     * @param sortByScore Whether to sort by highest score.
     */
    public void reviewAllResults(boolean isNewestFirst, boolean sortByScore) {
        List<QuizResults.Result> allResults = quizResults.getAllResults();

        if (sortByScore) {
            allResults = quizResults.sortByScore(allResults);
        } else {
            allResults = quizResults.sortByDate(allResults, isNewestFirst);
        }

        for (QuizResults.Result result : allResults) {
            cli.printMessage(result.toString());
        }
    }

    /**
     * Adds a flashcard question based on user input.
     *
     * @param input The user's input specifying the flashcard question.
     */
    public void addInput(String input) {
        try {
            topicManager.addFlashcardByUser(input);
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }

    /**
     * Saves quiz results and flashcards, displaying feedback to the user regarding the success of the save operations.
     */
    public void saveResults() {
        cli.printMessage("Saving functionality is not yet implemented.");
    }

    /**
     * Provides access to the current quiz session, primarily for testing purposes.
     *
     * @return The active or newly created `QuizSession` instance.
     */
    public QuizGenerator getQuizSession() {
        if (this.quizSession == null) {
            this.quizSession = new QuizGenerator(topicManager, cli);
        }
        return this.quizSession;
    }

    /**
     * Provides access to the quiz results instance, primarily for testing purposes.
     *
     * @return The `QuizResults` instance storing past quiz results.
     */
    public QuizResults getQuizResults() {
        return this.quizResults;
    }

    /**
     * Provides access to the topic manager, primarily for testing purposes.
     *
     * @return The `TopicManager` instance managing quiz topics and flashcards.
     */
    public TopicManager getTopicManager() {
        return this.topicManager;
    }
}
