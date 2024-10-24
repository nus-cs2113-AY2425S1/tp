package wheresmymoney;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class RecurringExpenseList extends ExpenseList {
    private ArrayList<RecurringExpense> recurringExpenses;

    public RecurringExpenseList() {
        recurringExpenses = new ArrayList<>();
    }

    /**
     * Add a recurring expense to the end of the list
     *
     * @param price New price of expense
     * @param description New description of expense
     * @param category New category of expense
     * @param lastAddedDate Date of when the expense was last updated
     * @param frequency Frequency of recurring expense
     */
    public void addExpense(Float price, String description, String category, 
            String lastAddedDate, String frequency) {
        logger.log(Level.INFO,
                String.format("Adding recurring expense with parameters: %f, %s, %s", price, description, category));
        RecurringExpense recurringExpense = new RecurringExpense(price, description, category, lastAddedDate, frequency);
        assert (recurringExpense != null);
        recurringExpenses.add(recurringExpense);
        logger.log(Level.INFO, "Successfully added recurring expense.");
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
    public void editExpense(int index, Float price, String description, String category, 
            String lastAddedDate, String frequency) throws WheresMyMoneyException {
        try {
            logger.log(Level.INFO, "Attempting to edit recurring expense.");
            RecurringExpense recurringExpense = recurringExpenses.get(index);
            assert (recurringExpense != null);
            recurringExpense.setPrice(price);
            recurringExpense.setDescription(description);
            recurringExpense.setCategory(category);
            recurringExpense.setlastAddedDate(lastAddedDate);
            recurringExpense.setFrequency(frequency);
            logger.log(Level.INFO, "Successfully edited recurring expense.");
        } catch (WheresMyMoneyException e) {
            logger.log(Level.INFO, "Failure when editing recurring expense.");
            throw new WheresMyMoneyException(e.getMessage());
        }
    }
    
    /**
     * Returns the list of all recurringExpenses from the specified category
     *
     * @param category Category of expense
     */
    public ArrayList<Expense> listByCategory(String category) {
        ArrayList<Expense> recurringExpensesFromCategory = new ArrayList<>();
        for (Expense recurringExpense: recurringExpenses) {
            if (recurringExpense.category.equals(category)) {
                logger.log(Level.INFO, "Found matching expense: " + recurringExpense.description);
                recurringExpensesFromCategory.add(recurringExpense);
            }
        }
        return recurringExpensesFromCategory;
    }

    /**
     * Loads from a csv file into Expense List.
     *
     * @param filePath File Path to read csv
     */
    public void loadFromCsv(String filePath) throws Exception {
        File file = new File(filePath);
        FileReader reader = new FileReader(file);
        CSVReader csvReader = new CSVReader(reader);

        csvReader.readNext(); // Skip the header
        String[] line;
        while ((line = csvReader.readNext()) != null) {
            addExpense(Float.parseFloat(line[2]), line[1], line[0]);
        }

        // closing writer connection
        reader.close();
    }

    /**
     * Saves to a csv file.
     *
     * @param filePath File Path to save csv to
     */
    public void saveToCsv(String filePath) throws IOException {
        File file = new File(filePath);

        // create FileWriter object with file as parameter
        FileWriter outputfile = new FileWriter(file);

        // create CSVWriter object filewriter object as parameter
        CSVWriter writer = new CSVWriter(outputfile);

        // adding header to csv
        String[] header = { "Category", "Description", "Price" };
        writer.writeNext(header);

        for (Expense recurringExpense: recurringExpenses) {
            String[] row = {
                    recurringExpense.getCategory(),
                    recurringExpense.getDescription(),
                    recurringExpense.getPrice().toString()
            };
            writer.writeNext(row);
        }

        // closing writer connection
        writer.close();
    }
}
