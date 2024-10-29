package command.programme.edit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EditCommandTest {

    private static final int VALID_PROGRAMME_ID = 0;
    private static final int INVALID_PROGRAMME_ID = -2;
    private static final int VALID_DAY_ID = 0;
    private static final int VALID_EXERCISE_ID = 0;
    private static final int INVALID_EXERCISE_ID = -1;
    private static final int INVALID_DAY_ID = -1;

    // Test for the constructor with valid parameters
    @Test
    void constructor_withValidParameters_initializesCorrectly() {
        assertDoesNotThrow(() -> new TestEditCommand(VALID_PROGRAMME_ID, VALID_DAY_ID, VALID_EXERCISE_ID));
    }

    // Edge case for constructor with exerciseId: Negative exercise ID
    @Test
    void constructor_withNegativeExerciseId_throwsAssertionError() {
        assertThrows(AssertionError.class, () ->
                new TestEditCommand(VALID_PROGRAMME_ID, VALID_DAY_ID, INVALID_EXERCISE_ID)
        );
    }

    // Happy path for constructor without exerciseId
    @Test
    void constructor_withoutExerciseId_initializesCorrectly() {
        assertDoesNotThrow(() -> new TestEditCommand(VALID_PROGRAMME_ID, VALID_DAY_ID));
    }

    // Edge case for constructor without exerciseId: Invalid day ID
    @Test
    void constructor_withNegativeDayId_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new TestEditCommand(VALID_PROGRAMME_ID, INVALID_DAY_ID));
    }

    // Happy path for constructor with only programme index
    @Test
    void constructor_withProgrammeIndex_initializesCorrectly() {
        assertDoesNotThrow(() -> new TestEditCommand(VALID_PROGRAMME_ID));
    }

    // Edge case for constructor with programme index: Negative programme index
    @Test
    void constructor_withNegativeProgrammeIndex_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new TestEditCommand(INVALID_PROGRAMME_ID));
    }
}
