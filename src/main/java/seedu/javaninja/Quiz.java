package seedu.javaninja;

import seedu.javaninja.question.FillInTheBlank;
import seedu.javaninja.question.Mcq;
import seedu.javaninja.question.Question;
import seedu.javaninja.question.TrueFalse;

import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class Quiz {
    private Topic topic;
    private int currentQuestionIndex;
    private int correctAnswers;
    private Scanner scanner;
    private Timer timer;
    private AtomicBoolean timeUp;

    public Quiz(Topic topic, Scanner scanner) {
        assert topic != null : "Topic must not be null";
        assert !topic.getQuestions().isEmpty() : "Topic must contain at least one question";
        this.topic = topic;
        this.scanner = scanner;
        this.currentQuestionIndex = 0;
        this.correctAnswers = 0;
    }

    public void start(int timeLimitInSeconds, int questionLimit) {
        List<Question> questions = topic.getQuestions();
        startTimer(timeLimitInSeconds);

        if (questions.isEmpty()) {
            throw new IllegalStateException("Cannot start a quiz with no questions.");
        }

        while (currentQuestionIndex < questions.size() && currentQuestionIndex < questionLimit) {
            if (timeUp.get()) {
                break;
            }
            Question currentQuestion = questions.get(currentQuestionIndex);
            assert currentQuestion != null : "Current question must not be null";

            System.out.println(currentQuestion.getText());

            if (currentQuestion instanceof Mcq) {
                currentQuestion.printOptions();
            } else if (currentQuestion instanceof TrueFalse) {
                System.out.println("Please answer with 'true' or 'false'.");
            } else if (currentQuestion instanceof FillInTheBlank) {
                System.out.println("Please fill in the blank with the correct answer.");
            }

            boolean validInput = false;
            while (!validInput) {
                System.out.print("Enter your answer: ");
                String answer = scanner.nextLine().trim();

                if (timeUp.get()) {  // Check if the time is up
                    break;
                }

                // Check if the user wants to exit the quiz
                if (answer.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting the quiz. Returning to main menu...");
                    return;  // Exit the quiz early without quitting the entire application
                }

                try {
                    answerQuestion(answer);
                    validInput = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            currentQuestionIndex++;
        }

        timer.cancel();
        System.out.println("Quiz finished. Your score is: " + getScore() + "%");
    }

    public void startTimer(int seconds) {
        timer = new Timer();
        timeUp = new AtomicBoolean(false);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeUp.set(true);
                System.out.println("\nTime's up! The quiz is ending now.");
                timer.cancel();  // Stop the timer
            }
        }, seconds * 1000);  // Convert seconds to milliseconds
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
