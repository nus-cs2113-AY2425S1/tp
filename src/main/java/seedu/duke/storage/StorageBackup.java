package seedu.duke.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StorageBackup {
    private static final String BACKUP_PREFIX = "data/hospital_backup_";
    private static final String BACKUP_EXTENSION = ".json";


    /** The file path of the backup storage file. */
    private final String fileBackupPath;

    public StorageBackup(){
        this.fileBackupPath = BACKUP_PREFIX + System.currentTimeMillis() + BACKUP_EXTENSION;
    }

    public String getBackupFilePath() {
        return fileBackupPath;
    }

    private static String createBackupFileName() {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return BACKUP_PREFIX + timestamp + BACKUP_EXTENSION;
    }

    public static void createBackupFile(String filePath) {

        String backupFileName = createBackupFileName();
        File backupFile = new File(backupFileName);

        try {
            Files.copy(new File(filePath).toPath(), backupFile.toPath());
            System.out.println("Backup file created: " + backupFileName);
            System.out.println("New Data has been created.");

        } catch (IOException e) {
            System.out.println("Error creating backup file: " + e.getMessage());
        }
    }

}
