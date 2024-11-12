package seedu.storage;

import seedu.model.Inventory;

import seedu.exceptions.InventraException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles reading from and writing to a CSV file for the inventory management system.
 * Supports operations such as loading data into an Inventory object, updating headers,
 * appending records, and saving changes back to the CSV file.
 */
public class Csv {

    private final String csvFilePath;

    /**
     * Constructs a Csv instance with the specified CSV file path.
     * Ensures that the parent directory and the file exist.
     *
     * @param relativeCsvFilePath the relative path to the CSV file
     */
    public Csv(String relativeCsvFilePath) {
        this.csvFilePath = relativeCsvFilePath;
        ensureParentDirectoriesExist(); // Ensure the directory exists
        ensureFileExists();
    }

    private void ensureParentDirectoriesExist() {
        File file = new File(csvFilePath);
        File parentDir = file.getParentFile(); // Get the parent directory
        if (parentDir != null && !parentDir.exists()) {
            if (parentDir.mkdirs()) {
                System.out.println("Directory created: " + parentDir.getName());
            } else {
                System.err.println("Failed to create directory: " + parentDir.getName());
            }
        }
    }

    private void ensureFileExists() {
        File file = new File(csvFilePath);
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
        } catch (IOException e) {
            System.err.println("Failed to create file: " + e.getMessage());
        }
    }

    /**
     * Validates headers to ensure they meet length requirements when loading data into the inventory.
     *
     * @param inventory the Inventory object with fields to validate
     * @throws InventraException if any header exceeds 20 characters
     */
    private void validateHeadersOnLoad(Inventory inventory) throws InventraException {
        for (String field : inventory.getFields()) {
            if (field.length() > 20) {
                throw new InventraException(
                        "Invalid header: '" + field + "'. Header names must be 20 characters or less.\n" +
                                "Use the following command to update the header:\n" +
                                "    update -h " + field + ", <new_field_name>"
                );
            }
        }
    }

    /**
     * Updates the CSV file after a deletion operation by rewriting all records.
     *
     * @param inventory the Inventory object containing current fields and records
     */
    public void updateCsvAfterDeletion(Inventory inventory) {
        List<String> fields = inventory.getFields();
        Map<String, String> fieldTypes = inventory.getFieldTypes();

        // Create the metadata line from field types
        List<String> metadata = new ArrayList<>();
        for (String field : fields) {
            String type = fieldTypes.get(field);
            metadata.add(field + ":" + type);
        }
        String metadataLine = "#" + String.join(",", metadata);

        try (FileWriter writer = new FileWriter(csvFilePath, false)) {
            // Write the metadata line first
            writer.append(metadataLine).append("\n");

            // Write field names (headers)
            if (!fields.isEmpty()) {
                writer.append(String.join(",", fields)).append("\n");
            }

            // Handle records
            for (Map<String, String> record : inventory.getRecords()) {
                for (int i = 0; i < fields.size(); i++) {
                    String field = fields.get(i);
                    String value = record.getOrDefault(field, "null");
                    writer.append(value != null ? value : "null");
                    if (i < fields.size() - 1) {
                        writer.append(",");
                    }
                }
                writer.append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error updating CSV after deletion: " + e.getMessage());
        }
    }

    /**
     * Updates the CSV file headers and retains existing records.
     *
     * @param inventory the Inventory object containing updated fields and types
     */
    public void updateCsvHeaders(Inventory inventory) {
        List<String> fields = inventory.getFields();
        Map<String, String> fieldTypes = inventory.getFieldTypes();

        // metadata include for persistent field type
        List<String> metadata = new ArrayList<>();
        for (String field : fields) {
            String type = fieldTypes.get(field);
            metadata.add(field + ":" + type);
        }
        String metadataLine = "#" + String.join(",", metadata);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            // Write metadata line
            writer.write(metadataLine);
            writer.newLine();
            // Write header line (field names)
            writer.write(String.join(",", fields));
            writer.newLine();
            // Write data records
            for (Map<String, String> record : inventory.getRecords()) {
                List<String> values = new ArrayList<>();
                for (String field : fields) {
                    values.add(record.getOrDefault(field, ""));
                }
                writer.write(String.join(",", values));
                writer.newLine();
            }
            System.out.println("CSV file header updated.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Appends a single record to the CSV file, maintaining the field order.
     *
     * @param record    the record to append, with field-value pairs
     * @param inventory the Inventory object containing the current field order
     */
    public void appendRecord(Map<String, String> record, Inventory inventory) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath, true))) {
            List<String> values = new ArrayList<>();
            for (String field : inventory.getFields()) {
                values.add(record.getOrDefault(field, ""));
            }
            writer.write(String.join(",", values));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads records from the CSV file into the given Inventory object.
     *
     * @param inventory the Inventory object to populate with data from the CSV file
     */
    public void loadInventoryFromCsv(Inventory inventory) {
        File file = new File(csvFilePath);
        if (file.length() == 0) {
            // No data to load, fresh file
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            // Read metadata line (fields and types)
            if ((line = reader.readLine()) != null && line.startsWith("#")) {
                String metadata = line.substring(1);  // Remove '#'
                String[] fieldsWithTypes = metadata.split(",\\s*");
                List<String> fields = new ArrayList<>();
                Map<String, String> fieldTypes = new HashMap<>();

                for (String fieldType : fieldsWithTypes) {
                    String[] parts = fieldType.trim().split(":");
                    if (parts.length == 2) {
                        String fieldName = parts[0].trim();
                        String type = parts[1].trim();
                        fields.add(fieldName);
                        fieldTypes.put(fieldName, type);
                    } else {
                        System.out.println("Invalid metadata format: " + fieldType);
                        return;
                    }
                }

                inventory.setFields(fields);
                inventory.setFieldTypes(fieldTypes);
            } else {
                System.out.println("CSV file format error: Missing metadata header.");
                return;
            }

            try {
                validateHeadersOnLoad(inventory);
            } catch (InventraException e) {
                // Handle the exception, e.g., display an error message
                System.out.println(e.getMessage());
            }

            // Read headers (second line)
            if ((line = reader.readLine()) != null) {
                String[] headers = line.split(",");
            }

            // Read records (remaining lines)
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                Map<String, String> record = new HashMap<>();

                List<String> fields = inventory.getFields();
                for (int i = 0; i < values.length; i++) {
                    if (i < fields.size()) {
                        String field = fields.get(i).trim();
                        String value = values[i].trim();
                        record.put(field, value);
                    }
                }

                inventory.addRecord(record);
            }

            System.out.println("Finished loading CSV file.");
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Updates the entire CSV file with the latest inventory fields, field types, and records.
     *
     * @param inventory the Inventory object containing the latest data to save
     */
    public void updateCsv(Inventory inventory) {
        List<String> fields = inventory.getFields();
        Map<String, String> fieldTypes = inventory.getFieldTypes();

        // Create the metadata line from field types
        List<String> metadata = new ArrayList<>();
        for (String field : fields) {
            String type = fieldTypes.get(field);
            metadata.add(field + ":" + type);
        }
        String metadataLine = "#" + String.join(",", metadata);

        try (FileWriter writer = new FileWriter(csvFilePath, false)) {
            // Write the metadata line first
            writer.append(metadataLine).append("\n");

            // Write field names (headers)
            if (!fields.isEmpty()) {
                writer.append(String.join(",", fields)).append("\n");
            }

            // Handle records
            for (Map<String, String> record : inventory.getRecords()) {
                for (int i = 0; i < fields.size(); i++) {
                    String field = fields.get(i);
                    String value = record.getOrDefault(field, "null");
                    writer.append(value != null ? value : "null");
                    if (i < fields.size() - 1) {
                        writer.append(",");
                    }
                }
                writer.append("\n");
            }
            System.out.println("CSV file updated.");
        } catch (IOException e) {
            System.err.println("Error updating CSV: " + e.getMessage());
        }
    }


}
