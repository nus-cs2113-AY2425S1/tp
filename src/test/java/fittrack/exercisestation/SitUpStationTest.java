package fittrack.exercisestation;

import fittrack.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SitUpStationTest {
    private User user;
    private SitUpStation sitUpStation;

    @BeforeEach
    void setUp() {
        user = new User("female", "14");
        sitUpStation = new SitUpStation();
    }

    @Test
    void testDefaultValues() {
        assertEquals(0, sitUpStation.getPoints(user));
        assertEquals("Sit Up Station", sitUpStation.getName());
    }

    @Test
    void testSetPerformance() {
        sitUpStation.setPerformance(28);
        int points = sitUpStation.getPoints(user);
        assertEquals(4, points);
    }

    @Test
    void testToString() {
        sitUpStation.setPerformance(50);
        sitUpStation.getPoints(user);
        assertEquals("Reps: 50 | 5 points", sitUpStation.toString());
    }
}
