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
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author jadenlimjc
public class InternshipList {
    private static final UiInternshipList ui = new UiInternshipList();
    private static final Logger LOGGER = Logger.getLogger("EasInternship");
    public ArrayList<Internship> internships;
    public ArrayList<Internship> favouriteInternships;

    // Constructor
    public InternshipList() {
        internships = new ArrayList<>();
        favouriteInternships = new ArrayList<>();
    }

    public InternshipList(ArrayList<Internship> internships) {
        this.internships = internships;
        favouriteInternships = new ArrayList<>();
    }

    public void addInternship(Internship internship) {

        assert internship != null : "Internship object cannot be null";

        if (internship.getId() == -1) {
            internship.setId(internships.size());
        }

        assert internship.getId() == internships.size() + 1;

        internships.add(internship);
        LOGGER.log(Level.INFO, "Internship added");
    }

    //@@author Ridiculouswifi
    /**
     * Returns whether the index given is within the boundaries of the list.
     */
    public boolean isWithinBounds(int index) {
        if (index < 0 || index >= internships.size()) {
            return false;
        }
        return true;
    }

    // Method to remove an internship by index (0-based)

    //@@author jadenlimjc
    public void removeInternship(int index) throws InvalidIndex {
        if (!isWithinBounds(index)) {
            throw new InvalidIndex(index);
        }
        Internship internship = internships.remove(index);
        assert internship != null : "Removed internship should not be null";

        internship.clearDeadlines();
        favouriteInternships.remove(internship);
        ui.showDeletedInternship(index + 1);
        updateIds(); // Reassign IDs after removal

        LOGGER.log(Level.INFO, "Internship removed");
    }

    // Private method to update the IDs after a removal
    private void updateIds() {
        for (int i = 0; i < internships.size(); i++) {
            internships.get(i).setId(i);
        }
    }

    //@@author Ridiculouswifi

    // Method to get an internship by index
    public Internship getInternship(int index) {
        try {
            if (!isWithinBounds(index)) {
                throw new InvalidIndex(index);
            }
            return internships.get(index);
        } catch (InvalidIndex ie) {
            ui.showOutput(ie.getMessage());
            return null;
        }
    }

    //@@author Ridiculouswifi
    /**
     * Updates the specified field with new values.
     *
     * @param index <code>Internship</code> index in <code>InternshipList</code>.
     * @param field Specific attribute to update.
     * @param value Updated value
     */
    public String updateField(int index, String field, String value) throws InvalidStatus, InvalidDeadline {
        String updatedValue = value;
        switch (field) {
        case "status":
            updatedValue = internships.get(index).updateStatus(value);
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
            updatedValue = internships.get(index).updateDeadline(value);
            break;
        default:
            assert false : "All valid fields should we handled in individual cases";
            break;
        }
        LOGGER.log(Level.INFO, "Internship " + (index + 1) + " updated: " + field);
        return updatedValue;
    }

    public void removeField(int index, String field, String value) throws MissingValue {
        switch (field) {
        case "skills":
            internships.get(index).removeSkill(value);
            break;
        case "deadline":
            internships.get(index).removeDeadline(value);
            break;
        default:
            assert false : "All valid fields should we handled in individual cases";
            break;
        }
        LOGGER.log(Level.INFO, "Internship " + (index + 1) + " removed: " + field);
    }

    //@@author jadenlimjc
    // Method to list all internships
    public void listAllInternships() {
        ui.showInternships(internships, "list");
    }

    public void listFavouriteInternshipsBySortedByID() {
        ArrayList<Internship> sortedInternships = new ArrayList<>(favouriteInternships);

        // Sort roles alphabetically, ignoring case sensitivity
        sortedInternships.sort(Comparator.comparing(Internship::getId));
        ui.showInternships(sortedInternships, "list");
    }

    public List<Internship> getAllInternships() {
        return Collections.unmodifiableList(internships);
    }

    //@@author Toby-Yu

    public int getSize() {
        return internships.size();
    }

