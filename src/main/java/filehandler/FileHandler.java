package filehandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import exceptions.SEPEmptyException;
import exceptions.SEPException;
import exceptions.SEPIOException;
import exceptions.SEPUnknownException;
import parser.Parser;
import student.Student;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static studentlist.StudentList.ADD_STUDENT_REGEX;

public class FileHandler {
    private String filePath;
    private Parser parser;

    public FileHandler(String filePath, Parser parser) {
        this.filePath = filePath.isEmpty() ? "data" : filePath;
        this.parser = parser;
    }

    public static boolean isValidPath(String path) {
        try {
            Path filePath = Paths.get(path);
            return true;
        } catch (InvalidPathException e) {
            return false;
        }
    }

    /**
     * Returns the file extension of the given file, or an empty string
     * if the file does not have an extension.
     * 
     * @param file the file to get the extension of
     * @return the file extension
     */
    private String getFileExtension(File file) {
        String name = file.getName();
        int lastDotIndex = name.lastIndexOf('.');
        return (lastDotIndex == -1) ? "" : name.substring(lastDotIndex + 1);
    }

    /**
     * Processes the file located at the path given during construction of
     * this object, and returns a boolean indicating whether the file was
     * processed successfully or not. The file type is determined by its
     * extension, and the appropriate private method is called to process
     * the file. If the file is missing or has an unknown extension, an
     * exception is thrown.
     * 
     * @return true if the file was processed successfully, false otherwise
     * @throws SEPException if there is an issue with the file, such as it
     *     being missing, or having an unknown extension
     */
    public boolean hasProcessFileSuccessfully() throws SEPException, IOException {
        Path path = Paths.get(this.filePath);
        boolean result;

        if (!Files.exists(path)) {
            throw SEPEmptyException.rejectFileNotFound();
        }

        if (Files.size(path) == 0) {
            throw SEPEmptyException.rejectEmptyFile();
        }

        String extension = getFileExtension(path.toFile());

        switch (extension.toLowerCase()) {
        case "csv":
            result = hasProcessCSV(path.toFile());
            break;
        case "json":
            result = hasProcessJSON(path.toFile());
            break;
        case "txt":
            result = hasProcessTXT(path);
            break;
        default:
            throw SEPUnknownException.rejectUnknownFileType();
        }

        return result;
    }


    
    /**
     * Processes a CSV file containing student data, validates each row,
     * and adds the student information to the system. The CSV file is
     * expected to have three columns: ID, GPA, and Preferences. If any
     * row is not in the correct format or contains invalid data, an
     * exception is thrown. The output is temporarily suppressed during
     * processing.
     *
     * @param file the CSV file to be processed
     * @return true if the file was processed successfully, false otherwise
     * @throws SEPIOException if there is an I/O error or invalid CSV format
     * @throws CsvValidationException if there is an issue with CSV parsing
     * @throws SEPException if there is an issue with student data validation
     */
    private boolean hasProcessCSV(File file) {
        // Save the current System.out
        PrintStream originalOut = System.out;

        // Suppress output
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        try {
            // Use default CSVParser to split on commas
            CSVParser parser = new CSVParserBuilder().withSeparator(',').build();

            // Initialize CSVReader with the parser
            CSVReader reader = new CSVReaderBuilder(new java.io.FileReader(file)).withCSVParser(parser).build();

            String[] line;
            reader.readNext();  // Skip the header
            while ((line = reader.readNext()) != null) {
                if (line.length != 3) {
                    throw SEPIOException.rejectCSVDataFormat(line);
                }
                assert line.length == 3;

                // Extract and format the command
                String id = line[0].trim();
                String gpa = line[1].trim();
                String preferences = line[2].trim();
                String command = String.format("add id/%s gpa/%s p/%s", id, gpa, preferences);

                // Validate the data format
                if ((!this.parser.isValidData(id, gpa,preferences)) || (!command.matches(ADD_STUDENT_REGEX))) {
                    throw SEPIOException.rejectCSVFile();
                }

                this.parser.parseInput(command);
            }
            System.setOut(originalOut);
        } catch (IOException | CsvValidationException | SEPException e) {
            System.setOut(originalOut);
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Prints an example of a correct JSON file containing three students.
     * The example is formatted with indentation for readability.
     */
    public void printExampleJson() {
        try {
            // Create ObjectMapper to build the JSON structure
            ObjectMapper objectMapper = new ObjectMapper();

            // Create root node "students"
            ObjectNode rootNode = objectMapper.createObjectNode();
            ArrayNode studentsArray = objectMapper.createArrayNode();

            // Example student 1
            ObjectNode student1 = objectMapper.createObjectNode();
            student1.put("ID", "A1234567J");
            student1.put("GPA", "4.5");
            student1.put("PREFERENCES", "{1,2,3}");

            // Example student 2
            ObjectNode student2 = objectMapper.createObjectNode();
            student2.put("ID", "A7654321K");
            student2.put("GPA", "3.8");
            student2.put("PREFERENCES", "{2,3,1}");

            // Example student 3
            ObjectNode student3 = objectMapper.createObjectNode();
            student3.put("ID", "A1357913L");
            student3.put("GPA", "4.0");
            student3.put("PREFERENCES", "{3,1,2}");

            // Add students to the array
            studentsArray.add(student1);
            studentsArray.add(student2);
            studentsArray.add(student3);

            // Add the array to the root node
            rootNode.set("students", studentsArray);

            // Convert root node to pretty-printed JSON string
            String exampleJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);

            // Print the example JSON
            System.out.println("Example of a correct JSON file:");
            System.out.println(exampleJson);

        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
    }

    
    /**
     * Processes a JSON file and adds students to the student list if the file is valid.
     * If the file is invalid, prints an error message and an example of a correct JSON file.
     * 
     * @param file The JSON file to be processed.
     * @return true if the file is valid, false otherwise.
     */
    private boolean hasProcessJSON(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        // Save the current System.out
        PrintStream originalOut = System.out;

        // Suppress output
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        try {
            JsonNode rootNode = objectMapper.readTree(file);
            JsonNode students = rootNode.get("students");

            // Check if "students" node exists and is an array
            if (students == null || !students.isArray()) {
                throw SEPIOException.missingStudentsJSONArray();
            }

            for (JsonNode student : students) {
                // Check if required fields are present
                if (!student.has("ID") || !student.has("GPA") || !student.has("PREFERENCES")) {
                    throw SEPIOException.missingRequiredJSONFields();
                }

                // Extract and format the command
                String id = student.get("ID").asText();
                String gpa = student.get("GPA").asText();
                String preferences = student.get("PREFERENCES").asText();
                String command = String.format("add id/%s gpa/%s p/%s", id, gpa, preferences);

                // Validate the data format
                if ((!this.parser.isValidData(id, gpa,preferences)) || (!command.matches(ADD_STUDENT_REGEX))) {
                    throw SEPIOException.invalidDataJSONFormat();
                }

                this.parser.parseInput(command);
            }
            System.setOut(originalOut);
        } catch (IOException | SEPException e) {
            System.setOut(originalOut);
            System.out.println(e.getMessage());
            printExampleJson();
            return false;
        }
        return true;
    }

    
    /**
     * Processes a text file and adds students to the student list if the file is valid.
     * 
     * @param path The path to the text file.
     * @return true if the file is processed successfully, false otherwise.
     * @throws SEPIOException If the file is invalid or any other exception occurs.
     */
    private boolean hasProcessTXT(Path path) {
        // Save the current System.out to restore later
        PrintStream originalOut = System.out;

        // Suppress output
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        try {
            // Read all lines from the file
            List<String> lines = Files.readAllLines(path);

            for (String line : lines) {
                // Split the line by commas and spaces
                String[] parts = line.split(", ");

                // Ensure the line has 3 parts: id, gpa, p
                if (parts.length != 3) {
                    throw SEPIOException.missingTXTRequiredFields();
                }

                String idPart = parts[0];
                String gpaPart = parts[1];
                String preferencesPart = parts[2];

                String command = "add " + String.format("%s %s %s", idPart, gpaPart, preferencesPart);

                // Validate the format of each part
                if (!command.matches(ADD_STUDENT_REGEX)) {
                    throw SEPIOException.missingTXTRequiredFields();
                }

                String id = idPart.substring(3);  // Extract the actual ID value
                String gpa = gpaPart.substring(4);  // Extract the actual GPA value
                String preferences = preferencesPart.substring(2);  // Extract the actual Preferences value

                // Validate the data using your existing parser
                if (!this.parser.isValidData(id, gpa, preferences)) {
                    throw SEPIOException.invalidTXTDataFormat();
                }

                this.parser.parseInput(command);
            }
            System.setOut(originalOut);
        } catch (IOException | SEPException e) {
            System.setOut(originalOut);
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Saves the allocation results to a file in the specified format.
     * 
     * @param results The list of students with their allocation results.
     * @param fileChoice  The format of the file to be saved, either "csv", "json", or
     *                "txt". If choice is "csv", the allocation results will be
     *                saved to a CSV file. If choice is "json", the allocation
     *                results will be saved to a JSON file. If choice is "txt", the
     *                allocation results will be saved to a text file.
     */
    public void saveAllocationResults(ArrayList<Student> results, String fileChoice) {
        switch (fileChoice) {
        case "csv":
            saveToCSV(results,  "data/allocation_results.csv");
            break;
        case "json":
            saveToJSON(results, "data/allocation_results.json");
            break;
        case "txt":
            saveToTXT(results, "data/allocation_results.txt");
            break;
        default:
            break;
        }
    }

    /**
     * Saves the allocation results to a JSON file at the specified file path.
     * The JSON file will contain an array of students, each with their respective
     * fields. The output is formatted with indentation for readability.
     * 
     * @param results The list of students with their allocation results to be saved.
     * @param filePath The file path where the JSON file will be saved.
     */
    public void saveToJSON(ArrayList<Student> results, String filePath) {
        // Create an ObjectMapper instance
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Create a map to hold the students array
        Map<String, ArrayList<Student>> studentsMap = new HashMap<>();
        studentsMap.put("students", results);

        Path path = Paths.get(filePath);

        try {
            Files.createDirectories(path.getParent());

            // Write the map to a JSON file
            File jsonFile = new File(path.toString());
            mapper.writeValue(jsonFile, studentsMap);
            System.out.println("Allocation results saved to JSON file at " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving allocation results to JSON: " + e.getMessage());
        }
    }

    /**
     * Saves the allocation results to a .txt file at the specified file path.
     * The .txt file will contain each student's details, with each line formatted
     * as follows: id/[id], gpa/[gpa], p/[rank1, rank2, ...], alloc/[allocatedUni]
     * 
     * @param results The list of students with their allocation results to be saved.
     * @param filePath The file path where the .txt file will be saved.
     */
    public void saveToTXT(ArrayList<Student> results, String filePath) {
        Path path = Paths.get(filePath);

        try {
            // Ensure parent directories exist
            Files.createDirectories(path.getParent());

            // Create a BufferedWriter to write to the .txt file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))) {
                for (Student student : results) {
                    String line = String.format("id/%s, gpa/%.1f, p/%s, alloc/%d",
                            student.getId(),
                            student.getGpa(),
                            student.getUniPreferences().toString(),
                            student.getAllocatedUniversity());
                    writer.write(line);
                    writer.newLine();
                }
                System.out.println("Allocation results saved to TXT file at " + filePath);
            }
        } catch (IOException e) {
            System.err.println("Error saving allocation results to TXT: " + e.getMessage());
        }
    }

    /**
     * Saves a list of students to a CSV file at the specified file path.
     * The file includes columns for student ID, GPA, university preferences,
     * and allocation status.
     *
     * @param students  The list of Student objects to be saved in the CSV file.
     * @param filePath  The file path where the CSV file will be saved.
     */
    public void saveToCSV(ArrayList<Student> students, String filePath) {
        Path path = Paths.get(filePath);
        try {
            // Ensure directories exist before creating the file
            Files.createDirectories(path.getParent());

            // Use try-with-resources to automatically close the writer
            try (CSVWriter writer = new CSVWriter(new FileWriter(path.toString()))) {
                // Add header row
                String[] header = { "ID", "GPA", "Preference Rankings", "Allocation Status" };
                writer.writeNext(header);

                for (Student student : students) {
                    String[] data = {
                            student.getId(),
                            String.valueOf(student.getGpa()),
                            String.valueOf(student.getUniPreferences()),
                            String.valueOf(student.getAllocatedUniversity())
                    };
                    writer.writeNext(data);
                }

                System.out.println("CSV file saved successfully to " + path);
            }

        } catch (IOException e) {
            System.out.println("Error while saving CSV file: " + e.getMessage());
        }
    }
}
