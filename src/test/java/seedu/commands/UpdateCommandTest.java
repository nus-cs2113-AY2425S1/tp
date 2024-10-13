package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.Internship;
import seedu.duke.InternshipList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UpdateCommandTest {

    InternshipList createList() {
        InternshipList internshipList = new InternshipList();
        Internship internship = new Internship("Data", "ABC", "01/24", "06/24");
        internshipList.addInternship(internship);
        return internshipList;
    }

    @Test
    void execute_validCompany1_expectUpdated() {
        InternshipList internships = createList();

        assertEquals(internships.getInternship(0).getCompany(), "ABC");

        UpdateCommand updateCommand = new UpdateCommand();
        updateCommand.setInternshipList(internships);

        String[] parsedInputs = {"01", "company XYZ"};
        ArrayList<String> arguments = new ArrayList<>(List.of(parsedInputs));

        updateCommand.execute(arguments);

        assertEquals(internships.getInternship(0).getCompany(), "XYZ");
    }

    @Test
    void execute_validStatus1_expectUpdated() {
        InternshipList internships = createList();

        assertEquals(internships.getInternship(0).getStatus(), "Application Pending");

        UpdateCommand updateCommand = new UpdateCommand();
        updateCommand.setInternshipList(internships);

        String[] parsedInputs = {"01", "status application completed"};
        ArrayList<String> arguments = new ArrayList<>(List.of(parsedInputs));

        updateCommand.execute(arguments);

        assertEquals(internships.getInternship(0).getStatus(), "Application Completed");
    }

    @Test
    void execute_invalidIndex1_expectThrow() {
        InternshipList internships = createList();

        assertEquals(internships.getInternship(0).getCompany(), "ABC");

        UpdateCommand updateCommand = new UpdateCommand();
        updateCommand.setInternshipList(internships);

        String[] parsedInputs = {"A1", "company XYZ"};
        ArrayList<String> arguments = new ArrayList<>(List.of(parsedInputs));

        updateCommand.execute(arguments);

        assertEquals(internships.getInternship(0).getCompany(), "ABC");
    }
}
