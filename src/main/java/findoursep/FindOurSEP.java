package findoursep;

import exceptions.SEPException;
import filehandler.FileHandler;
import studentlist.StudentList;
import ui.UI;
import parser.Parser;

import java.io.IOException;

public class FindOurSEP {
    private UI ui;
    private Parser parser;
    private StudentList studentList;
    private FileHandler fileHandler;

    /**
     * Constructs a new FindOurSEP object.
     * This constructor initializes the UI and parser.
     */
    public FindOurSEP() {
        this.ui = new UI();
        this.studentList = new StudentList(this.ui);
        this.parser = new Parser(this.studentList,this.ui);
    }

    /**
     * Initializes the program using the user's .csv, .json or .txt file.
     * Alternatively by the user's manual input. Calls FileHandler to parse
     * the text in the file and create the corresponding Student object.
     */
    public void setUpFileHandler() {
        this.ui.printConfigMessage();
        String filePath = this.ui.promptFilePath();
        this.fileHandler = new FileHandler(filePath,this.parser);
        if (filePath.isEmpty()) {
            return;
        }
        try {
            if (this.fileHandler.hasProcessFileSuccessfully()) {
                this.ui.printFileLoadSuccessMessage();
            } else {
                this.ui.printProcessError();
            }
        } catch (SEPException | IOException e) {
            this.ui.printResponse(e.getMessage());
        }
    }

    /**
     * The main loop of the application.
     * This method will continue to loop until the user chooses to exit the
     * application. It will read the user's input, process it.
     */
    public void start() {
        this.setUpFileHandler();
        String line;
        boolean isRunning = true;
        while (isRunning) {
            line = this.ui.getUserInput();
            isRunning = this.parser.parseInput(line);
        }
        if (this.ui.toSave()) {
            String choice = this.ui.getSaveFileChoice();
            assert choice != null;
            this.fileHandler.saveAllocationResults(this.studentList.getList(),choice);
        }
        this.ui.sayBye();
    }

    /**
     * Main entry-point for the findoursep.FindOurSEP application.
     */
    public static void main(String[] args) {
        FindOurSEP bob = new FindOurSEP();
        bob.start();
    }
}
