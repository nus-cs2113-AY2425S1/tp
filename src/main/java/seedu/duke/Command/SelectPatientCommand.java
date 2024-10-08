package seedu.duke.Command;

import seedu.duke.data.hospital.Patient;

import java.util.List;

public class SelectPatientCommand {
    private String patientName;

    public SelectPatientCommand(int index, List<Patient> patients) {
        if (index < 0 || index >= patients.size()) {
            throw new IndexOutOfBoundsException("Patient index out of range.");
        }
        this.patientName = patients.get(index).getName();
        System.out.println("____________________________________________________________");
        System.out.println("selected Patient: " + patientName);
    }
}