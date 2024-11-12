package seedu.javaninja;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

/**
 * The `QuizSession` class manages the lifecycle of a quiz session, including the countdown timer and user prompts.
 * It handles user interaction for quiz setup, displays countdown warnings, and tracks the quiz score.
 */
public class QuizSession {
    private static final Logger logger = Logger.getLogger(QuizSession.class.getName());
    private Cli cli;                        // CLI instance for user interaction
    private Quiz currentQuiz;               // Active quiz instance
    private int questionLimit;              // Limit on the number of questions in a session
    private int timeLimitInSeconds;         // Time limit for the quiz in seconds
    private Topic topic;                    // Selected quiz topic
    private Timer timer;                    // Timer for managing countdowns and time-based prompts

    /**
     * Constructs a `QuizSession` with the provided `Cli` instance for user interactions.
     *
     * @param cli The CLI instance to display messages and prompt the user.
     */
    public QuizSession(Cli cli) {
        this.cli = cli;
        this.topic = null;
        currentQuiz = null;
    }

    /**
     * Starts a quiz session on the specified topic and initiates user-defined time and question limits.
     *
     * @param topic The topic for the quiz session.
     */
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

        startCountdown();
        currentQuiz.start(timeLimitInSeconds, questionLimit);
    }

    /**
     * Starts a countdown timer for the quiz session, displaying warnings at critical time thresholds.
     * Ends the quiz when the time limit is reached.
     */
    private void startCountdown() {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            int remainingTime = timeLimitInSeconds;

            @Override
            public void run() {
                if (remainingTime > 0) {
                    showTimeWarning(remainingTime);
                    remainingTime--;
                } else {
                    cli.printMessage("Time is up! The quiz will now end.");
                    timer.cancel();
                    endQuiz();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    /**
     * Displays warnings to the user as time thresholds (3 minutes, 1 minute, 30 seconds, etc.) are reached.
     *
     * @param remainingTime The current remaining time in seconds.
     */
    private void showTimeWarning(int remainingTime) {
        if (remainingTime == 180) {
            cli.printMessage("\n[Hints: 3 minutes remaining!]");
        } else if (remainingTime == 60) {
            cli.printMessage("\n[Hints: 1 minute remaining!]");
        } else if (remainingTime == 30) {
            cli.printMessage("\n[Hints: 30 seconds remaining!]");
        } else if (remainingTime == 10) {
            cli.printMessage("\n[Hints: 10 seconds remaining!]");
        } else if (remainingTime == 5) {
            cli.printMessage("\n[Hints: 5 seconds remaining!]");
        }
    }

    /**
     * Ends the quiz session and displays a prompt to view the score.
     */
    private void endQuiz() {
        cli.printMessage("Please enter 'view' to see your score...");
        if (timer != null) {
            timer.cancel();
        }
    }

    /**
     * Returns the final quiz score.
     *
     * @return The score achieved in the current quiz session.
     */
    public int getQuizScore() {
        return currentQuiz.getScore();
    }

    /**
     * Prompts the user to set a time limit for the quiz, supporting either minutes or seconds.
     * Validates the input to ensure it's a positive integer.
     *
     * @return The time limit for the quiz in seconds.
     */
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

    /**
     * Prompts the user to set a limit on the number of questions for the quiz.
     * Validates the input to ensure it's within the total number of available questions.
     *
     * @return The question limit for the quiz session.
     */
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

    /**
     * Returns the question limit set by the user for this quiz session.
     *
     * @return The maximum number of questions allowed in this session.
     */
    public int getQuestionLimit() {
        return questionLimit;
    }

    /**
     * Returns the time limit set for the quiz session in seconds.
     *
     * @return The quiz session time limit in seconds.
     */
    public int getTimeLimitInSeconds() {
        return timeLimitInSeconds;
    }

    /**
     * Retrieves the name of the current topic for display.
     *
     * @return The name of the selected topic.
     */
    public String getTopicName() {
        return topic.getName();
    }

    /**
     * Provides access to the current quiz instance, primarily for testing purposes.
     * If a quiz is not currently active, it creates a new one.
     *
     * @param topic The topic associated with the quiz.
     * @param cli The CLI instance for user interaction.
     * @return The current `Quiz` instance.
     */
    public Quiz getCurrentQuiz(Topic topic, Cli cli) {
        if (currentQuiz == null) {
            currentQuiz = new Quiz(topic, cli);
        }
        return this.currentQuiz;
    }
}
