package parser;

public class ParserUtils {

    public static int parseIndex(String indexString) {
        try {
            int index = Integer.parseInt(indexString.trim()) - 1;
            if (index < 0) {
                throw new IllegalArgumentException("Task index must be a positive number.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid task index. Please provide a valid number.");
        }
    }

    public static String[] parseArguments(String argumentString, String... delimiters) {
        String delimiterPattern = String.join(" | ", delimiters);
        return argumentString.split(delimiterPattern);
    }
}
