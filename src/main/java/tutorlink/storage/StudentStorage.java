package tutorlink.storage;

import tutorlink.commons.Commons;
import tutorlink.exceptions.InvalidDataFileLineException;
import tutorlink.student.Student;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The StudentStorage class extends the Storage class and provides methods to load and save a list of students
 * from and to a file.
 */
public class StudentStorage extends Storage {
    public StudentStorage(String filePath) {
        super(filePath);
    }

    public ArrayList<Student> loadStudentList() throws IOException {
        ArrayList<Student> students = new ArrayList<>();
        Scanner fileScanner = new Scanner(path);
        while (fileScanner.hasNext()) {
            try {
                Student newStudent = getStudentFromFileLine(fileScanner.nextLine(), students);
                students.add(newStudent);
            } catch (InvalidDataFileLineException e) {
                discardedEntries.add(e.getMessage());
            }
        }
        return students;
    }

    public void saveStudentList(ArrayList<Student> students) throws IOException {
        FileWriter fileWriter = new FileWriter(path.toFile());
        for (Student student : students) {
            fileWriter.write(getFileInputForStudent(student) + System.lineSeparator());
        }
        fileWriter.close();
    }

    private Student getStudentFromFileLine(String fileLine, ArrayList<Student> students)
            throws InvalidDataFileLineException {
        String[] stringParts = fileLine.split(READ_DELIMITER);
        try {
            String matricNumber = stringParts[0].strip().toUpperCase();
            String name = stringParts[1].strip();
            Pattern pattern = Pattern.compile(Commons.MATRIC_NUMBER_REGEX);
            Matcher matcher = pattern.matcher(matricNumber);
            if (!matcher.find()) {
                throw new InvalidDataFileLineException(fileLine);
            }

            Student newStudent = new Student(matricNumber, name);
            if (students.contains(newStudent)) {
                throw new InvalidDataFileLineException(fileLine);
            }
            return newStudent;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidDataFileLineException(fileLine);
        }
    }

    private String getFileInputForStudent(Student student) {
        return student.getMatricNumber() + WRITE_DELIMITER + student.getName();
    }

}
