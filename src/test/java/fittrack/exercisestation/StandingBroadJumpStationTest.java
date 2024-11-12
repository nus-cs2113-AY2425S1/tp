package fittrack.exercisestation;

import fittrack.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StandingBroadJumpStationTest {
    private User user;
    private StandingBroadJumpStation standingBroadJumpStation;

    @BeforeEach
    void setUp() {
        user = new User("male", "16");
        standingBroadJumpStation = new StandingBroadJumpStation();
    }

    @Test
    void testDefaultValues() {
        assertEquals(0, standingBroadJumpStation.getPoints(user));
        assertEquals("Standing Broad Jump Station", standingBroadJumpStation.getName());
    }

    @Test
    void testSetPerformance() {
        standingBroadJumpStation.setPerformance(200);
        int points = standingBroadJumpStation.getPoints(user);
        assertEquals(0, points);
    }

    @Test
    void testToString() {
        standingBroadJumpStation.setPerformance(245);
        standingBroadJumpStation.getPoints(user);
        assertEquals("Distance: 245cm | 4 points", standingBroadJumpStation.toString());
    }
}
