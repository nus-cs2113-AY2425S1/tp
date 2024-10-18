package seedu.javaninja;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

public class QuizManager {
    private static final String FILE_PATH = "./data/Questions.txt";
    private List<Topic> topics;
    private Quiz currentQuiz;
    private List<String> pastResults;
    private Storage storage; // Storage for saving/loading results

    public QuizManager() {
        this.topics = new ArrayList<>();
        this.pastResults = new ArrayList<>();
        this.storage = new Storage("data/results.txt"); // File path for saving results
        loadTopicsFromFile();
        loadResultsFromFile();  // Load past results at startup
    }

    private void loadTopicsFromFile() {
        File file = new File(FILE_PATH);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                parseTopic(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
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

        String topicName = parts[0].trim();
        String questionType = parts[1].trim();
        String questionText = parts[2].trim();
        String correctAnswer = parts[3].trim();

        Topic topic = getOrCreateTopic(topicName);
        switch (questionType) {
        case "Mcq":
            List<String> options = new ArrayList<>();
            for (int i = 4; i < parts.length; i++) {
                options.add(parts[i]);
            }
            topic.addQuestion(new Mcq(questionText, correctAnswer, options));
            break;
        default:
            System.out.println("Invalid question type");
        }
    }

    public void selectTopic(String topicName) {
        for (Topic topic : topics) {
            if (topic.getName().equals(topicName)) {
                startQuiz(topic);
                return;
            }
        }
        System.out.println("There is no such topic");
    }

    public void startQuiz(Topic topic) {
        currentQuiz = new Quiz(topic);
        currentQuiz.start();
        int score = currentQuiz.getScore();
        String comment = generateComment(score);
        addPastResult(score, comment);
        saveResultsToFile();  // Save results after quiz ends
    }

    public void printTopics() {
        System.out.println("Available Topics: ");
        for (Topic topic : topics) {
            System.out.println(topic.getName());
        }
    }

    // Adds a new topic to the list of topics
    public void addTopic(Topic topic) {
        topics.add(topic);
    }

    // Returns the current number of topics
    public int getTopicsCount() {
        return topics.size();
    }

    // Removes an existing topic from the list
    public void removeTopic(Topic topic) {
        topics.remove(topic);
    }

    // Add past results (score and comment)
    private void addPastResult(int score, String comment) {
        pastResults.add("Score: " + score + "%, Comment: " + comment);
    }

    // Generates a comment based on the quiz score
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

    // Method to review past results
    public String getPastResults() {
        if (pastResults.isEmpty()) {
            return "No past results available. You haven't completed any quizzes yet.";
        }

        StringBuilder results = new StringBuilder();
        for (String result : pastResults) {
            results.append(result).append("\n");
        }
        return results.toString();
    }

    // Save the past results using Storage
    private void saveResultsToFile() {
        try {
            storage.saveResults(pastResults);
        } catch (IOException e) {
            System.out.println("Error saving results to file.");
        }
    }

    // Load past results from the file using Storage
    private void loadResultsFromFile() {
        try {
            pastResults = storage.loadResults();
        } catch (IOException e) {
            System.out.println("No past results found.");
        }
    }
}
