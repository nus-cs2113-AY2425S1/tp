package wheresmymoney;

import wheresmymoney.exception.StorageException;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.time.LocalDate;

//@@author khsienkit

public class RecurringExpenseList extends ExpenseList {
    private ArrayList<RecurringExpense> recurringExpenses;
    private ExpenseList expenseList;

    public RecurringExpenseList(ExpenseList expenseList) {
        recurringExpenses = new ArrayList<>();
        this.expenseList = expenseList;
    }

    public ArrayList<RecurringExpense> getRecurringExpenseList() {
        return recurringExpenses;
    }

    public boolean isEmpty() {
        return recurringExpenses.isEmpty();
    }

    public void clear() { 
        recurringExpenses.clear(); 
    }

    /**
     * Retrieves the {@code RecurringExpense} at the specified index in the list.
     *
     * @param index The index of the recurring expense to retrieve.
     * @return The {@code RecurringExpense} object at the specified index.
     * @throws WheresMyMoneyException If the index is out of bounds.
     */
    public RecurringExpense getRecurringExpenseAtIndex(int index) throws WheresMyMoneyException {
        try {
            return recurringExpenses.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new WheresMyMoneyException("Index is out of bounds.");
        }
    }

    /**
     * Returns the index of the specified {@code RecurringExpense} in the list.
     *
     * @param recurringExpense The {@code RecurringExpense} object to find the index of.
     * @return The index of the specified {@code RecurringExpense}.
     * @throws WheresMyMoneyException If the expense is not found in the list.
     */
    public int getIndexOf(RecurringExpense recurringExpense) throws WheresMyMoneyException {
        int index = recurringExpenses.indexOf(recurringExpense);
        if (index == -1) {
            throw new WheresMyMoneyException("Expense not in list.");
        }
        return index;
    }

    /**
     * Add a recurring expense with a specified date to the end of the list
     *
     * @param price New price of expense
     * @param description New description of expense
     * @param category New category of expense
     * @param lastAddedDate Date of when the expense was last updated
     * @param frequency Frequency of recurring expense
     */
    public void addRecurringExpense(Float price, String description, String category, 
            String lastAddedDate, String frequency) {
        Logging.log(Level.INFO,
                String.format("Adding recurring expense with parameters: %f, %s, %s, %s, %s", 
                price, description, category, lastAddedDate, frequency));
        try {
            RecurringExpense recurringExpense = new RecurringExpense(price, description, category, 
                    lastAddedDate, frequency);
            assert (recurringExpense != null);
            recurringExpenses.add(recurringExpense);
            Logging.log(Level.INFO, "Successfully added recurring expense.");

            expenseList.addExpense(price, description, category, lastAddedDate);
        } catch (WheresMyMoneyException e) {
            throw new WheresMyMoneyException(e.getMessage());
        }
    }

    /**
     * Add a recurring expense to the end of the list. Date is defaulted to the current date
     *
     * @param price New price of expense
     * @param description New description of expense
     * @param category New category of expense
     * @param frequency Frequency of recurring expense
     */
    public void addRecurringExpense(Float price, String description, String category, String frequency) {
        Logging.log(Level.INFO,
                String.format("Adding recurring expense with parameters: %f, %s, %s, %s", 
                price, description, category, frequency));
        try {
            RecurringExpense recurringExpense = new RecurringExpense(price, description, category, frequency);
            assert (recurringExpense != null);
            recurringExpenses.add(recurringExpense);
            Logging.log(Level.INFO, "Successfully added recurring expense.");

            expenseList.addExpense(price, description, category);
        } catch (WheresMyMoneyException e) {
            throw new WheresMyMoneyException(e.getMessage());
        }
    }

