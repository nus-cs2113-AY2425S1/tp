package seedu.javaninja;

import seedu.javaninja.question.FillInTheBlank;
import seedu.javaninja.question.Flashcard;
import seedu.javaninja.question.TrueFalse;
import seedu.javaninja.question.Mcq;
import seedu.javaninja.question.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TopicManager {
    private static final Logger logger = Logger.getLogger(TopicManager.class.getName());
    private static final String QUESTIONS_FILE_PATH = "./data/Questions.txt";
    private static final String FLASHCARDS_FILE_PATH = "./data/Flashcards.txt";
    private static final Storage questions = new Storage(QUESTIONS_FILE_PATH);
    private static final Storage flashcards = new Storage(FLASHCARDS_FILE_PATH);
    private final List<Topic> topics;
    private final Cli cli;

    public TopicManager(Cli cli) {
        this.topics = new ArrayList<>();
        this.cli = cli;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public Topic getOrCreateTopic(String topicName) {
        for (Topic topic : topics) {
            if (topic.getName().equals(topicName)) {
                return topic;
            }
        }
        Topic newTopic = new Topic(topicName);
        topics.add(newTopic);
        return newTopic;
    }

    public Topic getTopic(String topicName) {
        for (Topic topic : topics) {
            if (topic.getName().equals(topicName)) {
                return topic;
            }
        }
        cli.printMessage("Topic not found. Ensure the topic name matches exactly.");
        return null;
    }

    public void addTopic(Topic topic) {
        if (topic == null) {
            logger.warning("Cannot add a null topic.");
            return;
        }
        if (!topics.contains(topic)) {
            topics.add(topic);
            logger.info("Added topic: " + topic.getName());
        } else {
            logger.warning("Topic already exists: " + topic.getName());
        }
    }

    public List<String> getTopicNames() {
        List<String> topicNames = new ArrayList<>();
        for (Topic topic : topics) {
            topicNames.add(topic.getName());
        }
        return topicNames;
    }

    public void addFlashcardByUser(String input) {
        if (!input.startsWith("add Flashcards") || !input.contains("/q") || !input.contains("/a")) {
            cli.printMessage("Invalid command format. Use: add Flashcards /q [question] /a [answer]");
            return;
        }

        String commandPart = input.substring(0, input.indexOf("/q")).trim();
        if (!commandPart.equals("add Flashcards")) {
            cli.printMessage("Invalid command format. Use only 'add Flashcards /q [question] /a [answer]'.");
            return;
        }

        String[] questionSplit = input.split("/q", 2);
        if (questionSplit.length < 2 || questionSplit[1].isEmpty()) {
            cli.printMessage("Invalid command format. Please provide a question after '/q'.");
            return;
        }

        String[] answerSplit = questionSplit[1].split("/a", 2);
        if (answerSplit.length < 2 || answerSplit[0].trim().isEmpty() || answerSplit[1].trim().isEmpty()) {
            cli.printMessage("Invalid command format. Please provide both question and answer.");
            return;
        }

        String questionText = answerSplit[0].trim();
        String correctAnswer = answerSplit[1].trim();

        if (questionText.contains("/") || correctAnswer.contains("/")) {
            cli.printMessage("Invalid command format. Unexpected flag detected in question or answer.");
            return;
        }

        Topic topic = getOrCreateTopic("Flashcards");
        topic.addQuestion(new Flashcard(questionText, correctAnswer));

        cli.printMessage("Added flashcard: " + "Q: " + questionText + " A: " + correctAnswer);

        saveFlashcards();
    }

    private void saveFlashcards() {
        Topic flashcardsTopic = getTopic("Flashcards");
        if (flashcardsTopic == null) {
            logger.warning("No 'Flashcards' topic found to save.");
            return;
        }

        List<String> flashcardLines = new ArrayList<>();
        for (Question question : flashcardsTopic.getQuestions()) {
            if (question instanceof Flashcard flashcard) {
                String questionLine = String.format("Flashcards | Fc | %s | %s",
                        flashcard.getText(), flashcard.getCorrectAnswer());
                flashcardLines.add(questionLine);
            }
        }

        try {
            flashcards.saveToFile(flashcardLines, false);
            logger.info("All flashcards saved successfully.");
        } catch (Exception e) {
            logger.warning("Failed to save flashcards: " + e.getMessage());
        }
    }

    public void loadQuestions() {
        try {
            List<String> questionData = questions.loadData();
            for (String question : questionData) {
                if (!question.trim().isEmpty()) {
                    parseTopic(question);
                }
            }
        } catch (Exception e) {
            logger.warning("No questions found.");
        }
    }

    public void loadFlashcards() {
        try {
            List<String> flashcardData = flashcards.loadData();
            for (String flashcard : flashcardData) {
                if (!flashcard.trim().isEmpty()) {
                    parseTopic(flashcard);
                }
            }
        } catch (Exception e) {
            logger.warning("No flashcards found.");
        }
    }

    public void parseTopic(String line) {
        String[] parts = line.split("\\|");
        if (parts.length < 4) {
            logger.warning("Invalid line format: " + line);
            return;
        }

        String topicName = parts[0].trim();
        String questionType = parts[1].trim();
        String questionText = parts[2].trim();
        String correctAnswer = parts[3].trim();

        Topic topic = getOrCreateTopic(topicName);

        switch (questionType) {
        case "Mcq":
            List<String> options = new ArrayList<>();
            for (int i = 4; i < parts.length; i++) {
                options.add(parts[i].trim());
            }
            topic.addQuestion(new Mcq(questionText, correctAnswer, options));
            break;
        case "TrueFalse":
            boolean correctAnswerBoolean = Boolean.parseBoolean(correctAnswer);
            topic.addQuestion(new TrueFalse(questionText, correctAnswerBoolean));
            break;
        case "Fc":
            topic.addQuestion(new Flashcard(questionText, correctAnswer));
            break;
        case "FillInTheBlank":
            topic.addQuestion(new FillInTheBlank(questionText, correctAnswer));
            break;
        default:
            logger.warning("Invalid question type: " + questionType);
        }
    }
}
