package car;

/**
 * Represents a class containing attributes and methods pertaining to a car.
 */
public class Car {

    public static final int NUMBER_OF_PARAMETERS = 5;
    private final String model;
    private final String licensePlateNumber;
    private final double price;
    private boolean isRented;
    private boolean isExpensive;

    public Car(String model, String licensePlateNumber, double price) {
        this.model = model;
        this.licensePlateNumber = licensePlateNumber;
        this.price = price;
        isRented = false;
        isExpensive = false;
    }

    public Car(String model, String licensePlateNumber, double price , boolean isRented , boolean isExpensive) {
        this.model = model;
        this.licensePlateNumber = licensePlateNumber;
        this.price = price;
        this.isRented = isRented;
        this.isExpensive = isExpensive;
    }

    public String getModel() {
        return model;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public double getPrice() {
        return price;
    }

    public void markAsRented() {
        isRented = true;
    }

    public void markAsAvailable() {
        isRented = false;
    }

    public void markAsExpensive() {
        isExpensive = true;
    }

    public void markAsCheap() {
        isExpensive = false;
    }

    public boolean isRented() {
        return isRented;
    }

    public boolean isExpensive() {
        return isExpensive;
    }

    public String getExpensiveStatus(){
        if (isExpensive) {
            return "Expensive";
        } else {
            return "Affordable";
        }
    }

    public String getRentedStatus(){
        if (isRented) {
            return "Rented";
        }
        return "Available";
    }

    public String toFileString(){
        return this.getModel() + " | " + this.getLicensePlateNumber()
                + " | " + this.getPrice() + " | " + this.isRented() + " | " + this.isExpensive();
    }
}
