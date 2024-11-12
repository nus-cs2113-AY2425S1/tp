package seedu.commands;

import seedu.exceptions.InvalidDeadline;
import seedu.exceptions.InvalidID;
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
            if (!internshipsList.isWithinBounds(internshipIndex)) {
                throw new InvalidID(internshipIndex);
            }
            args.remove(0);


            uiCommand.clearInvalidFlags();
            uiCommand.clearUpdatedFields();
            uiCommand.clearInvalidFields();

            for (String arg : args) {
                String[] words = arg.split(" ", 2);
                updateOneField(words, internshipIndex);
            }

            uiCommand.showEditedInternship(internshipsList.getInternship(internshipIndex), "update");

            LOGGER.log(Level.INFO, "UpdateCommand Executed");
        } catch (NumberFormatException e) {
            uiCommand.showOutput("Invalid integer, please provide a valid internship ID");
        } catch (InvalidID ie) {
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
                String updatedValue = internshipsList.updateField(internshipIndex, field, value);
                uiCommand.addUpdatedField(field, updatedValue, "update");
                break;
            default:
                uiCommand.addInvalidFlag(field);
                break;
            }
        } catch (DateTimeParseException e) {
            uiCommand.addInvalidField(field, "Invalid date format");
        } catch (InvalidDeadline e) {
            String message = """
                    Either description or date is missing.
                    \tIf you have provided a date, please check it is valid and is in dd/MM/yy format""";
            uiCommand.addInvalidField(field, message);
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
                - deadline: {description} {date (in dd/MM/yy format)}
                
                Choose from the following statuses:
                - Application Pending
                - Application Completed
                - Accepted
                - Rejected""";
    }
}
