package file;

import car.Car;
import car.CarList;
import customer.Customer;
import customer.CustomerList;
import org.junit.jupiter.api.Test;
import transaction.Transaction;
import transaction.TransactionList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

class TransactionFileTest {

    private static final ArrayList<String> filenames = new ArrayList<>();

    @BeforeEach
    void setUp() {
        TransactionList.clearTransactionList();
        FileHandler.createFolderIfNotExist();
        TransactionList.clearTxCounter();
        CarList.clearCarList();
        CustomerList.clearCustomerList();
    }

    @AfterAll
    static void tearDown() {
        for (String filename : filenames) {
            File file = new File(filename);
            file.delete();
        }
    }

    private void validateTransaction(Transaction transaction, String expectedId, String expectedCarPlate
            , String expectedCustomer, int expectedDuration, LocalDate expectedStartDate
            , boolean expectedCompletionStatus) {
        assertEquals(expectedId, transaction.getTransactionId());
        assertEquals(expectedCarPlate, transaction.getCarLicensePlate());
        assertEquals(expectedCustomer, transaction.getCustomer());
        assertEquals(expectedDuration, transaction.getDuration());
        assertEquals(expectedStartDate, transaction.getStartDate());
        assertEquals(expectedCompletionStatus, transaction.isCompleted());
    }

    // Helper method to set up test data for adding transactions
    private static ArrayList<Integer> inputTestCases() {
        TransactionFile transactionFile = new TransactionFile("transactionData1.txt");
        int line = 1;

        CarList.addCarWithoutPrintingInfo(new Car("1", "SJA9173C", 0.01));
        CarList.addCarWithoutPrintingInfo(new Car("1", "SJX1234D", 0.01));

        CustomerList.addCustomerWithoutPrintingInfo(new Customer("John Doe", 18, "83468393"));
        CustomerList.addCustomerWithoutPrintingInfo(new Customer("Jane me", 18, "83468393"));

        ArrayList<Integer> errorLines = new ArrayList<>();
        String[] parameters = {"TX1", "SJA9173C", "John Doe", "5", "08-11-2024", "false"};
        transactionFile.addTransactionWithParameters(parameters, errorLines, line);
        line++;
        parameters = new String[]{"TX2", "SJX1234D", "Jane me", "3", "10-10-2024", "true"};
        transactionFile.addTransactionWithParameters(parameters, errorLines, line);
        line++;
        parameters = new String[]{"TX3", "SJE8720G", "Alice", "seven", "15-09-2024", "false"};
        transactionFile.addTransactionWithParameters(parameters, errorLines, line);
        filenames.add(transactionFile.getAbsolutePath());

        return errorLines;
    }

    @Test
    public void testGetTransactionDataFilename() {
        TransactionFile transactionFile = new TransactionFile();
        assertEquals("transactionData.txt", transactionFile.getTransactionDataFilename());
    }

    @Test
    void testAddTransactionWithParameters() {
        ArrayList<Integer> errorLines = inputTestCases();
        assertEquals(2, TransactionList.getTransactionList().size());

        Transaction transaction1 = TransactionList.getTransactionList().get(0);
        validateTransaction(transaction1, "TX1", "SJA9173C", "John Doe"
                , 5, LocalDate.of(2024, 11, 8), false);

        Transaction transaction2 = TransactionList.getTransactionList().get(1);
        validateTransaction(transaction2, "TX2", "SJX1234D", "Jane me"
                , 3, LocalDate.of(2024, 10, 10), true);

        if (errorLines.size() == 1 && errorLines.get(0) == 3) {
            assertTrue(true);
        } else {
            assert false;
        }
    }

    @Test
    void testUpdateTransactionDataFile() {
        TransactionFile transactionFile = new TransactionFile("transactionData2.txt");
        File testFile = new File(transactionFile.getAbsolutePath());
        transactionFile.createTransactionFileIfNotExist();
        assertTrue(testFile.exists());

        CarList.addCarWithoutPrintingInfo(new Car("1", "SJA9173C", 0.01));
        CarList.addCarWithoutPrintingInfo(new Car("1", "SJX1234D", 0.01));
        TransactionList.addTxWithoutPrintingInfo(new Transaction("TX1", "SJX1234D"
                , "John", 5, LocalDate.of(2024, 11, 8), false));
        TransactionList.addTxWithoutPrintingInfo(new Transaction("TX2", "SJA9173C"
                , "Jane", 3, LocalDate.of(2024, 10, 10), true));

        try {
            transactionFile.updateTransactionDataFile();
        } catch (IOException e) {
            assert false;
        }

        String[] expectedLines = {
            "TX1 | SJX1234D | John | 5 | 08-11-2024 | false",
            "TX2 | SJA9173C | Jane | 3 | 10-10-2024 | true"
        };

        try (Scanner scanner = new Scanner(testFile)) {
            int i = 0;
            while (scanner.hasNext()) {
                assertEquals(expectedLines[i], scanner.nextLine());
                i++;
            }
        } catch (FileNotFoundException e) {
            assert false;
        }

        filenames.add(transactionFile.getAbsolutePath());
    }

