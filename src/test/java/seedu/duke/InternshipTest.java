package seedu.duke;

import org.junit.jupiter.api.Test;

import java.time.DateTimeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@ Ridiculouswifi
class InternshipTest {

    Internship internship = new Internship("Data", "ABC", "01/24", "06/24");

    //@@ Ridiculouswifi
    @Test
    void updateStatus_validStatus1_expectNormal() {
        internship.updateStatus("application completed");

        assertEquals("Application Completed", internship.getStatus());
    }

    //@@ Ridiculouswifi
    @Test
    void updateStatus_validStatus2_expectNormal() {
        internship.updateStatus("accepted");

        assertEquals("Accepted", internship.getStatus());
    }

    //@@ Ridiculouswifi
    @Test
    void updateStatus_invalidStatus_expectException() {
        internship.updateStatus("interview pending");

        assertEquals("Application Pending", internship.getStatus());
    }

    //@@ Ridiculouswifi
    @Test
    void setStartDate_validStartDate_expectNormal() {
        internship.setStartDate("01/23");

        assertEquals("01/23", internship.getStartDate());
    }

    //@@ Ridiculouswifi
    @Test
    void setStartDate_invalidFormat_expectDateTimeException() {
        assertThrows(DateTimeException.class, () -> internship.setStartDate("01/02/2024"));
    }

    //@@ Ridiculouswifi
    @Test
    void setStartDate_invalidDate_expectDateTimeException() {
        assertThrows(DateTimeException.class, () -> internship.setStartDate("20/10"));
    }
}
