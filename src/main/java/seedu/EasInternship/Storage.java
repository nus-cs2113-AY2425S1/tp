package seedu.EasInternship;

import seedu.exceptions.InvalidStatus;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles the saving and loading of internships from a file.
 * The tasks are saved in a specified format and can be restored upon loading from the file.
 */

//@@author jadenlimjc
public class Storage {
    //define filepath
    private static final String FILE_PATH = "./data/EasInternship.txt";
    private static final Logger LOGGER = Logger.getLogger("EasInternship");


    /**
     * Saves all the Internships in the InternshipList to a file.
     * Internships are stored in a specific format, depending on the fields relevant to each internship
     * (ID, Role, Company, Duration, Deadlines, Skills, Status).
     */
    public static void saveToFile(InternshipList internshipList) {
        try {
            File dir = new File("./data");
            //create directory if file does not exist
            if (!dir.exists()) {
                dir.mkdirs();
                LOGGER.log(Level.INFO, "Directory created");
            }
            FileWriter writer = new FileWriter(FILE_PATH);
            List<Internship> internships = internshipList.getAllInternships();
            for (Internship internship : internships) {
                // Build the deadlines string
                StringBuilder deadlinesBuilder = new StringBuilder();
                List<Deadline> deadlines = internship.getDeadlines();

                if (deadlines.isEmpty()) {
                    deadlinesBuilder.append("No Deadlines set.");
                } else {
                    for (Deadline deadline : deadlines) {
                        deadlinesBuilder.append(deadline.getDescription())
                                .append(" -date ")
                                .append(deadline.getDate())
                                .append(" - ");
                    }
                    // Remove trailing " - "
                    deadlinesBuilder.setLength(deadlinesBuilder.length() - 3);
                }

                // Write the internship details to the file
                writer.write(internship.getId() + " | "
                        + internship.getRole() + " | "
                        + internship.getCompany() + " | "
                        + internship.getStartDate() + " | "
                        + internship.getEndDate() + " | "
                        + internship.getSkills() + " | "
                        + internship.getStatus() + " | "
                        + deadlinesBuilder + "\n");
            }

            // After writing internships, write the favourite IDs
            writer.write("FAVOURITES:");
            for (Internship favInternship : internshipList.favouriteInternships) {
                writer.write(" " + favInternship.getId());
            }
            writer.write("\n");
            LOGGER.log(Level.INFO, "Data saved");

            writer.close();
        } catch (IOException e) {
            System.out.println("Error while saving tasks: " + e.getMessage());
            LOGGER.log(Level.WARNING, "Error while saving tasks", e);
        }
    }

    /**
     * Loads internships from the file into the InternshipList.
     * The method parses each line to recreate the Internship objects and adds them to the InternshipList.
     */
    public static void loadFromFile(InternshipList internshipList) {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("No data file found.");
            LOGGER.log(Level.INFO, "No data file found.");
            return;
        }
        List<Integer> favouriteIds = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("FAVOURITES:")) {
                    // add IDs to favouriteIDs list
                    String favouritesString = line.substring("FAVOURITES:".length()).trim();
                    if (!favouritesString.isEmpty()) {
                        String[] parts = favouritesString.split(" ");
                        for (String id : parts) {
                            try {
                                int favInternshipId = Integer.parseInt(id.trim());

                                // Ensure the ID is a positive number
                                if (favInternshipId > 0) {
                                    int favInternshipIndex = favInternshipId - 1;
                                    favouriteIds.add(favInternshipIndex);
                                } else {
                                    System.out.println("Invalid ID '" + id + "': ID should be a positive integer.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid ID '" + id + "': Not a valid integer.");
                            }
                        }
                    }
                } else if (isValidFormat(line)) {
                    String[] data = line.split(" \\| ");
                    String role = data[1];
                    String company = data[2];
                    String startDate = data[3];
                    String endDate = data[4];
                    String skills = data[5];
                    String status = data[6];
                    String deadlines = data[7];

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
                    YearMonth defaultDate = YearMonth.of(1, 1);
                    YearMonth start;
                    YearMonth end;

                    try {
                        start = YearMonth.parse(startDate, formatter);
                    } catch (DateTimeParseException e) {
                        startDate = "01/01";
                        System.out.println("Incorrect startDate format found. Setting to 01/01");
                    }

                    try {
                        end = YearMonth.parse(endDate, formatter);
                    } catch (DateTimeParseException e) {
                        endDate = "01/01";
                        System.out.println("Incorrect endDate format found. Setting to 01/01");
                    }

                    Internship internship = new Internship(role, company, startDate, endDate);
                    internshipList.addInternship(internship);
                    internship.setSkills(skills);
                    loadStatus(internship, status);
                    List<Deadline> loadedDeadlines = parseDeadlines(deadlines, internship.getId());
                    for (Deadline deadline : loadedDeadlines) {
                        internship.addDeadline(deadline.getDescription(), deadline.getDate());
                    }
                } else {
                    System.out.println("Skipping invalid line in file: " + line);
                }

            }
            //process favourite internships
            for (Integer favInternshipId : favouriteIds) {
                if (favInternshipId > 0 && favInternshipId <= internshipList.getSize()) {
                    Internship favInternship = internshipList.getInternship(favInternshipId);
                    if (favInternship != null) {
                        internshipList.favouriteInternships.add(favInternship);
                    } else {
                        LOGGER.log(Level.WARNING, "Internship with ID " + favInternshipId + " not found.");
                    }

                } else {
                    System.out.println("Internship with ID " + favInternshipId
                            + " not found. Not added to Favourites List.");
                }
            }
            LOGGER.log(Level.INFO, "Data loaded");
        } catch (IOException e) {
            System.out.println("Error while loading tasks: " + e.getMessage());
            LOGGER.log(Level.WARNING, "Error while loading tasks", e);
        }
    }

    private static boolean isValidFormat(String line) {
        if (line.startsWith("FAVOURITES:")) {
            return line.matches("FAVOURITES:( \\d+)*");
        }
        return line.matches(
                "\\d+ \\| [^|]+ \\| [^|]"
                        + "+ \\| \\d{2}/\\d{2} \\| \\d{2}/\\d{2} "
                        + "\\| ([^|]*) \\| [^|]+ \\| .*"
        );
    }


    private static List<Deadline> parseDeadlines(String deadlineString, int internshipId) {
        List<Deadline> deadlines = new ArrayList<>();

        if (deadlineString.equals("No Deadlines set.")) {
            return deadlines;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        LocalDate defaultDate = LocalDate.of(1,1,1);

        String[] parts = deadlineString.split(" - ");
        for (String part : parts) {
            String[] deadlineParts = part.split(" -date ");
            if (deadlineParts.length == 2) {
                String description = deadlineParts[0].trim();
                String date = deadlineParts[1].trim();
                LocalDate parsedDate;
                try {
                    parsedDate = LocalDate.parse(date, formatter);
                } catch (DateTimeParseException e) {
                    date = "01/01/01";
                    System.out.println("Incorrect deadline format found. Setting to 01/01/01");
                }
                deadlines.add(new Deadline(internshipId, description, date));
            }
        }
        return deadlines;
    }

    private static void loadStatus(Internship internship, String status) {
        try {
            internship.updateStatus(status);
        } catch (InvalidStatus e) {
            internship.setStatus("Application Pending");
            System.out.println("Internship ID: " + internship.getId()
                    + " has corrupted status, reverting to Application Pending");
        }
    }

}
