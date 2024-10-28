package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.storage.Storage;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashSet;

import static seedu.exchangecoursemapper.constants.Messages.LINE_SEPARATOR;

public class CompareMappedCommand extends CheckInformationCommand {

    private final Storage storage;

    public CompareMappedCommand(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void execute(String userInput) {
        String[] inputs = userInput.split("pu/");
        if (inputs.length < 3) {
            System.out.println("Please provide two universities in the format: " +
                    "compare pu/<University 1> pu/<University 2>");
            return;
        }

        String university1 = inputs[1].trim();
        String university2 = inputs[2].trim();

        List<String> allModules = storage.loadAllCourses();

        // Filter mappings for each university
        List<String> uni1Modules = allModules.stream()
                .filter(module -> module.contains(" | " + university1 + " | "))
                .toList();

        List<String> uni2Modules = allModules.stream()
                .filter(module -> module.contains(" | " + university2 + " | "))
                .toList();

        // Extract course codes for comparison
        Set<String> uni1CourseCodes = uni1Modules.stream()
                .map(module -> module.split(" \\| ")[0])  // Extract NUS course code
                .collect(Collectors.toSet());

        Set<String> uni2CourseCodes = uni2Modules.stream()
                .map(module -> module.split(" \\| ")[0])  // Extract NUS course code
                .collect(Collectors.toSet());

        // Identify common and unique course codes
        Set<String> commonCourseCodes = new HashSet<>(uni1CourseCodes);
        commonCourseCodes.retainAll(uni2CourseCodes);

        Set<String> uni1UniqueCourseCodes = new HashSet<>(uni1CourseCodes);
        uni1UniqueCourseCodes.removeAll(commonCourseCodes);

        Set<String> uni2UniqueCourseCodes = new HashSet<>(uni2CourseCodes);
        uni2UniqueCourseCodes.removeAll(commonCourseCodes);

        // Display results
        System.out.println("Comparison of Course Mappings between " + university1 + " and " + university2 + ":");

        System.out.println("\nCommon Mappings:");
        System.out.println(LINE_SEPARATOR);
        if (commonCourseCodes.isEmpty()) {
            System.out.println("No common mappings found.");
        } else {
            for (String courseCode : commonCourseCodes) {
                uni1Modules.stream()
                        .filter(module -> module.startsWith(courseCode + " | "))
                        .forEach(System.out::println);

                uni2Modules.stream()
                        .filter(module -> module.startsWith(courseCode + " | "))
                        .forEach(System.out::println);
            }
        }
        System.out.println(LINE_SEPARATOR);

        printUniqueMappings(university1, uni1Modules, uni1UniqueCourseCodes);

        printUniqueMappings(university2, uni2Modules, uni2UniqueCourseCodes);
    }

    private void printUniqueMappings(String university1, List<String> uni1Modules, Set<String> uni1UniqueCourseCodes) {
        System.out.println("\nUnique to " + university1 + ":");
        System.out.println(LINE_SEPARATOR);
        if (uni1UniqueCourseCodes.isEmpty()) {
            System.out.println("No unique mappings for " + university1);
        } else {
            for (String courseCode : uni1UniqueCourseCodes) {
                uni1Modules.stream()
                        .filter(module -> module.startsWith(courseCode + " | "))
                        .forEach(System.out::println);
            }
        }
        System.out.println(LINE_SEPARATOR);
    }
}
