import java.util.Scanner;

public class Main {
    /**
     * Main entry-point for the application
     */
    public static void main(String[] args) {
        System.out.println("WheresMyMoney");
        System.out.println("What is your name?");
        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    }
}
