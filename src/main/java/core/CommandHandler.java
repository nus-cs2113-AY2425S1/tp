package core;

import command.Command;
import command.CommandResult;
import command.ExitCommand;
import history.History;
import parser.Parser;
import programme.ProgrammeList;
import ui.Ui;

public class CommandHandler {
    private boolean isRunning;
    private final Parser parser;

    public CommandHandler(){
        this.isRunning = true;
        this.parser = new Parser();
    }

    public void run(Ui ui, ProgrammeList programmes, History history){
        while (isRunning){
            try{
                String fullCommand = ui.readCommand();
                Command command = parser.parse(fullCommand);
                CommandResult result = command.execute(programmes, history);
                ui.showMessage(result);

                if (command instanceof ExitCommand){
                    isRunning = false;
                }

            } catch(Exception e){
                ui.showError(e);
            }
        }
    }
}
