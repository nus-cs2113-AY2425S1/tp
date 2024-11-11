package seedu.commands;

import seedu.duke.InternshipList;
import seedu.duke.Internship;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.function.BiPredicate;
import java.util.logging.Level;

public class FilterCommand extends Command {
    public boolean functionComplete = false; // For testing purposes

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
    private final Map<String, InternshipFieldGetter> fieldGetters = new HashMap<>();
    
    private ArrayList<Internship> internshipList;
    private ArrayList<Internship> favouriteInternshipList;
    private InternshipList filteredInternships;

    public FilterCommand() {
        // Map flags to getter methods using lambdas
        fieldGetters.put("role", Internship::getRole);
        fieldGetters.put("company", Internship::getCompany);
        fieldGetters.put("from", Internship::getStartDate);
        fieldGetters.put("to", Internship::getEndDate);
        fieldGetters.put("favourite", Internship::getFavourite);
    }

    public InternshipList getFilteredInternships() {
        return filteredInternships;
    }

    @Override
    public void execute(ArrayList<String> args) {
        assert internships != null : "Internship list should always be set before a command can be executed";
        internshipList = new ArrayList<>(internships.getAllInternships());
        favouriteInternshipList = new ArrayList<>(internships.favouriteInternships);
        filteredInternships = new InternshipList(internshipList);

        if (args.isEmpty()) {
            uiCommand.showInsufficientArguments();
            return;
        }

        for (String arg : args) {
            String[] words = arg.split(" ", 2);
            try {
                executeFilterByOneFlag(words);
            } catch (IllegalArgumentException e) {
                return;
            }
        }

        functionComplete = true;
        filteredInternships.listAllInternships();

        LOGGER.log(Level.INFO, "FilterCommand Executed");
    }

    private void executeFilterByOneFlag(String[] words) {
        String flag = words[INDEX_FIELD];
        // Retrieve the corresponding getter method based on the flag
        InternshipFieldGetter getter = fieldGetters.get(flag);

        if (!fieldGetters.containsKey(flag)) {
            uiCommand.clearInvalidFlags();
            uiCommand.addInvalidFlag(flag);
            uiCommand.printInvalidFlags();
            throw new IllegalArgumentException();
        }

        if (getter == null) {
            uiCommand.showOutput(flag + " flag has been repeated\nPlease input a flag only once");
            throw new IllegalArgumentException();
        }

        if (words.length < 2) {
            uiCommand.showOutput(words[INDEX_FIELD] + " field cannot be empty");
            throw new IllegalArgumentException();
        }

        fieldGetters.put(flag, null);

        String searchTerm = words[INDEX_DATA];
        BiPredicate<YearMonth, YearMonth> dateComparator = null;

        switch (flag) {
        case "role":
        case "company":
            filterByRoleAndCompany(getter, searchTerm);
            return;

        case "favourite":
            filterByFavouriteInternships(searchTerm);
            return;

        case "from":
            dateComparator = YearMonth::isBefore;
            break;

        case "to":
            dateComparator = YearMonth::isAfter;
            break;

        default:
            assert false : "Should never be able to reach this statement if all flags are accounted for";
        }

        filterByDate(getter, searchTerm, dateComparator);
    }

    private void filterByRoleAndCompany(InternshipFieldGetter getter, String searchTerm) {
        ArrayList<Internship> internshipListIterator = new ArrayList<>(internshipList);
        // Iterate over the internships and apply the getter for comparison
        for (Internship internship : internshipListIterator) {
            String fieldValue = getter.getField(internship); // Dynamically calls getRole(), getCompany(),
            // etc.
            boolean isEqualToSearchTerm = fieldValue.equalsIgnoreCase(searchTerm);
            if (!isEqualToSearchTerm) {
                internshipList.remove(internship);
            }
        }
    }

    private void filterByFavouriteInternships(String searchTerm) {
        // Iterate over the internships and retrieve favourites
        switch (searchTerm) {
        case "true":
        case "True":
            filteredInternships.internships.retainAll(favouriteInternshipList);
            break;
        case "false":
        case "False":
            filteredInternships.internships.removeAll(favouriteInternshipList);
            break;
        default:
            uiCommand.showOutput("Please only input 'true'/'false' following the -fav flag");
            throw new IllegalArgumentException();
        }
    }

    private void filterByDate(InternshipFieldGetter getter, String searchTerm,
                              BiPredicate<YearMonth, YearMonth> dateComparator) {
        assert dateComparator != null : "dateComparator should not be null in filterByDate method";

        ArrayList<Internship> internshipListIterator = new ArrayList<>(internshipList);
        for (Internship internship : internshipListIterator) {
            String fieldValue = getter.getField(internship); // Dynamically calls getRole(), getCompany(), etc.
            YearMonth fieldDate = parseDate(fieldValue);
            assert fieldDate != null : "fieldValue should always be a valid date";
            YearMonth searchDate = parseDate(searchTerm);
            if (searchDate == null) {
                String outputString = searchTerm + " is not a valid date\n" + "Please enter a date in the MM/yy format";
                uiCommand.showOutput(outputString);
                throw new IllegalArgumentException();
            }
            if (dateComparator.test(fieldDate, searchDate)) {
                internshipList.remove(internship);
            }
        }
    }

    private YearMonth parseDate(String stringDate) {
        try {
            return YearMonth.parse(stringDate, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    @Override
    public String getUsage() {
        return """
                filter
                Usage: filter -{flag} {field data}""";
    }
}
