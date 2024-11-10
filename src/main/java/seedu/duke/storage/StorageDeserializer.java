package seedu.duke.storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import seedu.duke.data.hospital.Patient;

/*
 * Storage deserializer for the storage of patients.
 */
public class StorageDeserializer extends JsonDeserializer<List<Patient>> {

    /**
     * Deserializes the JSON node into a list of patients.
     * The deserializer will check for duplicate names and only add patients with
     * unique names and recalculate the completion rate.
     *
     * @param p    the JSON parser
     * @param ctxt the deserialization context
     * @return the list of patients
     * @throws IOException             if there is an error reading the JSON node
     * @throws JsonProcessingException if there is an error processing the JSON node
     */
    @Override
    public List<Patient> deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = mapper.readTree(p);

        // Using a set to prevent duplicates names
        Set<String> patientNames = new HashSet<>();

        // Place holder for the list of patients
        List<Patient> patients = new ArrayList<>();

        for (JsonNode patientNode : node) {
            Patient patient = mapper.treeToValue(patientNode, Patient.class);
            if (patientNames.add(patient.getName())) { // Add the patient if the name is unique
                patients.add(patient);
            }

            // Recalculate completion rate for each patient
            double completionRate = patient.getTaskList().calCompletionRate();
            patient.getTaskList().setCompletionRate(completionRate);
        }

        return patients;
    }
}
