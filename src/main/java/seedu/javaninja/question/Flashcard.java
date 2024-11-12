package seedu.javaninja.question;

import java.util.List;

/**
 * Represents a flashcard with a question and an answer.
 * This type of question is typically used for self-study and revision.
 */
public class Flashcard extends Question {

    /**
     * Constructs a Flashcard question with the given question text and answer.
     *
     * @param question The question text for the flashcard.
     * @param answer   The correct answer for the flashcard question.
     */
    public Flashcard(String question, String answer) {
        super(question, answer);
    }

    /**
     * Checks if the provided answer matches the correct answer for the flashcard.
     * This comparison is case-insensitive.
     *
     * @param answer The answer provided by the user.
     * @return True if the provided answer matches the correct answer, otherwise false.
     */
    @Override
    public boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }

    /**
     * Returns null as flashcards do not have multiple-choice options.
     *
     * @return Null, indicating no options are present.
     */
    @Override
    public List<String> getOptions() {
        return null;
    }

    /**
     * Returns the question type identifier for flashcards.
     *
     * @return A string "Flashcards" representing the question type.
     */
    public String getQuestionType() {
        return "Flashcards";
    }
}
