package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.Internship;
import seedu.duke.InternshipList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FilterCommandTest {

    InternshipList internships;
    FilterCommand filterCommand;

    void createList() {
        internships = new InternshipList();
        filterCommand = new FilterCommand();
        filterCommand.setInternshipList(internships);
    }

    void createListOne() {
        createList();
        internships.addInternship(new Internship("Software Engineer", "Google", "01/24", "08/24"));
        internships.addInternship(new Internship("Data Scientist", "Meta", "02/24", "07/24"));
    }

    void createListTwo() {
        createList();
        internships.addInternship(new Internship("Software Engineer", "Meta", "01/24", "09/24"));
        internships.addInternship(new Internship("Data Scientist", "Meta", "09/23", "05/24"));
        internships.addInternship(new Internship("Data Scientist", "Google", "04/24", "07/24"));
    }

    void createListThree() {
        createList();
        internships.addInternship(new Internship("Data Scientist", "Meta", "04/24", "07/24"));
        internships.addInternship(new Internship("Software Engineer", "Google", "01/24", "09/24"));
        internships.addInternship(new Internship("Data Scientist", "Google", "09/23", "05/24"));
    }


    @Test
    void execute_noArguments_abortsFiltering() {
        createListOne();
        ArrayList<String> args = new ArrayList<>();

        filterCommand.execute(args);

        assertFalse(filterCommand.functionComplete);
    }

    @Test
    void execute_invalidFlag_abortsFiltering() {
        createListOne();
        ArrayList<String> args = new ArrayList<>();
        args.add("invalidflag Google");

        filterCommand.execute(args);

        assertFalse(filterCommand.functionComplete);
    }

    @Test
    void execute_emptyField_abortsFiltering() {
        createListOne();
        ArrayList<String> args = new ArrayList<>();
        args.add("company");

        filterCommand.execute(args);

        assertFalse(filterCommand.functionComplete);
    }

    @Test
    void execute_invalidDateField_abortsFiltering() {
        createListOne();
        ArrayList<String> args = new ArrayList<>();
        args.add("from blah");

        filterCommand.execute(args);

        assertFalse(filterCommand.functionComplete);
    }

    @Test
    void execute_validRoleFilter_internshipFiltered() {
        createListOne();
        ArrayList<String> args = new ArrayList<>();
        args.add("role Software Engineer");

        filterCommand.execute(args);

        assertEquals(1, filterCommand.getFilteredInternships().getSize());
        assertEquals("Software Engineer", filterCommand.getFilteredInternships().getInternship(0).getRole());
    }

    @Test
    void execute_validCompanyFilter_internshipFiltered() {
        createListOne();
        ArrayList<String> args = new ArrayList<>();
        args.add("company Meta");

        filterCommand.execute(args);

        assertEquals(1, filterCommand.getFilteredInternships().getSize());
        assertEquals("Meta", filterCommand.getFilteredInternships().getInternship(0).getCompany());
    }

    @Test
    void execute_validFromDateFilter_internshipFiltered() {
        createListTwo();
        ArrayList<String> args = new ArrayList<>();
        args.add("from 01/24");

        filterCommand.execute(args);

        assertEquals(2, filterCommand.getFilteredInternships().getSize());
        assertEquals("01/24", filterCommand.getFilteredInternships().getInternship(0).getStartDate());
        assertEquals("04/24", filterCommand.getFilteredInternships().getInternship(1).getStartDate());
    }

    @Test
    void execute_validEndDateFilter_internshipFiltered() {
        createListTwo();
        ArrayList<String> args = new ArrayList<>();
        args.add("to 08/24");

        filterCommand.execute(args);

        assertEquals(2, filterCommand.getFilteredInternships().getSize());
        assertEquals("05/24", filterCommand.getFilteredInternships().getInternship(0).getEndDate());
        assertEquals("07/24", filterCommand.getFilteredInternships().getInternship(1).getEndDate());
    }

    @Test
    void execute_validRoleAndCompanyFilter_internshipFiltered() {
        createListTwo();
        ArrayList<String> args = new ArrayList<>();
        args.add("role Software Engineer");
        args.add("company Meta");

        filterCommand.execute(args);

        assertEquals(1, filterCommand.getFilteredInternships().getSize());
        assertEquals("Software Engineer", filterCommand.getFilteredInternships().getInternship(0).getRole());
        assertEquals("Meta", filterCommand.getFilteredInternships().getInternship(0).getCompany());
    }

    @Test
    void execute_validFromAndEndDateFilter_noInternshipFiltered() {
        createListTwo();
        ArrayList<String> args = new ArrayList<>();
        args.add("from 10/23");
        args.add("to 06/24");

        filterCommand.execute(args);

        assertEquals(0, filterCommand.getFilteredInternships().getSize());
    }

    @Test
    void execute_validFromAndEndDateFilter_internshipFiltered() {
        createListTwo();
        ArrayList<String> args = new ArrayList<>();
        args.add("from 10/23");
        args.add("to 07/24");

        filterCommand.execute(args);

        assertEquals(1, filterCommand.getFilteredInternships().getSize());
        assertEquals("04/24", filterCommand.getFilteredInternships().getInternship(0).getStartDate());
    }

    @Test
    void execute_validFromDateAndCompanyFilter_internshipFiltered() {
        createListThree();
        ArrayList<String> args = new ArrayList<>();
        args.add("from 01/24");
        args.add("company Google");

        filterCommand.execute(args);

        assertEquals(1, filterCommand.getFilteredInternships().getSize());
        assertEquals("01/24", filterCommand.getFilteredInternships().getInternship(0).getStartDate());
    }

    @Test
    void execute_validRoleAndEndDateFilter_internshipFiltered() {
        createListThree();
        ArrayList<String> args = new ArrayList<>();
        args.add("role Data Scientist");
        args.add("to 06/24");

        filterCommand.execute(args);

        assertEquals(1, filterCommand.getFilteredInternships().getSize());
        assertEquals("09/23", filterCommand.getFilteredInternships().getInternship(0).getStartDate());
    }
}
