package ymfc;

import ymfc.ui.Ui;

public class YMFC {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Ui ui = new Ui(System.in);
        ui.greet();
        String userInput = ui.readCommand();
        System.out.println("Hello " + userInput);
        ui.bidFarewell();
    }
}
