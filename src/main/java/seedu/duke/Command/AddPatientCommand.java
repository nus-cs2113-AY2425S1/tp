package seedu.duke.Command;

import seedu.duke.data.hospital.Patient;

import java.util.List;

public class AddPatientCommand {
    private Patient patient;

    public AddPatientCommand(String name, List<Patient> patients) {
        this.patient = new Patient(name, patients.size() + 1);
        patients.add(this.patient);
        System.out.println("____________________________________________________________");
        System.out.println("added Patient: " + name);
    }
}
