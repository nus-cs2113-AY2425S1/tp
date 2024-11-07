package seedu.commands;

import seedu.exceptions.InvalidIndex;
import seedu.exceptions.MissingValue;

//@@author Ridiculouswifi
/**
 * Subclass of <code>UpdateCommand</code> to handle deleting fields of an Internship.
 */
public class RemoveCommand extends UpdateCommand {
    @Override
    protected void updateOneField(String[] words, int internshipIndex) {
        String field = words[INDEX_FIELD];
        try {
            switch (field) {
            case "skills":
            case "deadline":
                if (!isValidValue(words)) {
                    return;
                }
                String value = words[INDEX_DATA].trim();
                internships.removeField(internshipIndex, field, value);
                uiCommand.addUpdatedField(field, value, "remove");
                break;
            case "status":
            case "role":
            case "company":
            case "from":
            case "to":
                uiCommand.addInvalidField(field, "Field cannot be empty");
                break;
            default:
                uiCommand.addInvalidFlag(field);
                break;
            }
        } catch (MissingValue e) {
            uiCommand.addInvalidField(field, words[INDEX_DATA] + " is not found");
        }
    }

    @Override
    public String getUsage() {
        return """
                remove
                Usage: remove {ID} -{field} {value}
                
                List of fields:
                - skills""";
    }
}
