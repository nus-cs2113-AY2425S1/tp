//@@author glenda-1506
package seedu.spendswift.parser;

public class InputParser {
    private String parseComponent(String input, String prefix) {
        int startIndex = input.indexOf(prefix);
        if (startIndex == -1) {
            return "";
        }

        startIndex += prefix.length();
        int endIndex = input.length();
        String[] prefixes = {"n/", "a/", "c/", "e/", "l/"};

        for (String otherPrefix : prefixes) {
            if (!otherPrefix.equals(prefix)) {
                int prefixIndex = input.indexOf(otherPrefix, startIndex);
                if (prefixIndex != -1 && prefixIndex < endIndex) {
                    endIndex = prefixIndex;
                }
            }
        }

        return input.substring(startIndex, endIndex).trim();
    }

    public int parseIndex(String input) {
        String indexStr = parseComponent(input, "e/");
        try {
            return Integer.parseInt(indexStr) - 1; // Convert to 0-based index
        } catch (NumberFormatException e) {
            System.out.println("Invalid expense index format. Please enter a valid number after 'e/'.");
            return -1;
        }
    }

    public double parseLimit(String input) {
        String limitStr = parseComponent(input, "l/");
        try {
            return Double.parseDouble(limitStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid limit format. Please enter a valid number after 'l/'.");
            return -1;
        }
    }

    public double parseAmount(String input) {
        String amountStr = parseComponent(input, "a/");
        try {
            return Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount format. Please enter a valid number after 'a/'.");
            return Double.NaN;
        }
    }

    public String parseName(String input) {
        return parseComponent(input, "n/");
    }

    public String parseCategory(String input) {
        return parseComponent(input, "c/");
    }
}
