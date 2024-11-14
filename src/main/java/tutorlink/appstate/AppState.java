package tutorlink.appstate;

import tutorlink.component.Component;
import tutorlink.grade.Grade;
import tutorlink.lists.ComponentList;
import tutorlink.lists.GradeList;
import tutorlink.lists.StudentList;
import tutorlink.student.Student;

import java.util.ArrayList;

public class AppState {
    public StudentList students;
    public GradeList grades;
    public ComponentList components;

    public AppState() {
        students = new StudentList();
        grades = new GradeList();
        components = new ComponentList();
    }

    public AppState(ArrayList<Student> studentArrayList,
                    ArrayList<Grade> gradeArrayList,
                    ArrayList<Component> componentArrayList) {
        students = new StudentList(studentArrayList);
        grades = new GradeList(gradeArrayList);
        components = new ComponentList(componentArrayList);
    }

    public void updateAllStudentPercentageScores() {
        for (Student student : students.getStudentArrayList()) {
            student.setPercentageScore(
                    grades.calculateStudentPercentageScore(student.getMatricNumber(), components)
            );
        }
    }
}
