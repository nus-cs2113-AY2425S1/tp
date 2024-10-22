package fittrack.trainingsession;

import fittrack.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrainingSessionTest {

    private final String testDescription = "Test Session Description";
    private final LocalDateTime testDatetime = LocalDateTime.now();
    private final LocalDateTime testDatetimeString = testDatetime;
    private final String testDatetimeOutput = testDatetime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    private final User testUser = new User("MALE", "13");
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void editExercisePullUpStation() {
        new TrainingSession(testDatetimeString, testDescription, testUser).editExercise(0, "1");
        assertEquals("Exercise edited! Here's your new input: " +
                "Reps: 1 | 0 points" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void editExerciseShuttleRunStation() {
        new TrainingSession(testDatetimeString, testDescription, testUser).editExercise(1, "11.0");
        assertEquals("Exercise edited! Here's your new input: " +
                "Time: 110s | 3 points" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void editWalkAndRunStation() {
        new TrainingSession(testDatetimeString, testDescription, testUser).editExercise(5, "11:30");
        assertEquals("Exercise edited! Here's your new input: " +
                "Time: 690 | 5 points" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void testGetSessionDescription(){
        assertEquals(testDescription + " | " + testDatetimeOutput,
                new TrainingSession(testDatetimeString, testDescription, testUser).getSessionDescription());
    }

    @Test
    public void testPrintSessionDescription(){
        new TrainingSession(testDatetimeString, testDescription, testUser).printSessionDescription();
        assertEquals(testDescription + " | " + testDatetimeOutput + System.lineSeparator(),
                outContent.toString());
    }

    @Test
    public void testViewSession(){
        new TrainingSession(testDatetimeString, testDescription, testUser).viewSession();
        assertEquals("Training Session: " + testDescription + System.lineSeparator() +
                "Training Datetime: " + testDatetimeOutput + System.lineSeparator() +
                "Pull Up Station | Reps: 0 | 0 points" + System.lineSeparator() +
                "Shuttle Run Station | Time: NA | 0 points" + System.lineSeparator() +
                "Sit and Reach Station | Distance: 0cm | 0 points" + System.lineSeparator() +
                "Sit Up Station | Reps: 0 | 0 points" + System.lineSeparator() +
                "Standing Broad Jump Station | Distance: 0cm | 0 points" + System.lineSeparator() +
                "Walk and Run Station | Time: NA | 0 points" + System.lineSeparator() +
                "Total points: " + 0 + System.lineSeparator() +
                        "Overall Award: No Award" + System.lineSeparator(),
                outContent.toString());
    }
}
