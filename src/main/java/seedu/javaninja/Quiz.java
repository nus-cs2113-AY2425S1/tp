package seedu.javaninja;

import java.util.List;
import java.util.Scanner;

public class Quiz {
    private Topic topic;
    private int currentQuestionIndex;
    private int correctAnswers;
    private Scanner scanner;

    public Quiz(Topic topic) {
        this.topic = topic;
        this.currentQuestionIndex = 0;
        this.correctAnswers = 0;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        List<Question> questions = topic.getQuestions();

        while (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            System.out.println(currentQuestion.text);
            currentQuestion.printOptions();
            System.out.println("Enter your answer");
            String answer = scanner.nextLine();
            answerQuestion(answer);
            currentQuestionIndex++;
        }

        System.out.println("Quiz finished. Your score is: " + getScore() + "%");
    }

    public void answerQuestion (String answer) {
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
