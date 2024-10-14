package fittrack.trainingsession;

import fittrack.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrainingSessionTest {

    private final String testDescription = "Test Session Description";
    private final String testDatetime = "2024-10-01";
    private final String testDatetimeOutput = "01/10/2024 00:00";
    private final User testUser = new User("M", "13");
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void editExercise_PullUpStation() {
        new TrainingSession(testDescription, testDatetime, testUser).editExercise(1, 1);
        assertEquals("Exercise edited! Here's your new input: " + System.lineSeparator() +
                1 + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void editExercise_ShuttleRunStation() {
        new TrainingSession(testDescription, testDatetime, testUser).editExercise(2, 11);
        assertEquals("Exercise edited! Here's your new input: " + System.lineSeparator() +
                11 + "s" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void testGetSessionDescription(){
        assertEquals(testDescription,
                new TrainingSession(testDescription, testDatetime, testUser).getSessionDescription());
    }

    @Test
    public void testPrintSessionDescription(){
        new TrainingSession(testDescription, testDatetime, testUser);
        assertEquals(testDescription, outContent.toString());
    }

    @Test
    public void testViewSession(){
        new TrainingSession(testDescription, testDatetime, testUser).viewSession();
        assertEquals("Training Session: " + testDescription + System.lineSeparator() +
                "Training Datetime: " + testDatetimeOutput + System.lineSeparator() +
                "Pull Up Station | 0" + System.lineSeparator() +
                "Shuttle Run Station | 0" + System.lineSeparator() +
                "Sit and Reach Station | 0" + System.lineSeparator() +
                "Sit Up Station | 0" + System.lineSeparator() +
                "Standing Broad Jump Station | 0" + System.lineSeparator() +
                "Walk and Run Station | 0" + System.lineSeparator() +
                "Total points: " + 0 + System.lineSeparator() +
                        "Overall Award: No award" + System.lineSeparator(),
                outContent.toString());
    }
}
