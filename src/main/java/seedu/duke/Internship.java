package seedu.duke;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Internship {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");

    private String id;
    private String role;
    private String company;
    private YearMonth startDate;
    private YearMonth endDate;
    private String skills;
    private String status;


    public Internship(String role, String company, YearMonth start, YearMonth end) {
        this.role = role;
        this.company = company;
        this.startDate = start;
        this.endDate = end;
        this.skills = "No Skills Entered";
        this.status = "Application Pending";
    }

    // Set ID based on the index of the internship in the list
    public void setId(int index) {
        this.id = String.format("%02d", index);
    }

    // Getters and Setters
    public String getId() {
        return id;
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

    public void setStartDate(String start) {
        this.startDate = YearMonth.parse(start, formatter);
    }

    public String getEndDate() {
        return endDate.format(formatter); // Format as MM/yy
    }

    public void setEndDate(String end) {
        this.endDate = YearMonth.parse(end, formatter);
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills.isEmpty() ? "Not Stated" : skills;
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
        return "ID: " + id + "\tStatus: " + status + "\n" +
                "Role: " + role + "\n" +
                "Company: " + company + "\n" +
                "Duration: " + getStartDate() + " to " + getEndDate() + "\n" +
                "Skills: " + skills;
    }
}
