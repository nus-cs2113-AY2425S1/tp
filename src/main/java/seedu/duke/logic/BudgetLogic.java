package seedu.duke.logic;

import seedu.duke.budget.Budget;
import seedu.duke.exception.FinanceBuddyException;
import seedu.duke.financial.Expense;
import seedu.duke.financial.FinancialEntry;
import seedu.duke.financial.FinancialList;
import seedu.duke.parser.DateParser;
import seedu.duke.ui.AppUi;

import java.time.LocalDate;

/**
 * Handles the logic related to setting and modifying the budget.
 */
public class BudgetLogic {
    public final Budget budget;
    private final AppUi ui;

    /**
     * Constructs a BudgetLogic instance with the specified Budget and AppUi.
     *
     * @param budget the budget instance to be managed.
     * @param ui     the user interface instance for interacting with the user.
     */
    public BudgetLogic(Budget budget, AppUi ui) {
        this.budget = budget;
        this.ui = ui;
    }

    /**
     * Sets the budget if it has not been set. If the budget is already set,
     * prompts the user to confirm whether they want to modify it.
     */
    public void setBudget(FinancialList financialList) throws FinanceBuddyException {
        if (!budget.isBudgetSet()) {
            ui.displaySetBudgetMessage();
            handleSetBudget(financialList);
        } else {
            ui.displayModifyBudgetMessage(budget.getBudgetAmount());
            handleSetBudget(financialList);
        }
    }

    /**
     * Handles the process of setting the budget amount by receiving input from the user.
     * Validates that the input is a non-negative number before setting the budget.
     */
    public void handleSetBudget(FinancialList financialList) throws FinanceBuddyException {
        String input = "";
        boolean isValidInput = false;

        while (!isValidInput) {
            input = ui.getUserInput();

            if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("no")) {
                isValidInput = true;
            } else {
                System.out.println("--------------------------------------------\n");
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                System.out.println("--------------------------------------------\n");

            }
        }

        if (input.equalsIgnoreCase("yes")) {
            System.out.println("--------------------------------------------\n");
            System.out.println("Please set your budget amount:");
            System.out.println("--------------------------------------------\n");


            double amount = 0;
            boolean isAmountValid = false;

            while (!isAmountValid) {
                try {
                    String amountInput = ui.getUserInput();
                    amount = Double.parseDouble(amountInput);

                    if (amount >= 0.01) {
                        isAmountValid = true;
                    } else {
                        System.out.println("Budget amount must be >= $0.01. Please enter a valid amount.");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number:");
                }
            }

            budget.setBudgetAmount(amount);
            recalculateBalance(financialList);
            System.out.println("--------------------------------------------\n");
            System.out.println("Your budget has successfully been set to: " + budget.getBudgetAmount());
            System.out.println("Your current monthly balance is: " + budget.getBalance());
            System.out.println("--------------------------------------------\n");
        } else {
            System.out.println("--------------------------------------------\n");
            System.out.println("Budget setting skipped.");
            System.out.println("--------------------------------------------\n");
        }
    }



    /**
     * Modifies the budget balance by adding the specified amount to the current balance.
     * A positive amount increases the balance, while a negative amount decreases it.
     *
     * @param amount the amount to adjust the balance by.
     */
    public void modifyBalance(double amount) {
        double currentBalance = budget.getBalance();
        double newBalance = currentBalance + amount;
        budget.updateBalance(newBalance);
    }

    /**
     * Checks if the budget has been exceeded.
     *
     * @return true if the budget balance is less than or equal to zero, false otherwise.
     */
    public boolean hasExceededBudget() {
        return budget.getBalance() <= 0;
    }

    /**
     * Checks if the given date is in the current month and year.
     *
     * @param date the date to check.
     * @return true if the date is in the current month and year, false otherwise.
     */
    public boolean isCurrentMonth(LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        boolean isSameYear = currentDate.getYear() == date.getYear();
        boolean isSameMonth = currentDate.getMonth() == date.getMonth();
        return isSameYear && isSameMonth;
    }

    /**
     * Adjusts the budget balance upon recording an expense,
     * if the expense date is in the current year and month.
     *
     * @param amount the expense amount.
     * @param date   the date of the expense.
     * @throws FinanceBuddyException if an error occurs during parsing the date.
     */
    public void changeBalanceFromExpense(double amount, String date) throws FinanceBuddyException {
        if (!budget.isBudgetSet()) {
            return;
        }
        LocalDate parsedDate = DateParser.parse(date);
        if (isCurrentMonth(parsedDate)) {
            modifyBalance(amount);
        }
    }

    /**
     * Adjusts the budget balance upon recording an expense, if the expense date is in the current month.
     *
     * @param amount the expense amount.
     * @param date   the date of the expense.
     */
    public void changeBalanceFromExpense(double amount, LocalDate date) {
        if (!budget.isBudgetSet()) {
            return;
        }
        if (isCurrentMonth(date)) {
            modifyBalance(amount);
        }
    }

    /**
     * Recalculates the balance based on the budget amount and expenses in the financial list
     * that occurred in the current month.
     *
     * @param financialList the list of financial entries to consider.
     * @throws FinanceBuddyException if the financial list is null.
     */
    public void recalculateBalance(FinancialList financialList) throws FinanceBuddyException {
        if (!budget.isBudgetSet()) {
            return;
        }
        if (financialList == null) {
            throw new FinanceBuddyException("Financial list is null");
        }
        double balance = budget.getBudgetAmount();
        for (int i = 0; i < financialList.getEntryCount(); i++) {
            FinancialEntry entry = financialList.getEntry(i);
            LocalDate date = entry.getDate();
            if (isCurrentMonth(date) && entry instanceof Expense) {
                balance -= entry.getAmount();
            }
        }
        budget.updateBalance(balance);
    }
}
