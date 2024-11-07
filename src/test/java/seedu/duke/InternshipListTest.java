package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.exceptions.InvalidDeadline;
import seedu.exceptions.InvalidIndex;
import seedu.exceptions.InvalidStatus;
import seedu.exceptions.MissingValue;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author Ridiculouswifi
class InternshipListTest {

    InternshipList internshipList;

    void initialiseInternshipList() {
        internshipList = new InternshipList();
        Internship internship = new Internship("Data", "ABC", "01/24", "06/24");
        internshipList.addInternship(internship);
    }

    //@@author Ridiculouswifi
    @Test
    void updateField_validIndexStatus_expectUpdated() throws InvalidIndex, InvalidStatus, InvalidDeadline {
        initialiseInternshipList();
        internshipList.updateField(0, "status", "Application Completed");

        assertEquals("Application Completed", internshipList.getInternship(0).getStatus());
    }

    //@@author Ridiculouswifi
    @Test
    void updateField_invalidStartDate_expectThrow() {
        initialiseInternshipList();

        assertThrows(DateTimeParseException.class,
                () -> internshipList.updateField(0, "from", "20/20"));
    }

    //@@author Ridiculouswifi
    @Test
    void updateField_validDeadline_expectUpdated() throws InvalidDeadline, InvalidStatus, InvalidIndex {
        initialiseInternshipList();
        internshipList.updateField(0, "deadline", "Interview 11/11/25");

        assertEquals("Interview",
                internshipList.getInternship(0).getDeadlines().get(0).getDescription());
        assertEquals("11/11/25", internshipList.getInternship(0).getDeadlines().get(0).getDate());
    }

    //@@author Ridiculouswifi
    @Test
    void removeField_validSkill_expectUpdated() throws InvalidIndex, InvalidStatus, MissingValue, InvalidDeadline {
        initialiseInternshipList();
        internshipList.updateField(0, "skills", "Java, Python");

        internshipList.removeField(0, "skills", "Java");

        assertEquals("Python", internshipList.getInternship(0).getSkills());
    }

    //@@author Ridiculouswifi
    @Test
    void removeField_invalidSkill_expectThrow() throws InvalidIndex, InvalidStatus, InvalidDeadline {
        initialiseInternshipList();
        internshipList.updateField(0, "skills", "Java, Python");

        assertThrows(MissingValue.class, ()->internshipList.removeField(0, "skills", "C++"));
    }

    //@@author Ridiculouswifi
    @Test
    void removeField_invalidField_expectAssertion() {
        initialiseInternshipList();

        assertThrows(AssertionError.class, ()->internshipList.removeField(0, "status", "Java"));
    }

    //@@author Ridiculouswifi
    @Test
    void removeField_validDeadline_expectUpdated() throws InvalidIndex, InvalidStatus, MissingValue, InvalidDeadline {
        initialiseInternshipList();
        internshipList.updateField(0, "deadline", "Interview Deadline 11/11/25");

        internshipList.removeField(0, "deadline", "    Interview Deadline   ");

        assertThrows(IndexOutOfBoundsException.class,
                () -> internshipList.getInternship(0).getDeadlines().get(0));
    }
}
