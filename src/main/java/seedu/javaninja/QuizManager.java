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

    /**
     * Initiates a quiz session on the specified topic.
     *
     * @param topicName The name of the topic for the quiz session.
     */
    public void handleQuizSelection(String input) {
        QuizType quizType = QuizType.UNTIMED; // Default to untimed
        String topic = null;

        String[] parts = input.split(" ");
        for (int i = 1; i < parts.length; i++) {
            switch (parts[i]) {
            case "/d":
                if (i + 1 < parts.length && QuizType.isValidType(parts[i + 1].toLowerCase())) {
                    quizType = QuizType.valueOf(parts[i + 1].toUpperCase());
                    i++; // Skip next part as it's already read
                } else {
                    cli.printMessage("Please specify 'timed' or 'untimed' after /d.");
                    return;
                }
                break;
            case "/t":
                if (i + 1 < parts.length) {
                    topic = parts[i + 1].trim().equalsIgnoreCase("random") ? "random" : parts[i + 1].trim();
                    i++;
                } else {
                    cli.printMessage("Please specify a topic after /t.");
                    return;
                }
                break;
            }
        }

        if (topic == null) {
            cli.printMessage("Please provide a topic name using /t.");
            return;
        }

        if (topic.equalsIgnoreCase("random")) {
            quizSession.selectRandomTopicsQuiz(quizType == QuizType.TIMED);
        } else {
            if (quizType == QuizType.TIMED) {
                quizSession.selectTimedQuiz(topic);
            } else {
                quizSession.selectUntimedQuiz(topic);
            }
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
