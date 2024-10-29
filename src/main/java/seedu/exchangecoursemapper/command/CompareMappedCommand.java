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

        List<String> uni1Modules = filterModulesByUniversity(allModules, university1);
        List<String> uni2Modules = filterModulesByUniversity(allModules, university2);

        Set<String> uni1CourseCodes = extractCourseCodes(uni1Modules);
        Set<String> uni2CourseCodes = extractCourseCodes(uni2Modules);

        Set<String> commonCourseCodes = getCommonCourseCodes(uni1CourseCodes, uni2CourseCodes);
        Set<String> uni1UniqueCourseCodes = getUniqueCourseCodes(uni1CourseCodes, commonCourseCodes);
        Set<String> uni2UniqueCourseCodes = getUniqueCourseCodes(uni2CourseCodes, commonCourseCodes);

        displayComparisonResults(university1, university2, commonCourseCodes, uni1Modules, uni2Modules,
                uni1UniqueCourseCodes, uni2UniqueCourseCodes);
    }

    private List<String> filterModulesByUniversity(List<String> modules, String university) {
        return modules.stream()
                .filter(module -> module.contains(" | " + university + " | "))
                .toList();
    }

    private Set<String> extractCourseCodes(List<String> modules) {
        return modules.stream()
                .map(module -> module.split(" \\| ")[0])  // Extract NUS course code
                .collect(Collectors.toSet());
    }

    private Set<String> getCommonCourseCodes(Set<String> codes1, Set<String> codes2) {
        Set<String> commonCodes = new HashSet<>(codes1);
        commonCodes.retainAll(codes2);
        return commonCodes;
    }

    private Set<String> getUniqueCourseCodes(Set<String> allCodes, Set<String> commonCodes) {
        Set<String> uniqueCodes = new HashSet<>(allCodes);
        uniqueCodes.removeAll(commonCodes);
        return uniqueCodes;
    }

    private void displayComparisonResults(String university1, String university2, Set<String> commonCourseCodes,
                                          List<String> uni1Modules, List<String> uni2Modules,
                                          Set<String> uni1UniqueCourseCodes, Set<String> uni2UniqueCourseCodes) {

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

    private void printUniqueMappings(String university, List<String> modules, Set<String> uniqueCourseCodes) {
        System.out.println("\nUnique to " + university + ":");
        System.out.println(LINE_SEPARATOR);
        if (uniqueCourseCodes.isEmpty()) {
            System.out.println("No unique mappings for " + university);
        } else {
            for (String courseCode : uniqueCourseCodes) {
                modules.stream()
                        .filter(module -> module.startsWith(courseCode + " | "))
                        .forEach(System.out::println);
            }
        }
        System.out.println(LINE_SEPARATOR);
    }
}
