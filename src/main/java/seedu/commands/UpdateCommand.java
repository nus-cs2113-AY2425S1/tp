package seedu.commands;

import seedu.duke.InternshipList;

public class UpdateCommand implements Command {
    private static final int INDEX_FIELD = 0;
    private static final int INDEX_ID = 1;

    private InternshipList internships;

    public UpdateCommand(InternshipList internshipList) {
        this.internships = internshipList;
    }

    @Override
    public void execute(String[] args) {
        int internshipId = -1;
        String status = "";
        for (int i = 0; i < args.length; i++) {
            String[] words = args[i].split(" ");
            switch (words[INDEX_FIELD]) {
            case "id":
                internshipId = Integer.parseInt(words[INDEX_ID]);
                break;
            case "status":
                status = args[i].replaceFirst("status", "").trim();
                break;
            default:
                System.out.println("Unknown flag: " + words[INDEX_FIELD]);
                break;
            }
        }
        int internshipIndex = internshipId - 1;
        internships.getInternship(internshipIndex).updateStatus(status);
    }

    public String getUsage() {
        return """
                Usage: update -id {ID} -status {status}
                
                Choose from the following statuses"
                - Application Pending
                - Application Completed
                - Accepted
                - Rejected
                """;
    }
}
