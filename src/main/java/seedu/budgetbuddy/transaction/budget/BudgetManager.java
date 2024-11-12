package seedu.budgetbuddy.transaction.budget;

import seedu.budgetbuddy.Ui;
import seedu.budgetbuddy.util.LoggerSetup;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Manages the budgets for different months and years.
 * Provides methods to add, retrieve, and manage multiple budgets.
 */
public class BudgetManager {
    private static final Logger LOGGER = LoggerSetup.getLogger();
    private static int numberOfBudgets = 0;
    private static ArrayList<Budget> budgets = new ArrayList<>();
    private static BudgetManager instance;

    /**
     * Construct a BudgetManager of array content incomes
     */
    public BudgetManager(){

    }

    /**
     * Construct a BudgetManager of array content incomes
     *
     * @param budgets The content to be instantiated
     * @param numberOfBudgets The initial count of budgets managed by BudgetManager.
     */
    public BudgetManager(ArrayList<Budget> budgets, int numberOfBudgets){
        BudgetManager.budgets = budgets;
        BudgetManager.numberOfBudgets = numberOfBudgets;
    }

    /**
     * Adds a new budget to the list and increments the total number of budgets.
     * Displays a message indicating that a new budget has been added.
     *
     * @param budget The Budget object to be added.
     */
    public static void addBudget(Budget budget) {
        assert budget != null : "Budget to be added cannot be null";
        budgets.add(budget);
        numberOfBudgets++;
        LOGGER.info("Added budget: " + budget);
    }

    /**
     * Deletes budget from the list and decrements the total number of budgets.
     * Displays a message indicating that a budget is deleted.
     *
     * @param budget The Budget object to be added.
     */
    public static void deleteBudget(Budget budget) {
        assert budget != null : "Budget to be deleted cannot be null";
        budgets.remove(budget);
        numberOfBudgets--;
        LOGGER.info("Deleted budget: " + budget.getDate());
        Ui.displayBudgetDeletedMessage(budget.getDate(), numberOfBudgets);
    }

    /**
     * Returns the current number of budgets.
     *
     * @return The total number of budgets.
     */
    public static int getNumberOfBudgets() {
        return numberOfBudgets;
    }

    /**
     * Retrieves a budget for the specified YearMonth date.
     *
     * @param date The YearMonth representing the month and year for the budget.
     * @return The existing Budget for the specified date, or null if no budget exists.
     */
    public static Budget getBudget(YearMonth date) {
        assert date != null : "Date cannot be null";
        for (Budget budget : budgets) {
            if (budget.getDate().equals(date)) {
                LOGGER.info("Retrieved budget for date: " + date);
                return budget;
            }
        }
        LOGGER.info("No budget found for date: " + date);
        return null; // No budget found for the specified date
    }

    /**
     * Lists all or specified budgets managed by the manager.
     * Displays each budget with its corresponding number.
     */
    public static void listBudgets(YearMonth date) {
        if (budgets.isEmpty()) {
            Ui.displayToUser("No budgets recorded.");
            return;
        }

        String result = "";

        // Sort the budgets by YearMonth in descending order
        budgets.sort((b1, b2) -> b2.getDate().compareTo(b1.getDate()));

        if (date == null) {
            LOGGER.info("No date specified for listing budget.");

            result += "Listing up to the 12 most recent budgets:\n";

            int entriesToDisplay = Math.min(budgets.size(), 12);
            for (int counter = 1; counter <= entriesToDisplay; counter++) {
                Budget budget = budgets.get(counter - 1);
                result += counter + ". " + budget.toString();
                if (counter < entriesToDisplay) {
                    result += "\n";
                }
            }
        } else {
            // Assume validator guarantees date is valid
            LOGGER.info("Listing budgets for date: " + date);

            Budget budget = getBudget(date);

            if (budget != null) {
                result += "Here is the budget for the specified month:\n";
                result += budget.toString();
            } else {
                result += "No budget found for date: " + date;
            }
        }
        Ui.displayToUser(result);
    }

    /**
     * A get-function to obtain the information in the current Budget List.
     *
     * @return return the budget ArrayList
     */
    public static ArrayList<Budget> getBudgets() {
        return budgets;
    }

    /**
     * Resets the state of the BudgetManager by clearing all budgets and
     * setting the total number of budgets to zero.
     * <p>
     * This method is used for unit testing, ensuring that each test
     * starts with a clean slate and does not retain any state from
     * previous tests.
     * </p>
     */
    public static void reset() {
        numberOfBudgets = 0;
        budgets.clear();
    }

    /**
     * Provides a global point of access to the single instance of the BudgetManager class.
     * <p>
     * This method ensures that only one instance of BudgetManager exists throughout the application's lifetime.
     * If an instance does not exist, it creates a new one. Otherwise, it returns the existing instance.
     * This method is used for unit testing for Storage to ensure that stored values are correct.
     * </p>
     *
     * @return The single instance of the BudgetManager class.
     */
    public static BudgetManager getInstance() {
        if (instance == null) {
            instance = new BudgetManager(budgets, numberOfBudgets); // Pass static fields to constructor
        }
        return instance;
    }

}
