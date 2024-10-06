package seedu.commands;

import seedu.duke.InternshipList;

public class UpdateCommand implements Command {
    @Override
    public void execute(String[] args) {
        final int INDEX_FIELD = 0;
        int internshipIndex = -1;
        String status = "";
        for (int i = 0; i < args.length; i++) {
            String[] words = args[i].split(" ");
            switch (words[INDEX_FIELD]) {
            case "id":
                final int INDEX_ID = 1;
                internshipIndex = Integer.parseInt(words[INDEX_ID]);
            case "status":
                status = args[i].replaceFirst("status", "").trim();
            }
        }
        InternshipList.internships.get(internshipIndex).updateStatus(status);
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
