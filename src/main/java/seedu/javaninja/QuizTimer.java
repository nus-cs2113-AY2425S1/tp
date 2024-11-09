package seedu.javaninja;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The `QuizTimer` class manages the countdown timer for a quiz session. It provides methods to start,
 * monitor, and cancel the timer, including an indicator for when time is up.
 */
public class QuizTimer {
    private Timer timer;                   // Timer instance for scheduling tasks
    private AtomicBoolean timeUp;          // Atomic flag to indicate when time is up

    /**
     * Starts the quiz timer for a specified duration.
     *
     * @param seconds The total countdown duration in seconds.
     * @return An AtomicBoolean flag that will be set to true when time is up.
     */
    public AtomicBoolean startTimer(int seconds) {
        timer = new Timer();
        timeUp = new AtomicBoolean(false);

        // Schedule a task to set the timeUp flag and end the timer when the duration is reached
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeUp.set(true);
                System.out.println("\nTime's up! The quiz is ending now.");
                timer.cancel();  // Stop the timer after time is up
            }
        }, seconds * 1000);  // Convert seconds to milliseconds

        return timeUp;
    }

    /**
     * Cancels the quiz timer if it is currently active.
     */
    public void cancelTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    /**
     * Checks if the timer has completed and if the time is up.
     *
     * @return True if the timer has finished, false otherwise.
     */
    public boolean isTimeUp() {
        return timeUp.get();
    }
}
