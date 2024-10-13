package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.exceptions.InvalidIndex;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@ Ridiculouswifi
class InternshipListTest {

    //@@ Ridiculouswifi
    @Test
    void updateStatus_validIndexStatus_expectUpdated() throws InvalidIndex {
        InternshipList internshipList = new InternshipList();
        Internship internship = new Internship("Data", "ABC", "01/24", "06/24");
        internshipList.addInternship(internship);
        internshipList.updateField(0, "status", "Application Completed");

        assertEquals("Application Completed", internshipList.getInternship(0).getStatus());
    }

    //@@Ridiculouswifi
    @Test
    void updateStatus_outOfBoundsIndex_expectThrow() {
        InternshipList internshipList = new InternshipList();
        Internship internship = new Internship("Data", "ABC", "01/24", "06/24");
        internshipList.addInternship(internship);

        assertThrows(InvalidIndex.class,
                () -> internshipList.updateField(1, "status", "Application Completed"));
    }
}
