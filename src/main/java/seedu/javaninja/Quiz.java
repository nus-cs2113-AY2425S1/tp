package seedu.javaninja;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Quiz {
    private Topic topic;
    private int currentQuestionIndex;
    private int correctAnswers;
    private Scanner scanner;
    private static final Logger logger = Logger.getLogger(Quiz.class.getName());

    public Quiz(Topic topic) {
        assert topic != null : "Topic must not be null";
        this.topic = topic;
        this.currentQuestionIndex = 0;
        this.correctAnswers = 0;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        logger.info("Quiz started for topic: " + topic.getName());
        assert topic.getQuestions() != null && !topic.getQuestions().isEmpty()
            : "Quiz must have questions";
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
        logger.info("Quiz finished for topic: " + topic.getName() + ". Score: " + getScore() + "%");
    }

    public void answerQuestion (String answer) {
        assert currentQuestionIndex < topic.getQuestions().size() : "Question index out of bounds";
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
