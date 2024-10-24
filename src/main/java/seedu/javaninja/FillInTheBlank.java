package seedu.javaninja;

public class FillInTheBlank extends Question {

    public FillInTheBlank(String question, String answer) {
        super(question, answer);
    }

    @Override
    public boolean checkAnswer(String answer) {
        // Normalize the user's answer and the correct answer for comparison
        String normalizedUserAnswer = normalizeAnswer(answer);
        String normalizedCorrectAnswer = normalizeAnswer(correctAnswer);
        return normalizedUserAnswer.equals(normalizedCorrectAnswer);
    }

    @Override
    public void printOptions() {
        // No options to print for fill-in-the-blank questions
        ;
    }

    // Method to normalize the answer for comparison
    private String normalizeAnswer(String answer) {
        if (answer == null) {
            return "";
        }
        // Convert to lowercase and strip any leading or trailing whitespace
        return answer.toLowerCase().trim();
    }

    public String getQuestionType() {
        return "FITB";
    }
}
