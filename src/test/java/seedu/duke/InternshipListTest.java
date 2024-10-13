package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.exceptions.InvalidIndex;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@ Ridiculouswifi
class InternshipListTest {

    //@@ Ridiculouswifi
    @Test
    void updateField_validIndexStatus_expectUpdated() throws InvalidIndex {
        InternshipList internshipList = new InternshipList();
        Internship internship = new Internship("Data", "ABC", "01/24", "06/24");
        internshipList.addInternship(internship);
        internshipList.updateField(0, "status", "Application Completed");

        assertEquals("Application Completed", internshipList.getInternship(0).getStatus());
    }

    //@@Ridiculouswifi
    @Test
    void updateField_outOfBoundsIndex_expectThrow() {
        InternshipList internshipList = new InternshipList();
        Internship internship = new Internship("Data", "ABC", "01/24", "06/24");
        internshipList.addInternship(internship);

        assertThrows(InvalidIndex.class,
                () -> internshipList.updateField(1, "status", "Application Completed"));
    }

    @Test
    void updateField_invalidStartDate_expectThrow() {
        InternshipList internshipList = new InternshipList();
        Internship internship = new Internship("Data", "ABC", "01/24", "06/24");
        internshipList.addInternship(internship);

        assertThrows(DateTimeParseException.class,
                () -> internshipList.updateField(0, "from", "20/20"));
    }
}
