package seedu.javaninja;

import java.util.List;
import java.util.Scanner;

public class Quiz {
    private Topic topic;
    private int currentQuestionIndex;
    private int correctAnswers;
    private Scanner scanner;

    public Quiz(Topic topic, Scanner scanner) {
        assert topic != null : "Topic must not be null";
        assert !topic.getQuestions().isEmpty() : "Topic must contain at least one question";
        this.topic = topic;
        this.scanner = scanner;
        this.currentQuestionIndex = 0;
        this.correctAnswers = 0;
    }

    public void start() {
        List<Question> questions = topic.getQuestions();

        if (questions.isEmpty()) {
            throw new IllegalStateException("Cannot start a quiz with no questions.");
        }

        while (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            assert currentQuestion != null : "Current question must not be null";

            System.out.println(currentQuestion.text);
            currentQuestion.printOptions();
            System.out.print("Enter your answer: ");
            String answer = scanner.nextLine().trim();

            answerQuestion(answer);
            currentQuestionIndex++;
        }

        System.out.println("Quiz finished. Your score is: " + getScore() + "%");
    }

    public void answerQuestion(String answer) {
        assert currentQuestionIndex < topic.getQuestions().size() : "Question index out of bounds";
        assert answer != null && !answer.trim().isEmpty() : "Answer must not be null or empty";

        Question currentQuestion = topic.getQuestions().get(currentQuestionIndex);
        if (currentQuestion.checkAnswer(answer)) {
            System.out.println("Correct!");
            correctAnswers++;
        } else {
            System.out.println("Incorrect!");
        }
    }

    public int getScore() {
        int totalQuestions = topic.getQuestions().size();
        return (int) ((double) correctAnswers / totalQuestions * 100);
    }
}
