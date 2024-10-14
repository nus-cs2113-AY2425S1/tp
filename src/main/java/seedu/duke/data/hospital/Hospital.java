package seedu.duke.data.hospital;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonPropertyOrder({"patients"})
@JsonRootName("hospital")
// TODO: Add JsonInclude for null values
public class Hospital {
    private List<Patient> patients;

    @JsonCreator
    public Hospital() {
        this.patients = new ArrayList<>();
    }

    public void addPatient(String name) {
        Patient newPatient = new Patient(name);
        patients.add(newPatient);
    }

    public void deletePatient(int index) throws PatientNotFoundException {
        if (!isValidIndex(index)) {
            throw new PatientNotFoundException();
        }
        patients.remove(index);
    }

    public Patient getPatient(int index) throws PatientNotFoundException {
        if (!isValidIndex(index)) {
            throw new PatientNotFoundException();
        }
        return patients.get(index);
    }

    public boolean isValidIndex(int index) {
        return index >= 0 && index < getSize();
    }

    @JsonIgnore
    public int getSize() {
        return patients.size();
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void printList() {
        StringBuilder resultMessage = new StringBuilder("Here are the patients in your list:\n");
        for (int i = 0; i < patients.size(); i++) {
            resultMessage.append((i + 1)).append(". ").append(patients.get(i).getName()).append("\n");
        }
        System.out.println(resultMessage);
    }

    public static class PatientNotFoundException extends Exception {
        public PatientNotFoundException() {
            super("Patient not found in the list!");
        }
    }
}
