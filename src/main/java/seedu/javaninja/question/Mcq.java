package seedu.javaninja.question;

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
    // Returns formatted options as a single string
    public List<String> getOptions() {
        return options;
    }
}
