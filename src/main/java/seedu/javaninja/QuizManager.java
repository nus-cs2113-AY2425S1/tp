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
    private QuizResults quizResults;       // Stores and retrieves quiz results
    private TopicManager topicManager;     // Manages topics and flashcards
    private Cli cli;                       // CLI instance for user interaction

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
        // Default values
        QuizType quizType = QuizType.UNTIMED;
        String topicName = null;

        // Split input by "/d" and "/t"
        String[] typeParts = input.split("/d", 2);

        if (typeParts.length < 2 || typeParts[1].trim().isEmpty()) {
            cli.printMessage("Please specify '/d timed' or '/d untimed' for quiz type.");
            return;
        }

        String[] topicParts = typeParts[1].split("/t", 2);

        String quizTypeStr = topicParts[0].trim().toLowerCase();
        if (QuizType.isValidType(quizTypeStr)) {
            quizType = QuizType.valueOf(quizTypeStr.toUpperCase());
        } else {
            cli.printMessage("Invalid quiz type. Use '/d timed' or '/d untimed'.");
            return;
        }

        if (topicParts.length > 1 && !topicParts[1].trim().isEmpty()) {
            topicName = topicParts[1].trim();
        } else {
            cli.printMessage("Please provide a topic name after '/t'.");
            return;
        }

        boolean quizStarted = false;
        if (topicName.equalsIgnoreCase("random")) {
            quizStarted = quizSession.selectRandomTopicsQuiz(quizType == QuizType.TIMED);
        } else if (quizType == QuizType.TIMED) {
            quizStarted = quizSession.selectTimedQuiz(topicName);
        } else {
            quizStarted = quizSession.selectUntimedQuiz(topicName);
        }

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
        String comment = quizResults.generateComment(completedQuizScore);
        int questionLimit = quizSession.getQuestionLimit();
        int timeLimit = quizSession.getTimeLimitInSeconds();
        quizResults.addResult(topicName, completedQuizScore, questionLimit, comment, timeLimit);
    }

    /**
     * Loads data from files, including topics, flashcards, and past quiz results.
     */
    private void loadDataFromFile() {
        topicManager.loadQuestions();
        topicManager.loadFlashcards();
        quizResults.loadResults();
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
     * Retrieves a summary of past quiz results.
     *
     * @return A formatted string representing past quiz results.
     */
    public String getPastResults() {
        return quizResults.getPastResults();
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
        if (quizResults.isResultsSaved()) {
            cli.printMessage("Goodbye! Your quiz results have been successfully saved");
        } else {
            cli.printMessage("Your quiz results have not been successfully saved unfortunately");
        }

        if (topicManager.isFlashcardsSaved()) {
            cli.printMessage("Flashcards have been successfully saved");
        } else {
            cli.printMessage("Flashcards have not been successfully saved unfortunately");
        }
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
