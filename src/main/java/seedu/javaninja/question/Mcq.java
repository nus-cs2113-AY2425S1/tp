package seedu.javaninja.question;

import java.util.List;

/**
 * Represents a multiple-choice question with a question text, correct answer, and a list of options.
 */
public class Mcq extends Question {
    private List<String> options;  // The list of options for the multiple-choice question

    /**
     * Constructs an Mcq (Multiple Choice Question) object with the specified question text,
     * correct answer, and list of options.
     *
     * @param text          The question text.
     * @param correctAnswer The correct answer for the question.
     * @param options       The list of options for the question.
     */
    public Mcq(String text, String correctAnswer, List<String> options) {
        super(text, correctAnswer);
        this.options = options;
    }

    /**
     * Checks if the provided answer matches the correct answer for the multiple-choice question.
     * The answer must be a valid choice ('a', 'b', 'c', or 'd').
     *
     * @param answer The answer provided by the user.
     * @return True if the provided answer matches the correct answer, otherwise false.
     * @throws IllegalArgumentException If the answer provided is not a valid choice.
     */
    @Override
    public boolean checkAnswer(String answer) {
        if (!answer.matches("[abcdABCD]")) {
            throw new IllegalArgumentException("Invalid answer. Please enter only 'a', 'b', 'c', or 'd'.");
        }

        // Check if the answer matches the correct answer
        return correctAnswer.equalsIgnoreCase(answer);
    }

    /**
     * Returns the list of options for the multiple-choice question.
     *
     * @return The list of options.
     */
    @Override
    public List<String> getOptions() {
        return options;
    }
}
