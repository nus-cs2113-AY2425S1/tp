package seedu.duke.storage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.budget.Budget;
import seedu.duke.exception.FinanceBuddyException;
import seedu.duke.financial.Expense;
import seedu.duke.financial.FinancialList;
import seedu.duke.financial.Income;
import seedu.duke.logic.BudgetLogic;
import seedu.duke.ui.AppUi;




/**
 * Unit tests for the Storage class.
 * 
 * This class contains various test methods to verify the functionality of the Storage class,
 * including methods for getting storage files, updating storage, parsing expenses and incomes,
 * and loading data from files.
 * 
 * The tests cover the following scenarios:
 * - Verifying the existence of storage and budget files.
 * - Updating storage with financial entries and budget data.
 * - Checking parameters for validity.
 * - Parsing expenses and incomes from string arrays.
 * - Loading data from files, including handling of empty files and invalid data formats.
 * - Handling invalid budget amounts and dates.
 * 
 * Each test method is annotated with @Test and includes assertions to verify the expected behavior.
 * The setUp method is annotated with @BeforeEach to initialize necessary objects before each test.
 */
public class StorageTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private Storage storage;
    private FinancialList financialList;
    private BudgetLogic budgetLogic;
    private Budget budget;
    private AppUi ui;

    /**
     * Sets up the necessary objects before each test.
     * Initializes the Storage, FinancialList, and BudgetLogic instances.
     */
    @BeforeEach
    public void setUp() {
        storage = new Storage();
        financialList = new FinancialList();
        budgetLogic = new BudgetLogic(budget,ui);
        System.setOut(new PrintStream(outputStream));
        Storage.getStorageFileWithoutMsg();
        Storage.getBudgetFileWithoutMsg();
    }

    /**
     * Tests the getStorageFile method of the Storage class.
     * This test verifies that the file returned by getStorageFile exists.
     */
    @Test
    public void testGetStorageFile() {
        File file = Storage.getStorageFile();
        assertTrue(file.exists());

        storage.printLoadingResult();
        String output = outputStream.toString();
        String expection =  System.lineSeparator();
        assertEquals(expection, output);
    }

    /**
     * Tests the getBudgetFile method of the Storage class.
     * This test verifies that the budget file exists.
     */
    @Test
    public void testGetBudgetFile() {
        File file = Storage.getBudgetFile();
        assertTrue(file.exists());

        storage.printLoadingResult();
        String output = outputStream.toString();
        String expection =  System.lineSeparator();
        assertEquals(expection, output);
    }

    /**
     * Tests the update method of the Storage class.
     * 
     * This test performs the following steps:
     * 1. Adds a new expense entry to the financial list.
     * 2. Sets a temporary budget amount and overwrites the existing budget.
     * 3. Calls the update method on the storage object with the financial list and budget logic.
     * 4. Verifies that the storage file has been updated by checking if it contains any lines.
     * 5. Verifies that the budget file has been updated by checking if it contains any lines.
     * 
     * @throws IOException if an I/O error occurs during the test.
     * @throws FinanceBuddyException if a FinanceBuddy specific error occurs during the test.
     */
    @Test
    public void testUpdate() throws IOException, FinanceBuddyException {
        financialList.addEntry(new Expense(100, "Lunch", LocalDate.now(), Expense.Category.FOOD));
        financialList.addEntry(new Income(200, "Salary", LocalDate.now(), Income.Category.SALARY));
        financialList.addEntry(new Expense(300, "Dinner", LocalDate.parse("2023-01-01"), Expense.Category.FOOD));
        Budget tmpBudget = new Budget();
        tmpBudget.setBudgetAmount(500);
        budgetLogic.overwriteBudget(tmpBudget);

        storage.update(financialList, budgetLogic);

        File file = Storage.getStorageFile();
        Scanner scanner = new Scanner(file);
        assertTrue(scanner.hasNextLine());
        assertEquals("E ¦¦ 300.00 ¦¦ Dinner ¦¦ 01/01/2023 ¦¦ FOOD", scanner.nextLine());
        assertTrue(scanner.hasNextLine());
        assertEquals("E ¦¦ 100.00 ¦¦ Lunch ¦¦ " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    + " ¦¦ FOOD", scanner.nextLine());
        assertTrue(scanner.hasNextLine());
        assertEquals("I ¦¦ 200.00 ¦¦ Salary ¦¦ " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    + " ¦¦ SALARY", scanner.nextLine());
        assertFalse(scanner.hasNextLine());
        scanner.close();

        File budgetFile = Storage.getBudgetFile();
        Scanner budgetScanner = new Scanner(budgetFile);
        assertTrue(budgetScanner.hasNextLine());
        assertEquals("500.00", budgetScanner.nextLine());
        assertTrue(budgetScanner.hasNextLine());
        assertEquals(LocalDate.now().toString(), budgetScanner.nextLine());
        budgetScanner.close();
    }

    /**
     * Creates a DateTimeFormatter with the pattern "dd/MM/yyyy".
     * This formatter can be used to format or parse dates in the specified pattern.
     */
    @Test
    public void testCheckParameters() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.now();

        assertDoesNotThrow(() -> storage.checkParameters(100, "Description", formatter, date));
        assertThrows(FinanceBuddyException.class, 
                    () -> storage.checkParameters(-1, "Description", formatter, date));
        assertThrows(FinanceBuddyException.class, 
                    () -> storage.checkParameters(10000000, "Description", formatter, date));
        assertThrows(FinanceBuddyException.class, 
                    () -> storage.checkParameters(100, "", formatter, date));
        assertThrows(FinanceBuddyException.class, 
                    () -> storage.checkParameters(100, "Description", formatter, date.plusDays(1)));
    }

    /**
     * Tests the parseExpense method of the Storage class.
     * Ensures that an Expense object is correctly created from a valid array of tokens.
     * Verifies that the amount, description, date, and category of the Expense object
     * match the expected values.
     */
    @Test
    public void testParseExpense() {
        String[] tokens = {"E", "100", "Lunch", "01/01/2023", "FOOD"};
        Expense expense = assertDoesNotThrow(() -> storage.parseExpense(tokens));
        assertEquals(100, expense.getAmount());
        assertEquals("Lunch", expense.getDescription());
        assertEquals(LocalDate.of(2023, 1, 1), expense.getDate());
        assertEquals(Expense.Category.FOOD, expense.getCategory());
    }

    /**
     * Tests the parseIncome method of the Storage class.
     * Ensures that the method correctly parses an array of strings into an Income object.
     * 
     * The test checks the following:
     * - The amount is correctly parsed and set.
     * - The description is correctly parsed and set.
     * - The date is correctly parsed and set.
     * - The category is correctly parsed and set.
     */
    @Test
    public void testParseIncome() {
        String[] tokens = {"I", "200", "Salary", "01/01/2023", "SALARY"};
        Income income = assertDoesNotThrow(() -> storage.parseIncome(tokens));
        assertEquals(200, income.getAmount());
        assertEquals("Salary", income.getDescription());
        assertEquals(LocalDate.of(2023, 1, 1), income.getDate());
        assertEquals(Income.Category.SALARY, income.getCategory());
    }

    /**
     * Tests the loading of data from files into the application.
     * 
     * This test writes a sample entry and budget data to their respective files,
     * then loads the data using the storage.loadFromFile method. It verifies that
     * the loaded data matches the expected values.
     * 
     * @throws IOException if an I/O error occurs during file operations.
     */
    @Test
    public void testLoadFromFile() throws IOException {
        File file = Storage.getStorageFile();
        FileWriter writer = new FileWriter(file);
        writer.write("E ¦¦ 100 ¦¦ Lunch ¦¦ 01/01/2023 ¦¦ FOOD\n");
        writer.close();

        File budgetFile = Storage.getBudgetFile();
        FileWriter budgetWriter = new FileWriter(budgetFile);
        budgetWriter.write("500\n2024-11-01\n");
        budgetWriter.close();

        FinancialList loadedList = storage.loadFromFile(budgetLogic);
        assertEquals(1, loadedList.getEntryCount());
        assertEquals(500, budgetLogic.getBudget().getBudgetAmount());
        assertEquals("01/11/2024",
            budgetLogic.getBudget().getBudgetSetDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));


        storage.printLoadingResult();
        String output = outputStream.toString();
        String expection = "I have loaded 1 expenses and 0 incomes from file." + System.lineSeparator() +
                            "Budget loaded from file." + System.lineSeparator();
        assertEquals(expection, output);
    }

    /**
     * Tests the loadFromFile method when the storage file and budget file are empty.
     * This test ensures that an empty FinancialList is returned and the budget is null.
     *
     * @throws IOException if an I/O error occurs while writing to the files.
     */
    @Test
    public void testLoadFromFileEmpty() throws IOException {
        File file = Storage.getStorageFile();
        FileWriter writer = new FileWriter(file);
        writer.write("");
        writer.close();

        File budgetFile = Storage.getBudgetFile();
        FileWriter budgetWriter = new FileWriter(budgetFile);
        budgetWriter.write("");
        budgetWriter.close();

        FinancialList loadedList = storage.loadFromFile(budgetLogic);
        assertEquals(0, loadedList.getEntryCount());
        assertEquals(null, budgetLogic.getBudget());

        storage.printLoadingResult();
        String output = outputStream.toString();
        String expection = "No transactions loaded from file." + System.lineSeparator() +
                            "Budget format in file is invalid, the budget won't be set." + System.lineSeparator();
        assertEquals(expection, output);
    }

    /**
     * Tests the storage format for various invalid cases and ensures that only valid entries are loaded.
     * 
     * The test writes several entries to the storage file with different types of invalid data:
     * - Invalid category
     * - Invalid date
     * - Invalid amount
     * - Invalid type
     * - Missing fields
     * 
     * It then writes a valid budget to the budget file.
     * 
     * Finally, it loads the financial list from the storage and asserts that only the valid entry is loaded.
     * 
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void testStorageFormatInvalid() throws IOException {
        File file = Storage.getStorageFile();
        FileWriter writer = new FileWriter(file);
        // the CATEGORY is invalid
        writer.write("E ¦¦ 100 ¦¦ Lunch ¦¦ 01/01/2023 ¦¦ TAIWANGOOD\n");
        // the DATE is invalid
        writer.write("E ¦¦ 100 ¦¦ Lunch ¦¦ 01/01/2323 ¦¦ FOOD\n");
        // the AMOUNT is invalid
        writer.write("E ¦¦ -100 ¦¦ Lunch ¦¦ 01/01/2023 ¦¦ FOOD\n");
        // the type is invalid
        writer.write("F ¦¦ 100 ¦¦ Lunch ¦¦ 01/01/2023 ¦¦ FOOD\n");
        // missing fields
        writer.write("E ¦¦ 100 ¦¦ Lunch ¦¦ 01/01/2023\n");
        writer.write("E ¦¦ 100 ¦¦ Lunch ¦¦ FOOD\n");
        writer.write("E ¦¦ 100 ¦¦ 01/01/2023 ¦¦ FOOD\n");
        // duplicate fields
        writer.write("E ¦¦ 1 ¦¦ 0 ¦¦ 0 ¦¦ L ¦¦ u ¦¦ n ¦¦ c ¦¦ h ¦¦ 01/01/2023 ¦¦ FOOD ¦¦ FOOD\n");
        // all valid
        writer.write("E ¦¦ 100 ¦¦ TAIWANGOOD ¦¦ 01/01/2023 ¦¦ FOOD\n");
        writer.write("I ¦¦ 100 ¦¦ TAIWANGOOD ¦¦ 01/01/2023 ¦¦ SALARY\n");
        writer.close();

        File budgetFile = Storage.getBudgetFile();
        FileWriter budgetWriter = new FileWriter(budgetFile);
        budgetWriter.write("500\n2024-11-01\n");
        budgetWriter.close();

        FinancialList financialList = storage.loadFromFile(budgetLogic);
        assertEquals(2, financialList.getEntryCount());
        storage.printLoadingResult();
        String output = outputStream.toString();
        String expection = "Deleting invalid line from file: " + System.lineSeparator() +
                                "\tE ¦¦ 100 ¦¦ Lunch ¦¦ 01/01/2023 ¦¦ TAIWANGOOD" + System.lineSeparator() +
                                "\tE ¦¦ 100 ¦¦ Lunch ¦¦ 01/01/2323 ¦¦ FOOD" + System.lineSeparator() +
                                "\tE ¦¦ -100 ¦¦ Lunch ¦¦ 01/01/2023 ¦¦ FOOD" + System.lineSeparator() +
                                "\tE ¦¦ 100 ¦¦ Lunch ¦¦ 01/01/2023" + System.lineSeparator() +
                                "\tE ¦¦ 100 ¦¦ Lunch ¦¦ FOOD" + System.lineSeparator() +
                                "\tE ¦¦ 100 ¦¦ 01/01/2023 ¦¦ FOOD" + System.lineSeparator() +
                                "\tE ¦¦ 1 ¦¦ 0 ¦¦ 0 ¦¦ L ¦¦ u ¦¦ n ¦¦ c ¦¦ h ¦¦ 01/01/2023 ¦¦ FOOD ¦¦ FOOD" +
                                        System.lineSeparator() + System.lineSeparator() +
                            "I have loaded 1 expenses and 1 incomes from file." + System.lineSeparator() +
                            "Budget loaded from file." + System.lineSeparator();
        assertEquals(expection, output);
    }

    /**
     * Tests the scenario where an invalid budget amount is provided.
     * This test writes an invalid budget amount to the budget file and verifies
     * that the budget is not set in the budget logic.
     *
     * @throws IOException if an I/O error occurs during file operations.
     */
    @Test
    public void testBudgetInvaildAmount() throws IOException {
        File file = Storage.getStorageFile();
        FileWriter writer = new FileWriter(file);
        writer.write("E ¦¦ 100 ¦¦ Lunch ¦¦ 01/01/2023 ¦¦ FOOD\n");
        writer.close();

        File budgetFile = Storage.getBudgetFile();
        FileWriter budgetWriter = new FileWriter(budgetFile);
        // invalid amount
        budgetWriter.write("-500\n2024-11-01\n");
        budgetWriter.close();

        FinancialList financialList = storage.loadFromFile(budgetLogic);
        assertEquals(1, financialList.getEntryCount());
        assertEquals(null, budgetLogic.getBudget());

        storage.printLoadingResult();
        String output = outputStream.toString();
        String expection = "I have loaded 1 expenses and 0 incomes from file." + System.lineSeparator() +
                            "Budget format in file is invalid, the budget won't be set." + System.lineSeparator();
        assertEquals(expection, output);
    }

    /**
     * Tests the behavior of the storage system when an invalid date is provided in the budget file.
     * This test writes an entry with an invalid date format to the budget file and verifies that
     * the system correctly handles the invalid date by setting the budget date to the current date.
     *
     * @throws IOException if an I/O error occurs during file operations.
     */
    @Test
    public void testBudgetInvalidDate() throws IOException {
        File file = Storage.getStorageFile();
        FileWriter writer = new FileWriter(file);
        writer.write("E ¦¦ 100 ¦¦ Lunch ¦¦ 01/01/2023 ¦¦ FOOD\n");
        writer.close();

        File budgetFile = Storage.getBudgetFile();
        FileWriter budgetWriter = new FileWriter(budgetFile);
        // invalid date
        budgetWriter.write("500\n20???????1\n");
        budgetWriter.close();

        FinancialList financialList = storage.loadFromFile(budgetLogic);
        assertEquals(1, financialList.getEntryCount());
        assertEquals(LocalDate.now().toString()
            , budgetLogic.getBudget().getBudgetSetDate().toString());
        
        storage.printLoadingResult();
        String output = outputStream.toString();
        String expection = "I have loaded 1 expenses and 0 incomes from file." + System.lineSeparator() +
                            "Date in budget file is invalid, setting to current date." + System.lineSeparator();
        assertEquals(expection, output);
    }

    /**
     * Tests the scenario where a budget is set with a future date.
     * 
     * This test writes a budget entry with a future date to the budget file and verifies
     * that the budget date is reset to the current date when loaded.
     * 
     * @throws IOException if an I/O error occurs during file operations.
     */
    @Test
    public void testBudgetFutureDate() throws IOException {
        File file = Storage.getStorageFile();
        FileWriter writer = new FileWriter(file);
        writer.write("E ¦¦ 100 ¦¦ Lunch ¦¦ 01/01/2023 ¦¦ FOOD\n");
        writer.close();

        File budgetFile = Storage.getBudgetFile();
        FileWriter budgetWriter = new FileWriter(budgetFile);
        // invalid date
        budgetWriter.write("500\n2099-11-01\n");
        budgetWriter.close();

        FinancialList financialList = storage.loadFromFile(budgetLogic);
        assertEquals(1, financialList.getEntryCount());
        assertEquals(LocalDate.now().toString()
            , budgetLogic.getBudget().getBudgetSetDate().toString());
        
        storage.printLoadingResult();
        String output = outputStream.toString();
        String expection = "I have loaded 1 expenses and 0 incomes from file." + System.lineSeparator() +
                            "Date in budget file is invalid, setting to current date." + System.lineSeparator();
        assertEquals(expection, output);
    }

    /**
     * Tests the scenario where the budget file is missing a date.
     * It writes a valid financial entry to the storage file and an invalid budget entry 
     * (missing date) to the budget file.
     * Then, it loads the financial list from the storage and checks:
     * 1. The number of entries in the financial list is 1.
     * 2. The budget set date is the current date.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Test
    public void testBudgetMissingDate() throws IOException {
        File file = Storage.getStorageFile();
        FileWriter writer = new FileWriter(file);
        writer.write("E ¦¦ 100 ¦¦ Lunch ¦¦ 01/01/2023 ¦¦ FOOD\n");
        writer.write("E ¦¦ 100 ¦¦ Lunch ¦¦ 01/01/2023 ¦¦ FOOD\n");
        writer.write("E ¦¦ 100 ¦¦ Lunch ¦¦ 01/01/2023 ¦¦ FOOD\n");
        writer.close();

        File budgetFile = Storage.getBudgetFile();
        FileWriter budgetWriter = new FileWriter(budgetFile);
        // invalid date
        budgetWriter.write("500\n\n");
        budgetWriter.close();

        FinancialList financialList = storage.loadFromFile(budgetLogic);
        assertEquals(3, financialList.getEntryCount());
        assertEquals(LocalDate.now().toString()
            , budgetLogic.getBudget().getBudgetSetDate().toString());

        storage.printLoadingResult();
        String output = outputStream.toString();
        String expection = "I have loaded 3 expenses and 0 incomes from file." + System.lineSeparator() +
                            "Date in budget file is invalid, setting to current date." + System.lineSeparator();
        assertEquals(expection, output);
    }

    /**
     * Tests the deleteBudgetFromFile method of the Storage class.
     * Ensures that no exception is thrown when deleting the budget file
     * and verifies that the budget file no longer exists after deletion.
     */
    @Test
    void testDeleteBudgetFromFile() {
        File budgetFile = Storage.getBudgetFile();
        assertDoesNotThrow(() -> storage.deleteBudgetFromFile());
        assertFalse(budgetFile.exists());
    }

    /**
     * Tests the deleteFinancialListFromFile method in the Storage class.
     * Ensures that no exception is thrown when attempting to delete the financial list from the file.
     * Also verifies that the file no longer exists after the deletion.
     */
    @Test
    void testDeleteFinancialListFromFile() {
        File file = Storage.getStorageFile();
        assertDoesNotThrow(() -> storage.deleteFinancialListFromFile());
        assertFalse(file.exists());
    }
}
