package seedu.javaninja;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

public class QuizManager {
    private List<Topic> topics;
    private Quiz currentQuiz;
    private QuizManager quizManager;
    private final static String FILE_PATH = "./data/Questions.txt";

    public QuizManager() {
        this.topics = new ArrayList<>();
        loadTopicsFromFile();
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

}
