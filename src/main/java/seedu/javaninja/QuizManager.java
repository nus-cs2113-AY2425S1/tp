package seedu.javaninja;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class QuizManager {
    private static final String FILE_PATH = "./data/Questions.txt";
    private static final Logger logger = Logger.getLogger(QuizManager.class.getName());
    private List<Topic> topics;
    private Quiz currentQuiz;
    private List<String> pastResults;
    private Storage storage;

    public QuizManager() {
        this.topics = new ArrayList<>();
        this.pastResults = new ArrayList<>();
        this.storage = new Storage("data/results.txt");
        loadTopicsFromFile();
        loadResultsFromFile();
    }

    private void loadTopicsFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            logger.warning("Questions file not found. No topics loaded.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    parseTopic(line);
                }
            }
        } catch (IOException e) {
            logger.severe("Error reading file: " + e.getMessage());
        }
    }

    private Topic getOrCreateTopic(String topicName) {
        for (Topic topic : topics) {
            if (topic.getName().equalsIgnoreCase(topicName)) {
                return topic;
            }
        }
        Topic newTopic = new Topic(topicName);
        topics.add(newTopic);
        return newTopic;
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
        case "Flashcard":
            topic.addQuestion(new Flashcard(questionText, correctAnswer));
            break;
        default:
            logger.warning("Invalid question type: " + questionType);
        }
    }

    public void selectTopic(String topicName, Scanner scanner) {
        if (topicName == null || topicName.trim().isEmpty()) {
            logger.warning("Invalid input. Please provide a topic name.");
            return;
        }

        for (Topic topic : topics) {
            if (topic.getName().equalsIgnoreCase(topicName.trim())) {
                startQuiz(topic, scanner);
                return;
            }
        }
        logger.warning("No such topic: " + topicName);
    }

    public void startQuiz(Topic topic, Scanner scanner) {
        currentQuiz = new Quiz(topic, scanner);
        currentQuiz.start();
        int score = currentQuiz.getScore();
        String comment = generateComment(score);
        addPastResult(score, comment);
        saveResultsToFile();
    }

    public void printTopics() {
        if (topics.isEmpty()) {
            System.out.println("No topics available.");
        } else {
            logger.info("Listing all available topics:");
            topics.forEach(topic -> System.out.println(topic.getName()));
        }
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

    public int getTopicsCount() {
        return topics.size();
    }

    public void removeTopic(Topic topic) {
        if (topic == null) {
            logger.warning("Cannot remove a null topic.");
            return;
        }
        if (topics.remove(topic)) {
            logger.info("Removed topic: " + topic.getName());
        } else {
            logger.warning("Topic not found: " + topic.getName());
        }
    }

    private void addPastResult(int score, String comment) {
        pastResults.add("Score: " + score + "%, Comment: " + comment);
    }

    private String generateComment(int score) {
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

    public String getPastResults() {
        if (pastResults.isEmpty()) {
            return "No past results available. You haven't completed any quizzes yet.";
        }

        StringBuilder results = new StringBuilder();
        pastResults.forEach(result -> results.append(result).append("\n"));
        return results.toString();
    }

    private void saveResultsToFile() {
        try {
            storage.saveResults(pastResults);
        } catch (IOException e) {
            logger.severe("Error saving results to file: " + e.getMessage());
        }
    }

    private void loadResultsFromFile() {
        try {
            pastResults = storage.loadResults();
        } catch (IOException e) {
            logger.warning("No past results found.");
        }
    }

    public void addQuestionByUser(String input) throws IOException {
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
            logger.info("Added new Flashcard question.");

            String questionLine = "Flashcards | Flashcard | " + questionText + " | " + correctAnswer;
            saveQuestionToFile(questionLine);
        } else {
            logger.warning("Invalid command: " + input);
        }
    }

    public void saveQuestionToFile(String questionLine) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(questionLine);
            writer.newLine();
            logger.info("Question saved to file: " + FILE_PATH);
        } catch (IOException e) {
            logger.severe("Error saving question to file: " + e.getMessage());
        }
    }
}
