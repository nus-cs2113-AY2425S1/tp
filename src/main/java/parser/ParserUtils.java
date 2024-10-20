package parser;

public class ParserUtils {

    public static int parseIndex(String indexString) {
        try {
            int index = Integer.parseInt(indexString.trim()) - 1;
            if (index < 0) {
                throw new IllegalArgumentException("Index must be a positive number.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid index. Please provide a valid number.");
        }
    }
}
