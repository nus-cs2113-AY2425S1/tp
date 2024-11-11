package seedu.exchangecoursemapper.courses;

import static seedu.exchangecoursemapper.constants.Assertions.THREE_COURSE_PARTS;

public class Course {

    private String puCourseCode;
    private String nusCourseCode;
    private String partnerUniversity;

    public Course(String puCourseCode, String nusCourseCode, String partnerUniversity) {
        this.puCourseCode = puCourseCode;
        this.nusCourseCode = nusCourseCode;
        this.partnerUniversity = partnerUniversity;
    }

    public String getPuCourseCode() {
        return puCourseCode;
    }

    public String getNusCourseCode() {
        return nusCourseCode;
    }

    public String getPartnerUniversity() {
        return partnerUniversity;
    }

    public String formatOutput(){
        return getNusCourseCode() + " | " + getPartnerUniversity() + " | " + getPuCourseCode();
    }

    public static Course parseCourseEntry(String courseEntry) {
        String[] parts = courseEntry.split(" \\| ");
        assert parts.length == 3 : THREE_COURSE_PARTS;
        return new Course(parts[2], parts[0], parts[1]);
    }

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
