package seedu.javaninja;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The `QuizTimer` class manages the countdown timer for a quiz session.
 * It provides methods to start, monitor, and cancel the timer, including an indicator for when time is up.
 */
public class QuizTimer {
    private Timer timer;
    private AtomicBoolean timeUp;
    private Runnable onTimeUpCallback;

    public AtomicBoolean startTimer(int seconds, Runnable onTimeUp) {
        timer = new Timer();
        timeUp = new AtomicBoolean(false);
        this.onTimeUpCallback = onTimeUp;

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeUp.set(true);
                if (onTimeUpCallback != null) {
                    onTimeUpCallback.run();
                }
                timer.cancel();
            }
        }, seconds * 1000);

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
