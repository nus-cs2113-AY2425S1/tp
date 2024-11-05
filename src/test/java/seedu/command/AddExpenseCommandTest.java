package seedu.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.category.Category;
import seedu.category.CategoryList;
import seedu.main.UI;
import seedu.transaction.Expense;
import seedu.transaction.Transaction;
import seedu.transaction.TransactionList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AddExpenseCommandTest {

    private TransactionList transactionList;
    private AddExpenseCommand command;
    private CategoryList categoryList;
    private UI ui;

    @BeforeEach
    void setUp() {
        transactionList = new TransactionList();
        categoryList = new CategoryList();
        command = new AddExpenseCommand(transactionList, ui, categoryList);
    }

    @Test
    public void execute_addExpenseAllValidFields_success() {
        categoryList.addCategory(new Category("Food"));
        command.setArguments(Map.of("", "dinner", "a/", "1000",
                "d/", "2024-10-01 1800", "c/", "Food"));
        List<Transaction> expectedList = List.of(new Expense(1000,
                "dinner", "2024-10-01 1800", new Category("Food")));

        command.execute();

        assertEquals(transactionList.getTransactions(), expectedList);
    }

    @Test
    public void execute_addExpenseInvalidDate_notAdding() {
        command.setArguments(Map.of("", "dinner", "a/",
                "1000", "d/", "2024", "c/", "Food"));
        List<Transaction> expectedList = new ArrayList<>();

        command.execute();

        assertEquals(transactionList.getTransactions(), expectedList);
    }

    @Test
    public void execute_addExpenseNoDate_success() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String currentDateTime = LocalDateTime.now().format(formatter);
        categoryList.addCategory(new Category("Food"));

        command.setArguments(Map.of("", "dinner", "a/", "1000", "c/", "Food"));
        List<Transaction> expectedList = List.of(new Expense(1000, "dinner", currentDateTime, new Category("Food")));

        command.execute();

        assertEquals(transactionList.getTransactions(), expectedList);
    }

}
