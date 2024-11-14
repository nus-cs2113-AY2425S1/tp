package parser;

import car.Car;
import car.CarList;
import customer.Customer;
import customer.CustomerList;
import transaction.Transaction;
import exceptions.CliRentalException;
import transaction.TransactionList;

import java.util.Scanner;

public class Parser {

    public static Scanner scanner = new Scanner(System.in);
    public static final String ADD_TRANSACTION_COMMAND = "add-tx";
    private static final String HELP_COMMAND = "help";
    private static final String ADD_CUSTOMER_COMMAND = "add-user";
    private static final String REMOVE_CUSTOMER_COMMAND = "remove-user";
    private static final String LIST_USERS_COMMAND = "list-users";
    private static final String REMOVE_ALL_CUSTOMERS_COMMAND = "remove-all-users";
    private static final String ADD_CAR_COMMAND = "add-car";
    private static final String REMOVE_CAR_COMMAND = "remove-car";
    private static final String REMOVE_ALL_CARS_COMMAND = "remove-all-cars";
    private static final String LIST_CARS_COMMAND = "list-cars";
    private static final String LIST_RENTED_CARS_COMMAND = "list-rented";
    private static final String LIST_AVAILABLE_CARS_COMMAND = "list-available";
    private static final String REMOVE_TRANSACTION_COMMAND = "remove-tx";
    private static final String REMOVE_ALL_TRANSACTIONS_COMMAND = "remove-all-txs";
    private static final String MARK_TRANSACTION_COMMAND = "mark-tx";
    private static final String UNMARK_TRANSACTION_COMMAND = "unmark-tx";
    private static final String LIST_ALL_TRANSACTIONS = "list-txs";
    private static final String LIST_COMPLETED_TRANSACTIONS = "list-txs-completed";
    private static final String LIST_UNCOMPLETED_TRANSACTIONS = "list-txs-uncompleted";
    private static final String FIND_TRANSACTIONS_BY_CUSTOMER_COMMAND = "find-txs-by-customer";
    private static final String EXIT_COMMAND = "exit";

    public static void printDividerLine() {
        System.out.println("_".repeat(60));
    }

    public static String getUserInput(){
        System.out.println("What would you like to do?");
        printDividerLine();

        return scanner.nextLine().trim();
    }

    public static boolean parse(String userInput) throws CliRentalException {
        String[] words = userInput.split(" ",2);
        String command = words[0].toLowerCase();

        switch (command) {
        case HELP_COMMAND:
            HelpParser.parseHelpCommand();
            return false;
        case ADD_CUSTOMER_COMMAND:
            Customer customer = CustomerParser.parseIntoCustomer(userInput);
            CustomerList.addCustomer(customer);
            return false;
        case REMOVE_CUSTOMER_COMMAND:
            if (CustomerList.getCustomers().isEmpty()) {
                System.out.println("Customer list is empty. No customer to remove.");
                return false;
            }
            String customerName = CustomerParser.parseCustomerForRemoval(userInput);
            CustomerList.removeCustomer(customerName);
            return false;
        case LIST_USERS_COMMAND:
            CustomerList.printCustomers();
            return false;
        case REMOVE_ALL_CUSTOMERS_COMMAND:
            CustomerList.removeAllCustomers();
            return false;
        case ADD_CAR_COMMAND:
            try {
                Car car = CarParser.parseIntoCar(userInput);
                CarList.addCar(car);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
            return false;
        case REMOVE_CAR_COMMAND:
            try {
                String carLicensePlateNumber = CarParser.parseCarLicenseForRemoval(userInput);
                CarList.removeCar(carLicensePlateNumber);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return false;
        case REMOVE_ALL_CARS_COMMAND:
            try {
                CarList.removeAllCars();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return false;
        case LIST_CARS_COMMAND:
            CarList.printCarList();
            return false;
        case LIST_RENTED_CARS_COMMAND:
            CarList.printRentedCarsList();
            return false;
        case LIST_AVAILABLE_CARS_COMMAND:
            CarList.printAvailableCarsList();
            return false;
        case ADD_TRANSACTION_COMMAND:
            try {
                Transaction transaction = TransactionParser.parseIntoTransaction(userInput);
                TransactionList.addTx(transaction);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            return false;
        case REMOVE_TRANSACTION_COMMAND:
            TransactionParser.parseRemoveTx(userInput);
            return false;
        case REMOVE_ALL_TRANSACTIONS_COMMAND:
            try {
                TransactionList.removeAllTxs();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return false;
        case MARK_TRANSACTION_COMMAND:
            TransactionParser.parseMarkCompleted(userInput);
            return false;
        case UNMARK_TRANSACTION_COMMAND:
            TransactionParser.parseUnmarkCompleted(userInput);
            return false;
        case LIST_ALL_TRANSACTIONS:
            TransactionList.printAllTransactions();
            return false;
        case LIST_COMPLETED_TRANSACTIONS:
            TransactionList.printCompletedTransactions();
            return false;
        case LIST_UNCOMPLETED_TRANSACTIONS:
            TransactionList.printUncompletedTransactions();
            return false;
        case FIND_TRANSACTIONS_BY_CUSTOMER_COMMAND:
            TransactionParser.parseFindTxsByCustomer(userInput);
            return false;
        case EXIT_COMMAND:
            return true;
        default:
            throw CliRentalException.unknownCommand();
        }
    }
}
