package customer;

import exceptions.CustomerException;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class CustomerList {

    public static ArrayList<Customer> customers = new ArrayList<>();

    // Regex pattern to allow only alphabetic characters and spaces
    private static final Pattern VALID_NAME_PATTERN = Pattern.compile("^[a-zA-Z ]+$");

    public static void addCustomer(Customer customer) {
        String customerName = customer.getCustomerName();

        // Check for valid characters in the name
        if (isInvalidName(customerName)) {
            throw CustomerException.invalidCustomerNameException(customerName);
        }

        // Check for duplicate customer name (case-insensitive)
        if (isExistingCustomer(customerName)) {
            throw CustomerException.duplicateCustomerNameException(customerName);
        }

        customers.add(customer);
        System.out.println("Customer added");
        System.out.println(customer.toDisplayString());
    }

    public static ArrayList<Customer> getCustomerList() {
        return customers;
    }

    public static void addCustomerWithoutPrintingInfo(Customer customer) {
        String customerName = customer.getCustomerName();

        // Check for valid characters in the name
        if (isInvalidName(customerName)) {
            throw CustomerException.invalidCustomerNameException(customerName);
        }

        // Check for duplicate customer name (case-insensitive)
        if (isExistingCustomer(customerName)) {
            throw CustomerException.duplicateCustomerNameException(customerName);
        }

        customers.add(customer);
    }

    public static void removeCustomer(String customerName) {
        if (customers.isEmpty()) {
            System.out.println("Customer list is empty. Nothing to remove");
            return;
        }

        boolean customerFound = false;

        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            if (customer.getCustomerName().equalsIgnoreCase(customerName)) {
                customers.remove(i);
                System.out.println("Customer Name: " + customer.getCustomerName()
                        + " has been removed from the customer list");
                customerFound = true;
                break;
            }
        }

        if (!customerFound) {
            System.out.println(customerName + " is not in the customer list. No removal done");
        }
    }


    public static void clearCustomerList() {
        customers.clear();
    }

    public static void removeAllCustomers() {
        clearCustomerList();
        System.out.println("All customers removed!!!");
    }

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    public static void printCustomers() {
        if (CustomerList.getCustomers().isEmpty()) {
            System.out.println("Customer list is empty.");
        } else {
            System.out.println("Here are all the customers:");
            for (int i = 0; i < customers.size(); i++) {
                System.out.print((i + 1) + ") ");
                Customer customer = customers.get(i);
                System.out.println(customer.toString());
            }
        }
    }

    public static boolean isExistingCustomer(String customerName) {
        for (Customer customer : customers) {
            if (customer.getCustomerName().equalsIgnoreCase(customerName)) {
                return true;
            }
        }
        return false;
    }

    public static String customerListToFileString() {
        StringBuilder customerData = new StringBuilder();
        for (Customer customer : customers) {
            customerData.append(customer.toFileString());
            customerData.append("\n");
        }
        return customerData.toString();
    }

    public static String getCustomerNameIfExist(String inputName) {
        for (Customer customer : customers) {
            String customerName = customer.getCustomerName();
            if (customer.getCustomerName().equalsIgnoreCase(inputName)) {
                return customerName;
            }
        }
        return inputName;
    }

    // Helper method to validate the customer's name
    private static boolean isInvalidName(String name) {
        return !VALID_NAME_PATTERN.matcher(name).matches();
    }
}

