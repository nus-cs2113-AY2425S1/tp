package seedu.type;

import seedu.classes.Ui;
import seedu.commands.CommandUtils;
import seedu.exception.WiagiInvalidInputException;
import seedu.recurrence.RecurrenceFrequency;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static seedu.classes.Constants.RESTRICT_CHARACTER;
import static seedu.classes.Constants.DATE_NOT_ENCLOSED;
import static seedu.classes.Constants.INVALID_DESCRIPTION_CHARACTERS_IN_EDIT;
import static seedu.classes.Constants.INVALID_FREQUENCY;
import static seedu.classes.Constants.INVALID_TAG_CHARACTERS;
import static seedu.classes.Constants.LIST_SEPARATOR;
import static seedu.classes.Constants.RECURRENCE_IDENTIFIER;
import static seedu.classes.Constants.TAG_IDENTIFIER;
import static seedu.classes.Constants.DATE_IDENTIFIER;
import static seedu.classes.Constants.ADD_COMMAND_FORMAT;
import static seedu.classes.Constants.DAILY_RECURRENCE;
import static seedu.classes.Constants.MONTHLY_RECURRENCE;
import static seedu.classes.Constants.RECURRENCE_NOT_ENCLOSED;
import static seedu.classes.Constants.TAG_NOT_ENCLOSED;
import static seedu.classes.Constants.TODAY;
import static seedu.classes.Constants.YEARLY_RECURRENCE;
import static seedu.classes.Constants.INVALID_DATE_FORMAT;
import static seedu.classes.Constants.EDIT_COMMAND_FORMAT;
import static seedu.commands.CommandUtils.checkDateLimit;


/**
 * Represents an entry with details such as amount, description, date, tag, and recurrence frequency.
 * Provides functionality to create, edit, and retrieve details of an entry.
 */
public class EntryType {
    public static final int MAX_ENTRY_DATE_DECREMENT = 100;
    private double amount;
    private String description;
    private LocalDate date;
    private String tag;
    private RecurrenceFrequency recurrenceFrequency;
    private LocalDate lastRecurrence;
    private int dayOfRecurrence;

    //@@author wongwh2002
    public EntryType(String optionalArguments, double amount, String description) {
        this.amount = amount;
        assert amount > 0 : "Amount should be greater than zero";
        this.description = description;
        assert description != null && !description.isEmpty() : "Description should not be null or empty";
        this.date = extractDate(optionalArguments);
        assert date != null : "Date should not be null";
        this.tag = extractTag(optionalArguments);
        assert tag != null : "Tag should not be null";
        this.recurrenceFrequency = extractRecurrenceFrequency(optionalArguments);
        this.lastRecurrence = this.date;
        this.dayOfRecurrence = lastRecurrence.getDayOfMonth();
        Ui.printWithTab("Entry successfully added!");
    }

    public EntryType(EntryType other) {
        this.amount = other.amount;
        this.description = other.description;
        this.date = other.date;
        this.tag = other.tag;
        this.recurrenceFrequency = RecurrenceFrequency.NONE;
        this.lastRecurrence = null;
        this.dayOfRecurrence = other.dayOfRecurrence;
    }

    //@@author rharwo
    public EntryType(double amount, String description, LocalDate date,
                     String tag, RecurrenceFrequency recurrenceFrequency,
                     LocalDate lastRecurrence, int dayOfRecurrence) {
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.tag = tag;
        this.recurrenceFrequency = recurrenceFrequency;
        this.lastRecurrence = lastRecurrence;
        this.dayOfRecurrence = dayOfRecurrence;
    }

    private String extractTag(String optionalArguments) {
        String[] commandAndTag = optionalArguments.split(TAG_IDENTIFIER);
        switch (commandAndTag.length) {
        case 1:
            return "";
        case 2:
            throw new WiagiInvalidInputException(TAG_NOT_ENCLOSED + ADD_COMMAND_FORMAT);
        default:
            return commandAndTag[1].trim();
        }
    }

