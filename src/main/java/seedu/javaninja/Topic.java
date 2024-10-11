package seedu.javaninja;

import java.util.ArrayList;
import java.util.List;

/* Represents a programming topic */
public class Topic {
    private String name;
    private List<Question> questions;

    public Topic (String name) {
        this.name = name;
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public String getName() {
        return name;
    }

}
