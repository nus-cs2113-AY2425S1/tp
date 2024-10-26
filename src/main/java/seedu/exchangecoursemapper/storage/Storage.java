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

import seedu.exchangecoursemapper.courses.Course;

public class Storage {
    public static final String MYLIST_FILE_PATH = "./data/myList.json";

    public Storage() {
        initializeMyList();
    }

    private void initializeMyList() {
        Path path = Paths.get(MYLIST_FILE_PATH);
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
        } catch (IOException e) {
            System.err.println("Failed to initialize myList.json");
        }
    }

    public void addCourse(Course course) {
        String courseEntry = formatCourseEntry(course);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MYLIST_FILE_PATH, true))) {
            writer.write(courseEntry);
            writer.newLine(); // Add newline for each course
        } catch (IOException e) {
            System.err.println("Failed to add course to myList.json");
        }
    }

    public void deleteCourse(int index) {
        List<String> allCourses = loadAllCourses();
        if (index < 0 || index >= allCourses.size()) {
            System.err.println("Invalid index: " + index);
            return;
        }

        allCourses.remove(index); // Remove the specified course
        saveAllCourses(allCourses); // Rewrite the file without the removed course
    }

    public Course getCourse(int index) {
        List<String> allCourses = loadAllCourses();
        if (index < 0 || index >= allCourses.size()) {
            throw new IndexOutOfBoundsException("Course index out of bounds");
        }

        String[] parts = allCourses.get(index).split(" \\| ");
        return new Course(parts[2], parts[0], parts[1]);
    }

    private List<String> loadAllCourses() {
        List<String> courses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(MYLIST_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                courses.add(line);
            }
        } catch (IOException e) {
            System.err.println("Failed to load courses from myList.json");
        }
        return courses;
    }

    private void saveAllCourses(List<String> courses) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MYLIST_FILE_PATH))) {
            for (String course : courses) {
                writer.write(course);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Failed to save courses to myList.json");
        }
    }

    private String formatCourseEntry(Course course) {
        return course.getNusCourseCode() + " | " + course.getPartnerUniversity() + " | " + course.getPuCourseCode();
    }
}

