package seedu.ui;

import seedu.commands.Command;
import seedu.duke.Internship;

import java.util.ArrayList;

//@@author Ridiculouswifi
/**
 * Subclass of <code>Ui</code> to print outputs from <code>Command</code> subclasses
 */
public class UiCommand extends Ui {
    private String invalidFlags;
    private String updatedFields;
    private String invalidFields;

    /**
     * Prints newly added or updated internship and invalid flags (if any).
     */
    public void showEditedInternship(Internship internship, String action) {
        String message = "";
        if (!getInvalidFlags().isEmpty()) {
            message += getInvalidFlags() + DIVIDER;
        }
        if (!getInvalidFields().isEmpty()) {
            message += getInvalidFields() + DIVIDER;
        }
        switch (action) {
        case "add":
            message += "Internship added:\n" + internship.toString();
            break;
        case "update":
            if (getUpdatedFields().isEmpty()) {
                message += "No Fields Updated";
            } else {
                message += getUpdatedFields() + DIVIDER + "Internship updated:\n" + internship.toString();
            }
            break;
        default:
            assert false: "All available actions should be covered in individual cases";
            break;
        }
        showOutput(message);
    }

    /**
     * Resets <code>invalidFlags</code> to empty <code>String</code>.
     */
    public void clearInvalidFlags() {
        setInvalidFlags("");
    }

    /**
     * Resets <code>updatedFields</code> to empty <code>String</code>.
     */
    public void clearUpdatedFields() {
        setUpdatedFields("");
    }

    /**
     * Resets <code>invalidFields</code> to empty <code>String</code>.
     */
    public void clearInvalidFields() {
        setInvalidFields("");
    }

    public void addUpdatedField(String updatedField, String updatedValue, String type) {
        String newUpdatedFields = getUpdatedFields();
        newUpdatedFields += updatedField;
        switch (type) {
        case "update":
            newUpdatedFields += " updated: ";
            break;
        case "remove":
            newUpdatedFields += " removed: ";
            break;
        default:
            assert false: "All valid types should be handled in individual switch cases.";
            break;
        }
        newUpdatedFields += updatedValue + "\n";
        setUpdatedFields(newUpdatedFields);
    }

    public void addCreatedField(String createdField, String createdValue) {
        String newCreatedFields = getUpdatedFields();
        newCreatedFields += createdField + " created: " + createdValue + "\n";
        setUpdatedFields(newCreatedFields);
    }

    /**
     * Adds appropriate message to <code>invalidFlags</code> for specified flag.
     */
    public void addInvalidFlag(String flag) {
        String newInvalidFlags = getInvalidFlags();
        switch (flag) {
        case "role":
            newInvalidFlags += "Role not specified." + "\n";
            break;
        case "company":
            newInvalidFlags += "Company not specified." + "\n";
            break;
        case "from":
            newInvalidFlags += "Start date not specified." + "\n";
            break;
        case "to":
            newInvalidFlags += "End date not specified." + "\n";
            break;
        case "deadline":
            newInvalidFlags += "Deadline description flag not specified." + "\n";
            break;
        case "date":
            newInvalidFlags += "Deadline date flag not specified." + "\n";
            break;
        default:
            newInvalidFlags += "Unknown flag: " + flag + "\n";
            break;
        }
        setInvalidFlags(newInvalidFlags);
    }

    /**
     * Adds appropriate message to <code>invalidFields</code> for specified field.
     */
    public void addInvalidField(String field, String invalidMessage) {
        String newInvalidFields = getInvalidFields();
        newInvalidFields += field + ": " + invalidMessage + "\n";
        setInvalidFields(newInvalidFields);
    }

    /**
     * Prints invalid flags of a command.
     */
    public void printInvalidFlags() {
        printHeadDivider();
        System.out.print(getInvalidFlags());
        printTailDivider();
    }


    /**
     * Prints message to show no flags available to filter.
     */
    public void showInsufficientArguments() {
        showOutput("Insufficient arguments! Please include a flag to filter by.");
    }

    //@@author Toby-Yu
    /**
     * Prints message indicating how internships are sorted.
     *
     * @param field Criteria that internships are sorted by.
     */
    public void showSortedInternships(String field) {
        printHeadDivider();
        switch (field) {
        case "none":
            System.out.println("No sorting option provided. Listing internships by ID.");
            break;
        case "alphabet":
            System.out.println("Sorted internships by role alphabetically (case-insensitive).");
            break;
        case "deadline":
            System.out.println("Sorted internships by start date (year first), then end date.");
            break;
        default:
            // Handling invalid sorting options
            System.out.println("Error: Unknown or invalid sorting option: \"" + field + "\".");
            System.out.println(getSortUsageMessage());
            break;
        }
        printTailDivider();
    }

    /**
     * Prints the correct usage message for the sort command.
     */
    public String getSortUsageMessage() {
        return "Usage: sort [alphabet | deadline]\n" +
                "alphabet: Sort internships alphabetically by role (case-insensitive).\n" +
                "deadline: Sort internships by start date (year first), then end date.";
    }

    //@@author Ridiculouswifi
    public void showCommands(ArrayList<Command> commands) {
        printHeadDivider();
        for (Command command : commands) {
            System.out.println(command.getUsage());
            printDivider();
        }
        System.out.println("""
                exit
                Usage: exit""");
        printTailDivider();
    }

    public String getInvalidFlags() {
        return invalidFlags;
    }

    public void setInvalidFlags(String invalidFlags) {
        this.invalidFlags = invalidFlags;
    }

    public String getUpdatedFields() {
        return updatedFields;
    }

    public void setUpdatedFields(String updatedFields) {
        this.updatedFields = updatedFields;
    }

    public String getInvalidFields() {
        return invalidFields;
    }

    public void setInvalidFields(String invalidFields) {
        this.invalidFields = invalidFields;
    }



}
