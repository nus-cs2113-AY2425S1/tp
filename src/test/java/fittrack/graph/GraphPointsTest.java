package fittrack.graph;

import fittrack.trainingsession.TrainingSession;
import fittrack.enums.Exercise;
import fittrack.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphPointsTest {
    private ArrayList<TrainingSession> sessionList;
    private TrainingSession session1;
    private TrainingSession session2;
    private TrainingSession session3;
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
    public void testEmptyGraphSessions(){
        GraphPoints.graphSessions(sessionList);
        assertEquals("Here's your point progression over the various training sessions:"
                + System.lineSeparator()
                + "Session Description | Date             | Points" + System.lineSeparator()
                + "--------------------|------------------|" + System.lineSeparator()
                + "session1            | " + testDatetimeOutput1 + " |  (0)" + System.lineSeparator()
                + "session2            | " + testDatetimeOutput2 + " |  (0)" + System.lineSeparator()
                + "session3            | " + testDatetimeOutput3 + " |  (0)" + System.lineSeparator() +
                System.lineSeparator(),
                outContent.toString());
    }

    @Test
    public void testEmptyExerciseSessions(){
        GraphPoints.graphExercisePoints(Exercise.PULL_UP, sessionList);
        assertEquals(
        "Here's your point progression for PULL_UP over your training sessions:" + System.lineSeparator()
                + "Session Description | Date             | Points" + System.lineSeparator()
                + "--------------------|------------------|" + System.lineSeparator()
                + "session1            | " + testDatetimeOutput1 + " |  (0)" + System.lineSeparator()
                + "session2            | " + testDatetimeOutput2 + " |  (0)" + System.lineSeparator()
                + "session3            | " + testDatetimeOutput3 + " |  (0)" + System.lineSeparator() +
                System.lineSeparator(),
                outContent.toString());
    }

    @Test
    public void testGraphSessions(){
        session1.editExercise(Exercise.PULL_UP, "10", false);
        session1.editExercise(Exercise.SIT_UP, "15", false);
        session2.editExercise(Exercise.PULL_UP, "20", false);
        session2.editExercise(Exercise.SIT_UP, "30", false);
        session3.editExercise(Exercise.PULL_UP, "5", false);
        GraphPoints.graphSessions(sessionList);
        assertEquals("Here's your point progression over the various training sessions:" +
                System.lineSeparator() +
                "Session Description | Date             | Points" + System.lineSeparator() +
                "--------------------|------------------|" + System.lineSeparator() +
                "session1            | " + testDatetimeOutput1 + " | * (1)" + System.lineSeparator() +
                "session2            | " + testDatetimeOutput2 + " | ***** (5)" + System.lineSeparator() +
                "session3            | " + testDatetimeOutput3 + " |  (0)" + System.lineSeparator()
                + System.lineSeparator(),
                outContent.toString());
    }

    @Test
    public void testGraphExercisePoints(){
        session1.editExercise(Exercise.PULL_UP, "10", false);
        session1.editExercise(Exercise.SIT_UP, "15", false);
        session2.editExercise(Exercise.PULL_UP, "20", false);
        session2.editExercise(Exercise.SIT_UP, "30", false);
        session3.editExercise(Exercise.PULL_UP, "5", false);
        GraphPoints.graphExercisePoints(Exercise.PULL_UP, sessionList);
        assertEquals("Here's your point progression for PULL_UP over your training sessions:" +
                System.lineSeparator() +
                "Session Description | Date             | Points" + System.lineSeparator() +
                "--------------------|------------------|" + System.lineSeparator() +
                "session1            | " + testDatetimeOutput1 + " | * (1)" + System.lineSeparator() +
                "session2            | " + testDatetimeOutput2 + " | *** (3)" + System.lineSeparator() +
                "session3            | " + testDatetimeOutput3 + " |  (0)" + System.lineSeparator()
                + System.lineSeparator(),
                outContent.toString());
    }
}
