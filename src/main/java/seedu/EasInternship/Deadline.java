package seedu.EasInternship;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;


//@@author jadenlimjc
/**
* Represents a deadline associated with an internship, with a description and a specific due date.
* The date is stored in the format "dd/MM/yy", and the class ensures that the date is parsed and validated
* strictly according to the given pattern. The deadline also contains an internship ID
* to link it to a specific internship.
*/
public class Deadline {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/uu")
            .withResolverStyle(ResolverStyle.STRICT);

    private int internshipID;
    private String description;
    private LocalDate date;

    /**
    * Constructs a new Deadline object with the specified internship ID, description, and deadline date.
    * The date is parsed and validated according to the format "dd/MM/yy".
    *
    * @param internshipID The ID of the internship associated with the deadline.
    * @param description A brief description of the deadline.
    * @param date The deadline date in the "dd/MM/yy" format.
    * @throws DateTimeParseException if the provided date string does not match the expected format.
    */
    public Deadline(int internshipID, String description, String date) {
        this.internshipID = internshipID;
        this.description = description;
        setDate(date);
    }

    /**
    * Gets the internship ID associated with the deadline.
    *
    * @return The internship ID.
    */
    public int getInternshipId() {
        return internshipID;
    }

    /**
    * Sets the internship ID associated with the deadline.
    *
    * @param internshipId The new internship ID.
    */
    public void setInternshipId(int internshipId) {
        this.internshipID = internshipId;
    }

    /**
    * Gets the description of the deadline.
    *
    * @return The description of the deadline.
    */
    public String getDescription() {
        return description;
    }

    /**
    * Sets the description of the deadline.
    *
    * @param description The new description of the deadline.
    */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
    * Sets the deadline date using a string. The date is parsed and validated according to the format "dd/MM/yy".
    *
    * @param date The deadline date in the "dd/MM/yy" format.
    * @throws DateTimeParseException if the provided date string does not match the expected format.
    */
    public void setDate(String date) throws DateTimeParseException {
        assert date != null;
        this.date = LocalDate.parse(date, DATE_FORMATTER);
    }

    /**
    * Gets the deadline date as a string in the "dd/MM/yy" format.
    *
    * @return The deadline date formatted as a string.
    */
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
