package fittrack.trainingsession;

import fittrack.enums.Exercise;
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
    private final String testDefaultMood = "No mood recorded";
    private final String testEditedMood = "Feeling happy";
    private final User testUser = new User("MALE", "13");
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    // Static method is tested first, otherwise it would get affected by the other instances instantiated
    @Test
    public void getLongestSessionDescription() {
        new TrainingSession(testDatetimeString, "test description", testUser);
        new TrainingSession(testDatetimeString, "longer description", testUser);
        new TrainingSession(testDatetimeString, "short", testUser);
        new TrainingSession(testDatetimeString, "very very very very very very very long" +
                " description", testUser);
        assertEquals("very very very very very very very long description".length(),
                TrainingSession.getLongestSessionDescription());
    }

    @Test
    public void getExercisePoints() {
        TrainingSession session = new TrainingSession(testDatetimeString, testDescription, testUser);
        session.editExercise(Exercise.PULL_UP, "10"); // 1 Point
        assertEquals(1, session.getExercisePoints(Exercise.PULL_UP));

        session.editExercise(Exercise.SHUTTLE_RUN, "11.0"); // 3 Points
        assertEquals(3, session.getExercisePoints(Exercise.SHUTTLE_RUN));

        session.editExercise(Exercise.WALK_AND_RUN, "12:00"); // 4 Points
        assertEquals(4, session.getExercisePoints(Exercise.WALK_AND_RUN));

        session.editExercise(Exercise.SIT_UP, "0"); // 5 Points
        assertEquals(0, session.getExercisePoints(Exercise.SIT_UP));
    }

    @Test
    public void getExercisePerformance() {
        TrainingSession session = new TrainingSession(testDatetimeString, testDescription, testUser);
        session.editExercise(Exercise.PULL_UP, "10"); // 1 Point
        assertEquals(10, session.getExercisePerformance(Exercise.PULL_UP));

        session.editExercise(Exercise.SHUTTLE_RUN, "11.0"); // 3 Points
        assertEquals(110, session.getExercisePerformance(Exercise.SHUTTLE_RUN));

        session.editExercise(Exercise.WALK_AND_RUN, "12:00"); // 4 Points
        assertEquals(12*60, session.getExercisePerformance(Exercise.WALK_AND_RUN));

        session.editExercise(Exercise.SIT_UP, "0"); // 5 Points
        assertEquals(0, session.getExercisePerformance(Exercise.SIT_UP));
    }

    @Test
    public void editExercisePullUpStation() {
        new TrainingSession(testDatetimeString, testDescription, testUser).editExercise(Exercise.PULL_UP, "1");
        assertEquals("Exercise edited! Here's your new input: " +
                "Reps: 1 | 0 points" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void editExerciseShuttleRunStation() {
        new TrainingSession(testDatetimeString, testDescription, testUser).editExercise(Exercise.SHUTTLE_RUN,
                "11.0");
        assertEquals("Exercise edited! Here's your new input: " +
                "Time: 11.0s | 3 points" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void editWalkAndRunStation1() {
        new TrainingSession(testDatetimeString, testDescription, testUser).editExercise(Exercise.WALK_AND_RUN,
                "11:30");
        assertEquals("Exercise edited! Here's your new input: " +
                "Time: 11:30 | 5 points" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void editWalkAndRunStation2() {
        new TrainingSession(testDatetimeString, testDescription, testUser).editExercise(Exercise.WALK_AND_RUN,
                "08:30");
        assertEquals("Exercise edited! Here's your new input: " +
                "Time: 08:30 | 5 points" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void editWalkAndRunStation3() {
        new TrainingSession(testDatetimeString, testDescription, testUser).editExercise(Exercise.WALK_AND_RUN,
                "11:03", true);
        assertEquals("Exercise edited! Here's your new input: " +
                "Time: 11:03 | 5 points" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void getTotalPoints() {
        TrainingSession session = new TrainingSession(testDatetimeString, testDescription, testUser);

        session.editExercise(Exercise.PULL_UP, "10"); // 1 Point
        session.editExercise(Exercise.SHUTTLE_RUN, "11.0"); // 3 Points
        session.editExercise(Exercise.WALK_AND_RUN, "12:00"); // 4 Points
        session.editExercise(Exercise.SIT_UP, "60"); // 5 Points
        session.editExercise(Exercise.SIT_AND_REACH, "40"); // 4 Points
        session.editExercise(Exercise.STANDING_BROAD_JUMP, "220"); // 5 Points
        assertEquals( 22, session.getTotalPoints()); // Expect a total of 22 points
    }

    @Test
    public void editSessionMood() {
        TrainingSession test = new TrainingSession(testDatetimeString, testDescription, testUser);
        test.setMood(testEditedMood);
        test.viewSession();

        assertEquals("Training Session: " + testDescription + System.lineSeparator() +
                        "Training Datetime: " + testDatetimeOutput + System.lineSeparator() +
                        "Mood: " + testEditedMood + System.lineSeparator() +
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

    @Test
    public void testGetSessionDescription(){
        assertEquals(testDescription,
                new TrainingSession(testDatetimeString, testDescription, testUser).getSessionDescription());
    }

    @Test
    public void testGetSessionDatetime(){
        assertEquals(testDatetimeOutput,
                new TrainingSession(testDatetimeString, testDescription, testUser).getSessionDatetime());
    }


    @Test
    public void testPrintSessionInformation(){
        new TrainingSession(testDatetimeString, testDescription, testUser).printSessionInformation();
        assertEquals(testDescription + " | " + testDatetimeOutput + System.lineSeparator(),
                outContent.toString());
    }

    @Test
    public void testViewSessionNoAward() {
        TrainingSession session = new TrainingSession(testDatetimeString, testDescription, testUser);
        session.viewSession();
        assertEquals("Training Session: " + testDescription + System.lineSeparator() +
                "Training Datetime: " + testDatetimeOutput + System.lineSeparator() +
                "Mood: " + testDefaultMood + System.lineSeparator() +
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

    @Test
    public void testViewSessionBronzeAward() {
        TrainingSession session = new TrainingSession(testDatetimeString, testDescription, testUser);
        session.editExercise(Exercise.PULL_UP, "7", false); // 1 Point
        session.editExercise(Exercise.SHUTTLE_RUN, "11.9", false); // 1 Points
        session.editExercise(Exercise.WALK_AND_RUN, "16:00", false); // 1 Points
        session.editExercise(Exercise.SIT_UP, "25", false); // 1 Points
        session.editExercise(Exercise.SIT_AND_REACH, "25", false); // 1 Points
        session.editExercise(Exercise.STANDING_BROAD_JUMP, "164", false); // 1 Points

        session.viewSession();
        assertEquals("Training Session: " + testDescription + System.lineSeparator() +
                "Training Datetime: " + testDatetimeOutput + System.lineSeparator() +
                "Mood: " + testDefaultMood + System.lineSeparator() +
                "Pull Up Station | Reps: 7 | 1 points" + System.lineSeparator() +
                "Shuttle Run Station | Time: 11.9s | 1 points" + System.lineSeparator() +
                "Sit and Reach Station | Distance: 25cm | 1 points" + System.lineSeparator() +
                "Sit Up Station | Reps: 25 | 1 points" + System.lineSeparator() +
                "Standing Broad Jump Station | Distance: 164cm | 1 points" + System.lineSeparator() +
                "Walk and Run Station | Time: 16:00 | 1 points" + System.lineSeparator() +
                "Total points: " + 6 + System.lineSeparator() +
                "Overall Award: Bronze" + System.lineSeparator(),
                outContent.toString());
    }

    @Test
    public void testViewSessionSilverAward() {
        TrainingSession session = new TrainingSession(testDatetimeString, testDescription, testUser);
        session.editExercise(Exercise.PULL_UP, "12", false); // 2 Point
        session.editExercise(Exercise.SHUTTLE_RUN, "11.2", false); // 2 Points
        session.editExercise(Exercise.WALK_AND_RUN, "13:41", false); // 2 Points
        session.editExercise(Exercise.SIT_UP, "33", false); // 2 Points
        session.editExercise(Exercise.SIT_AND_REACH, "33", false); // 2 Points
        session.editExercise(Exercise.STANDING_BROAD_JUMP, "215", false); // 5 Points

        session.viewSession();
        assertEquals("Training Session: " + testDescription + System.lineSeparator() +
                "Training Datetime: " + testDatetimeOutput + System.lineSeparator() +
                "Mood: " + testDefaultMood + System.lineSeparator() +
                "Pull Up Station | Reps: 12 | 2 points" + System.lineSeparator() +
                "Shuttle Run Station | Time: 11.2s | 2 points" + System.lineSeparator() +
                "Sit and Reach Station | Distance: 33cm | 2 points" + System.lineSeparator() +
                "Sit Up Station | Reps: 33 | 2 points" + System.lineSeparator() +
                "Standing Broad Jump Station | Distance: 215cm | 5 points" + System.lineSeparator() +
                "Walk and Run Station | Time: 13:41 | 2 points" + System.lineSeparator() +
                "Total points: " + 15 + System.lineSeparator() +
                "Overall Award: Silver" + System.lineSeparator(),
                outContent.toString());
    }

    @Test
    public void testViewSessionGoldAward() {
        TrainingSession session = new TrainingSession(testDatetimeString, testDescription, testUser);
        session.editExercise(Exercise.PULL_UP, "22", false); // 4 Point
        session.editExercise(Exercise.SHUTTLE_RUN, "10.3", false); // 4 Points
        session.editExercise(Exercise.WALK_AND_RUN, "12:31", false); // 3 Points
        session.editExercise(Exercise.SIT_UP, "34", false); // 3 Points
        session.editExercise(Exercise.SIT_AND_REACH, "41", false); // 4 Points
        session.editExercise(Exercise.STANDING_BROAD_JUMP, "215", false); // 5 Points

        session.viewSession();
        assertEquals("Training Session: " + testDescription + System.lineSeparator() +
                "Training Datetime: " + testDatetimeOutput + System.lineSeparator() +
                "Mood: " + testDefaultMood + System.lineSeparator() +
                "Pull Up Station | Reps: 22 | 4 points" + System.lineSeparator() +
                "Shuttle Run Station | Time: 10.3s | 4 points" + System.lineSeparator() +
                "Sit and Reach Station | Distance: 41cm | 4 points" + System.lineSeparator() +
                "Sit Up Station | Reps: 34 | 3 points" + System.lineSeparator() +
                "Standing Broad Jump Station | Distance: 215cm | 5 points" + System.lineSeparator() +
                "Walk and Run Station | Time: 12:31 | 3 points" + System.lineSeparator() +
                "Total points: " + 23 + System.lineSeparator() +
                "Overall Award: Gold" + System.lineSeparator(),
                outContent.toString());
    }
}
