package seedu.javaninja;

import java.util.List;
import java.util.logging.Logger;

public class QuizManager {
    private static final Logger logger = Logger.getLogger(QuizManager.class.getName());
    private QuizSession quizSession;
    private QuizResults quizResults;
    private TopicManager topicManager;
    private Cli cli;


    public QuizManager(Cli cli) {
        this.cli = cli;
        topicManager = new TopicManager(cli);
        quizResults = new QuizResults();

        loadDataFromFile();
    }

    public void selectQuizToAttempt(String topicName) {
        if (topicName == null || topicName.trim().isEmpty()) {
            logger.warning("Invalid input. Please provide a topic name.");
            return;
        }
        Topic selectedTopic = topicManager.getTopic(topicName);

        if (selectedTopic == null) {
            logger.warning("No such topic: " + topicName);
            return;
        }

        quizSession = new QuizSession(cli);
        quizSession.startQuiz(selectedTopic);

        // quiz has completed
        addResultsAndPrintScore();

    }

    public void addResultsAndPrintScore() {
        String topicName = quizSession.getTopicName();
        int completedQuizScore = quizSession.getQuizScore();
        String comment = quizResults.generateComment(completedQuizScore);
        int questionLimit = quizSession.getQuestionLimit();
        int timeLimit = quizSession.getTimeLimitInSeconds();
        quizResults.addResult(topicName, completedQuizScore, questionLimit, comment, timeLimit);
    }

    private void loadDataFromFile() {
        topicManager.loadQuestions();
        topicManager.loadFlashcards();
        quizResults.loadResults();
    }

    public List<String> getQuizzesAvailable() {
        return topicManager.getTopicNames();
    }

    public String getPastResults() {
        return quizResults.getPastResults();
    }

    public void addInput (String input) {
        try {
            topicManager.addFlashcardByUser(input);
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }

    public void saveResults () {
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

    /* For quizManagerTest */
    public QuizSession getQuizSession() {
        if (this.quizSession == null) {
            this.quizSession = new QuizSession(cli);
        }
        return this.quizSession;
    }

    public QuizResults getQuizResults() {
        return this.quizResults;
    }

    public TopicManager getTopicManager() {
        return this.topicManager;
    }
}
