package seedu.exchangecoursemapper.command;

public class ListCommandsCommand extends Command {

    @Override
    public void execute(String userInput) {
        System.out.println("Here are the available commands:");
        System.out.println("filter <subject code> - Filter courses by subject code.");
        System.out.println("set <SCHOOL_NAME> - Set a partner university for course mapping.");
        System.out.println("list schools - List all available partner universities.");
        System.out.println("list mapped - List all mapped courses between NUS and partner universities.");
    }
}
