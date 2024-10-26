package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.data.hospital.Hospital;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatientCommandTest {
    private Hospital hospital;

    @BeforeEach
    public void setUp() {
        hospital = new Hospital();
    }


    @Test
    public void testFindPatientCommandNoMatch() {
        hospital.addPatient("Alice");

        FindPatientCommand findPatientCommand = new FindPatientCommand("charlie");
        findPatientCommand.setHospital(hospital);
        CommandResult result = findPatientCommand.execute();

        assertEquals("There are no matching patients in your list.", result.getFeedbackToUser());
    }

}
