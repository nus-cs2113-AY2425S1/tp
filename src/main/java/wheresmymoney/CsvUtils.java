package wheresmymoney;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import wheresmymoney.exception.InvalidInputException;
import wheresmymoney.exception.StorageException;
import wheresmymoney.exception.WheresMyMoneyException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Consumer;


public class CsvUtils {
    public static void readCsv(String filePath, Consumer<? super String[]> readAction) throws WheresMyMoneyException {
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
            csvReader.readAll().forEach(readAction);
            // closing writer connection
            reader.close();
            csvReader.close();
        } catch (CsvException | IOException | WheresMyMoneyException e) {
            throw new StorageException("File is corrupted! Some data might have been salvaged." +
                    "\nRelated Error (if any): "+ e.getMessage());
        }
    }

    public static void writeCsv(String filePath, String[] header, Consumer<? super CSVWriter> writeAction)
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

        writeAction.accept(writer);

        // closing writer connection
        try {
            writer.close();
        } catch (IOException e) {
            throw new StorageException("Unable to save Expense List to file: " + filePath);
        }
    }

    /**
     * Parses a String field into a Float, with exception handling.
     *
     * @param string String with the number
     * @return Float parsed from the string
     * @throws WheresMyMoneyException
     */
    public static Float parseFloat(String string) throws WheresMyMoneyException {
        try {
            return Float.parseFloat(string);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Cannot parse string to float.");
        }
    }
}
