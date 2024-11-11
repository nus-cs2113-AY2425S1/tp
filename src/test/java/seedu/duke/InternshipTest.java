package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.exceptions.InvalidDeadline;
import seedu.exceptions.InvalidStatus;
import seedu.exceptions.MissingValue;

import java.time.DateTimeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author Ridiculouswifi
class InternshipTest {

    Internship internship = new Internship("Data", "ABC", "01/24", "06/24");

    //@@author Ridiculouswifi
    @Test
    void updateStatus_validStatus1_expectNormal() throws InvalidStatus {
        internship.updateStatus("application completed");

        assertEquals("Application Completed", internship.getStatus());
    }

    //@@author Ridiculouswifi
    @Test
    void updateStatus_validStatus2_expectNormal() throws InvalidStatus {
        internship.updateStatus("accepted");

        assertEquals("Accepted", internship.getStatus());
    }

    //@@author Ridiculouswifi
    @Test
    void updateStatus_invalidStatus_expectException() throws InvalidStatus {
        assertThrows(InvalidStatus.class, () -> internship.updateStatus("interview pending"));
    }

    //@@author Ridiculouswifi
    @Test
    void setStartDate_validStartDate_expectNormal() {
        internship.setStartDate("01/23");

        assertEquals("01/23", internship.getStartDate());
    }

    //@@author Ridiculouswifi
    @Test
    void setStartDate_invalidFormat_expectDateTimeException() {
        assertThrows(DateTimeException.class, () -> internship.setStartDate("01/02/2024"));
    }

    //@@author Ridiculouswifi
    @Test
    void setStartDate_invalidDate_expectDateTimeException() {
        assertThrows(DateTimeException.class, () -> internship.setStartDate("20/10"));
    }

    //@@author Ridiculouswifi
    @Test
    void setSkills_oneValidSkill_expectUpdated() {
        assertEquals("", internship.getSkills());

        internship.setSkills("Java");

        assertEquals("Java", internship.getSkills());
    }

    //@@author Ridiculouswifi
    @Test
    void setSkills_twoValidSkill_expectUpdated() {
        internship.setSkills("Java,   Python");

        assertEquals("Java, Python", internship.getSkills());
    }

    //@@author Ridiculouswifi
    @Test
    void setSkills_emptyInput_expectNoUpdate() {
        internship.setSkills("     ");

        assertEquals("", internship.getSkills());
    }

    //@@author Ridiculouswifi
    @Test
    void removeSkill_validSkill_expectUpdated() throws MissingValue {
        internship.setSkills("Java, Python");
        internship.removeSkill("Java");

        assertEquals("Python", internship.getSkills());
    }

    //@@author Ridiculouswifi
    @Test
    void removeSkill_skillWithWhiteSpace_expectUpdated() throws MissingValue {
        internship.setSkills("Java, Python");
        internship.removeSkill("     Python     ");

        assertEquals("Java", internship.getSkills());
    }

    //@@author Ridiculouswifi
    @Test
    void removeSkill_invalidSkill_expectException() {
        internship.setSkills("Java, Python");

        assertThrows(MissingValue.class, () -> internship.removeSkill("C++"));
    }

    //@@author Ridiculouswifi
    @Test
    void updateDeadline_validDeadline_expectUpdated() throws InvalidDeadline {
        internship.updateDeadline("Interview Deadline 11/11/25");

        assertEquals("Interview Deadline", internship.getDeadlines().get(0).getDescription());
    }

    //@@author Ridiculouswifi
    @Test
    void updateDeadline_descriptionEmpty_expectException() {
        assertThrows(InvalidDeadline.class, () -> internship.updateDeadline("    11/11/25"));
    }

    //@@author Ridiculouswifi
    @Test
    void updateDeadline_noValidDates_expectException() {
        assertThrows(InvalidDeadline.class, () -> internship.updateDeadline("Interview Deadline 11/11"));
    }

    //@@author Ridiculouswifi
    @Test
    void updateDeadline_sameDescription_expectUpdated() throws InvalidDeadline {
        internship.updateDeadline("Interview Deadline 11/11/25");
        internship.updateDeadline("Interview Deadline 12/12/24");

        assertEquals("12/12/24", internship.getDeadlines().get(0).getDate());
    }

    //@@author Ridiculouswifi
    @Test
    void removeDeadline_validDescription_expectUpdated() throws MissingValue, InvalidDeadline {
        internship.updateDeadline("Interview Deadline 11/11/25");
        internship.updateDeadline("Round 2 12/12/24");

        internship.removeDeadline("Interview Deadline");

        assertEquals("Round 2", internship.getDeadlines().get(0).getDescription());
    }

    //@@author Ridiculouswifi
    @Test
    void removeDeadline_invalidDescription_expectException() throws InvalidDeadline {
        internship.updateDeadline("Interview Deadline 11/11/25");
        internship.updateDeadline("Round 2 12/12/24");

        assertThrows(MissingValue.class, () -> internship.removeDeadline("Round 2 12/12/24"));
    }
}
