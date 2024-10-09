package seedu.commands;

import seedu.duke.InternshipList;

public class UpdateCommand implements Command {
    private static final int INDEX_FIELD = 0;
    private static final int INDEX_ID = 1;

    private final InternshipList internships;

    public UpdateCommand(InternshipList internshipList) {
        this.internships = internshipList;
    }

    /**
     * Updates specific internship entries.
     *
     * @param args user input split by fields and the new values.
     */
    @Override
    public void execute(String[] args) {
        String field = "";
        String value = "";
        int internshipId = -1;
        String status = "";
        for (int i = 0; i < args.length; i++) {
            String[] words = args[i].split(" ");
            switch (words[INDEX_FIELD]) {
            case "id":
                internshipId = Integer.parseInt(words[INDEX_ID]);
                break;
            case "status":
            case "skills":
            case "role":
            case "company":
            case "end":
                field = words[INDEX_FIELD];
                value = words[INDEX_ID].replace(field, "").trim();
                internships.getInternship(internshipId).updateField(field, value);
                break;
            default:
                System.out.println("Unknown flag: " + words[INDEX_FIELD]);
                break;
            }
        }
        int internshipIndex = internshipId - 1;
        internships.getInternship(internshipIndex).updateStatus(status);
        System.out.println(internships.toString());
    }

    public String getUsage() {
        return """
                Usage: update -id {ID} -{field} {status}
                
                Choose from the following statuses:
                - Application Pending
                - Application Completed
                - Accepted
                - Rejected
                """;
    }
}
