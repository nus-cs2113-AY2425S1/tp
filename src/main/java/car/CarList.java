package car;

import exceptions.CarException;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Represents an <code>ArrayList</code> of cars storing <code>Car</code> objects.
 */
public class CarList {

    public static ArrayList<Car> carsList = new ArrayList<>();

    public static ArrayList<Car> getCarsList() {
        return carsList;
    }

    public static void clearCarList(){
        carsList.clear();
    }

    /**
     * Formats the specified price to 2 d.p.
     *
     * @param price Price to be formatted to 2 d.p.
     * @return Price formatted to 2.d.p.
     */
    public static String formatPriceToTwoDp(double price) {
        assert price >= 0.00 : "ERROR.. Price cannot be negative!!";

        String result = "";
        String integerPart = String.valueOf((int)price);

        double remainder = price - (int)price;
        String formattedRemainder = String.format("%.2f", remainder);
        String remainderPart = formattedRemainder.substring(1);

        result += (integerPart + remainderPart);
        return result;
    }

    /**
     * Adds a <code>Car</code> to the car list.
     * <p>
     * If the license plate number of the <code>Car</code> already exists in the car list,
     * a <code>CarException</code> is thrown instead.
     *
     * @param car <code>Car</code> to be added.
     * @throws CarException If the license plate number already exists in the list.
     */
    public static void addCar(Car car) throws CarException {
        if (isExistingLicensePlateNumber(car.getLicensePlateNumber())) {
            throw CarException.duplicateLicensePlateNumber();
        }

        assert !isExistingLicensePlateNumber(car.getLicensePlateNumber()) :
                "ERROR.. Cannot add car with same license plate number";
        carsList.add(car);
        CarList.sortCarsByPrice();
        CarList.markCarAsExpensive();
        System.out.println("Car added to list");
        System.out.println("Car details:");
        System.out.println(car.getModel() + " | " + car.getLicensePlateNumber()
                + " | $" + formatPriceToTwoDp(car.getPrice()) + " | " + car.getRentedStatus()
                + " | " + car.getExpensiveStatus() + " | " + "Median price: " + getMedianPrice());
    }

    public static void addCarWithoutPrintingInfo(Car car) throws CarException {

        if (isExistingLicensePlateNumber(car.getLicensePlateNumber())) {
            throw CarException.duplicateLicensePlateNumber();
        }

        carsList.add(car);
        CarList.sortCarsByPrice();
        CarList.getMedianPrice();
        CarList.markCarAsExpensive();
    }

    /**
     * Removes a <code>Car</code> from the car list.
     *
     * @param carLicensePlateNumber License plate number of <code>Car</code> to be removed.
     */
    public static void removeCar(String carLicensePlateNumber) {
        Car carToRemove = null;

        // Iterate through the list of cars to find the one with the given license plate
        for (Car car : carsList) {
            if (car.getLicensePlateNumber().equalsIgnoreCase(carLicensePlateNumber)) {
                carToRemove = car;
                break;
            }
        }

        // Remove the car if it exists
        if (carToRemove != null) {
            carsList.remove(carToRemove);
            System.out.println("Car with license plate " + carLicensePlateNumber.toUpperCase() + " removed from list.");
        } else {
            System.out.println("No car found with license plate " + carLicensePlateNumber.toUpperCase());
        }
    }

    public static void removeAllCars() {
        carsList.clear();
        System.out.println("All cars removed!!!");
    }

    /**
     * Prints a list of all current cars in the company.
     * <p>
     * If the list is empty, prints out a message instead to inform user that car list is empty.
     */
    public static void printCarList(){
        if (carsList.isEmpty()) {
            System.out.println("Oops!! Car list is empty..."
                    + "\nUse command <add-car> to add a new car.");
            return;
        }

        System.out.println("Here are the current cars in the company:");

        for(int i = 0 ; i < carsList.size(); i++){
            Car car = carsList.get(i);
            System.out.println( (i + 1) + ") " + car.getModel() + " | " + car.getLicensePlateNumber()
                    + " | $" + formatPriceToTwoDp(car.getPrice()) + " | " + car.getRentedStatus()
                    + " | " + car.getExpensiveStatus() + " | " + "Median price: " + getMedianPrice());
        }
    }

