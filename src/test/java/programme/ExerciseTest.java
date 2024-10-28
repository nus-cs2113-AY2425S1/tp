package programme;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExerciseTest {

    private Exercise exercise;

    @BeforeEach
    void setUp() {
        // Set up an initial exercise object
        exercise = new Exercise(3, 10, 50, 160,"Bench_Press");
    }

    @Test
    void testUpdateExerciseAllFieldsNonNull() {
        Exercise updateExercise = new Exercise(4, 12, 60, 200,"Squat");
        //TODO: use exercise equals() to test
        exercise.updateExercise(updateExercise);
    }

    @Test
    void testUpdateExerciseOnlySomeFieldsNonNull() {
        Exercise updateExercise = new Exercise(-1, 12, 40, -1,"");
        //TODO: use exercise equals() to test
        exercise.updateExercise(updateExercise);
    }
}


