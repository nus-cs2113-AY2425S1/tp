package seedu.duke.parser;

public class InputParser {
    private String parseComponent(String input, String prefix) {
        String[] parts = input.split(" ");
        StringBuilder componentBuilder = new StringBuilder();
        boolean isComponentStarted = false;

        for (String part : parts) {
            if (part.startsWith(prefix)) {
                isComponentStarted = true;
                componentBuilder.append(part.substring(prefix.length())).append(" ");
            } else if (isComponentStarted && !part.contains("/")) {
                componentBuilder.append(part).append(" ");
            } else if (isComponentStarted) {
                break;
            }
        }
        return componentBuilder.toString().trim();
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
            return -1;
        }
    }

    public String parseName(String input) {
        return parseComponent(input, "n/");
    }

    public String parseCategory(String input) {
        return parseComponent(input, "c/");
    }
}
