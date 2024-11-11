package wheresmymoney.utils;

import wheresmymoney.Ui;
import wheresmymoney.exception.InvalidInputException;

import java.util.HashMap;

public class ArgumentsMap extends HashMap<String, String> {
    /**
     * Checks if the arguments passed shows that the user wants to access the Recurring Expense List.
     *
     * @return Has the recur argument
     */
    public boolean isRecur() {
        return containsKey(Parser.ARGUMENT_RECUR);
    }

    /**
     * Gets a required argument and throws an exception if that argument is not provided.
     *
     * @param argumentName Name of the argument
     * @return Value of the argument
     * @throws InvalidInputException If argument is not provided
     */
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

    /**
     * Gets a required index and throws an exception if that argument is not provided.
     *
     * @return Value of the index in the ArgumentsMap
     * @throws InvalidInputException If index is not provided
     */
    public Integer getRequiredIndex()
            throws InvalidInputException {
        String indexArgument = getRequired(Parser.ARGUMENT_MAIN);
        try {
            return Integer.parseInt(indexArgument) - 1;
        } catch (Exception e) {
            throw new InvalidInputException("Index given \""+indexArgument+"\" is invalid. Check if it is a number.");
        }
    }

    private Float parsePrice(String priceString, String argumentDisplayName) throws InvalidInputException {
        Float price;
        try {
            price = Float.parseFloat(priceString);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(argumentDisplayName + " given \""+priceString+"\" is invalid. "+
                    "Please make sure it is a number.");
        }
        assert price != null;
        if (price <= 0) {
            throw new InvalidInputException(argumentDisplayName +
                    " cannot take on a value that is less than or equal to 0.");
        }
        if (price >= 9999.99) {
            Ui.displayMessage("Warning: \""+priceString+"\" input is equal to or exceeds 9999.99, "+
                    "which may not be well supported by the program.");
        }
        return price;
    }

    private Float parsePrice(String priceString) throws InvalidInputException {
        return parsePrice(priceString, "Price");
    }

    /**
     * Gets a price and throws an exception if that price is invalid.
     *
     * @return Price
     * @throws InvalidInputException If price is invalid
     */
    public Float getPrice() throws InvalidInputException {
        String priceString = get(Parser.ARGUMENT_PRICE);
        return parsePrice(priceString);
    }

    /**
     * Gets a required price and throws an exception if that price is not provided/ invalid.
     *
     * @return Price
     * @throws InvalidInputException If price is not given or is invalid
     */
    public Float getRequiredPrice() throws InvalidInputException {
        String priceString = getRequired(Parser.ARGUMENT_PRICE);
        return parsePrice(priceString);
    }


    /**
     * Gets a required limit and throws an exception if that limit is not provided/ invalid.
     *
     * @return Price
     * @throws InvalidInputException If limit is not given or is invalid
     */
    public Float getRequiredLimit() throws InvalidInputException {
        String limitString = getRequired(Parser.ARGUMENT_LIMIT);
        return parsePrice(limitString, "Limit");
    }
}
