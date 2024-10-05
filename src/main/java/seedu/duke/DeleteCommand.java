package seedu.duke;

public class DeleteCommand implements Command {
    private final InternshipList internships;
    private int index;

    public DeleteCommand(InternshipList internshipList, int index) {
        this.internships = internshipList;
        this.index = index;
    }
    public void execute (String[] args) {
        index = Integer.parseInt(args[1]);
        int id = index - 1;
        internships.removeInternship(id);
    }

    public String getUsage() {
        return "Usage: del {ID}";
    }
}
