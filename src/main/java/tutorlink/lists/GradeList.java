package tutorlink.lists;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import tutorlink.appstate.AppState;
import tutorlink.component.Component;
import tutorlink.exceptions.DuplicateGradeException;
import tutorlink.exceptions.DuplicateMatricNumberException;
import tutorlink.exceptions.GradeNotFoundException;
import tutorlink.exceptions.StudentNotFoundException;
import tutorlink.exceptions.TutorLinkException;
import tutorlink.grade.Grade;
import tutorlink.student.Student;

import static tutorlink.command.DeleteStudentCommand.STUDENT_NOT_FOUND;
import static tutorlink.lists.StudentList.ERROR_DUPLICATE_MATRIC_NUMBER_ON_ADD;

/**
 * Represents a list of grades.
 */
public class GradeList {
    private static final String ERROR_DUPLICATE_GRADE_ON_ADD = "Error! Grade (%s, %s) already exists in the list!";
    private static final String ERROR_NO_GRADE_FOUND = "Error! Grade (%s, %s) does not exist in the list!";
    private static final String ERROR_DUPLICATE_MATRIC_NUMBER =
            "Error! There is more than 1 student with the Matric Number, %s!";

    private ArrayList<Grade> gradeArrayList;

    public GradeList() {
        this.gradeArrayList = new ArrayList<>();
    }

    public boolean deleteGrade(String matricNumber, String componentDescription) {
        matricNumber = matricNumber.toUpperCase();
        componentDescription = componentDescription.toLowerCase();
        for (Grade grade : gradeArrayList) {
            if (grade.getStudent().getMatricNumber().equals(matricNumber)
                    && grade.getComponent().getName().equals(componentDescription)) {
                return gradeArrayList.remove(grade);
            }
        }
        return false;
    }

    public void addGrade(AppState appState, String matricNumber, String componentDescription, String scoreNumber)
            throws DuplicateGradeException {

        //Get Component component object using String componentDescription
        Component component = appState.components.findComponent(componentDescription);

        //Get Student student object using String matricNumber
        StudentList filteredList = appState.students.findStudentByMatricNumber(matricNumber);

        Student student;
        if (filteredList.size() == 1) {
            student = filteredList.getStudentArrayList().get(0);
        } else if (filteredList.size() == 0) {
            throw new StudentNotFoundException(String.format(STUDENT_NOT_FOUND, matricNumber));
        } else {
            String errorMessage = String.format(ERROR_DUPLICATE_MATRIC_NUMBER, matricNumber);
            throw new DuplicateMatricNumberException(errorMessage);
        }

        //Convert scoreNumber to double
        double score = Double.parseDouble(scoreNumber);

        //create a new grade object
        Grade grade = new Grade(component, student, score);

        for (Grade gradeToCompare : gradeArrayList) {
            if (grade.equals(gradeToCompare)) {
                throw new DuplicateGradeException(ERROR_DUPLICATE_GRADE_ON_ADD);
            }
        }
        gradeArrayList.add(grade);
    }

    @Override
    public String toString() {
        return IntStream.range(0, gradeArrayList.size())
                .mapToObj(i -> (i + 1) + ": " + gradeArrayList.get(i)) // 1-based index
                .collect(Collectors.joining("\n\t"));
    }

    public GradeList findGrade(String matricNumber, String componentDescription) throws TutorLinkException {
        GradeList filteredList = new GradeList();
        filteredList.gradeArrayList = gradeArrayList
                .stream()
                .filter(grade -> grade.getStudent().getMatricNumber().equals(matricNumber.toUpperCase())
                && grade.getComponent().getName().equals(componentDescription.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
        if (filteredList.gradeArrayList.isEmpty()) {
            throw new GradeNotFoundException(String.format(ERROR_NO_GRADE_FOUND, matricNumber, componentDescription));
        }
        return filteredList;
    }

}
