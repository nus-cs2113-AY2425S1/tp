package seedu.exchangecoursemapper.courses;

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
}
