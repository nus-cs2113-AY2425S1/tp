package seedu.duke;

import seedu.exceptions.InvalidDeadline;
import seedu.exceptions.InvalidStatus;
import seedu.exceptions.MissingValue;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Class to store the relevant information for an internship.
 */
//@@author jadenlimjc
public class Internship {
    private static final DateTimeFormatter FORMATTER_MONTH_YEAR = DateTimeFormatter.ofPattern("MM/yy");
    private static final DateTimeFormatter FORMATTER_DATE = DateTimeFormatter.ofPattern("dd/MM/yy")
            .withResolverStyle(ResolverStyle.LENIENT);

    private static final List<String> STATUSES = Arrays.asList(
            "Application Pending", "Application Completed", "Accepted", "Rejected");

    private static final Logger LOGGER = Logger.getLogger("EasInternship");

    private int id = -1;
    private String role;
    private String company;
    private YearMonth startDate;
    private YearMonth endDate;
    private String favourite = "false";
    private String status;

    private ArrayList<Deadline> deadlines;
    private ArrayList<String> skills;


    /**
     * Constructs an <code>Internship</code> instance with information provided.
     * Skills and Status fields are initialised with default values.
     *
     * @param role    description of role.
     * @param company company name.
     * @param start   start year/month.
     * @param end     end year/month.
     */
    public Internship(String role, String company, String start, String end) {
        this.role = role;
        this.company = company;
        setStartDate(start);
        setEndDate(end);

        this.deadlines = new ArrayList<>();

        this.skills = new ArrayList<>();

        this.status = "Application Pending";
    }