    /**
     * Load a recurring expense with a specified date to the end of the recurring expense list
     *
     * @param price New price of expense
     * @param description New description of expense
     * @param category New category of expense
     * @param lastAddedDate Date of when the expense was last updated
     * @param frequency Frequency of recurring expense
     */
    public void loadRecurringExpense(Float price, String description, String category, 
            String lastAddedDate, String frequency) {
        Logging.log(Level.INFO,
                String.format("Adding recurring expense with parameters: %f, %s, %s, %s, %s", 
                price, description, category, lastAddedDate, frequency));
        try {
            RecurringExpense recurringExpense = new RecurringExpense(price, description, category, 
                    lastAddedDate, frequency);
            assert (recurringExpense != null);
            recurringExpenses.add(recurringExpense);
            Logging.log(Level.INFO, "Successfully added recurring expense.");
        } catch (WheresMyMoneyException e) {
            throw new WheresMyMoneyException(e.getMessage());
        }
    }

    /**
     * Edit the details of a recurring expense given its position in the list
     *
     * @param index index of Expense in the list that is to be edited
     * @param price New price of expense
     * @param description New description of expense
     * @param category New category of expense
     * @param lastAddedDate New start date of recurring expense
     * @param frequency New frequency of recurring expense
     */
    public void editRecurringExpense(int index, Float price, String description, String category, 
            String lastAddedDate, String frequency) throws WheresMyMoneyException {
        try {
            Logging.log(Level.INFO, "Attempting to edit recurring expense.");
            RecurringExpense recurringExpense = recurringExpenses.get(index);
            assert (recurringExpense != null);
            if (price != null) {
                recurringExpense.setPrice(price);
            }
            if (description != null) {
                recurringExpense.setDescription(description);
            }
            if (category != null) {
                recurringExpense.setCategory(category);
            }
            if (lastAddedDate != null) {
                recurringExpense.setDateAdded(DateUtils.stringToDate(lastAddedDate));
                recurringExpense.setLastAddedDate(lastAddedDate);
            }
            if (frequency != null) {
                recurringExpense.setFrequency(frequency);
            }
            Logging.log(Level.INFO, "Successfully edited recurring expense.");
        } catch (WheresMyMoneyException e) {
            Logging.log(Level.INFO, "Failure when editing recurring expense.");
            throw new WheresMyMoneyException(e.getMessage());
        }
    }

    /**
     * Delete a recurring expense
     * 
     * @param index Index of recurring expense in the ArrayList
     */
    public void deleteRecurringExpense(int index) throws WheresMyMoneyException {
        if (index < 0 || index >= recurringExpenses.size()) {
            throw new WheresMyMoneyException("Index out of range!");
        }
        recurringExpenses.remove(index);
    }
    
    public ArrayList<RecurringExpense> listRecurringByFilter(String category, String from, String to)
            throws WheresMyMoneyException {
        return ExpenseFilter.filterRecurringExpenses(recurringExpenses, category, from, to);
    }

    /**
     * Adds daily expenses to the expense list from the last added date to the current date
     * 
     * @param recurringExpense
     * @param lastAddedDate
     * @param currentDate
     */
    private void addDailyExpense(RecurringExpense recurringExpense, String lastAddedDate, LocalDate currentDate) {
        try {
            LocalDate nextDate = DateUtils.stringToDate(lastAddedDate).plusDays(1);
            Float price = recurringExpense.getPrice();
            String description = recurringExpense.getDescription();
            String category = recurringExpense.getCategory();

            while (nextDate.isBefore(currentDate) || nextDate.isEqual(currentDate)) {
                expenseList.addExpense(price, description, category, DateUtils.dateFormatToString(nextDate));  
                nextDate = nextDate.plusDays(1);
            }

            recurringExpense.setLastAddedDate(DateUtils.dateFormatToString(currentDate));
        } catch (WheresMyMoneyException e) {
            throw new WheresMyMoneyException(e.getMessage());
        }
    }

