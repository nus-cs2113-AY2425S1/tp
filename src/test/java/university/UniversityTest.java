package university;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UniversityTest {
    private University uni;

    @BeforeEach
    public void setUp(){
        uni = new University("National University of Singapore","NUS", 5);
    }

    @Test
    public void testConstruction(){
        assertNotNull(uni);
        assertEquals("National University of Singapore", uni.getFullName());
        assertEquals("NUS", uni.getAcronym());
        assertEquals(5, uni.getSpotsLeft());
    }

    @Test
    public void testRemoveASpot(){
        uni.removeASpot();
        assertEquals(4, uni.getSpotsLeft());
    }

    @Test
    public void testToString(){
        assertEquals("National University of Singapore (NUS)", uni.toString());
    }
    
}
