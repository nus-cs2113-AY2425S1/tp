package parser;

import car.Car;
import exceptions.CarException;

/**
 * Represents a Parser to parse the user command into a <code>Car</code> object.
 */
public class CarParser {

    private static final String[] ADD_CAR_PARAMETERS = {"/n", "/c", "/p"};
    /** Number of chars to offset to obtain start index of parameters in <code>add-car</code> command */
    private static final int ADD_CAR_PARAMETERS_OFFSET = 2;
    private static final int MIN_LICENSE_PLATE_NUMBER_LENGTH = 5;
    private static final int MAX_LICENSE_PLATE_NUMBER_LENGTH = 8;

    /**
     * Parses the <code>add-car</code> user command into a <code>Car</code> object.
     * <p>
     * If all the parameters in the <code>add-car</code> command are valid, a
     * new <code>Car</code> object is created and returned.
     *
     * @param userInput Full command entered by user.
     * @return <code>Car</code> object.
     * @throws CarException If <b>license plate number</b> or <b>price</b> of <code>Car</code> is invalid.
     * @throws NumberFormatException If <b>price</b> is not a numeric value.
     */
    public static Car parseIntoCar(String userInput) throws CarException, NumberFormatException {
        userInput = userInput.trim();

        if (!isValidFormat(userInput)) {
            throw CarException.addCarException();
        }

        String carModel = extractCarModel(userInput).trim();

        String carLicensePlateNumber = extractCarLicensePlateNumber(userInput).trim();
        if (!isValidLicensePlateNumber(carLicensePlateNumber)) {
            throw CarException.invalidLicensePlateNumber();
        }

        String carPriceString = extractCarPrice(userInput).trim();
        double carPrice = Double.parseDouble(carPriceString);
        if (!isValidPrice(carPrice)) {
            throw CarException.invalidPrice();
        }

        assert carPrice >= 0.00 : "ERROR.. Car price is negative!!";
        double formattedCarPrice = Double.parseDouble(String.format("%.2f", carPrice));

        return new Car(carModel, carLicensePlateNumber, formattedCarPrice);
    }

    /**
     * Extracts the name of the car model from the <code>add-car</code> command.
     *
     * @param userInput Full command entered by user.
     * @return Car model name.
     */
    private static String extractCarModel(String userInput) {
        int startIndexOfCarModel = userInput.indexOf(ADD_CAR_PARAMETERS[0]) + ADD_CAR_PARAMETERS_OFFSET;
        int endIndexOfCarModel = userInput.indexOf(ADD_CAR_PARAMETERS[1]);

        String carModel = userInput.substring(startIndexOfCarModel, endIndexOfCarModel);
        if (carModel.trim().isEmpty()) {
            throw new CarException("Car model missing!!");
        }

        return carModel;
    }

    /**
     * Extracts the license plate number of the car from the <code>add-car</code> command.
     *
     * @param userInput Full command entered by user.
     * @return License plate number of car.
     */
    private static String extractCarLicensePlateNumber(String userInput) {
        //dsa
        int startIndexOfLicensePlateNumber = userInput.indexOf(ADD_CAR_PARAMETERS[1])
                + ADD_CAR_PARAMETERS_OFFSET;
        int endIndexOfLicensePlateNumber = userInput.indexOf(ADD_CAR_PARAMETERS[2]);

        String carLicensePlateNumber = userInput.substring(startIndexOfLicensePlateNumber,
                endIndexOfLicensePlateNumber);
        if (carLicensePlateNumber.trim().isEmpty()) {
            throw new CarException("License plate number missing!!");
        }

        return carLicensePlateNumber.toUpperCase();
    }

