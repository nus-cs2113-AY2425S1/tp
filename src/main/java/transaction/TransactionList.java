package transaction;

import car.CarList;
import exceptions.CarException;
import exceptions.CustomerException;
import parser.CarParser;

import java.util.ArrayList;

public class TransactionList {

    // Ensure the transaction list is initialized properly
    private static final ArrayList<Transaction> transactionList = new ArrayList<>();
    private static int txCounter = 1;

    public static void setTxCounter(int counter) {
        if (counter > txCounter) {
            txCounter = counter;
        }
    }

    public static void addTx(Transaction transaction) {
        // Assert that the transaction is not null
        assert transaction != null : "Transaction to add should not be null.";

        String licensePlateNumber = transaction.getCarLicensePlate();
        String uniqueCustomer = transaction.getCustomer();
        // Assert that the license plate number and customer are not null
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

        // Assert that the license plate number is valid and exists before adding
        assert CarParser.isValidLicensePlateNumber(licensePlateNumber)
                && CarList.isExistingLicensePlateNumber(licensePlateNumber)
                : "License plate number must be valid and exist in CarList.";

        String newTransactionId = "TX" + txCounter++;
        transaction.setTransactionId(newTransactionId);
        transactionList.add(transaction);

        // Assert that the transaction was added successfully
        assert transactionList.contains(transaction) : "Transaction was not added to the list.";

        CarList.markCarAsRented(licensePlateNumber);
        System.out.println("Transaction added: ");
        System.out.println(transaction);
    }

    public static void addTxWithoutPrintingInfo(Transaction transaction) {
        // Assert that the transaction is not null
        assert transaction != null : "Transaction to add should not be null.";

        String licensePlateNumber = transaction.getCarLicensePlate();
        String uniqueCustomer = transaction.getCustomer();

        if (isCustomerInTransactionList(uniqueCustomer)) {
            throw CustomerException.customerAlreadyInTransactionList();
        }

        String newTransactionId = "TX" + txCounter++;
        transaction.setTransactionId(newTransactionId);

        if (isCarInTransactionList(licensePlateNumber)) {
            throw CarException.carAlreadyInTransactionList();
        }

        transactionList.add(transaction);
        CarList.markCarAsRented(licensePlateNumber);
        // Assert that the transaction was added successfully
        assert transactionList.contains(transaction) : "Transaction was not added to the list.";
    }

    // Helper method to check if a customer already exists in the transaction list
    private static boolean isCustomerInTransactionList(String customer) {
        for (Transaction transaction : transactionList) {
            if (transaction.getCustomer().equalsIgnoreCase(customer) && !transaction.isCompleted()) {
                return true;
            }
        }
        return false;
    }

    // Helper method to check if a car with a specific license plate already exists in the transaction list
    private static boolean isCarInTransactionList(String licensePlateNumber) {
        for (Transaction transaction : transactionList) {
            if (transaction.getCarLicensePlate().equalsIgnoreCase(licensePlateNumber) && !transaction.isCompleted()) {
                return true;
            }
        }
        return false;
    }


    public static void printAllTransactions() {
        int index = 1;

        if (transactionList.isEmpty()) {
            System.out.println("No transaction available.");
            return;
        }

        System.out.println("Here are all the transactions: ");

        for (Transaction transaction : transactionList) {
            // Assert that each transaction is not null
            assert transaction != null : "Transaction in the list should not be null.";
            System.out.println(index + ") " + transaction);
            index++;
        }
    }

