package customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import exceptions.CustomerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerTest {
    @BeforeEach
    void setUp() {
        CustomerList.removeAllCustomers();
    }

    @Test
    public void testValidCustomerCreation() {
        Customer customer = new Customer("John Doe", 25, "92345678");
        assertEquals("John Doe", customer.getCustomerName());
        assertEquals(25, customer.getAge());
        assertEquals("92345678", customer.getContactNumber());
    }

    @Test
    public void testAddCustomerWithInvalidAge() {
        CustomerException exception = assertThrows(CustomerException.class, () -> {
            new Customer("John Doe", 17, "92345678");  // Age is 17, which is invalid
        });

        assertEquals("Illegal driver!! Age should be more than 17!!", exception.getMessage());
    }

    @Test
    public void testSetInvalidAge() {
        Customer customer = new Customer("John Doe", 25, "92345678");

        CustomerException exception = assertThrows(CustomerException.class, () -> {
            customer.setAge(16);  // Attempt to set an invalid age
        });

        assertEquals("Illegal driver!! Age should be more than 17!!", exception.getMessage());
    }

    @Test
    public void testAddCustomerWithValidAge() {
        Customer customer = new Customer("Jane Doe", 18, "92345678");  // Minimum valid age
        assertEquals(18, customer.getAge());
    }

    @Test
    public void testCustomerGetters() {
        Customer customer = new Customer("John", 18, "97193723");
        assertEquals("John", customer.getCustomerName());
        assertEquals(18, customer.getAge());
        assertEquals("97193723", customer.getContactNumber());
    }

    @Test
    public void testSetters() {
        Customer customer = new Customer("John", 18, "97193723");
        customer.setCustomerName("Mary");
        customer.setAge(20);
        customer.setContactNumber("97529752");
        assertEquals("Mary", customer.getCustomerName());
        assertEquals(20, customer.getAge());
        assertEquals("97529752", customer.getContactNumber());
    }

    @Test
    public void testInvalidNameWithSpecialCharacters() {
        assertThrows(CustomerException.class, () -> {
            new Customer("John@Doe", 25, "91234567");
        });
    }

    @Test
    public void testInvalidNameWithNumbers() {
        assertThrows(CustomerException.class, () -> {
            new Customer("John123", 25, "91234567");
        });
    }

    @Test
    public void testValidNameWithSpaces() {
        Customer customer = new Customer("John Doe", 30, "92345678");
        assertEquals("John Doe", customer.getCustomerName());
    }
}
