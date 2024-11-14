package transaction;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static parser.TransactionParser.dateTimeFormatter;

/**
 * Represents a transaction associated with a car rental.
 */
public class Transaction {
    public static final int NUMBER_OF_PARAMETERS = 6;
    private String transactionId;
    private final String carLicensePlate;
    private final String customer;
    private final int duration;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private boolean isCompleted;

    /**
     * Constructor for creating a new transaction.
     *
     * @param carLicensePlate the license plate of the car
     * @param customer the customer associated with the transaction
     * @param duration the rental duration in days
     * @param startDate the start date of the rental period
     */
    public Transaction(String carLicensePlate, String customer, int duration, LocalDate startDate) {
        this(null, carLicensePlate, customer, duration, startDate, false);
    }

    /**
     * Constructor for loading an existing transaction.
     *
     * @param transactionId the unique identifier for the transaction
     * @param carLicensePlate the license plate of the car
     * @param customer the customer associated with the transaction
     * @param duration the rental duration in days
     * @param startDate the start date of the rental period
     * @param isCompleted the completion status of the transaction
     */
    public Transaction(String transactionId, String carLicensePlate, String customer, int duration,
                       LocalDate startDate, boolean isCompleted) {
        this.transactionId = transactionId;
        this.carLicensePlate = carLicensePlate;
        this.customer = customer;
        this.duration = duration;
        this.startDate = startDate;
        this.endDate = startDate.plusDays(duration);
        this.isCompleted = isCompleted;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getDuration() {
        return duration;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getCustomer() {
        return customer;
    }

    public String getCarLicensePlate() {
        return carLicensePlate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    /**
     * Converts the duration to a human-readable string.
     *
     * @return formatted duration as a string
     */
    private String formatDuration() {
        return duration == 1 ? "1 day" : duration + " days";
    }

    /**
     * Validates the format of a transaction ID.
     *
     * @param transactionId the transaction ID to validate
     * @return true if the transaction ID is valid, false otherwise
     */
    public static boolean isValidTxId(String transactionId) {
        String regex = "TX([1-9]\\d*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(transactionId);
        return matcher.matches();
    }

    /**
     * Provides a string representation of the transaction.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        String formattedStartDate = startDate.format(dateTimeFormatter);
        String formattedEndDate = endDate.format(dateTimeFormatter);
        String status = isCompleted ? "[X]" : "[ ]";

        return String.format("%s %s | %s | %s | %s%nStart Date: %s | End Date: %s",
                status, transactionId, carLicensePlate, customer, formatDuration(),
                formattedStartDate, formattedEndDate);
    }

    /**
     * Returns a string representation of the transaction formatted for file storage.
     *
     * @return formatted string for file storage
     */
    public String toFileString() {
        return String.format("%s | %s | %s | %d | %s | %b",
                transactionId, carLicensePlate, customer, duration,
                startDate.format(dateTimeFormatter), isCompleted);
    }
}
