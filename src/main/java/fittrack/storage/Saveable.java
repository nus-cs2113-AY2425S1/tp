package fittrack.storage;


/**
 * An abstract base class representing objects that can be saved to and loaded from
 * a persistent storage file. Subclasses implement the serialization and deserialization
 * of data to enable saving and loading of objects.
 */
public abstract class Saveable {

    /**
     * Generates a formatted string representation of the object suitable for saving to a file.
     * The format typically includes type, description, user, and other specific data required
     * for restoring the object later.
     *
     * @return A string formatted as "{@code itemType} | {@code Description} | ...Misc-information... | {@code User}".
     */
    public abstract String toSaveString();

    /**
     * Parses a formatted string and creates a `Saveable` object based on its content.
     * This method should be overridden by subclasses to handle object-specific parsing logic.
     *
     * @param saveString The string data from the save file representing a `Saveable` object.
     * @return A new instance of the subclass, populated with data from `saveString`.
     * @throws UnsupportedOperationException If called directly on `Saveable` class instead of a subclass.
     */
    public static Saveable fromSaveString(String saveString) {
        throw new UnsupportedOperationException("Must be implemented by subclass.");
    }

}
