import java.util.ArrayList;

public class InternshipList {
    private ArrayList<Internship> internships;

    public InternshipList() {
        this.internships = new ArrayList<>();
    }

    public void addInternship(Internship internship) {
        internship.setId(internships.size() + 1);
        internships.add(internship);
    }

    // Method to remove an internship by index (0-based)
    public void removeInternship(int index) {
        if (index >= 0 && index < internships.size()) {
            internships.remove(index);
            updateIds(); // Reassign IDs after removal
        } else {
            System.out.println("Invalid index");
        }
    }

    // Private method to update the IDs after an addition/removal
    private void updateIds() {
        for (int i = 0; i < internships.size(); i++) {
            internships.get(i).setId(i + 1); // ID is 1-based
        }
    }

    // Method to get an internship by index
    public Internship getInternship(int index) {
        if (index >= 0 && index < internships.size()) {
            return internships.get(index);
        } else {
            System.out.println("Invalid index");
            return null;
        }
    }

    // Method to list all internships
    public void listAllInternships() {
        if (internships.isEmpty()) {
            System.out.println("No internships added.");
        } else {
            for (Internship internship : internships) {
                System.out.println(internship);
                System.out.println("---------------------------------");
            }
        }
    }
}
