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

        day.updateExercise(3, updatedExercise);
        assertEquals(updatedExercise, day.getExercise(3));

    }

    @Test
    void testDeleteExercise() {

        Exercise deletedExercise = day.deleteExercise(3);

        assertEquals(3, day.getExercisesCount());
        assertEquals(deletedExercise, day.getExercise(3));
    }

    @Test
    void testToString() {
        // Get the string representation of the Day object
        String dayString = day.toString();

        // Verify the expected output
        String expectedString = """
                Monday
                
                1. Bench Press: 3 sets of 10 reps at 50 kg
                2. Squat: 4 sets of 12 reps at 60 kg
                
                """;

        assertEquals(expectedString, dayString);
    }
}
