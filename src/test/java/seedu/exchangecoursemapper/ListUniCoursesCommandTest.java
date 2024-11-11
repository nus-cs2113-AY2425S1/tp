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
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import seedu.exchangecoursemapper.command.ListUniCoursesCommand;
import seedu.exchangecoursemapper.exception.UnknownUniversityException;

public class ListUniCoursesCommandTest {

    private ListUniCoursesCommand listUniCoursesCommand;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp () {
        outputStreamCaptor.reset();
        System.setOut(new PrintStream(outputStreamCaptor));
        System.setErr(new PrintStream(outputStreamCaptor));

        Logger logger = Logger.getLogger(ListUniCoursesCommand.class.getName());
        logger.setLevel(Level.OFF);

        listUniCoursesCommand = new ListUniCoursesCommand();
    }

    @Test
    public void getPuName_withValidInput_success() {
        String userInput = "list courses the university of western australia";
        String puName = listUniCoursesCommand.getPuName(userInput);
        assertEquals("the university of western australia", puName);
    }

    @Test
    public void getPuName_withNullUni_throwsException() {
        String userInput = "list courses";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> listUniCoursesCommand.getPuName(userInput));

        assertEquals("Please provide a University name.", exception.getMessage());
    }

    @Test
    public void getUniCourses_withValidUni_success() throws IOException, UnknownUniversityException {
        JsonReader jsonReader = Json.createReader(new FileReader("./data/database.json"));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();

        String uniName = "Victoria University of Wellington";
        listUniCoursesCommand.getUniCourses(jsonObject, uniName);

        String expectedOutput = """
                COMP102: Introduction to Computer Program Design
                CS1010J: Programming Methodology
                -----------------------------------------------------
                COMP103: Introduction to Data Structures and Algorithms
                CS2040: Data Structures and Algorithms
                -----------------------------------------------------
                INFO151: Databases
                CS2102: Database Systems
                -----------------------------------------------------
                SWEN221: Software Development
                CS2103: Software Engineering
                -----------------------------------------------------
                NWEN301: Operating Systems Design
                CS2106: Introduction to Operating Systems
                -----------------------------------------------------
                NWEN405: Security Engineering
                CS2107: Introduction to Information Security
                -----------------------------------------------------
                NWEN303: Concurrent Programming
                CS3211: Parallel and Concurrent Programming
                -----------------------------------------------------
                COMP361: Design and Analysis of Algorithms
                CS3230: Design and Analysis of Algorithms
                -----------------------------------------------------
                MATH324: Coding and Cryptography
                CS3236: Introduction to Information Theory
                -----------------------------------------------------
                SWEN303: User Interface Design
                CS3240: Interaction Design
                -----------------------------------------------------
                MDDN241: 3D Modelling and Animation II
                CS3242: 3D Modeling and Animation
                -----------------------------------------------------
                SWEN421: Formal Software Engineering
                CS4211: Formal Methods for Software Engineering
                -----------------------------------------------------
                CYBR371: System and Network Security
                CS5231: Systems Security
                -----------------------------------------------------
                This is the end of the list.
                """;

        String actualOutput = outputStreamCaptor.toString();
        assertEquals(normalizeLineEndings(expectedOutput), normalizeLineEndings(actualOutput));
    }

    @Test
    public void getUniCourses_withInvalidUni_throwsException() throws IOException {
        JsonReader jsonReader = Json.createReader(new FileReader("./data/database.json"));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();

        String invalidUniName = "Invalid Uni";

        UnknownUniversityException exception = assertThrows(UnknownUniversityException.class,
                () -> listUniCoursesCommand.getUniCourses(jsonObject, invalidUniName));


        assertEquals("Unknown university: Invalid Uni", exception.getMessage());
    }

    @Test
    public void execute_validInput_success() {
        String userInput = "list courses Victoria University of Wellington";
        listUniCoursesCommand.execute(userInput);

        String expectedOutput = """
                COMP102: Introduction to Computer Program Design
                CS1010J: Programming Methodology
                -----------------------------------------------------
                COMP103: Introduction to Data Structures and Algorithms
                CS2040: Data Structures and Algorithms
                -----------------------------------------------------
                INFO151: Databases
                CS2102: Database Systems
                -----------------------------------------------------
                SWEN221: Software Development
                CS2103: Software Engineering
                -----------------------------------------------------
                NWEN301: Operating Systems Design
                CS2106: Introduction to Operating Systems
                -----------------------------------------------------
                NWEN405: Security Engineering
                CS2107: Introduction to Information Security
                -----------------------------------------------------
                NWEN303: Concurrent Programming
                CS3211: Parallel and Concurrent Programming
                -----------------------------------------------------
                COMP361: Design and Analysis of Algorithms
                CS3230: Design and Analysis of Algorithms
                -----------------------------------------------------
                MATH324: Coding and Cryptography
                CS3236: Introduction to Information Theory
                -----------------------------------------------------
                SWEN303: User Interface Design
                CS3240: Interaction Design
                -----------------------------------------------------
                MDDN241: 3D Modelling and Animation II
                CS3242: 3D Modeling and Animation
                -----------------------------------------------------
                SWEN421: Formal Software Engineering
                CS4211: Formal Methods for Software Engineering
                -----------------------------------------------------
                CYBR371: System and Network Security
                CS5231: Systems Security
                -----------------------------------------------------
                This is the end of the list.
                """;

        String actualOutput = outputStreamCaptor.toString();
        assertEquals(normalizeLineEndings(expectedOutput), normalizeLineEndings(actualOutput));
    }

    @Test
    public void execute_invalidUni_displayError() {
        String userInput = "list courses Invalid Uni";
        listUniCoursesCommand.execute(userInput);

        String expectedOutput = """
            Unknown university: Invalid Uni
            -----------------------------------------------------
            """;

        String actualOutput = outputStreamCaptor.toString();
        assertEquals(normalizeLineEndings(expectedOutput), normalizeLineEndings(actualOutput));
    }

    @Test
    public void execute_emptyUni_displayError() {
        String userInput = "list courses";
        listUniCoursesCommand.execute(userInput);

        String expectedOutput = """
                 Please provide a University name.
                 -----------------------------------------------------
                 """;
        String actualOutput = outputStreamCaptor.toString();
        assertEquals(normalizeLineEndings(expectedOutput), normalizeLineEndings(actualOutput));
    }

    String normalizeLineEndings(String input) {
        return input.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n").trim();
    }
}
