// @@author nirala-ts

package programme;

import exceptions.ProgrammeExceptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static common.Utils.NULL_INTEGER;
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
        programmeList = new ProgrammeList();

        // Create mock Programme and Day objects
        mockProgramme1 = mock(Programme.class);
        mockProgramme2 = mock(Programme.class);
        mockDay1 = mock(Day.class);
        mockDay2 = mock(Day.class);

        // Stub methods for mock programmes
        when(mockProgramme1.getProgrammeName()).thenReturn("Mocked Programme 1");
        when(mockProgramme2.getProgrammeName()).thenReturn("Mocked Programme 2");
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
        ArrayList<Day> days = new ArrayList<>();
        days.add(mockDay1);
        Programme newProgramme = programmeList.insertProgramme("New Programme", days);

        assertEquals(newProgramme, programmeList.getProgramme(2));
        assertEquals(3, programmeList.getProgrammeListSize());
    }

    @Test
    void testDeleteProgrammeValidIndex() {
        Programme deletedProgramme = programmeList.deleteProgram(0);

        assertEquals(mockProgramme1, deletedProgramme);
        assertEquals(1, programmeList.getProgrammeListSize());
    }

    @Test
    void testDeleteActiveProgramme() {
        if (programmeList.getCurrentActiveProgramme() != 0) {
            programmeList.startProgramme(0);
        }
        Programme deletedProgramme = programmeList.deleteProgram(-1);
        assertEquals(mockProgramme1, deletedProgramme);
        assertEquals(1, programmeList.getProgrammeListSize());
    }


    @Test
    void testDeleteProgrammeInvalidIndex() {
        assertThrows(ProgrammeExceptions.class, () -> programmeList.deleteProgram(5));

        assertEquals(2, programmeList.getProgrammeListSize());
    }

    @Test
    void testGetActiveProgramme() {
        if (programmeList.getCurrentActiveProgramme() != 0) {
            programmeList.startProgramme(0);
        }
        Programme activeProgramme = programmeList.getProgramme(-1);
        assertEquals(mockProgramme1, activeProgramme);
    }

    @Test
    void testGetProgrammeValidIndex() {
        Programme programme = programmeList.getProgramme(1);

        assertEquals(mockProgramme2, programme);
    }

    @Test
    void testGetCurrentActiveProgrammeWithActiveProgramme() {
        // Start a programme and check if the index is correctly returned
        programmeList.startProgramme(1);
        int currentActive = programmeList.getCurrentActiveProgramme();
        assertEquals(1, currentActive, "Should return the index of the current active programme.");
    }

    @Test
    void testGetProgrammeInvalidIndex() {
        assertThrows(ProgrammeExceptions.class, () -> programmeList.getProgramme(5));
    }

    @Test
    void testStartProgramme() {
        Programme activeProgramme = programmeList.startProgramme(1);

        assertEquals(mockProgramme2, activeProgramme);
    }

    @Test
    void testStartProgrammeInvalidIndex() {
        assertThrows(ProgrammeExceptions.class, () -> programmeList.startProgramme(5));
    }

    @Test
    void testToString() {
        if (programmeList.getCurrentActiveProgramme() != 0) {
            programmeList.startProgramme(0);
        }
        String programmeListString = programmeList.toString();
        String expectedString = "1. Mocked Programme 1 -- Active\n2. Mocked Programme 2\n";

        assertEquals(expectedString, programmeListString);
    }

    @Test
    void testToStringEmptyList() {
        ProgrammeList emptyProgrammeList = new ProgrammeList();

        String programmeListString = emptyProgrammeList.toString();
        String expectedString = "No programmes found.";

        assertEquals(expectedString, programmeListString);
    }
}
