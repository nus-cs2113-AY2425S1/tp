package tutorlink.storage;

import tutorlink.exceptions.InvalidDataFileLineException;
import tutorlink.student.Student;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
            String matricNumber = stringParts[0];
            String name = stringParts[1];
            String gpaString = stringParts[2];
            double gpa = Double.parseDouble(gpaString);
            Student newStudent = new Student(matricNumber, name, gpa);
            if (students.contains(newStudent)) {
                throw new InvalidDataFileLineException(fileLine);
            }
            return newStudent;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidDataFileLineException(fileLine);
        }
    }

    private String getFileInputForStudent(Student student) {
        return student.getMatricNumber() + WRITE_DELIMITER + student.getName() + WRITE_DELIMITER +
                student.getPercentageScore();
    }

}