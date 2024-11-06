package seedu.javaninja;

import java.util.logging.Logger;

public class QuizSession {
    private static final Logger logger = Logger.getLogger(QuizSession.class.getName());
    private Cli cli;
    private Quiz currentQuiz;

    public QuizSession(Cli cli) {
        this.cli = cli;
        currentQuiz = null;
    }

    public void startQuiz(Topic topic) {
        currentQuiz = new Quiz(topic, cli);

        int timeLimitInSeconds = getTimeLimitInSeconds();
        int questionLimit = getQuestionLimit();

        currentQuiz.start(timeLimitInSeconds, questionLimit);
    }

    public int getQuizScore() {
        return currentQuiz.getScore();
    }

    public int getTimeLimitInSeconds() {
        cli.printMessage("Set a time limit for the quiz.");
        cli.printMessage("Enter the number of minutes (or 0 if you want to set seconds): ");

        int minutes = Integer.parseInt(cli.readInput());

        int timeLimitInSeconds = 0;
        if (minutes == 0) {
            cli.printMessage("Enter the number of seconds: ");
            timeLimitInSeconds = Integer.parseInt(cli.readInput());
        } else {
            timeLimitInSeconds = minutes * 60;  // Convert minutes to seconds
        }

        return timeLimitInSeconds;
    }

    public int getQuestionLimit() {
        cli.printMessage("Enter the number of questions you want to attempt: ");
        return Integer.parseInt(cli.readInput());
    }

    /* For Tests */
    public Quiz getCurrentQuiz(Topic topic, Cli cli) {
        if (currentQuiz == null) {
            currentQuiz = new Quiz(topic, cli);
        }
        return this.currentQuiz;
    }
}
