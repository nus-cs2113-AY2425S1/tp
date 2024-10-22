package seedu.javaninja;

public class Flashcard extends Question {

    public Flashcard(String question, String answer) {
        super(question, answer);
    }

    @Override
    public boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }

    @Override
    public void printOptions() {
        ;
    }

    public String getQuestionType () {
        return "FC";
    }





}
