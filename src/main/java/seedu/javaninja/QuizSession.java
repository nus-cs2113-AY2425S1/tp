package seedu.javaninja;

import java.util.Scanner;
import java.util.logging.Logger;

public class QuizSession {
    private static final Logger logger = Logger.getLogger(QuizSession.class.getName());
    public Scanner scanner;
    private Quiz currentQuiz;

    public QuizSession() {
        scanner = new Scanner(System.in);
        currentQuiz = null;
    }

    public void startQuiz(Topic topic) {
        currentQuiz = new Quiz(topic, scanner);

        int timeLimitInSeconds = getTimeLimitInSeconds(scanner);
        int questionLimit = getQuestionLimit(scanner);

        currentQuiz.start(timeLimitInSeconds, questionLimit);
    }

    public int getQuizScore() {
        return currentQuiz.getScore();
    }

    public int getTimeLimitInSeconds(Scanner scanner) {
        System.out.println("Set a time limit for the quiz.");
        System.out.print("Enter the number of minutes (or 0 if you want to set seconds): ");
        int minutes = Integer.parseInt(scanner.nextLine().trim());

        int timeLimitInSeconds = 0;
        if (minutes == 0) {
            System.out.print("Enter the number of seconds: ");
            timeLimitInSeconds = Integer.parseInt(scanner.nextLine().trim());
        } else {
            timeLimitInSeconds = minutes * 60;  // Convert minutes to seconds
        }

        return timeLimitInSeconds;
    }

    public int getQuestionLimit(Scanner scanner) {
        System.out.print("Enter the number of questions you want to attempt: ");
        return Integer.parseInt(scanner.nextLine().trim());
    }

    /* For Tests */
    public Quiz getCurrentQuiz(Topic topic, Scanner scanner) {
        if (currentQuiz == null) {
            currentQuiz = new Quiz(topic, scanner);
        }
        return this.currentQuiz;
    }

}
