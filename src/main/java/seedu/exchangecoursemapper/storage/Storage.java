package seedu.exchangecoursemapper.storage;

import seedu.exchangecoursemapper.courses.Course;
import java.util.List;

/**
 * The Storage class contains all the methods related to storing course mappings.
 */
public class Storage {

    private final CourseRepository courseRepository;

    /** Storage constructor without filepath*/
    public Storage() {
        this.courseRepository = new CourseRepository();
    }

    /**
     * Storage constructor with filepath.
     *
     * @param filePath of which the `myList.json` file is located.
     */
    public Storage(String filePath) {
        this.courseRepository = new CourseRepository(filePath);
    }

    /**
     * Adds a course into `myList.json` file.
     *
     * @param course a course object to be added into storage.
     */
    public void addCourse(Course course) {
        courseRepository.addCourse(course);
    }

    /**
     * Deletes a course from `myList.json` file.
     *
     * @param index of the course to be deleted from `myList.json` file.
     */
    public void deleteCourse(int index) {
        courseRepository.deleteCourse(index);
    }

    /**
     * Updates and save all courses in a Course List, and subsequently in the `myList.json` file.
     *
     * @param courses a List containing all the course mappings, as represented in a Course object.
     */
    public void saveCourses(List <Course> courses) {
        courseRepository.saveCourses(courses);
    }

    /**
     * Gets a course from the storage by indicating its index.
     *
     * @param index of course that the user refers to.
     * @return the course specified by the index as a Course object.
     */
    public Course getCourse(int index) {
        return courseRepository.getCourse(index);
    }

    /**
     * Loads all the user's stored course mappings.
     *
     * @return a list with all the user's course mappings.
     */
    public List<Course> loadAllCourses() {
        return courseRepository.loadAllCourses();
    }
}
