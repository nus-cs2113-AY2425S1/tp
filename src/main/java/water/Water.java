// @@author Atulteja
package water;

import exceptions.WaterException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a list of water intake records, allowing addition, deletion, and retrieval of records.
 */
public class Water {

    private static final Logger logger = Logger.getLogger(Water.class.getName());
    private final ArrayList<Float> waterList;

    /**
     * Constructs a new Water instance with an empty list of water entries.
     */
    public Water() {
        waterList = new ArrayList<>();
        logger.log(Level.INFO, "Water instance created with an empty list.");
    }

    /**
     * Adds a specified amount of water to the list.
     *
     * @param water the amount of water in liters to add; must be positive
     * @throws AssertionError if the amount of water is not positive
     */
    public void addWater(float water) {
        assert water > 0 : "Water amount must be positive";

        waterList.add(water);
        logger.log(Level.INFO, "{0} liters of water added. Current list: {1}", new Object[]{water, waterList});
    }

    /**
     * Checks if the water list is empty.
     *
     * @return true if the water list contains no entries; false otherwise
     */
    public boolean isEmpty() {
        return waterList.isEmpty();
    }

    /**
     * Deletes a water entry at the specified index.
     *
     * @param index the index of the water entry to delete
     * @return the amount of water deleted
     * @throws IndexOutOfBoundsException if the index is out of bounds for the water list
     */
    public float deleteWater(int index) {
        if (index < 0 || index >= waterList.size()) {
            logger.log(Level.WARNING, "Invalid index for deletion: {0}", index);
            throw WaterException.doesNotExist();
        }

        float waterToBeDeleted = waterList.get(index);
        waterList.remove(index);
        logger.log(Level.INFO, "Deleted {0} liters of water at index {1}. Current list: {2}",
                new Object[]{waterToBeDeleted, index, waterList});
        return waterToBeDeleted;
    }

    /**
     * Retrieves the list of water entries.
     *
     * @return an ArrayList containing all water entries in liters
     */
    public ArrayList<Float> getWaterList() {
        logger.log(Level.INFO, "Retrieved water list: {0}", waterList);
        return waterList;
    }

    /**
     * Returns a string representation of the water entries.
     *
     * @return a string listing all water entries or "No record" if the list is empty
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        if(waterList.isEmpty()) {
            return "No record.";
        }

        for (int i = 0; i < waterList.size(); i++) {
            output.append(i + 1).append(": ").append(waterList.get(i)).append("\n");
        }

        return output.toString().trim();
    }
}
