package seedu.javaninja.question;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a True/False question in a quiz.
 * Stores a boolean correct answer and verifies the user's answer.
 */
public class TrueFalse extends Question {
    private boolean correctAnswer;  // The correct answer for the True/False question

    /**
     * Constructs a `TrueFalse` question with the specified question text and correct answer.
     *
     * @param questionText   The text of the True/False question.
     * @param correctAnswer  The correct answer as a boolean.
     * @throws AssertionError if the question text is null or empty.
     */
    public TrueFalse(String questionText, boolean correctAnswer) {
        super(questionText, Boolean.toString(correctAnswer));
        assert questionText != null && !questionText.trim().isEmpty() : "Question text must not be null or empty";
        this.correctAnswer = correctAnswer;
    }

    /**
     * Checks if the user's answer matches the correct answer.
     *
     * @param userAnswer The answer provided by the user as a string.
     * @return True if the user's answer is correct, false otherwise.
     * @throws IllegalArgumentException if the input is not 'true' or 'false'.
     */
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

        return userAnswerBoolean == this.correctAnswer;
    }

    /**
     * Provides the answer options for the True/False question.
     *
     * @return A list containing "true" and "false" as options.
     */
    @Override
    public List<String> getOptions() {
        List<String> options = new ArrayList<>();
        options.add("true");
        options.add("false");
        return options;
    }

    /**
     * Returns a formatted string representation of the True/False question.
     *
     * @return A string containing the question text followed by "(True/False)".
     */
    @Override
    public String toString() {
        assert this.getText() != null : "Question text should not be null";
        return this.getText() + " (True/False)";
    }
}
