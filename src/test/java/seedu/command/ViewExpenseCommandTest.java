package seedu.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.category.Category;
import seedu.transaction.Expense;
import seedu.transaction.Income;
import seedu.transaction.Transaction;
import seedu.transaction.TransactionList;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ViewExpenseCommandTest {

    private ViewExpenseCommand viewExpenseCommand;
    private TransactionList inputTransactionList;
    Transaction item1;
    Transaction item2;
    Transaction item3;

    @BeforeEach
    public void setUp() {
        TransactionList transactionList = new TransactionList();
        viewExpenseCommand = new ViewExpenseCommand(transactionList);

        inputTransactionList = new TransactionList();
        item1 = new Expense(300, "", "2024-01-15", new Category("Other"));
        inputTransactionList.addTransaction(item1);
        item2 = new Expense(300, "", "2024-01-15", new Category("Abc"));
        inputTransactionList.addTransaction(item2);
        item3 = new Income(300, "", "2024-01-15");
        inputTransactionList.addTransaction(item3);
    }

    @Test
    void setTransactionList_newTransactionList_TransactionList()
            throws NoSuchFieldException, IllegalAccessException{

        // Set transactions
        viewExpenseCommand.setTransactionList(inputTransactionList);


        Field transactionsField = ViewExpenseCommand.class.getDeclaredField("transactionList");
        transactionsField.setAccessible(true); // Make private field accessible
        TransactionList commandTransactionList = (TransactionList) transactionsField.get(viewExpenseCommand);

        // Verify the result
        assertEquals(inputTransactionList, commandTransactionList);
    }

    @Test
    void execute_withoutCategory_show2Expenses() {
        // Set transactions
        viewExpenseCommand.setTransactionList(inputTransactionList);

        // Expected messages
        List<String> expectedMessages = new ArrayList<>();
        expectedMessages.add("1. "+item1.toString());
        expectedMessages.add("2. "+item2.toString());
        // Execute the command
        List<String> messages = viewExpenseCommand.execute();

        // Verify the result
        assertEquals(expectedMessages, messages);
    }

    @Test
    void execute_withCategory_show1ExpenseWithOtherCategory() {
        // Set transactions
        viewExpenseCommand.setTransactionList(inputTransactionList);

        Map<String, String> arguments = new HashMap<>();
        arguments.put("c/", "Other");

        viewExpenseCommand.setArguments(arguments);
        // Expected messages
        List<String> expectedMessages = new ArrayList<>();
        expectedMessages.add("1. "+item1.toString());

        // Execute the command
        List<String> messages = viewExpenseCommand.execute();

        // Verify the result
        assertEquals(expectedMessages, messages);
    }

    @Test
    void execute_withNotExistCategory_showNoExpenseMessage() {
        // Set transactions
        viewExpenseCommand.setTransactionList(inputTransactionList);

        Map<String, String> arguments = new HashMap<>();
        arguments.put("c/", "Lmao");

        viewExpenseCommand.setArguments(arguments);
        // Expected messages
        List<String> expectedMessages = new ArrayList<>();
        expectedMessages.add(ViewExpenseCommand.EXPENSE_EMPTY_MESSAGE);

        // Execute the command
        List<String> messages = viewExpenseCommand.execute();

        // Verify the result
        assertEquals(expectedMessages, messages);
    }
}