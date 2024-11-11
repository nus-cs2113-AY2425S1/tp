package seedu.ui;

import seedu.commands.Command;
import seedu.easinternship.Deadline;
import seedu.easinternship.Internship;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

//@@author Ridiculouswifi
/**
 * Subclass of <code>Ui</code> to print outputs from <code>Command</code> subclasses
 */
public class UiCommand extends Ui {
    private static final DateTimeFormatter FORMATTER_DATE = DateTimeFormatter.ofPattern("dd/MM/yy");
    private static final String POINTER_TO_NOW = "--> Today";

    private String invalidFlags;
    private String updatedFields;
    private String invalidFields;

    public UiCommand() {
        super();
        clearInvalidFields();
        clearUpdatedFields();
        clearInvalidFlags();
    }
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

    /**
     * Adds appropriate message to <code>updatedFields</code> for a specified field.
     *
     * @param updatedField  Field to update.
     * @param updatedValue  Value to update the field with.
     * @param type          Whether the value was updated or removed.
     */
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

    //@@author Ridiculouswifi
    /**
     * Prints out the deadlines in order of date.
     * @param deadlines     Sorted list of deadlines.
     * @param companies     List of companies that correspond to the deadlines
     */
    public void showCalendar(ArrayList<Deadline> deadlines, ArrayList<String> companies) {
        printHeadDivider();
        LocalDate present = LocalDate.now();
        boolean isPresentPrinted = false;

        LocalDate currentDate = LocalDate.parse("01/01/00", FORMATTER_DATE);
        if (deadlines.isEmpty()) {
            System.out.println("No deadlines listed");
            printTailDivider();
            return;
        }

        System.out.println("Deadlines:");

        for (int i = 0; i < deadlines.size(); i++) {
            LocalDate date = deadlines.get(i).getUnformattedDate();
            if (!isPresentPrinted && date.isAfter(present)) {
                isPresentPrinted = true;
                printDate(present, POINTER_TO_NOW);
            }
            String pointer = "";
            if (!isPresentPrinted && date.isEqual(present)) {
                isPresentPrinted = true;
                pointer = POINTER_TO_NOW;
            }
            if (!date.isEqual(currentDate)) {
                currentDate = date;
                printDate(date, pointer);
            }
            System.out.println("\t" + deadlines.get(i).getInternshipId()
                    + " (" + companies.get(i) + "): " + deadlines.get(i).getDescription());
        }

        if (!isPresentPrinted) {
            printDate(present, POINTER_TO_NOW);
        }

        printTailDivider();
    }

    private void printDate(LocalDate date, String pointer) {
        System.out.println();
        System.out.println(date.format(FORMATTER_DATE) + " " + pointer);
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
