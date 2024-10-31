package seedu.javaninja;

import java.util.logging.Logger;

public class QuizManager {
    private static final Logger logger = Logger.getLogger(QuizManager.class.getName());
    private QuizSession quizSession;
    private QuizResults quizResults;
    private TopicManager topicManager;


    public QuizManager() {
        topicManager = new TopicManager();
        quizResults = new QuizResults();
        loadDataFromFile();
    }

    public void selectQuizToAttempt(String topicName) {
        if (topicName == null || topicName.trim().isEmpty()) {
            logger.warning("Invalid input. Please provide a topic name.");
            return;
        }
        Topic selectedTopic = topicManager.getOrCreateTopic(topicName);

        if (selectedTopic == null) {
            logger.warning("No such topic: " + topicName);
            return;
        }

        quizSession = new QuizSession();
        quizSession.startQuiz(selectedTopic);

        // quiz has completed
        addResultsAndPrintScore();

    }

    public void addResultsAndPrintScore() {
        int completedQuizScore = quizSession.getQuizScore();
        String comment = quizResults.generateComment(completedQuizScore);
        quizResults.addResult(completedQuizScore, comment);
    }

    private void loadDataFromFile() {
        topicManager.loadQuestions();
        quizResults.loadResults();
    }

    public void printQuizzesAvailable() {
        topicManager.printTopics();
    }

    public void printPastResults() {
        quizResults.getPastResults();
    }

    public void addInput (String input) {
        try {
            topicManager.addFlashcardByUser(input);
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }
}
