package seedu.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.category.Category;
import seedu.transaction.Expense;
import seedu.transaction.Income;
import seedu.transaction.Transaction;
import seedu.transaction.TransactionList;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ViewTotalCommandTest {

    private ViewTotalCommand viewTotalCommand;
    private TransactionList inputTransactionList;
    private Transaction item1;
    private Transaction item2;
    private Transaction item3;
    private Transaction item4;

    @BeforeEach
    public void setUp() throws Exception {

        inputTransactionList = new TransactionList();
        item1 = new Income(300, "", "2024-01-15");
        inputTransactionList.addTransaction(item1);
        item2 = new Income(300, "", "2024-02-15");
        inputTransactionList.addTransaction(item2);
        item3 = new Expense(300, "", "2024-03-15", new Category("Abc"));
        inputTransactionList.addTransaction(item3);
        item4 = new Expense(100, "", "2024-01-15", new Category("Abc"));
        inputTransactionList.addTransaction(item4);
    }

    @Test
    public void execute_ViewTotalNoTransactions() {
        TransactionList emptyTransactionList = new TransactionList();
        viewTotalCommand = new ViewTotalCommand(emptyTransactionList);

        List<String> result = viewTotalCommand.execute();

        assertEquals("Total Income: $0.00", result.get(0));
        assertEquals("Total Expenses: $0.00", result.get(1));
        assertEquals("Net Total: $0.00", result.get(2));
    }

    @Test
    public void execute_ViewTotalAllTransactions() {
        viewTotalCommand = new ViewTotalCommand(inputTransactionList);
        List<String> result = viewTotalCommand.execute();

        assertEquals("Total Income: $600.00", result.get(0));
        assertEquals("Total Expenses: $400.00", result.get(1));
        assertEquals("Net Total: $200.00", result.get(2));
    }

}