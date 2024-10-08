package seedu.duke.Command;

import seedu.duke.data.hospital.Patient;

import java.util.List;

public class ListPatientCommand {
    private List<Patient> patients;

    public ListPatientCommand(List<Patient> patients) {
        this.patients = patients;
    }

    public void execute() {
        for (int i = 0; i < patients.size(); i++) {
            System.out.println((i + 1) + ". " + patients.get(i).getName());
        }
    }
}