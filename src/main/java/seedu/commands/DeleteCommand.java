package seedu.commands;

import seedu.exceptions.InvalidID;

import java.util.ArrayList;
import java.util.logging.Level;

//@@author jadenlimjc
/**
* The DeleteCommand class handles the deletion of internships from the internship list.
* It takes an internship ID as an argument, validates the ID, and removes the corresponding internship
* from the list. If the ID is invalid (non-integer or out of range), appropriate error messages are shown to the user.
* The command also logs the action for debugging purposes.
*
* Usage:
* delete {ID}
*
* The ID corresponds to the index of the internship in the list, and it should be a valid integer.
*/
public class DeleteCommand extends Command {
    @Override
    public void execute (ArrayList<String> args) {
        try {
            int id = Integer.parseInt(args.get(0));
            int index = id - 1;
            internshipsList.removeInternship(index);

            LOGGER.log(Level.INFO, "DeleteCommand Executed");
        } catch (NumberFormatException e) {
            uiCommand.showOutput("Invalid integer, please provide a valid internship ID");
        } catch (InvalidID ie) {
            uiCommand.showOutput(ie.getMessage());
        }
    }

    @Override
    public String getUsage() {
        return """
                delete
                Usage: delete {ID}""";
    }
}
