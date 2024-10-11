package seedu;

import java.util.ArrayList;
import java.util.List;

class TransactionList {
    private List<seedu.functions.Transaction> transactions;

    public TransactionList() {
        transactions = new ArrayList<>();
    }

    // 添加交易
    public void addTransaction(seedu.functions.Transaction transaction) {
        transactions.add(transaction);
        System.out.println("seedu.functions.Transaction added: " + transaction);
    }

    // 删除交易
    public void deleteTransaction(int index) {
        if (index >= 0 && index < transactions.size()) {
            seedu.functions.Transaction removed = transactions.remove(index);
            System.out.println("seedu.functions.Transaction removed: " + removed);
        } else {
            System.out.println("Invalid transaction index!");
        }
    }

    // 列出所有交易
    public void listTransactions() {
        System.out.println("All transactions:");
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println((i + 1) + ". " + transactions.get(i));
        }
    }

    // 获取交易列表
    public List<seedu.functions.Transaction> getTransactions() {
        return transactions;
    }
}
