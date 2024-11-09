package wheresmymoney.utils;

import wheresmymoney.exception.InvalidInputException;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.HashMap;

public class ArgumentsMap extends HashMap<String, String> {
    public boolean isRecur() {
        return containsKey(Parser.ARGUMENT_RECUR);
    }
    public String getRequired(String argumentName)
            throws InvalidInputException {
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

    public Integer getRequiredIndex()
            throws InvalidInputException {
        String indexArgument = getRequired(Parser.ARGUMENT_MAIN);
        try {
            return Integer.parseInt(indexArgument) - 1;
        } catch (Exception e) {
            throw new InvalidInputException("Index given is invalid. Check if it is a number, or in range.");
        }
    }

    private Float parsePrice(String priceString) throws InvalidInputException {
        Float price;
        try {
            price = Float.parseFloat(priceString);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Price given is invalid.");
        }
        assert price != null;
        if (price <= 0) {
            throw new InvalidInputException("Price cannot take on a value that is less than or equal to 0");
        }
        return price;
    }

    public Float getPrice() throws InvalidInputException {
        String priceString = get(Parser.ARGUMENT_PRICE);
        return parsePrice(priceString);
    }

    public Float getRequiredPrice() throws InvalidInputException {
        String priceString = getRequired(Parser.ARGUMENT_PRICE);
        return parsePrice(priceString);
    }
}
