package seedu.javaninja.question;

import java.util.List;

/**
 * Represents a fill-in-the-blank question.
 * This type of question requires the user to provide an answer that fills a blank space in the question.
 */
public class FillInTheBlank extends Question {

    /**
     * Constructs a FillInTheBlank question with the given question text and answer.
     *
     * @param question The question text with a blank to be filled.
     * @param answer   The correct answer for the fill-in-the-blank question.
     */
    public FillInTheBlank(String question, String answer) {
        super(question, answer);
    }

    /**
     * Checks if the provided answer matches the correct answer for the question.
     *
     * @param answer The answer provided by the user.
     * @return True if the provided answer matches the correct answer, otherwise false.
     */
    @Override
    public boolean checkAnswer(String answer) {
        // Normalize the user's answer and the correct answer for comparison
        String normalizedUserAnswer = normalizeAnswer(answer);
        String normalizedCorrectAnswer = normalizeAnswer(correctAnswer);
        return normalizedUserAnswer.equals(normalizedCorrectAnswer);
    }

    /**
     * Returns null as fill-in-the-blank questions do not have options.
     *
     * @return Null, indicating no options are present.
     */
    @Override
    public List<String> getOptions() {
        // No options to print for fill-in-the-blank questions
        return null;
    }

    /**
     * Normalizes the answer by converting it to lowercase and trimming leading/trailing whitespace.
     *
     * @param answer The answer to normalize.
     * @return The normalized answer.
     */
    private String normalizeAnswer(String answer) {
        if (answer == null) {
            return "";
        }
        // Convert to lowercase and strip any leading or trailing whitespace
        return answer.toLowerCase().trim();
    }

    /**
     * Returns the question type identifier for fill-in-the-blank questions.
     *
     * @return A string "FITB" representing the question type.
     */
    public String getQuestionType() {
        return "FITB";
    }
}
