import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Internship {
    private String id;
    private String role;
    private String company;
    private YearMonth startDate;
    private YearMonth endDate;
    private String skills;
    private String status;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");

    public Internship (String role, String company, String start, String end, String skills, String status) {
        this.role = role;
        this.company = company;
        this.startDate = (start == null || start.isEmpty()) ? YearMonth.parse("01/00", formatter) : YearMonth.parse(start, formatter);
        this.endDate = (end == null || end.isEmpty()) ? YearMonth.parse("01/00", formatter) : YearMonth.parse(end, formatter);
        this.skills = (skills == null || skills.isEmpty()) ? "" : skills;
        this.status = (status == null || status.isEmpty()) ? "Application Pending" : status;
    }

    // Set ID based on the index of the internship in the list
    public void setId(int index) {
        this.id = String.format("%02d", index); // Format the index as 2-digit ID
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
        this.skills = skills.equals("") ? "Not Stated" : skills;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status.equals("") ? "Pending" : status;
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
