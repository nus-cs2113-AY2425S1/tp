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
    private QuizGenerator quizSession;
    private QuizResults quizResults;
    private TopicManager topicManager;
    private Cli cli;

    public QuizManager(Cli cli) {
        this.cli = cli;
        topicManager = new TopicManager(cli);
        quizResults = new QuizResults();
        quizSession = new QuizGenerator(topicManager, cli);

        loadDataFromFile();
    }

    public QuizManager(Cli cli, boolean loadFromStorage) {
        this.cli = cli;
        topicManager = new TopicManager(cli);
        quizResults = new QuizResults();
        quizSession = new QuizGenerator(topicManager, cli);

        if (loadFromStorage) {
            loadDataFromFile();
        }
    }

    public void handleQuizSelection(String input) {
        QuizType quizType = QuizType.UNTIMED;
        final String topicName;

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

        if (topicName.equalsIgnoreCase("random")) {
            boolean quizStarted = quizSession.selectRandomTopicsQuiz(quizType == QuizType.TIMED);
            if (quizStarted) {
                addResultsAndPrintScore();
            }
            return;
        }

        String matchedTopic = topicManager.getTopicNames().stream()
                .filter(name -> name.equalsIgnoreCase(topicName))
                .findFirst()
                .orElse(null);

        if (matchedTopic == null) {
            cli.printMessage("Topic not found. Ensure the topic name matches exactly.");
            cli.printMessage("Topic not found: " + topicName);
            return;
        }

        boolean quizStarted = false;
        if (quizType == QuizType.TIMED) {
            quizStarted = quizSession.selectTimedQuiz(matchedTopic);
        } else {
            quizStarted = quizSession.selectUntimedQuiz(matchedTopic);
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

    public void addResultsAndPrintScore() {
        String topicName = quizSession.getTopicName();
        int completedQuizScore = quizSession.getQuizScore();
        int questionLimit = quizSession.getQuestionLimit();
        int timeLimit = quizSession.getTimeLimitInSeconds();

        if (quizResults.getAllResults().stream().noneMatch(result -> result.getTopic().equalsIgnoreCase(topicName)
                && result.getScore() == completedQuizScore)) {
            quizResults.addResult(topicName, completedQuizScore, questionLimit, timeLimit);
            String comment = quizResults.generateComment(completedQuizScore);
            cli.printMessage("Quiz finished. Your score is: " + completedQuizScore + "%");
            cli.printMessage("Comment: " + comment);
        }
    }

    private void loadDataFromFile() {
        topicManager.loadQuestions();
        topicManager.loadFlashcards();
        quizResults.loadResults();
    }

    public List<String> getQuizzesAvailable() {
        return topicManager.getTopicNames();
    }

    public void reviewResultsByTopic(String topic, boolean isNewestFirst, boolean sortByScore, boolean sortByLowestScore) {
        List<QuizResults.Result> filteredResults = quizResults.getResultsByTopic(topic);

        if (sortByScore) {
            filteredResults = quizResults.sortByScore(filteredResults);
        } else if (sortByLowestScore) {
            filteredResults = quizResults.sortByLowestScore(filteredResults);
        } else {
            filteredResults = quizResults.sortByDate(filteredResults, isNewestFirst);
        }

        for (QuizResults.Result result : filteredResults) {
            cli.printMessage(result.toString());
        }
    }

    public void reviewAllResults(boolean isNewestFirst, boolean sortByScore, boolean sortByLowestScore) {
        List<QuizResults.Result> allResults = quizResults.getAllResults();

        if (sortByScore) {
            allResults = quizResults.sortByScore(allResults);
        } else if (sortByLowestScore) {
            allResults = quizResults.sortByLowestScore(allResults);
        } else {
            allResults = quizResults.sortByDate(allResults, isNewestFirst);
        }

        for (QuizResults.Result result : allResults) {
            cli.printMessage(result.toString());
        }
    }

    public void addInput(String input) {
        try {
            topicManager.addFlashcardByUser(input);
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }

    public void saveResults() {
        cli.printMessage("Saving functionality is not yet implemented.");
    }

    public QuizGenerator getQuizSession() {
        if (this.quizSession == null) {
            this.quizSession = new QuizGenerator(topicManager, cli);
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
