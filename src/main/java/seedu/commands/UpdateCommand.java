package seedu.commands;

import seedu.duke.InternshipList;

public class UpdateCommand implements Command {
    private static final int INDEX_FIELD = 0;
    private static final int INDEX_ID = 1;

    private static InternshipList internships;

    public UpdateCommand(InternshipList internshipList) {
        internships = internshipList;
    }

    /**
     * Updates specific internship entries.
     *
     * @param args user input split by fields and the new values.
     */
    @Override
    public void execute(String[] args) {
        int internshipIndex = -1;
        String field = "";
        String value = "";
        for (int i = 0; i < args.length; i++) {
            String[] words = args[i].split(" ");
            switch (words[INDEX_FIELD]) {
            case "id":
                internshipIndex = Integer.parseInt(words[INDEX_ID]);
                break;
            case "status":
            case "skills":
            case "role":
            case "company":
            case "end":
                field = words[INDEX_FIELD];
                value = words[INDEX_ID].replace(field, "").trim();
                internships.getInternship(internshipIndex).updateField(field, value);
                break;
            default:
                System.out.println("Unknown flag: " + words[INDEX_FIELD]);
                break;
            }
        }
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
