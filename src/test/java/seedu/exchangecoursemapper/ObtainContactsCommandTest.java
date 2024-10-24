package seedu.exchangecoursemapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
            JsonReader jsonReader = Json.createReader(new FileReader("./data/fake.json")); // This should throw FileNotFoundException
            jsonReader.close();
        } catch (FileNotFoundException e) {
            System.err.println(Exception.fileReadError());
        }
        String actualOutput = outputStreamCaptor.toString().trim();
        assertEquals(expectedOutput, actualOutput);
    }
}
