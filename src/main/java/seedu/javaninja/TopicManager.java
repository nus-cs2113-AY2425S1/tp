package seedu.javaninja;

import seedu.javaninja.question.FillInTheBlank;
import seedu.javaninja.question.Flashcard;
import seedu.javaninja.question.Mcq;
import seedu.javaninja.question.TrueFalse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class TopicManager {
    private static final Logger logger = Logger.getLogger(TopicManager.class.getName());
    private List<Topic> topics;
    private static final String QUESTIONS_FILE_PATH = "./data/Questions.txt";
    private static Storage questions;

    public TopicManager() {
        this.topics = new ArrayList<>();
        this.questions = new Storage(QUESTIONS_FILE_PATH);
    }

    public Topic getOrCreateTopic(String topicName) {
        for (Topic topic : topics) {
            if (topic.getName().equalsIgnoreCase(topicName)) {
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

    public void printTopics() {
        if (topics.isEmpty()) {
            System.out.println("No topics available.");
        } else {
            logger.info("Listing all available topics:");
            topics.forEach(topic -> System.out.println(topic.getName()));
        }
    }

    public void addFlashcardByUser(String input) {
        if (input.startsWith("add Flashcard")) {
            String[] parts = input.split("/q|/a");
            if (parts.length < 3) {
                System.out.println("Invalid command format. Please provide both question and answer.");
                return;
            }

            String questionText = parts[1].trim();
            String correctAnswer = parts[2].trim();

            Topic topic = getOrCreateTopic("Flashcards");
            topic.addQuestion(new Flashcard(questionText, correctAnswer));

            String questionLine = "Flashcards | Flashcard | " + questionText + " | " + correctAnswer;
            try {
                questions.saveToFile(QUESTIONS_FILE_PATH, Collections.singletonList(questionLine), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        case "Flashcard":
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