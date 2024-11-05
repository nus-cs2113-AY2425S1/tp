package seedu.javaninja;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class QuizTimer {
    private Timer timer;
    private AtomicBoolean timeUp;

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
