package seedu.commands;

import seedu.exceptions.InvalidDeadline;
import seedu.exceptions.InvalidIndex;
import seedu.exceptions.InvalidStatus;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;

//@@author Ridiculouswifi
/**
 * Subclass of <code>Command</code> to handle updating <code>Internship</code> attributes
 */
public class UpdateCommand extends Command {
    @Override
    public void execute(ArrayList<String> args) {
        try {
            assert !args.get(0).startsWith("update") : "Parser should have removed the command";

            int internshipId = Integer.parseInt(args.get(0));
            int internshipIndex = internshipId - 1;
            if (!internships.isWithinBounds(internshipIndex)) {
                throw new InvalidIndex(internshipIndex);
            }
            args.remove(0);


            uiCommand.clearInvalidFlags();
            uiCommand.clearUpdatedFields();
            uiCommand.clearInvalidFields();

            for (String arg : args) {
                String[] words = arg.split(" ", 2);
                updateOneField(words, internshipIndex);
            }

            uiCommand.showEditedInternship(internships.getInternship(internshipIndex), "update");

            logger.log(Level.INFO, "UpdateCommand Executed");
        } catch (NumberFormatException e) {
            uiCommand.showOutput("Invalid integer, please provide a valid internship ID");
        } catch (InvalidIndex ie) {
            uiCommand.showOutput(ie.getMessage());
        }
    }

    protected boolean isValidValue(String[] words) {
        try {
            String value = words[INDEX_DATA].trim();
            if (value.isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
            return true;
        } catch (IndexOutOfBoundsException e) {
            uiCommand.addInvalidField(words[INDEX_FIELD], "Field cannot be empty");
            return false;
        }
    }

    protected void updateOneField(String[] words, int internshipIndex) {
        String field = words[INDEX_FIELD];
        try {
            switch (field) {
            case "status":
            case "skills":
            case "role":
            case "company":
            case "from":
            case "to":
            case "deadline":
                if (!isValidValue(words)) {
                    return;
                }
                String value = words[INDEX_DATA].trim();
                internships.updateField(internshipIndex, field, value);
                uiCommand.addUpdatedField(field, value, "update");
                break;
            default:
                uiCommand.addInvalidFlag(field);
                break;
            }
        } catch (DateTimeParseException e) {
            uiCommand.addInvalidField(field, "Invalid date format");
        } catch (InvalidDeadline e) {
            uiCommand.addInvalidField(field, "Either description or date is missing.");
        } catch (InvalidStatus e) {
            String message = """
                    Status provided is not recognised
                    \tPlease provide one of the following:
                    \t- Application Pending
                    \t- Application Completed
                    \t- Accepted
                    \t- Rejected""";
            uiCommand.addInvalidField(field, message);
        }
    }

    public String getUsage() {
        return """
                update
                Usage: update {ID} -{field} {new value}
                
                List of fields:
                - status (refer to below for valid statuses)
                - skills
                - role
                - company
                - start (in MM/yy format)
                - end (in MM/yy format)
                - deadline ({description} {date (in dd/MM/yy format)}
                
                Choose from the following statuses:
                - Application Pending
                - Application Completed
                - Accepted
                - Rejected""";
    }
}
