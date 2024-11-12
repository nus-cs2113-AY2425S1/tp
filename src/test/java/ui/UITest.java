package ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import exceptions.SEPEmptyException;
import student.Student;

public class UITest {
    private UI ui;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        // Redirect system output to capture it for tests
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Initialize UI with default constructor
        ui = new UI();
    }

    @Test
    void printResponse() {
        String message = "Hello, World!";
        ui.printResponse(message);
        String expected = "-".repeat(80) + "\n" + message + "\n" + "-".repeat(80) + "\n";
        assertEquals(expected.trim(), outContent.toString().trim());
    }

    @Test
    void printResponse_emptyInput_null() {
        ui.printResponse(null);
        String expected = "-".repeat(80) + "\nnull\n" + "-".repeat(80) + "\n";
        assertEquals(expected.trim(), outContent.toString().trim());
    }

    @Test
    void getUserInput_validInput() {
        // Simulate user input
        String simulatedInput = "test input\n";
        ByteArrayInputStream inContent = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inContent);

        UI testUi = new UI();
        assertEquals("test input", testUi.getUserInput());
    }

    @Test
    void getUserInput_emptyInput_error() {
        // Simulate erroneous input
        ByteArrayInputStream inContent = new ByteArrayInputStream(new byte[0]);
        System.setIn(inContent);

        assertEquals("", ui.getUserInput());
        assertTrue(outContent.toString().contains("Error reading input"));
    }

    @Test
    void sayHi() {
        ui.sayHi();
        assertTrue(outContent.toString().contains(Messages.WELCOME.toString()));
    }

    @Test
    void cleanupAndExit() {
        ui.cleanupAndExit();
        assertTrue(outContent.toString().contains("Do you want to save your results?"));
    }

    @Test
    void printStudentList_emptyList_throwEmpty() {
        ArrayList<Student> studentList = new ArrayList<>();
        assertThrows(SEPEmptyException.class, () -> ui.printStudentList(studentList));
    }

    @Test
    void generateReport_emptyList_throwEmpty() {
        ArrayList<Student> studentList = new ArrayList<>();
        assertThrows(SEPEmptyException.class, () -> ui.generateReport(studentList));
    }

    @Test
    void printAllocatingMessage() throws InterruptedException {
        Thread allocationThread = new Thread(() -> ui.printAllocatingMessage());
        allocationThread.start();

        Thread.sleep(2000); // Let the loading message print for a while
        allocationThread.interrupt(); // Interrupt the thread to stop the loading

        allocationThread.join(); // Ensure the thread has finished

        assertTrue(outContent.toString().contains(Messages.ALLOCATE_COMPLETE.toString()));
    }
}
