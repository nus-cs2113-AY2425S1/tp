package programme;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DayTest {

    private Day day;

    @BeforeEach
    void setUp() {

        Exercise exercise1 = new Exercise(3, 10, 50, "Bench_Press");
        Exercise exercise2 = new Exercise(3, 12, 20, "Triceps_Extension");
        Exercise exercise3 = new Exercise(3, 10, 50, "Seated_Press");

        ArrayList<Exercise> exercises = new ArrayList<>();
        exercises.add(exercise1);
        exercises.add(exercise2);
        exercises.add(exercise3);

        day = new Day("Push", exercises);

    }

    @Test
    void testInsertExercises() {

        Exercise exercise4 = new Exercise(3, 12, 40, "Chest_Fly");
        day.insertExercise(exercise4);

        assertEquals(4, day.getExercisesCount());
        assertEquals(exercise4, day.getExercise(3));

    }

    @Test
    void testUpdateExercises() {
        Exercise updatedExercise = new Exercise(3, 12, 40, "Chest_Fly");

        day.updateExercise(2, updatedExercise);

        //compare strings instead of objects because although they are the same objects, memory addresses are different
        //so assertEquals fails
        assertEquals(updatedExercise.toString(), day.getExercise(2).toString());
    }

    @Test
    void testDeleteExercise() {
        Exercise deletedExercise = day.deleteExercise(2);

        assertEquals(2, day.getExercisesCount());
        assertEquals( "Seated Press: 3 sets of 10 reps at 50 kg", deletedExercise.toString());
    }

    @Test
    void testToString() {
        // Get the string representation of the Day object
        String dayString = day.toString();

        // Verify the expected output
        String expectedString = """
                Push
                
                1. Bench Press: 3 sets of 10 reps at 50 kg
                2. Triceps Extension: 3 sets of 12 reps at 20 kg
                3. Seated Press: 3 sets of 10 reps at 50 kg
                
                
                """;

        assertEquals(expectedString, dayString);
    }
}


