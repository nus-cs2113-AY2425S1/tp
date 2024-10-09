package seedu.commands;

import java.util.ArrayList;

public class UpdateCommand extends Command {
    @Override
    public void execute(ArrayList<String> args) {
        int internshipId = Integer.parseInt(args.get(0));
        args.remove(0);
        String status = "";
        for (String arg : args) {
            String[] words = arg.split(" ", 2);
            switch (words[INDEX_FIELD]) {
            case "status":
                status = words[INDEX_DATA].trim();
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
