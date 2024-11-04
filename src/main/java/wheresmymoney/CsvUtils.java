package wheresmymoney;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import wheresmymoney.exception.StorageException;
import wheresmymoney.exception.WheresMyMoneyException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Consumer;


public class CsvUtils {
    public static void readCSV(String filePath, Consumer<? super String[]> read_action) throws WheresMyMoneyException{
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
}
