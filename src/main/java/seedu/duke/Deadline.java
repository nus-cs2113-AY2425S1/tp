package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


//@@author jadenlimjc
public class Deadline {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy");

    private int internshipID;
    private String description;
    private LocalDate date;

    public Deadline(int internshipID, String description, String date) {
        this.internshipID = internshipID;
        this.description = description;
        setDate(date);
    }

    public int getInternshipId() {
        return internshipID;
    }

    public void setInternshipId(int internshipId) {
        this.internshipID = internshipId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) throws DateTimeParseException {
        this.date = LocalDate.parse(date, DATE_FORMATTER);
    }

    public String getDate() {
        return date.format(DATE_FORMATTER);
    }

    public LocalDate getUnformattedDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return description + ": " + getDate();
    }

    public String toStringMessage() {
        return description + " (" + getDate() + ")";
    }
}
