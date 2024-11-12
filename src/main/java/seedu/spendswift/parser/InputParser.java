//@@glenda-1506
package seedu.spendswift.parser;

import seedu.spendswift.ui.ErrorMessage;

public class InputParser {
    private String parseComponent(String input, String prefix) {
        int startIndex = input.indexOf(prefix);
        if (startIndex == -1) {
            return "";
        }

        startIndex += prefix.length();
        int endIndex = input.length();
        String[] prefixes = {
            "n/",
            "a/",
            "c/",
            "e/",
            "l/",
            "hcur/",
            "ocur/"
        };

        for (String otherPrefix: prefixes) {
            if (!otherPrefix.equals(prefix)) {
                int prefixIndex = input.indexOf(otherPrefix, startIndex);
                if (prefixIndex != -1 && prefixIndex < endIndex) {
                    endIndex = prefixIndex;
                }
            }
        }

        return input.substring(startIndex, endIndex).trim();
    }

    public String parseHomeCurrency(String input) throws IllegalArgumentException {
        return parseCurrency(input, "hcur/");
    }

    public String parseOriginalCurrency(String input) throws IllegalArgumentException {
        return parseCurrency(input, "ocur/");
    }

    private String parseCurrency(String input, String currencyPrefix) throws IllegalArgumentException {
        // Ensure the currency code follows immediately after the prefix and is exactly three letters long
        if (!input.matches(".*" + currencyPrefix + "[a-zA-Z]{3}(\\b|\\s|$).*")) {
            throw new IllegalArgumentException("Error: A 3-letter currency code directly following '" +
                currencyPrefix + "' is needed.");
        }

        String currency = parseComponent(input, currencyPrefix);
        if (currency.isEmpty() || !currency.matches("[a-zA-Z]{3}")) {
            throw new IllegalArgumentException("Error: A valid 3-letter currency code is needed after '" +
                currencyPrefix + "'. Found: '" + currency + "'");
        }

        return currency.toUpperCase();
    }

    public String parseName(String input) {
        return parseComponent(input, "n/");
    }

    public String parseCategory(String input) {
        return parseComponent(input, "c/");
    }
    /**
     * Parses the limit from the input string.
     *
     * @param input The input string containing the limit component prefixed with "l/".
     * @return The parsed limit as a double, capped at 1 quadrillion if exceeded.
     *         Returns Double.NaN if the limit is invalid or not found.
     */
    public static double parseLimit(String input) {
        // Define the prefix to look for
        final String PREFIX = "l/";
        // Define the maximum allowed limit as 1 quadrillion without underscores
        final double QUADRILLION = 1000000000000000.0; // 1,000,000,000,000,000

        // Check if the input is valid and contains the prefix
        if (input == null || !input.contains(PREFIX)) {
            System.err.println("No limit prefix 'l/' found in the input.");
            return Double.NaN;
        }

        // Split the input based on the prefix
        String[] parts = input.split(PREFIX);
        if (parts.length < 2) {
            System.err.println("Limit prefix found but no value specified.");
            return Double.NaN;
        }

        // Extract the substring after the prefix
        String limitStr = parts[1].split(" ")[0].trim();

        try {
            // Parse the limit string into double
            double limit = Double.parseDouble(limitStr);

            // Assertion to ensure the limit does not exceed 1 quadrillion
            assert limit <= QUADRILLION: "Limit exceeds 1 quadrillion, capping to 1 quadrillion";

            // Cap the limit at 1 quadrillion if it exceeds this value
            if (limit > QUADRILLION) {
                System.err.println("Limit exceeds 1 quadrillion, capping to 1 quadrillion.");
                limit = QUADRILLION;
            }

            return limit;
        } catch (NumberFormatException e) {
            System.err.println("Invalid limit format: '" + limitStr + "'.");
            return Double.NaN;
        }
    }
    public double parseAmount(String input) {
        String amountStr = parseComponent(input, "a/");
        try {
            return Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            ErrorMessage.printInvalidAmount();
            return Double.NaN;
        }
    }

    public int parseIndex(String input) {
        String indexStr = parseComponent(input, "e/");
        try {
            return Integer.parseInt(indexStr) - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

