package seedu.commands;

import seedu.duke.InternshipList;

/**
 * Command to list all internships in the system.
 */
public class ListCommand implements Command {
    private final InternshipList internships;

    public ListCommand(InternshipList internshipList) {
        this.internships = internshipList;
    }

    @Override
    public void execute(String[] args) {
        internships.listAllInternships();
    }

    @Override
    public String getUsage() {
        return "Usage: list";
    }
}