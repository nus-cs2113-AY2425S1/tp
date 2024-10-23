package seedu.duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;

import seedu.duke.financial.Expense;
import seedu.duke.financial.FinancialEntry;
import seedu.duke.financial.FinancialList;
import seedu.duke.financial.Income;

public class Storage {
    public static final String STORAGE_FILE_PATH = "data/FinancialList.txt";

    public Storage() {
    }

    /**
     * Retrieves the log file. If the storage file does not exist, it creates the file
     * and its parent directories if necessary.
     *
     * @return The storage file.
     */
    public static File getStorageFile() {
        File file = new File(STORAGE_FILE_PATH);
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


    public void update(FinancialList theList) {
        try {
            // run through the list of tasks and write them to the file
            File file = getStorageFile();
            FileWriter fileWritter = new FileWriter(file);
            for (int i = 0; i < theList.getEntryCount(); i++) {
                seedu.duke.financial.FinancialEntry entry = theList.getEntry(i);
                fileWritter.write(entry.toStorageString() + "\n");
            }
            fileWritter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Expense parseExpense(String[] tokens) {
        double amount = Double.parseDouble(tokens[1]);
        String description = tokens[2];
        LocalDate date = LocalDate.parse(tokens[3]);
        return new Expense(amount, description, date);
    }

    public Income parseIncome(String[] tokens) {
        double amount = Double.parseDouble(tokens[1]);
        String description = tokens[2];
        LocalDate date = LocalDate.parse(tokens[3]);
        return new Income(amount, description, date);
    }

    public FinancialList loadFromFile(){
        FinancialList theList = new FinancialList();
        try {
            File file = getStorageFile();
            java.util.Scanner sc = new java.util.Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                // parse the line and add the task to the list
                if (line.charAt(0) == 'E') {
                    String[] tokens = line.split(" \\| ");
                    theList.addEntry(parseExpense(tokens));
                    System.out.println("Expense added from file");
                } else if (line.charAt(0) == 'I') {
                    String[] tokens = line.split(" \\| ");
                    theList.addEntry(parseIncome(tokens));
                    System.out.println("Income added from file");
                }
            }
            return theList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
