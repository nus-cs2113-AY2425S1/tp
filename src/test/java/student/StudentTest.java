package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentTest {
    private Student student;

    @BeforeEach
    public void setUp(){
        student = new Student("A0271560J",5.0f,new ArrayList<>(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void testConstruction() {
        // Test constructor with valid inputs
        assertNotNull(student);
        assertEquals("A0271560J", student.getId());
        assertEquals(5.0f, student.getGpa());
        assertEquals(Arrays.asList(1, 2, 3), student.getUniPreferences());
    }

    @Test
    public void testCopyConstructor() {
        // Create a copy of the original student
        Student copiedStudent = new Student(student);

        // Test if the copy has the same data but is a different object
        assertNotNull(copiedStudent);
        assertEquals(student.getId(), copiedStudent.getId());
        assertEquals(student.getGpa(), copiedStudent.getGpa());
        assertEquals(student.getUniPreferences(), copiedStudent.getUniPreferences());
        assertNotSame(student.getUniPreferences(), copiedStudent.getUniPreferences()); // Deep copy check
    }

    @Test
    public void testSetSuccessfullyAllocated() {
        // Test updating allocation status
        student.setSuccessfullyAllocated(true);
        assertTrue(student.getSuccessfullyAllocated());

        student.setSuccessfullyAllocated(false);
        assertFalse(student.getSuccessfullyAllocated());
    }

    @Test
    public void testSetAllocatedUniversity() {
        // Test setting allocated university
        student.setAllocatedUniversity(5);
        assertEquals(5, student.getAllocatedUniversity());
    }
}
