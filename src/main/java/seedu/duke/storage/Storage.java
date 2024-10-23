package seedu.duke.storage;

import java.io.File;
import java.io.FileWriter;

import seedu.duke.financial.FinancialList;

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
}
