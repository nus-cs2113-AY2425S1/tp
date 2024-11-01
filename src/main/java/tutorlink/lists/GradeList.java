package tutorlink.lists;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


import tutorlink.component.Component;
import tutorlink.exceptions.DuplicateGradeException;
import tutorlink.exceptions.GradeNotFoundException;
import tutorlink.exceptions.StudentNotFoundException;
import tutorlink.exceptions.TutorLinkException;
import tutorlink.exceptions.IncompleteGradesException;
import tutorlink.grade.Grade;

/**
 * Represents a list of grades.
 */
public class GradeList {
    private static final String ERROR_DUPLICATE_GRADE_ON_ADD =
            "Error! Grade for component %s for student %s already exists in the list!";
    private static final String ERROR_NO_GRADE_FOUND =
            "Error! Grade for component %s for student %s does not exist in the list!";


    private ArrayList<Grade> gradeArrayList;

    public GradeList() {
        this.gradeArrayList = new ArrayList<>();
    }

    public GradeList(ArrayList<Grade> gradeArrayList) {
        this.gradeArrayList = gradeArrayList;
    }

    //@@author RCPilot1604
    public void deleteGrade(String matricNumber, String componentDescription) throws GradeNotFoundException {
        matricNumber = matricNumber.toUpperCase();
        componentDescription = componentDescription.toUpperCase();
        for (Grade grade : gradeArrayList) {
            if (grade.getStudent().getMatricNumber().equals(matricNumber)
                    && grade.getComponent().getName().toUpperCase().equals(componentDescription)) {
                gradeArrayList.remove(grade);
                return;
            }
        }
        throw new GradeNotFoundException(String.format(ERROR_NO_GRADE_FOUND, componentDescription, matricNumber));
    }

    public void deleteGradesByMatric(String matricNumber) {
        matricNumber = matricNumber.toUpperCase();
        ArrayList<Grade> gradesToDelete = new ArrayList<>();
        for (Grade grade : gradeArrayList) {
            if (grade.getStudent().getMatricNumber().equals(matricNumber)) {
                gradesToDelete.add(grade);
            }
        }
        gradeArrayList.removeAll(gradesToDelete);
    }

    public void deleteGradesByComponent(String componentDescription) {
        componentDescription = componentDescription.toUpperCase();
        ArrayList<Grade> gradesToDelete = new ArrayList<>();
        for (Grade grade : gradeArrayList) {
            if (grade.getComponent().getName().toUpperCase().equals(componentDescription.toUpperCase())) {
                gradesToDelete.add(grade);
            }
        }
        gradeArrayList.removeAll(gradesToDelete);
    }

    public void addGrade(Grade grade) {
        for (Grade gradeToCompare : gradeArrayList) {
            if (grade.equals(gradeToCompare)) {
                throw new DuplicateGradeException(
                        String.format(ERROR_DUPLICATE_GRADE_ON_ADD,
                                grade.getComponentName(), grade.getStudentMatricNumber()));
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
                        && grade.getComponent().getName().toUpperCase().equals(componentDescription.toUpperCase()))
                .collect(Collectors.toCollection(ArrayList::new));
        if (filteredList.gradeArrayList.isEmpty()) {
            throw new GradeNotFoundException(String.format(ERROR_NO_GRADE_FOUND, componentDescription, matricNumber));
        }
        return filteredList;
    }
    //@@author

    //@@author TrungBui32
    public double calculateStudentGPA(String matricNumber, ComponentList componentList)
            throws IncompleteGradesException {
        ArrayList<Grade> studentGrades = gradeArrayList
                .stream()
                .filter(grade -> grade.getStudent().getMatricNumber().equals(matricNumber.toUpperCase()))
                .collect(Collectors.toCollection(ArrayList::new));

        ArrayList<Component> allComponents = componentList.findAllComponents();

        if (studentGrades.isEmpty()) {
            throw new StudentNotFoundException("No grades found for student: " + matricNumber);
        }

        boolean hasAllComponents = allComponents.stream()
                .allMatch(component -> studentGrades.stream()
                        .anyMatch(grade -> grade.getComponent().equals(component)));

        if (!hasAllComponents) {
            throw new IncompleteGradesException(
                    "Cannot calculate GPA: Student " + matricNumber + " is missing grades for some components");
        }

        return studentGrades
                .stream()
                .mapToDouble(grade -> grade.getScore() * grade.getComponent().getWeight())
                .sum();
    }
    //@@author

    public ArrayList<Grade> getGradeArrayList() {
        return gradeArrayList;
    }

}