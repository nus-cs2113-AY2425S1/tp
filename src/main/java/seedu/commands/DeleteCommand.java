package seedu.commands;

import seedu.duke.InternshipList;

public class DeleteCommand implements Command {
    private final InternshipList internships;

    public DeleteCommand(InternshipList internshipList) {
        this.internships = internshipList;
    }
    public void execute (String[] args) {
        int id = Integer.parseInt(args[1]);
        int index = id - 1;
        internships.removeInternship(index);
    }

    public String getUsage() {
        return "Usage: del {ID}";
    }
}
