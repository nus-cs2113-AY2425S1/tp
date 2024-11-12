package university;

/**
 * Represents a university with details such as its full name, acronym, and
 * the number of available spots for allocation.
 * The class provides methods to retrieve university information and update
 * available spots when a student is allocated.
 */

public class University {
    private String fullName;
    private String acronym;
    private int spotsLeft;

    /**
     * Constructs a University with the specified name, acronym, and available spots.
     * 
     * @param fullName The full name of the university.
     * @param acronym The acronym representing the university.
     * @param spotsLeft The number of spots left for student allocation.
     */

    public University(String fullName, String acronym, int spotsLeft) {
        assert fullName != null : "Full name cannot be null";
        assert acronym != null : "Acronym cannot be null";
        assert spotsLeft >= 0 : "Spots left cannot be negative";

        this.fullName = fullName;
        this.acronym = acronym;
        this.spotsLeft = spotsLeft;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAcronym() {
        return acronym;
    }

    public int getSpotsLeft() {
        return spotsLeft;
    }

    /**
     * Decrements the number of available spots by one, typically called when
     * a student is successfully allocated to this university.
     */

    public void removeASpot(){
        assert spotsLeft > 0 : "Cannot remove a spot when none are left";
        this.spotsLeft--;
    }

    /**
     * Increments the number of available spots by one, typically called when
     * a student is deleted from this university.
     */
    public void addASpot(){
        assert spotsLeft >= 0 : "spot left cannot be negative";
        this.spotsLeft++;
    }

    /**
     * Returns a string representation of the university in the format:
     * "Full Name (Acronym)".
     * 
     * @return A string representation of the university.
     */

    @Override
    public String toString() {
        return fullName + " (" + acronym + ")";
    }
}

