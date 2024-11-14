package file;

import car.CarList;
import customer.CustomerList;
import exceptions.CarException;
import exceptions.CustomerException;
import exceptions.TransactionException;
import parser.CarParser;
import transaction.Transaction;
import transaction.TransactionList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import static parser.TransactionParser.dateTimeFormatter;

/**
 * Handles file operation for the transaction.
 */
public class TransactionFile {

    private final String transactionDataFileName;
    private final String transactionDataFilePath ;
    private final File transactionDataFile;

    public TransactionFile(){
        this.transactionDataFileName = "transactionData.txt";
        this.transactionDataFilePath = FileHandler.getDirName() + "/" + transactionDataFileName;
        this.transactionDataFile = new File(transactionDataFilePath);
    }

    public TransactionFile(String filename){
        this.transactionDataFileName = filename;
        this.transactionDataFilePath = FileHandler.getDirName() + "/" + transactionDataFileName;
        this.transactionDataFile = new File(transactionDataFilePath);
    }

    public String getTransactionDataFilename() {
        return transactionDataFileName;
    }

    public void createTransactionFileIfNotExist(){
        if(!transactionDataFile.exists()){
            FileHandler.createNewFile(transactionDataFile);
        }
    }

    /**
     * Reads every line in the transactionData.txt file and add it to the current transaction list.
     *
     * @throws FileNotFoundException if transactionData.txt does not exist.
     * @throws TransactionException if there is corruption in file data.
     */
    public void loadTransactionData() throws FileNotFoundException, TransactionException {
        if(transactionDataFile.exists()){
            Scanner scanner = new Scanner(transactionDataFile);
            ArrayList<Integer> errorLines = new ArrayList<>();
            int line = 1;

            while (scanner.hasNext()) {
                scanLineAndAddTransaction(scanner, errorLines, line);
                line ++;
            }

            if (!errorLines.isEmpty()) {
                throw TransactionException.invalidParameters(errorLines);
            }
        }
    }

    /**
     * Loads data from transactionData.txt if the file exist.
     */
    public void loadTransactionDataIfExist(){
        try {
            loadTransactionData();
        } catch (FileNotFoundException e) {
            System.out.println("transactionData.txt not found in data directory. Please try again");
        } catch (TransactionException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Scans the current line and add data to current transaction list.
     *
     * @param errorLines list of rows of data which are wrong so far.
     * @param line current line number which this transaction data is at in transactionData.txt.
     */
    public void scanLineAndAddTransaction(Scanner scanner, ArrayList<Integer> errorLines, int line) {
        String input = scanner.nextLine();
        String[] parameters = input.split(" \\| ");

        if (parameters.length != Transaction.NUMBER_OF_PARAMETERS) {
            errorLines.add(line);
        }else{
            addTransactionWithParameters(parameters , errorLines, line);
        }
    }

    /**
     * Add transaction object to the list according to the parameters
     *
     * @param parameters parameters of the Transaction object.
     * @param errorLines list of rows of data which are wrong so far.
     * @param line current line number which this transaction data is at in transactionData.txt.
     */
    public void addTransactionWithParameters(String[] parameters , ArrayList<Integer> errorLines , int line) {
        assert parameters.length == Transaction.NUMBER_OF_PARAMETERS : "wrong no. of parameter";

        try {

            String transactionId = parameters[0];

            if (!Transaction.isValidTxId(transactionId) || FileHandler.containEmptyParameter(parameters)) {
                throw new TransactionException("");
            }

            int idNumber = Integer.parseInt(transactionId.substring(2));
            TransactionList.setTxCounter(idNumber);
            String carLicensePlate = parameters[1];
            String borrowerName = parameters[2];

            if(!CarParser.isValidLicensePlateNumber(carLicensePlate) ||
                    !CarList.isExistingLicensePlateNumber(carLicensePlate)){
                throw new CarException("");
            }

            if(!CustomerList.isExistingCustomer(borrowerName)){
                throw new CustomerException("");
            }

            int duration = Integer.parseInt(parameters[3]);
            LocalDate startDate = LocalDate.parse(parameters[4], dateTimeFormatter);
            boolean isCompleted = Boolean.parseBoolean(parameters[5]);
            Transaction transaction = new Transaction(transactionId , carLicensePlate, borrowerName, duration,
                    startDate, isCompleted);

            TransactionList.addTxWithoutPrintingInfo(transaction);

        }catch (NumberFormatException | DateTimeParseException | CarException | TransactionException |
                CustomerException e){
            errorLines.add(line);
        }
    }

    /**
     * Reads the current transaction list and updates transactionData.txt file.
     *
     * @throws IOException File does not exist.
     */
    public void updateTransactionDataFile() throws IOException {
        FileWriter fw = new FileWriter(transactionDataFile);
        String textToAdd = TransactionList.transactionListToFileString();
        fw.write(textToAdd);
        fw.close();
    }

    public String getAbsolutePath(){
        return transactionDataFile.getAbsolutePath();
    }

    public boolean isFileExist(){
        return transactionDataFile.exists();
    }
}