    /**
     * Extracts the price of the car from the <code>add-car</code> command.
     *
     * @param userInput Full command entered by user.
     * @return Price of car.
     */
    private static String extractCarPrice(String userInput) {
        int startIndexOfPrice = userInput.indexOf(ADD_CAR_PARAMETERS[2]) + ADD_CAR_PARAMETERS_OFFSET;

        String carPrice = userInput.substring(startIndexOfPrice);
        if (carPrice.trim().isEmpty()) {
            throw new CarException("Car price missing!!");
        }

        return carPrice;
    }

    /**
     * Checks if the format of the <code>add-car</code> command is valid.
     * <p>
     * A valid format means that all parameter specifiers must be
     * included and in the correct order.
     *
     * @param userInput Full command entered by user.
     * @return <code>true</code> if format is valid, <code>false</code> otherwise.
     */
    public static boolean isValidFormat(String userInput) {
        for (String param : ADD_CAR_PARAMETERS) {
            if (!userInput.contains(param)) {
                return false;
            }
        }

        for (int i = 0; i < ADD_CAR_PARAMETERS.length - 1; i++) {
            if (userInput.indexOf(ADD_CAR_PARAMETERS[i]) > userInput.indexOf(ADD_CAR_PARAMETERS[i+1])) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if price entered by user is valid.
     * <p>
     * A valid price must be <b>non-negative</b>.
     *
     * @param price Price of car specified by user.
     * @return <code>true</code> if price is valid, <code>false</code> otherwise.
     */
    public static boolean isValidPrice(double price) {
        return !(price < 0.00);
    }

    /**
     * Checks if license plate number entered by user is valid.
     * <p>
     * A valid license plate number must start with "S" and have a length of 5 to 8 characters.
     * </p>
     * The license plate number must also conform to the following format: <b>SXX####X</b>, where
     * <p>
     * <code>X</code> represents any letter from A to Z.
     * </p>
     * <code>####</code> represents any number from 1 to 9999.
     *
     * @param licensePlateNumber License plate number of car specified by user.
     * @return <code>true</code> if license plate number is valid, <code>false</code> otherwise.
     */
    public static boolean isValidLicensePlateNumber(String licensePlateNumber) {
        if (!licensePlateNumber.startsWith("S") ||
            licensePlateNumber.length() < MIN_LICENSE_PLATE_NUMBER_LENGTH ||
                licensePlateNumber.length() > MAX_LICENSE_PLATE_NUMBER_LENGTH) {
            return false;
        }

        assert licensePlateNumber.startsWith("S") : "ERROR.. All license plate numbers MUST start with S";

        char[] licensePlateNumberChars = licensePlateNumber.toCharArray();
        int licensePlateNumberLength = licensePlateNumber.length();
        // Example: SGD1234X
        for (int i = 1; i < licensePlateNumberLength; i++) {
            // Checks if second, third and last char are (uppercase) letters.
            if (i <= 2 || i == licensePlateNumberLength - 1) {
                if (licensePlateNumberChars[i] < 'A' || licensePlateNumberChars[i] > 'Z') {
                    return false;
                }
            } else {
                // Checks if middle chars are numbers.
                if (licensePlateNumberChars[i] < '0' || licensePlateNumberChars[i] > '9') {
                    return false;
                }
                // Checks if first number is 0
                if (licensePlateNumberChars[3] == '0') {
                    return false;
                }
            }
        }

        assert licensePlateNumberChars[3] != '0' : "ERROR.. License plate number cannot start with 0";
        return true;
    }

    public static String parseCarLicenseForRemoval(String userInput) throws CarException {
        userInput = userInput.trim();

        String licensePlateNumber = extractLicensePlateForRemoval(userInput).trim();
        if (licensePlateNumber.isEmpty()) {
            throw new CarException("License plate number missing!!");
        }

        return licensePlateNumber;
    }

    private static String extractLicensePlateForRemoval(String userInput) {
        String[] splitInput = userInput.split(" ");
        if (splitInput.length < 2) {
            throw new CarException("Invalid format for removing a car. Use: remove-car /i [CAR_ID]");
        }
        return splitInput[2];  // Expecting the license plate number to be the second argument
    }
}