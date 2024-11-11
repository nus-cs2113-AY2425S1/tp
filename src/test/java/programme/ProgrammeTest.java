// @@author nirala-ts

package programme;

import exceptions.ProgrammeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class ProgrammeTest {

    private Programme programme;
    private Day mockDay1;
    private Day mockDay2;

    @BeforeEach
    public void setUp() {
        mockDay1 = mock(Day.class);
        mockDay2 = mock(Day.class);

        ArrayList<Day> dayList = new ArrayList<>();
        dayList.add(mockDay1);
        programme = new Programme("Mocked Programme", dayList);
    }

    @Test
    void testConstructor() {
        ArrayList<Day> dayList = new ArrayList<>();
        dayList.add(mockDay1);
        Programme newProgramme = new Programme("Test Programme", dayList);

        assertEquals("Test Programme", newProgramme.getProgrammeName());
        assertEquals(1, newProgramme.getDayCount());
    }

    @Test
    void testGetProgrammeName() {
        assertEquals("Mocked Programme", programme.getProgrammeName());
    }

    @Test
    void testGetDayValidIndex() {
        Day day = programme.getDay(0);

        assertEquals(mockDay1, day);
    }

    @Test
    void testGetDayInvalidIndex() {
        assertThrows(ProgrammeException.class, () -> programme.getDay(5));
    }

    @Test
    void testInsertDay() {
        programme.insertDay(mockDay2);

        assertEquals(2, programme.getDayCount());
        assertEquals(mockDay2, programme.getDay(1));
    }

    @Test
    void testDeleteDayValidIndex() {
        Day deletedDay = programme.deleteDay(0);

        assertEquals(mockDay1, deletedDay);
        assertEquals(0, programme.getDayCount());
    }

    @Test
    void testDeleteDayInvalidIndex() {
        assertThrows(ProgrammeException.class, () -> programme.deleteDay(5));
    }

    @Test
    void testGetDayCount() {
        assertEquals(1, programme.getDayCount());
        programme.insertDay(mockDay2);
        assertEquals(2, programme.getDayCount());
    }

    @Test
    void testToString() {
        programme.insertDay(mockDay2);
        String expectedString = "Mocked Programme\n\nDay 1: " + mockDay1 + "\nDay 2: " + mockDay2 + "\n";

        assertEquals(expectedString, programme.toString());
    }

    @Test
    void testToStringEmptyProgramme() {
        Programme emptyProgramme = new Programme("Empty Programme", new ArrayList<>());
        String expectedString = "Empty Programme\n\n";

        assertEquals(expectedString, emptyProgramme.toString());
    }
}
