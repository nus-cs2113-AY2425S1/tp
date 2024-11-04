package seedu.exchangecoursemapper.storage;

import seedu.exchangecoursemapper.courses.Course;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.exchangecoursemapper.constants.Assertions.NO_COURSE_INFORMATION;
import static seedu.exchangecoursemapper.constants.Assertions.THREE_COURSE_PARTS;
import static seedu.exchangecoursemapper.constants.Assertions.FORMATTED_ENTRY;

public class CourseFormatter {

    private static final Logger logger = Logger.getLogger(CourseFormatter.class.getName());

    public static String formatCourseEntry(Course course) {
        assert course != null : NO_COURSE_INFORMATION;
        String formattedEntry = course.getNusCourseCode() + " | "
                + course.getPartnerUniversity() + " | "
                + course.getPuCourseCode();
        logger.log(Level.INFO, FORMATTED_ENTRY, formattedEntry);
        return formattedEntry;
    }

    public static Course parseCourseEntry(String courseEntry) {
        String[] parts = courseEntry.split(" \\| ");
        assert parts.length == 3 : THREE_COURSE_PARTS;
        return new Course(parts[2], parts[0], parts[1]);
    }
}
