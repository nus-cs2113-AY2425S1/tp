/**
 * This class contains unit tests for the {@link Flashcard} class to ensure proper functionality with Java-related questions.
 */
package seedu.javaninja;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.javaninja.question.Flashcard;

import static org.junit.jupiter.api.Assertions.*;

class FlashcardTest {

    private Flashcard flashcard;

    /**
     * Sets up the test environment before each test by initializing a Flashcard instance with a Java-related question.
     */
    @BeforeEach
    public void setUp() {
        flashcard = new Flashcard("What is the default access modifier in Java?", "package-private");
    }

    /**
     * Tests the constructor of {@link Flashcard} to ensure that the question and answer are properly initialized.
     */
    @Test
    public void constructor_initializesFlashcardCorrectly() {
        assertNotNull(flashcard, "Flashcard should be initialized.");
        assertEquals("What is the default access modifier in Java?", flashcard.getText(), "Question text should match the initialized text.");
        assertEquals("package-private", flashcard.getCorrectAnswer(), "Answer should match the initialized answer.");
    }

    /**
     * Tests the {@link Flashcard#checkAnswer(String)} method to verify that correct answers are recognized.
     */
    @Test
    public void checkAnswer_correctAnswer_returnsTrue() {
        assertTrue(flashcard.checkAnswer("package-private"), "Expected checkAnswer to return true for the correct answer.");
    }

    /**
     * Tests the {@link Flashcard#checkAnswer(String)} method to verify that incorrect answers are recognized.
     */
    @Test
    public void checkAnswer_incorrectAnswer_returnsFalse() {
        assertFalse(flashcard.checkAnswer("public"), "Expected checkAnswer to return false for the incorrect answer.");
    }

    /**
     * Tests the {@link Flashcard#checkAnswer(String)} method to ensure it handles case insensitivity.
     */
    @Test
    public void checkAnswer_caseInsensitive_returnsTrue() {
        assertTrue(flashcard.checkAnswer("Package-Private"), "Expected checkAnswer to handle case insensitivity and return true.");
    }

    /**
     * Tests the {@link Flashcard#getOptions()} method to verify that it returns null as expected for flashcards.
     */
    @Test
    public void getOptions_returnsNull() {
        assertNull(flashcard.getOptions(), "Expected getOptions to return null for flashcards.");
    }

    /**
     * Tests the {@link Flashcard#getQuestionType()} method to ensure the correct question type is returned.
     */
    @Test
    public void getQuestionType_returnsFlashcards() {
        assertEquals("Flashcards", flashcard.getQuestionType(), "Expected getQuestionType to return 'Flashcards'.");
    }

    /**
     * Tests the {@link Flashcard#toString()} method to ensure that the correct formatted string is returned.
     */
    @Test
    public void toString_returnsFormattedQuestion() {
        String expectedString = "What is the default access modifier in Java?";
        assertEquals(expectedString, flashcard.toString(), "Expected toString to return the correctly formatted question.");
    }
}
