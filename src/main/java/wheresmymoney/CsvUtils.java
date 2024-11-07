package wheresmymoney;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import wheresmymoney.exception.StorageException;
import wheresmymoney.exception.WheresMyMoneyException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Consumer;


public class CsvUtils {
    /**
     * Reads a csv file and processes it using the given Consumer.
     *
     * @param filePath CSV file path to read from
     * @param read_action Consumer that runs on each line on the csv (excluding the header)
     * @throws WheresMyMoneyException If there are any reading errors
     */
    public static void readCsv(String filePath, Consumer<? super String[]> read_action) throws WheresMyMoneyException {
        FileReader reader;
        CSVReader csvReader;
        try {
            File file = new File(filePath);
            reader = new FileReader(file);
            csvReader = new CSVReader(reader);
        } catch (IOException ex) {
            throw new StorageException("Unable to load from file: " + filePath);
        }

        try{
            csvReader.readNext(); // Skip the header
            csvReader.readAll().forEach(read_action);
            // closing writer connection
            reader.close();
            csvReader.close();
        } catch (CsvException | IOException e) {
            throw new StorageException("File is corrupted! Some data might have been salvaged.");
        }
    }

    /**
     * Writes to a csv file.
     *
     * @param filePath CSV file path to read from
     * @param header Header of new CSV file to write to
     * @param write_action Consumer that runs on the CSV writer to write the data
     * @throws WheresMyMoneyException If there are any writing errors
     */
    public static void writeCsv(String filePath, String[] header, Consumer<? super CSVWriter> write_action)
            throws WheresMyMoneyException{
        File file = new File(filePath);

        // create FileWriter object with file as parameter
        FileWriter outFile;
        try{
            outFile = new FileWriter(file);
        } catch (IOException e) {
            throw new StorageException("Unable to save to file: " + filePath);
        }

        // create CSVWriter object filewriter object as parameter
        CSVWriter writer = new CSVWriter(outFile);

        // adding header to csv
        writer.writeNext(header);

        write_action.accept(writer);

        // closing writer connection
        try {
            writer.close();
        } catch (IOException e) {
            throw new StorageException("Unable to save Expense List to file: " + filePath);
        }
    }
}
