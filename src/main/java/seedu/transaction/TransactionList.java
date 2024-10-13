package seedu.transaction;

import seedu.category.Category;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionList {
    private final List<Transaction> transactions;

    public TransactionList() {
        transactions = new ArrayList<>();
        initializeDefaultTransactions();
    }

    private void initializeDefaultTransactions() {
        // Optionally initialize with some default transactions if needed.
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        System.out.println("Transaction added: " + transaction);
    }

    public void deleteTransaction(int index) {
        if (index >= 0 && index < transactions.size()) {
            Transaction removed = transactions.remove(index);
            System.out.println("Transaction removed: " + removed);
        } else {
            System.out.println("Invalid transaction index!");
        }
    }

    public void listTransactions() {
        System.out.println("All transactions:");
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println((i + 1) + ". " + transactions.get(i));
        }
    }

    public List<Transaction> getTransactions() {

        return transactions;
    }

    // Method to get transactions between a start time and an end time
    public List<Transaction> getTransactionsBetween(LocalDateTime startTime, LocalDateTime endTime) {
        return transactions.stream()
                .filter(transaction -> (transaction.getDate().isEqual(startTime) ||
                        transaction.getDate().isAfter(startTime)) &&
                        (transaction.getDate().isEqual(endTime) || transaction.getDate().isBefore(endTime)))
                .collect(Collectors.toList());
    }

    // Method to get transactions of a specific category for expenses
    public List<Expense> getExpensesByCategory(Category category) {
        return transactions.stream()
                .filter(transaction -> transaction instanceof Expense)
                .map(transaction -> (Expense) transaction)
                .filter(expense -> expense.getCategory().equals(category))
                .collect(Collectors.toList());
    }
}

