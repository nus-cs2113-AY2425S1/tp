package seedu.commands;

import seedu.duke.InternshipList;
import seedu.duke.Internship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.function.BiPredicate;

public class FilterCommand extends Command {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
    private final Map<String, InternshipFieldGetter> fieldGetters = new HashMap<>();

    public boolean functionComplete = false; // For testing purposes
    
    private ArrayList<Internship> internshipList;
    private InternshipList filteredInternships;

    public FilterCommand() {
        // Map flags to getter methods using lambdas
        fieldGetters.put("role", Internship::getRole);
        fieldGetters.put("company", Internship::getCompany);
        fieldGetters.put("from", Internship::getStartDate);
        fieldGetters.put("to", Internship::getEndDate);
    }

    public InternshipList getFilteredInternships() {
        return filteredInternships;
    }

    @Override
    public void execute(ArrayList<String> args) {
        assert internships != null: "Internship list should always be set before a command can be executed";
        internshipList = new ArrayList<>(internships.getAllInternships());
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
    }

    private void executeFilterByOneFlag(String[] words) {
        String flag = words[INDEX_FIELD];
        // Retrieve the corresponding getter method based on the flag
        InternshipFieldGetter getter = fieldGetters.get(flag);

        if (getter == null) {
            uiCommand.clearInvalidFlags();
            uiCommand.addInvalidFlag(flag);
            uiCommand.printInvalidFlags();
            throw new IllegalArgumentException();
        }

        if (words.length < 2) {
            uiCommand.showOutput(words[INDEX_FIELD] + " field cannot be empty");
            throw new IllegalArgumentException();
        }

        String searchTerm = words[INDEX_DATA];
        BiPredicate<YearMonth, YearMonth> dateComparator = null;

        switch (flag) {
        case "role":
        case "company":
            filterByRoleAndCompany(getter, searchTerm);
            return;

        case "from":
            dateComparator = YearMonth::isBefore;
            break;

        case "to":
            dateComparator = YearMonth::isAfter;
            break;

        default:
            assert false: "Should never be able to reach this statement if all flags are accounted for";
        }

        if (isValidDate(flag, searchTerm)) {
            filterByDate(getter, searchTerm, dateComparator);
        }
    }

    private boolean isValidDate(String flag, String searchTerm) {
        if (!searchTerm.matches("\\d{2}/\\d{2}")) {
            uiCommand.showOutput("Please enter a valid date for the " + flag + " flag");
            throw new IllegalArgumentException();
        }
        return true;
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

    private void filterByDate(InternshipFieldGetter getter, String searchTerm,
                              BiPredicate<YearMonth, YearMonth> dateComparator) {
        assert dateComparator != null : "dateComparator should not be null in filterByDate method";

        ArrayList<Internship> internshipListIterator = new ArrayList<>(internshipList);
        for (Internship internship : internshipListIterator) {
            String fieldValue = getter.getField(internship); // Dynamically calls getRole(), getCompany(), etc.
            YearMonth fieldDate = YearMonth.parse(fieldValue, formatter);
            YearMonth searchDate = YearMonth.parse(searchTerm, formatter);
            if (dateComparator.test(fieldDate, searchDate)) {
                internshipList.remove(internship);
            }
        }
    }

    @Override
    public String getUsage() {
        return """
                filter
                Usage: filter -{flag} {field data}""";
    }
}
