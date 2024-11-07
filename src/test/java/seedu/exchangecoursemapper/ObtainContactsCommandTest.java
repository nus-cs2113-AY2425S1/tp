package seedu.exchangecoursemapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.FileNotFoundException;

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
        JsonObject jsonObject = getJsonObject();

        String userInput = "obtain The University of Melbourne /email";
        obtainContactsCommand.execute(userInput);

        String actualOutput = outputStreamCaptor.toString().trim();
        String[] outputLines = actualOutput.split("\n");
        String expectedOutput = "Email for The University of Melbourne: unimelb-support@unimelb.edu.au\n";

        boolean isFound = false;
        for (String line : outputLines) {
            if (line.trim().equals(expectedOutput.trim())) {
                isFound = true;
                break;
            }
        }
        assertEquals(true, isFound);
    }

    @Test
    public void execute_validNumber_success() throws IOException {
        JsonObject jsonObject = getJsonObject();

        String userInput = "obtain The University of Melbourne /number";
        obtainContactsCommand.execute(userInput);

        String actualOutput = outputStreamCaptor.toString().trim();
        String expectedPhoneNumber = "Phone number for The University of Melbourne: +61 3 9035 5511";
        String[] outputLines = actualOutput.split("\n");

        boolean isPhoneNumberFound = false;
        for (String line : outputLines) {
            if (line.contains(expectedPhoneNumber)) {
                isPhoneNumberFound = true;
                break;
            }
        }
        assertEquals(true, isPhoneNumberFound);
    }

    @Test
    public void execute_invalidUniversity_displaysError() throws IOException {
        JsonObject jsonObject = getJsonObject();

        String userInput = "NUS";
        String name = obtainContactsCommand.getSchoolName(userInput);
        obtainContactsCommand.findMatchingSchool(jsonObject, name);

        String expectedOutput = "Unknown university - NUS";
        String actualOutput = outputStreamCaptor.toString().trim();
        assertTrue(actualOutput.contains(expectedOutput), "Expected output not found in actual output.");
    }

    @Test
    public void execute_invalidInputFormat_throwsException() throws FileNotFoundException {
        JsonObject jsonObject = getJsonObject();

        String userInput = " ";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            obtainContactsCommand.getContactType(userInput);
        });

        assertEquals("Invalid input format", exception.getMessage());
    }

    @Test
    public void execute_invalidContactType_displaysError() throws IOException {
        JsonObject jsonReader = getJsonObject();

        String userInput = "obtain The University of Melbourne /fax";
        obtainContactsCommand.execute(userInput);

        String actualOutput = outputStreamCaptor.toString().trim();
        String expectedOutput = Exception.invalidContactType();

        assertTrue(actualOutput.contains(expectedOutput), "Does not match the actual output.");
    }

    @Test
    public void inputWithOneWord_expectException() {
        String userInput = "obtain";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        obtainContactsCommand.execute(userInput);

        String expectedMessage = "Invalid input format";
        String actualOutput = outContent.toString().trim();

        assertTrue(actualOutput.contains(expectedMessage), "Expected " + expectedMessage);
    }

    private static JsonObject getJsonObject() throws FileNotFoundException {
        JsonReader jsonReader = Json.createReader(new FileReader("./data/database.json"));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();
        return jsonObject;
    }
}
