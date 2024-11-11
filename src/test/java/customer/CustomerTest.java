package customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CustomerTest {

    @Test
    public void testCustomerGetters() {
        Customer customer = new Customer("John" , 18 , "97193723");
        assertEquals("John" , customer.getCustomerName());
        assertEquals(18 , customer.getAge());
        assertEquals("97193723" , customer.getContactNumber());
    }

    @Test
    public void testSetters(){
        Customer customer = new Customer("John" , 18 , "97193723");
        customer.setCustomerName("Mary");
        customer.setAge(20);
        customer.setContactNumber("97529752");
        assertEquals("Mary" , customer.getCustomerName());
        assertEquals(20 , customer.getAge());
        assertEquals("97529752" , customer.getContactNumber());
    }
}