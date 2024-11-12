// @@author Atulteja

package programme;

import exceptions.ProgrammeException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Represents a Programme consisting of multiple days, each containing exercises.
 * Provides functionality to add, retrieve, and delete days, as well as access programme information.
 */
public class Programme {
    private static final Logger logger = Logger.getLogger(Programme.class.getName());

    private final String programmeName;
    private final ArrayList<Day> dayList;

    /**
     * Constructs a Programme with the specified name and a list of days.
     *
     * @param programmeName the name of the programme
     * @param dayList       the list of days in the programme
     * @throws AssertionError if the programme name is null/empty or dayList is null
     */
    public Programme(String programmeName, ArrayList<Day> dayList) {
        assert programmeName != null && !programmeName.isEmpty() : "Programme name cannot be null or empty";
        assert dayList != null : "Day list cannot be null";

        this.programmeName = programmeName;
        this.dayList = dayList;

        logger.log(Level.INFO, "Programme created: {0}", this);
    }

    public String getProgrammeName() {
        return programmeName;
    }

    /**
     * Retrieves the day at the specified index.
     *
     * @param index the index of the day to retrieve
     * @return the Day at the specified index
     * @throws ProgrammeException if the index is out of bounds for the day list
     */
    public Day getDay(int index){
        if (index < 0 || index >= dayList.size()) {
            logger.log(Level.WARNING, "Invalid index: {0} for getDay()", index);
            throw ProgrammeException.doesNotExist("day");
        }
        logger.log(Level.INFO, "Retrieving day at index {0}: {1}", new Object[]{index, dayList.get(index)});
        return dayList.get(index);
    }

    /**
     * Inserts a new day into the programme.
     *
     * @param day the day to insert
     * @throws AssertionError if the day is null
     */
    public void insertDay(Day day) {
        assert day != null : "Day to insert cannot be null";
        logger.log(Level.INFO, "Inserting day: {0}", day);

        dayList.add(day);
    }

    public int getDayCount() {
        logger.log(Level.INFO, "Getting day count: {0}", dayList.size());
        return dayList.size();
    }


    /**
     * Deletes the day at the specified index.
     *
     * @param index the index of the day to delete
     * @return the Day that was deleted
     * @throws ProgrammeException if the index is out of bounds for the day list
     */
    public Day deleteDay(int index){
        if (index < 0 || index >= dayList.size()) {
            logger.log(Level.WARNING, "Invalid index: {0} for deleteDay()", index);
            throw ProgrammeException.doesNotExist("day");
        }
        Day toBeDeleted = dayList.get(index);
        dayList.remove(index);
        logger.log(Level.INFO, "Deleted day at index {0}: {1}", new Object[]{index, toBeDeleted});
        return toBeDeleted;
    }


    /**
     * Returns a string representation of the programme, including its name and all days.
     *
     * @return a string representation of the programme
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(programmeName).append("\n\n");

        for (int i = 0; i < dayList.size(); i++) {
            str.append("Day ").append(i+1).append(": ").append(dayList.get(i)).append("\n");
        }

        return str.toString();
    }
}