    @Test
    void testLoadTransactionDataIfExist() {
        TransactionFile transactionFile = new TransactionFile("transactionData3.txt");
        File testFile = new File(transactionFile.getAbsolutePath());
        transactionFile.createTransactionFileIfNotExist();

        CarList.addCarWithoutPrintingInfo(new Car("1", "SJX1234D", 0.01));
        CarList.addCarWithoutPrintingInfo(new Car("1", "SJE8720G", 0.01));
        CarList.addCarWithoutPrintingInfo(new Car("1", "SJA9173C", 0.01));
        CustomerList.addCustomerWithoutPrintingInfo(new Customer("John", 18
                , "83468393"));
        CustomerList.addCustomerWithoutPrintingInfo(new Customer("Jane", 18
                , "83468393"));
        CustomerList.addCustomerWithoutPrintingInfo(new Customer("Alice", 18
                , "83468393"));

        try (FileWriter fw = new FileWriter(testFile)) {
            String textToAdd = "TX1 | SJX1234D | John | 5 | 08-11-2024 | false\n";
            textToAdd += "TX2 | SJE8720G | Jane | 3 | 10-10-2024 | false\n";
            textToAdd += "TX3 | SJA9173C | Jane | 3 | 10-10-2024 | true\n";
            textToAdd += "TX3 | SJA9173C | Alice | 7 | 15-09-2024 | false\n";
            fw.write(textToAdd);
        } catch (IOException e) {
            assert false;
        }

        transactionFile.loadTransactionDataIfExist();
        assertEquals(3, TransactionList.getTransactionList().size());

        Transaction transaction1 = TransactionList.getTransactionList().get(0);
        validateTransaction(transaction1, "TX1", "SJX1234D", "John"
                , 5, LocalDate.of(2024, 11, 8), false);

        Transaction transaction2 = TransactionList.getTransactionList().get(1);
        validateTransaction(transaction2, "TX2", "SJE8720G", "Jane"
                , 3, LocalDate.of(2024, 10, 10), false);

        Transaction transaction3 = TransactionList.getTransactionList().get(2);
        validateTransaction(transaction3, "TX3", "SJA9173C", "Alice"
                , 7, LocalDate.of(2024, 9, 15), false);

        filenames.add(transactionFile.getAbsolutePath());
    }

    @Test
    void testScanLineAndAddTransaction() {
        TransactionFile transactionFile = new TransactionFile("transactionData4.txt");
        File testFile = new File(transactionFile.getAbsolutePath());
        transactionFile.createTransactionFileIfNotExist();
        CarList.addCarWithoutPrintingInfo(new Car("1", "SJX1234D", 0.01));
        CustomerList.addCustomerWithoutPrintingInfo(new Customer("John", 18
                , "83468393"));

        try (FileWriter fw = new FileWriter(testFile)) {
            String textToAdd = "TX1 | SJX1234D | John | 5 | 08-11-2024 | false";
            fw.write(textToAdd);
        } catch (IOException e) {
            assert false;
        }

        int line = 1;
        ArrayList<Integer> errorLines = new ArrayList<>();
        try (Scanner scanner = new Scanner(testFile)) {
            transactionFile.scanLineAndAddTransaction(scanner, errorLines, line);
        } catch (FileNotFoundException e) {
            assert false;
        }

        Transaction transaction1 = TransactionList.getTransactionList().get(0);
        validateTransaction(transaction1, "TX1", "SJX1234D", "John"
                , 5, LocalDate.of(2024, 11, 8), false);

        filenames.add(transactionFile.getAbsolutePath());
    }

    @Test
    void testCreateTransactionFileIfNotExist() {
        TransactionFile transactionFile = new TransactionFile("transactionData5.txt");
        File testFile = new File(transactionFile.getAbsolutePath());
        transactionFile.createTransactionFileIfNotExist();

        assertTrue(testFile.exists());
        filenames.add(transactionFile.getAbsolutePath());
    }
}
