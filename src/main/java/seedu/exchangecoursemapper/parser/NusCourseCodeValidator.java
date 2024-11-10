package seedu.exchangecoursemapper.parser;

import static seedu.exchangecoursemapper.constants.Commands.NUS_COURSE_CODE_LENGTH;
import static seedu.exchangecoursemapper.constants.Commands.NUS_COURSE_CODE_NUMERALS_START_INDEX;

/**
 * NusCourseCodeValidator class methods are used to check the user's input for the filter command,
 * giving them guidance if they have used invalid course codes as filters.
 */
public class NusCourseCodeValidator {
    /**
     * Returns true if the NUS course code provided is a School of Computing (SoC) course, false otherwise.
     *
     * @param nusCourseCode a String containing the extracted information: NUS course code.
     * @return true if the NUS course code provided is an SoC offered course, false otherwise.
     */
    public static boolean isValidSocCourseCode(String nusCourseCode) {
        return nusCourseCode.startsWith("cs") | nusCourseCode.startsWith("ee") | nusCourseCode.startsWith("bt") |
                nusCourseCode.startsWith("is") | nusCourseCode.startsWith("cg");
    }

    /**
     * Returns true if the NUS course code provided follows the format of a NUS course code, false otherwise.
     *
     * @param nusCourseCode a String containing the extracted information: NUS course code.
     * @return true if the NUS course code provided follows the format of a NUS course code, false otherwise.
     */
    public static boolean isValidNusCourseCodeFormat(String nusCourseCode) {
        try {
            String substring = nusCourseCode.substring(NUS_COURSE_CODE_NUMERALS_START_INDEX, NUS_COURSE_CODE_LENGTH);
            Integer.parseInt(substring);
            return nusCourseCode.length() == NUS_COURSE_CODE_LENGTH;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return false;
        }
    }
}
