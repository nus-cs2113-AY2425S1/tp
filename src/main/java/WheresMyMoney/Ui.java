package WheresMyMoney;
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
     */
    public void commandEntryLoop(){
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
                isAskingInput = parser.commandMatching(argumentsList);
            } catch (WheresMyMoneyException e){
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Runs the entire program
     */
    public void run(){
        displayIntroText();
        commandEntryLoop();
    }
}