package seedu.duke;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * Handles the saving and loading of internships from a file.
 * The tasks are saved in a specified format and can be restored upon loading from the file.
 */
public class Storage {
    //define filepath
    private static final String FILE_PATH = "./data/EasInternship.txt";


    /**
     * Saves all the Internships in the InternshipList to a file.
     * Internships are stored in a specific format, depending on the fields relevant to each internship
     * (ID, Role, Company, Duration, Skills, Status).
     */
    public static void saveToFile(InternshipList internshipList) {
        try {
            File dir = new File("./data");
            //create directory if file does not exist
            if (!dir.exists()) {
                dir.mkdirs();
            }
            FileWriter writer = new FileWriter(FILE_PATH);
            List<Internship> internships = internshipList.getAllInternships();
            for (Internship internship : internships) {
                writer.write(internship.getId() + " | "
                    + internship.getRole() + " | "
                    + internship.getCompany() + " | "
                    + internship.getStartDate() + " | "
                    + internship.getEndDate() + " | "
                    + internship.getSkills() + " | "
                    + internship.getStatus() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error while saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads internships from the file into the InternshipList.
     * The method parses each line to recreate the Internship objects and adds them to the InternshipList.
     */
    public static void loadFromFile(InternshipList internshipList) {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.out.println("No data file found.");
                return;
            }
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" \\| ");
                String role = data[1];
                String company = data[2];
                String startDate = data[3];
                String endDate = data[4];
                String skills = data[5];
                String status = data[6];

                Internship internship = new Internship(role, company, startDate, endDate);
                internship.setSkills(skills);
                internship.setStatus(status);
                internshipList.addInternship(internship);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error while loading tasks: " + e.getMessage());
        }
    }

}
