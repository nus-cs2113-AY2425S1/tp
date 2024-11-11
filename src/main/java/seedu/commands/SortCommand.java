package seedu.commands;

import java.util.ArrayList;
import java.util.logging.Level;

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
        assert internships != null : "Internships list cannot be null";
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
            assert internships.getAllInternships().size() > 0
                    : "Internships list should not be empty when sorting by role";
            internships.listInternshipsSortedByRole();  // Sort by role alphabetically (case-insensitive)
            break;
        case "duration":
            assert internships.getAllInternships().stream().allMatch(internship -> internship.getStartDate() != null &&
                    internship.getEndDate() != null) :
                    "Internships must have valid start and end dates to sort by duration";
            internships.listInternshipsSortedByDuration();  // Sort by start date, then end date (year first)
            break;
        case "deadline":
            internships.listInternshipsSortedByDeadline();
            break;
        case "skills":
            assert internships.getAllInternships().stream().anyMatch(internship -> !internship.getSkills().isEmpty()) :
                    "At least one internship must have skills to sort by skills";
            internships.listInternshipsSortedByFirstSkill();  // Sort by first skill alphabetically
            break;
        case "status":
            internships.listInternshipsSortedByStatus();  // Sort by status alphabetically
            break;
        case "company":
            internships.listInternshipsSortedByCompany();  // Sort by company alphabetically
            break;
        case "role in favourite":
            internships.listFavouriteInternshipsSortedByRole(); // Sort by role in favourite alphabetically
            break;                                              // (case-insensitive)
        case "duration in favourite":
            internships.listFavouriteInternshipsSortedByDuration();  // Sort by duration in favourite with start date,
            break;                                                   // then end date (year first)
        case "deadline in favourite":
            internships.listFavouriteInternshipsSortedByDeadline();  // Sort by the earliest deadline in favourite
            break;
        case "skills in favourite":
            internships.listFavouriteInternshipsSortedByFirstSkill(); // Sort by first skill in favourite alphabetically
            break;
        case "status in favourite":
            internships.listFavouriteInternshipsSortedByStatus();  // Sort by status in favourite  alphabetically
            break;
        case "company in favourite":
            internships.listFavouriteInternshipsSortedByCompany();// Sort by company in favourite alphabetically
            break;
        default:
            // Handle invalid sorting options
            internships.listInternshipsInvalidFlag(sortOption);
        }

        LOGGER.log(Level.INFO, "SortCommand Executed");
    }

    @Override
    public String getUsage() {
        return """
                sort
                Usage: sort -{field}
                
                Only sort by exactly one field in each time
                
                List of fields:
                
                - role: Sort internships alphabetically by role (case-insensitive).
                - deadline: Sort internships by start date (year first), then end date.
                - duration: Sort internships by internship duration.
                - skills: Sort internships by the first skill alphabetically.
                - status: Sort internships by status, (application pending, application completed
                         ,accepted, rejected) with this ascending order. Within the same status, the order is sorted by
                         role.
                - role in favourite: Sort internships in favourite alphabetically by role (case-insensitive).
                - deadline in favourite: Sort internships in favourite by start date (year first), then end date.
                - duration in favourite: Sort internships in favourite by internship duration.
                - skills in favourite: Sort internships in favourite by the first skill alphabetically.
                - status in favourite: Sort internships in favourite by status, (application pending,
                                       application completed, accepted, rejected) with this ascending order. Within the 
                                       same status, the order is sorted by role.
                """;
    }
}
