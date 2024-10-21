package programme;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProgrammeTest {

    private Programme programme;
    private Day mockDay1;
    private Day mockDay2;

    @BeforeEach
    void setUp() {
        mockDay1 = mock(Day.class);
        mockDay2 = mock(Day.class);

        ArrayList<Day> dayList = new ArrayList<>();
        dayList.add(mockDay1);
        dayList.add(mockDay2);

        programme = new Programme("My Programme", dayList);
    }

    @Test
    void testGetDay() {
        Day day = programme.getDay(0);
        assertEquals(mockDay1, day);
    }

    @Test
    void testInsertDay(){
        Day mockDayToInsert = mock(Day.class);
        programme.insertDay(mockDayToInsert);

        //Compare objects instead of string outputs
        assertEquals(mockDayToInsert, programme.getDay(2));
        assertEquals(3, programme.getDayCount());
    }

    @Test
    void testDeleteDayValidIndex(){
        Day mockDayToDelete = programme.deleteDay(0);

        assertEquals(mockDay1, mockDayToDelete);
        assertEquals(1, programme.getDayCount());
    }

    @Test
    void testDeleteDayInvalidIndex() {
        // Verify that an invalid index throws an IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> {programme.deleteDay(5);});

        // Verify that the size of the day list remains unchanged
        assertEquals(2, programme.getDayCount());
    }

    @Test
    void testToString() {
        // Stub the toString method of the mock days
        when(mockDay1.toString()).thenReturn("Mocked Day 1");
        when(mockDay2.toString()).thenReturn("Mocked Day 2");

        String programmeString = programme.toString();

        String expectedString = """
                My Programme
                
                Day 1: Mocked Day 1
                Day 2: Mocked Day 2
                """;

        assertEquals(expectedString, programmeString);
    }
}


