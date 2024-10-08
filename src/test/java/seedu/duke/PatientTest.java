package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.Command.AddPatientCommand;
import seedu.duke.Command.DelPatientCommand;
import seedu.duke.Command.ListPatientCommand;
import seedu.duke.Command.SelectPatientCommand;
import seedu.duke.data.hospital.*;

import java.util.ArrayList;
import java.util.List;

class PatientTest {
    private List<Patient> patients;

    @BeforeEach
    public void setUp() {
        patients = new ArrayList<>();
    }

    @Test
    public void testAddPatient() {
        new AddPatientCommand("John", patients);
        assertEquals(1, patients.size());
        assertEquals("John", patients.get(0).getName());
    }

    @Test
    public void testDeletePatient() {
        new AddPatientCommand("John", patients);
        new AddPatientCommand("Jane", patients);
        new DelPatientCommand(0, patients);
        assertEquals(1, patients.size());
        assertEquals("Jane", patients.get(0).getName());
    }

    @Test
    public void testDeletePatientOutOfBounds() {
        new AddPatientCommand("John", patients);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            new DelPatientCommand(5, patients);
        });
    }

    @Test
    public void testSelectPatient() {
        new AddPatientCommand("John", patients);
        new SelectPatientCommand(0, patients);
        assertEquals("John", patients.get(0).getName());
    }

    @Test
    public void testSelectPatientOutOfBounds() {
        new AddPatientCommand("John", patients);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            new SelectPatientCommand(5, patients);
        });
    }

    @Test
    public void testListPatients() {
        new AddPatientCommand("John", patients);
        new AddPatientCommand("Jane", patients);
        ListPatientCommand listCommand = new ListPatientCommand(patients);
        listCommand.execute();
        assertEquals(2, patients.size());
    }
}
