package fittrack.graph;

import fittrack.trainingsession.TrainingSession;
import fittrack.enums.Exercise;

import java.util.ArrayList;

public class GraphPointsTest {
    private ArrayList<TrainingSession> sessionList;
    private TrainingSession session1, session2, session3;

    @BeforeEach
    void setUp() {
        sessionList = new ArrayList<>();

        // Sample TrainingSession objects for testing
        session1 = new TrainingSession("session1", "2024-11-01 07:00");
        session1.editExercise(Exercise.PULL_UP, 10);
        session1.editExercise(Exercise.SQUAT, 15);

        session2 = new TrainingSession("session2", "2024-11-03 18:00");
        session2.editExercise(Exercise.PULL_UP, 20);
        session2.editExercise(Exercise.SR, 30);

        session3 = new TrainingSession("session3", "2024-11-05 15:30");
        session3.editExercise(Exercise.PULL_UP, 5);

        sessionList.add(session1);
        sessionList.add(session2);
        sessionList.add(session3);
    }
}
