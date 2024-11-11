package seedu.exchangecoursemapper.storage;

import seedu.exchangecoursemapper.courses.Course;

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

public class CourseRepository {

    public static final String MYLIST_FILE_PATH = "./data/myList.json";
    private static final Logger logger = Logger.getLogger(CourseRepository.class.getName());

    static {
        logger.setLevel(Level.SEVERE);
    }

    private final FileHandler fileHandler;
    private final DataIntegrityChecker dataIntegrityChecker;

    public CourseRepository() {
        this.fileHandler = new FileHandler(MYLIST_FILE_PATH);
        this.dataIntegrityChecker = new DataIntegrityChecker();
    }

    public CourseRepository(String filePath) {
        this.fileHandler = new FileHandler(filePath);
        this.dataIntegrityChecker = new DataIntegrityChecker();
    }

    public boolean isFileValid() {
        List<String> lines = fileHandler.readAllLines();
        return dataIntegrityChecker.validateFileIntegrity(lines);
    }

    public boolean hasDuplicateEntries() {
        return dataIntegrityChecker.checkForDuplicateCourses(loadAllCourses(), new Storage());
    }

    public List<Course> loadAllCourses() {
        List<Course> courses = new ArrayList<>();
        List<String> lines = fileHandler.readAllLines();
        for (String line : lines) {
            courses.add(Course.parseCourseEntry(line));
        }

        logger.log(Level.INFO, COURSE_SIZE, courses.size());
        return courses;
    }

    public void addCourse(Course course) {
        String courseEntry = course.formatOutput();
        fileHandler.appendLine(courseEntry);
        logger.log(Level.INFO, COURSE_ENTRY, courseEntry);
    }

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

    public void saveCourses(List<Course> courses) {
        List<String> courseLines = new ArrayList<>();
        for (Course course : courses) {
            courseLines.add(course.formatOutput());
        }
        fileHandler.writeAllLines(courseLines);
        logger.log(Level.INFO, SAVE_LINE_SIZE, courses.size());
    }

    public Course getCourse(int index) {
        List<String> allCourses = fileHandler.readAllLines();
        if (index < 0 || index >= allCourses.size()) {
            throw new IndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS);
        }
        String courseLine = allCourses.get(index);
        return Course.parseCourseEntry(courseLine);
    }

}


