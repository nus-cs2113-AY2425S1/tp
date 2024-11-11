/**
 * This class contains unit tests for the {@link TrueFalse} class to ensure proper functionality.
 */
package seedu.javaninja;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.javaninja.question.TrueFalse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrueFalseTest {

    private TrueFalse trueFalseQuestion;

    /**
     * Sets up the test environment before each test by initializing a TrueFalse question instance.
     */
    @BeforeEach
    public void setUp() {
        trueFalseQuestion = new TrueFalse("Java supports multiple inheritance.", false);
    }

    /**
     * Tests the constructor of {@link TrueFalse} to ensure that the question is properly initialized.
     */
    @Test
    public void constructor_initializesQuestionCorrectly() {
        assertNotNull(trueFalseQuestion, "TrueFalse question should be initialized.");
        assertEquals("Java supports multiple inheritance.", trueFalseQuestion.getText(), "Question text should match the initialized text.");
    }

    /**
     * Tests the {@link TrueFalse#checkAnswer(String)} method to verify that correct answers are recognized.
     */
    @Test
    public void checkAnswer_correctAnswer_returnsTrue() {
        assertTrue(trueFalseQuestion.checkAnswer("false"), "Expected checkAnswer to return true for the correct answer.");
    }

    /**
     * Tests the {@link TrueFalse#checkAnswer(String)} method to verify that incorrect answers are recognized.
     */
    @Test
    public void checkAnswer_incorrectAnswer_returnsFalse() {
        assertFalse(trueFalseQuestion.checkAnswer("true"), "Expected checkAnswer to return false for the incorrect answer.");
    }

    /**
     * Tests the {@link TrueFalse#checkAnswer(String)} method to ensure it handles case insensitivity.
     */
    @Test
    public void checkAnswer_caseInsensitive_returnsTrue() {
        assertTrue(trueFalseQuestion.checkAnswer("FALSE"), "Expected checkAnswer to handle case insensitivity and return true.");
    }

    /**
     * Tests the {@link TrueFalse#checkAnswer(String)} method with invalid input and verifies that it throws an exception.
     */
    @Test
    public void checkAnswer_invalidInput_throwsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            trueFalseQuestion.checkAnswer("maybe");
        }, "Expected checkAnswer to throw an IllegalArgumentException for invalid input.");
        assertEquals("Invalid input! Please enter 'true' or 'false'.", exception.getMessage(), "Expected exception message to match.");
    }

    /**
     * Tests the {@link TrueFalse#getOptions()} method to ensure it returns the correct options.
     */
    @Test
    public void getOptions_returnsTrueAndFalseOptions() {
        List<String> options = trueFalseQuestion.getOptions();
        assertEquals(2, options.size(), "Expected options list to contain two items.");
        assertTrue(options.contains("true"), "Expected options to contain 'true'.");
        assertTrue(options.contains("false"), "Expected options to contain 'false'.");
    }

    /**
     * Tests the {@link TrueFalse#toString()} method to ensure the correct formatted string is returned.
     */
    @Test
    public void toString_returnsFormattedQuestion() {
        String expectedString = "Java supports multiple inheritance. (True/False)";
        assertEquals(expectedString, trueFalseQuestion.toString(), "Expected toString to return the correctly formatted question.");
    }
}
