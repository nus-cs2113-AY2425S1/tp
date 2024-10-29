package seedu.javaninja;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class QuizTimer {
    private Timer timer;
    private AtomicBoolean timeUp;
    private Scanner scanner;

    public QuizTimer(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getTimeLimitInSeconds() {
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

    public int getQuestionLimit() {
        System.out.print("Enter the number of questions you want to attempt: ");
        return Integer.parseInt(scanner.nextLine().trim());
    }

    public AtomicBoolean startTimer(int seconds) {
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

        return timeUp;
    }

    public void cancelTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    public boolean isTimeUp() {
        return timeUp.get();
    }

}
