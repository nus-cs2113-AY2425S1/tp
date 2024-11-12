package seedu.budgetbuddy.transaction.income;

import seedu.budgetbuddy.Ui;
import seedu.budgetbuddy.util.LoggerSetup;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages a collection of income transactions.
 * Provides functionality to add, delete, and list incomes.
 */
public class IncomeManager {
    private static final Logger LOGGER = LoggerSetup.getLogger();
    private static int numberOfIncomes = 0;
    private static ArrayList<Income> incomes = new ArrayList<>();
    private static IncomeManager instance;

    /**
     * Construct a IncomeManager of array content incomes
     *
     * @param incomes is the content to be instantiated
     */
    public IncomeManager(ArrayList<Income> incomes, int numberOfIncomes) {
        assert numberOfIncomes >= 0 : "numberOfIncomes should be greater than 0";
        IncomeManager.incomes = incomes;
        IncomeManager.numberOfIncomes = numberOfIncomes;
    }

    /**
     * Construct a IncomeManager of array content incomes
     */
    public IncomeManager() {

    }

    /**
     * Adds a new income to the manager.
     *
     * @param income The income to be added.
     */
    public static void addIncome(Income income) {
        incomes.add(income);
        numberOfIncomes++;
        String result = "The following income transaction has been added:\n"
                + income + '\n'
                + "You have " + numberOfIncomes + " income transaction(s) in total.";
        Ui.displayToUser(result);
    }

    /**
     * Load a new income from storage to the manager.
     *
     * @param income The income to be added.
     */
    public static void loadIncome(Income income) {
        incomes.add(income);
        numberOfIncomes++;
    }

    /**
     * Deletes an income from the manager at the specified index.
     *
     * @param index The index of the income to be deleted.
     */
    public static void deleteIncome(int index) {
        numberOfIncomes--;
        String result = "The following income transaction has been deleted:\n"
                + incomes.get(index) + '\n'
                + "You have " + numberOfIncomes + " income transaction(s) in total.";
        incomes.remove(index);
        Ui.displayToUser(result);
    }

    /**
     * Returns the current number of incomes.
     *
     * @return The total number of incomes.
     */
    public static int getNumberOfIncomes() {
        return numberOfIncomes;
    }

    /**
     * Calculates the total income for a specified month.
     *
     * @param month The month to calculate income for.
     * @return The total income for the month; returns 0.0 if no income is found.
     */
    public static double getMonthlyIncome(YearMonth month) {
        double sum = 0;
        for (Income income : incomes) {
            if(month.equals(getYearMonthFromDate(income.getDate()))) {
                sum += income.getAmount();
            }
        }
        return sum;
    }

    /**
     * Lists all the incomes managed by the manager.
     * Displays each income with its corresponding number.
     */
    public static void listIncomes() {
        if (numberOfIncomes == 0) {
            Ui.displayToUser("There are currently no income entries. Try again after adding an income entry.");
            return;
        }
        String result = "";
        int counter = 0;
        double sumOfIncome = 0;
        for (Income income : incomes) {
            counter++;
            result += counter + ". " + income.toString() + "\n";
            sumOfIncome += income.getAmount();
        }
        result += "There are " + counter + " income(s) in total" +
                ", with a sum of $" + String.format("%.2f", sumOfIncome) + ".";
        LOGGER.log(Level.INFO, "Listing {0} incomes", numberOfIncomes);
        Ui.displayToUser(result);
    }

    /**
     * List all income that matches with month field that are managed by the manager.
     * Displays each income with its corresponding number.
     * @param month
     */
    public static void listIncomeWithMonth(YearMonth month) {
        String result = "";
        int counter = 0;
        double filteredIncomeSum = 0;
        String monthInString;

        for (Income income : incomes) {
            if (month.equals(getYearMonthFromDate(income.getDate()))) {
                counter++;
                result += counter + ". " + income.toString() + "\n";
                filteredIncomeSum += income.getAmount();
            }
        }
        if (result.equals("")) {
            result = getEmptyDisplayMessage();
            Ui.displayToUser(result);
            return;
        }
        monthInString = month.format(DateTimeFormatter.ofPattern("MMMM yyyy"));
        result += "There are " + counter + " income(s) in total for " + monthInString +
                ", with a sum of $" + filteredIncomeSum + ".";
        LOGGER.log(Level.INFO, "Listing {0} incomes", numberOfIncomes);
        Ui.displayToUser(result);
    }

    /**
     * Gets an Income object based on user input index.
     * Searches IncomeList for Income object with desired index and returns reference to object.
     *
     * @param index user input index to be extracted from IncomeList
     * @return an income object for future reference
     */
    public static Income getIncomeByIndex(int index) {
        if (index > numberOfIncomes) {
            return null;
        }
        return incomes.get(index-1);
    }

    /**
     * Extract YearMonth value from date
     * @param date
     * @return
     */
    public static YearMonth getYearMonthFromDate(LocalDate date) {
        return YearMonth.from(date);
    }

    /**
     * Generates a custom empty Display income message
     * @return custom display income message
     */
    public static String getEmptyDisplayMessage() {
        return "No income entry with given month found, try again with a different month.";
    }

    /**
     * A get-function to obtain the information in the current Income List.
     *
     * @return return the income ArrayList
     */
    public static ArrayList<Income> getIncomes() {
        return incomes;
    }

    /**
     * Resets the state of the IncomeManager by clearing all incomes and
     * setting the total number of incomes to zero.
     * <p>
     * This method is used for unit testing, ensuring that each test
     * starts with a clean slate and does not retain any state from
     * previous tests.
     * </p>
     */
    public static void reset() {
        numberOfIncomes = 0;
        incomes.clear();
    }

    /**
     * Provides a global point of access to the single instance of the IncomeManager class.
     * <p>
     * This method ensures that only one instance of IncomeManager exists throughout the application's lifetime.
     * If an instance does not exist, it creates a new one. Otherwise, it returns the existing instance.
     * This method is used for unit testing for Storage to ensure that stored values are correct.
     * </p>
     *
     * @return The single instance of the IncomeManager class.
     */
    public static IncomeManager getInstance() {
        if (instance == null) {
            instance = new IncomeManager(incomes, numberOfIncomes); // Pass static fields to constructor
        }
        return instance;
    }
}