    /**
     * Adds weekly expenses to the expense list from the last added date to the current date
     * 
     * @param recurringExpense
     * @param lastAddedDate
     * @param currentDate
     */
    private void addWeeklyExpense(RecurringExpense recurringExpense, String lastAddedDate, LocalDate currentDate) {
        try {
            LocalDate nextDate = DateUtils.stringToDate(lastAddedDate).plusDays(7);
            Float price = recurringExpense.getPrice();
            String description = recurringExpense.getDescription();
            String category = recurringExpense.getCategory();

            while (nextDate.isBefore(currentDate)) {
                expenseList.addExpense(price, description, category, DateUtils.dateFormatToString(nextDate));
                nextDate = nextDate.plusDays(7);
            }

            recurringExpense.setLastAddedDate(DateUtils.dateFormatToString(nextDate.minusDays(7)));
        } catch (WheresMyMoneyException e) {
            Ui.displayMessage(e.getMessage());
        }
    }

    /**
     * Adds monthly expenses to the expense list from the last added date to the current date
     * 
     * @param recurringExpense
     * @param lastAddedDate
     * @param currentDate
     */
    private void addMonthlyExpense(RecurringExpense recurringExpense, String lastAddedDate, LocalDate currentDate) {
        try {
            LocalDate dateAdded = recurringExpense.getDateAdded();
            LocalDate nextDate = DateUtils.stringToDate(lastAddedDate);
            int numberOfMonthsToAdd = dateAdded.getMonthValue() - nextDate.getMonthValue() + 1;
            
            LocalDate newDate = dateAdded.plusMonths(numberOfMonthsToAdd);
            Float price = recurringExpense.getPrice();
            String description = recurringExpense.getDescription();
            String category = recurringExpense.getCategory();

            while (newDate.isBefore(currentDate)) {
                String date =  DateUtils.dateFormatToString(newDate);
                expenseList.addExpense(price, description, category, date);    
                numberOfMonthsToAdd += 1;
                newDate = nextDate.plusMonths(numberOfMonthsToAdd);
            }

            newDate = nextDate.plusMonths(numberOfMonthsToAdd - 1);
            recurringExpense.setLastAddedDate(DateUtils.dateFormatToString(newDate));
        } catch (WheresMyMoneyException e) {
            Ui.displayMessage(e.getMessage());
        }
    }

    /**
     * Loads from a csv file into Expense List.
     *
     * @param filePath File Path to read csv
     */
    public void loadFromCsv(String filePath) throws StorageException {
        clear();

        CsvUtils.readCsv(filePath, line -> {
            loadRecurringExpense(CsvUtils.parseFloat(line[2]), line[1], line[0], line[4], line[5]);
        });

        LocalDate currentDate = DateUtils.getCurrentDate();
        for (RecurringExpense recurringExpense: recurringExpenses) {
            String frequency = recurringExpense.getFrequency();
            String lastAddedDate = recurringExpense.getLastAddedDate();
            switch (frequency) {
            case "daily":
                addDailyExpense(recurringExpense, lastAddedDate, currentDate);
                break;
            case "weekly":
                addWeeklyExpense(recurringExpense, lastAddedDate, currentDate);
                break;
            case "monthly":
                addMonthlyExpense(recurringExpense, lastAddedDate, currentDate);
                break;
            default:
                Ui.displayMessage("There was an error loading a recurring expense");
            }
        }
    }

    /**
     * Saves to a csv file.
     *
     * @param filePath File Path to save csv to
     */
    public void saveToCsv(String filePath) throws StorageException {
        String[] header = { "Category", "Description", "Price", "DateAdded", "LastAddedDate", "Frequency" };
        CsvUtils.writeCsv(filePath, header, (writer) -> {
            for (RecurringExpense recurringExpense: recurringExpenses) {
                String[] row = {
                    recurringExpense.getCategory(),
                    recurringExpense.getDescription(),
                    recurringExpense.getPrice().toString(),
                    DateUtils.dateFormatToString(recurringExpense.getDateAdded()),
                    recurringExpense.getLastAddedDate(),
                    recurringExpense.getFrequency()
                };
                writer.writeNext(row);
            }
        });
    }
}
