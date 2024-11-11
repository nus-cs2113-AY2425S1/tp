package seedu.commands;

import seedu.easinternship.InternshipList;
import seedu.easinternship.Internship;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.function.BiPredicate;
import java.util.logging.Level;

/**
 * Filters the list of internships based on the given criteria.
 * It supports filtering by role, company, favourite status, and date range.
 */
public class FilterCommand extends Command {
    // Boolean variable is used for unit testing purposes
    public boolean functionComplete = false;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
    private final Map<String, InternshipFieldGetter> fieldGetters = new HashMap<>();
    private ArrayList<Internship> internships;
    private ArrayList<Internship> favouriteInternships;
    private InternshipList filteredInternshipsList;

    /**
     * Constructs a {@code FilterCommand} object
     * and populates the {@code fieldGetters} attribute with all the flags
     * and their respective lambda functions
     */
    public FilterCommand() {
        fieldGetters.put("role", Internship::getRole);
        fieldGetters.put("company", Internship::getCompany);
        fieldGetters.put("from", Internship::getStartDate);
        fieldGetters.put("to", Internship::getEndDate);
        fieldGetters.put("favourite", Internship::getFavourite);
    }

    public InternshipList getFilteredInternships() {
        return filteredInternshipsList;
    }

    @Override
    public void execute(ArrayList<String> args) {
        assert internshipsList != null : "Internship list should always be set before a command can be executed";
        internships = new ArrayList<>(internshipsList.getAllInternships());
        favouriteInternships = new ArrayList<>(internshipsList.favouriteInternships);
        filteredInternshipsList = new InternshipList(internships);

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
        filteredInternshipsList.listAllInternships();

        LOGGER.log(Level.INFO, "FilterCommand Executed");
    }

    /**
     * Extracts the flag, its corresponding getter method and the search term
     * from the parsed arguments
     * and then filters by the respective flag
     *
     * @param words The parsed arguments; must have a length of exactly 2
     * @throws IllegalArgumentException If the arguments have an unknown flag, repeated flags or an empty field
     *                                  and in the case of an invalid favourite status or date field input by the user
     */
    private void executeFilterByOneFlag(String[] words) throws IllegalArgumentException {
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
        filterByRespectiveFlag(flag, getter, searchTerm);
    }

    /**
     * Parses the flag argument to then filter by the appropriate method for the
     * given flag
     *
     * @param flag       The field type to filter by
     * @param getter     The lambda method for fetching the respective field based on the flag
     * @param searchTerm The field value input by the user
     * @throws IllegalArgumentException If an invalid favourite status or date field is input by the user
     */
    private void filterByRespectiveFlag(String flag, InternshipFieldGetter getter, String searchTerm)
            throws IllegalArgumentException {
        switch (flag) {
        case "role":
        case "company":
            filterByRoleAndCompany(getter, searchTerm);
            return;

        case "favourite":
            filterByFavouriteInternships(searchTerm);
            return;

        case "from":
            filterByDate(getter, searchTerm, YearMonth::isBefore);
            break;

        case "to":
            filterByDate(getter, searchTerm, YearMonth::isAfter);
            break;

        default:
            assert false : "Should never be able to reach this statement if all flags are accounted for";
        }
    }

    /**
     * Retains any internships whose role/company field is equal to the field entered
     * by the user (case-insensitive) and discards the rest.
     *
     * @param getter     The lambda method for fetching the role/company field
     * @param inputValue The field value input by the user
     */
    private void filterByRoleAndCompany(InternshipFieldGetter getter, String inputValue) {
        ArrayList<Internship> internshipListIterator = new ArrayList<>(internships);
        // Iterate over the internships and apply the getter for comparison
        for (Internship internship : internshipListIterator) {
            String fieldValue = getter.getField(internship); // Dynamically calls getRole(), getCompany(),
            // etc.
            boolean isEqualToSearchTerm = fieldValue.equalsIgnoreCase(inputValue);
            if (isEqualToSearchTerm) {
                continue;
            }
            internships.remove(internship);
        }
    }

    /**
     * Retains any internships whose favourite status is equal to the status entered
     * by the user (case-insensitive) and discards the rest.
     *
     * @param inputFavouriteStatus The favourite status value input by the user
     * @throws IllegalArgumentException If the {@code inputFavouriteStatus} is invalid; neither true nor false
     */
    private void filterByFavouriteInternships(String inputFavouriteStatus) throws IllegalArgumentException {
        // Iterate over the internships and retrieve favourites
        if (inputFavouriteStatus.equalsIgnoreCase("true")) {
            filteredInternshipsList.internships.retainAll(favouriteInternships);
        } else if (inputFavouriteStatus.equalsIgnoreCase("false")) {
            filteredInternshipsList.internships.removeAll(favouriteInternships);
        } else {
            uiCommand.showOutput("Please only input 'true'/'false' following the -favourite flag");
            throw new IllegalArgumentException();
        }
    }

    /**
     * Removes any internships whose date is before the {@code -from} date entered by the user
     * and if the date is after the {@code -to} date entered by the user. It retains the rest of the
     * internships.
     *
     * @param getter         The lambda method for fetching the date field
     * @param inputDate      The date value input by the user
     * @param dateComparator The lambda method for comparing the dates
     * @throws IllegalArgumentException If the {@code inputDate} is an invalid date
     */
    private void filterByDate(InternshipFieldGetter getter, String inputDate,
                              BiPredicate<YearMonth, YearMonth> dateComparator) throws IllegalArgumentException {
        ArrayList<Internship> internshipListIterator = new ArrayList<>(internships);
        for (Internship internship : internshipListIterator) {
            String fieldTerm = getter.getField(internship); // Dynamically calls getRole(), getCompany(), etc.
            YearMonth fieldDate = parseDate(fieldTerm);
            YearMonth searchDate = parseDate(inputDate);
            if (dateComparator.test(fieldDate, searchDate)) {
                internships.remove(internship);
            }
        }
    }

    private YearMonth parseDate(String stringDate) throws IllegalArgumentException {
        try {
            return YearMonth.parse(stringDate, formatter);
        } catch (DateTimeParseException e) {
            String outputString = stringDate + " is not a valid date\n" + "Please enter a date in the MM/yy format";
            uiCommand.showOutput(outputString);
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String getUsage() {
        return """
                filter
                Usage: filter -{flag} {field data}""";
    }
}
