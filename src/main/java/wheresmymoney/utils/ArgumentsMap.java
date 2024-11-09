package wheresmymoney.utils;

import wheresmymoney.exception.InvalidInputException;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.HashMap;

public class ArgumentsMap extends HashMap<String, String> {
    public String getRequiredArgument(String argumentName)
            throws WheresMyMoneyException {
        String errorMessage = "Required argument not given: " + argumentName;
        if (argumentName.equals(Parser.ARGUMENT_MAIN)) {
            errorMessage = errorMessage.concat("\nThe " + argumentName +
                    " is keyed in as such: <command> <index> ...remaining arguments");
        } else {
            errorMessage = errorMessage.concat("\nThe " + argumentName +
                    " is keyed in as such: <command> [index] /" + argumentName + " value ...remaining arguments");
        }
        if (!containsKey(argumentName)) {
            throw new InvalidInputException(errorMessage);
        }
        return get(argumentName);
    }

    public Integer getIndex()
            throws WheresMyMoneyException {
        String indexArgument = getRequiredArgument(Parser.ARGUMENT_MAIN);
        try {
            return Integer.parseInt(indexArgument) - 1;
        } catch (Exception e) {
            throw new InvalidInputException("Index given is invalid. Check if it is a number, or in range.");
        }
    }

    public Float getPrice() {
        String priceString = get(Parser.ARGUMENT_PRICE);
        Float price;
        try {
            price = Float.parseFloat(priceString);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Price given is invalid.");
        }
        assert price != null;
        return price;
    }
}
