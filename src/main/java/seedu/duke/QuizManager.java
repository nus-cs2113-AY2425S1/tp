package seedu.duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuizManager {
    private List<Topic> topics;
    private Quiz currentQuiz;

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
    }

    public void printTopics() {
        System.out.println("Available Topics: ");
        for (Topic topic : topics) {
            System.out.println(topic.getName());
        }
    }


}
