package seedu.duke.command;

import seedu.duke.financial.FinancialList;

public class ExitCommand extends Command {

    final String goodByeMessage = "--------------------------------------------\n" +
                "Goodbye! Hope to see you again soon :)\n" +
                "--------------------------------------------\n";

    @Override
    public void execute(FinancialList list) {

        System.out.println(goodByeMessage);
    }

    public boolean isExit() {
        return true;
    }

}
