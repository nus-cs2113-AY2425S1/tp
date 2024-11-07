package seedu.javaninja;

import seedu.javaninja.question.FillInTheBlank;
import seedu.javaninja.question.Flashcard;
import seedu.javaninja.question.TrueFalse;
import seedu.javaninja.question.Mcq;
import seedu.javaninja.question.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TopicManager {
    private static final Logger logger = Logger.getLogger(TopicManager.class.getName());
    private static final String QUESTIONS_FILE_PATH = "./data/Questions.txt";
    private static final String FLASHCARDS_FILE_PATH = "./data/Flashcards.txt";
    private static Storage questions;
    private static Storage flashcards;
    private List<Topic> topics;
    private Cli cli;

    public TopicManager(Cli cli) {
        this.topics = new ArrayList<>();
        this.questions = new Storage(QUESTIONS_FILE_PATH);
        this.flashcards = new Storage(FLASHCARDS_FILE_PATH);
        this.cli = cli;
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
            if (topic.getName().equalsIgnoreCase(topicName)) {
                return topic;
            }
        }
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

        // Split first by "/q" to separate the command from the question and answer
        String[] questionSplit = input.split("/q", 2);
        String[] answerSplit = questionSplit[1].split("/a", 2);

        if (answerSplit.length < 2) {
            cli.printMessage("Invalid command format. Please provide both question and answer.");
            return;
        }

        // Extract the question and answer text, and trim to remove extra whitespace
        String questionText = answerSplit[0].trim();
        String correctAnswer = answerSplit[1].trim();

        // Add the Flashcard to the "Flashcards" topic
        Topic topic = getOrCreateTopic("Flashcards");
        topic.addQuestion(new Flashcard(questionText, correctAnswer));

        cli.printMessage("Added flashcard: " + "Q: " + questionText + " A: " + correctAnswer);
    }

    public boolean isFlashcardsSaved () {
        Topic flashcardsTopic = getTopic("Flashcards");

        if (flashcardsTopic == null) {
            logger.warning("No 'flashcards' topic found to save.");
            return false;
        }

        List<String> flashcardLines = new ArrayList<>();

        for (Question question : flashcardsTopic.getQuestions()) {
            if (question instanceof Flashcard) {
                Flashcard flashcard = (Flashcard) question;

                String questionLine = String.format("Flashcards | Fc | %s | %s",
                    flashcard.getText(),
                    flashcard.getCorrectAnswer());

                flashcardLines.add(questionLine);
            }
        }

        try {
            questions.saveToFile(FLASHCARDS_FILE_PATH, flashcardLines, false);
            logger.info("All flashcards saved successfully");
            return true;
        } catch (IOException e) {
            logger.warning("Failed to save flashcards");
            return false;
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
        } catch (IOException e) {
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
        } catch (IOException e) {
            logger.warning("No questions found.");
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
