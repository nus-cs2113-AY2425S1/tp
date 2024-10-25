package seedu.commands;

import seedu.duke.InternshipList;
import seedu.duke.Internship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FilterCommand extends Command {
    Map<String, InternshipFieldGetter> fieldGetters = new HashMap<>();
    private ArrayList<Internship> internshipList = new ArrayList<>();
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

        filteredInternships.listAllInternships();
    }

    private void executeFilterByOneFlag(String[] words) {
        String flag = words[INDEX_FIELD];
        // Retrieve the corresponding getter method based on the flag
        InternshipFieldGetter getter = fieldGetters.get(flag);
        ArrayList<Internship> internshipListIterator = new ArrayList<>(internshipList);

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

        // Iterate over the internships and apply the getter for comparison
        for (Internship internship : internshipListIterator) {
            String fieldValue = getter.getField(internship); // Dynamically calls getRole(), getCompany(),
            // etc.
            if (!fieldValue.equalsIgnoreCase(searchTerm)) {
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
