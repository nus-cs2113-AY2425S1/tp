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
import static org.junit.jupiter.api.Assertions.*;




public class StorageTest {

    private Storage storage;
    private FinancialList financialList;
    private BudgetLogic budgetLogic;
    private Budget budget;
    private AppUi ui;

    @BeforeEach
    public void setUp() {
        storage = new Storage();
        financialList = new FinancialList();
        budgetLogic = new BudgetLogic(budget,ui);
    }

    @Test
    public void testGetStorageFile() {
        File file = Storage.getStorageFile();
        assertTrue(file.exists());
    }

    @Test
    public void testGetBudgetFile() {
        File file = Storage.getBudgetFile();
        assertTrue(file.exists());
    }

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        LocalDate date = LocalDate.now();

        assertDoesNotThrow(() -> storage.checkParameters(100, "Description", formatter, date));
        assertThrows(FinanceBuddyException.class, () -> storage.checkParameters(-1, "Description", formatter, date));
        assertThrows(FinanceBuddyException.class, () -> storage.checkParameters(10000000, "Description", formatter, date));
        assertThrows(FinanceBuddyException.class, () -> storage.checkParameters(100, "", formatter, date));
        assertThrows(FinanceBuddyException.class, () -> storage.checkParameters(100, "Description", formatter, date.plusDays(1)));
    }

    @Test
    public void testParseExpense() {
        String[] tokens = {"E", "100", "Lunch", "01/01/23", "FOOD"};
        Expense expense = assertDoesNotThrow(() -> storage.parseExpense(tokens));
        assertEquals(100, expense.getAmount());
        assertEquals("Lunch", expense.getDescription());
        assertEquals(LocalDate.of(2023, 1, 1), expense.getDate());
        assertEquals(Expense.Category.FOOD, expense.getCategory());
    }

    @Test
    public void testParseIncome() {
        String[] tokens = {"I", "200", "Salary", "01/01/23", "SALARY"};
        Income income = assertDoesNotThrow(() -> storage.parseIncome(tokens));
        assertEquals(200, income.getAmount());
        assertEquals("Salary", income.getDescription());
        assertEquals(LocalDate.of(2023, 1, 1), income.getDate());
        assertEquals(Income.Category.SALARY, income.getCategory());
    }

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
        assertEquals("01/11/24", budgetLogic.getBudget().getBudgetSetDate().format(DateTimeFormatter.ofPattern("dd/MM/yy")));
    }

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