package exceptions;

import java.io.IOException;

public class StorageExceptions extends IOException {
    public StorageExceptions(String message) {
        super(message);
    }

    public static StorageExceptions unableToSave() {
      return new StorageExceptions("Could not save storage file");
    }

    public static StorageExceptions unableToCreateDirectory() {
      return new StorageExceptions("Could not create directory");
    }

  public static StorageExceptions unableToCreateFile() {
      return new StorageExceptions("Could not create file");
  }
}
