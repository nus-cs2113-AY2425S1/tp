package seedu.javaninja;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.HashSet;
import java.util.Set;

public class QuizTimer {
    private Timer timer;
    private AtomicBoolean timeUp;
    private int initialTime; // Store the total time in seconds
    private Set<Integer> displayedWarnings = new HashSet<>(); // Track displayed warnings

    /**
     * Starts the quiz timer for the specified number of seconds.
     *
     * @param seconds The total countdown duration in seconds.
     * @return An AtomicBoolean flag that indicates when time is up.
     */
    public AtomicBoolean startTimer(int seconds) {
        timer = new Timer();
        timeUp = new AtomicBoolean(false);
        initialTime = seconds;

        timer.scheduleAtFixedRate(new TimerTask() {
            int remainingTime = initialTime;

            @Override
            public void run() {
                if (remainingTime <= 0) {
                    timeUp.set(true);
                    System.out.println("\nTime's up! The quiz is ending now.");
                    timer.cancel();  // Stop the timer
                } else {
                    showTimeWarning(remainingTime);
                    remainingTime--;
                }
            }
        }, 0, 1000);  // Schedule the task to run every 1 second

        return timeUp;
    }

    /**
     * Cancels the quiz timer.
     */
    public void cancelTimer() {
        if (timer != null) {
            timer.cancel();
        }
        displayedWarnings.clear(); // Reset displayed warnings for the next session
    }

    /**
     * Checks if the time is up.
     *
     * @return True if the timer has finished, false otherwise.
     */
    public boolean isTimeUp() {
        return timeUp.get();
    }

    /**
     * Displays time warnings at specific intervals only once per session.
     *
     * @param remainingTime The current time left in seconds.
     */
    private void showTimeWarning(int remainingTime) {
        if (remainingTime == 180 && displayedWarnings.add(180)) {
            System.out.println("\n[Hint: 3 minutes remaining!]");
        } else if (remainingTime == 60 && displayedWarnings.add(60)) {
            System.out.println("\n[Hint: 1 minute remaining!]");
        } else if (remainingTime == 30 && displayedWarnings.add(30)) {
            System.out.println("\n[Hint: 30 seconds remaining!]");
        } else if (remainingTime == 10 && displayedWarnings.add(10)) {
            System.out.println("\n[Hint: 10 seconds remaining!]");
        } else if (remainingTime == 5 && displayedWarnings.add(5)) {
            System.out.println("\n[Hint: 5 seconds remaining!]");
        }
    }
}
