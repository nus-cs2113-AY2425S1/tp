package transaction;

import car.CarList;
import customer.Customer;
import customer.CustomerList;
import exceptions.CarException;
import exceptions.CustomerException;
import parser.CarParser;

import java.util.ArrayList;

/**
 * Manages a list of car rental transactions.
 * Provides methods to add, remove, and search transactions, as well as mark transactions as completed.
 */
public class TransactionList {

    // Stores all transactions
    private static final ArrayList<Transaction> transactionList = new ArrayList<>();
    private static int txCounter = 1;

    /**
     * Sets the transaction counter if the provided counter is greater than the current counter.
     *
     * @param counter the new transaction counter value
     */
    public static void setTxCounter(int counter) {
        if (counter > txCounter) {
            txCounter = counter;
        }
    }

    /**
     * Adds a transaction to the transaction list and marks the car as rented.
     *
     * @param transaction the transaction to add
     * @throws CarException if the car license plate is invalid or already in use
     * @throws CustomerException if the customer is already in the transaction list
     */
    public static void addTx(Transaction transaction) throws CarException {
        assert transaction != null : "Transaction to add should not be null.";

        String licensePlateNumber = transaction.getCarLicensePlate();
        String uniqueCustomer = transaction.getCustomer();
        assert licensePlateNumber != null : "License plate number should not be null.";
        assert uniqueCustomer != null : "Customer should not be null.";

        if (!CarParser.isValidLicensePlateNumber(licensePlateNumber)) {
            throw CarException.invalidLicensePlateNumber();
        }

        if (!CarList.isExistingLicensePlateNumber(licensePlateNumber)) {
            throw CarException.licensePlateNumberNotFound();
        }

        if (isCustomerInTransactionList(uniqueCustomer)) {
            throw CustomerException.customerAlreadyInTransactionList();
        }

        if (isCarInTransactionList(licensePlateNumber)) {
            throw CarException.carAlreadyInTransactionList();
        }

        String newTransactionId = "TX" + txCounter++;
        transaction.setTransactionId(newTransactionId);
        transactionList.add(transaction);

        assert transactionList.contains(transaction) : "Transaction was not added to the list.";

        CarList.markCarAsRented(licensePlateNumber);
        System.out.println("Transaction added:");
        System.out.println(transaction);
    }

    /**
     * Adds a transaction without printing information.
     *
     * @param transaction the transaction to add
     * @throws CustomerException if the customer is already in the transaction list
     * @throws CarException if the car license plate is already in the transaction list
     */
    public static void addTxWithoutPrintingInfo(Transaction transaction) throws CustomerException, CarException {
        assert transaction != null : "Transaction to add should not be null.";

        String licensePlateNumber = transaction.getCarLicensePlate();
        String uniqueCustomer = transaction.getCustomer();

        if (isCustomerInTransactionList(uniqueCustomer)) {
            throw CustomerException.customerAlreadyInTransactionList();
        }

        if (!CarParser.isValidLicensePlateNumber(licensePlateNumber)) {
            throw CarException.invalidLicensePlateNumber();
        }

        if (!CarList.isExistingLicensePlateNumber(licensePlateNumber)) {
            throw CarException.licensePlateNumberNotFound();
        }

        String newTransactionId = "TX" + txCounter++;
        transaction.setTransactionId(newTransactionId);

        if (isCarInTransactionList(licensePlateNumber)) {
            throw CarException.carAlreadyInTransactionList();
        }

        transactionList.add(transaction);
        CarList.markCarAsRented(licensePlateNumber);
        assert transactionList.contains(transaction) : "Transaction was not added to the list.";
    }