    public double getAmount() {
        return this.amount;
    }

    private LocalDate extractDate(String optionalArguments) throws WiagiInvalidInputException {
        String[] commandAndDate = optionalArguments.split(DATE_IDENTIFIER);
        switch (commandAndDate.length) {
        case 1:
            return TODAY;
        case 2:
            throw new WiagiInvalidInputException(DATE_NOT_ENCLOSED + ADD_COMMAND_FORMAT);
        default:
            try {
                LocalDate date = LocalDate.parse(commandAndDate[1].trim());
                checkDateLimit(date);
                return date;
            } catch (DateTimeParseException e) {
                throw new WiagiInvalidInputException(INVALID_DATE_FORMAT + ADD_COMMAND_FORMAT);
            }
        }
    }

    private RecurrenceFrequency extractRecurrenceFrequency(String optionalArguments)
            throws WiagiInvalidInputException {
        String[] commandAndFrequency = optionalArguments.split(RECURRENCE_IDENTIFIER);
        String frequency;
        switch (commandAndFrequency.length) {
        case 1:
            return RecurrenceFrequency.NONE;
        case 2:
            throw new WiagiInvalidInputException(RECURRENCE_NOT_ENCLOSED + ADD_COMMAND_FORMAT);
        default:
            frequency = commandAndFrequency[1].trim().toLowerCase();
        }

        switch (frequency) {
        case DAILY_RECURRENCE:
            return RecurrenceFrequency.DAILY;
        case MONTHLY_RECURRENCE:
            return RecurrenceFrequency.MONTHLY;
        case YEARLY_RECURRENCE:
            return RecurrenceFrequency.YEARLY;
        default:
            throw new WiagiInvalidInputException(INVALID_FREQUENCY + ADD_COMMAND_FORMAT);
        }
    }

    @Override
    public String toString() {
        String amountString = (amount % 1 == 0) ? String.valueOf((int) amount) : String.format("%.02f", amount);
        String returnString = description + LIST_SEPARATOR + amountString + LIST_SEPARATOR + date;
        if (!tag.isEmpty()) {
            returnString += LIST_SEPARATOR + "Tag: " + tag;
        }
        if (recurrenceFrequency != RecurrenceFrequency.NONE && recurrenceFrequency != null) {
            returnString += LIST_SEPARATOR + "Recurring: " + recurrenceFrequency;
        }
        return returnString;
    }

    public void editAmount(String newAmount) throws WiagiInvalidInputException{
        this.amount = CommandUtils.formatAmount(newAmount, EDIT_COMMAND_FORMAT);
    }

    public void editDescription(String newDescription){
        if (newDescription.matches(RESTRICT_CHARACTER)) {
            throw new WiagiInvalidInputException(INVALID_DESCRIPTION_CHARACTERS_IN_EDIT);
        }
        this.description = newDescription;
    }

    public void editDate(String newDate) throws WiagiInvalidInputException{
        LocalDate date = CommandUtils.formatDate(newDate, EDIT_COMMAND_FORMAT);
        checkDateLimit(date);
        this.date = date;
    }

    public void editDateWithLocalDate(LocalDate date) {
        this.date = date;
    }

    public void editLastRecurrence(LocalDate date) {
        this.lastRecurrence = date;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getDescription() {
        return this.description;
    }

    public void editTag(String newTag) {
        if (newTag.matches(RESTRICT_CHARACTER)) {
            throw new WiagiInvalidInputException(INVALID_TAG_CHARACTERS);
        }
        this.tag = newTag;
    }

    public String getTag() {
        return this.tag;
    }

    public LocalDate getLastRecurrence() {
        return this.lastRecurrence;
    }

    public RecurrenceFrequency getRecurrenceFrequency() {
        return recurrenceFrequency;
    }

    public int getDayOfRecurrence() {
        return dayOfRecurrence;
    }
}
