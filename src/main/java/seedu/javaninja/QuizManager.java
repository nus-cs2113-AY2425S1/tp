package seedu.javaninja;

import seedu.javaninja.question.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class QuizManager {
    private static final String QUESTIONS_FILE_PATH = "./data/Questions.txt";
    private static final String RESULTS_FILE_PATH = "./data/results.txt";
    private static final Logger logger = Logger.getLogger(QuizManager.class.getName());
    private List<Topic> topics;
    private Quiz currentQuiz;
    private List<String> pastResults;
    private Storage results;
    private Storage questions;

    public QuizManager() {
        this.topics = new ArrayList<>();
        this.pastResults = new ArrayList<>();
        this.results = new Storage(RESULTS_FILE_PATH);
        this.questions = new Storage(QUESTIONS_FILE_PATH);
        loadDataFromFile();
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

    public void selectTopic(String topicName) {
        if (topicName == null || topicName.trim().isEmpty()) {
            logger.warning("Invalid input. Please provide a topic name.");
            return;
        }

        for (Topic topic : topics) {
            if (topic.getName().equalsIgnoreCase(topicName.trim())) {
                startQuiz(topic);
                return;
            }
        }
        logger.warning("No such topic: " + topicName);
    }

    public void startQuiz(Topic topic) {
        Scanner quizScanner = new Scanner(System.in);
        currentQuiz = new Quiz(topic, quizScanner);
        currentQuiz.start();
        int score = currentQuiz.getScore();
        String comment = generateComment(score);
        addPastResult(score, comment);
        saveDataToFile();
    }

    public void printTopics() {
        if (topics.isEmpty()) {
            System.out.println("No topics available.");
        } else {
            //logger.info("Listing all available topics:");
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

    public void saveDataToFile() {
        try {
            results.saveToFile(RESULTS_FILE_PATH, pastResults, false);
        } catch (IOException e) {
            logger.severe("Error saving results to file: " + e.getMessage());
        }
    }

    public void loadDataFromFile() {
        try {
            pastResults = results.loadData();
            // load questions from file
        } catch (IOException e) {
            logger.warning("No past results found.");
        }
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


    public void addFlashcardByUser(String input) throws IOException {
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
            questions.saveToFile(QUESTIONS_FILE_PATH, Collections.singletonList(questionLine), true);

        } else {
            logger.warning("Invalid command: " + input);
        }
    }

}
