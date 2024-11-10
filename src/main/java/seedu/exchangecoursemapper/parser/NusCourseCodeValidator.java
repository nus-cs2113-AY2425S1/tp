package seedu.exchangecoursemapper.parser;

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
            String substring = nusCourseCode.substring(2, 6);
            Integer.parseInt(substring);
            return nusCourseCode.length() == 6;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return false;
        }
    }
}
