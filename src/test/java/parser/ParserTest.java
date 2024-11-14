package parser;

import car.CarList;
import customer.CustomerList;
import exceptions.CliRentalException;
import exceptions.CustomerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ParserTest {

    @BeforeEach
    public void setUp() {
        CustomerList.getCustomers().clear();
        CarList.getCarsList().clear();
    }

    @Test
    public void parse_validUserInput_commandActionExecuted() throws CliRentalException {
        String userInput = "add-user /u John Doe /a 18 /c 82750174";

        assertFalse(Parser.parse(userInput));
        assertEquals(1, CustomerList.getCustomers().size());
        assertEquals("John Doe", CustomerList.getCustomers().get(0).getCustomerName());

        String userInput1 = "add-car /n Honda Civic /c SGE4966P /p 123";
        assertFalse(Parser.parse(userInput1));
        assertEquals(1, CarList.getCarsList().size());
        assertEquals(123.0, CarList.getCarsList().get(0).getPrice());

        String userInput2 = "exit";
        assertTrue(Parser.parse(userInput2));
    }

    @Test
    public void parse_invalidUserInput_invalidCharactersInName() {
        String userInput = "add-user /u John@Doe /a 25 /c 82750174";
        assertThrows(CustomerException.class, () -> Parser.parse(userInput));

        String userInput1 = "add-user /u Jane_Doe /a 30 /c 92345678";
        assertThrows(CustomerException.class, () -> Parser.parse(userInput1));

        String userInput2 = "add-user /u Mike123 /a 22 /c 83456789";
        assertThrows(CustomerException.class, () -> Parser.parse(userInput2));

        String userInput3 = "add-user /u Sarah! /a 28 /c 94567890";
        assertThrows(CustomerException.class, () -> Parser.parse(userInput3));
    }

    @Test
    public void parse_invalidUserInput_duplicateNames() throws CliRentalException {
        String userInput = "add-user /u John Doe /a 18 /c 82750174";
        Parser.parse(userInput);

        // Try to add the same name in different cases
        String duplicateUserInput1 = "add-user /u john doe /a 21 /c 92345678";
        assertThrows(CustomerException.class, () -> Parser.parse(duplicateUserInput1));

        String duplicateUserInput2 = "add-user /u JOHN DOE /a 22 /c 83456789";
        assertThrows(CustomerException.class, () -> Parser.parse(duplicateUserInput2));

        // Exact duplicate
        String duplicateUserInput3 = "add-user /u John Doe /a 25 /c 91234567";
        assertThrows(CustomerException.class, () -> Parser.parse(duplicateUserInput3));
    }

    @Test
    public void parse_invalidUserInput_tooYoung() {
        String userInput = "add-user /u John Doe /a 16 /c 82750174"; // Age below minimum
        assertThrows(CustomerException.class, () -> Parser.parse(userInput));
    }

    @Test
    public void parse_invalidUserInput_invalidContactNumber() {
        String userInput = "add-user /u John Doe /a 25 /c 123456"; // Too short
        assertThrows(CustomerException.class, () -> Parser.parse(userInput));

        String userInput1 = "add-user /u Jane Doe /a 30 /c 9123456789"; // Too long
        assertThrows(CustomerException.class, () -> Parser.parse(userInput1));

        String userInput2 = "add-user /u Mike Doe /a 22 /c 71234567"; // Starts with invalid digit
        assertThrows(CustomerException.class, () -> Parser.parse(userInput2));
    }
}
