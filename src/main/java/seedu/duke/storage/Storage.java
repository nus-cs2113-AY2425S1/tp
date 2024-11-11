package seedu.duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import seedu.duke.budget.Budget;
import seedu.duke.exception.FinanceBuddyException;
import seedu.duke.financial.Expense;
import seedu.duke.financial.FinancialList;
import seedu.duke.financial.Income;
import seedu.duke.log.Log;
import seedu.duke.log.LogLevels;
import seedu.duke.logic.BudgetLogic;

/**
 * The Storage class handles the reading and writing of financial data to a storage file.
 * It ensures that the storage file and its parent directories are created if they do not exist.
 * The class provides methods to update the storage file with financial entries, parse expenses and incomes,
 * and load financial data from the storage file into a FinancialList.
 * 
 * <p>Usage example:</p>
 * <pre>
 * {@code
 * Storage storage = new Storage();
 * FinancialList financialList = storage.loadFromFile();
 * storage.update(financialList);
 * }
 * </pre>
 * 
 * <p>Methods:</p>
 * <ul>
 *   <li>{@link #getStorageFile()} - Retrieves the storage file, creating it if necessary.</li>
 *   <li>{@link #update(FinancialList, BudgetLogic)} - Updates the storage file with the entries 
 *          from the given FinancialList and BudgetLogic.</li>
 *   <li>{@link #parseExpense(String[])} - Parses a string array into an Expense object.</li>
 *   <li>{@link #parseIncome(String[])} - Parses a string array into an Income object.</li>
 *   <li>{@link #loadFromFile(BudgetLogic)} - Loads financial data from the storage file into a 
 *          FinancialList and updates the BudgetLogic.</li>
 *   <li>{@link #loadTransactionsFromFile()} - Loads transactions from the storage file into a
 *      FinancialList.</li>
 *   <li>{@link #loadBudgetFromFile(FinancialList, BudgetLogic)} - Loads the budget from the storage
 *          file and updates the FinancialList and BudgetLogic.</li>
 *   <li>{@link #checkParameters(double, String, DateTimeFormatter, LocalDate)} - Checks the parameters
 *          for validity.</li>
 *   <li>{@link #getInvalidLines()} - Retrieves the list of invalid lines encountered during processing.</li>
 *   <li>{@link #clearInvalidLines()} - Clears all the invalid lines stored in the invalidLines list.</li>
 *   <li>{@link #printInvalidLines()} - Prints the invalid lines stored in the invalidLines list.</li>
 *   <li>{@link #printLoadingResult()} - Prints the result of loading data from storage.</li>
 *  
 * </ul>
 * 
 * <p>Fields:</p>
 * <ul>
 *   <li>{@link #logger} - Logger for logging information and errors.</li>
 *   <li>{@link #FINANCIAL_LIST_FILE_PATH} - Path to the storage file.</li>
 *   <li>{@link #BUDGET_FILE_PATH} - Path to the budget file.</li>
 *   <li>{@link #storageFileNotFoundMsg} - Message indicating that the storage file was not found.</li>
 *   <li>{@link #budgetFileNotFoundMsg} - Message indicating that the budget file was not found.</li>
 *   <li>{@link #loadedTransactionsMsg} - Message indicating that transactions were loaded from the file.</li>
 *   <li>{@link #failedLoadingBudgetMsg} - Message indicating that the budget failed to load.</li>
 *   <li>{@link #loadedBudgetMsg} - Message indicating that the budget was loaded from the file.</li>
 *   <li>{@link #invalidLines} - List of invalid lines encountered during processing.</li>
 * 
 * </ul>
 */
public class Storage {
    public static final String FINANCIAL_LIST_FILE_PATH = "data/FinancialList.txt";
    public static final String BUDGET_FILE_PATH = "data/Budget.txt";
    private static final Log logger = Log.getInstance();

    private static String storageFileNotFoundMsg = "";
    private static String budgetFileNotFoundMsg = "";
    private String loadedTransactionsMsg = "";
    private String failedLoadingBudgetMsg = "";
    private String loadedBudgetMsg = "";
    private ArrayList<String> invalidLines = new ArrayList<>();

    public Storage() {
    }

