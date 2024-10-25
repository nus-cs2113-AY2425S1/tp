package seedu.duke.parser;

import seedu.duke.commands.EditPatientCommand;
import seedu.duke.commands.Command;
import seedu.duke.data.state.State;
import seedu.duke.data.state.StateType;

public class EditParser implements CommandParser {

    @Override
    public Command execute(String line, State state) {
        // can only edit in MAIN_STATE
        if (state.getState() != StateType.MAIN_STATE) {
            System.out.println("You can only edit patients while in the MAIN_STATE.");
            return null;
        }

        //example input: "edit 1 /tag <newTag>"
        String[] parts = line.split(" ");

        if (parts.length < 3 || !parts[2].startsWith("/tag")) { //ensure proper format. edit must have t/.
            System.out.println("Invalid command format. Specify the tag using /tag <newTag>.");
            return null;
        }

        //pass the patient index (2nd part of the command)
        int index;
        try {
            index = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid index provided.");
            return null;
        }
        // combine everything after 't/' to be 1 tag
        String newTag = line.substring(line.indexOf("/tag") + 4).trim();

        return new EditPatientCommand(index, newTag);
    }
}


