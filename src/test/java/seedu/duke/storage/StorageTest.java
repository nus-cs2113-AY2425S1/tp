package seedu.duke.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.budget.Budget;
import seedu.duke.exception.FinanceBuddyException;
import seedu.duke.financial.Expense;
import seedu.duke.financial.FinancialList;
import seedu.duke.financial.Income;
import seedu.duke.logic.BudgetLogic;
import seedu.duke.ui.AppUi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;




public class StorageTest {

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
    }

    /**
     * Tests the getStorageFile method of the Storage class.
     * This test verifies that the file returned by getStorageFile exists.
     */
    @Test
    public void testGetStorageFile() {
        File file = Storage.getStorageFile();
        assertTrue(file.exists());
    }

    /**
     * Tests the getBudgetFile method of the Storage class.
     * This test verifies that the budget file exists.
     */
    @Test
    public void testGetBudgetFile() {
        File file = Storage.getBudgetFile();
        assertTrue(file.exists());
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
        Budget tmpBudget = new Budget();
        tmpBudget.setBudgetAmount(500);
        budgetLogic.overwriteBudget(tmpBudget);

        storage.update(financialList, budgetLogic);

        File file = Storage.getStorageFile();
        Scanner scanner = new Scanner(file);
        assertTrue(scanner.hasNextLine());
        scanner.close();

        File budgetFile = Storage.getBudgetFile();
        Scanner budgetScanner = new Scanner(budgetFile);
        assertTrue(budgetScanner.hasNextLine());
        budgetScanner.close();
    }

    @Test
    public void testCheckParameters() {
        /**
         * Creates a DateTimeFormatter with the pattern "dd/MM/yy".
         * This formatter can be used to format or parse dates in the specified pattern.
         */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
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
        String[] tokens = {"E", "100", "Lunch", "01/01/23", "FOOD"};
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
        String[] tokens = {"I", "200", "Salary", "01/01/23", "SALARY"};
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
        writer.write("E ¦¦ 100 ¦¦ Lunch ¦¦ 01/01/23 ¦¦ FOOD\n");
        writer.close();

        File budgetFile = Storage.getBudgetFile();
        FileWriter budgetWriter = new FileWriter(budgetFile);
        budgetWriter.write("500\n2024-11-01\n");
        budgetWriter.close();

        FinancialList loadedList = storage.loadFromFile(budgetLogic);
        assertEquals(1, loadedList.getEntryCount());
        assertEquals(500, budgetLogic.getBudget().getBudgetAmount());
        assertEquals("01/11/24", 
            budgetLogic.getBudget().getBudgetSetDate().format(DateTimeFormatter.ofPattern("dd/MM/yy")));
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
    }
}
