package seedu.duke.flashutils.commands;

import seedu.duke.flashutils.types.Card;
import seedu.duke.flashutils.types.FlashBook;
import seedu.duke.flashutils.types.FlashCardSet;

import java.util.HashMap;
import java.util.Map;

public class ViewAllCommand extends Command {
    @Override
    public CommandResult execute() {
        HashMap<String, FlashCardSet> sets = FlashBook.getInstance().getAllFlashCardSets();
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, FlashCardSet> entry : sets.entrySet()) {
            sb.append("MODULE NAME: ").append(entry.getKey()).append("\n");
            int flashCardIndex = 1;
            for (Card card : entry.getValue()) {
                sb.append(String.format("#%1$s -> ",flashCardIndex)).append(card.toString()).append("\n");
                flashCardIndex+=1;
            }
            sb.append("\n");
        }

        if (sb.length() >= 2 && sb.substring(sb.length() - 2).equals("\n\n")) {
            sb.delete(sb.length() - 2, sb.length());
        }

        return new CommandResult(sb.toString());
    }
}
