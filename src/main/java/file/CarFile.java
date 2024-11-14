package file;

import car.Car;
import car.CarList;
import exceptions.CarException;
import exceptions.CustomerException;
import exceptions.TransactionException;
import parser.CarParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles file operation for the car.
 */
public class CarFile {

    private final String carDataFileName;
    private final String carDataFilePath;
    private final File carDataFile;

    public CarFile(){
        this.carDataFileName = "carData.txt";
        this.carDataFilePath = FileHandler.getDirName() + "/" + carDataFileName;
        this.carDataFile = new File(carDataFilePath);
    }

    public CarFile(String filename){
        this.carDataFileName = filename;
        this.carDataFilePath = FileHandler.getDirName() + "/" + carDataFileName;
        this.carDataFile = new File(carDataFilePath);
    }

    public String getCarDataFilename() {
        return this.carDataFileName;
    }

    /**
     * Add car object to the list according to the parameters.
     *
     * @param parameters parameters of the Car object.
     * @param errorLines list of rows of data which are wrong so far.
     * @param line current line number which this car data is at in carData.txt.
     */
    public void addCarWithParameters(String[] parameters, ArrayList<Integer> errorLines, int line) {
        assert parameters.length == Car.NUMBER_OF_PARAMETERS : "wrong no. of parameter";

        try {
            if(FileHandler.containEmptyParameter(parameters)){
                throw new CarException("");
            }
            String model = parameters[0];
            String licensePlateNumber = parameters[1];
            double price = Double.parseDouble(parameters[2]);
            boolean isRented = Boolean.parseBoolean(parameters[3]);
            boolean isExpensive = Boolean.parseBoolean(parameters[4]);
            if(!CarParser.isValidLicensePlateNumber(licensePlateNumber) || !CarParser.isValidPrice(price) ||
                    CarList.isExistingLicensePlateNumber(licensePlateNumber)){
                throw new CarException("");
            }
            Car car = new Car(model, licensePlateNumber, price, isRented , isExpensive);
            CarList.addCarWithoutPrintingInfo(car);
            CarList.sortCarsByPrice();
            CarList.markCarAsExpensive();
          
        } catch(NumberFormatException | CarException | CustomerException | TransactionException e) {

            errorLines.add(line);
        }
    }

    /**
     * Reads the current car list and updates carData.txt file.
     *
     * @throws IOException File does not exist.
     */
    public void updateCarDataFile() throws IOException {
        FileWriter fw = new FileWriter(carDataFile);
        String textToAdd = CarList.carListToFileString();
        fw.write(textToAdd);
        fw.close();
    }

    public void createCarFileIfNotExist(){
        if(!carDataFile.exists()){
            FileHandler.createNewFile(carDataFile);
        }
    }

    /**
     * Reads every line in the carData.txt file and add it to the current car list.
     *
     * @throws FileNotFoundException if carData.txt does not exist.
     * @throws CarException if there is corruption in file data.
     */
    public void loadCarData() throws FileNotFoundException, CarException {
        Scanner scanner = new Scanner(carDataFile);
        ArrayList<Integer> errorLines = new ArrayList<>();
        int line = 1;
        while (scanner.hasNext()) {
            scanLineAndAddCar(scanner, errorLines, line);
            line ++;
        }
        CarList.sortCarsByPrice();
        CarList.markCarAsExpensive();
        if(!errorLines.isEmpty()) {
            throw CarException.invalidParameters(errorLines);
        }
    }

    /**
     * Scans the current line and add data to current car list.
     *
     * @param errorLines list of rows of data which are wrong so far.
     * @param line current line number which this car data is at in carData.txt.
     */
    public void scanLineAndAddCar(Scanner scanner, ArrayList<Integer> errorLines, int line) {
        String input = scanner.nextLine();
        String[] parameters = input.split(" \\| ");
        if(parameters.length != Car.NUMBER_OF_PARAMETERS){
            errorLines.add(line);
        }else{
            addCarWithParameters(parameters, errorLines, line);
        }
    }

    /**
     * Loads data from carData.txt if the file exist.
     */
    public void loadCarDataIfExist(){
        try {
            this.loadCarData();
        } catch (FileNotFoundException e) {
            System.out.println("carData.txt not found in data directory. Please try again");
        } catch (CarException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isFileExist(){
        return carDataFile.exists();
    }

    public String getAbsolutePath(){
        return carDataFile.getAbsolutePath();
    }
}
