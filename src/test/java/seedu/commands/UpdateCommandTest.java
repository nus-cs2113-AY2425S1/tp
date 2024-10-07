package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.Internship;
import seedu.duke.InternshipList;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateCommandTest {

    @Test
    void execute_validInput1_expectUpdated() {
        InternshipList internshipList = new InternshipList();
        Internship internship = new Internship("Data", "ABC", "01/24", "06/24");
        internshipList.addInternship(internship);

        assertEquals(internship.getCompany(), "ABC");

        UpdateCommand updateCommand = new UpdateCommand(internshipList);
        String input = "update -id 0 -company XYZ";
        String[] words = input.split("-");
        updateCommand.execute(Arrays.copyOfRange(words, 1, words.length));

        assertEquals(internship.getCompany(), "XYZ");
    }
}
