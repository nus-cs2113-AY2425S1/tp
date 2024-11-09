package seedu.spendswift;

public class ErrorMessage {
    private static final String INPUTPARSER_INVALID_LIMIT = "Invalid limit format. Please enter a valid number after 'l/'.";
    private static final String INPUTPARSER_INVALID_AMOUNT = "Invalid amount format. Please enter a valid number after 'a/'.";
    private static final String EXPENSEMANAGER_EMPTY_NAME = "Invalid input! Please provide a name for your expense.";
    private static final String EXPENSEMANAGER_EMPTY_CATEGORY = "Invalid input! Please provide a category for your expense.";
    private static final String EXPENSEMANAGER_NEGATIVE_AMOUNT = "Invalid input! Please provide a positive amount!";
    private static final String PARSING_ERROR = "Error parsing the input. Please use the correct format.";

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

    public static void printExpensesManagerEmptyName(){
        System.out.println(UI.SEPARATOR);
        System.out.println(EXPENSEMANAGER_EMPTY_NAME);
        System.out.println(UI.SEPARATOR);
    }

    public static void printExpensesManagerEmptyCategory(){
        System.out.println(UI.SEPARATOR);
        System.out.println(EXPENSEMANAGER_EMPTY_CATEGORY);
        System.out.println(UI.SEPARATOR);
    }

    public static void printExpensesManagerNegativeAmount(){
        System.out.println(UI.SEPARATOR);
        System.out.println(EXPENSEMANAGER_NEGATIVE_AMOUNT);
        System.out.println(UI.SEPARATOR);
    }

    public static void printParsingError(){
        System.out.println(UI.SEPARATOR);
        System.out.println(PARSING_ERROR);
        System.out.println(UI.SEPARATOR);
    }
}
