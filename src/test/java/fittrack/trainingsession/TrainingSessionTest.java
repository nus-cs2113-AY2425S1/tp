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
    private final String testDatetimeString = testDatetime.toString();
    private final String testDatetimeOutput = testDatetime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    private final User testUser = new User("MALE", "13");
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void editExercise_PullUpStation() {
        new TrainingSession(testDatetimeString, testDescription, testUser).editExercise(0, 1);
        assertEquals("Exercise edited! Here's your new input: " + System.lineSeparator() +
                1 + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void editExercise_ShuttleRunStation() {
        new TrainingSession(testDatetimeString, testDescription, testUser).editExercise(1, 11);
        assertEquals("Exercise edited! Here's your new input: " + System.lineSeparator() +
                11 + "s" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void testGetSessionDescription(){
        assertEquals(testDescription + " | " + testDatetimeOutput,
                new TrainingSession(testDatetimeString, testDescription, testUser).getSessionDescription());
    }

    @Test
    public void testPrintSessionDescription(){
        new TrainingSession(testDatetimeString, testDescription, testUser);
        assertEquals(testDescription + " | " + testDatetimeOutput, outContent.toString());
    }

    @Test
    public void testViewSession(){
        new TrainingSession(testDatetimeString, testDescription, testUser).viewSession();
        assertEquals("Training Session: " + testDescription + System.lineSeparator() +
                "Training Datetime: " + testDatetimeOutput + System.lineSeparator() +
                "Pull Up Station | 0 | 0" + System.lineSeparator() +
                "Shuttle Run Station | 0s | 0" + System.lineSeparator() +
                "Sit and Reach Station | 0cm | 0" + System.lineSeparator() +
                "Sit Up Station | 0 | 0" + System.lineSeparator() +
                "Standing Broad Jump Station | 0cm | 0" + System.lineSeparator() +
                "Walk and Run Station | 00:00 | 0" + System.lineSeparator() +
                "Total points: " + 0 + System.lineSeparator() +
                        "Overall Award: No award" + System.lineSeparator(),
                outContent.toString());
    }
}
