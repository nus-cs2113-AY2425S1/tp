package ymfc;

import ymfc.parser.Parser;
import ymfc.commands.Command;
import ymfc.exception.InvalidArgumentException;
import ymfc.exception.InvalidCommandException;
import ymfc.recipelist.RecipeList;
import ymfc.ui.Ui;

public class YMFC {
    /**
     * Main entry-point for the java.ymfc.YMFC application.
     */
    public static void main(String[] args) {

        Ui ui = new Ui(System.in);
        RecipeList recipeList = new RecipeList();
        boolean saidBye = false;

        ui.greet();
        String userInput;
        while (!saidBye) {
            userInput = ui.readCommand();

            try {
                Command command = Parser.parseCommand(userInput);
                command.execute(recipeList, ui);

                if (command.isBye()) {
                    saidBye = true;
                }
            } catch (InvalidArgumentException | InvalidCommandException e) {
                System.out.println(e.getMessage());
            }


        }

    }

}
