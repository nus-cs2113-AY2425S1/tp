package seedu.javaninja;

public class TrueFalse extends Question {
    private boolean correctAnswer;

    public TrueFalse(String questionText, boolean correctAnswer) {
        super(questionText, Boolean.toString(correctAnswer));
        assert questionText != null && !questionText.trim().isEmpty() : "Question text must not be null or empty";
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        assert userAnswer != null && !userAnswer.trim().isEmpty() : "User answer must not be null or empty";

        boolean userAnswerBoolean;
        if (userAnswer.equalsIgnoreCase("true")) {
            userAnswerBoolean = true;
        } else if (userAnswer.equalsIgnoreCase("false")) {
            userAnswerBoolean = false;
        } else {
            throw new IllegalArgumentException("Invalid input! Please enter 'true' or 'false'.");
        }

        assert (userAnswerBoolean == true || userAnswerBoolean == false) : "Invalid user answer";
        return userAnswerBoolean == this.correctAnswer;
    }

    @Override
    public void printOptions() {
        System.out.println("1. True");
        System.out.println("2. False");
    }

    @Override
    public String toString() {
        assert this.getText() != null : "Question text should not be null";
        return this.getText() + " (True/False)";
    }
}
