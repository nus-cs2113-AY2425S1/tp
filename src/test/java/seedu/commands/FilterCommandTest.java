package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.Internship;
import seedu.duke.InternshipList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterCommandTest {

    InternshipList internships;
    FilterCommand filterCommand;

    void createList() {
        internships = new InternshipList();
        filterCommand = new FilterCommand();
        filterCommand.setInternshipList(internships);
    }

    @Test
    void execute_noArguments_abortsFiltering() {
        createList();
        ArrayList<String> args = new ArrayList<>();

        filterCommand.execute(args);

        assertEquals(0, filterCommand.getFilteredInternships().getSize());
    }

    @Test
    void execute_invalidFlag_abortsFiltering() {
        createList();
        ArrayList<String> args = new ArrayList<>();
        args.add("invalidflag Google");

        filterCommand.execute(args);

        assertEquals(0, filterCommand.getFilteredInternships().getSize());
    }

    @Test
    void execute_emptyField_abortsFiltering() {
        createList();
        ArrayList<String> args = new ArrayList<>();
        args.add("company");

        filterCommand.execute(args);

        assertEquals(0, filterCommand.getFilteredInternships().getSize());
    }

    @Test
    void execute_validRoleFilter_internshipFiltered() {
        createList();
        internships.addInternship(new Internship("Software Engineer", "Google", "01/24", "08/24"));
        internships.addInternship(new Internship("Data Scientist", "Meta", "02/24", "07/24"));
        ArrayList<String> args = new ArrayList<>();
        args.add("role Software Engineer");

        filterCommand.execute(args);

        assertEquals(1, filterCommand.getFilteredInternships().getSize());
        assertEquals("Software Engineer", filterCommand.getFilteredInternships().getInternship(0).getRole());
    }

    @Test
    void execute_validCompanyFilter_internshipFiltered() {
        createList();
        internships.addInternship(new Internship("Software Engineer", "Google", "01/24", "08/24"));
        internships.addInternship(new Internship("Data Scientist", "Meta", "02/24", "07/24"));
        ArrayList<String> args = new ArrayList<>();
        args.add("company Meta");

        filterCommand.execute(args);

        assertEquals(1, filterCommand.getFilteredInternships().getSize());
        assertEquals("Meta", filterCommand.getFilteredInternships().getInternship(0).getCompany());
    }

    @Test
    void execute_validDateFilter_internshipFiltered() {
        createList();
        internships.addInternship(new Internship("Software Engineer", "Google", "01/24", "08/24"));
        internships.addInternship(new Internship("Data Scientist", "Meta", "02/24", "07/24"));
        ArrayList<String> args = new ArrayList<>();
        args.add("from 01/24");

        filterCommand.execute(args);

        assertEquals(1, filterCommand.getFilteredInternships().getSize());
        assertEquals("01/24", filterCommand.getFilteredInternships().getInternship(0).getStartDate());
    }

    @Test
    void execute_validMultipleFlags_internshipFiltered() {
        createList();
        internships.addInternship(new Internship("Software Engineer", "Google", "01/24", "08/24"));
        internships.addInternship(new Internship("Data Scientist", "Meta", "02/24", "07/24"));
        internships.addInternship(new Internship("Software Engineer", "Meta", "04/24", "09/24"));
        ArrayList<String> args = new ArrayList<>();
        args.add("role Software Engineer");
        args.add("company Meta");

        filterCommand.execute(args);

        assertEquals(1, filterCommand.getFilteredInternships().getSize());
        assertEquals("Software Engineer", filterCommand.getFilteredInternships().getInternship(0).getRole());
        assertEquals("Meta", filterCommand.getFilteredInternships().getInternship(0).getCompany());
    }
}
