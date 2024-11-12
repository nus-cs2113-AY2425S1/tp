package student;

import java.util.ArrayList;

/**
 * This class represents a student with a unique ID, GPA, university preferences
 * and allocation status.
 */
public class Student {
    private String id;
    private float gpa;
    private ArrayList<Integer> uniPreferences;
    private boolean isSuccessfullyAllocated = false;
    private int allocatedUniversity = -1;

    /**
     * Constructs a new Student with the given ID, GPA, and university preferences.
     *
     * @param id The unique identifier of the student.
     * @param gpa The student's GPA.
     * @param uniPreferences A list of the student's university preferences.
     */
    public Student(String id, float gpa, ArrayList<Integer> uniPreferences){
        assert id != null : "Student ID cannot be null";
        assert gpa >= 0.0 && gpa <= 5.0 : "GPA must be between 0.0 and 5.0"; // Assuming GPA scale from 0.0 to 5.0
        assert uniPreferences != null : "University preferences cannot be null";
        
        this.id = id;
        this.gpa = gpa;
        this.uniPreferences = uniPreferences;
    }

    /**
     * Copy constructor that creates a deep copy of another Student object.
     *
     * @param other The Student object to copy.
     */
    public Student(Student other) {
        this.id = other.id;
        this.gpa = other.gpa;
        this.uniPreferences = new ArrayList<>(other.uniPreferences.size());
        for (Integer preference : other.uniPreferences) {
            this.uniPreferences.add(preference);
        }
        this.isSuccessfullyAllocated = other.isSuccessfullyAllocated;
        this.allocatedUniversity = other.allocatedUniversity;
    }

    public float getGpa() {
        return gpa;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Integer> getUniPreferences() {
        return uniPreferences;
    }

    public boolean getSuccessfullyAllocated() {
        return isSuccessfullyAllocated;
    }

    public void setSuccessfullyAllocated(boolean success) {
        isSuccessfullyAllocated = success;
    }

    public void setAllocatedUniversity(int university) {
        this.allocatedUniversity = university;
    }

    public int getAllocatedUniversity() {
        return this.allocatedUniversity;
    }

    public void resetAllocationStatus() {
        this.isSuccessfullyAllocated = false;
        this.allocatedUniversity = -1;
    }
}
