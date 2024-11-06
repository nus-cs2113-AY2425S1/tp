package seedu.commands;

import seedu.duke.Internship;
import seedu.exceptions.InvalidIndex;

import java.util.ArrayList;

public class FavouriteCommand extends Command {
    public boolean functionComplete = false;

    @Override
    public void execute(ArrayList<String> args) {
        for (String arg : args) {
            try {
                int internshipId = Integer.parseInt(arg);
                int internshipIndex = internshipId - 1;
                Internship internship = internships.getInternship(internshipIndex);
                ArrayList<Internship> favouriteInternships = internships.favouriteInternships;
                if (internship == null) {
                    throw new InvalidIndex();
                }
                if (favouriteInternships.contains(internship)) {
                    continue;
                }
                functionComplete = true;
                favouriteInternships.add(internship);
            } catch (NumberFormatException e) {
                uiCommand.showOutput("Invalid integer, please provide a valid internship ID");
                functionComplete = false;
                return;
            } catch (InvalidIndex e) {
                functionComplete = false;
                return;
                // Exception message is already handled in InternshipList class
            }
        }
        if (args.isEmpty()) {
            uiCommand.showEmptyFlags();
            return;
        }
        internships.listAllInternships(internships.favouriteInternships);
        if (!functionComplete) {
            uiCommand.showOutput("All id's provided were marked as favourite already");
        }
        functionComplete = true;
    }

    // rahul can change here also
    @Override
    public String getUsage() {
        return """
                filter
                Usage: filter -{flag} {field data}""";
    }
}
