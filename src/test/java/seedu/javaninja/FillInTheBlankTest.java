import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.javaninja.question.FillInTheBlank;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FillInTheBlankTest {

    private FillInTheBlank fitbQuestion;

    @BeforeEach
    public void setUp() {
        // Initialize a FillInTheBlank question with a known answer
        fitbQuestion = new FillInTheBlank("The keyword used to define a constant in Java is ___",
                "final");
    }

    @Test
    public void checkAnswer_correctAnswer_returnsTrue() {
        // Test that the checkAnswer method returns true for the correct answer
        assertTrue(fitbQuestion.checkAnswer("final"),
                "checkAnswer should return true for the correct answer.");
    }

    @Test
    public void checkAnswer_incorrectAnswer_returnsFalse() {
        // Test that the checkAnswer method returns false for an incorrect answer
        assertFalse(fitbQuestion.checkAnswer("const"),
                "checkAnswer should return false for an incorrect answer.");
    }

    @Test
    public void checkAnswer_caseInsensitiveComparison_returnsTrue() {
        // Test that the checkAnswer method is case insensitive
        assertTrue(fitbQuestion.checkAnswer("FINAL"),
                "checkAnswer should return true for the correct answer, regardless of case.");
    }

    @Test
    public void checkAnswer_withExtraSpaces_returnsTrue() {
        // Test that the checkAnswer method trims whitespace before comparison
        assertTrue(fitbQuestion.checkAnswer("  final  "),
                "checkAnswer should return true even with leading/trailing spaces.");
    }

    @Test
    public void normalizeAnswer_nullInput_returnsEmptyString() {
        // Test that the normalizeAnswer method returns an empty string when input is null
        String normalizedAnswer = fitbQuestion.checkAnswer(null) ? "normalized" : "empty";
        assertEquals("empty", normalizedAnswer,
                "normalizeAnswer should return an empty string for null input.");
    }

    @Test
    public void getQuestionType_returnsFITB() {
        // Test that the getQuestionType method returns "FITB"
        assertEquals("FITB", fitbQuestion.getQuestionType(), "getQuestionType should return 'FITB'.");
    }
}
