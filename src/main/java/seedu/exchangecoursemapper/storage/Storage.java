package seedu.exchangecoursemapper.storage;

import java.util.ArrayList;

public class Storage {
    // For our personal tracker
    private ArrayList<String> savedCourses = new ArrayList<>();

    public void addCourse(String course) {
        savedCourses.add(course);
    }

}
