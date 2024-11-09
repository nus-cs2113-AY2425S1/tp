package seedu.spendswift;

public class ErrorMessage {
    private static final String INPUTPARSER_INVALID_LIMIT = "Invalid limit format. Please enter a valid number after 'l/'.";
    private static final String INPUTPARSER_INVALID_AMOUNT = "Invalid amount format. Please enter a valid number after 'a/'.";

    public static void printInputParserInvalidLimit(){
        System.out.println(UI.SEPARATOR);
        System.out.println(INPUTPARSER_INVALID_LIMIT);
        System.out.println(UI.SEPARATOR);
    }

    public static void printInputParserInvalidAmount(){
        System.out.println(UI.SEPARATOR);
        System.out.println(INPUTPARSER_INVALID_AMOUNT);
        System.out.println(UI.SEPARATOR);
    }
}
