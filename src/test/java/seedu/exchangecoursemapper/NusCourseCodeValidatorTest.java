package seedu.exchangecoursemapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.exchangecoursemapper.parser.NusCourseCodeValidator.isValidNusCourseCodeFormat;
import static seedu.exchangecoursemapper.parser.NusCourseCodeValidator.isValidSocCourseCode;

public class NusCourseCodeValidatorTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void isValidSocCourseCode_inputWithCsCourseCode_expectTrue() {
        String userInput = "cs3244";
        boolean isValidSocCourseCode = isValidSocCourseCode(userInput);
        assertTrue(isValidSocCourseCode);
    }

    @Test
    public void isValidSocCourseCode_inputWithIsCourseCode_expectTrue() {
        String userInput = "gess1000";
        boolean isValidSocCourseCode = isValidSocCourseCode(userInput);
        assertFalse(isValidSocCourseCode);
    }

    @Test
    public void isValidNusCourseCodeFormat_inputWithValidCourseCode_expectTrue() {
        String userInput = "cs3241";
        boolean isValidSocCourseCode = isValidSocCourseCode(userInput);
        assertTrue(isValidSocCourseCode);
    }

    @Test
    public void isValidNusCourseCodeFormat_inputWithInvalidCourseCode_expectFalse() {
        String userInput = "eeeeee";
        boolean isValidSocCourseCode = isValidNusCourseCodeFormat(userInput);
        assertFalse(isValidSocCourseCode);
    }

    @Test
    public void isValidNusCourseCodeFormat_inputWithLongerCourseCode_expectFalse() {
        String userInput = "cs12311231";
        boolean isValidSocCourseCode = isValidNusCourseCodeFormat(userInput);
        assertFalse(isValidSocCourseCode);
    }
}
