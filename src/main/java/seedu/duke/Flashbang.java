package seedu.duke;

import seedu.duke.flashutils.commands.Command;
import seedu.duke.flashutils.commands.CommandResult;
import seedu.duke.flashutils.commands.DeleteCommand;
import seedu.duke.flashutils.commands.QuitCommand;
import seedu.duke.flashutils.types.FlashBook;
import seedu.duke.flashutils.utils.Parser;
import seedu.duke.flashutils.utils.Storage;
import seedu.duke.flashutils.utils.Ui;

import java.io.IOException;

import static seedu.duke.flashutils.utils.Ui.displayCommands;

public class Flashbang {
    /**
     * Main entry-point for the java.duke.Flashbang application.
     */

    private Ui ui;
    private Storage storage;
    private FlashBook flashBook;

    private Flashbang(String dataPath) {
        ui = new Ui();
        storage = new Storage(dataPath);
        try {
            FlashBook.setInstance(storage.readFlashCardsFromFile());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        flashBook = FlashBook.getInstance();
    }

    private void run() {
        Ui.welcomeMessage();
        displayCommands();
        String input = "";
        Command command = null;
        while (!(command instanceof QuitCommand)) {
            try {
                input = Ui.getRequest().trim();
                command = Parser.parseCommand(input);
                CommandResult result = command.execute();
                Ui.printResponse(result.feedbackToUser);
                storage.writeFlashBookToFile(FlashBook.getInstance());
                if (command instanceof DeleteCommand && ((DeleteCommand) command).getTargetCard() == null) {
                    storage.deleteFlashCardSetFile(((DeleteCommand) command).getTargetSet().getModuleName());
                }
            } catch (IllegalArgumentException e) {
                Ui.printResponse(e.getMessage());
                displayCommands();
            } catch (IOException e) {
                Ui.printResponse(e.getMessage() + "\nAn IO Exception has been detected, please reset the App!");
            }
        }
    }

    /**
     * Main function to run the Flashbang app
     */
    public static void main(String[] args) {
        new Flashbang("./data").run();
    }
}
