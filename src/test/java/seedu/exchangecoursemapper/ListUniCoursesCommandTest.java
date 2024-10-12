package seedu.exchangecoursemapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.FileReader;
import java.io.IOException;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.exchangecoursemapper.command.ListUniCoursesCommand;

public class ListUniCoursesCommandTest {

    private ListUniCoursesCommand listUniCoursesCommand;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp () {
        System.setOut(new PrintStream(outputStreamCaptor));
        listUniCoursesCommand = new ListUniCoursesCommand();
    }

    @Test
    public void getPuName_withValidInput_success() {
        String userInput = "set the university of western australia";
        String puName = listUniCoursesCommand.getPuName(userInput);
        assertEquals("the university of western australia", puName);
    }

    @Test
    public void getPuName_withInvalidInput_success() {
        String userInput = "set";
        String getPuNameOutput = listUniCoursesCommand.getPuName(userInput);
        assertEquals("", getPuNameOutput);
    }

    @Test
    public void getUniCourses_withValidUni_success() throws IOException {
        JsonReader jsonReader = Json.createReader(new FileReader("./data/database.json"));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();

        String uniName = "Chulalongkorn University";
        listUniCoursesCommand.getUniCourses(jsonObject, uniName);

        String expectedOutput = """
                ICE2190472: Netcentric Architecture
                CS2105: Introduction to Computer Networks
                -----------------------------------------------------
                ICSE2190479: Graphics Computing
                CS3241: Computer Graphics
                -----------------------------------------------------
                2603637: Machine Learning
                CS3244: Machine Learning
                -----------------------------------------------------
                """;

        String actualOutput = outputStreamCaptor.toString();
        assertEquals(normalizeLineEndings(expectedOutput), normalizeLineEndings(actualOutput));
    }

    @Test
    public void getUniCourses_withInvalidUni_success() throws IOException {
        JsonReader jsonReader = Json.createReader(new FileReader("./data/database.json"));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();

        String invalidUniName = "Invalid Uni";

        listUniCoursesCommand.getUniCourses(jsonObject, invalidUniName);

        String expectedOutput = """
                University not found: Invalid Uni
                -----------------------------------------------------
                """;

        String actualOutput = outputStreamCaptor.toString();
        assertEquals(normalizeLineEndings(expectedOutput), normalizeLineEndings(actualOutput));
    }

    @Test
    public void execute_validInput_success() throws IOException {
        String puName = "Chulalongkorn University";
        listUniCoursesCommand.execute(puName);

        String expectedOutput = """
                ICE2190472: Netcentric Architecture
                CS2105: Introduction to Computer Networks
                -----------------------------------------------------
                ICSE2190479: Graphics Computing
                CS3241: Computer Graphics
                -----------------------------------------------------
                2603637: Machine Learning
                CS3244: Machine Learning
                -----------------------------------------------------
                """;

        String actualOutput = outputStreamCaptor.toString();
        assertEquals(normalizeLineEndings(expectedOutput), normalizeLineEndings(actualOutput));
    }

    @Test
    public void execute_invalidUni_success() throws IOException {
        String puName = "Invalid Uni";
        listUniCoursesCommand.execute(puName);

        String expectedOutput = """
                University not found: Invalid Uni
                -----------------------------------------------------
                """;

        String actualOutput = outputStreamCaptor.toString();
        assertEquals(normalizeLineEndings(expectedOutput), normalizeLineEndings(actualOutput));
    }

    String normalizeLineEndings(String input) {
        return input.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n").trim();
    }
}
