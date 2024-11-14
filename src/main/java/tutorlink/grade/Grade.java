package tutorlink.grade;

import tutorlink.component.Component;
import tutorlink.student.Student;

/**
 * Represents a grade assigned to a student for a specific component of an assessment.
 * Each grade has a score, a corresponding component, and a student to whom it belongs.
 */
public class Grade {
    private double score;
    private Component component;
    private Student student;

    /**
     * Constructs a {@code Grade} object with the specified component, student, and score.
     *
     * @param component The component associated with the grade.
     * @param student The student associated with the grade.
     * @param score The score awarded to the student for the component.
     */
    public Grade(Component component, Student student, double score) {
        this.component = component;
        this.student = student;
        this.score = score;
    }

    /**
     * Returns the student associated with this grade.
     *
     * @return The {@code Student} object linked to this grade.
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Returns the component associated with this grade.
     *
     * @return The {@code Component} object linked to this grade.
     */
    public Component getComponent() {
        return component;
    }

    /**
     * Returns the score for this grade. If the score exceeds the component's maximum
     * score, it is capped to the maximum score of the component.
     *
     * @return The score for this grade, capped at the component's maximum score if necessary.
     */
    public double getScore() {
        if (component != null && component.getMaxScore() < score) {
            score = component.getMaxScore();
        }
        return score;
    }

    /**
     * Checks if this grade is equal to another object. Two grades are considered equal if
     * they have the same component and student.
     *
     * @param obj The object to compare with this grade.
     * @return {@code true} if the object is a grade with the same component and student; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Grade grade) {
            return this.component.equals(grade.component)
                    && this.student.equals(grade.student);
        }
        return false;
    }

    /**
     * Returns the matriculation number of the student associated with this grade.
     *
     * @return The matriculation number of the student.
     */
    public String getStudentMatricNumber() {
        return student.getMatricNumber();
    }

    /**
     * Returns the name of the component associated with this grade.
     *
     * @return The name of the component.
     */
    public String getComponentName() {
        return component.getName();
    }

    /**
     * Returns a string representation of the grade, including the student, component,
     * and score.
     *
     * @return A string representing the grade.
     */
    @Override
    public String toString() {
        return "Student: " + this.student + ", Component: " + this.component
                + ", Score: " + this.score;
    }
}
