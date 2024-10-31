package seedu.duke.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a storage backup file to manage Hospital if storage cannot be recovered.
 */
public class StorageBackup {
    private static final String BACKUP_PREFIX = "data/hospital_backup_";
    private static final String BACKUP_EXTENSION = ".json";

    /** The file path of the backup storage file. */
    private static String fileBackupPath;

    /**
     * Constructs a StorageBackup with the default file path with timestamp.
     */
    public StorageBackup(){
        this(BACKUP_PREFIX + System.currentTimeMillis() + BACKUP_EXTENSION);
    }

    /**
     * Constructs a StorageBackup with the specified file path.
     *
     * @param filePath The String file path of the backup storage file.
     */
    public StorageBackup(String filePath) {
        this.fileBackupPath = filePath;
    }

    /**
     * Returns the file path of the backup storage file.
     *
     * @return The String file path of the backup storage file.
     */
    public static String getBackupFilePath() {
        return fileBackupPath;
    }

    /**
     * Creates a filename with the current timestamp.
     *
     * @return The String file path of the backup storage file.
     */
    private static String createBackupFileName() {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return BACKUP_PREFIX + timestamp + BACKUP_EXTENSION;
    }


    /**
     * Creates a backup file with the default file path.
     *
     * @param filePath The String file path of the storage file.
     */
    public static void createBackupFile(String filePath) {
        // String backupFileName = createBackupFileName();
        File backupFile = new File(getBackupFilePath());

        try {
            Files.copy(new File(filePath).toPath(), backupFile.toPath());
            System.out.println("Backup file created: " + getBackupFilePath());
            System.out.println("Empty Data has been created.");

        } catch (IOException e) {
            System.out.println("Error creating backup file: " + e.getMessage());
        }
    }
}
