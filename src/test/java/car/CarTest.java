package car;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarTest {

    @Test
    public void testCarGetters_validParams() {
        Car car = new Car("Toyota 86", "SJX1234D", 120);
        assertEquals("Toyota 86", car.getModel());
        assertEquals("SJX1234D", car.getLicensePlateNumber());
        assertEquals(120, car.getPrice());
        assertFalse(car.isRented());
        assertEquals("Available", car.getRentedStatus());

        Car car1 = new Car("Honda Fit", "SGE1234X", 50.69);
        assertEquals("Honda Fit", car1.getModel());
        assertEquals("SGE1234X", car1.getLicensePlateNumber());
        assertEquals(50.69, car1.getPrice());
        assertFalse(car1.isRented());
        assertEquals("Available", car1.getRentedStatus());
    }

    @Test
    public void markAsRented_validCarObject_carMarkedAsRented() {
        Car car = new Car("Subaru BRZ", "SDC443M", 123.45);
        car.markAsRented();
        assertTrue(car.isRented());
        assertEquals("Rented", car.getRentedStatus());
    }

    @Test
    public void markAsAvailable_validCarObject_carMarkedAsAvailable() {
        Car car = new Car("Toyota Corolla", "SNE12T", 55.99);
        car.markAsAvailable();
        assertFalse(car.isRented());
        assertEquals("Available", car.getRentedStatus());
    }
}
