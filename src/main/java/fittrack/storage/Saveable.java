package fittrack.storage;

public abstract class Saveable {

    /**
     * Returns a formatted string to be saved to a file on disk
     *
     * @return String using format "{@code itemType} | {@code Description} | ...Misc-information... | {@code User}"
     */

    public abstract String toSaveString();

    // Parses a formatted string to load task data from a save-file
    public static Saveable fromSaveString(String saveString) {
        throw new UnsupportedOperationException("Must be implemented by subclass.");
    }

}
