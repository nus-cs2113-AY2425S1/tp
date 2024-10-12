package seedu.exchangecoursemapper.command;

import static seedu.exchangecoursemapper.constants.Messages.LINE_SEPARATOR;

public class ListCommandsCommand extends Command {

    @Override
    public void execute(String userInput) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Here are the available commands:");
        System.out.println("filter <subject code> - Filter courses by subject code.");
        System.out.println("set <SCHOOL_NAME> - Set a partner university for course mapping.");
        System.out.println("list schools - List all available partner universities.");
        System.out.println("add <NUS_COURSE_CODE> /pu <NAME_OF_PU> /coursepu <PU_COURSE_CODE> " +
                "- Add mapped courses between NUS and partner universities.");
        System.out.println(LINE_SEPARATOR);;
    }
}
