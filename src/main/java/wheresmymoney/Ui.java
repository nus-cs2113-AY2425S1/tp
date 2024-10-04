package wheresmymoney;
import java.util.HashMap;
import java.util.Scanner;

public class Ui {
    public Parser parser;
    public Ui(){
        parser = new Parser();
    }

    /**
     * Displays an Introductory message to the user
     */
    public static void displayIntroText(){
        System.out.println("WheresMyMoney");
        System.out.println("What can I do for you?");
    }

    /**
     * Runs a loop where the user can enter commands, until they exit
     * @param expenseList Object representing the list of expenses
     */
    public void commandEntryLoop(ExpenseList expenseList){
        // Command Entry
        String line;
        HashMap<String, String> argumentsList;
        Scanner scanner = new Scanner(System.in);
        boolean isAskingInput = true;
        while (isAskingInput){
            System.out.print("> ");
            line = scanner.nextLine();
            argumentsList = parser.parseCommandToArguments(line);
            try {
                isAskingInput = parser.commandMatching(argumentsList, expenseList);
            } catch (WheresMyMoneyException e){
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    /**
     * Runs the entire program
     * @param expenseList Object representing the list of expenses
     */
    public void run(ExpenseList expenseList){
        displayIntroText();
        commandEntryLoop(expenseList);
    }
}
