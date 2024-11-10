package seedu.duke.logic;

import seedu.duke.budget.Budget;
import seedu.duke.exception.FinanceBuddyException;
import seedu.duke.financial.Expense;
import seedu.duke.financial.FinancialEntry;
import seedu.duke.financial.FinancialList;
import seedu.duke.log.Log;
import seedu.duke.log.LogLevels;
import seedu.duke.parser.DateParser;
import seedu.duke.ui.AppUi;
import seedu.duke.util.Commons;

import java.time.LocalDate;

/**
 * Handles the logic related to setting and modifying the budget.
 */
public class BudgetLogic {
    private static final Log logger = Log.getInstance();
    public Budget budget;
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
     * Overwrites the current budget with the specified budget.
     *
     * @param budget The new budget to be set.
     */
    public void overwriteBudget(Budget budget){
        this.budget = budget;
    }

    /**
     * Retrieves the current budget.
     *
     * @return the current budget
     */
    public Budget getBudget() {
        return budget;
    }

    public void promptUserToSetBudget(FinancialList financialList) throws FinanceBuddyException {
        LocalDate budgetSetDate = budget.getBudgetSetDate();
        if (!isCurrentMonth(budgetSetDate)) {
            System.out.println("Your budget was set in a previous month.");
            setBudget(financialList);
            return;
        }
        if (!budget.isBudgetSet()) {
            setBudget(financialList);
            return;
        }
        recalculateBalance(financialList);
    }

    /**
     * Sets the budget if it has not been set. If the budget is already set,
     * prompts the user to confirm whether they want to modify it.
     */
    public void setBudget(FinancialList financialList) throws FinanceBuddyException {
        if (!budget.isBudgetSet()) {
            logger.log(LogLevels.INFO, "Budget has not been set.");
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
                Commons.printSingleLineWithBars("Invalid input. Please enter 'yes' or 'no'.");
            }
        }

        if (input.equalsIgnoreCase("yes")) {
            Commons.printSingleLineWithBars("Please set your budget amount:");

            double amount = 0;
            boolean isAmountValid = false;

            while (!isAmountValid) {
                try {
                    String amountInput = ui.getUserInput();
                    amount = Double.parseDouble(amountInput);

                    if (amount >= 0.01) {
                        isAmountValid = true;
                    } else {
                        Commons.printSingleLineWithBars(
                                "Budget amount must be >= $0.01. Please enter a valid amount.");
                        logger.log(LogLevels.WARNING, "Amount less than $0.01 entered.");
                    }

                } catch (NumberFormatException e) {
                    Commons.printSingleLineWithBars("Invalid input. Please enter a valid number:");
                    logger.log(LogLevels.WARNING, "Invalid number entered.");
                }
            }

            budget.setBudgetAmount(amount);
            recalculateBalance(financialList);
            System.out.println(Commons.LINE_SEPARATOR);
            System.out.println("Your budget has successfully been set to: " +
                    String.format("$ %.2f", budget.getBudgetAmount()));
            System.out.println("Your current monthly balance is: " +
                    String.format("$ %.2f", budget.getBalance()));
            System.out.println(Commons.LINE_SEPARATOR);
            logger.log(LogLevels.INFO, "Budget set to " + String.format("$ %.2f", budget.getBudgetAmount()) + ".");
        } else {
            recalculateBalance(financialList);
            Commons.printSingleLineWithBars("Budget setting skipped.");
            logger.log(LogLevels.INFO, "Budget setting skipped.");
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
     * Displays the current budget and balance, if the budget is set.
     * Otherwise, notifies the user that the budget is not set.
     */
    public void getBudgetAndBalance() {
        if (!budget.isBudgetSet()) {
            System.out.println("No budget has been set.");
            System.out.println("--------------------------------------------");
            return;
        }
        String budgetAmount = String.format("$ %.2f", budget.getBudgetAmount());
        String balanceAmount = String.format("$ %.2f", budget.getBalance());
        System.out.println("Your current budget is: " + budgetAmount);
        System.out.println("Your current monthly balance is: " + balanceAmount);
        System.out.println("--------------------------------------------");
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
     * Adjusts the budget balance upon recording an expense given as a string,
     * if the expense date is in the current month and year. It notifies the user
     * if the budget is exceeded.
     *
     * @param amount the expense amount.
     * @param date   the date of the expense as a string.
     * @throws FinanceBuddyException if an error occurs during parsing the date.
     */
    public void changeBalanceFromExpenseString(double amount, String date) throws FinanceBuddyException {
        if (!budget.isBudgetSet()) {
            return;
        }
        LocalDate parsedDate = DateParser.parse(date);
        if (isCurrentMonth(parsedDate)) {
            modifyBalance(amount);
            if (hasExceededBudget()) {
                ui.displayBudgetBalanceExceededMessage(budget.getBudgetAmount());
                logger.log(LogLevels.INFO, "Budget has been exceeded.");
            }
            ui.displayBudgetBalanceMessage(budget.getBalance());
            logger.log(LogLevels.INFO, "Balance updated to " + String.format("$ %.2f", budget.getBalance()) + ".");
        }

    }

    /**
     * Adjusts the budget balance upon recording an expense,
     * if the expense date is in the current month. It notifies the user
     * if the budget is exceeded.
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
            if (hasExceededBudget()) {
                ui.displayBudgetBalanceExceededMessage(budget.getBudgetAmount());
                logger.log(LogLevels.INFO, "Budget has been exceeded.");
            }
            ui.displayBudgetBalanceMessage(budget.getBalance());
            logger.log(LogLevels.INFO, "Balance updated to " + String.format("$ %.2f", budget.getBalance()) + ".");
        }
    }

    /**
     * Recalculates the balance based on the budget amount and expenses in the financial list
     * that occurred in the current month. It notifies the user if the budget is exceeded.
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
        if (hasExceededBudget()) {
            ui.displayBudgetBalanceExceededMessage(budget.getBudgetAmount());
        }
        logger.log(LogLevels.INFO, "Recalculation of balance complete.");
    }
}
