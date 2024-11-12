// @@author nirala-ts

package programme;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static common.Utils.NULL_INTEGER;


class ExerciseTest {



    private Exercise exercise;

    @BeforeEach
    void setUp() {
        exercise = new Exercise(1, 2, 3, 400, "Bench_Press");
    }

    @Test
    void testConstructor() {
        Exercise expectedExercise = new Exercise(1, 2, 3, 400, "Bench_Press");
        assertEquals(expectedExercise, exercise);
    }

    @Test
    void testUpdateExerciseSets() {
        ExerciseUpdate update = new ExerciseUpdate(5, NULL_INTEGER,
                NULL_INTEGER, NULL_INTEGER, null);
        exercise.updateExercise(update);

        Exercise expectedExercise = new Exercise(5, 2,
                3, 400, "Bench_Press");
        assertEquals(expectedExercise, exercise);
    }

    @Test
    void testUpdateExerciseReps() {
        ExerciseUpdate update = new ExerciseUpdate(NULL_INTEGER, 12,
                NULL_INTEGER, NULL_INTEGER, null);
        exercise.updateExercise(update);

        Exercise expectedExercise = new Exercise(1, 12,
                3, 400, "Bench_Press");
        assertEquals(expectedExercise, exercise);
    }

    @Test
    void testUpdateExerciseWeight() {
        ExerciseUpdate update = new ExerciseUpdate(NULL_INTEGER, NULL_INTEGER,
                60, NULL_INTEGER, null);
        exercise.updateExercise(update);

        Exercise expectedExercise = new Exercise(1, 2,
                60, 400, "Bench_Press");
        assertEquals(expectedExercise, exercise);
    }

    @Test
    void testUpdateExerciseCalories() {
        ExerciseUpdate update = new ExerciseUpdate(NULL_INTEGER, NULL_INTEGER,
                NULL_INTEGER, 200, null);
        exercise.updateExercise(update);

        Exercise expectedExercise = new Exercise(1, 2,
                3, 200, "Bench_Press");
        assertEquals(expectedExercise, exercise);
    }

    @Test
    void testUpdateExerciseName() {
        ExerciseUpdate update = new ExerciseUpdate(NULL_INTEGER, NULL_INTEGER,
                NULL_INTEGER, NULL_INTEGER, "Incline_Bench_Press");
        exercise.updateExercise(update);

        Exercise expectedExercise = new Exercise(1, 2,
                3, 400, "Incline_Bench_Press");
        assertEquals(expectedExercise, exercise);
    }

    @Test
    void testUpdateMultipleFields() {
        ExerciseUpdate update = new ExerciseUpdate(4, 12,
                55, 180, "Incline_Bench_Press");
        exercise.updateExercise(update);

        Exercise expectedExercise = new Exercise(4, 12,
                55, 180, "Incline_Bench_Press");
        assertEquals(expectedExercise, exercise);
    }

    @Test
    void testEqualsAndHashCode() {
        Exercise exerciseCopy = new Exercise(1, 2, 3, 400, "Bench_Press");
        assertEquals(exercise, exerciseCopy);
        assertEquals(exercise.hashCode(), exerciseCopy.hashCode());
    }

    @Test
    void testNotEquals() {
        Exercise differentExercise = new Exercise(3, 10, 50, 160, "Squats");
        assertNotEquals(exercise, differentExercise);
    }

    @Test
    void testToString() {
        String expectedString = "Bench_Press: 1 sets of 2 at 3kg | Burnt 400 kcal";
        assertEquals(expectedString, exercise.toString());
    }

    @Test
    void testGetCalories() {
        assertEquals(400, exercise.getCalories());
    }

    @Test
    void testGetName() {
        assertEquals("Bench_Press", exercise.getName());
    }

    @Test
    void testGetWeight() { assertEquals(3, exercise.getWeight()); }
}

