package fittrack.parser;

import fittrack.healthprofile.FoodWaterIntake;
import fittrack.trainingsession.TrainingSession;
import fittrack.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private User testUser;
    private ArrayList<TrainingSession> sessionList;

    @BeforeEach
    public void setUp() {
        testUser = new User("MALE", "25");
        sessionList = new ArrayList<>();

        LocalDateTime startDateTime = LocalDateTime.parse("11/11/2024 10:00",
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        // Create 5 sessions with chronologically increasing datetime values
        for (int i = 0; i < 5; i++) {
            LocalDateTime sessionDateTime = startDateTime.plusHours(i);  // Increment by 1 hour for each session
            TrainingSession session =
                    new TrainingSession(sessionDateTime, "Test Session " + (i + 1), testUser);
            sessionList.add(session);
        }
    }

    @Test
    public void testModifySessionDateTime_NoReorder() throws IOException {
        // Modify the datetime of a session to a value that doesn't change its order
        String input = "modify 2 11/11/2024 11:10"; // Modify session at index 2 (original datetime 2024-11-12 11:00)

        Parser.parse(testUser, input, sessionList, new ArrayList<>(), new ArrayList<>(), new FoodWaterIntake());

        // Verify the session list order is unchanged
        for (int i = 0; i < sessionList.size(); i++) {
            assertEquals("Test Session " + (i + 1), sessionList.get(i).getSessionDescription());
            // Verify that the datetime has been updated
            if (i == 1) {
                assertEquals("11/11/2024 11:10", sessionList.get(i).getSessionDatetime());
            }
        }
    }

    @Test
    public void testModifySessionDateTime_Reorder() throws IOException {
        // Modify the datetime of a session to make it fall earlier (to trigger reordering)
        String input = "modify 3 10/11/2024 10:00";

        Parser.parse(testUser, input, sessionList, new ArrayList<>(), new ArrayList<>(), new FoodWaterIntake());

        // Verify the order of session datetime after modification
        String expectedFirstSessionDatetime = "10/11/2024 10:00";
        String expectedSecondSessionDatetime = "11/11/2024 10:00";

        assertEquals(expectedFirstSessionDatetime, sessionList.get(0).getSessionDatetime());
        assertEquals(expectedSecondSessionDatetime, sessionList.get(1).getSessionDatetime());
    }
}