    /**
     * Prints a list of all <b>rented out</b> cars.
     * <p>
     * If the list is empty, prints out a message instead to inform user that no cars
     * are currently rented out.
     */
    public static void printRentedCarsList() {
        ArrayList<Car> rentedCarsList = getRentedCarsList();

        if (rentedCarsList.isEmpty()) {
            System.out.println("No cars currently rented out...");
            return;
        }

        System.out.println("Here are all the rented out cars:");

        int index = 1;
        for (Car car : rentedCarsList) {
            System.out.println(index + ") " + car.getModel() + " | " + car.getLicensePlateNumber()
                    + " | $" + formatPriceToTwoDp(car.getPrice()));
            index++;
        }
    }

    /**
     * Prints a list of all <b>available</b> cars.
     * <p>
     * If the list is empty, prints out a message instead to inform user that there
     * are no available cars currently.
     */
    public static void printAvailableCarsList() {
        ArrayList<Car> availableCarsList = getAvailableCarsList();

        if (availableCarsList.isEmpty()) {
            System.out.println("There are no available cars at the moment...");
            return;
        }

        System.out.println("Here are all the available cars:");

        int index = 1;
        for (Car car : availableCarsList) {
            System.out.println(index + ") " + car.getModel() + " | " + car.getLicensePlateNumber()
                    + " | $" + formatPriceToTwoDp(car.getPrice()));
            index++;
        }
    }

    private static ArrayList<Car> getRentedCarsList() {
        ArrayList<Car> rentedCars = new ArrayList<>();

        for (Car car : carsList) {
            if (car.isRented()) {
                rentedCars.add(car);
            }
        }
        return rentedCars;
    }

    private static ArrayList<Car> getAvailableCarsList() {
        ArrayList<Car> availableCars = new ArrayList<>();

        for (Car car : carsList) {
            if (!car.isRented()) {
                availableCars.add(car);
            }
        }
        return availableCars;
    }

    /**
     * Checks if the specified license plate number exists in the car list.
     *
     * @param licensePlateNumber License plate number to check.
     * @return <code>true</code> if license plate number already exists, <code>false</code> otherwise.
     */
    public static boolean isExistingLicensePlateNumber(String licensePlateNumber) {
        for (Car car : carsList) {
            if (car.getLicensePlateNumber().equalsIgnoreCase(licensePlateNumber)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Marks a <code>Car</code> as <b>rented</b>.
     *
     * @param carLicensePlateNumber License plate number of to-be-marked <code>Car</code>.
     */
    public static void markCarAsRented(String carLicensePlateNumber) {
        for (Car car : carsList) {
            if (car.getLicensePlateNumber().equals(carLicensePlateNumber)) {
                car.markAsRented();
                break;
            }
        }
    }

    /**
     * Marks a <code>Car</code> as <b>available</b>.
     *
     * @param carLicensePlateNumber License plate number of to-be-marked <code>Car</code>.
     */
    public static void markCarAsAvailable(String carLicensePlateNumber) {
        for (Car car : carsList) {
            if (car.getLicensePlateNumber().equals(carLicensePlateNumber)) {
                car.markAsAvailable();
                break;
            }
        }
    }

    public static String carListToFileString() {
        StringBuilder carData = new StringBuilder();
        for (Car car : carsList) {
            carData.append(car.toFileString());
            carData.append("\n");
        }
        return carData.toString();
    }

    public static void sortCarsByPrice() {
        carsList.sort(Comparator.comparingDouble(Car::getPrice));
    }

    public static double getMedianPrice() {
        if (carsList.isEmpty()) {
            return 0;
        }

        int middleIndex = carsList.size() / 2;
        if (carsList.size() % 2 == 0) {
            // For even-sized lists, choose the lower middle element
            middleIndex--;
        }
        return carsList.get(middleIndex).getPrice();
    }

    public static void markCarAsExpensive() {
        for (Car car : carsList) {
            if (car.getPrice() > getMedianPrice()) {
                car.markAsExpensive();
            } else {
                car.markAsCheap();
            }
        }
    }
}
