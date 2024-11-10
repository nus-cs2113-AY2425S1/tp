package seedu.javaninja.question;

/*
 * Abstract class representing a question in a quiz. The question can be of different types such as
 * multiple-choice, true/false, or fill-in-the-blank.
 */
import java.util.List;

public abstract class Question {
    protected String text;           // The text of the question
    protected String correctAnswer;  // The correct answer for the question

    /**
     * Constructs a `Question` object with the specified question text and correct answer.
     *
     * @param text   The question text.
     * @param answer The correct answer for the question.
     */
    public Question(String text, String answer) {
        this.text = text;
        this.correctAnswer = answer;
    }

    /**
     * Retrieves the text of the question.
     *
     * @return The question text.
     */
    public String getText() {
        return this.text;
    }

    /**
     * Retrieves the correct answer for the question.
     *
     * @return The correct answer.
     */
    public String getCorrectAnswer() {
        return this.correctAnswer;
    }

    /**
     * Abstract method to check if the provided answer is correct.
     * Must be implemented by subclasses.
     *
     * @param answer The answer provided by the user.
     * @return True if the answer is correct, otherwise false.
     */
    public abstract boolean checkAnswer(String answer);

    /**
     * Abstract method to retrieve options for the question.
     * Must be implemented by subclasses.
     *
     * @return A list of options for the question.
     */
    public abstract List<String> getOptions();
}
