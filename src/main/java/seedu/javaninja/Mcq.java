package seedu.javaninja;

import java.util.List;

public class Mcq extends Question {
    private List<String> options;

    public Mcq(String text, String correctAnswer, List<String> options) {
        super(text, correctAnswer);
        this.options = options;
    }

    @Override
    public boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }

    @Override
    public void printOptions() {
        for (String option : options) {
            System.out.println(option);
        }
    }

}
