package seedu.duke.Command;

import seedu.duke.data.hospital.Patient;

import java.util.List;

public class DelPatientCommand {
    private String patientName;

    public DelPatientCommand(int index, List<Patient> patients) {
        if (index < 0 || index >= patients.size()) {
            throw new IndexOutOfBoundsException("Patient index out of range.");
        }
        this.patientName = patients.get(index).getName();
        patients.remove(index);
        System.out.println("____________________________________________________________");
        System.out.println("deleted Patient: " + patientName);
    }
}
