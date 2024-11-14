package file;

import car.Car;
import car.CarList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarFileTest {

    private static final ArrayList<String> filenames = new ArrayList<>();

    @BeforeEach
    void setUp() {
        CarList.clearCarList();
        FileHandler.createFolderIfNotExist();
    }

    @AfterAll
    static void tearDown() {
        for (String filename : filenames) {
            File file = new File(filename);
            file.delete();
        }
    }

    // Helper method to validate car attributes
    private void validateCar(Car car, String expectedModel, String expectedLicensePlate, double expectedPrice,
                             boolean isRented, boolean isExpensive) {
        assertEquals(expectedModel, car.getModel());
        assertEquals(expectedLicensePlate, car.getLicensePlateNumber());
        assertEquals(expectedPrice, car.getPrice(), 0.0);
        assertEquals(isRented, car.isRented());
        assertEquals(isExpensive, car.isExpensive());
    }

    @Test
    public void testGetCarDataFilename() {
        CarFile carFile = new CarFile();
        assertEquals("carData.txt", carFile.getCarDataFilename());
    }

    private static ArrayList<Integer> inputTestCases() {
        CarFile carFile = new CarFile("carData1.txt");
        int line = 1;
        ArrayList<Integer> errorLines = new ArrayList<>();
        String[] parameters = {"Corolla123", "SGM1432K", "1.0", "false", "false"};
        carFile.addCarWithParameters(parameters, errorLines, line);
        line++;
        parameters = new String[]{"Toyota", "SGK6200F", "0", "false", "true"};
        carFile.addCarWithParameters(parameters, errorLines, line);
        line++;
        parameters = new String[]{"Toyota Cor", "SGK4932K", "jph", "false", "true"};
        carFile.addCarWithParameters(parameters, errorLines, line);
        filenames.add(carFile.getAbsolutePath());
        return errorLines;
    }

    @Test
    void testAddCarWithParameters() {
        ArrayList<Integer> errorLines = inputTestCases();
        assertEquals(2, CarList.getCarsList().size());

        Car car1 = CarList.getCarsList().get(0);
        validateCar(car1, "Toyota", "SGK6200F", 0.0, false, false);

        Car car2 = CarList.getCarsList().get(1);
        validateCar(car2, "Corolla123", "SGM1432K", 1.0, false, true);

        // Check for errors in the line
        assertTrue(errorLines.size() == 1 && errorLines.get(0) == 3);
    }

    @Test
    void testUpdateCarDataFile() {
        CarFile carFile = new CarFile("carData2.txt");
        File testFile = new File(carFile.getAbsolutePath());
        carFile.createCarFileIfNotExist();
        assertTrue(testFile.exists());

        // Add cars to the list
        CarList.addCarWithoutPrintingInfo(new Car("Toyota Corolla", "SGM4932K", 10));
        CarList.addCarWithoutPrintingInfo(new Car("Toyota Cor", "S4932K", 1));
        CarList.addCarWithoutPrintingInfo(new Car("Toyota", "SGK", 0));

        try {
            carFile.updateCarDataFile();
        } catch (IOException e) {
            assert false;
        }

        String[] lines = {
            "Toyota | SGK | 0.0 | false | false",
            "Toyota Cor | S4932K | 1.0 | false | false",
            "Toyota Corolla | SGM4932K | 10.0 | false | true"
        };

        try (Scanner scanner = new Scanner(testFile)) {
            int i = 0;
            while (scanner.hasNext()) {
                assertEquals(lines[i], scanner.nextLine());
                i++;
            }
        } catch (FileNotFoundException e) {
            assert false;
        }

        filenames.add(carFile.getAbsolutePath());
    }

    @Test
    void testLoadCarDataIfExist() {
        CarFile carFile = new CarFile("carData3.txt");
        File testFile = new File(carFile.getAbsolutePath());
        carFile.createCarFileIfNotExist();

        try (FileWriter fw = new FileWriter(testFile)) {
            String textToAdd =
                    "Toyota Corolla | SGM4932K | 10 | false | false\n" +
                            "Toyota Cor | SGM4933K | 1 | false | false\n" +
                            "Toyota Corolla | SGM4933K | 10 | false | false\n" +
                            "Toyota | SGK1234F | 0 | false | false\n" +
                            "Toyota | SGK1234F | 0 | false | false\n" +
                            "  |  |  |  |  \n" +
                            "SGE1234X | 10000.0 | false | false\n" +
                            "  | SGE1234X | 10000.0 | false | false\n" +
                            "Honda Civic |  | 10000.0 | false | false\n" +
                            "Honda Civic | SGE1234X |  | false | false\n" +
                            "Honda Civic | SGE1234X | 10000.0 |  | false\n" +
                            "Honda Civic | SGE1234X | 10000.0 | false |\n" +
                            "Honda Civic | SGE1234X | -10000.0 | false | false\n" +
                            "Honda Civic | SGE1234X | 10000.0 | false |\n";
            fw.write(textToAdd);
        } catch (IOException e) {
            assert false;
        }

        carFile.loadCarDataIfExist();
        assertEquals(3, CarList.getCarsList().size());

        // Validate the cars loaded from the file
        Car car1 = CarList.getCarsList().get(0);
        validateCar(car1, "Toyota", "SGK1234F", 0.0, false, false);

        Car car2 = CarList.getCarsList().get(1);
        validateCar(car2, "Toyota Cor", "SGM4933K", 1.0, false, false);

        Car car3 = CarList.getCarsList().get(2);
        validateCar(car3, "Toyota Corolla", "SGM4932K", 10.0, false, true);

        filenames.add(carFile.getAbsolutePath());
    }

    @Test
    void scanLineAndAddCar() {
        CarFile carFile = new CarFile("carData4.txt");
        File testFile = new File(carFile.getAbsolutePath());
        carFile.createCarFileIfNotExist();

        try (FileWriter fw = new FileWriter(testFile)) {
            String textToAdd = "Toyota Corolla | SGM4932K | 120.0 | false | false";
            fw.write(textToAdd);
        } catch (IOException e) {
            assert false;
        }

        int line = 1;
        ArrayList<Integer> errorLines = new ArrayList<>();
        try (Scanner scanner = new Scanner(testFile)) {
            carFile.scanLineAndAddCar(scanner, errorLines, line);
        } catch (FileNotFoundException e) {
            assert false;
        }

        Car car1 = CarList.getCarsList().get(0);
        validateCar(car1, "Toyota Corolla", "SGM4932K", 120.0, false, false);

        filenames.add(carFile.getAbsolutePath());
    }

    @Test
    void testCreateCarFileIfNotExist() {
        CarFile carFile = new CarFile("carData5.txt");
        File testFile = new File(carFile.getAbsolutePath());
        carFile.createCarFileIfNotExist();

        assertTrue(testFile.exists());
        filenames.add(carFile.getAbsolutePath());
    }
}
