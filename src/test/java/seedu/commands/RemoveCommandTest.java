package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.Internship;
import seedu.duke.InternshipList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author Ridiculouswifi
class RemoveCommandTest {

    InternshipList internships;
    RemoveCommand removeCommand;

    //@@author Ridiculouswifi
    void createList() {
        internships = new InternshipList();
        Internship internship = new Internship("Data", "ABC", "01/24", "06/24");
        internship.setSkills("Java, Python");
        internships.addInternship(internship);

        removeCommand = new RemoveCommand();
        removeCommand.setInternshipList(internships);
    }

    //@@author Ridiculouswifi
    @Test
    void updateOneField_validSkill_expectUpdate() {
        createList();

        String[] parsedInputs = {"01", "skills Java"};
        ArrayList<String> arguments = new ArrayList<>(List.of(parsedInputs));

        removeCommand.execute(arguments);

        assertEquals("Python", internships.getInternship(0).getSkills());
    }

    //@@author Ridiculouswifi
    @Test
    void updateOneField_invalidField_expectNoUpdate() {
        createList();

        String[] parsedInputs = {"01", "status Application Pending"};
        ArrayList<String> arguments = new ArrayList<>(List.of(parsedInputs));

        removeCommand.execute(arguments);

        assertEquals("Java, Python", internships.getInternship(0).getSkills());
    }

    //@@author Ridiculouswifi
    @Test
    void updateOneField_invalidSkill_expectException() {
        createList();

        String[] parsedInputs = {"01", "skills C++"};
        ArrayList<String> arguments = new ArrayList<>(List.of(parsedInputs));

        removeCommand.execute(arguments);

        assertEquals("Java, Python", internships.getInternship(0).getSkills());
    }
}
