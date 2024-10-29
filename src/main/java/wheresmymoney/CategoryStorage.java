package wheresmymoney;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import wheresmymoney.exception.StorageException;
import wheresmymoney.exception.WheresMyMoneyException;

/**
 * The {@code CategoryStorage} class handles conversion between CSV and CategoryTracker.
 */
public class CategoryStorage {
    
    /**
     * Creates a {@code CategoryTracker} object based on the given {@code ExpenseList}.
     *
     * @param expenseList The list of expenses to be tracked, each with a category and a price.
     * @return A CategoryTracker containing the total spending for each category based on the provided expense list.
     * @throws WheresMyMoneyException If an error occurs while adding a category.
     */
    public static CategoryTracker trackCategoriesOf(ArrayList<Expense> expenseList) throws WheresMyMoneyException {
        CategoryTracker categoryTracker = new CategoryTracker();
        for (Expense expense : expenseList) {
            String categoryName = expense.getCategory();
            Float price = expense.getPrice();
            categoryTracker.addCategory(categoryName, price);
        }
        return categoryTracker;
    }
    /**
     * Loads from CSV file and updates spending limits for found categories.
     *
     * @param filePath File Path to read CSV from
     */
    public static void loadFromCsv(String filePath, CategoryTracker categoryTracker) throws StorageException {
        try {
            File file = new File(filePath);
            FileReader reader = new FileReader(file);
            CSVReader csvReader = new CSVReader(reader);
            
            csvReader.readNext(); // Skip the header
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                if (line.length != 2) {
                    continue;
                }
                
                String categoryName = line[0];
                Float spendingLimit = Float.parseFloat(line[1]);
                if (categoryTracker.contains(categoryName)) {
                    CategoryData categoryData = categoryTracker.getCategoryDataOf(categoryName);
                    categoryData.setMaxExpenditure(spendingLimit);
                }
            }
            
            // closing writer connection
            reader.close();
        } catch (WheresMyMoneyException exc) {
            throw new StorageException("An expense's price, description, category and/or date added is missing");
        } catch (IOException ex) {
            throw new StorageException("Unable to read file!");
        } catch (CsvValidationException e){
            throw new StorageException("File not in the correct format!");
        }
    }

    /**
     * Saves a Category Tracker to a csv file.
     *
     * @param filePath File Path to save csv to
     */
    public static void saveToCsv(String filePath, CategoryTracker categoryTracker) throws StorageException {
        File file = new File(filePath);

        // create FileWriter object with file as parameter
        FileWriter outFile;
        try{
            outFile = new FileWriter(file);
        } catch (IOException e) {
            throw new StorageException("Unable to save to file!");
        }
        
        // create CSVWriter object filewriter object as parameter
        CSVWriter writer = new CSVWriter(outFile);

        // adding header to csv
        String[] header = { "Category", "SpendingLimit" };
        writer.writeNext(header);
        
        HashMap<String, CategoryData> tracker = categoryTracker.getTracker();
        for (String categoryName : tracker.keySet()) {
            Float spendingLimit = tracker.get(categoryName).getMaxExpenditure();
            String[] row = {
                categoryName,
                spendingLimit.toString()
            };
            writer.writeNext(row);
        }

        // closing writer connection
        try {
            writer.close();
        } catch (IOException e) {
            throw new StorageException("Unable to save to file!");
        }
    }
}
