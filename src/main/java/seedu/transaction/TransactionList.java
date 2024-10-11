package seedu.transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionList {
    private List<Transaction> transactions;

    public TransactionList() {
        transactions = new ArrayList<>();
    }

    //Add Transaction
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        System.out.println("Transaction added: " + transaction);
    }

    // Delete Transaction
    public void deleteTransaction(int index) {
        if (index >= 0 && index < transactions.size()) {
            Transaction removed = transactions.remove(index);
            System.out.println("Transaction removed: " + removed);
        } else {
            System.out.println("Invalid transaction index!");
        }
    }

    // List Transaction
    public void listTransactions() {
        System.out.println("All transactions:");
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println((i + 1) + ". " + transactions.get(i));
        }
    }

    // Get Transaction List
    public List<Transaction> getTransactions() {
        return transactions;
    }
}