    /**
     * Checks if a customer already exists in the transaction list.
     *
     * @param customer the customer name to check
     * @return true if the customer exists in the transaction list, otherwise false
     */
    private static boolean isCustomerInTransactionList(String customer) {
        for (Transaction transaction : transactionList) {
            if (transaction.getCustomer().equalsIgnoreCase(customer) && !transaction.isCompleted()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a car with a specific license plate is already in the transaction list.
     *
     * @param licensePlateNumber the license plate to check
     * @return true if the car is in the transaction list, otherwise false
     */
    private static boolean isCarInTransactionList(String licensePlateNumber) {
        for (Transaction transaction : transactionList) {
            if (transaction.getCarLicensePlate().equalsIgnoreCase(licensePlateNumber) && !transaction.isCompleted()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prints all transactions in the list.
     */
    public static void printAllTransactions() {
        int index = 1;

        if (transactionList.isEmpty()) {
            System.out.println("No transaction available.");
            return;
        }

        System.out.println("Here are all the transactions:");

        for (Transaction transaction : transactionList) {
            assert transaction != null : "Transaction in the list should not be null.";
            System.out.println(index + ") " + transaction);
            index++;
        }
    }

    /**
     * Prints all completed transactions.
     */
    public static void printCompletedTransactions() {
        int index = 1;

        if (transactionList.isEmpty()) {
            System.out.println("No transaction available.");
            return;
        }

        System.out.println("Here are all the completed transactions:");

        boolean containsCompletedTx = false;

        for (Transaction transaction : transactionList) {
            assert transaction != null : "Transaction in the list should not be null.";
            if (transaction.isCompleted()) {
                System.out.println(index + ") " + transaction);
                index++;
                containsCompletedTx = true;
            }
        }

        if (!containsCompletedTx) {
            System.out.println("No completed transaction available.");
        }
    }

    /**
     * Prints all uncompleted transactions.
     */
    public static void printUncompletedTransactions() {
        int index = 1;

        if (transactionList.isEmpty()) {
            System.out.println("No transaction available.");
            return;
        }

        boolean containsUncompletedTx = false;

        System.out.println("Here are all the uncompleted transactions:");

        for (Transaction transaction : transactionList) {
            assert transaction != null : "Transaction in the list should not be null.";
            if (!transaction.isCompleted()) {
                System.out.println(index + ") " + transaction);
                index++;
                containsUncompletedTx = true;
            }
        }

        if (!containsUncompletedTx) {
            System.out.println("No uncompleted transaction available.");
        }
    }

    /**
     * Removes a transaction by its transaction ID.
     *
     * @param txId the transaction ID to remove
     */
    public static void removeTxByTxId(String txId) {
        assert txId != null : "Transaction ID to remove should not be null.";

        for (Transaction transaction : transactionList) {
            assert transaction != null : "Transaction in the list should not be null.";
            if (transaction.getTransactionId() != null && transaction.getTransactionId().equalsIgnoreCase(txId)) {
                System.out.println("Transaction deleted: " + transaction);
                transactionList.remove(transaction);
                CarList.markCarAsAvailable(transaction.getCarLicensePlate());
                assert !transactionList.contains(transaction) : "Transaction was not removed from the list.";
                return;
            }
        }
        System.out.println("Transaction not found");
    }

    /**
     * Removes all transactions from the list and resets the counter.
     */
    public static void removeAllTxs() {
        for (Transaction transaction : transactionList) {
            CarList.markCarAsAvailable(transaction.getCarLicensePlate());
        }
        transactionList.clear();
        clearTxCounter();
        System.out.println("All transactions removed!!!");
    }

    /**
     * Finds transactions by customer name.
     *
     * @param customer the customer name to search for
     */
    public static void findTxsByCustomer(String customer) {
        assert customer != null : "Customer name to find transactions should not be null.";

        // Check if the customer exists in CustomerList
        boolean customerExists = false;
        String foundCustomer = "";
        for (Customer cust : CustomerList.getCustomerList()) {
            assert cust != null : "Customer in the list should not be null.";
            if (cust.getCustomerName().toLowerCase().contains(customer.toLowerCase())) {
                customerExists = true;
                foundCustomer = cust.getCustomerName();
                break;
            }
        }

        if (!customerExists) {
            System.out.println("User " + customer + " was not found");
            return;
        }

        // Search for transactions by the specified customer
        boolean found = false;
        System.out.println("Transaction(s) by " + foundCustomer + " found:");
        for (Transaction transaction : transactionList) {
            assert transaction != null : "Transaction in the list should not be null.";
            if (transaction.getCustomer().toLowerCase().contains(customer.toLowerCase())) {
                found = true;
                System.out.println(transaction);
            }
        }

        if (!found) {
            System.out.println("None");
        }
    }

    /**
     * Marks a transaction as completed by its transaction ID.
     *
     * @param txId the transaction ID to mark as completed
     */
    public static void markCompletedByTxId(String txId) {
        assert txId != null : "Transaction ID to mark as completed should not be null.";

        for (Transaction transaction : transactionList) {
            assert transaction != null : "Transaction in the list should not be null.";
            if (transaction.getTransactionId() != null && transaction.getTransactionId().equalsIgnoreCase(txId)) {
                transaction.setCompleted(true);
                CarList.markCarAsAvailable(transaction.getCarLicensePlate());
                System.out.println("Transaction completed: " + transaction);
                assert transaction.isCompleted() : "Transaction was not marked as completed.";
                return;
            }
        }
        System.out.println("Transaction not found");
    }

    /**
     * Unmarks a transaction as completed by its transaction ID.
     *
     * @param txId the transaction ID to unmark as completed
     */
    public static void unmarkCompletedByTxId(String txId) {
        assert txId != null : "Transaction ID to unmark as completed should not be null.";

        for (Transaction transaction : transactionList) {
            assert transaction != null : "Transaction in the list should not be null.";
            if (transaction.getTransactionId() != null && transaction.getTransactionId().equalsIgnoreCase(txId)) {
                transaction.setCompleted(false);
                CarList.markCarAsRented(transaction.getCarLicensePlate());
                System.out.println("Transaction set uncompleted: " + transaction);
                assert !transaction.isCompleted() : "Transaction was not unmarked as completed.";
                return;
            }
        }
        System.out.println("Transaction not found");
    }

    /**
     * Converts the transaction list to a formatted string for file storage.
     *
     * @return formatted string representing all transactions
     */
    public static String transactionListToFileString() {
        StringBuilder transactionData = new StringBuilder();
        for (Transaction transaction : transactionList) {
            assert transaction != null : "Transaction in the list should not be null.";
            transactionData.append(transaction.toFileString());
            transactionData.append(System.lineSeparator());
        }
        return transactionData.toString();
    }

    /**
     * Retrieves the transaction list.
     *
     * @return the list of transactions
     */
    public static ArrayList<Transaction> getTransactionList() {
        return transactionList;
    }

    /**
     * Clears the transaction list.
     */
    public static void clearTransactionList() {
        transactionList.clear();
    }

    /**
     * Resets the transaction counter to 1.
     */
    public static void clearTxCounter() {
        txCounter = 1;
    }
}
