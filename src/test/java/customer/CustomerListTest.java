package customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerListTest {

    @BeforeEach
    void setUp() {
        CustomerList.removeAllCustomers();
    }

    @Test
    public void testAddCustomer() {
        Customer customer1 = new Customer("John" , 18 , "88414916");
        Customer customer2 = new Customer("Mary" , 20 , "88411416");
        CustomerList.addCustomer(customer1);
        CustomerList.addCustomer(customer2);
        assertEquals(2 , CustomerList.getCustomers().size());
        assertEquals(customer1, CustomerList.getCustomers().get(0));
        assertEquals(customer2, CustomerList.getCustomers().get(1));

        CustomerList.getCustomers().remove(customer1);
        CustomerList.getCustomers().remove(customer2);
    }
}