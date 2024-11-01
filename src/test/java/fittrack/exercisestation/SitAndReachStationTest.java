package fittrack.exercisestation;

import fittrack.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SitAndReachStationTest {
    private User user;
    private SitAndReachStation sitAndReachStation;

    @BeforeEach
    void setUp() {
        user = new User("male", "12");
        sitAndReachStation = new SitAndReachStation();
    }

    @Test
    void testDefaultValues() {
        assertEquals(0, sitAndReachStation.getPoints(user));
        assertEquals("Sit and Reach Station", sitAndReachStation.getName());
    }

    @Test
    void testSetPerformance() {
        sitAndReachStation.setPerformance(28);
        int points = sitAndReachStation.getPoints(user);
        assertEquals(2, points);
    }

    @Test
    void testToString() {
        sitAndReachStation.setPerformance(32);
        sitAndReachStation.getPoints(user);
        assertEquals("Distance: 32cm | 3 points", sitAndReachStation.toString());
    }
}
