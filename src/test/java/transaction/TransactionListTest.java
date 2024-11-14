package transaction;

import car.Car;
import car.CarList;
import customer.Customer;
import customer.CustomerList;
import exceptions.CarException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import parser.CarParser;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class TransactionListTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        // Redirect System.out to capture print statements
        System.setOut(new PrintStream(outContent));

        // Clear the transaction list before each test
        TransactionList.getTransactionList().clear();
        CarList.clearCarList();
        // Reset the transaction counter using reflection to ensure predictable transaction IDs
        try {
            java.lang.reflect.Field counterField = TransactionList.class.getDeclaredField("txCounter");
            counterField.setAccessible(true);
            counterField.setInt(null, 1);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            fail("Failed to reset transactionCounter");
        }
    }

    @AfterEach
    void tearDown() {
        // Restore the original System.out
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Test adding a valid transaction with correct license plate format")
    void testAddTxValid() {
        try (MockedStatic<CarParser> carParserMock = Mockito.mockStatic(CarParser.class);
             MockedStatic<CarList> carListMock = Mockito.mockStatic(CarList.class)) {

            // Define a valid license plate adhering to SXX####X format
            String validLicensePlate = "SAB1234C";
            CarList.addCarWithoutPrintingInfo(new Car("1" , "SAB1234C" , 0.01));
            // Mock the static methods
            carParserMock.when(() -> CarParser.isValidLicensePlateNumber(validLicensePlate)).thenReturn(true);
            carListMock.when(() -> CarList.isExistingLicensePlateNumber(validLicensePlate)).thenReturn(true);

            // Create a transaction
            Transaction transaction = new Transaction(validLicensePlate, "John Doe", 5,
                    LocalDate.of(2024, 10, 1));

            // Add the transaction
            TransactionList.addTx(transaction);

            // Verify that the transaction was added
            assertEquals(1, TransactionList.getTransactionList().size(),
                    "Transaction should be added to the list");
            assertEquals(transaction, TransactionList.getTransactionList().get(0),
                    "Added transaction should match");

            // Verify that CarList.markCarAsRented was called
            carListMock.verify(() -> CarList.markCarAsRented(validLicensePlate),
                    Mockito.times(1));

            // Verify the printed output
            String expectedOutput = "Transaction added:" + System.lineSeparator() + transaction +
                    System.lineSeparator();
            assertEquals(expectedOutput, outContent.toString(), "Printed output should match expected");
        }
    }

    @Test
    @DisplayName("Test adding a transaction with invalid license plate format")
    void testAddTxInvalidLicensePlateFormat() {
        try (MockedStatic<CarParser> carParserMock = Mockito.mockStatic(CarParser.class)) {

            // Define an invalid license plate not adhering to SXX####X format
            String invalidLicensePlate = "TAB12345"; // Does not start with 'S' and has too many characters

            // Mock the static method to return false
            carParserMock.when(() -> CarParser.isValidLicensePlateNumber(invalidLicensePlate)).thenReturn(false);

            // Create a transaction with invalid license plate
            Transaction transaction = new Transaction(invalidLicensePlate, "Jane Doe", 3,
                    LocalDate.of(2024, 10, 2));

            // Attempt to add the transaction and expect an exception
            CarException exception = assertThrows(CarException.class, () -> TransactionList.addTx(transaction),
                    "Adding a transaction with invalid license plate should throw CarException");

            assertEquals("""
                Oops!! License Plate number is invalid...
                
                License plate number format: SXX####X
                X -> Letters [A - Z], #### -> Numbers [1 - 9999]""", exception.getMessage(),
                    "Exception message should match");

            // Verify that no transaction was added
            assertTrue(TransactionList.getTransactionList().isEmpty(), "Transaction list should remain empty");
        }
    }

    @Test
    @DisplayName("Test adding a transaction with non-existing license plate number")
    void testAddTxLicensePlateNotFound() {
        try (MockedStatic<CarParser> carParserMock = Mockito.mockStatic(CarParser.class);
             MockedStatic<CarList> carListMock = Mockito.mockStatic(CarList.class)) {

            // Define a valid license plate format but not existing in CarList
            String validButNonExistingLicensePlate = "SCD5678Z";
            CarList.addCarWithoutPrintingInfo(new Car("1" , "SCD5678Z" , 0.01));
            // Mock the static methods
            carParserMock.when(() -> CarParser.isValidLicensePlateNumber(validButNonExistingLicensePlate)).
                    thenReturn(true);
            carListMock.when(() -> CarList.isExistingLicensePlateNumber(validButNonExistingLicensePlate)).
                    thenReturn(false);

            // Create a transaction with a valid but non-existing license plate
            Transaction transaction = new Transaction(validButNonExistingLicensePlate, "Alice Smith",
                    2, LocalDate.of(2024, 10, 3));

            // Attempt to add the transaction and expect an exception
            CarException exception = assertThrows(CarException.class, () -> TransactionList.addTx(transaction),
                    "Adding a transaction with non-existing license plate should throw CarException");

            assertEquals("Car license plate number not found!!" + System.lineSeparator() +
                            "Use command <list-cars> to view list of available cars.",
                    exception.getMessage(), "Exception message should match");

            // Verify that no transaction was added
            assertTrue(TransactionList.getTransactionList().isEmpty(), "Transaction list should remain empty");
        }
    }

    @Test
    @DisplayName("Test adding a transaction without printing info")
    void testAddTxWithoutPrintingInfo() {
        // Define a valid license plate adhering to SXX####X format
        String validLicensePlate = "SXY9012A";
        CarList.addCarWithoutPrintingInfo(new Car("1" , "SXY9012A" , 0.01));

        // Create a transaction
        Transaction transaction = new Transaction(validLicensePlate, "Bob Brown", 4,
                LocalDate.of(2024, 10, 4));

        // Add the transaction without printing info
        TransactionList.addTxWithoutPrintingInfo(transaction);

        // Verify that the transaction was added
        assertEquals(1, TransactionList.getTransactionList().size(),
                "Transaction should be added to the list");
        assertEquals(transaction, TransactionList.getTransactionList().get(0),
                "Added transaction should match");

        // Verify that nothing was printed
        assertTrue(outContent.toString().isEmpty(), "No output should be printed");
    }

    @Test
    @DisplayName("Test printing all transactions when list is empty")
    void testPrintAllTransactionsEmpty() {
        TransactionList.printAllTransactions();

        String expectedOutput = "No transaction available." + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString(),
                "Should indicate that no transactions are available");
    }

    @Test
    @DisplayName("Test printing all transactions")
    void testPrintAllTransactions() {
        // Define valid license plates adhering to SXX####X format
        String licensePlate1 = "SAB1234C";
        String licensePlate2 = "SXY5678Z";
        CarList.addCarWithoutPrintingInfo(new Car("1" , "SAB1234C" , 0.01));
        CarList.addCarWithoutPrintingInfo(new Car("1" , "SXY5678Z" , 0.01));
        // Add transactions without printing info
        Transaction tx1 = new Transaction(licensePlate1, "John Doe", 5,
                LocalDate.of(2024, 10, 1));
        Transaction tx2 = new Transaction(licensePlate2, "Jane Smith", 3,
                LocalDate.of(2024, 10, 2));
        TransactionList.addTxWithoutPrintingInfo(tx1);
        TransactionList.addTxWithoutPrintingInfo(tx2);

        TransactionList.printAllTransactions();

        String expectedOutput = "Here are all the transactions:" + System.lineSeparator() +
                "1) " + tx1 + System.lineSeparator() +
                "2) " + tx2 + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString(), "Printed transactions should match the list");
    }

    @Test
    @DisplayName("Test printing completed transactions")
    void testPrintCompletedTransactions() {
        // Define valid license plates adhering to SXX####X format
        String licensePlate1 = "SAB1234C";
        String licensePlate2 = "SXY5678Z";
        CarList.addCarWithoutPrintingInfo(new Car("1" , "SAB1234C" , 0.01));
        CarList.addCarWithoutPrintingInfo(new Car("1" , "SXY5678Z" , 0.01));
        // Add transactions without printing info
        Transaction tx1 = new Transaction(licensePlate1, "John Doe", 5,
                LocalDate.of(2024, 10, 1));
        Transaction tx2 = new Transaction(licensePlate2, "Jane Smith", 3,
                LocalDate.of(2024, 10, 2));
        tx1.setCompleted(true);
        TransactionList.addTxWithoutPrintingInfo(tx1);
        TransactionList.addTxWithoutPrintingInfo(tx2);

        TransactionList.printCompletedTransactions();

        String expectedOutput = "Here are all the completed transactions:" + System.lineSeparator() +
                "1) " + tx1 + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString(), "Printed completed transactions should match");
    }

    @Test
    @DisplayName("Test printing uncompleted transactions")
    void testPrintUncompletedTransactions() {
        // Define valid license plates adhering to SXX####X format
        String licensePlate1 = "SAB1234C";
        String licensePlate2 = "SXY5678Z";
        CarList.addCarWithoutPrintingInfo(new Car("1" , "SAB1234C" , 0.01));
        CarList.addCarWithoutPrintingInfo(new Car("1" , "SXY5678Z" , 0.01));
        // Add transactions without printing info
        Transaction tx1 = new Transaction(licensePlate1, "John Doe", 5,
                LocalDate.of(2024, 10, 1));
        Transaction tx2 = new Transaction(licensePlate2, "Jane Smith", 3,
                LocalDate.of(2024, 10, 2));
        tx2.setCompleted(true);
        TransactionList.addTxWithoutPrintingInfo(tx1);
        TransactionList.addTxWithoutPrintingInfo(tx2);

        TransactionList.printUncompletedTransactions();

        String expectedOutput = "Here are all the uncompleted transactions:" + System.lineSeparator() +
                "1) " + tx1 + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString(), "Printed uncompleted transactions should match");
    }

    @Test
    @DisplayName("Test removing a transaction by transaction ID")
    void testRemoveTxByTxId() {
        // Define valid license plates adhering to SXX####X format
        String licensePlate1 = "SAB1234C";
        String licensePlate2 = "SXY5678Z";
        CarList.addCarWithoutPrintingInfo(new Car("1" , "SAB1234C" , 0.01));
        CarList.addCarWithoutPrintingInfo(new Car("1" , "SXY5678Z" , 0.01));
        // Add transactions without printing info
        Transaction tx1 = new Transaction(licensePlate1, "John Doe", 5,
                LocalDate.of(2024, 10, 1));
        Transaction tx2 = new Transaction(licensePlate2, "Jane Smith", 3,
                LocalDate.of(2024, 10, 2));
        TransactionList.addTxWithoutPrintingInfo(tx1);
        TransactionList.addTxWithoutPrintingInfo(tx2);

        // Remove tx1
        TransactionList.removeTxByTxId("tx1"); // Assuming case-insensitive

        // Verify that tx1 is removed
        assertEquals(1, TransactionList.getTransactionList().size(),
                "Transaction list should have one transaction after removal");
        assertEquals(tx2, TransactionList.getTransactionList().get(0), "Remaining transaction should be tx2");

        // Verify the printed output
        String expectedOutput = "Transaction deleted: " + tx1 + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString(), "Printed output should confirm deletion");
    }

    @Test
    @DisplayName("Test removing a transaction with non-existing transaction ID")
    void testRemoveTxByTxIdNotFound() {
        // Define a valid license plate adhering to SXX####X format
        String licensePlate1 = "SAB1234C";
        CarList.addCarWithoutPrintingInfo(new Car("1" , "SAB1234C" , 0.01));
        // Add a transaction
        Transaction tx1 = new Transaction(licensePlate1, "John Doe", 5,
                LocalDate.of(2024, 10, 1));
        TransactionList.addTxWithoutPrintingInfo(tx1);

        // Attempt to remove a non-existing transaction
        TransactionList.removeTxByTxId("tx999");

        // Verify that the transaction list remains unchanged
        assertEquals(1, TransactionList.getTransactionList().size(),
                "Transaction list should remain unchanged");

        // Verify the printed output
        String expectedOutput = "Transaction not found" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString(), "Should indicate that transaction was not found");
    }

    @Test
    @DisplayName("Test finding transactions by customer")
    void testFindTxsByCustomer() {
        // Add users to CustomerList
        CustomerList.addCustomer(new Customer("John Doe", 25, "92345678"));
        CustomerList.addCustomer(new Customer("Jane Smith", 30, "93456789"));
        CustomerList.addCustomer(new Customer("Mike Johnson", 28, "91234567"));

        // Define valid license plates adhering to SXX####X format
        String licensePlate1 = "SAB1234C";
        String licensePlate2 = "SXY5678Z";
        String licensePlate3 = "SCD9012A";
        String licensePlate4 = "SGD4091D";
        CarList.addCarWithoutPrintingInfo(new Car("1" , "SAB1234C" , 0.01));
        CarList.addCarWithoutPrintingInfo(new Car("1" , "SXY5678Z" , 0.01));
        CarList.addCarWithoutPrintingInfo(new Car("1" , "SCD9012A" , 0.01));
        CarList.addCarWithoutPrintingInfo(new Car("1" , "SGD4091D" , 0.01));

        // Add transactions without printing info
        Transaction tx1 = new Transaction(licensePlate1, "John Doe", 5,
                LocalDate.of(2024, 10, 1));
        TransactionList.addTxWithoutPrintingInfo(tx1);

        Transaction tx2 = new Transaction(licensePlate2, "Jane Smith", 3,
                LocalDate.of(2024, 10, 2));
        TransactionList.addTxWithoutPrintingInfo(tx2);

        Transaction tx3 = new Transaction(licensePlate3, "Mike Johnson", 2,
                LocalDate.of(2024, 10, 3));
        TransactionList.addTxWithoutPrintingInfo(tx3);

        // Mark tx1 as completed
        TransactionList.markCompletedByTxId(tx1.getTransactionId());

        Transaction tx4 = new Transaction(licensePlate4, "John Doe", 2,
                LocalDate.of(2024, 10, 3));
        TransactionList.addTxWithoutPrintingInfo(tx4);

        // Find transactions by "John Doe"
        TransactionList.findTxsByCustomer("john doe");

        String expectedOutput =
                "Customer added" + System.lineSeparator() +
                        "Customer name: John Doe\n" +
                        "Age: 25\n" +
                        "Contact Number: 92345678" + System.lineSeparator() +
                        "Customer added" + System.lineSeparator() +
                        "Customer name: Jane Smith\n" +
                        "Age: 30\n" +
                        "Contact Number: 93456789" + System.lineSeparator() +
                        "Customer added" + System.lineSeparator() +
                        "Customer name: Mike Johnson\n" +
                        "Age: 28\n" +
                        "Contact Number: 91234567" + System.lineSeparator() +
                        "Transaction completed: [X] TX1 | SAB1234C | John Doe | 5 days" + System.lineSeparator() +
                        "Start Date: 01-10-2024 | End Date: 06-10-2024" + System.lineSeparator() +
                        "Transaction(s) by John Doe found:" + System.lineSeparator() +
                        "[X] " + tx1.getTransactionId() + " | " + licensePlate1 +
                        " | John Doe | 5 days" + System.lineSeparator() +
                        "Start Date: 01-10-2024 | End Date: 06-10-2024" + System.lineSeparator() +
                        "[ ] " + tx4.getTransactionId() + " | " + licensePlate4 +
                        " | John Doe | 2 days" + System.lineSeparator() +
                        "Start Date: 03-10-2024 | End Date: 05-10-2024";

        String actualOutput = outContent.toString();

        // Check that the found transactions are printed
        assertTrue(actualOutput.contains("Transaction(s) by John Doe found:"),
                "Should indicate transactions found by customer");
        assertTrue(actualOutput.contains(tx1.toString()), "Should contain tx1 details");
        assertTrue(actualOutput.contains(tx4.toString()), "Should contain tx4 details");
        assertEquals(expectedOutput, actualOutput.trim(), "Printed output should confirm deletion");
    }


    @Test
    @DisplayName("Test finding transactions by customer with no matches")
    void testFindTxsByCustomerNoMatches() {
        // Define a valid license plate adhering to SXX####X format
        String licensePlate1 = "SAB1234C";
        CarList.addCarWithoutPrintingInfo(new Car("1" , "SAB1234C" , 0.01));
        // Add a transaction
        Transaction tx1 = new Transaction(licensePlate1, "John Doe", 5,
                LocalDate.of(2024, 10, 1));
        TransactionList.addTxWithoutPrintingInfo(tx1);

        // Attempt to find transactions by a non-existing customer
        TransactionList.findTxsByCustomer("Alice Johnson");

        String expectedOutput = "User Alice Johnson was not found" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString(), "Should indicate that no transactions was found");
    }

    @Test
    @DisplayName("Test marking a transaction as completed by transaction ID")
    void testMarkCompletedByTxId() {
        String licensePlate1 = "SAB1234C";
        CarList.addCarWithoutPrintingInfo(new Car("1" , "SAB1234C" , 0.01));
        // Add a transaction
        Transaction tx1 = new Transaction(licensePlate1, "John Doe", 5,
                LocalDate.of(2024, 10, 1));
        TransactionList.addTxWithoutPrintingInfo(tx1);

        // Mark the transaction as completed
        TransactionList.markCompletedByTxId(tx1.getTransactionId().toLowerCase());

        // Verify that the transaction is marked as completed
        assertTrue(tx1.isCompleted(), "Transaction should be marked as completed");

        // Verify the printed output
        String expectedOutput = "Transaction completed: " + tx1 + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString(), "Printed output should confirm completion");
    }

    @Test
    @DisplayName("Test marking a transaction as completed with non-existing transaction ID")
    void testMarkCompletedByTxIdNotFound() {
        // Define a valid license plate adhering to SXX####X format
        String licensePlate1 = "SAB1234C";
        CarList.addCarWithoutPrintingInfo(new Car("1" , "SAB1234C" , 0.01));
        // Add a transaction
        Transaction tx1 = new Transaction(licensePlate1, "John Doe", 5,
                LocalDate.of(2024, 10, 1));
        TransactionList.addTxWithoutPrintingInfo(tx1);

        // Attempt to mark a non-existing transaction as completed
        TransactionList.markCompletedByTxId("TX999");

        // Verify that the transaction is not marked as completed
        assertFalse(tx1.isCompleted(), "Transaction should remain uncompleted");

        // Verify the printed output
        String expectedOutput = "Transaction not found" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString(), "Should indicate that transaction was not found");
    }

    @Test
    @DisplayName("Test unmarking a transaction as completed by transaction ID")
    void testUnmarkCompletedByTxId() {
        // Define a valid license plate adhering to SXX####X format
        String licensePlate1 = "SAB1234C";
        CarList.addCarWithoutPrintingInfo(new Car("1" , "SAB1234C" , 0.01));
        // Add a transaction and mark it as completed
        Transaction tx1 = new Transaction(licensePlate1, "John Doe", 5,
                LocalDate.of(2024, 10, 1));
        TransactionList.addTxWithoutPrintingInfo(tx1);
        tx1.setCompleted(true);

        // Unmark the transaction as completed
        TransactionList.unmarkCompletedByTxId(tx1.getTransactionId().toLowerCase()); // Assuming case-insensitive

        // Verify that the transaction is unmarked
        assertFalse(tx1.isCompleted(), "Transaction should be marked as uncompleted");

        // Verify the printed output
        String expectedOutput = "Transaction set uncompleted: " + tx1 + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString(), "Printed output should confirm un-completion");
    }

    @Test
    @DisplayName("Test unmarking a transaction as completed with non-existing transaction ID")
    void testUnmarkCompletedByTxIdNotFound() {
        // Define a valid license plate adhering to SXX####X format
        String licensePlate1 = "SAB1234C";
        CarList.addCarWithoutPrintingInfo(new Car("1" , "SAB1234C" , 0.01));
        // Add a transaction
        Transaction tx1 = new Transaction(licensePlate1, "John Doe", 5,
                LocalDate.of(2024, 10, 1));
        TransactionList.addTxWithoutPrintingInfo(tx1);

        // Attempt to unmark a non-existing transaction
        TransactionList.unmarkCompletedByTxId("TX999");

        // Verify that the transaction remains unchanged
        assertFalse(tx1.isCompleted(), "Transaction should remain uncompleted");

        // Verify the printed output
        String expectedOutput = "Transaction not found" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString(), "Should indicate that transaction was not found");
    }

    @Test
    @DisplayName("Test converting transaction list to file string")
    void testTransactionListToFileString() {
        // Define valid license plates adhering to SXX####X format
        String licensePlate1 = "SAB1234C";
        String licensePlate2 = "SXY5678Z";
        CarList.addCarWithoutPrintingInfo(new Car("1" , "SAB1234C" , 0.01));
        CarList.addCarWithoutPrintingInfo(new Car("1" , "SXY5678Z" , 0.01));
        // Add transactions without printing info
        Transaction tx1 = new Transaction(licensePlate1, "John Doe", 5,
                LocalDate.of(2024, 10, 1));
        Transaction tx2 = new Transaction(licensePlate2, "Jane Smith", 3,
                LocalDate.of(2024, 10, 2));
        TransactionList.addTxWithoutPrintingInfo(tx1);
        TransactionList.addTxWithoutPrintingInfo(tx2);

        String expectedFileString = tx1.toFileString() + System.lineSeparator() + tx2.toFileString() +
                System.lineSeparator();
        String actualFileString = TransactionList.transactionListToFileString();

        assertEquals(expectedFileString, actualFileString,
                "File string representation should match expected format");
    }

    @Test
    @DisplayName("Test retrieving the transaction list")
    void testGetTransactionList() {
        // Define valid license plates adhering to SXX####X format
        String licensePlate1 = "SAB1234C";
        String licensePlate2 = "SXY5678Z";
        CarList.addCarWithoutPrintingInfo(new Car("1" , "SAB1234C" , 0.01));
        CarList.addCarWithoutPrintingInfo(new Car("1" , "SXY5678Z" , 0.01));
        // Add transactions without printing info
        Transaction tx1 = new Transaction(licensePlate1, "John Doe", 5,
                LocalDate.of(2024, 10, 1));
        Transaction tx2 = new Transaction(licensePlate2, "Jane Smith", 3,
                LocalDate.of(2024, 10, 2));
        TransactionList.addTxWithoutPrintingInfo(tx1);
        TransactionList.addTxWithoutPrintingInfo(tx2);

        ArrayList<Transaction> transactions = TransactionList.getTransactionList();

        assertEquals(2, transactions.size(), "Transaction list should contain two transactions");
        assertTrue(transactions.contains(tx1), "Transaction list should contain tx1");
        assertTrue(transactions.contains(tx2), "Transaction list should contain tx2");
    }

    @Test
    @DisplayName("Test adding multiple transactions and verifying transaction IDs")
    void testMultipleTransactionIds() {
        try (MockedStatic<CarParser> carParserMock = Mockito.mockStatic(CarParser.class);
             MockedStatic<CarList> carListMock = Mockito.mockStatic(CarList.class)) {

            // Define valid license plates adhering to SXX####X format
            String licensePlate1 = "SAB1234C";
            String licensePlate2 = "SXY5678Z";
            String licensePlate3 = "SCD9012A";

            CarList.addCarWithoutPrintingInfo(new Car("1" , "SAB1234C" , 0.01));
            CarList.addCarWithoutPrintingInfo(new Car("1" , "SXY5678Z" , 0.01));
            CarList.addCarWithoutPrintingInfo(new Car("1" , "SCD9012A" , 0.01));
            // Mock the static methods to always return valid
            carParserMock.when(() -> CarParser.isValidLicensePlateNumber(Mockito.anyString())).
                    thenAnswer(invocation -> {
                        String plate = invocation.getArgument(0);
                        return plate.matches("S[A-Z]{2}\\d{4}[A-Z]");
                    });
            carListMock.when(() -> CarList.isExistingLicensePlateNumber(Mockito.anyString())).thenReturn(true);

            // Add multiple transactions
            Transaction tx1 = new Transaction(licensePlate1, "Alice", 2,
                    LocalDate.of(2024, 10, 5));
            Transaction tx2 = new Transaction(licensePlate2, "Bob", 3,
                    LocalDate.of(2024, 10, 6));
            Transaction tx3 = new Transaction(licensePlate3, "Charlie", 1,
                    LocalDate.of(2024, 10, 7));

            TransactionList.addTx(tx1);
            TransactionList.addTx(tx2);
            TransactionList.addTx(tx3);

            // Verify transaction IDs
            assertEquals("TX1", tx1.getTransactionId(), "First transaction ID should be TX1");
            assertEquals("TX2", tx2.getTransactionId(), "Second transaction ID should be TX2");
            assertEquals("TX3", tx3.getTransactionId(), "Third transaction ID should be TX3");

            // Verify that all transactions are added
            assertEquals(3, TransactionList.getTransactionList().size(),
                    "All transactions should be added");
        }
    }
}
