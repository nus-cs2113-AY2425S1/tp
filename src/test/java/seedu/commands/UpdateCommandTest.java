package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.Internship;
import seedu.duke.InternshipList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateCommandTest {

    @Test
    void execute_validCompany1_expectUpdated() {
        InternshipList internshipList = new InternshipList();
        Internship internship = new Internship("Data", "ABC", "01/24", "06/24");
        internshipList.addInternship(internship);

        assertEquals(internship.getCompany(), "ABC");

        UpdateCommand updateCommand = new UpdateCommand();
        updateCommand.setInternshipList(internshipList);

        String[] parsedInputs = {"01", "company XYZ"};
        ArrayList<String> arguments = new ArrayList<>(List.of(parsedInputs));

        updateCommand.execute(arguments);

        assertEquals(internship.getCompany(), "XYZ");
    }

    @Test
    void execute_validStatus1_expectUpdated() {
        InternshipList internshipList = new InternshipList();
        Internship internship = new Internship("Data", "ABC", "01/24", "06/24");
        internshipList.addInternship(internship);

        assertEquals(internship.getStatus(), "Application Pending");

        UpdateCommand updateCommand = new UpdateCommand();
        updateCommand.setInternshipList(internshipList);

        String[] parsedInputs = {"01", "status application completed"};
        ArrayList<String> arguments = new ArrayList<>(List.of(parsedInputs));

        updateCommand.execute(arguments);

        assertEquals(internship.getStatus(), "Application Completed");
    }
}
