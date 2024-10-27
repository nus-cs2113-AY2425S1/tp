package seedu.duke;

import seedu.exceptions.InvalidStatus;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class to store the relevant information for an internship.
 */
//@@author jadenlimjc
public class Internship {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");

    private int id = -1;
    private String role;
    private String company;
    private YearMonth startDate;
    private YearMonth endDate;
    private ArrayList<String> skills;
    private String status;


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
        this.skills = new ArrayList<>();
        this.status = "Application Pending";
    }

    //@@author Ridiculouswifi
    /**
     * Updates the status of the Internship with a valid status.
     * Does not update with invalid statuses and will prompt for a valid status.
     *
     * @param userStatus user-inputted status.
     */
    public void updateStatus(String userStatus) throws InvalidStatus {
        List<String> statuses = Arrays.asList("Application Pending", "Application Completed", "Accepted", "Rejected");
        for (String status : statuses) {
            if (status.equalsIgnoreCase(userStatus)) {
                this.status = status;
                return;
            }
        }
        throw new InvalidStatus();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    /**
     * Sets ID based on the index of the internship in the list.
     */
    public void setId(int index) {
        this.id = index + 1;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStartDate() {
        return startDate.format(formatter); // Format as MM/yy
    }

    public void setStartDate(String start) throws DateTimeParseException {
        this.startDate = YearMonth.parse(start, formatter);
    }

    public String getEndDate() {
        return endDate.format(formatter); // Format as MM/yy
    }

    public void setEndDate(String end) throws DateTimeParseException {
        this.endDate = YearMonth.parse(end, formatter);
    }

    //@@author Ridiculouswifi

    /**
     * Returns all skills stored in `skills` field as a combined String.
     */
    public String getSkills() {
        String skillList = "";
        if (this.skills.isEmpty()) {
            return skillList;
        }
        for (String skill: skills) {
            skillList += ", " + skill;
        }
        int INDEX_START = 2;
        return skillList.trim().substring(INDEX_START);
    }

    //@@author Ridiculouswifi
    /**
     * Adds the input to the skills field.
     *
     * @param skills    List of skills, individual skills are separated by commas.
     */
    public void setSkills(String skills) {
        if (skills.isEmpty()) {
            return;
        }
        String[] skillArray = skills.split(",");
        for (String skill: skillArray) {
            this.skills.add(skill.trim());
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status.isEmpty() ? "Pending" : status;
    }

    // toString method for displaying the details
    @Override
    public String toString() {
        String skillsField = getSkills();
        if (skillsField.isEmpty()) {
            skillsField = "No Skills Entered";
        }
        return "ID: " + id + "\tStatus: " + status + "\n" + "Role: " + role + "\n" + "Company: " + company + "\n" +
                "Duration: " + getStartDate() + " to " + getEndDate() + "\n" + "Skills: " + skillsField;
    }
}
