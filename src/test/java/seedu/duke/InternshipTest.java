package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.exceptions.InvalidStatus;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.*;

class InternshipTest {

    @Test
    void updateStatus_validStatus1_expectNormal() {
        Internship internship = new Internship("Data", "ABC", "01/24", "06/24");
        internship.updateStatus("application completed");

        assertEquals("Application Completed", internship.getStatus());
    }

    @Test
    void updateStatus_validStatus2_expectNormal() {
        Internship internship = new Internship("Data", "ABC", "01/24", "06/24");
        internship.updateStatus("accepted");

        assertEquals("Accepted", internship.getStatus());
    }

    @Test
    void updateStatus_invalidStatus_expectException() {
        Internship internship = new Internship("Data", "ABC", "01/24", "06/24");
        internship.updateStatus("interview pending");

        assertEquals("Application Pending", internship.getStatus());
    }
}