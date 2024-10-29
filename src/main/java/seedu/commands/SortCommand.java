package seedu.commands;

import java.util.ArrayList;

//@@author Toby-Yu
/**
 * Command to sort internships by different fields: alphabetically by role, by duration, by earliest deadline,
 * by the first skill in the skills list, or by status alphabetically.
 *
 * Usage:
 * - `sort -alphabet`: Sort internships alphabetically by role (case-insensitive).
 * - `sort -duration`: Sort internships by start date (year first), then end date.
 * - `sort -deadline`: Sort internships by earliest deadline.
 * - `sort -skills`: Sort internships by the first skill in the skills list alphabetically.
 * - `sort -status`: Sort internships by status alphabetically.
 *
 * Invalid sort options will display an error message along with the correct usage.
 */
public class SortCommand extends Command {

    // Execute method for the SortCommand
    @Override
    public void execute(ArrayList<String> args) {
        // Check if no arguments are provided after "sort"
        if (args.isEmpty()) {
            uiCommand.showSortedInternships("none");  // No valid sort option provided
            internships.listAllInternships();  // Default to listing by ID
            return;
        }

        // Get the first argument, which should be the sort option
        String sortOption = args.get(0).toLowerCase();

        // Handle valid sorting options
        switch (sortOption) {
        case "role":
            uiCommand.showSortedInternships(sortOption);  // Show sorting message for alphabet
            internships.listInternshipsSortedByRole();  // Sort by role alphabetically (case-insensitive)
            break;
        case "duration":
            uiCommand.showSortedInternships(sortOption);  // Show sorting message for deadline
            internships.listInternshipsSortedByDuration();  // Sort by start date, then end date (year first)
            break;
        case "deadline":
            uiCommand.showSortedInternships(sortOption);
            internships.listInternshipsSortedByDeadline();
            break;
        case "skills":
            uiCommand.showSortedInternships(sortOption);
            internships.listInternshipsSortedByFirstSkill();  // Sort by first skill alphabetically
            break;
        case "status":
            uiCommand.showSortedInternships(sortOption);
            internships.listInternshipsSortedByStatus();  // Sort by status alphabetically
            break;
        default:
            // Handle invalid sorting options
            uiCommand.clearInvalidFlags();
            uiCommand.addInvalidFlag(sortOption);
            uiCommand.printInvalidFlags();
            System.out.println(uiCommand.getSortUsageMessage());  // Show correct usage message
            internships.listAllInternships();  // Default to listing by ID
        }
    }

    @Override
    public String getUsage() {
        return """
                sort
                Usage: sort [role | deadline | duration | skills | status]
                alphabet: Sort internships alphabetically by role (case-insensitive).
                deadline: Sort internships by start date (year first), then end date.
                duration: Sort internships by internship duration.
                skills: Sort internships by the first skill alphabetically.
                status: Sort internships by status alphabetically.
                """;
    }
}
