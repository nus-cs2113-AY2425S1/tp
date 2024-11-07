package fittrack.graph;

public abstract class GraphBase {

    //Returns String of specified char
    static String generateChar(int num, char character){
        return String.valueOf(character).repeat(Math.max(0, num));
    }

    //Centers the target text with blank spaces as padding on both sides depending on the maxLength given
    static String centerText(String text, int maxLength) {
        int paddingBack =  (maxLength - text.length()) / 2;
        int paddingFront =  maxLength - text.length() - paddingBack;
        //Add 1 for additional spacing from adjacent elements
        return generateChar(paddingFront + 1, ' ') + text +
                generateChar(paddingBack + 1, ' ');
    }
}
