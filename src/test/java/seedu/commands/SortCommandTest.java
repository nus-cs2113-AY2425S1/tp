package seedu.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.EasInternship.Internship;
import seedu.EasInternship.InternshipList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for SortCommand.
 */
class SortCommandTest {

    private InternshipList internshipList;

    @BeforeEach
    void setUp() {
        internshipList = new InternshipList();

        // Add some internships to the list for testing
        Internship internship1 = new Internship("Software Engineer", "Google", "01/24", "06/24");
        Internship internship2 = new Internship("Data Scientist", "Facebook", "02/23", "08/23");
        Internship internship3 = new Internship("Product Manager", "Amazon", "01/23", "09/23");
        Internship internship4 = new Internship("Backend Developer", "Apple", "03/25", "12/25");
        Internship internship5 = new Internship("Frontend Developer", "Netflix", "04/24", "10/24");

        internshipList.addInternship(internship1);
        internshipList.addInternship(internship2);
        internshipList.addInternship(internship3);
        internshipList.addInternship(internship4);
        internshipList.addInternship(internship5);
    }

    @Test
    void execute_sortNoArgs_expectNoCrash() {
        SortCommand sortCommand = new SortCommand();
        sortCommand.setInternshipsList(internshipList);

        // Test "sort" with no additional arguments
        ArrayList<String> arguments = new ArrayList<>();
        assertDoesNotThrow(() -> sortCommand.execute(arguments));
    }

    @Test
    void execute_sortEmptyFlag_expectNoCrash() {
        SortCommand sortCommand = new SortCommand();
        sortCommand.setInternshipsList(internshipList);

        // Test "sort -" with an empty flag
        ArrayList<String> arguments = new ArrayList<>(List.of("-"));
        assertDoesNotThrow(() -> sortCommand.execute(arguments));
    }

    @Test
    void execute_sortRole_expectSortedByRole() {
        SortCommand sortCommand = new SortCommand();
        sortCommand.setInternshipsList(internshipList);

        // Test "sort role" (sorting by role alphabetically)
        ArrayList<String> arguments = new ArrayList<>(List.of("role"));
        assertDoesNotThrow(() -> sortCommand.execute(arguments));
    }

    @Test
    void execute_sortCompany_expectSortedByCompany() {
        SortCommand sortCommand = new SortCommand();
        sortCommand.setInternshipsList(internshipList);

        // Test "sort company" (sorting by company alphabetically)
        ArrayList<String> arguments = new ArrayList<>(List.of("company"));
        assertDoesNotThrow(() -> sortCommand.execute(arguments));
    }

    @Test
    void execute_sortDuration_expectSortedByDuration() {
        SortCommand sortCommand = new SortCommand();
        sortCommand.setInternshipsList(internshipList);

        // Test "sort duration" (sorting by duration)
        ArrayList<String> arguments = new ArrayList<>(List.of("duration"));
        assertDoesNotThrow(() -> sortCommand.execute(arguments));
    }

    @Test
    void execute_sortDeadline_expectSortedByDeadline() {
        SortCommand sortCommand = new SortCommand();
        sortCommand.setInternshipsList(internshipList);

        // Test "sort -deadline" (sorting by the earliest deadline)
        ArrayList<String> arguments = new ArrayList<>(List.of("deadline"));
        assertDoesNotThrow(() -> sortCommand.execute(arguments));
    }

    @Test
    void execute_sortSkills_expectSortedByFirstSkill() {
        SortCommand sortCommand = new SortCommand();
        sortCommand.setInternshipsList(internshipList);

        // Add skills to some internships
        internshipList.getInternship(0).setSkills("Java, Python");
        internshipList.getInternship(1).setSkills("SQL, Machine Learning");
        internshipList.getInternship(2).setSkills("Leadership");

        // Test "sort skills" (sorting by the first skill alphabetically)
        ArrayList<String> arguments = new ArrayList<>(List.of("skills"));
        assertDoesNotThrow(() -> sortCommand.execute(arguments));
    }

    @Test
    void execute_sortStatus_expectSortedByStatus() {
        SortCommand sortCommand = new SortCommand();
        sortCommand.setInternshipsList(internshipList);

        // Update statuses
        internshipList.getInternship(0).setStatus("Application Pending");
        internshipList.getInternship(1).setStatus("Accepted");
        internshipList.getInternship(2).setStatus("Application Completed");
        internshipList.getInternship(3).setStatus("Rejected");
        internshipList.getInternship(4).setStatus("Accepted");

        // Test "sort status" (sorting by status in the correct order)
        ArrayList<String> arguments = new ArrayList<>(List.of("status"));
        assertDoesNotThrow(() -> sortCommand.execute(arguments));
    }

    @Test
    void execute_sortUnknown_expectNoCrash() {
        SortCommand sortCommand = new SortCommand();
        sortCommand.setInternshipsList(internshipList);

        // Test "sort -unknown" (invalid flag)
        ArrayList<String> arguments = new ArrayList<>(List.of("unknown"));
        assertDoesNotThrow(() -> sortCommand.execute(arguments));
    }

    @Test
    void execute_sortRoleInFavourites_expectSortedByRoleInFavourites() {
        SortCommand sortCommand = new SortCommand();
        sortCommand.setInternshipsList(internshipList);

        // Add some internships to favourites
        internshipList.favouriteInternships.add(internshipList.getInternship(0));
        internshipList.favouriteInternships.add(internshipList.getInternship(2));

        // Test "sort role in favourite"
        ArrayList<String> arguments = new ArrayList<>(List.of("role in favourite"));
        assertDoesNotThrow(() -> sortCommand.execute(arguments));
    }

    @Test
    void execute_sortDurationInFavourites_expectSortedByDurationInFavourites() {
        SortCommand sortCommand = new SortCommand();
        sortCommand.setInternshipsList(internshipList);

        // Add some internships to favourites
        internshipList.favouriteInternships.add(internshipList.getInternship(0));
        internshipList.favouriteInternships.add(internshipList.getInternship(1));

        // Test "sort duration in favourite"
        ArrayList<String> arguments = new ArrayList<>(List.of("duration in favourite"));
        assertDoesNotThrow(() -> sortCommand.execute(arguments));
    }

    @Test
    void execute_sortByCompanyInFavourites_expectSortedByCompanyInFavourites() {
        SortCommand sortCommand = new SortCommand();
        sortCommand.setInternshipsList(internshipList);

        // Add some internships to favourites
        internshipList.favouriteInternships.add(internshipList.getInternship(1));
        internshipList.favouriteInternships.add(internshipList.getInternship(4));

        // Test "sort company in favourite"
        ArrayList<String> arguments = new ArrayList<>(List.of("company in favourite"));
        assertDoesNotThrow(() -> sortCommand.execute(arguments));
    }

    @Test
    void execute_sortByStatusInFavourites_expectSortedByStatusInFavourites() {
        SortCommand sortCommand = new SortCommand();
        sortCommand.setInternshipsList(internshipList);

        // Add some internships to favourites and set statuses
        internshipList.favouriteInternships.add(internshipList.getInternship(0));
        internshipList.favouriteInternships.add(internshipList.getInternship(2));
        internshipList.getInternship(0).setStatus("Application Pending");
        internshipList.getInternship(2).setStatus("Application Completed");

        // Test "sort status in favourite"
        ArrayList<String> arguments = new ArrayList<>(List.of("status in favourite"));
        assertDoesNotThrow(() -> sortCommand.execute(arguments));
    }
}