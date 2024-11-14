package car;

import exceptions.CarException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class CarListTest {

    @Test
    public void formatPriceToTwoDp_validPriceMoreThanOrEqualToZero_priceFormattedToTwoDp() {
        assertEquals("123.08", CarList.formatPriceToTwoDp(123.08));
        assertEquals("123.40", CarList.formatPriceToTwoDp(123.4));
        assertEquals("123.46", CarList.formatPriceToTwoDp(123.456));
        assertEquals("123.00", CarList.formatPriceToTwoDp(123.0000000002));
        assertEquals("123.00", CarList.formatPriceToTwoDp(123));
    }

    @Test
    public void addCar_validCarObject_carAddedToList() {
        Car car = new Car("Toyota Camry", "SLK12D", 180);
        Car car1 = new Car("Nissan Qashqai", "SKA88X", 250);

        CarList.addCar(car);
        CarList.addCar(car1);

        assertEquals(2, CarList.getCarsList().size());
        assertEquals(car, CarList.getCarsList().get(0));
        assertEquals(car1, CarList.getCarsList().get(1));

        CarList.getCarsList().clear();
    }

    @Test
    public void addCar_duplicateLicensePlateNumber_carExceptionThrown() throws CarException{
        Car car = new Car("BYD seal", "SND26F", 2500);
        CarList.addCar(car);

        Car car1 = new Car("BYD Atto", "SND26F", 1500);
        assertThrows(CarException.class, () -> CarList.addCar(car1));

        CarList.getCarsList().clear();
    }

    @Test
    public void removeCar_carRemovedFromList() {
        Car car = new Car("Audi TT", "SCD99S", 6800);
        Car car1 = new Car("BMW M3", "SCE19N", 4500);
        Car car2 = new Car("Mercedes CLA", "SPL5569V", 5800);

        CarList.addCar(car);
        CarList.addCar(car1);
        CarList.addCar(car2);

        // After adding Car objects and before removing a car
        assertEquals(3, CarList.getCarsList().size());

        // License plate number provided not found in list
        CarList.removeCar("SBA1111W");
        assertEquals(3, CarList.getCarsList().size());

        // License plate number provided found in list
        CarList.removeCar("SCE19N");
        assertEquals(2, CarList.getCarsList().size());

        CarList.getCarsList().clear();
    }

    @Test
    public void markCarAsRented_existingLicensePlateNumber_carMarkedAsRented() {
        Car car = new Car("Ford Focus", "SKP77Y", 520);
        CarList.addCar(car);

        CarList.markCarAsRented("SKP77Y");
        assertTrue(car.isRented());

        CarList.getCarsList().clear();
    }

    @Test
    public void markCarAsAvailable_existingLicensePlateNumber_carMarkedAsAvailable() {
        Car car = new Car("Suzuki swift", "SJP9982R", 260);
        CarList.addCar(car);

        CarList.markCarAsAvailable("SJP9982R");
        assertFalse(car.isRented());

        CarList.getCarsList().clear();
    }

    @Test
    public void testCarList() {
        CarList.addCar(new Car("Honda Civic" , "SGE1234T" , 123));
        CarList.addCar(new Car("Hyundai" , "SFR124T" , 133));
        CarList.printCarList();
    }
}
