package tutorlink.storage;

import tutorlink.component.Component;
import tutorlink.exceptions.InvalidDataFileLineException;
import tutorlink.grade.Grade;
import tutorlink.student.Student;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage manager for grades, handling loading and saving of grade data to and from a file.
 * Utilizes a list of components and students to validate grade entries.
 */
public class GradeStorage extends Storage {
    private final ArrayList<Component> componentList;
    private final ArrayList<Student> studentList;

    //@@author RCPilot1604
    /**
     * Constructs a {@code GradeStorage} object with the specified file path, component list, and student list.
     *
     * @param filePath      The file path to store grades.
     * @param componentList The list of components for validation.
     * @param studentList   The list of students for validation.
     */
    //@@author jinzihan2002
    public GradeStorage(String filePath, ArrayList<Component> componentList, ArrayList<Student> studentList) {
        super(filePath);
        this.componentList = componentList;
        this.studentList = studentList;
    }

    //@@author RCPilot1604
    /**
     * Loads the grade list from the file.
     *
     * @return An {@code ArrayList} of {@code Grade} objects loaded from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    //@@author jinzihan2002
    public ArrayList<Grade> loadGradeList() throws IOException {
        ArrayList<Grade> grades = new ArrayList<>();
        Scanner fileScanner = new Scanner(path);
        while (fileScanner.hasNext()) {
            try {
                Grade newGrade = getGradeFromFileLine(fileScanner.nextLine(), grades);
                grades.add(newGrade);
            } catch (InvalidDataFileLineException e) {
                discardedEntries.add(e.getMessage());
            }
        }
        return grades;
    }

    //@@author RCPilot1604
    /**
     * Saves the provided list of grades to the file.
     *
     * @param grades The {@code ArrayList} of grades to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    //@@author jinzihan2002
    public void saveGradeList(ArrayList<Grade> grades) throws IOException {
        FileWriter fileWriter = new FileWriter(path.toFile());
        for (Grade grade : grades) {
            fileWriter.write(getFileInputForGrade(grade) + System.lineSeparator());
        }
        fileWriter.close();
    }

    //@@author RCPilot1604
    /**
     * Parses a line from the grade file and returns a {@code Grade} object if the data is valid.
     *
     * @param fileLine A line from the file representing a grade entry.
     * @param grades   The current list of grades to check for duplicates.
     * @return A {@code Grade} object created from the parsed data.
     * @throws InvalidDataFileLineException If the line data is invalid or contains duplicates.
     */
    //@@author jinzihan2002
    private Grade getGradeFromFileLine(String fileLine, ArrayList<Grade> grades)
            throws InvalidDataFileLineException {
        String componentName;
        String matricNumber;
        double score;
        String[] stringParts = fileLine.split(READ_DELIMITER);

        try {
            componentName = stringParts[0].strip();
            matricNumber = stringParts[1].strip();
            score = Double.parseDouble(stringParts[2].strip());
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidDataFileLineException(fileLine);
        }

        Component selectedComp = null;
        for (Component comp : componentList) {
            if (comp.getName().equals(componentName)) {
                selectedComp = comp;
                break;
            }
        }

        Student selectedStudent = null;
        for (Student student : studentList) {
            if (student.getMatricNumber().equals(matricNumber)) {
                selectedStudent = student;
                break;
            }
        }

        if (selectedComp == null || selectedStudent == null) {
            throw new InvalidDataFileLineException(fileLine);
        }

        boolean isValidScore = (score >= 0 && score <= selectedComp.getMaxScore());
        Grade newGrade = new Grade(selectedComp, selectedStudent, score);
        if (!isValidScore || grades.contains(newGrade)) {
            throw new InvalidDataFileLineException(fileLine);
        }
        return newGrade;
    }

    //@@author RCPilot1604
    /**
     * Formats a {@code Grade} object for storage in a file.
     *
     * @param grade The grade to format.
     * @return A string representing the grade in file format.
     */
    //@@author jinzihan2002
    private String getFileInputForGrade(Grade grade) {
        String componentName = grade.getComponent().getName();
        String matricNumber = grade.getStudent().getMatricNumber();
        double score = grade.getScore();
        return componentName + WRITE_DELIMITER + matricNumber + WRITE_DELIMITER + score;
    }

}
