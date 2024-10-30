package seedu.commands;

//@@author Ridiculouswifi

import seedu.duke.Deadline;
import seedu.duke.Internship;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Subclass of <code>Command</code> to display all deadlines from every <code>Intenship</code> entry.
 */
public class CalendarCommand extends Command {
    @Override
    public void execute(ArrayList<String> args) {
        ArrayList<Deadline> deadlines = getDeadlines();
        ArrayList<Deadline> sortedDeadlines = sortDeadlines(deadlines);
        uiCommand.showCalendar(sortedDeadlines);
    }

    protected ArrayList<Deadline> getDeadlines() {
        List<Internship> allInternships = internships.getAllInternships();
        ArrayList<Deadline> deadlines = new ArrayList<>();
        for (Internship internship : allInternships) {
            deadlines.addAll(internship.getDeadlines());
        }
        return deadlines;
    }

    protected ArrayList<Deadline> sortDeadlines(ArrayList<Deadline> deadlines) {
        deadlines.sort(Comparator.comparing(deadline -> deadline.getDate()));
        return deadlines;
    }

    @Override
    public String getUsage() {
        return """
                calendar
                Usage: calendar""";
    }
}
