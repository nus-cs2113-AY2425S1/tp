package seedu.exchangecoursemapper.storage;

import seedu.exchangecoursemapper.courses.Course;
import seedu.exchangecoursemapper.ui.UI;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.exchangecoursemapper.constants.Logs.COURSE_SIZE;
import static seedu.exchangecoursemapper.constants.Logs.COURSE_ENTRY;
import static seedu.exchangecoursemapper.constants.Logs.INVALID_DELETE_INDEX;
import static seedu.exchangecoursemapper.constants.Logs.DELETE_INDEX;
import static seedu.exchangecoursemapper.constants.Logs.SAVE_LINE_SIZE;
import static seedu.exchangecoursemapper.constants.Messages.INDEX_OUT_OF_BOUNDS;

/**
 * The CourseRepository class contains all the methods that
 * handles the course mappings found in the `myList.json` file.
 */
public class CourseRepository {


    public static final String MYLIST_FILE_PATH = "./data/myList.json";
    private static final UI ui = new UI();
    private static final Logger logger = Logger.getLogger(CourseRepository.class.getName());

    static {
        logger.setLevel(Level.SEVERE);
    }

    private final FileHandler fileHandler;
    private final DataIntegrityChecker dataIntegrityChecker;

    /** CourseRepository constructor.*/
    public CourseRepository() {
        this.fileHandler = new FileHandler(MYLIST_FILE_PATH);
        this.dataIntegrityChecker = new DataIntegrityChecker();
    }

    /**
     * CourseRepository constructor with file path.
     *
     * @param filePath of where the `myList.json` file is located.
     */
    public CourseRepository(String filePath) {
        this.fileHandler = new FileHandler(filePath);
        this.dataIntegrityChecker = new DataIntegrityChecker();
    }

    /**
     * Returns true if the `myList.json` file is valid, otherwise false.
     * The file is valid if all the course mappings found in the `myList.json` file are correct according to
     * the approved course mapping database and the file is not corrupted.
     *
     * @return true if the file is valid, otherwise false.
     */
    public boolean isFileValid() {
        List<String> lines = fileHandler.readAllLines();
        return dataIntegrityChecker.validateFileIntegrity(lines);
    }

    public void removeDuplicateEntries() {
        dataIntegrityChecker.removeDuplicateCourses(loadAllCourses(), new Storage());
    }

    /**
     * Returns a list of all the user's stored course mappings.
     *
     * @return a list with all the user's course mappings.
     */
    public List<Course> loadAllCourses() {
        List<Course> courses = new ArrayList<>();
        List<String> lines = fileHandler.readAllLines();
        for (String line : lines) {
            courses.add(Course.parseCourseEntry(line));
        }

        logger.log(Level.INFO, COURSE_SIZE, courses.size());
        return courses;
    }

    /**
     * Adds a new course to `myList.json` for storage.
     *
     * @param course to be added for storage, represented by a Course object.
     */
    public void addCourse(Course course) {
        // Load all existing courses
        List<Course> existingCourses = loadAllCourses();

        // Check if the course already exists
        for (Course existingCourse : existingCourses) {
            if (existingCourse.equals(course)) {
                ui.printDuplicateCourseNotAdded();
                return; // Exit the method if a duplicate is found
            }
        }

        // If no duplicate is found, add the new course
        String courseEntry = course.formatOutput();
        fileHandler.appendLine(courseEntry);
        ui.printAddMessage(courseEntry);
        logger.log(Level.INFO, COURSE_ENTRY, courseEntry);
    }

    /**
     * Deletes a course mapping from `myList.json` file by indicating its index.
     *
     * @param index of the course mapping to be deleted.
     */
    public void deleteCourse(int index) {
        List<String> allCourses = fileHandler.readAllLines();
        if (index < 0 || index >= allCourses.size()) {
            logger.log(Level.WARNING, INVALID_DELETE_INDEX, index);
            return;
        }
        allCourses.remove(index);
        fileHandler.writeAllLines(allCourses);
        logger.log(Level.INFO, DELETE_INDEX, index);
    }

    /**
     * Updates and save all courses in a Course List, and subsequently in the `myList.json` file.
     *
     * @param courses a List containing all the course mappings, as represented in a Course object.
     */
    public void saveCourses(List<Course> courses) {
        List<String> courseLines = new ArrayList<>();
        for (Course course : courses) {
            courseLines.add(course.formatOutput());
        }
        fileHandler.writeAllLines(courseLines);
        logger.log(Level.INFO, SAVE_LINE_SIZE, courses.size());
    }

    /**
     * Returns a course from the storage by indicating its index.
     *
     * @param index of course that the user refers to.
     * @return the course specified by the index as a Course object.
     */
    public Course getCourse(int index) {
        List<String> allCourses = fileHandler.readAllLines();
        if (index < 0 || index >= allCourses.size()) {
            throw new IndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS);
        }
        String courseLine = allCourses.get(index);
        return Course.parseCourseEntry(courseLine);
    }

}


