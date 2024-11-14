package parser;

import customer.Customer;
import exceptions.CustomerException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerParser {

    private static final String ADD_CUSTOMER_COMMAND = "add-user";

    /**
     * Creates new customer object based on user input.
     *
     * @throws CustomerException if input is not compliant with format.
     * @throws NumberFormatException if the age and contact content are not integer string.
     */
    public static Customer parseIntoCustomer(String userInput) throws CustomerException, NumberFormatException {
        userInput = userInput.substring(ADD_CUSTOMER_COMMAND.length()).trim();
        String[] parameters = { "/u" , "/a" , "/c"};
        String[] parameterContents;

        if(isValidSequence(parameters, userInput)){
            parameterContents = parseParameterContents(parameters, userInput);
        } else{
            throw CustomerException.addCustomerException();
        }

        if(!isValidContactNumber(parameterContents[2])){
            throw CustomerException.invalidContactNumberException();
        }

        String customerName = parameterContents[0];
        int age = Integer.parseInt(parameterContents[1]);

        if(age <= 17){
            throw CustomerException.invalidAgeException();
        }else if(age > 100){
            throw CustomerException.invalidMaxAgeException();
        }

        String contactNumber = parameterContents[2];
        assert !customerName.isEmpty() : "Customer name should not be empty.";
        assert isValidContactNumber(contactNumber) : "Invalid contact number format.";
        return new Customer(customerName , age, contactNumber );
    }

    /**
     * Parses the user input according to the parameters based on a fixed sequence.
     *
     * @param parameters Sequence to parse the input.
     * @return content of each parameter in sequence.
     * @throws CustomerException when any parameters are empty.
     */
    public static String[] parseParameterContents(String[] parameters, String userInput) throws CustomerException {

        String[] contents = new String[parameters.length];

        for(int i = 0; i < parameters.length - 1; i++){
            int indexOfBeforeParameter = userInput.indexOf(parameters[i]);
            int indexOfAfterParameter = userInput.indexOf(parameters[i+1]);
            int endOfBeforeParameter = indexOfBeforeParameter + parameters[i].length();
            contents[i] = userInput.substring(endOfBeforeParameter, indexOfAfterParameter).trim();
        }

        int indexOfLastParameter = userInput.indexOf(parameters[parameters.length - 1]);
        int endOfLastParameter = indexOfLastParameter + parameters[parameters.length - 1].length();
        contents[parameters.length - 1] = userInput.substring(endOfLastParameter).trim();

        for(int i = 0; i < parameters.length; i++) {
            if(contents[i].isEmpty()){
                throw CustomerException.addCustomerException();
            }
        }

        return contents;
    }

    /**
     * Checks if the string is a valid contact number.
     */
    public static boolean isValidContactNumber(String contactNumber) {
        String regex = "^[89]\\d{7}$"; // Singapore phone number
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(contactNumber);
        return matcher.matches();
    }

    /**
     * Checks if the parameters exist and is in sequence.
     *
     * @param parameters parameters which the input should have , in sequence.
     */
    private static boolean isValidSequence(String[] parameters, String userInput){

        for (String parameter : parameters) {
            if (!userInput.contains(parameter)) {
                return false;
            }
        }

        for(int i = 1 ; i < parameters.length ; i++){
            if(userInput.indexOf(parameters[i]) < userInput.indexOf(parameters[i-1])){
                return false;
            }
        }

        return true;
    }

    public static String parseCustomerForRemoval(String userInput) throws CustomerException {

        String[] words = userInput.split("\\s+", 3);
        if (words.length < 2 || !Objects.equals(words[1], "/u")) {
            throw CustomerException.removeCustomerException();
        } else if (words.length != 3) {
            throw CustomerException.missingNameWhenRemoving();
        } else {
            return words[2];  // assuming input format is: remove-user <customerName>
        }
    }
}
