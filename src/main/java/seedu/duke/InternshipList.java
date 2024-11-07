package seedu.duke;

import seedu.exceptions.InvalidDeadline;
import seedu.exceptions.InvalidIndex;
import seedu.exceptions.InvalidStatus;
import seedu.exceptions.MissingValue;
import seedu.ui.UiInternshipList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

//@@author jadenlimjc
public class InternshipList {
    private static final UiInternshipList ui = new UiInternshipList();
    public ArrayList<Internship> internships;

    // Constructor
    public InternshipList() {
        internships = new ArrayList<>();
    }

    public InternshipList(ArrayList<Internship> internships) {
        this.internships = internships;
    }

    public void addInternship(Internship internship) {

        assert internship != null : "Internship object cannot be null";

        if (internship.getId() == -1) {
            internship.setId(internships.size());
        }

        assert internship.getId() == internships.size() + 1;

        internships.add(internship);
    }

    //@@author Ridiculouswifi
    /**
     * Returns whether the index given is within the boundaries of the list.
     */
    public boolean isWithinBounds(int index) {
        if (index >= 0 && index < internships.size()) {
            return true;
        }
        ui.showInvalidIndex();
        return false;
    }

    // Method to remove an internship by index (0-based)
    public void removeInternship(int index) {

        if (isWithinBounds(index)) {
            Internship internship = internships.remove(index);
            assert internship != null : "Removed internship should not be null";

            internship.clearDeadlines();
            ui.showDeletedInternship(index + 1);
            updateIds(); // Reassign IDs after removal
        }
    }

    // Private method to update the IDs after a removal
    private void updateIds() {
        for (int i = 0; i < internships.size(); i++) {
            internships.get(i).setId(i);
        }
    }

    // Method to get an internship by index
    public Internship getInternship(int index) {
        if (isWithinBounds(index)) {
            return internships.get(index);
        }
        return null;
    }

    //@@author Ridiculouswifi
    /**
     * Updates the specified field with new values.
     *
     * @param index <code>Internship</code> index in <code>InternshipList</code>.
     * @param field Specific attribute to update.
     * @param value Updated value
     */
    public void updateField(int index, String field, String value)
            throws InvalidIndex, InvalidStatus, InvalidDeadline {
        try {
            switch (field) {
            case "status":
                internships.get(index).updateStatus(value);
                break;
            case "skills":
                internships.get(index).setSkills(value);
                break;
            case "role":
                internships.get(index).setRole(value);
                break;
            case "company":
                internships.get(index).setCompany(value);
                break;
            case "from":
                internships.get(index).setStartDate(value);
                break;
            case "to":
                internships.get(index).setEndDate(value);
                break;
            case "deadline":
                internships.get(index).updateDeadline(value);
                break;
            default:
                assert false: "All valid fields should we handled in individual cases";
                break;
            }
        } catch (IndexOutOfBoundsException e) {
            ui.showInvalidIndex();
            throw new InvalidIndex();
        }
    }

    public void removeField(int index, String field, String value) throws InvalidIndex, MissingValue {
        try {
            switch (field) {
            case "skills":
                internships.get(index).removeSkill(value);
                break;
            case "deadline":
                internships.get(index).removeDeadline(value);
                break;
            default:
                assert false: "All valid fields should we handled in individual cases";
                break;
            }
        } catch (IndexOutOfBoundsException e) {
            ui.showInvalidIndex();
            throw new InvalidIndex();
        }
    }

    //@@author jadenlimjc
    // Method to list all internships
    public void listAllInternships() {
        ui.showInternships(internships, "list");
    }

    public List<Internship> getAllInternships() {
        return Collections.unmodifiableList(internships);
    }

    public int getSize() {
        return internships.size();
    }

    //@@author Toby-Yu
    // Method to list all internships in sorted order by role (case-insensitive)
    public void listInternshipsSortedByRole() {
        ArrayList<Internship> sortedList = new ArrayList<>(internships);

        // Sort roles alphabetically, ignoring case sensitivity
        Collections.sort(sortedList, Comparator.comparing(internship -> internship.getRole().toLowerCase()));

        // Display the sorted list without changing IDs
        ui.showInternships(sortedList, "role");
    }

    // Method to list all internships sorted by start date (year first), then end date
    public void listInternshipsSortedByDuration() {
        ArrayList<Internship> sortedInternships = new ArrayList<>(internships);

        Collections.sort(sortedInternships, (i1, i2) -> {
            // First compare start dates (year then month)
            int startComparison = compareYearMonth(i1.getStartDate(), i2.getStartDate());
            if (startComparison != 0) {
                return startComparison;
            }
            // If start dates are equal, compare end dates (year then month)
            return compareYearMonth(i1.getEndDate(), i2.getEndDate());
        });

        // Display the sorted list without changing IDs
        ui.showInternships(sortedInternships, "duration");
    }

    // Helper method to compare YearMonth strings in "MM/yy" format (year first, then month)
    private int compareYearMonth(String date1, String date2) {
        String[] parts1 = date1.split("/");
        String[] parts2 = date2.split("/");

        int year1 = Integer.parseInt(parts1[1]);
        int year2 = Integer.parseInt(parts2[1]);
        int month1 = Integer.parseInt(parts1[0]);
        int month2 = Integer.parseInt(parts2[0]);

        if (year1 != year2) {
            return Integer.compare(year1, year2);
        }
        return Integer.compare(month1, month2);
    }


    // Method to list all internships sorted by start date (year first), then end date
    public void listInternshipsSortedByDeadline() {
        ArrayList<Internship> sortedInternships = new ArrayList<>(internships);

        sortedInternships.sort((i1, i2) -> {
            Deadline earliestDeadline1 = i1.getEarliestDeadline();
            Deadline earliestDeadline2 = i2.getEarliestDeadline();

            // Place internships with no deadlines last
            if (earliestDeadline1 == null && earliestDeadline2 == null) {
                return 0; // Both have no deadlines, so they are considered equal
            } else if (earliestDeadline1 == null) {
                return 1; // i1 has no deadline, so it goes after i2
            } else if (earliestDeadline2 == null) {
                return -1; // i2 has no deadline, so it goes after i1
            }
            return compareYearMonth(earliestDeadline1.getDate(), earliestDeadline2.getDate());
        });
        ui.showInternships(sortedInternships, "deadline");
    }

    /**
     * Lists internships sorted by the first skill alphabetically (case-insensitive).
     */
    public void listInternshipsSortedByFirstSkill() {
        ArrayList<Internship> sortedList = new ArrayList<>(internships);

        // Sort by the first skill alphabetically
        Collections.sort(sortedList, Comparator.comparing(internship -> {
            String firstSkill = internship.getFirstSkill();
            return firstSkill.isEmpty() ? "No skills" : firstSkill.toLowerCase();// Sort internships with no skills last
        }));

        // Display the sorted list without changing IDs
        ui.showInternships(sortedList, "skills");
    }

    /**
     * Lists internships sorted by status alphabetically (case-insensitive).
     */
    public void listInternshipsSortedByStatus() {
        ArrayList<Internship> sortedList = new ArrayList<>(internships);

        // Sort by status alphabetically
        Collections.sort(sortedList, Comparator.comparing(internship -> internship.getStatus().toLowerCase()));

        // Display the sorted list without changing IDs
        ui.showInternships(sortedList, "status");
    }

    public void listInternshipsNotSorted() {
        ui.showInternships(internships, "none");
    }

    public void listInternshipsInvalidFlag(String flag) {
        ui.showInternships(internships, flag);
    }

}
