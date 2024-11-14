package exceptions;

import java.util.ArrayList;

/**
 * Customer related exceptions
 */
public class CustomerException extends RuntimeException {

    public static final String ADD_FORMAT = "add-user /u [CUSTOMER_NAME] /a [AGE] /c [CONTACT_NUMBER]";
    public static final String REMOVE_FORMAT = "remove-user /u [CUSTOMER_NAME]";

    public CustomerException(String message) {
        super(message);
    }

    public void printErrorMessage(){
        System.out.println(getMessage());
    }

    /**
     * Exception for the add-user command
     */
    public static CustomerException addCustomerException(){
        return new CustomerException("Unable to add customer. Please follow: " + ADD_FORMAT);
    }

    /**
     * Exception thrown if data in customerData.txt does not fit pre-determined format.
     *
     * @param errorLines List of row numbers in the customerData.txt file which the data format is wrong.
     * @return Exception with message of which the row of data which are wrong.
     */
    public static CustomerException invalidParameters(ArrayList<Integer> errorLines){
        String message = "Customer data do not match parameters requirements in "
                + errorLines.size() + " rows of data\n";
        message += "Rows are : ";
        message += errorLines.toString();
        message = message + "\n";
        return new CustomerException(message);
    }

    public static CustomerException removeCustomerException(){
        return new CustomerException("Unable to remove customer. Please follow: " + REMOVE_FORMAT);
    }

    public static CustomerException missingNameWhenRemoving(){
        return new CustomerException("Please enter customer name for removal.");
    }

    public static CustomerException customerAlreadyInTransactionList() {
        return new CustomerException("Customer has rented a car.");
    }

    public static CustomerException invalidContactNumberException(){
        return new CustomerException("Invalid contact number. Format for contact number is [8 DIGITS AND " +
                "STARTS WITH 8 OR 9]");
    }

    public static CustomerException invalidAgeException(){
        return new CustomerException("Illegal driver!! Age should be more than 17!!");
    }

    public static CustomerException invalidMaxAgeException(){
        return new CustomerException("This age is not safe to drive!! Too old!!");
    }

    /**
     * Exception thrown when the customer name already exists (case-insensitive).
     *
     * @param customerName The name of the customer that already exists.
     * @return Exception with a message specifying the duplicate name.
     */
    public static CustomerException duplicateCustomerNameException(String customerName) {
        return new CustomerException("The customer name \"" + customerName + "\" already exists. " +
                "Customer names must be unique and are case-insensitive.");
    }

    /**
     * Exception thrown when the customer name contains invalid characters.
     *
     * @param customerName The invalid name entered.
     * @return Exception with a message specifying the invalid characters.
     */
    public static CustomerException invalidCustomerNameException(String customerName) {
        return new CustomerException("The customer name \"" + customerName + "\" contains invalid characters. " +
                "Only alphabetic characters and spaces are allowed.");
    }
}
