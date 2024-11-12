package fittrack.graph;

/**
 * Abstract base class that provides utility methods for generating characters and centering text.
 * This class is designed to be extended by specific graph implementations.
 */
public abstract class GraphBase {

    /**
     * Generates a string consisting of the specified character repeated a given number of times.
     *
     * @param num      The number of times to repeat the character.
     * @param character The character to repeat.
     * @return A string containing the repeated character. If num is zero or negative, returns an empty string.
     */
    static String generateChar(int num, char character){
        return String.valueOf(character).repeat(Math.max(0, num));
    }

    /**
     * Centers the target text within a string by adding blank spaces as padding on both sides.
     * The text is padded to fit within the specified maximum length.
     *
     * @param text      The text to be centered.
     * @param maxLength The maximum length of the resulting centered text (including padding).
     * @return A string with the text centered and padded with spaces on both sides.
     *         Includes an extra space before and after the text for separation from adjacent elements.
     */
    static String centerText(String text, int maxLength) {
        int paddingBack =  (maxLength - text.length()) / 2;
        int paddingFront =  maxLength - text.length() - paddingBack;
        //Add 1 for additional spacing from adjacent elements
        return generateChar(paddingFront + 1, ' ') + text +
                generateChar(paddingBack + 1, ' ');
    }
}
