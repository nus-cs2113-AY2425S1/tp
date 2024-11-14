package file;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileHandlerTest {

    @Test
    void getDirName() {
        assertEquals("data", FileHandler.getDirName());
    }

    @Test
    void createAndLoadFiles() {
        FileHandler.createAndLoadFiles();
        CarFile carFile1 = FileHandler.getCarFile();
        CustomerFile customerFile1 = FileHandler.getCustomerFile();
        TransactionFile transactionFile1 = FileHandler.getTransactionFile();
        File carfile = new File(carFile1.getAbsolutePath());
        File customerfile = new File(customerFile1.getAbsolutePath());
        File transactionfile = new File(transactionFile1.getAbsolutePath());
        assertTrue(FileHandler.getDataDir().exists());
        assertTrue(carfile.exists());
        assertTrue(customerfile.exists());
        assertTrue(transactionfile.exists());
        carfile.delete();
        customerfile.delete();
        transactionfile.delete();
    }

    @Test
    void createFolderIfNotExist() {
        FileHandler.createFolderIfNotExist();
        assertTrue(FileHandler.getDataDir().exists());
    }
}
