package seedu.javaninja;

import java.util.logging.Logger;

public class QuizSession {
    private static final Logger logger = Logger.getLogger(QuizSession.class.getName());
    private Cli cli;
    private Quiz currentQuiz;
    private int questionLimit;
    private int timeLimitInSeconds;
    private Topic topic;

    public QuizSession(Cli cli) {
        this.cli = cli;
        this.topic = null;
        currentQuiz = null;
    }

    public void startQuiz(Topic topic) {
        this.topic = topic;
        currentQuiz = new Quiz(topic, cli);

        try {
            timeLimitInSeconds = getTimeLimitInSecondsFromUser();
            questionLimit = getQuestionLimitFromUser();
        } catch (Exception e) {
            cli.printMessage(e.getMessage());
            return;
        }

        currentQuiz.start(timeLimitInSeconds, questionLimit);
    }

    public int getQuizScore() {
        return currentQuiz.getScore();
    }

    public int getTimeLimitInSecondsFromUser() {
        while (true) {
            try {
                cli.printMessage("Set a time limit for the quiz.");
                cli.printMessage("Enter the number of minutes (or 0 if you want to set seconds): ");

                int minutes = Integer.parseInt(cli.readInput());
                int timeLimitInSeconds;

                if (minutes < 0) {
                    cli.printMessage("Please enter a positive integer, or '0' for seconds");
                    continue;
                }

                if (minutes == 0) {
                    cli.printMessage("Enter the number of seconds: ");
                    timeLimitInSeconds = Integer.parseInt(cli.readInput());

                    if (timeLimitInSeconds < 0) {
                        cli.printMessage("Please enter a positive integer");
                        continue;
                    }

                    if (timeLimitInSeconds == 0) {
                        cli.printMessage("Time limit cannot be 0 seconds");
                        continue;
                    }
                } else {
                    timeLimitInSeconds = minutes * 60;  // Convert minutes to seconds
                }

                return timeLimitInSeconds; // Return if input is valid

            } catch (NumberFormatException e) {
                cli.printMessage("Invalid input. Please enter a valid number for minutes or seconds.");
            }
        }
    }

    public int getQuestionLimitFromUser() {
        while (true) {
            try {
                cli.printMessage("Enter the number of questions you want to attempt: ");
                int questionLimit = Integer.parseInt(cli.readInput());

                if (questionLimit < 0) {
                    cli.printMessage("Cannot attempt a negative number of questions. Please try again.");
                    continue;
                }
                if (questionLimit == 0) {
                    cli.printMessage("Cannot attempt zero questions. Please try again.");
                    continue;
                }
                if (questionLimit > topic.getQuestions().size()) {
                    cli.printMessage("Question limit exceeded. Please try again.");
                    continue;
                }
                return questionLimit; // Return if input is valid

            } catch (NumberFormatException e) {
                cli.printMessage("Invalid input. Please enter a valid number for the question limit.");
            }
        }
    }

    public int getQuestionLimit() {
        return questionLimit;
    }

    public int getTimeLimitInSeconds() {
        return timeLimitInSeconds;
    }

    public String getTopicName() {
        return topic.getName();
    }

    /* For Tests */
    public Quiz getCurrentQuiz(Topic topic, Cli cli) {
        if (currentQuiz == null) {
            currentQuiz = new Quiz(topic, cli);
        }
        return this.currentQuiz;
    }
}
