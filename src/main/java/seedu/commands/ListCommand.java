package seedu.commands;

import java.util.ArrayList;
import java.util.logging.Level;

//@@author Toby-Yu
/**
 * Command to list all internships in the system.
 */
public class ListCommand extends Command {
    @Override
    public void execute(ArrayList<String> args) {
        internshipsList.listAllInternships();

        LOGGER.log(Level.INFO, "ListCommand Executed");
    }

    @Override
    public String getUsage() {
        return """
                list
                Usage: list""";
    }
}
