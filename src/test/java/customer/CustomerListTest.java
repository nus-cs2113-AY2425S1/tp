package customer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import file.FileHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerListTest {

    @BeforeEach
    void setUp() {
        CustomerList.clearCustomerList();
    }

    @Test
    public void testAddCustomer() {
        Customer customer1 = new Customer("John" , 18 , "+98414916");
        Customer customer2 = new Customer("Mary" , 20 , "+98411416");
        CustomerList.addCustomer(customer1);
        CustomerList.addCustomer(customer2);
        assertEquals(2 , CustomerList.getCustomers().size());
        assertEquals(customer1, CustomerList.getCustomers().get(0));
        assertEquals(customer2, CustomerList.getCustomers().get(1));

        CustomerList.getCustomers().remove(customer1);
        CustomerList.getCustomers().remove(customer2);
    }
}
