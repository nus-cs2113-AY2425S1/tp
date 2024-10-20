package programme;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProgrammeListTest {

    private ProgrammeList programmeList;
    private Programme mockProgramme1;
    private Programme mockProgramme2;
    private Day mockDay;

    @BeforeEach
    public void setUp() {
        programmeList = new ProgrammeList();

        mockProgramme1 = mock(Programme.class);
        mockProgramme2 = mock(Programme.class);

        programmeList.insertProgramme("Bulk", new ArrayList<>());
        programmeList.insertProgramme("Cut", new ArrayList<>());

        mockDay = mock(Day.class);
    }

    @Test
    void testInsertProgramme() {
        ArrayList<Day> days = new ArrayList<>();
        Programme newProgramme = programmeList.insertProgramme("New Programme", days);

        assertEquals("New Programme", newProgramme.toString());
        assertEquals(newProgramme, programmeList.getProgramme(2));
    }

    @Test
    void testDeleteProgrammeValidIndex() {
        Programme deletedProgramme = programmeList.deleteProgram(0);

        assertEquals("Programme 1", deletedProgramme.toString());
        assertEquals(2, programmeList.getProgrammeListSize());
    }

    @Test
    void testDeleteProgrammeInvalidIndex() {
        Programme deletedProgramme = programmeList.deleteProgram(5);

        assertNull(deletedProgramme);
        assertEquals(3, programmeList.getProgrammeListSize());
    }

    @Test
    void testStartProgramme() {
        Programme activeProgramme = programmeList.startProgramme(1);
        assertEquals(mockProgramme2, activeProgramme);
    }

    @Test
    void testGetDay() {
        // Stub the behaviour of the mock Programme and Day objects
        when(mockProgramme1.getDay(0)).thenReturn(mockDay);

        programmeList.startProgramme(0);

        Day retrievedDay = programmeList.getDay(0, 0);

        assertEquals(mockDay, retrievedDay);
    }

    @Test
    void testToString() {
        // Stub the toString() method of the mock programmes
        when(mockProgramme1.toString()).thenReturn("Mocked Programme 1");
        when(mockProgramme2.toString()).thenReturn("Mocked Programme 2");

        String programmeListString = programmeList.toString();

        String expectedString = "*Active* Mocked Programme 1" +
                "Mocked Programme 2";

        assertEquals(expectedString, programmeListString);
    }

}
