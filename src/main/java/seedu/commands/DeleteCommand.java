package seedu.commands;

import seedu.exceptions.InvalidID;

import java.util.ArrayList;
import java.util.logging.Level;

//@@author jadenlimjc
public class DeleteCommand extends Command {
    @Override
    public void execute (ArrayList<String> args) {
        try {
            int id = Integer.parseInt(args.get(0));
            int index = id - 1;
            internshipsList.removeInternship(index);

            logger.log(Level.INFO, "DeleteCommand Executed");
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
