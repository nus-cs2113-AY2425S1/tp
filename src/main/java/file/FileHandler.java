package file;

import java.io.IOException;
import java.io.File;

/**
 * Handles general file operations.
 */
public class FileHandler {

    private static final String DIR_NAME = "data";
    private static final File DATA_DIR = new File(DIR_NAME);
    private static final CarFile carFile = new CarFile();
    private static final CustomerFile customerFile = new CustomerFile();
    private static final TransactionFile transactionFile = new TransactionFile();

    public FileHandler(){

    }

    public static CarFile getCarFile() {
        return carFile;
    }

    public static CustomerFile getCustomerFile() {
        return customerFile;
    }

    public static TransactionFile getTransactionFile() {
        return transactionFile;
    }

    public static File getDataDir() {
        return DATA_DIR;
    }

    public static String getDirName() {
        return DIR_NAME;
    }

    /**
     * Creates and load files to store customer, transaction and car data.
     */
    public static void createAndLoadFiles(){
        createFolderIfNotExist();
        carFile.createCarFileIfNotExist();
        customerFile.createCustomerFileIfNotExist();
        transactionFile.createTransactionFileIfNotExist();
        carFile.loadCarDataIfExist();
        customerFile.loadCustomerDataIfExist();
        transactionFile.loadTransactionDataIfExist();

        assert carFile.isFileExist() : "Car file does not exist.";
        assert customerFile.isFileExist() : "Customer file does not exist.";
        assert transactionFile.isFileExist() : "Transaction file does not exist.";
    }

    /**
     * Creates a new file according to filepath.
     *
     * @param filename The name of the file to create.
     */
    public static void createNewFile(File filename) {

        try {
            if (filename.createNewFile()) {
                System.out.println("File created successfully: " + filename.getName());
            } else {
                System.out.println("File already exists.");
            }

        } catch (IOException exception) {
            System.err.println("Failed to create file: " + exception.getMessage());
        }
    }

    /**
     * Creates a directory called data to store data files if it does not exist.
     */
    public static void createFolderIfNotExist(){

        if (!DATA_DIR.isDirectory()) {
            System.out.println(DIR_NAME + " does not exist. Creating it now.......");
            createFolder();
        }
        assert DATA_DIR.isDirectory() : "Data directory was not created successfully.";
    }

    /**
     * Creates the data directory if it does not exist
     */
    private static void createFolder() {

        boolean isCreated = DATA_DIR.mkdirs();

        if (isCreated) {
            System.out.println(DIR_NAME + " directory created successfully.");
        } else {
            System.out.println(DIR_NAME + " directory already exists or failed to create.");
        }
    }

    public static boolean containEmptyParameter(String[] parameters){
        for (String parameter : parameters) {
            if (parameter.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Reads customer , transaction and car list and refreshes the data file accordingly.
     */
    public static void updateFiles(){
        try {
            carFile.updateCarDataFile();
        } catch (IOException e) {
            System.out.println("Unable to update " + carFile.getCarDataFilename());
        }
        try {
            customerFile.updateCustomerDataFile();
        } catch (IOException e) {
            System.out.println("Unable to update " + customerFile.getCustomerDataFilename());
        }
        try {
            transactionFile.updateTransactionDataFile();
        } catch (IOException e) {
            System.out.println("Unable to update " + transactionFile.getTransactionDataFilename());
        }
    }
}
