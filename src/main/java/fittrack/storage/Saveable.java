package fittrack.storage;

public abstract class Saveable {

    /**
     * Returns a formatted string to be saved to a file on disk
     *
     * @return String using format "{@code itemType} | {@code Description} | ...Misc-information... | {@code User}"
     */
    public abstract String toSaveString();

    // Parses a formatted string from a save-file to create a new Saveable item.
    public static Saveable fromSaveString(String saveString) {
        throw new UnsupportedOperationException("Must be implemented by subclass.");
    }

}
