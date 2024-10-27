package seedu.exchangecoursemapper.storage;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.exchangecoursemapper.courses.Course;

public class Storage {
    public static final String MYLIST_FILE_PATH = "./data/myList.json";
    private static final Logger logger = Logger.getLogger(Storage.class.getName());
    private final String filePath;


    public Storage() {
        this(MYLIST_FILE_PATH);
    }

    public Storage(String filePath) {
        this.filePath = filePath;
        initializeMyList();
    }

    private void initializeMyList() {
        Path path = Paths.get(filePath);
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
                logger.log(Level.INFO, "Initialized myList.json at {0}", filePath);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to initialize myList.json", e);
        }
    }

    public void addCourse(Course course) {
        assert course != null : "Course cannot be null";
        String courseEntry = formatCourseEntry(course);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(courseEntry);
            writer.newLine();
            logger.log(Level.INFO, "Successfully added course to myList.json: {0}", courseEntry);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to add course to myList.json", e);
        }
    }

    public void deleteCourse(int index) {
        List<String> allCourses = loadAllCourses();
        if (index < 0 || index >= allCourses.size()) {
            logger.log(Level.WARNING, "Invalid index for deletion: {0}", index);
            return;
        }

        allCourses.remove(index);
        logger.log(Level.INFO, "Deleted course at index {0} from myList.json", index);
        saveAllCourses(allCourses);
    }

    public Course getCourse(int index) {
        List<String> allCourses = loadAllCourses();
        if (index < 0 || index >= allCourses.size()) {
            throw new IndexOutOfBoundsException("Course index out of bounds");
        }

        String courseLine = allCourses.get(index);
        logger.log(Level.INFO, "Retrieved course at index {0}: {1}", new Object[]{index, courseLine});

        String[] parts = courseLine.split(" \\| ");
        assert parts.length == 3 : "Course entry must contain exactly 3 parts";
        return new Course(parts[2], parts[0], parts[1]);
    }

    public List<String> loadAllCourses() {
        List<String> courses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                courses.add(line);
            }
            logger.log(Level.INFO, "Loaded all courses from myList.json, total courses: {0}", courses.size());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to load courses from myList.json", e);
        }
        return courses;
    }

    private void saveAllCourses(List<String> courses) {
        assert courses != null : "Course list to save should not be null";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String course : courses) {
                writer.write(course);
                writer.newLine();
            }
            logger.log(Level.INFO, "Saved all courses to myList.json, total courses: {0}", courses.size());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to save courses to myList.json", e);
        }
    }

    private String formatCourseEntry(Course course) {
        assert course != null : "Course cannot be null";
        String formattedEntry = course.getNusCourseCode() + " | "
                + course.getPartnerUniversity() + " | "
                + course.getPuCourseCode();
        logger.log(Level.INFO, "Formatted course entry: {0}", formattedEntry);
        return formattedEntry;
    }
}