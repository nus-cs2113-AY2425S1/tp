package tutorlink.storage;

import tutorlink.component.Component;
import tutorlink.exceptions.InvalidDataFileLineException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the loading and saving of {@code Component} objects from and to a file.
 * The file contains data on each component with its name, maximum score, and weight.
 */
public class ComponentStorage extends Storage {
    private static final double MAX_SCORE = 10000.0;

    /**
     * Constructs a {@code ComponentStorage} with the specified file path.
     *
     * @param filePath The file path for storing the component data.
     */
    public ComponentStorage(String filePath) {
        super(filePath);
    }

    /**
     * Loads the list of components from the file specified in the file path.
     * Each line in the file represents a component's data: name, maximum score, and weight.
     *
     * @return A list of {@code Component} objects loaded from the file.
     * @throws IOException If an I/O error occurs when reading the file.
     */
    public ArrayList<Component> loadComponentList() throws IOException {
        ArrayList<Component> components = new ArrayList<>();
        int totalWeight = 0;
        Scanner fileScanner = new Scanner(path);
        while (fileScanner.hasNext()) {
            try {
                Component newComponent = getComponentFromFileLine(fileScanner.nextLine(), components, totalWeight);
                totalWeight += newComponent.getWeight();
                components.add(newComponent);
            } catch (InvalidDataFileLineException e) {
                discardedEntries.add(e.getMessage());
            }
        }
        return components;
    }

    /**
     * Saves the list of components to the file specified in the file path.
     * Each component is written to a new line in the file in the format:
     * name, maximum score, and weight.
     *
     * @param components The list of components to save to the file.
     * @throws IOException If an I/O error occurs when writing to the file.
     */
    public void saveComponentList(ArrayList<Component> components) throws IOException {
        FileWriter fileWriter = new FileWriter(path.toFile());
        for (Component comp : components) {
            fileWriter.write(getFileInputForComponent(comp) + System.lineSeparator());
        }
        fileWriter.close();
    }

    /**
     * Parses a line from the file and creates a {@code Component} object.
     *
     * @param fileLine    The line from the file to parse.
     * @param components  The current list of components for duplicate checks.
     * @param totalWeight The cumulative weight of all components read so far.
     * @return A new {@code Component} object parsed from the line.
     * @throws InvalidDataFileLineException If the line is malformed or represents
     *                                      invalid component data.
     */
    private Component getComponentFromFileLine(
            String fileLine, ArrayList<Component> components, int totalWeight)
            throws InvalidDataFileLineException {
        String[] stringParts = fileLine.split(READ_DELIMITER);
        String name;
        double maxScore;
        int weight;
        try {
            name = stringParts[0].strip();
            maxScore = Double.parseDouble(stringParts[1].strip());
            weight = Integer.parseInt(stringParts[2].strip());
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidDataFileLineException(fileLine);
        }

        boolean isValidMaxScore = (maxScore >= 0 && maxScore <= MAX_SCORE);
        boolean isValidWeight = (weight >= 0 && (weight + totalWeight) <= 100);
        Component newComponent = new Component(name, maxScore, weight);
        if (!isValidMaxScore || !isValidWeight || components.contains(newComponent)) {
            throw new InvalidDataFileLineException(fileLine);
        }
        return newComponent;
    }

    /**
     * Formats a {@code Component} object into a string suitable for saving to a file.
     *
     * @param component The {@code Component} to format.
     * @return A string representing the component in the file format.
     */
    private String getFileInputForComponent(Component component) {
        String convertedString = component.getName() + WRITE_DELIMITER + component.getMaxScore()
                + WRITE_DELIMITER + component.getWeight();
        return convertedString;
    }

}
