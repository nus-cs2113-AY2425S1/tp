package customer;

import exceptions.CustomerException;
import java.util.regex.Pattern;

/**
 * Represents customers of CliRental
 */
public class Customer {

    public static final int NUMBER_OF_PARAMETERS = 3;
    private static final Pattern VALID_NAME_PATTERN = Pattern.compile("^[a-zA-Z ]+$");
    private static final Pattern VALID_CONTACT_PATTERN = Pattern.compile("^[89]\\d{7}$");
    // 8 digits, starts with 8 or 9

    private String customerName;
    private int age;
    private String contactNumber;

    public Customer(String customerName, int age, String contactNumber) {
        // Validate name format
        if (isInvalidName(customerName)) {
            throw CustomerException.invalidCustomerNameException(customerName);
        }
        // Validate age
        if(age <= 17){
            throw CustomerException.invalidAgeException();
        }else if(age > 100){
            throw CustomerException.invalidMaxAgeException();
        }

        // Validate contact number format
        if (isInvalidContactNumber(contactNumber)) {
            throw CustomerException.invalidContactNumberException();
        }

        this.customerName = customerName;
        this.age = age;
        this.contactNumber = contactNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public int getAge() {
        return age;
    }

    public void setCustomerName(String customerName) {
        if (isInvalidName(customerName)) {
            throw CustomerException.invalidCustomerNameException(customerName);
        }
        this.customerName = customerName;
    }

    public void setAge(int age) {
        if(age <= 17){
            throw CustomerException.invalidAgeException();
        }else if(age > 100){
            throw CustomerException.invalidMaxAgeException();
        }

        this.age = age;
    }

    public void setContactNumber(String contactNumber) {
        if (isInvalidContactNumber(contactNumber)) {
            throw CustomerException.invalidContactNumberException();
        }
        this.contactNumber = contactNumber;
    }

    public String toString() {
        return this.getCustomerName() + " | "
                + this.getAge() + " | "
                + this.getContactNumber();
    }

    public String toFileString() {
        return this.getCustomerName() + " | " + this.getAge() + " | " + this.getContactNumber();
    }

    public String toDisplayString() {
        return "Customer name: " + getCustomerName() + "\nAge: " + getAge() + "\nContact Number: "
                + getContactNumber();
    }

    private static boolean isInvalidName(String name) {
        return !VALID_NAME_PATTERN.matcher(name).matches();
    }

    private static boolean isInvalidContactNumber(String contactNumber) {
        return !VALID_CONTACT_PATTERN.matcher(contactNumber).matches();
    }
}
