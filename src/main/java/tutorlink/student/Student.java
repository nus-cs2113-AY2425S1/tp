package tutorlink.student;

public class Student {
    private String matricNumber;
    private String name;
    private double percentageScore;

    public Student(String matricNumber, String name) {
        this.name = name;
        this.matricNumber = matricNumber.toUpperCase();
        this.percentageScore = 0.0;
    }

    /**
     * Constructs a new Student with the specified matriculation number, name, and GPA.
     *
     * @param matricNumber The matriculation number of the student.
     * @param name The name of the student.
     * @param gpa The GPA of the student.
     */
    public Student(String matricNumber, String name, double gpa) {
        this.name = name;
        this.matricNumber = matricNumber.toUpperCase();
        this.percentageScore = gpa;
    }

    public String getName() {
        return name;
    }

    public String getMatricNumber() {
        return matricNumber;
    }

    public double getPercentageScore() {
        return percentageScore;
    }

    public void setPercentageScore(double percentageScore) {
        this.percentageScore = percentageScore;
    }

    @Override
    public String toString() {
        return this.name + " (matric no: " + this.matricNumber + ", percentage score: " +
                String.format("%.2f", this.percentageScore) + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Student)) {
            return false;
        }
        Student s = (Student) obj;
        return this.matricNumber.equalsIgnoreCase(s.getMatricNumber());
    }
}
