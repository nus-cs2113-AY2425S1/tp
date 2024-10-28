package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.storage.Storage;

import java.util.List;

import static seedu.exchangecoursemapper.constants.Messages.LINE_SEPARATOR;

public class ListPersonalTrackerCommand extends CheckInformationCommand {

    private final Storage storage;

    public ListPersonalTrackerCommand(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void execute(String userInput) {
        List<String> mappedModules = storage.loadAllCourses();

        if (mappedModules.isEmpty()) {
            System.out.println("No modules mapped yet.");
        } else {
            System.out.println("Mapped Modules:");
            System.out.println(LINE_SEPARATOR);
            for (String module : mappedModules) {
                System.out.println(module);
            }
            System.out.println(LINE_SEPARATOR);
        }
    }
}

