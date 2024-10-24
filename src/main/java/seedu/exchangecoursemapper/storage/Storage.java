package seedu.exchangecoursemapper.storage;

import java.util.ArrayList;
import seedu.exchangecoursemapper.courses.Course;

public class Storage {
    // For our personal tracker
    private ArrayList<Course> savedCourses = new ArrayList<>();


    /**
     * Returns the Course stored at the specified index in the saved courses list.
     *
     * @param listIndex Index of the Course to get from the saved courses list.
     * @return The course stored at listIndex.
     */
    public Course getCourse(int listIndex) {
        return savedCourses.get(listIndex);
    }

    public void addCourse(Course course) {
        savedCourses.add(course);
    }

    public void deleteCourse(int listIndex) {
        savedCourses.remove(listIndex);
    }
}
