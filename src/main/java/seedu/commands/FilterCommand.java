package seedu.commands;
import seedu.duke.Internship;
import seedu.duke.InternshipList;

import java.util.HashMap;
import java.util.Map;

public class FilterCommand implements Command {
    private final InternshipList internships;
    private InternshipList filteredInternships;

    public FilterCommand(InternshipList internshipList) {
        this.internships = internshipList;
        this.filteredInternships = new InternshipList();
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            String flag = args[0];        // The flag (e.g., -role, -company)
            String searchTerm = args[1];  // What you are searching for

            // Map flags to getter methods using lambdas
            Map<String, InternshipFieldGetter> fieldGetters = new HashMap<>();
            fieldGetters.put("-name", Internship::getRole);
            fieldGetters.put("-company", Internship::getCompany);
            fieldGetters.put("-from", Internship::getStartDate);
            fieldGetters.put("-to", Internship::getEndDate);

            // Retrieve the corresponding getter method based on the flag
            InternshipFieldGetter getter = fieldGetters.get(flag);

            if (getter == null) {
                System.out.println("Unknown flag: " + flag);
                return;
            }

            // Iterate over the internships and apply the getter for comparison
            for (Internship internship : internships.getAllInternships()) {
                String fieldValue = getter.getField(internship); // Dynamically calls getRole(), getCompany(), etc.
                if (fieldValue.equals(searchTerm)) {
                    filteredInternships.addInternship(internship);
                }
            }

            filteredInternships.listAllInternships();
        } else {
            System.out.println("Insufficient arguments.");
        }
    }

    @Override
    public String getUsage() {
        return "Usage: filter -name {Role name}";
    }
}
