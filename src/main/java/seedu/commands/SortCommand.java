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
        uiCommand.clearInvalidFlags();

        // Check if no arguments are provided after "sort"
        if (args.isEmpty()) {
            internships.listInternshipsNotSorted(); // No valid sort option provided
            return;
        }

        // Get the first argument, which should be the sort option
        String sortOption = args.get(0).toLowerCase();

        // Handle valid sorting options
        switch (sortOption) {
        case "role":
            internships.listInternshipsSortedByRole();  // Sort by role alphabetically (case-insensitive)
            break;
        case "duration":
            internships.listInternshipsSortedByDuration();  // Sort by start date, then end date (year first)
            break;
        case "deadline":
            internships.listInternshipsSortedByDeadline();
            break;
        case "skills":
            internships.listInternshipsSortedByFirstSkill();  // Sort by first skill alphabetically
            break;
        case "status":
            internships.listInternshipsSortedByStatus();  // Sort by status alphabetically
            break;
        default:
            // Handle invalid sorting options
            internships.listInternshipsInvalidFlag(sortOption);
        }
    }

    @Override
    public String getUsage() {
        return """
                sort
                Usage: sort [role | deadline | duration | skills | status]
                
                role: Sort internships alphabetically by role (case-insensitive).
                deadline: Sort internships by start date (year first), then end date.
                duration: Sort internships by internship duration.
                skills: Sort internships by the first skill alphabetically.
                status: Sort internships by status alphabetically.
                """;
    }
}