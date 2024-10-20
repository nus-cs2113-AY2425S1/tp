package programme;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExerciseTest {

    private Exercise exercise;

    @BeforeEach
    void setUp() {
        // Set up an initial exercise object
        exercise = new Exercise(3, 10, 50, "Bench_Press");
    }

    @Test
    void testUpdateExerciseAllFieldsNonNull() {
        Exercise updateExercise = new Exercise(4, 12, 60, "Squat");

        exercise.updateExercise(updateExercise);

        assertEquals("Squat: 4 sets of 12 reps at 60 kg", exercise.toString());
    }

    @Test
    void testUpdateExerciseOnlySomeFieldsNonNull() {
        Exercise updateExercise = new Exercise(-1, 12, 40, "");

        exercise.updateExercise(updateExercise);

        assertEquals("Bench Press: 3 sets of 12 reps at 40 kg", exercise.toString());
    }
}


