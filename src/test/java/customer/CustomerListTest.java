package customer;

import exceptions.CustomerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerListTest {

    @BeforeEach
    void setUp() {
        CustomerList.removeAllCustomers();
    }

    @Test
    public void testAddCustomer() {
        Customer customer1 = new Customer("John", 18, "88414916");
        Customer customer2 = new Customer("Mary", 20, "88411416");
        CustomerList.addCustomer(customer1);
        CustomerList.addCustomer(customer2);
        assertEquals(2, CustomerList.getCustomers().size());
        assertEquals(customer1, CustomerList.getCustomers().get(0));
        assertEquals(customer2, CustomerList.getCustomers().get(1));
    }

    @Test
    public void testAddCustomerWithDuplicateName() {
        Customer customer1 = new Customer("John", 18, "88414916");
        Customer customer2 = new Customer("john", 20, "88411416");
        CustomerList.addCustomer(customer1);
        assertThrows(CustomerException.class, () -> {
            CustomerList.addCustomer(customer2);
        });
    }

    @Test
    public void testAddCustomerWithInvalidCharactersInName() {
        CustomerException exception = assertThrows(CustomerException.class, () -> {
            new Customer("John@Doe", 25, "92345678");
        });

        assertEquals("The customer name \"John@Doe\" contains invalid characters. " +
                "Only alphabetic characters and spaces are allowed.", exception.getMessage());
    }

    @Test
    public void testAddCustomerWithInvalidCharactersInNameUnderscore() {
        CustomerException exception = assertThrows(CustomerException.class, () -> {
            new Customer("John_Doe", 25, "92345678");
        });

        assertEquals("The customer name \"John_Doe\" contains invalid characters. " +
                "Only alphabetic characters and spaces are allowed.", exception.getMessage());
    }

    @Test
    public void testAddCustomerWithInvalidCharactersInNameWithNumbers() {
        CustomerException exception = assertThrows(CustomerException.class, () -> {
            new Customer("John123", 25, "92345678");
        });

        assertEquals("The customer name \"John123\" contains invalid characters. " +
                "Only alphabetic characters and spaces are allowed.", exception.getMessage());
    }

    @Test
    public void testAddCustomerWithInvalidContactNumberTooShort() {
        CustomerException exception = assertThrows(CustomerException.class, () -> {
            new Customer("John Doe", 25, "12345");
        });

        assertEquals("Invalid contact number. Format for contact number is [8 DIGITS AND STARTS WITH 8 OR 9]",
                exception.getMessage());
    }

    @Test
    public void testAddCustomerWithInvalidContactNumberTooLong() {
        CustomerException exception = assertThrows(CustomerException.class, () -> {
            new Customer("John Doe", 25, "12345678901");
        });

        assertEquals("Invalid contact number. Format for contact number is [8 DIGITS AND STARTS WITH 8 OR 9]",
                exception.getMessage());
    }

    @Test
    public void testAddCustomerWithInvalidContactNumberStartingWithInvalidDigit() {
        CustomerException exception = assertThrows(CustomerException.class, () -> {
            new Customer("John Doe", 25, "71234567");
        });

        assertEquals("Invalid contact number. Format for contact number is [8 DIGITS AND STARTS WITH 8 OR 9]",
                exception.getMessage());
    }

    @Test
    public void testAddCustomerWithValidName() {
        Customer customer = new Customer("John Doe", 25, "92345678");
        CustomerList.addCustomer(customer);
        assertEquals(1, CustomerList.getCustomers().size());
        assertEquals("John Doe", CustomerList.getCustomers().get(0).getCustomerName());
    }

    @Test
    public void testRemoveCustomer_existingCustomer() {
        Customer customer = new Customer("John Doe", 30, "91234567");
        CustomerList.addCustomerWithoutPrintingInfo(customer);

        CustomerList.removeCustomer("John Doe");

        int customerListLength = CustomerList.getCustomers().size();
        assertFalse(CustomerList.isExistingCustomer("John Doe"));
        assertEquals(0, customerListLength, "Customer list should be empty after removal.");
    }

    @Test
    public void testRemoveCustomer_nonExistingCustomer() {
        Customer customer = new Customer("Jane Doe", 28, "81234567");
        CustomerList.addCustomerWithoutPrintingInfo(customer);

        CustomerList.removeCustomer("John Smith");

        int customerListLength = CustomerList.getCustomers().size();
        assertTrue(CustomerList.isExistingCustomer("Jane Doe"));
        assertFalse(CustomerList.isExistingCustomer("John Smith"));
        assertEquals(1, customerListLength, "Customer list should contain 1 customer after non-existent removal.");
    }

    @Test
    public void testRemoveCustomer_caseInsensitive() {
        Customer customer = new Customer("Alice Smith", 35, "91234567");
        CustomerList.addCustomerWithoutPrintingInfo(customer);

        CustomerList.removeCustomer("alice smith");

        int customerListLength = CustomerList.getCustomers().size();
        assertFalse(CustomerList.isExistingCustomer("Alice Smith"));
        assertEquals(0, customerListLength, "Customer list should be empty after removal.");
    }



    @Test
    public void testRemoveCustomer_emptyList() {
        CustomerList.removeCustomer("Non Existent Customer");
        int customerListLength = CustomerList.getCustomers().size();
        assertEquals(0, customerListLength, "Customer list should remain empty when there is no customer.");
    }
}
