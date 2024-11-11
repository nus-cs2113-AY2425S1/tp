package seedu.commands;

//@@author Ridiculouswifi

import seedu.EasInternship.Deadline;
import seedu.EasInternship.Internship;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;

/**
 * Subclass of <code>Command</code> to display all deadlines from every <code>Internship</code> entry.
 */
public class CalendarCommand extends Command {
    @Override
    public void execute(ArrayList<String> args) {
        ArrayList<Deadline> deadlines = getDeadlines();
        ArrayList<Deadline> sortedDeadlines = sortDeadlines(deadlines);
        uiCommand.showCalendar(sortedDeadlines);

        logger.log(Level.INFO, "CalendarCommand Executed");
    }

    protected ArrayList<Deadline> getDeadlines() {
        List<Internship> allInternships = internshipsList.getAllInternships();
        ArrayList<Deadline> deadlines = new ArrayList<>();
        for (Internship internship : allInternships) {
            deadlines.addAll(internship.getDeadlines());
        }
        return deadlines;
    }

    protected ArrayList<Deadline> sortDeadlines(ArrayList<Deadline> deadlines) {
        deadlines.sort(Comparator.comparing(deadline -> deadline.getUnformattedDate()));
        return deadlines;
    }

    @Override
    public String getUsage() {
        return """
                calendar
                Usage: calendar""";
    }
}