    /**
     * Retrieves the log file. If the storage file does not exist, it creates the file
     * and its parent directories if necessary.
     *
     * @return The storage file.
     */
    public static File getStorageFile() {
        File file = new File(FINANCIAL_LIST_FILE_PATH);
        // check if the file exists
        if (!file.exists()) {
            storageFileNotFoundMsg = "Creating new " + FINANCIAL_LIST_FILE_PATH + " file.";
            try {
                // check if the dictionary exists
                File directory = new File(file.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * Retrieves the budget file. If the file does not exist, it creates the necessary directories
     * and the file itself.
     *
     * @return The budget file.
     */
    public static File getBudgetFile(){
        File file = new File(BUDGET_FILE_PATH);
        // check if the file exists
        if (!file.exists()) {
            budgetFileNotFoundMsg = "Creating new " + BUDGET_FILE_PATH + " file.";
            try {
                // check if the dictionary exists
                File directory = new File(file.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * Retrieves the log file. If the storage file does not exist, it creates the file
     * and its parent directories if necessary.
     *
     * @return The storage file.
     */
    public static File getStorageFileWithoutMsg() {
        File file = new File(FINANCIAL_LIST_FILE_PATH);
        // check if the file exists
        if (!file.exists()) {
            try {
                // check if the dictionary exists
                File directory = new File(file.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * Retrieves the budget file. If the file does not exist, it creates the necessary directories
     * and the file itself.
     *
     * @return The budget file.
     */
    public static File getBudgetFileWithoutMsg() {
        File file = new File(BUDGET_FILE_PATH);
        // check if the file exists
        if (!file.exists()) {
            try {
                // check if the dictionary exists
                File directory = new File(file.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * Updates the storage file with the current entries in the provided FinancialList.
     * 
     * This method iterates through the entries in the given FinancialList, converts each entry
     * to its storage string representation, and writes it to the storage file. If the file does
     * not exist, it will be created. If an error occurs during the file writing process, the 
     * exception stack trace will be printed.
     * 
     * @param theList The FinancialList containing the entries to be written to the storage file.
     */
    public void update(FinancialList theList, BudgetLogic budgetLogic) {
        try {
            // run through the list of tasks and write them to the file
            File file = getStorageFile();
            FileWriter fileWritter = new FileWriter(file);
            // Store the transactions
            for (int i = 0; i < theList.getEntryCount(); i++) {
                seedu.duke.financial.FinancialEntry entry = theList.getEntry(i);
                fileWritter.write(entry.toStorageString() + "\n");
            }
            fileWritter.close();

            File budgetFile = getBudgetFile();
            FileWriter budgetFileWritter = new FileWriter(budgetFile);
            // Store the budget
            Budget budget = budgetLogic.getBudget();
            budgetFileWritter.write(budget.toStorageString() + "\n");
            budgetFileWritter.close();
            logger.log(LogLevels.INFO, "Updated file with " + theList.getEntryCount() + " entries.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Checks the parameters for validity.
     *
     * @param amount The amount to be checked. It should be non-negative and not exceed $9999999.00.
     * @param description The description to be checked. It should not be null or empty.
     * @param formatter The DateTimeFormatter used for formatting dates.
     * @param date The date to be checked. It should not be null and should not be after the current date.
     * @throws FinanceBuddyException If any of the parameters are invalid.
     */
    public void checkParameters(double amount, String description, DateTimeFormatter formatter,
                                LocalDate date) throws FinanceBuddyException {
        if (amount < 0) {
            throw new FinanceBuddyException("Amount should be non-negative");
        }
        if (amount > 9999999.00) {
            throw new FinanceBuddyException("Invalid amount. Amount must be $9999999.00 or less.");
        }
        if (description == null || description.isEmpty()) {
            throw new FinanceBuddyException("Description should not be empty");
        }
        if (date == null) {
            throw new FinanceBuddyException("Date should not be empty");
        }
        if (date.isAfter(LocalDate.now())){
            throw new FinanceBuddyException("Date cannot be after current date.");
        }
    }

    /**
     * Parses an array of strings to create an Expense object.
     *
     * @param tokens An array of strings where:
     *               tokens[1] is the amount as a string,
     *               tokens[2] is the description,
     *               tokens[3] is the date in ISO-8601 format (yyyy-MM-dd).
     * @return An Expense object created from the parsed data.
     * @throws NumberFormatException if the amount cannot be parsed as a double.
     * @throws DateTimeParseException if the date cannot be parsed as a LocalDate.
     */
    public Expense parseExpense(String[] tokens) throws FinanceBuddyException {
        try {
            double amount = Double.parseDouble(tokens[1]);
            String description = tokens[2];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(tokens[3], formatter);
            Expense.Category category = Expense.Category.valueOf(tokens[4].toUpperCase());
            checkParameters(amount, description, formatter, date);
            return new Expense(amount, description, date, category);
        } catch (NumberFormatException e) {
            logger.log(LogLevels.WARNING, "Error parsing amount in expense: " + tokens[1], e);
            throw e;
        } catch (IllegalArgumentException e) {
            logger.log(LogLevels.WARNING, "Error parsing category in expense: " + tokens[4], e);
            throw e;
        } catch (DateTimeParseException e) {
            logger.log(LogLevels.WARNING, "Error parsing date in expense: " + tokens[3], e);
            throw e;
        }
    }

    /**
     * Parses an array of strings to create an Income object.
     *
     * @param tokens An array of strings where:
     *               tokens[1] is the amount as a double,
     *               tokens[2] is the description as a string,
     *               tokens[3] is the date as a LocalDate in ISO-8601 format.
     * @return An Income object constructed from the provided tokens.
     * @throws NumberFormatException if tokens[1] cannot be parsed as a double.
     * @throws DateTimeParseException if tokens[3] cannot be parsed as a LocalDate.
     */
    public Income parseIncome(String[] tokens) throws FinanceBuddyException {
        try{
            double amount = Double.parseDouble(tokens[1]);
            String description = tokens[2];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(tokens[3], formatter);
            Income.Category category = Income.Category.valueOf(tokens[4].toUpperCase());
            checkParameters(amount, description, formatter, date);
            return new Income(amount, description, date, category);
        } catch (NumberFormatException e) {
            logger.log(LogLevels.WARNING, "Error parsing amount in income: " + tokens[1], e);
            throw e;
        } catch (IllegalArgumentException e) {
            logger.log(LogLevels.WARNING, "Error parsing category in income: " + tokens[4], e);
            throw e;
        } catch (DateTimeParseException e) {
            logger.log(LogLevels.WARNING, "Error parsing date in income: " + tokens[3], e);
            throw e;
        }
    }


    /**
     * Loads the budget from a file and updates the budget logic and financial list.
     *
     * @param theList The financial list to be updated.
     * @param budgetLogic The budget logic to be updated.
     */
    public void loadBudgetFromFile(FinancialList theList, BudgetLogic budgetLogic){
        try{
            // load budget
            File budgetFile = getBudgetFile();
            java.util.Scanner scBudget = new java.util.Scanner(budgetFile);
            // start reading the budget
            try {
                // parse the budget amout
                String amount = scBudget.nextLine();
                Budget budget = new Budget();
                if (Double.parseDouble(amount) < 0.01) {
                    logger.log(LogLevels.WARNING,
                            "Budget amount should be more than 0.01, the budget won't be set.");
                    scBudget.close();
                    failedLoadingBudgetMsg = "Budget format in file is invalid, the budget won't be set.";
                    return ;
                }
                budget.setBudgetAmount(Double.parseDouble(amount));
                // parse the budget date
                String date = scBudget.nextLine();
                try{
                    LocalDate.parse(date);
                } catch (DateTimeParseException e) {
                    logger.log(LogLevels.WARNING,
                            "Error parsing date in budget: " + date + ", setting to current date.");
                    date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    failedLoadingBudgetMsg = "Date in budget file is invalid, setting to current date.";
                }
                // check if the date is in future 
                if (LocalDate.parse(date).isAfter(LocalDate.now())) {
                    budget.setBudgetSetDate(LocalDate.now());
                    logger.log(LogLevels.WARNING, "Budget date is in the future, setting to current date.");
                    failedLoadingBudgetMsg = "Date in budget file is in the future, setting to current date.";
                } else {
                    budget.setBudgetSetDate(LocalDate.parse(date));
                }
                budgetLogic.overwriteBudget(budget);
                update(theList, budgetLogic);
            } catch (Exception e) {
                logger.log(LogLevels.WARNING, "Budget format invalid, the budget won't be set.");
                logger.log(LogLevels.WARNING, e.getMessage());
                File file = getBudgetFile();
                if (file.length() == 0) {
                    failedLoadingBudgetMsg = "No budget loaded from file.";
                }else{
                    failedLoadingBudgetMsg = "Budget format in file is invalid, the budget won't be set.";
                }
            }
            scBudget.close();
            loadedBudgetMsg = "Budget loaded from file.";
        } catch(FileNotFoundException e){
            logger.log(LogLevels.WARNING, "File not found: " + e.getMessage() );
        }
    }

    /**
     * Loads transactions from a file and returns a FinancialList containing the transactions.
     * The file is expected to contain lines representing either expenses or incomes.
     * Each line should start with 'E' for expenses or 'I' for incomes, followed by the transaction details.
     * 
     * @return FinancialList containing the loaded transactions.
     * @throws FileNotFoundException if the storage file is not found.
     */
    public FinancialList loadTransactionsFromFile(){
        try{
            FinancialList theList = new FinancialList();
            File file = getStorageFile();
            java.util.Scanner sc = new java.util.Scanner(file);
            Integer loadedExpenseCount = 0;
            Integer loadedIncomeCount = 0;
            // start reading the transactions
            Boolean transectionLoading = true;
            while (sc.hasNextLine() && transectionLoading) {
                String tmpLine = "";
                try {
                    String line = sc.nextLine();
                    tmpLine = line;
                    // parse the line and add the task to the list
                    if (line.charAt(0) == 'E') {
                        String[] tokens = line.split(" \\¦¦ ");
                        theList.addEntry(parseExpense(tokens));
                        loadedExpenseCount++;
                    } else if (line.charAt(0) == 'I') {
                        String[] tokens = line.split(" \\¦¦ ");
                        theList.addEntry(parseIncome(tokens));
                        loadedIncomeCount++;
                    } else {
                        logger.log(LogLevels.WARNING,
                                "Skiping logged transection cause storage format invalid, "
                                + "unknown entry type: " + line.charAt(0));
                    }
                } catch (Exception e) {
                    logger.log(LogLevels.WARNING, "Skiping logged transection cause storage format invalid");
                    logger.log(LogLevels.WARNING, e.getMessage());
                    // add the invalid line to the invalid lines list
                    this.invalidLines.add("\t" + tmpLine);
                }
            }
            if (loadedExpenseCount > 0 || loadedIncomeCount > 0) {
                loadedTransactionsMsg = "I have loaded " + loadedExpenseCount + " expense(s) and " +
                        loadedIncomeCount + " income(s) from file." ;
            }else{
                loadedTransactionsMsg =  "No transactions loaded from file.";
            }
            logger.log(LogLevels.INFO, "Loaded " + loadedExpenseCount + " expense(s) and " +
                    loadedIncomeCount + " income(s) from file.");
            sc.close();
            return theList;
        } catch(FileNotFoundException e){
            logger.log(LogLevels.WARNING, "File not found: " + e.getMessage() + "Creating new FinancialList.");
            return new FinancialList();
        }
    }
    
    /**
     * Loads the financial data from a file.
     * This includes loading transactions and budget information.
     *
     * @param budgetLogic The logic to handle budget-related operations.
     * @return A FinancialList containing the loaded transactions and budget information.
     */
    public FinancialList loadFromFile(BudgetLogic budgetLogic) {
        try {
            FinancialList theList = loadTransactionsFromFile();
            loadBudgetFromFile(theList, budgetLogic);
            return theList;
        } catch (Exception e) {
            logger.log(LogLevels.WARNING, "Error loading file: " + e.getMessage());
            return new FinancialList();
        }
    }

    /**
     * Retrieves the list of invalid lines encountered during processing.
     *
     * @return An ArrayList containing the invalid lines.
     */
    public ArrayList<String> getInvalidLines() {
        return invalidLines;
    }

    /**
     * Clears all the invalid lines stored in the invalidLines list.
     */
    public void clearInvalidLines() {
        invalidLines.clear();
    }

    /**
     * Prints the invalid lines stored in the invalidLines list.
     * If there are no invalid lines, the method returns without printing anything.
     * Otherwise, it prints a message indicating that invalid lines are being deleted,
     * followed by each invalid line.
     */
    public void printInvalidLines() {
        if (invalidLines.isEmpty()) {
            return;
        }
        System.out.println("Deleting invalid line(s) from storage file: ");
        for (String line : invalidLines) {
            System.out.println(line);
        }
        System.out.println();
    }

    /**
     * Prints the result of loading data from storage.
     * 
     * This method prints various messages related to the loading process:
     * - If the storage file was not found, it prints the corresponding message.
     * - If the budget file was not found, it prints the corresponding message.
     * - It prints any invalid lines encountered during loading.
     * - It prints a message indicating the transactions that were loaded.
     * - If there was a failure in loading the budget, it prints the corresponding message.
     * - Otherwise, it prints a message indicating the budget was successfully loaded.
     */
    public void printLoadingResult() {
        if (!storageFileNotFoundMsg.isEmpty()) {
            System.out.println(storageFileNotFoundMsg);
        }
        if (!budgetFileNotFoundMsg.isEmpty()) {
            System.out.println(budgetFileNotFoundMsg);
        }
        printInvalidLines();   
        System.out.println(loadedTransactionsMsg);
        if(!failedLoadingBudgetMsg.isEmpty()){
            System.out.println(failedLoadingBudgetMsg);
        } else if(!loadedBudgetMsg.isEmpty() ){
            System.out.println(loadedBudgetMsg);
        }
        clearInvalidLines();
    }

    /**
     * Deletes the budget file from the file system.
     * If an error occurs during deletion, it logs a warning message.
     */
    public void deleteBudgetFromFile() {
        try {
            File budgetFile = getBudgetFile();
            budgetFile.delete();
        } catch (Exception e) {
            logger.log(LogLevels.WARNING, "Error deleting budget file: " + e.getMessage());
        }
    }

    /**
     * Deletes the financial list file from the storage.
     * If an error occurs during the deletion process, it logs a warning message.
     */
    public void deleteFinancialListFromFile() {
        try {
            File financialListFile = getStorageFile();
            financialListFile.delete();
        } catch (Exception e) {
            logger.log(LogLevels.WARNING, "Error deleting financial list file: " + e.getMessage());
        }
    }
}


