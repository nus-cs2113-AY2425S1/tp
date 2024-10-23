package seedu.javaninja;

public class TrueFalse extends Question {
    private boolean correctAnswer;

    public TrueFalse(String questionText, boolean correctAnswer) {
        super(questionText, Boolean.toString(correctAnswer));
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        boolean userAnswerBoolean;
        if (userAnswer.equalsIgnoreCase("true")) {
            userAnswerBoolean = true;
        } else if (userAnswer.equalsIgnoreCase("false")) {
            userAnswerBoolean = false;
        } else {
            throw new IllegalArgumentException("Invalid input! Please enter 'true' or 'false'.");
        }
        return userAnswerBoolean == this.correctAnswer;
    }

    @Override
    public void printOptions() {
        System.out.println("1. True");
        System.out.println("2. False");
    }

    @Override
    public String toString() {
        return this.getText() + " (True/False)";
    }
}
