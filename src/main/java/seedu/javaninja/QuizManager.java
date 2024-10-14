package seedu.javaninja;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuizManager {
    private List<Topic> topics;
    private Quiz currentQuiz;
    private QuizManager quizManager;
    private List<String> pastResults;

    public QuizManager() {
        this.topics = new ArrayList<>();
        loadTopics();
    }

    private void loadTopics() {
        Topic loopsTopic = new Topic("Loops");
        loopsTopic.addQuestion(new Mcq(
                "Which of the following is a loop structure in Java?",
                "b",
                Arrays.asList("a) if-else", "b) for", "c) switch", "d) try-catch")));
        topics.add(loopsTopic);
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
        if (pastResults == null || pastResults.isEmpty()) {
            return "No past results available. You haven't completed any quizzes yet.";
        }

        StringBuilder results = new StringBuilder();
        for (String result : pastResults) {
            results.append(result).append("\n");
        }
        return results.toString();
    }
}
