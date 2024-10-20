package programme;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExerciseTest {

    private Exercise exercise;

    @BeforeEach
    void setUp() {
        // Set up an initial exercise object
        exercise = new Exercise(3, 10, 50, "Bench_Press");
    }

    @Test
    void testUpdateExerciseAllFieldsNonNull() {
        // Create a mock Exercise object for updating
        Exercise mockUpdate = mock(Exercise.class);

        // Stub behavior for the mock update object
        when(mockUpdate.getSets()).thenReturn(4);
        when(mockUpdate.getReps()).thenReturn(12);
        when(mockUpdate.getWeight()).thenReturn(60);
        when(mockUpdate.getName()).thenReturn("Squat");

        // Update the exercise using the mock object
        exercise.updateExercise(mockUpdate);

        // Verify the exercise fields have been updated correctly
        assertEquals("Squat: 4 sets of 12 reps at 60 kg", exercise.toString());
    }

    @Test
    void testUpdateExerciseOnlySomeFieldsNonNull() {
        // Create a mock Exercise object for updating
        Exercise mockUpdate = mock(Exercise.class);

        // Stub the mock to update only reps and weight
        when(mockUpdate.getSets()).thenReturn(-1);  // No update for sets
        when(mockUpdate.getReps()).thenReturn(12);  // Update reps
        when(mockUpdate.getWeight()).thenReturn(40);  // Update weight
        when(mockUpdate.getName()).thenReturn("");  // No update for name

        // Update the exercise using the mock object
        exercise.updateExercise(mockUpdate);

        // Verify the updated fields
        assertEquals("Bench Press: 3 sets of 12 reps at 40 kg", exercise.toString());
    }
}