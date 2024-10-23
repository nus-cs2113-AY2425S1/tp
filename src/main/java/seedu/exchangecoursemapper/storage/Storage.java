package seedu.exchangecoursemapper.storage;

import java.util.ArrayList;
import seedu.exchangecoursemapper.courses.Course;

public class Storage {
    // For our personal tracker
    private ArrayList<Course> savedCourses = new ArrayList<>();

    public void addCourse(Course course) {
        savedCourses.add(course);
    }

}
