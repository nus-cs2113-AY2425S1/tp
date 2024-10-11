package seedu.javaninja;

/* Represents a question that could be of different types (MCQ / True or False) */

public abstract class Question {
    protected String text;
    protected String correctAnswer;

    public Question(String text, String answer) {
        this.text = text;
        this.correctAnswer = answer;
    }

    public abstract boolean checkAnswer(String answer);

    public abstract void printOptions();

}
