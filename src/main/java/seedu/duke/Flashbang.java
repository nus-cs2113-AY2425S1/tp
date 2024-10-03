package seedu.duke;

import java.util.Scanner;

public class Flashbang {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = "";
        while (!input.equals("quit")) {
            input = scan.nextLine();
            System.out.println("input: " + input);
        }
    }
}
