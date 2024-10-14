package seedu.duke.storage;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.data.hospital.Hospital;
import seedu.duke.data.hospital.Hospital.PatientNotFoundException;
import seedu.duke.data.task.TaskList.TaskNotFoundException;

public class StorageFileTest {

    String hospitalJson;
    String filePath = "src/test/java/seedu/duke/data/hospital_data_load.json";
    private Hospital hospital = new Hospital();

    @BeforeEach
    public void setUp() {
        hospital.addPatient("Alice");
        hospital.addPatient("Bob");

        hospitalJson = """
                {
                  "patients" : [ {
                    "name" : "Alice",
                    "taskList" : {
                      "tasks" : [ {
                        "description" : "Consultation",
                        "isDone" : true
                      }, {
                        "description" : "Medication",
                        "isDone" : false
                      } ]
                    }
                  }, {
                    "name" : "Bob",
                    "taskList" : {
                      "tasks" : [ {
                        "description" : "Surgery",
                        "isDone" : false
                      }, {
                        "description" : "Medication",
                        "isDone" : false
                      } ]
                    }
                  } ]
                }
                    """;
    }

    @Test
    public void loadFromFile_success() throws TaskNotFoundException, PatientNotFoundException {
        Hospital loadHospital = JsonUtil.loadFromFile(filePath);

        assertEquals("Alice", loadHospital.getPatient(0).getName());
        assertEquals("Bob", loadHospital.getPatient(1).getName());
        assertEquals("Consultation",
                loadHospital.getPatient(0).getTaskList().getTask(0).getDescription());
        assertEquals("Medication",
                loadHospital.getPatient(0).getTaskList().getTask(1).getDescription());
        assertTrue(loadHospital.getPatient(0).getTaskList().getTask(0).isDone());
    }

    @Test
    public void saveToFile_success() {
        String pathToSave = "src/test/java/seedu/duke/data/hospital_data_save.json";
        File file = new File(pathToSave);
        // check if file exists
        if (file.exists()) {
            file.delete();
        }

        JsonUtil.saveToFile(hospital, pathToSave);
        assertTrue(file.exists());
    }
}
