package seedu.duke;

public class SortCommand implements Command {
    private final InternshipList internships;

    public SortCommand(InternshipList internshipList) {
        this.internships = internshipList;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1 && args[1].equals("-alphabet")) {
            internships.listInternshipsSortedByRole(); // Sort by role alphabetically
            System.out.println("Sorted internships by role alphabetically.");
        } else if (args.length > 1 && args[1].equals("-deadline")) {
            internships.listInternshipsSortedByDeadline(); // Sort by deadline (start date, then end date)
            System.out.println("Sorted internships by start date, then end date.");
        } else {
            System.out.println("Unknown or missing flag. Listing internships by ID.");
            internships.listAllInternships(); // Default to listing by original order (ID)
        }
    }

    @Override
    public String getUsage() {
        return "Usage: sort [-alphabet | -deadline]";
    }
}
