package transaction;

import customer.Customer;
import customer.CustomerList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static parser.TransactionParser.dateTimeFormatter;

class TransactionTest {
    private Transaction transaction1;
    private Transaction transaction2;
    private int transactionCounter = 1;

    private String generateTransactionId() {
        return "TX" + transactionCounter++;
    }

    @BeforeEach
    void setUp() {
        CustomerList.removeAllCustomers(); // Clear customers list before each test
        CustomerList.addCustomer(new Customer("John Doe", 25, "92345678"));
        CustomerList.addCustomer(new Customer("Jane Smith", 30, "93456789"));
        LocalDate startDate1 = LocalDate.parse("01-10-2024", dateTimeFormatter);
        LocalDate startDate2 = LocalDate.parse("02-10-2024", dateTimeFormatter);
        transaction1 = new Transaction("SGA1234A", "John Doe", 5, startDate1);
        transaction1.setTransactionId(generateTransactionId());
        transaction2 = new Transaction("SGZ5678B", "Jane Smith", 3, startDate2);
        transaction2.setTransactionId(generateTransactionId());
    }

    @Test
    void testTransactionIdGeneration() {
        assertEquals("TX1", transaction1.getTransactionId());
        assertEquals("TX2", transaction2.getTransactionId());
    }

    @Test
    void testGetCarLicensePlate() {
        assertEquals("SGA1234A", transaction1.getCarLicensePlate(), "Car license plate should match");
        assertEquals("SGZ5678B", transaction2.getCarLicensePlate(), "Car license plate should match");
    }

    @Test
    void testGetCustomer() {
        assertEquals("John Doe", transaction1.getCustomer(), "Customer name should match");
        assertEquals("Jane Smith", transaction2.getCustomer(), "Customer name should match");
    }

    @Test
    void testIsCompletedInitiallyFalse() {
        assertFalse(transaction1.isCompleted(), "New transaction should not be completed");
        assertFalse(transaction2.isCompleted(), "New transaction should not be completed");
    }

    @Test
    void testSetCompleted() {
        transaction1.setCompleted(true);
        assertTrue(transaction1.isCompleted(), "Transaction should be marked as completed");

        transaction2.setCompleted(true);
        assertTrue(transaction2.isCompleted(), "Transaction should be marked as completed");

        transaction2.setCompleted(false);
        assertFalse(transaction2.isCompleted(), "Transaction should be marked as not completed");
    }

    @Test
    void testToStringWhenNotCompleted() {
        String expected1 = "[ ] " + transaction1.getTransactionId() +
                " | SGA1234A | John Doe | 5 days" + System.lineSeparator() + "Start Date: 01-10-2024"
                + " | End Date: 06-10-2024";
        String expected2 = "[ ] " + transaction2.getTransactionId() +
                " | SGZ5678B | Jane Smith | 3 days" + System.lineSeparator() + "Start Date: 02-10-2024"
                + " | End Date: 05-10-2024";

        assertEquals(expected1, transaction1.toString(), "toString should match expected format when not completed");
        assertEquals(expected2, transaction2.toString(), "toString should match expected format when not completed");
    }

    @Test
    void testToStringWhenCompleted() {
        transaction1.setCompleted(true);
        transaction2.setCompleted(true);

        String expected1 = "[X] " + transaction1.getTransactionId() +
                " | SGA1234A | John Doe | 5 days" + System.lineSeparator() + "Start Date: 01-10-2024"
                + " | End Date: 06-10-2024";
        String expected2 = "[X] " + transaction2.getTransactionId() +
                " | SGZ5678B | Jane Smith | 3 days" + System.lineSeparator() + "Start Date: 02-10-2024"
                + " | End Date: 05-10-2024";

        assertEquals(expected1, transaction1.toString(), "toString should match expected format when completed");
        assertEquals(expected2, transaction2.toString(), "toString should match expected format when completed");
    }

    @Test
    void testMultipleTransactionIds() {
        LocalDate startDate3 = LocalDate.parse("03-10-2024", dateTimeFormatter);
        LocalDate startDate4 = LocalDate.parse("04-10-2024", dateTimeFormatter);
        Transaction transaction3 = new Transaction("SGB4321C", "Alice Johnson", 2, startDate3);
        transaction3.setTransactionId(generateTransactionId());
        Transaction transaction4 = new Transaction("SGD8765D", "Bob Brown", 4, startDate4);
        transaction4.setTransactionId(generateTransactionId());

        assertEquals("TX3", transaction3.getTransactionId(), "Third transaction ID should be TX3");
        assertEquals("TX4", transaction4.getTransactionId(), "Fourth transaction ID should be TX4");
    }
}
