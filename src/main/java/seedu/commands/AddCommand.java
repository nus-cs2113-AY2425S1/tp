package seedu.commands;

import seedu.EasInternship.Internship;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;

//@@author jadenlimjc
/**
* The AddCommand class handles the addition of new internships to the internship list.
* It parses the arguments provided by the user, validates the input (role, company, start date, and end date),
* and then adds the new internship to the list if the input is valid.
* If any input is invalid, appropriate error messages are shown to the user.
* The command also logs the action for debugging purposes.
*
* Usage:
* -role {Role name} -company {Company name} -from {start date} -to {end date}
*
* The date format for 'from' and 'to' is MM/yy.
*/
public class AddCommand extends Command {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
    @Override
    public void execute(ArrayList<String> args) {
        String role = "";
        String company = "";
        String startDate = "01/01";
        String endDate = "01/01";
        boolean hasRole = false;
        boolean hasCompany = false;

        uiCommand.clearInvalidFlags();
        uiCommand.clearUpdatedFields();
        uiCommand.clearInvalidFields();
        for (String arg : args) {
            String[] words = arg.split(" ", 2);
            String flag = words[0];
            switch (flag) {
            case "role":
                hasRole = true;
                if (words.length > 1) {
                    role = words[INDEX_DATA];
                } else {
                    uiCommand.addInvalidFlag(flag);
                }
                break;
            case "company":
                hasCompany = true;
                if (words.length > 1) {
                    company = words[INDEX_DATA];
                } else {
                    uiCommand.addInvalidFlag(flag);
                }
                break;
            case "from":
                if (words.length == 1) {
                    uiCommand.addInvalidFlag(flag);
                    break;
                }
                startDate = words[INDEX_DATA];
                try {
                    YearMonth.parse(startDate, formatter);
                } catch (DateTimeParseException ex) {
                    uiCommand.showOutput(startDate + " is not a valid date\nPlease enter a date in the MM/yy format");
                    return;
                }
                break;
            case "to":
                if (words.length == 1) {
                    uiCommand.addInvalidFlag(flag);
                    break;
                }
                endDate = words[INDEX_DATA];
                try {
                    YearMonth.parse(endDate, formatter);
                } catch (DateTimeParseException ex) {
                    uiCommand.showOutput(endDate + " is not a valid date\nPlease enter a date in the MM/yy format");
                    return;
                }
                break;
            default:
                uiCommand.addInvalidFlag(flag);
                break;
            }

        }

        if (!hasRole) {
            uiCommand.addInvalidFlag("role");
        }

        if (!hasCompany) {
            uiCommand.addInvalidFlag("company");
        }
        if (!uiCommand.getInvalidFlags().isEmpty()) {
            uiCommand.printInvalidFlags();
            return;
        }

        Internship newInternship = new Internship(role, company, startDate, endDate);
        internshipsList.addInternship(newInternship);
        uiCommand.showEditedInternship(newInternship, "add");

        LOGGER.log(Level.INFO, "AddCommand Executed");
    }

    @Override
    public String getUsage() {
        return """
                add
                Usage: add -role {Role name} -company {Company name} -from {start date} -to {end date}""";
    }
}