    /**
     * List all internships in sorted order by role alphabetically (case-insensitive)
     */
    public void listInternshipsSortedByRole() {
        ArrayList<Internship> sortedInternships = new ArrayList<>(internships);

        // Sort roles alphabetically, ignoring case sensitivity
        Collections.sort(sortedInternships, Comparator.comparing(internship -> internship.getRole().toLowerCase()));
        ui.showInternships(sortedInternships, "role");
    }

    /**
     * List all internships sorted by duration (year first, then month) in start date first, then end date
     */
    public void listInternshipsSortedByDuration() {
        ArrayList<Internship> sortedInternships = new ArrayList<>(internships);

        sortedByDurationFunction(sortedInternships);
        ui.showInternships(sortedInternships, "duration");
    }

    private void sortedByDurationFunction(ArrayList<Internship> sortedInternships) {
        Collections.sort(sortedInternships, (i1, i2) -> {
            // First compare start dates (year then month)
            int startComparison = compareYearMonth(i1.getStartDate(), i2.getStartDate());
            if (startComparison != 0) {
                return startComparison;
            }
            // If start dates are equal, compare end dates (year then month)
            return compareYearMonth(i1.getEndDate(), i2.getEndDate());
        });
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

    //@@author jadenlimjc
    /**
     * List all internships sorted by the earliest deadline
     */
    public void listInternshipsSortedByDeadline() {
        ArrayList<Internship> sortedInternships = new ArrayList<>(internships);

        sortedByDeadlineFunction(sortedInternships);
        ui.showInternships(sortedInternships, "deadline");
    }

    private void sortedByDeadlineFunction(ArrayList<Internship> sortedInternships) {
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
    }

    /**
     * Lists internships sorted by the first skill alphabetically (case-insensitive).
     */
    public void listInternshipsSortedByFirstSkill() {
        ArrayList<Internship> sortedInternships = new ArrayList<>(internships);

        sortedByFirstSkillFunction(sortedInternships);
        ui.showInternships(sortedInternships, "skills");
    }

    private static void sortedByFirstSkillFunction(ArrayList<Internship> sortedList) {
        // Sort by the first skill alphabetically
        Collections.sort(sortedList, Comparator.comparing(internship -> {
            String firstSkill = internship.getFirstSkill();
            return firstSkill.isEmpty() ? "No skills" : firstSkill.toLowerCase();// Sort internships with no skills last
        }));
    }

    /**
     * Lists internships sorted by status in the following order:
     * 1. Application Pending
     * 2. Application Completed
     * 3. Accepted
     * 4. Rejected
     * If internships have the same status, they are sorted by role alphabetically.
     */
    public void listInternshipsSortedByStatus() {
        // Separate internships by status into four different lists
        ArrayList<Internship> pendingList = new ArrayList<>();
        ArrayList<Internship> completedList = new ArrayList<>();
        ArrayList<Internship> acceptedList = new ArrayList<>();
        ArrayList<Internship> rejectedList = new ArrayList<>();

        for (Internship internship : internships) {
            String status = internship.getStatus().toLowerCase();
            switch (status) {
            case "application pending":
                pendingList.add(internship);
                break;
            case "application completed":
                completedList.add(internship);
                break;
            case "accepted":
                acceptedList.add(internship);
                break;
            case "rejected":
                rejectedList.add(internship);
                break;
            default:
                // If there's an unknown status, you might want to handle it or ignore it
                logger.log(Level.WARNING, "Unknown status: " + status);
                break;
            }
        }

        // Sort each list by role alphabetically (case-insensitive)
        Comparator<Internship> roleComparator = Comparator.comparing(internship -> internship.getRole().toLowerCase());
        pendingList.sort(roleComparator);
        completedList.sort(roleComparator);
        acceptedList.sort(roleComparator);
        rejectedList.sort(roleComparator);

        // Create a final sorted list by combining the lists in the required order
        ArrayList<Internship> sortedInternships = new ArrayList<>();
        sortedInternships.addAll(pendingList);
        sortedInternships.addAll(completedList);
        sortedInternships.addAll(acceptedList);
        sortedInternships.addAll(rejectedList);

        // Display the sorted internships
        ui.showInternships(sortedInternships, "status");
    }

    /**
     * Lists internships sorted by company alphabetically (case-insensitive).
     */
    public void listInternshipsSortedByCompany() {
        ArrayList<Internship> sortedInternships = new ArrayList<>(internships);

        Collections.sort(sortedInternships, Comparator.comparing(internship -> internship.getCompany().toLowerCase()));
        ui.showInternships(sortedInternships, "company");
    }

    /**
     * List all favourite internships in sorted order by role alphabetically (case-insensitive)
     */
    public void listFavouriteInternshipsSortedByRole() {
        ArrayList<Internship> sortedInternships = new ArrayList<>(favouriteInternships);
        Collections.sort(sortedInternships, Comparator.comparing(internship -> internship.getRole().toLowerCase()));
        ui.showInternships(sortedInternships, "role in favourite");
    }

    /**
     * List all internships sorted by duration (year first), then end date
     */
    public void listFavouriteInternshipsSortedByDuration() {
        ArrayList<Internship> sortedInternships = new ArrayList<>(favouriteInternships);
        sortedByDurationFunction(sortedInternships);
        ui.showInternships(sortedInternships, "duration in favourite");
    }

    /**
     * List all favourite internships sorted by the earliest deadline
     */
    public void listFavouriteInternshipsSortedByDeadline() {
        ArrayList<Internship> sortedInternships = new ArrayList<>(favouriteInternships);

        sortedByDeadlineFunction(sortedInternships);
        ui.showInternships(sortedInternships, "deadline in favourite");
    }

    /**
     * Lists all favourite internships sorted by the first skill alphabetically (case-insensitive).
     */
    public void listFavouriteInternshipsSortedByFirstSkill() {
        ArrayList<Internship> sortedInternships = new ArrayList<>(favouriteInternships);

        sortedByFirstSkillFunction(sortedInternships);
        ui.showInternships(sortedInternships, "skills in favourite");
    }


    /**
     * Lists internships sorted by status in the following order:
     * 1. Application Pending
     * 2. Application Completed
     * 3. Accepted
     * 4. Rejected
     * If internships have the same status, they are sorted by role alphabetically.
     */
    public void listFavouriteInternshipsSortedByStatus() {
        // Separate internships by status into four different lists
        ArrayList<Internship> pendingList = new ArrayList<>();
        ArrayList<Internship> completedList = new ArrayList<>();
        ArrayList<Internship> acceptedList = new ArrayList<>();
        ArrayList<Internship> rejectedList = new ArrayList<>();

        for (Internship internship : favouriteInternships) {
            String status = internship.getStatus().toLowerCase();
            switch (status) {
            case "application pending":
                pendingList.add(internship);
                break;
            case "application completed":
                completedList.add(internship);
                break;
            case "accepted":
                acceptedList.add(internship);
                break;
            case "rejected":
                rejectedList.add(internship);
                break;
            default:
                // If there's an unknown status, you might want to handle it or ignore it
                logger.log(Level.WARNING, "Unknown status: " + status);
                break;
            }
        }

        // Sort each list by role alphabetically (case-insensitive)
        Comparator<Internship> roleComparator = Comparator.comparing(internship -> internship.getRole().toLowerCase());
        pendingList.sort(roleComparator);
        completedList.sort(roleComparator);
        acceptedList.sort(roleComparator);
        rejectedList.sort(roleComparator);

        // Create a final sorted list by combining the lists in the required order
        ArrayList<Internship> sortedInternships = new ArrayList<>();
        sortedInternships.addAll(pendingList);
        sortedInternships.addAll(completedList);
        sortedInternships.addAll(acceptedList);
        sortedInternships.addAll(rejectedList);

        // Display the sorted internships
        ui.showInternships(sortedInternships, "status in favourite");
    }

    /**
     * Lists all favourite internships sorted by company alphabetically (case-insensitive).
     */
    public void listFavouriteInternshipsSortedByCompany() {
        ArrayList<Internship> sortedList = new ArrayList<>(favouriteInternships);
        Collections.sort(sortedList, Comparator.comparing(internship -> internship.getCompany().toLowerCase()));
        ui.showInternships(sortedList, "company in favourite");
    }


    public void listInternshipsNotSorted() {
        ui.showInternships(internships, "none");
    }

    public void listInternshipsInvalidFlag(String flag) {
        ui.showInternships(internships, flag);
    }

}