    public static void printCompletedTransactions() {
        int index = 1;

        if (transactionList.isEmpty()) {
            System.out.println("No transaction available.");
            return;
        }

        System.out.println("Here are all the completed transactions: ");

        boolean containsCompletedTx = false;

        for (Transaction transaction : transactionList) {
            // Assert that each transaction is not null
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

    public static void printUncompletedTransactions() {
        int index = 1;

        if (transactionList.isEmpty()) {
            System.out.println("No transaction available.");
            return;
        }

        boolean containsUncompletedTx = false;

        System.out.println("Here are all the uncompleted transactions: ");

        for (Transaction transaction : transactionList) {
            // Assert that each transaction is not null
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

    public static void removeTxByTxId(String txId) {
        // Assert that txId is not null
        assert txId != null : "Transaction ID to remove should not be null.";

        for (Transaction transaction : transactionList) {
            // Assert that each transaction is not null
            assert transaction != null : "Transaction in the list should not be null.";
            if (transaction.getTransactionId() != null) {
                if (transaction.getTransactionId().equalsIgnoreCase(txId)) {
                    System.out.println("Transaction deleted: " + transaction);
                    transactionList.remove(transaction);
                    CarList.markCarAsAvailable(transaction.getCarLicensePlate());

                    // Assert that the transaction was removed successfully
                    assert !transactionList.contains(transaction) : "Transaction was not removed from the list.";
                    return;
                }
            }
        }
        System.out.println("Transaction not found");
    }

    public static void removeAllTxs() {
        transactionList.clear();
        System.out.println("All transactions removed!!!");
    }

    public static void findTxsByCustomer(String customer) {
        // Assert that customer is not null
        assert customer != null : "Customer name to find transactions should not be null.";

        boolean found = false;
        System.out.println("Transaction(s) by " + customer + " found:");
        for (Transaction transaction : transactionList) {
            // Assert that each transaction is not null
            assert transaction != null : "Transaction in the list should not be null.";
            if (transaction.getCustomer().toLowerCase().contains(customer)) {
                found = true;
                System.out.println(transaction);
            }
        }
        if (!found) {
            System.out.println("none");
        }
    }

    public static void markCompletedByTxId(String txId) {
        // Assert that txId is not null
        assert txId != null : "Transaction ID to mark as completed should not be null.";

        for (Transaction transaction : transactionList) {
            // Assert that each transaction is not null
            assert transaction != null : "Transaction in the list should not be null.";
            if (transaction.getTransactionId() != null) {
                if (transaction.getTransactionId().equalsIgnoreCase(txId)) {
                    transaction.setCompleted(true);
                    CarList.markCarAsAvailable(transaction.getCarLicensePlate());
                    System.out.println("Transaction completed: " + transaction);

                    // Assert that the transaction is marked as completed
                    assert transaction.isCompleted() : "Transaction was not marked as completed.";
                    return;
                }
            }
        }
        System.out.println("Transaction not found");
    }

    public static void unmarkCompletedByTxId(String txId) {
        // Assert that txId is not null
        assert txId != null : "Transaction ID to unmark as completed should not be null.";

        for (Transaction transaction : transactionList) {
            // Assert that each transaction is not null
            assert transaction != null : "Transaction in the list should not be null.";
            if (transaction.getTransactionId() != null) {
                if (transaction.getTransactionId().equalsIgnoreCase(txId)) {
                    transaction.setCompleted(false);
                    CarList.markCarAsRented(transaction.getCarLicensePlate());
                    System.out.println("Transaction set uncompleted: " + transaction);

                    // Assert that the transaction is marked as uncompleted
                    assert !transaction.isCompleted() : "Transaction was not unmarked as completed.";
                    return;
                }
            }
        }
        System.out.println("Transaction not found");
    }

    public static String transactionListToFileString() {

        StringBuilder transactionData = new StringBuilder();
        for (Transaction transaction : transactionList) {

            assert transaction != null : "Transaction in the list should not be null.";
            transactionData.append(transaction.toFileString());
            transactionData.append(System.lineSeparator());
        }
        return transactionData.toString();
    }

    public static ArrayList<Transaction> getTransactionList() {
        return transactionList;
    }

    public static void clearTransactionList() {
        transactionList.clear();
    }

    public static void clearTxCounter() {
        txCounter = 1;
    }
}