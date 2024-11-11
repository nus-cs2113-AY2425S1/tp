package fittrack.exceptions;

import fittrack.exception.ParserExceptions;
import fittrack.trainingsession.TrainingSession;
import fittrack.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static fittrack.messages.Messages.INVALID_SESSION_INDEX_MESSAGE;
import static fittrack.messages.Messages.INVALID_SHUTTLE_RUN_TIMING_MESSAGE;
import static fittrack.messages.Messages.INVALID_WALK_AND_RUN_TIMING_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserExceptionsTest {
    public static final int SESSION_LIST_SIZE = 5;
    private User testUser;
    private TrainingSession session;

    @BeforeEach
    public void setUp() {
        testUser = new User("MALE", "20");
        session = new TrainingSession(java.time.LocalDateTime.now(), "Sample Session", testUser);
    }

    @Test
    public void testValidShuttleRunTiming() {
        // Valid case: Shuttle Run time in decimal format
        String[] result = ParserExceptions.validEditDetails("1 SR 11.2", SESSION_LIST_SIZE);
        assertEquals("1", result[0]);
        assertEquals("SR", result[1]);
        assertEquals("11.2", result[2]);

        // Invalid case: Shuttle Run with invalid decimal format
        Exception exception1 = assertThrows(IllegalArgumentException.class, () ->
                ParserExceptions.validEditDetails("1 SR 11.25", SESSION_LIST_SIZE));
        assertTrue(exception1.getMessage().contains(INVALID_SHUTTLE_RUN_TIMING_MESSAGE));

        // Invalid case: Shuttle Run with invalid decimal format
        Exception exception2 = assertThrows(IllegalArgumentException.class, () ->
                ParserExceptions.validEditDetails("1 SR 11.2.5", SESSION_LIST_SIZE));
        assertTrue(exception2.getMessage().contains(INVALID_SHUTTLE_RUN_TIMING_MESSAGE));

        // Invalid case: Non-numeric input for Shuttle Run
        Exception exception3 = assertThrows(IllegalArgumentException.class, () ->
                ParserExceptions.validEditDetails("1 SR abc", SESSION_LIST_SIZE));
        assertTrue(exception3.getMessage().contains(INVALID_SHUTTLE_RUN_TIMING_MESSAGE));
    }

    @Test
    public void testValidWalkAndRunTiming() {
        // Valid case: Walk and Run time in HH:MM format
        String[] result = ParserExceptions.validEditDetails("2 WAR 12:45", SESSION_LIST_SIZE);
        assertEquals("2", result[0]);
        assertEquals("WAR", result[1]);
        assertEquals("12:45", result[2]);

        // Invalid case: Walk and Run with invalid minutes (greater than 59)
        Exception exception1 = assertThrows(IllegalArgumentException.class, () ->
                ParserExceptions.validEditDetails("2 WAR 12:1234", SESSION_LIST_SIZE));
        assertTrue(exception1.getMessage().contains(INVALID_WALK_AND_RUN_TIMING_MESSAGE));

        // Invalid case: Walk and Run with more than one colon
        Exception exception2 = assertThrows(IllegalArgumentException.class, () ->
                ParserExceptions.validEditDetails("2 WAR 12:30:45", SESSION_LIST_SIZE));
        assertTrue(exception2.getMessage().contains(INVALID_WALK_AND_RUN_TIMING_MESSAGE));

        // Invalid case: Non-numeric input for Walk and Run
        Exception exception3 = assertThrows(IllegalArgumentException.class, () ->
                ParserExceptions.validEditDetails("2 WAR abc", SESSION_LIST_SIZE));
        assertTrue(exception3.getMessage().contains(INVALID_WALK_AND_RUN_TIMING_MESSAGE));
    }

    @Test
    public void testInvalidSessionIndex() {
        // Test invalid session index outside of range
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                ParserExceptions.validEditDetails("6 SR 11.2", SESSION_LIST_SIZE)); // Index 6 is out of range
        assertTrue(exception.getMessage().contains(INVALID_SESSION_INDEX_MESSAGE));
    }
}

