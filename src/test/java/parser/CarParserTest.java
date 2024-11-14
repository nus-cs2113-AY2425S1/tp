package parser;

import car.Car;
import exceptions.CarException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CarParserTest {

    @Test
    public void isValidFormat_validUserInput_expectTrue() {
        String userInput = "add-car /n civic /c 12345 /p 150";
        String userInput1 = "add-car /n Abc /c DEF123X /p 57.30";

        // Valid format refers to correct order/sequence of parameters
        assertTrue(CarParser.isValidFormat(userInput));
        assertTrue(CarParser.isValidFormat(userInput1));
    }

    @Test
    public void isValidFormat_invalidUserInput_expectFalse() throws CarException, NumberFormatException {
        String userInput = "add-car civic ABC123 150";
        String userInput1 = "add-car /c BCE123 /n civic /p 150";
        String userInput2 = "add-car /p 130 /c XYZ888 /n civic";

        // Valid format refers to correct order/sequence of parameters
        assertFalse(CarParser.isValidFormat(userInput));
        assertFalse(CarParser.isValidFormat(userInput1));
        assertFalse(CarParser.isValidFormat(userInput2));
    }

    @Test
    public void isValidFormat_missingRequiredFields_expectFalse() {
        String userInput = "add-car /n civic /p 150"; // Missing license plate number
        String userInput1 = "add-car /c 12345 /p 150"; // Missing car name

        assertFalse(CarParser.isValidFormat(userInput));
        assertFalse(CarParser.isValidFormat(userInput1));
    }

    @Test
    public void isValidFormat_boundaryPriceValue_expectTrue() {
        String userInput = "add-car /n civic /c 12345 /p 0"; // Boundary price value (0)
        String userInput1 = "add-car /n civic /c 12345 /p 99999"; // Upper boundary value for price

        assertTrue(CarParser.isValidFormat(userInput));
        assertTrue(CarParser.isValidFormat(userInput1));
    }

    @Test
    public void parseIntoCar_validUserInput_carObjectCreated() {
        String userInput = "add-car /n civic /c SGT1234X /p 150";

        Car car = CarParser.parseIntoCar(userInput);
        assertEquals("civic", car.getModel());
        assertEquals("SGT1234X", car.getLicensePlateNumber());
        assertEquals(150, car.getPrice());
    }

    @Test
    public void isValidPrice_nonNegativePrice_expectTrue() {
        assertTrue(CarParser.isValidPrice(100));
        assertTrue(CarParser.isValidPrice(100.50));
        assertTrue(CarParser.isValidPrice(100.3));
        assertTrue(CarParser.isValidPrice(100.1234560));
        assertTrue(CarParser.isValidPrice(0));
    }

    @Test
    public void isValidPrice_negativePrice_expectFalse() {
        assertFalse(CarParser.isValidPrice(-10));
        assertFalse(CarParser.isValidPrice(-10.5));
        assertFalse(CarParser.isValidPrice(-10.56));
    }

    @Test
    public void isValidLicensePlateNumber_validLicensePlateNumberFormat_expectTrue() {
        assertTrue(CarParser.isValidLicensePlateNumber("SGE1234X"));
        assertTrue(CarParser.isValidLicensePlateNumber("STD123Y"));
        assertTrue(CarParser.isValidLicensePlateNumber("SRC12Z"));
        assertTrue(CarParser.isValidLicensePlateNumber("SLB1A"));
    }

    @Test
    public void isValidLicensePlateNumber_invalidLicensePlateNumberFormat_expectFalse() {
        // License plate number doesn't start with S
        assertFalse(CarParser.isValidLicensePlateNumber("1234"));
        assertFalse(CarParser.isValidLicensePlateNumber("ABC1234"));

        // License plate number length not in the valid range (>= 5 && <= 8)
        assertFalse(CarParser.isValidLicensePlateNumber("SG1T"));
        assertFalse(CarParser.isValidLicensePlateNumber("SGET12345B"));

        // Numeric part of license plate number contains letters
        assertFalse(CarParser.isValidLicensePlateNumber("SGF1A35T"));
        assertFalse(CarParser.isValidLicensePlateNumber("SABCDET"));

        // Numeric part of license plate number starts with 0
        assertFalse(CarParser.isValidLicensePlateNumber("SDT0017B"));
    }

    @Test
    public void parseIntoCar_invalidUserInput_carExceptionThrown() throws CarException {
        String userInput = "add-car /n civic /c JKL12345 /p -138";
        String userInput1 = "add-car /n civic ABC123 /p 150";

        assertThrows(CarException.class, () -> CarParser.parseIntoCar(userInput));
        assertThrows(CarException.class, () -> CarParser.parseIntoCar(userInput1));
    }

    @Test
    public void parseIntoCar_boundaryPriceValue_carObjectCreated() {
        String userInput = "add-car /n civic /c SCT6677K /p 0"; // Minimum price
        String userInput1 = "add-car /n civic /c SPL9773R /p 10000"; // Maximum valid price

        Car car = CarParser.parseIntoCar(userInput);
        assertEquals(0, car.getPrice());

        Car car1 = CarParser.parseIntoCar(userInput1);
        assertEquals(10000, car1.getPrice());
    }

    @Test
    public void parseIntoCar_invalidPrice_expectCarException() {
        String userInput = "add-car /n civic /c SCT1234N /p -100"; // Negative price
        String userInput1 = "add-car /n civic /c SBE678L /p abc"; // Non-numeric price
        String userInput2 = "add-car /n civic /c SBE678L /p 99999999999999999999"; // Price exceeding limit

        assertThrows(CarException.class, () -> CarParser.parseIntoCar(userInput));
        assertThrows(NumberFormatException.class, () -> CarParser.parseIntoCar(userInput1));
        assertThrows(CarException.class, () -> CarParser.parseIntoCar(userInput2));
    }
}
