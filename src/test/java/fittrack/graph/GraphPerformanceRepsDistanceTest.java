package fittrack.graph;

import fittrack.enums.Exercise;
import fittrack.trainingsession.TrainingSession;
import fittrack.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphPerformanceRepsDistanceTest {
    private ArrayList<TrainingSession> sessionList;
    private TrainingSession session1, session2, session3;
    private final LocalDateTime testDatetime1 = LocalDateTime.now();
    private final LocalDateTime testDatetime2 = testDatetime1.plusHours(1);
    private final LocalDateTime testDatetime3 = testDatetime2.plusHours(1);
    private final String testDatetimeOutput1 = testDatetime1.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    private final String testDatetimeOutput2 = testDatetime2.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    private final String testDatetimeOutput3 = testDatetime3.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final User testUser = new User("MALE", "13");

    @BeforeEach
    public void setUp() {
        sessionList = new ArrayList<>();

        // Sample TrainingSession objects for testing
        session1 = new TrainingSession(testDatetime1, "session1", testUser);
        session2 = new TrainingSession(testDatetime2, "session2", testUser);
        session3 = new TrainingSession(testDatetime3,"session3", testUser);

        sessionList.add(session1);
        sessionList.add(session2);
        sessionList.add(session3);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testEmptyPerformance(){
        GraphPerformance.graphExercisePerformance(Exercise.PULL_UP, sessionList);
        assertEquals("Here's your progression for PULL_UP over your training sessions:"
            + System.lineSeparator() + System.lineSeparator() +
            "1     0 points          0 points          0 points     " + System.lineSeparator() +
            "     session1          session2          session3     " + System.lineSeparator() +
            " " + testDatetimeOutput1 + "  " + testDatetimeOutput2 + "  " + testDatetimeOutput3 + " " +
            System.lineSeparator() + System.lineSeparator(),
            outContent.toString());
    }

    @Test
    public void testNonEmptyPerformance(){
        session1.editExercise(Exercise.PULL_UP, "10", false);
        session1.editExercise(Exercise.SIT_UP, "15", false);
        session2.editExercise(Exercise.PULL_UP, "20", false);
        session2.editExercise(Exercise.SIT_UP, "30", false);
        session3.editExercise(Exercise.PULL_UP, "5", false);
        GraphPerformance.graphExercisePerformance(Exercise.PULL_UP, sessionList);

        String graphOutput =
                "Here's your progression for PULL_UP over your training sessions:" + System.lineSeparator() +
                    System.lineSeparator() +
                    "21                      3 points                       " + System.lineSeparator() +
                    "20                          *                          " + System.lineSeparator() +
                    "19                          *                          " + System.lineSeparator() +
                    "18                          *                          " + System.lineSeparator() +
                    "17                          *                          " + System.lineSeparator() +
                    "16                          *                          " + System.lineSeparator() +
                    "15                          *                          " + System.lineSeparator() +
                    "14                          *                          " + System.lineSeparator() +
                    "13                          *                          " + System.lineSeparator() +
                    "12                          *                          " + System.lineSeparator() +
                    "11    1 points              *                          " + System.lineSeparator() +
                    "10        *                 *                          " + System.lineSeparator() +
                    "9         *                 *                          " + System.lineSeparator() +
                    "8         *                 *                          " + System.lineSeparator() +
                    "7         *                 *                          " + System.lineSeparator() +
                    "6         *                 *             0 points     " + System.lineSeparator() +
                    "5         *                 *                 *        " + System.lineSeparator() +
                    "4         *                 *                 *        " + System.lineSeparator() +
                    "3         *                 *                 *        " + System.lineSeparator() +
                    "2         *                 *                 *        " + System.lineSeparator() +
                    "1         *                 *                 *        " + System.lineSeparator() +
                    "      session1          session2          session3     " + System.lineSeparator() +
                    "  " + testDatetimeOutput1 + "  " + testDatetimeOutput2 + "  " + testDatetimeOutput3 + " "
                    + System.lineSeparator() + System.lineSeparator();

        assertEquals(graphOutput, outContent.toString());
    }
}
