package seedu.commands;

import java.util.ArrayList;

public class SortCommand extends Command {
    @Override
    public void execute(ArrayList<String> args) {
        // Check if no arguments are provided after "sort"
        if (args.isEmpty()) {
            ui.showSortedInternships("none");  // No valid sort option provided
            internships.listAllInternships();  // Default to listing by ID
            return;
        }

        // Get the first argument, which should be the sort option
        String sortOption = args.get(0).toLowerCase();

        // Handle valid sorting options
        switch (sortOption) {
        case "alphabet":
            ui.showSortedInternships(sortOption);  // Show sorting message for alphabet
            internships.listInternshipsSortedByRole();  // Sort by role alphabetically
            break;
        case "deadline":
            ui.showSortedInternships(sortOption);  // Show sorting message for deadline
            internships.listInternshipsSortedByDeadline();  // Sort by start date, then end date
            break;
        default:
            // Handle invalid sorting options
            ui.showSortedInternships(sortOption);  // Show error message for invalid option
            internships.listAllInternships();  // Default to listing by ID
        }
    }

    @Override
    public String getUsage() {
        return "Usage: sort [alphabet | deadline]";
    }
}
