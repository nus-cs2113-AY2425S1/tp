package seedu.duke;

import org.junit.jupiter.api.Test;
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
}
