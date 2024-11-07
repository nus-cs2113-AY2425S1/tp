package seedu.javaninja.question;

import java.util.List;

public class Flashcard extends Question {

    public Flashcard(String question, String answer) {
        super(question, answer);
    }

    @Override
    public boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }

    @Override
    public List<String> getOptions() {
        return null;
    }

    public String getQuestionType () {
        return "Flashcards";
    }
}
