package seedu.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.easinternship.Internship;
import seedu.easinternship.InternshipList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FavouriteCommandTest {

    InternshipList internships;
    FavouriteCommand favouriteCommand;
    ArrayList<String> args;

    @BeforeEach
    void initializeParams() {
        internships = new InternshipList();
        favouriteCommand = new FavouriteCommand();
        args = new ArrayList<>();
        favouriteCommand.setInternshipsList(internships);
    }

    void createList() {
        initializeParams();
        internships.addInternship(new Internship("Data Scientist", "Meta", "04/24", "07/24"));
        internships.addInternship(new Internship("Software Engineer", "Google", "01/24", "09/24"));
        internships.addInternship(new Internship("Data Scientist", "Google", "09/23", "05/24"));
    }

    @Test
    void execute_validFavouriteCommand_expectUpdated() {
        createList();
        ArrayList<String> args = new ArrayList<>();
        args.add("1");

        favouriteCommand.execute(args);

        ArrayList<Internship> favouriteInternships = internships.favouriteInternships;

        assertEquals(1, favouriteInternships.size());
    }

    @Test
    void execute_validFavouriteCommandMoreArguments_expectUpdated() {
        createList();
        ArrayList<String> args = new ArrayList<>();
        args.add("3");
        args.add("1");

        favouriteCommand.execute(args);

        ArrayList<Internship> favouriteInternships = internships.favouriteInternships;

        assertEquals(2, favouriteInternships.size());
        assertEquals(3, favouriteInternships.get(0).getId());
        assertEquals(1, favouriteInternships.get(1).getId());
    }

    @Test
    void execute_validFavouriteCommandDifferentCombination_expectUpdated() {
        createList();
        ArrayList<String> args = new ArrayList<>();
        args.add("2");
        args.add("3");

        favouriteCommand.execute(args);

        ArrayList<Internship> favouriteInternships = internships.favouriteInternships;

        assertEquals(2, favouriteInternships.size());
        assertEquals(2, favouriteInternships.get(0).getId());
        assertEquals(3, favouriteInternships.get(1).getId());
    }

    @Test
    void execute_validFavouriteCommandAllArguments_expectUpdated() {
        createList();
        ArrayList<String> args = new ArrayList<>();
        args.add("1");
        args.add("3");
        args.add("2");

        favouriteCommand.execute(args);

        ArrayList<Internship> favouriteInternships = internships.favouriteInternships;

        assertEquals(3, favouriteInternships.size());
        assertEquals(1, favouriteInternships.get(0).getId());
        assertEquals(3, favouriteInternships.get(1).getId());
        assertEquals(2, favouriteInternships.get(2).getId());
    }

    @Test
    void execute_invalidFavouriteCommandNoArguments_expectNoUpdated() {
        createList();
        ArrayList<String> args = new ArrayList<>();

        favouriteCommand.execute(args);

        ArrayList<Internship> favouriteInternships = internships.favouriteInternships;

        assert(favouriteInternships.isEmpty());
    }

    @Test
    void execute_invalidFavouriteCommandInvalidNumber_expectNoUpdated() {
        createList();
        ArrayList<String> args = new ArrayList<>();
        args.add("1");
        args.add("3");
        args.add("-");
        args.add("2");
        args.add("hmm");

        favouriteCommand.execute(args);

        ArrayList<Internship> favouriteInternships = internships.favouriteInternships;

        assertEquals(0, favouriteInternships.size());
    }

    @Test
    void execute_invalidFavouriteCommandInvalidIndex_expectNoUpdated() {
        createList();
        ArrayList<String> args = new ArrayList<>();
        args.add("1");
        args.add("2");
        args.add("3");
        args.add("4");
        args.add("5");

        favouriteCommand.execute(args);

        ArrayList<Internship> favouriteInternships = internships.favouriteInternships;

        assertEquals(0, favouriteInternships.size());
    }
}
