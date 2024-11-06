package seedu.javaninja.question;

/* Represents a question that could be of different types (MCQ / True or False) */

import java.util.List;

public abstract class Question {
    protected String text;
    protected String correctAnswer;

    public Question(String text, String answer) {
        this.text = text;
        this.correctAnswer = answer;
    }

    public String getText() {
        return this.text;
    }

    public String getCorrectAnswer() {
        return this.correctAnswer;
    }

    public abstract boolean checkAnswer(String answer);

    public abstract List<String> getOptions();
}
