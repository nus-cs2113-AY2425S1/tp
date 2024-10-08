package YMFC;

import java.util.Scanner;

public class YMFC {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.greet();

        String userInput = ui.readCommand();
        System.out.println("Hello " + userInput);
        ui.bidFarewell();
    }
}
