package seedu.exchangecoursemapper.courses;

import static seedu.exchangecoursemapper.constants.Assertions.THREE_COURSE_PARTS;

/** Represents a course mapping. */
public class Course {

    private String puCourseCode;
    private String nusCourseCode;
    private String partnerUniversity;

    /**
     * Course mapping constructor.
     *
     * @param puCourseCode Partner university course code to be mapped at the partner university.
     * @param nusCourseCode NUS course code of the course the user want to map overseas.
     * @param partnerUniversity Name of partner university.
     */
    public Course(String puCourseCode, String nusCourseCode, String partnerUniversity) {
        this.puCourseCode = puCourseCode;
        this.nusCourseCode = nusCourseCode;
        this.partnerUniversity = partnerUniversity;
    }

    /**
     * Returns the partner university course code.
     *
     * @return A string containing the partner university course code.
     */
    public String getPuCourseCode() {
        return puCourseCode;
    }

    /**
     * Returns the NUS module course code.
     *
     * @return A string containing the NUS module course code.
     */
    public String getNusCourseCode() {
        return nusCourseCode;
    }

    /**
     * Returns the name of partner university.
     *
     * @return A string containing the name of partner university.
     */
    public String getPartnerUniversity() {
        return partnerUniversity;
    }

    /**
     * Returns the formatted output of a course mapping for saving in the `myList.json` file.
     * Format: "nusCourseCode | partnerUniversity | puCourseCode"
     *
     * @return A string containing the formatted output of a course mapping.
     */
    public String formatOutput(){
        return getNusCourseCode() + " | " + getPartnerUniversity() + " | " + getPuCourseCode();
    }

    /**
     * Returns a Course object parsed from a formatted course mapping string.
     *
     * @param courseEntry A string containing the course mapping's formatted output.
     * @return a Course object with the relevant NUS course code, PU name and PU Course code.
     */
    public static Course parseCourseEntry(String courseEntry) {
        String[] parts = courseEntry.split(" \\| ");
        assert parts.length == 3 : THREE_COURSE_PARTS;
        return new Course(parts[2], parts[0], parts[1]);
    }

    /**
     * Returns whether the object parsed in is equals to this Course object.
     * Compares this Course object to the specified object. The result is true if and only if
     * the argument is not null, is a Course object, and has the same NUS course code, partner
     * university, and partner university course code as this Course.
     *
     * @param obj the object to compare this Course against
     * @return true if the given object represents a Course with the same values for
     *         NUS course code, partner university, and partner university course code;
     *         false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Course course = (Course) obj;
        return nusCourseCode.equals(course.nusCourseCode) &&
                partnerUniversity.equals(course.partnerUniversity) &&
                puCourseCode.equals(course.puCourseCode);
    }
}
