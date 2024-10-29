package programme;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProgrammeListTest {

    private ProgrammeList programmeList;
    private Programme mockProgramme1;
    private Programme mockProgramme2;
    private Day mockDay1;
    private Day mockDay2;

    @BeforeEach
    public void setUp() {
        // Initialize ProgrammeList
        programmeList = new ProgrammeList();

        // Create mock Programme and Day objects
        mockProgramme1 = mock(Programme.class);
        mockProgramme2 = mock(Programme.class);
        mockDay1 = mock(Day.class);
        mockDay2 = mock(Day.class);

        // Stub the getDay(0) behavior for mockProgramme1
        when(mockProgramme1.getDay(0)).thenReturn(mockDay1);
        when(mockProgramme1.deleteDay(0)).thenReturn(mockDay1);

        // Insert mock Programmes into ProgrammeList
        ArrayList<Day> days1 = new ArrayList<>();
        days1.add(mockDay1);
        programmeList.insertProgramme("Mocked Programme 1", days1);

        ArrayList<Day> days2 = new ArrayList<>();
        days2.add(mockDay2);
        programmeList.insertProgramme("Mocked Programme 2", days2);

        // Replace with mocked Programmes
        programmeList.getProgrammeList().set(0, mockProgramme1);
        programmeList.getProgrammeList().set(1, mockProgramme2);
    }

    @Test
    void testInsertProgramme() {
        // Insert a new programme
        ArrayList<Day> days = new ArrayList<>();
        days.add(mockDay1);
        Programme newProgramme = programmeList.insertProgramme("New Programme", days);

        // Verify insertion
        assertEquals(newProgramme, programmeList.getProgramme(2));
        assertEquals(3, programmeList.getProgrammeListSize());
    }

    @Test
    void testDeleteProgrammeValidIndex() {
        // Delete the programme at index 0
        Programme deletedProgramme = programmeList.deleteProgram(0);

        // Verify deletion
        assertEquals(mockProgramme1, deletedProgramme);
        assertEquals(1, programmeList.getProgrammeListSize());
    }

    @Test
    void testDeleteProgrammeInvalidIndex() {
        // Verify that an invalid index throws an IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> programmeList.deleteProgram(5));

        // Verify that the size remains unchanged
        assertEquals(2, programmeList.getProgrammeListSize());
    }

    @Test
    void testGetProgramme() {
        // Verify retrieval of programme at index 1
        Programme programme = programmeList.getProgramme(1);
        assertEquals(mockProgramme2, programme);
    }

    @Test
    void testStartProgramme() {
        // Set the active programme to index 1
        Programme activeProgramme = programmeList.startProgramme(1);

        // Verify that the active programme is set correctly
        assertEquals(mockProgramme2, activeProgramme);
    }

    @Test
    void testToString() {
        // Stub the toString() method of the mock programmes
        when(mockProgramme1.toString()).thenReturn("Mocked Programme 1");
        when(mockProgramme2.toString()).thenReturn("Mocked Programme 2");

        // Set the active programme to index 0
        programmeList.startProgramme(0);

        // Get the string representation of the ProgrammeList
        String programmeListString = programmeList.toString();

        // Expected string with the first programme marked as active
        String expectedString = "*Active* Mocked Programme 1\nMocked Programme 2\n";

        // Verify the expected output
        assertEquals(expectedString, programmeListString);
    }

}
