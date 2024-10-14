package seedu.duke;

import seedu.duke.data.hospital.Hospital;
import seedu.duke.data.hospital.Patient;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class PatientTest {

    @Test
    public void testAddPatient() throws Hospital.PatientNotFoundException {
        Hospital hospital = new Hospital();
        hospital.addPatient("Alice");
        assertEquals(1, hospital.getSize());
        assertEquals("Alice", hospital.getPatient(0).getName());
    }

    @Test
    public void testDeletePatient() {
        Hospital hospital = new Hospital();
        hospital.addPatient("Alice");
        hospital.addPatient("Bob");
        try {
            hospital.deletePatient(0);
            assertEquals(1, hospital.getSize());
            assertEquals("Bob", hospital.getPatient(0).getName());
        } catch (Hospital.PatientNotFoundException e) {
            fail("PatientNotFoundException should not be thrown");
        }
    }

    @Test
    public void testDeletePatientNotFound() {
        Hospital hospital = new Hospital();
        Exception exception = assertThrows(Hospital.PatientNotFoundException.class, () -> {
            hospital.deletePatient(0);
        });
        assertEquals("Patient not found in the list!", exception.getMessage());
    }

    @Test
    public void testGetPatient() {
        Hospital hospital = new Hospital();
        hospital.addPatient("Alice");
        try {
            Patient patient = hospital.getPatient(0);
            assertEquals("Alice", patient.getName());
        } catch (Hospital.PatientNotFoundException e) {
            fail("PatientNotFoundException should not be thrown");
        }
    }

    @Test
    public void testGetPatientNotFound() {
        Hospital hospital = new Hospital();
        Exception exception = assertThrows(Hospital.PatientNotFoundException.class, () -> {
            hospital.getPatient(0);
        });
        assertEquals("Patient not found in the list!", exception.getMessage());
    }

    @Test
    public void testSetPatientName() {
        Patient patient = new Patient("Alice", 1);
        patient.setName("Bob");
        assertEquals("Bob", patient.getName());
    }
}
