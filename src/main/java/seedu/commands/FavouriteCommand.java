package seedu.commands;

import seedu.EasInternship.Internship;
import seedu.exceptions.InvalidID;
import seedu.exceptions.InvalidInteger;

import java.util.ArrayList;

/**
 * The {@code FavouriteCommand} class allows users to add internships to their list of favourite internships.
 * Users can do this by specifying the internship IDs.
 */
public class FavouriteCommand extends Command {
    /**
     * A {@code StringBuilder} to accumulate messages to be displayed to the user.
     */
    StringBuilder message = new StringBuilder();

    @Override
    public void execute(ArrayList<String> args) {
        if (args.isEmpty()) {
            uiCommand.showEmptyFlags();
            return;
        }

        ArrayList<Internship> inputInternships;
        try {
            inputInternships = getInputInternships(args);
        } catch (InvalidInteger | InvalidID exception) {
            uiCommand.showOutput(exception.getMessage());
            return;
        }

        ArrayList<Internship> favouriteInternships = internshipsList.favouriteInternships;
        for (Internship internship : inputInternships) {
            if (favouriteInternships.contains(internship)) {
                message.append("Internship with ID ").append(internship.getId())
                        .append(" is already present in favourite internships\n");
                continue;
            }
            favouriteInternships.add(internship);
        }
        message.append("The favourite internships are listed below");
        uiCommand.showOutput(String.valueOf(message));
        internshipsList.listFavouriteInternshipsBySortedByID();
    }

    /**
     * Retrieves the internships corresponding to the provided IDs.
     *
     * @param args an {@code ArrayList<String>} containing internship IDs; must not be empty.
     * @return an {@code ArrayList<Internship>} of internships matching the provided IDs
     * @throws InvalidInteger if any of the input IDs are not valid integers
     * @throws InvalidID   if any of the input IDs do not correspond to the IDs of the internships in the list
     */
    private ArrayList<Internship> getInputInternships(ArrayList<String> args) throws InvalidID, InvalidInteger {
        ArrayList<Internship> inputInternships = new ArrayList<>();
        for (String arg : args) {
            int InternshipIndex = getInternshipIndex(arg);
            Internship internship = internshipsList.getInternship(InternshipIndex);
            if (internship == null) {
                throw new InvalidID(InternshipIndex);
            }
            inputInternships.add(internship);
        }
        return inputInternships;
    }

    /**
     * Converts a string argument to an internship index.
     *
     * @param arg a string representing the internship ID; must be a valid integer
     * @return the internship index corresponding to the ID
     * @throws InvalidInteger if the {@code arg} is not a valid integer
     */
    private int getInternshipIndex (String arg) throws InvalidInteger {
        try {
            int internshipId = Integer.parseInt(arg);
            return internshipId - 1;
        } catch (NumberFormatException e) {
            throw new InvalidInteger(arg);
        }
    }

    @Override
    public String getUsage() {
        return """
                favourite
                Usage: favourite {Internship ID}""";
    }
}
