package fittrack.exercisestation;

import fittrack.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PullUpStationTest {
    private User user;
    private PullUpStation pullUpStation;

    @BeforeEach
    void setUp() {
        user = new User("male", "20");
        pullUpStation = new PullUpStation();
    }

    @Test
    void testDefaultValues() {
        assertEquals(0, pullUpStation.getPoints(user));
        assertEquals("Pull Up Station", pullUpStation.getName());
    }

    @Test
    void testSetPerformance() {
        pullUpStation.setPerformance(15);
        int points = pullUpStation.getPoints(user);
        assertEquals(5, points);
    }

    @Test
    void testToString() {
        pullUpStation.setPerformance(10);
        pullUpStation.getPoints(user);
        assertEquals("Reps: 10 | 4 points", pullUpStation.toString());
    }
}