    //@@author Ridiculouswifi
    /**
     * Updates the status of the Internship with a valid status.
     * Does not update with invalid statuses and will prompt for a valid status.
     *
     * @param userStatus user-inputted status.
     *
     * @return Updated status as reflected in the entry.
     */
    public String updateStatus(String userStatus) throws InvalidStatus {
        assert !userStatus.isEmpty() : "Status cannot be empty";

        for (String status : STATUSES) {
            if (status.equalsIgnoreCase(userStatus)) {
                this.status = status;
                return status;
            }
        }
        throw new InvalidStatus();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    /**
     * Sets ID based on the index of the internship in the list and updates all associated deadlines.
     *
     * @param index the new zero-based ID for the internship.
     */
    public void setId(int index) {
        this.id = index + 1;
        for (Deadline deadline : deadlines) {
            deadline.setInternshipId(id);
        }
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        assert role != null;
        this.role = role;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        assert company != null;
        this.company = company;
    }
    public String getStartDate() {
        return startDate.format(FORMATTER_MONTH_YEAR); // Format as MM/yy
    }

    public void setStartDate(String start) throws DateTimeParseException {
        assert start != null;
        this.startDate = YearMonth.parse(start, FORMATTER_MONTH_YEAR);
    }

    public String getEndDate() {
        return endDate.format(FORMATTER_MONTH_YEAR); // Format as MM/yy
    }

    public void setEndDate(String end) throws DateTimeParseException {
        assert end != null;
        this.endDate = YearMonth.parse(end, FORMATTER_MONTH_YEAR);
    }

    public String getFavourite() {
        return favourite;
    }

    public void setFavourite(String favourite) {
        this.favourite = favourite;
    }


    /**
     * Adds a new deadline for this internship.
     *
     * @param description description of the deadline (e.g., "Application", "Interview").
     * @param date        deadline date in MM/yy format.
     */
    public void addDeadline(String description, String date) throws DateTimeParseException{
        assert description != null && !description.isEmpty() : "Deadline cannot be null or empty";

        deadlines.add(new Deadline(getId(), description, date));
    }

    //@@ jadenlimjc
    /**
     * Removes a deadline by its description.
     *
     * @param description   description of the deadline to remove.
     * @throws MissingValue No deadline in the list of deadlines is
     */
    public void removeDeadline(String description) throws MissingValue {
        String trimmedDescription = description.trim();
        if(!deadlines.removeIf(deadline -> deadline.getDescription().equalsIgnoreCase(trimmedDescription))) {
            throw new MissingValue();
        }
    }

    /**
     * Clears all deadlines when the internship is deleted.
     */
    public void clearDeadlines() {

        deadlines.clear();
    }

    //@@author Ridiculouswifi
    /**
     * Updates the deadlines of the <code>Internship</code> class.
     * If no deadline has the same description, a new deadline entry is created.
     *
     * @param value             <code>String</code> with description and deadline.
     * @return                  Updated deadline as reflected in <code>Internship</code> entry.
     * @throws InvalidDeadline  Either description is empty or there is no parsable date.
     */
    public String updateDeadline(String value) throws InvalidDeadline {
        String[] words = value.split(" ");
        String description = "";
        String date = "";
        boolean hasFoundDate = false;

        int wordsIndex = 0;
        while (!hasFoundDate && wordsIndex < words.length) {
            String trimmedWord = words[wordsIndex].trim();
            if (isValidDate(trimmedWord)) {
                date = trimmedWord;
                hasFoundDate = true;
            } else {
                description += trimmedWord + " ";
            }
            wordsIndex++;
        }

        if (description.trim().isEmpty() || date.trim().isEmpty()) {
            LOGGER.log(Level.WARNING, "Deadline is invalid");
            throw new InvalidDeadline();
        }

        int deadlineIndex = getDeadlineIndex(description.trim());
        assert deadlineIndex >= -1 : "The index must be -1 minimally";
        if (deadlineIndex != -1) {
            deadlines.get(deadlineIndex).setDate(date);
            return deadlines.get(deadlineIndex).toStringMessage();
        }
        Deadline newDeadline = new Deadline(getId(), description.trim(), date);
        deadlines.add(newDeadline);
        return newDeadline.toStringMessage();
    }

    //@@author Ridiculouswifi
    protected boolean isValidDate(String date) {
        try {
            LocalDate.parse(date, FORMATTER_DATE);
            return true;
        } catch (DateTimeParseException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
            return false;
        }
    }

    //@@author Ridiculouswifi
    protected int getDeadlineIndex(String description) {
        for (int i = 0; i < deadlines.size(); i++) {
            if (deadlines.get(i).getDescription().equalsIgnoreCase(description)) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Deadline> getDeadlines() {
        return this.deadlines;
    }

    //@@author jadenlimjc
    public Deadline getEarliestDeadline() {
        return getDeadlines().stream()
                .min(Comparator.comparing(Deadline::getDate)).orElse(null);
    }

    //@@author jadenlimjc
    public String getFormattedDeadlines() {
        if (deadlines.isEmpty()) {
            return "No deadlines set.";
        }
        StringBuilder builder = new StringBuilder();
        for (Deadline deadline : deadlines) {
            builder.append("\t").append(deadline.toString()).append("\n");
        }
        return builder.toString().trim();
    }


    //@@author Ridiculouswifi
    /**
     * Returns all skills stored in <code>skills</code> field as a combined String.
     */
    public String getSkills() {
        String skillList = "";
        if (this.skills.isEmpty()) {
            return skillList;
        }
        for (String skill: skills) {
            skillList += ", " + skill;
        }
        int indexStart = 2;
        return skillList.trim().substring(indexStart);
    }

    //@@author Ridiculouswifi
    /**
     * Adds the inputs to the skills field.
     *
     * @param skills    List of skills, individual skills are separated by commas.
     */
    public void setSkills(String skills) {
        if (skills.trim().isEmpty()) {
            return;
        }
        String[] skillArray = skills.split(",");
        for (String skill: skillArray) {
            if (!this.skills.contains(skill.trim())) {
                this.skills.add(skill.trim());
            }
        }
    }

    //@@author Ridiculouswifi
    /**
     * Removes the input from the <code>skills</code> field.
     *
     * @throws MissingValue     skill is not found within <code>skills</code> field.
     */
    public void removeSkill(String skill) throws MissingValue {
        if (!this.skills.remove(skill.trim())) {
            throw new MissingValue();
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status.isEmpty() ? "Pending" : status;
    }

    //@@author Toby-Yu
    /**
     * Returns the first skill in the list, or an empty string if no skills are available.
     */
    public String getFirstSkill() {
        if (skills.isEmpty()) {
            return "";
        }
        return skills.get(0);
    }

    //@@author Ridiculouswifi
    // toString method for displaying the details
    @Override
    public String toString() {
        String skillsField = getSkills();
        if (skillsField.isEmpty()) {
            skillsField = "No Skills Entered";
        }
        return "ID: " + id + "\tStatus: " + status + "\n" + "Role: " + role + "\n" + "Company: " + company + "\n" +
                "Duration: " + getStartDate() + " to " + getEndDate() + "\n" + "Skills: " + skillsField + " \n" +
                "Deadlines:\n\t" + getFormattedDeadlines();
    }
}
