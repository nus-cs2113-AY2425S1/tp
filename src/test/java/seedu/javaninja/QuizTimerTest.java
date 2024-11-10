package seedu.javaninja;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuizTimerTest {

    private QuizTimer quizTimer;
    private Cli cli;

    @BeforeEach
    public void setUp() {
        String simulatedUserInput = "Sample input\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());
        cli = new Cli(inputStream);
        quizTimer = new QuizTimer();
    }

    @Test
    public void startTimer_validDuration_setsTimeUpAfterCompletion() throws InterruptedException {
        AtomicBoolean timeUp = quizTimer.startTimer(2);
        Thread.sleep(3000);
        assertTrue(timeUp.get(), "Expected timeUp flag to be true after timer completion.");
    }

    @Test
    public void isTimeUp_beforeTimerCompletes_returnsFalse() throws InterruptedException {
        quizTimer.startTimer(2);
        assertFalse(quizTimer.isTimeUp(), "Expected timeUp flag to be false before timer completion.");
        Thread.sleep(3000);
        assertTrue(quizTimer.isTimeUp(), "Expected timeUp flag to be true after timer completion.");
    }

    @Test
    public void cancelTimer_timerRunning_stopsTimerBeforeCompletion() throws InterruptedException {
        quizTimer.startTimer(5);
        Thread.sleep(1000);
        quizTimer.cancelTimer();
        Thread.sleep(5000);
        assertFalse(quizTimer.isTimeUp(), "Expected timeUp flag to remain false after timer was cancelled.");
    }
}
