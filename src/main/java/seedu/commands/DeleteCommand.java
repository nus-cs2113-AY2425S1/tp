package seedu.commands;

import seedu.duke.InternshipList;

public class DeleteCommand implements Command {
    private final InternshipList internships;
    private int id;

    public DeleteCommand(InternshipList internshipList, int id) {
        this.internships = internshipList;
        this.id = id;
    }
    public void execute (String[] args) {
        id = Integer.parseInt(args[1]);
        int index = id - 1;
        internships.removeInternship(index);
    }

    public String getUsage() {
        return "Usage: del {ID}";
    }
}
