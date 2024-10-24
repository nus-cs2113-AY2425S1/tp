package seedu.exchangecoursemapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import seedu.exchangecoursemapper.command.ObtainContactsCommand;
import seedu.exchangecoursemapper.exception.Exception;

public class ObtainContactsCommandTest {

    private ObtainContactsCommand obtainContactsCommand;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        outputStreamCaptor.reset();
        System.setOut(new PrintStream(outputStreamCaptor));
        System.setErr(new PrintStream(outputStreamCaptor));
        obtainContactsCommand = new ObtainContactsCommand();
    }

    @Test
    public void fileReadError_failure() {
        String expectedOutput = "Error reading the file.";
        try {
            JsonReader jsonReader = Json.createReader(new FileReader("./data/fake.json"));
            jsonReader.close();
        } catch (FileNotFoundException e) {
            System.err.println(Exception.fileReadError());
        }
        String actualOutput = outputStreamCaptor.toString().trim();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void execute_validEmail_success() throws IOException {
        JsonReader jsonReader = Json.createReader(new FileReader("./data/database.json"));
        jsonReader.close();

        String userInput = "obtain Chulalongkorn University /email";
        obtainContactsCommand.execute(userInput);

        String actualOutput = outputStreamCaptor.toString().trim();
        String[] outputLines = actualOutput.split("\n");
        String expectedOutput = "Email for Chulalongkorn University: int.off@chula.ac.th\n";

        boolean found = false;
        for (String line : outputLines) {
            if (line.trim().equals(expectedOutput.trim())) {
                found = true;
                break;
            }
        }
        assertEquals(true, found);
    }

    @Test
    public void execute_validNumber_success() throws IOException {
        JsonReader jsonReader = Json.createReader(new FileReader("./data/database.json"));
        jsonReader.close();

        String userInput = "obtain Chulalongkorn University /number";
        obtainContactsCommand.execute(userInput);

        String expectedOutput = "Phone number for Chulalongkorn University: +66 2 218 2000\n";
        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void execute_invalidUniversity_displaysError() throws IOException {
        JsonReader jsonReader = Json.createReader(new FileReader("./data/database.json"));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();

        String userInput = "NUS";
        String name = obtainContactsCommand.getSchoolName(userInput);
        obtainContactsCommand.findMatchingSchool(jsonObject, name);

        String expectedOutput = "Unknown university - NUS";
        String actualOutput = outputStreamCaptor.toString().trim();
        assertTrue(actualOutput.contains(expectedOutput), "Expected output not found in actual output.");
    }

    @Test
    public void execute_invalidInputFormat_throwsException() {
        String userInput = " ";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            obtainContactsCommand.getContactType(userInput);
        });

        assertEquals("Invalid input format", exception.getMessage());
    }

    @Test
    public void execute_invalidContactType_displaysError() throws IOException {
        JsonReader jsonReader = Json.createReader(new FileReader("./data/database.json"));
        jsonReader.close();

        String userInput = "obtain Chulalongkorn University /fax";
        obtainContactsCommand.execute(userInput);

        String expectedOutput = Exception.invalidContactType();
        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }
}
