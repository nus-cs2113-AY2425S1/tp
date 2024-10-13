package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.Internship;
import seedu.duke.InternshipList;
import seedu.exceptions.InvalidIndex;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@ Ridiculouswifi
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

        assertEquals("ABC", internships.getInternship(0).getCompany());

        UpdateCommand updateCommand = new UpdateCommand();
        updateCommand.setInternshipList(internships);

        String[] parsedInputs = {"01", "company XYZ"};
        ArrayList<String> arguments = new ArrayList<>(List.of(parsedInputs));

        updateCommand.execute(arguments);

        assertEquals("XYZ", internships.getInternship(0).getCompany());
    }

    @Test
    void execute_validStatus1_expectUpdated() {
        InternshipList internships = createList();

        assertEquals("Application Pending", internships.getInternship(0).getStatus());

        UpdateCommand updateCommand = new UpdateCommand();
        updateCommand.setInternshipList(internships);

        String[] parsedInputs = {"01", "status application completed"};
        ArrayList<String> arguments = new ArrayList<>(List.of(parsedInputs));

        updateCommand.execute(arguments);

        assertEquals("Application Completed", internships.getInternship(0).getStatus());
    }

    @Test
    void execute_nonNumberId_expectNoUpdate() {
        InternshipList internships = createList();

        assertEquals("ABC", internships.getInternship(0).getCompany());

        UpdateCommand updateCommand = new UpdateCommand();
        updateCommand.setInternshipList(internships);

        String[] parsedInputs = {"A1", "company XYZ"};
        ArrayList<String> arguments = new ArrayList<>(List.of(parsedInputs));

        updateCommand.execute(arguments);

        assertEquals("ABC", internships.getInternship(0).getCompany());
    }

    @Test
    void execute_emptyId_expectNoUpdate() {
        InternshipList internships = createList();

        assertEquals("ABC", internships.getInternship(0).getCompany());

        UpdateCommand updateCommand = new UpdateCommand();
        updateCommand.setInternshipList(internships);

        String[] parsedInputs = {"", "company XYZ"};
        ArrayList<String> arguments = new ArrayList<>(List.of(parsedInputs));

        updateCommand.execute(arguments);

        assertEquals("ABC", internships.getInternship(0).getCompany());
    }

    @Test
    void execute_outOfBoundsIndex_expectNoUpdate() {
        InternshipList internships = createList();

        assertEquals("ABC", internships.getInternship(0).getCompany());

        UpdateCommand updateCommand = new UpdateCommand();
        updateCommand.setInternshipList(internships);

        String[] parsedInputs = {"2", "company XYZ"};
        ArrayList<String> arguments = new ArrayList<>(List.of(parsedInputs));

        updateCommand.execute(arguments);

        assertEquals("ABC", internships.getInternship(0).getCompany());
    }

    @Test
    void execute_emptyValue_expectNoUpdate() {
        InternshipList internships = createList();

        assertEquals("ABC", internships.getInternship(0).getCompany());

        UpdateCommand updateCommand = new UpdateCommand();
        updateCommand.setInternshipList(internships);

        String[] parsedInputs = {"1", "company    "};
        ArrayList<String> arguments = new ArrayList<>(List.of(parsedInputs));

        updateCommand.execute(arguments);

        assertEquals("ABC", internships.getInternship(0).getCompany());
    }

    @Test
    void execute_oneValidoneInvalidValue_expectUpdateValidValue() {
        InternshipList internships = createList();

        assertEquals("ABC", internships.getInternship(0).getCompany());

        UpdateCommand updateCommand = new UpdateCommand();
        updateCommand.setInternshipList(internships);

        String[] parsedInputs = {"1", "company    ", "role Engineer"};
        ArrayList<String> arguments = new ArrayList<>(List.of(parsedInputs));

        updateCommand.execute(arguments);

        assertEquals("ABC", internships.getInternship(0).getCompany());
        assertEquals("Engineer", internships.getInternship(0).getRole());
    }

    @Test
    void execute_invalidStartDate_expectNoUpdate() {
        InternshipList internships = createList();

        UpdateCommand updateCommand = new UpdateCommand();
        updateCommand.setInternshipList(internships);

        String[] parsedInputs = {"1", "from 20/20"};
        ArrayList<String> arguments = new ArrayList<>(List.of(parsedInputs));

        updateCommand.execute(arguments);

        assertEquals("01/24", internships.getInternship(0).getStartDate());
    }
}
