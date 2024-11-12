package fittrack.exercisestation;

import fittrack.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShuttleRunStationTest {
    private User user;
    private ShuttleRunStation shuttleRunStation;

    @BeforeEach
    void setUp() {
        user = new User("female", "18");
        shuttleRunStation = new ShuttleRunStation();
    }

    @Test
    void testDefaultValues() {
        assertEquals(0, shuttleRunStation.getPoints(user));
        assertEquals("Shuttle Run Station", shuttleRunStation.getName());
        assertEquals("NA", shuttleRunStation.getTime());
    }

    @Test
    void testSetPerformance() {
        shuttleRunStation.setPerformance(117);
        int points = shuttleRunStation.getPoints(user);
        assertEquals(3, points);
    }

    @Test
    void testToString() {
        shuttleRunStation.setPerformance(123);
        shuttleRunStation.getPoints(user);
        assertEquals("Time: 12.3s | 1 points", shuttleRunStation.toString());
    }
}
