package fittrack.exercisestation;

import fittrack.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WalkAndRunStationTest {
    private User user;
    private WalkAndRunStation walkAndRunStation;

    @BeforeEach
    void setUp() {
        user = new User("female", "16");
        walkAndRunStation = new WalkAndRunStation();
    }

    @Test
    void testDefaultValues() {
        assertEquals(0, walkAndRunStation.getPoints(user));
        assertEquals("Walk and Run Station", walkAndRunStation.getName());
        assertEquals("NA", walkAndRunStation.getTime());
    }

    @Test
    void testSetPerformance() {
        walkAndRunStation.setPerformance(900);
        int points = walkAndRunStation.getPoints(user);
        assertEquals(4, points);
    }

    @Test
    void testToString() {
        walkAndRunStation.setPerformance(1010);
        walkAndRunStation.getPoints(user);
        assertEquals("Time: 16:50 | 2 points", walkAndRunStation.toString().toString());
    }
}
