package parser;

import customer.Customer;
import exceptions.CustomerException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerParserTest {

    @Test
    void parseParameterContents() {
        String userInput = "add-user /u test /a 18 /c 82750174";
        String[] parameters = { "/u", "/a", "/c" };
        String[] contents = CustomerParser.parseParameterContents(parameters, userInput);
        assert contents[0].equals("test");
        assert contents[1].equals("18");
        assert contents[2].equals("82750174");
    }

    @Test
    void parseIntoCustomer() {
        String userInput = "add-user /u test /a 18 /c 82750174";
        Customer customer = CustomerParser.parseIntoCustomer(userInput);
        assertEquals("test", customer.getCustomerName());
        assertEquals(18, customer.getAge());
        assertEquals("82750174", customer.getContactNumber());
    }

    @Test
    void parseWithValidPhoneNumberStartingWith8() {
        String userInput = "add-user /u validUser /a 25 /c 81234567";
        Customer customer = CustomerParser.parseIntoCustomer(userInput);
        assertEquals("81234567", customer.getContactNumber());
    }

    @Test
    void parseWithValidPhoneNumberStartingWith9() {
        String userInput = "add-user /u validUser /a 30 /c 92345678";
        Customer customer = CustomerParser.parseIntoCustomer(userInput);
        assertEquals("92345678", customer.getContactNumber());
    }

    @Test
    void parseWithInvalidPhoneNumberTooShort() {
        String userInput = "add-user /u invalidUser /a 22 /c 8123456";
        assertThrows(CustomerException.class, () -> {
            CustomerParser.parseIntoCustomer(userInput);
        });
    }

    @Test
    void parseWithInvalidPhoneNumberTooLong() {
        String userInput = "add-user /u invalidUser /a 22 /c 8123456789";
        assertThrows(CustomerException.class, () -> {
            CustomerParser.parseIntoCustomer(userInput);
        });
    }

    @Test
    void parseWithInvalidPhoneNumberStartingWithOtherThan8Or9() {
        String userInput = "add-user /u invalidUser /a 22 /c 71234567";
        assertThrows(CustomerException.class, () -> {
            CustomerParser.parseIntoCustomer(userInput);
        });
    }

    @Test
    void parseWithPhoneNumberStartingWith6() {
        String userInput = "add-user /u invalidUser /a 22 /c 61234567";
        assertThrows(CustomerException.class, () -> {
            CustomerParser.parseIntoCustomer(userInput);
        });
    }

    @Test
    void parseWithPhoneNumberStartingWith0() {
        String userInput = "add-user /u invalidUser /a 22 /c 01234567";
        assertThrows(CustomerException.class, () -> {
            CustomerParser.parseIntoCustomer(userInput);
        });
    }

    @Test
    void parseWithPhoneNumberContainingLetters() {
        String userInput = "add-user /u invalidUser /a 22 /c 9ABC5678";
        assertThrows(CustomerException.class, () -> {
            CustomerParser.parseIntoCustomer(userInput);
        });
    }

    @Test
    void parseWithPhoneNumberContainingSpecialCharacters() {
        String userInput = "add-user /u invalidUser /a 22 /c 9234@678";
        assertThrows(CustomerException.class, () -> {
            CustomerParser.parseIntoCustomer(userInput);
        });
    }

    @Test
    void parseWithPhoneNumberContainingSpace() {
        String userInput = "add-user /u invalidUser /a 22 /c 9234 5678";
        assertThrows(CustomerException.class, () -> {
            CustomerParser.parseIntoCustomer(userInput);
        });
    }

    @Test
    void parseWithValidPhoneNumberEightDigitsOnly() {
        String userInput = "add-user /u validUser /a 28 /c 98765432";
        Customer customer = CustomerParser.parseIntoCustomer(userInput);
        assertEquals("98765432", customer.getContactNumber());
    }
}
