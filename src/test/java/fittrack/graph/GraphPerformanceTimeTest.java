package fittrack.graph;

import fittrack.enums.Exercise;
import fittrack.trainingsession.TrainingSession;
import fittrack.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphPerformanceTimeTest {
    private ArrayList<TrainingSession> sessionList;

    @BeforeEach
    void setUp() {
        sessionList = new ArrayList<>();
        // Create a User instance for the TrainingSession
        User user = new User("Male", "20");

        TrainingSession session1 = new TrainingSession(LocalDateTime.now(), "Session1", user);
        TrainingSession session2 = new TrainingSession(LocalDateTime.now(), "Session2", user);
        TrainingSession session3 = new TrainingSession(LocalDateTime.now(), "Session3", user);
        TrainingSession session4 = new TrainingSession(LocalDateTime.now(), "Session4", user);
        TrainingSession session5 = new TrainingSession(LocalDateTime.now(), "Session5", user);

        // Set exercise performance for testing (assuming setters exist)
        session1.editExercise(Exercise.SHUTTLE_RUN, "10.9");   // 1.5 seconds
        session3.editExercise(Exercise.SHUTTLE_RUN, "11.5");   // 2.0 seconds
        session4.editExercise(Exercise.SHUTTLE_RUN, "12.0");   // Invalid time

        sessionList.add(session1);
        sessionList.add(session2);
        sessionList.add(session3);
        sessionList.add(session4);
        sessionList.add(session5);
    }

    @Test
    void testBuildTimeHeader() {
        Exercise exercise = Exercise.SHUTTLE_RUN;
        int maxXHeaderLength = 10;

        StringBuilder header = GraphPerformanceTime.buildTimeHeader(exercise, sessionList, maxXHeaderLength);
        String expectedHeader = "           10.9s        NIL        11.5s       12.0s        NIL    \n";

        assertEquals(expectedHeader, header.toString(),
                "The header should be correctly formatted with exercise performance times.");
    }

    @Test
    void testGetDisplayTime() {
        Exercise exercise = Exercise.SHUTTLE_RUN;
        int actualTime = 109; // 10.9 seconds

        String displayTime = GraphPerformanceTime.getDisplayTime(exercise, actualTime);
        assertEquals("10.9s", displayTime, "Display time should be formatted as seconds with one decimal place.");
    }

    @Test
    void testGetNormalizePerformance() {
        Exercise exercise = Exercise.SHUTTLE_RUN;
        int minPerformance = 109;
        int maxPerformance = 120;

        User user = new User("Male", "20");
        TrainingSession session = new TrainingSession(LocalDateTime.now(), "TestSession", user);
        session.editExercise(exercise, "115"); // Midway between 109 and 120

        double normalized = GraphPerformanceTime.getNormalizePerformance(exercise, minPerformance,
                maxPerformance, session);
        assertEquals(0.545, normalized, 0.001);
    }

    @Test
    void testGenerateMainGraphPerformance() {
        Exercise exercise = Exercise.SHUTTLE_RUN;
        int minPerformance = 109;
        int maxPerformance = 120;
        int maxXHeaderLength = 10;

        String graphContent = GraphPerformanceTime.generateMainGraphPerformance(exercise, sessionList,
                minPerformance, maxPerformance, maxXHeaderLength);

        // Check Y-axis normalized labels and their associated rows
        assertTrue(graphContent.contains("1.00"), "Graph should include the Y-axis normalized label 1.00.");
        assertTrue(graphContent.contains("0.50"), "Graph should include the Y-axis normalized label 0.50.");
        assertTrue(graphContent.contains("0.05"), "Graph should include the Y-axis normalized label 0.05.");

        // Check that specific points are plotted at expected normalized levels
        assertTrue(graphContent.contains("0.05         *"), "Graph should have a data point at 0.05 " +
                "normalized level for max performance.");
    }
}
