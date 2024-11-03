package seedu.ui;

import seedu.duke.Internship;

import java.util.ArrayList;

//@@author Ridiculouswifi
/**
 * Subclass of <code>Ui</code> to print outputs from <code>InternshipList</code>.
 */
public class UiInternshipList extends Ui {
    /**
     * Prints out all internships in a specified <code>ArrayList</code>.
     */
    public void showInternships(ArrayList<Internship> internships, String field) {
        printHeadDivider();
        String message = getSortField(field);
        if (!message.isEmpty()) {
            System.out.println(message);
            printDivider();
        }
        if (internships.isEmpty()) {
            System.out.println("No internships found.");
            printTailDivider();
            return;
        }
        for (Internship internship : internships) {
            System.out.println(internship.toString());
            printDivider();
        }
        printDivider();
        System.out.println("\n");
    }

    //@@author Toby-Yu
    /**
     * Returns message indicating how internships are sorted.
     *
     * @param field Criteria that internships are sorted by.
     */
    public String getSortField(String field) {
        String message = "Sorted internships by ";
        switch (field) {
        case "list":
            return "";
        case "none":
            return "No sorting option provided. Listing internships by ID.";
        case "role":
            return message + "role alphabetically (case-insensitive).";
        case "duration":
            return message + "start date (year first), then end date.";
        case "deadline":
            return message + "deadline.";
        case "skills":
            return message + "skills.";
        case "status":
            return message + "status.";
        default:
            // Handling invalid sorting options
            return "Unknown flag: " + field + "\n Use \"help\" to view valid flags.";
        }
    }

    //author Ridiculouswifi
    /**
     * Prints message when index provided is out of bounds.
     */
    public void showInvalidIndex() {
        showOutput("Invalid index!\nPlease use list to find internship ID");
    }

    /**
     * Prints message to show internship of specified id has been deleted from <code>InternshipList</code>.
     */
    public void showDeletedInternship(int id) {
        showOutput("Internship deleted: " + id);
    }
}
