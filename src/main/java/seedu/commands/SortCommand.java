package seedu.commands;

import seedu.duke.InternshipList;

public class SortCommand implements Command {
    private final InternshipList internships;

    public SortCommand(InternshipList internshipList) {
        this.internships = internshipList;
    }

    @Override
    public void execute(String[] args) {
        // Ensure that the args array contains at least one argument after "sort"
        if (args.length == 0) {
            System.out.println("No sorting option provided. Listing internships by ID.");
            internships.listAllInternships(); // Default to listing by original order (ID)
            return;
        }

        // Check if the user requested to sort by alphabet or deadline
        String sortOption = args[0].toLowerCase();

        switch (sortOption) {
            case "-alphabet":
                internships.listInternshipsSortedByRole(); // Sort by role alphabetically
                System.out.println("Sorted internships by role alphabetically.");
                break;
            case "-deadline":
                internships.listInternshipsSortedByDeadline(); // Sort by deadline (start date, then end date)
                System.out.println("Sorted internships by start date, then end date.");
                break;
            default:
                System.out.println("Unknown or missing flag. Listing internships by ID.");
                internships.listAllInternships(); // Default to listing by original order (ID)
        }
    }

    @Override
    public String getUsage() {
        return "Usage: sort [-alphabet | -deadline]";
    }
}