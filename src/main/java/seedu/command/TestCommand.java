package seedu.command;

import seedu.message.ErrorMessages;

import java.util.ArrayList;
import java.util.List;

public class TestCommand extends Command {
    public static final String COMMAND_WORD = "test";
    public static final String COMMAND_GUIDE = "test arg1/ MANDADOTY arg2/ EXTRA: A command for testing.";
    public static final String[] COMMAND_MANDATORY_KEYWORDS = {"arg1/"};
    public static final String[] COMMAND_EXTRA_KEYWORDS = {"arg2/"};

    /**
     * Executes the test command and returns a list of messages.
     *
     * @return A list of strings containing the messages generated by the command execution.
     */
    @Override
    public List<String> execute() {
        List<String> messages = new ArrayList<>();

        if (!isArgumentsValid()) {
            messages.add(ErrorMessages.LACK_ARGUMENTS_ERROR_MESSAGE);
            messages.add(COMMAND_GUIDE);
            return messages;
        }

        for (String key: COMMAND_MANDATORY_KEYWORDS) {
            messages.add(key + " " + arguments.get(key));
        }

        for (String key: COMMAND_EXTRA_KEYWORDS) {
            if (arguments.containsKey(key)) {
                messages.add(key + " " + arguments.get(key));
            }
        }

        return messages;
    }

    /**
     * Gets the mandatory keywords for the command.
     *
     * @return An array of strings containing the mandatory keywords associated with this command.
     */
    @Override
    protected String[] getMandatoryKeywords() {
        return COMMAND_MANDATORY_KEYWORDS;
    }

    /**
     * Gets the extra keywords for the command.
     *
     * @return An array of strings containing the extra keywords associated with this command.
     */
    @Override
    protected String[] getExtraKeywords() {
        return COMMAND_EXTRA_KEYWORDS;
    }

    /**
     * Gets the word for the command.
     *
     * @return A string representing the command word.
     */
    @Override
    protected String getCommandWord() {
        return COMMAND_WORD;
    }

    /**
     * Gets the guide for the command.
     *
     * @return A string representing the command guide.
     */
    @Override
    protected String getCommandGuide() {
        return COMMAND_GUIDE;
    }
}