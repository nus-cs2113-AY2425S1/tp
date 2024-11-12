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

    /**
     * Prompts the user to set a budget if it is not already set, or it was set in a previous month.
     * Recalculates the budget balance based on current month data.
     *
     * @param financialList the list of financial entries to consider.
     * @throws FinanceBuddyException if an error occurs during the budget setting process.
     */
    public void promptUserToSetBudget(FinancialList financialList) throws FinanceBuddyException {
        if (!budget.isBudgetSet()) {
            logger.log(LogLevels.INFO, "Prompting user to set budget.");
            ui.displaySetBudgetMessage();

            if (!shouldSetBudget()) {
                Commons.printSingleLineWithBars("Budget setting skipped.");
                logger.log(LogLevels.INFO, "Budget setting skipped.");
                recalculateBalance(financialList);
                return;
            }

            final double amount = getValidBudgetAmountFromUser();
            handleSetBudget(financialList, amount);
            recalculateBalance(financialList);
            return;
        }

        LocalDate budgetSetDate = budget.getBudgetSetDate();
        if (!isCurrentMonth(budgetSetDate)) {
            logger.log(LogLevels.INFO, "Prompting user to set budget.");
            System.out.println("Your budget of " + budget.getBudgetAmountString() + " was set in a previous month.");
            ui.displaySetBudgetMessage();

            if (!shouldSetBudget()) {
                Commons.printSingleLineWithBars("Using previous budget of " + budget.getBudgetAmountString() + ".");
                logger.log(LogLevels.INFO, "Using previous budget.");
                recalculateBalance(financialList);
                budget.setBudgetSetDate(LocalDate.now());
                return;
            }

            final double amount = getValidBudgetAmountFromUser();
            handleSetBudget(financialList, amount);
            recalculateBalance(financialList);
        }
    }

    /**
     * Handles the process of setting the budget amount by receiving input from the user.
     * Validates that the input is a non-negative number before setting the budget.
     */
    public void handleSetBudget(FinancialList financialList, double amount) throws FinanceBuddyException {
        budget.setBudgetAmount(amount);

        recalculateBalance(financialList);

        ui.displayBudgetSetMessage(budget.getBudgetAmount(), budget.getBalance());
        logger.log(LogLevels.INFO, "Budget set to " + String.format("$ %.2f", budget.getBudgetAmount()) + ".");
    }

    /**
     * Prompts the user to confirm whether to set the budget and validates the input.
     *
     * @return true if the user confirms to set the budget, false otherwise.
     */
    private boolean shouldSetBudget() {
        while (true) {
            String input = ui.getUserInput();
            if (input.equalsIgnoreCase("yes")) {
                return true;
            } else if (input.equalsIgnoreCase("no")) {
                return false;
            } else {
                Commons.printSingleLineWithBars("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }

    /**
     * Prompts the user to input a valid budget amount and validates the input.
     *
     * @return a valid budget amount entered by the user.
     */
    private double getValidBudgetAmountFromUser() {
        while (true) {
            Commons.printSingleLineWithBars("Please set your budget amount:");
            try {
                String amountInput = ui.getUserInput();
                double amount = Double.parseDouble(amountInput);
                if (isValidBudgetAmount(amount)) {
                    return amount;
                }
            } catch (NumberFormatException e) {
                Commons.printSingleLineWithBars("Invalid input. Please enter a valid number:");
                logger.log(LogLevels.WARNING, "Invalid number entered.");
            }
        }
    }

    /**
     * Validates if the given budget amount is valid.
     *
     * @param amount the budget amount to validate.
     * @return true if the amount is valid (>= $0.01), false otherwise.
     */
    public boolean isValidBudgetAmount(double amount) {
        if (amount >= 0.01 && amount <= 9999999.00) {
            return true;
        } else {
            logger.log(LogLevels.WARNING, "Amount less than $0.01 and more than $9999999.00 entered.");
            return false;
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
    public void printBudgetAndBalance() {
        if (!budget.isBudgetSet()) {
            System.out.println("No budget has been set.");
            System.out.println("--------------------------------------------");
            return;
        }
        System.out.println("Your current budget is: " + budget.getBudgetAmountString());
        printBalanceAmount();
    }

    /**
     * Prints the current balance amount to the user interface if budget is set.
     * Does nothing if budget has not been set.
     */
    public void printBalanceAmount() {
        if (!budget.isBudgetSet()) {
            return;
        }
        ui.displayBudgetBalanceMessage(budget.getBalance());
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
     * Resets the budget to zero and marks it as not set.
     *
     * <p>This method clears the current budget amount and balance by resetting them to zero,
     * updates the budget status to "not set," and logs the reset action. It also displays
     * a message to inform the user that the budget has been reset.</p>
     *
     * <p>Assertions:</p>
     * <ul>
     *     <li>The budget amount is reset to {@code 0.0}.</li>
     *     <li>The balance is reset to {@code 0.0}.</li>
     * </ul>
     *
     * <p>Note: Ensure that assertions are enabled when running the application to validate the state after reset.</p>
     */
    public void resetBudget() {
        budget.resetBudgetAmount();
        logger.log(LogLevels.INFO, "Budget reset to 0 and marked as not set.");
        ui.displayBudgetResetMessage();
        assert budget.getBudgetAmount() == 0.0;
        assert budget.getBalance() == 0.0;
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
            logger.log(LogLevels.INFO, "Balance updated to " + budget.getBalanceString() + ".");
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
            logger.log(LogLevels.INFO, "Balance updated to " + budget.getBalanceString() + ".");
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
