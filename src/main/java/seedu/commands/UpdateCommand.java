package seedu.commands;

import seedu.exceptions.InvalidIndex;

import java.util.ArrayList;

//@@author Ridiculouswifi
/**
 * Subclass of <code>Command</code> to handle updating <code>Internship</code> attributes
 */
public class UpdateCommand extends Command {
    @Override
    public void execute(ArrayList<String> args) {
        try {
            int internshipId = Integer.parseInt(args.get(0));
            int internshipIndex = internshipId - 1;
            args.remove(0);

            String field;
            String value;

            ui.clearInvalidFlags();
            ui.clearUpdatedFields();

            for (String arg : args) {
                String[] words = arg.split(" ", 2);
                switch (words[INDEX_FIELD]) {
                case "status":
                case "skills":
                case "role":
                case "company":
                case "from":
                case "to":
                    field = words[INDEX_FIELD];
                    if (!isValidValue(words)) {
                        continue;
                    }
                    value = words[INDEX_DATA].trim();
                    internships.updateField(internshipIndex, field, value);
                    ui.addUpdatedField(field, value);
                    break;
                default:
                    ui.addInvalidFlag(words[INDEX_FIELD]);
                    break;
                }
            }
            ui.showEditedInternship(internships.getInternship(internshipIndex), "update");
        } catch (NumberFormatException e) {
            System.out.println("Invalid integer, please provide a valid internship ID");
        } catch (InvalidIndex e) {
            // Exception message is already handled in InternshipList class
        }
    }

    private boolean isValidValue(String[] words) {
        try {
            String value = words[INDEX_DATA].trim();
            if (value.isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Field cannot be empty");
            return false;
        }
    }

    public String getUsage() {
        return """
                Usage: update {ID} -{field} {new value}
                
                List of fields:
                - status (refer to below for valid statuses)
                - skills
                - role
                - company
                - start (in MM/yy format)
                - end (in MM/yy format)
                
                Choose from the following statuses:
                - Application Pending
                - Application Completed
                - Accepted
                - Rejected
                """;
    }
}
