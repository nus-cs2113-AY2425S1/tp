// @@author Atulteja

package programme;

import exceptions.ProgrammeExceptions;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static common.Utils.NULL_INTEGER;


/**
 * Represents a list of Programmes, providing functionality to add, retrieve, delete, and start a Programme.
 * Maintains the current active Programme and supports basic Programme management operations.
 */
public class ProgrammeList {

    private static final Logger logger = Logger.getLogger(ProgrammeList.class.getName());

    int currentActiveProgramme;
    private final ArrayList<Programme> programmeList;

    /**
     * Constructs an empty ProgrammeList.
     */
    public ProgrammeList() {
        programmeList = new ArrayList<>();
        currentActiveProgramme = NULL_INTEGER;
        logger.log(Level.INFO, "ProgrammeList created with an empty list.");
    }

    public ArrayList<Programme> getProgrammeList() {
        return programmeList;
    }

    public int getProgrammeListSize(){
        logger.log(Level.INFO, "Getting programme list size: {0}", programmeList.size());
        return programmeList.size();
    }

    /**
     * Deactivates the current active programme by setting the active programme index to NULL_INTEGER.
     * If there are no programmes in the list, it logs a warning and ensures the index remains as NULL_INTEGER.
     */
    public void deactivateCurrentProgramme() {
        if (programmeList.isEmpty()) {
            logger.log(Level.WARNING, "Attempted to deactivate programme, but no programmes exist in the list.");
            currentActiveProgramme = NULL_INTEGER; // Defensive: ensure it's set correctly even if empty
        } else {
            logger.log(Level.INFO, "Deactivating current active programme.");
            currentActiveProgramme = NULL_INTEGER;
        }
    }

    /**
     * Inserts a new Programme into the Programme list with the specified name and days.
     *
     * @param programmeName the name of the Programme
     * @param days          the list of days associated with the Programme
     * @return the Programme that was added
     */
    public Programme insertProgramme(String programmeName, ArrayList<Day> days) {
        Programme programmeToAdd = new Programme(programmeName, days);
        programmeList.add(programmeToAdd);

        if (programmeList.size() == 1) {
            currentActiveProgramme = 0;
        }

        return programmeToAdd;
    }

    /**
     * Deletes a Programme at the specified index or at the current active Programme if index is NULL_INTEGER.
     *
     * @param index the index of the Programme to delete, or NULL_INTEGER to delete the active Programme
     * @return the Programme that was deleted
     * @throws IndexOutOfBoundsException if the index is out of bounds for the Programme list
     */
    public Programme deleteProgram(int index){
        if (index == NULL_INTEGER){
            index = currentActiveProgramme;
        }

        if (index < 0 || index >= programmeList.size()) {
            logger.log(Level.WARNING, "Invalid index: {0} for deleteProgram()", index);
            throw ProgrammeExceptions.doesNotExist("programme");
        }

        Programme programmeToDelete = programmeList.get(index);
        programmeList.remove(index);

        if (programmeList.isEmpty()) {
            deactivateCurrentProgramme();
            // currentActiveProgramme = NULL_INTEGER;
        } else if (index == currentActiveProgramme) {
            // Reset `currentActiveProgramme` to 0 if the deleted programme was the active one
            currentActiveProgramme = 0;
        }

        logger.log(Level.INFO, "Deleted programme at index {0}: {1}", new Object[]{index, programmeToDelete});

        return programmeToDelete;
    }

    /**
     * Retrieves a Programme at the specified index or at the current active Programme if index is NULL_INTEGER.
     *
     * @param index the index of the Programme to retrieve, or NULL_INTEGER to retrieve the active Programme
     * @return the Programme at the specified index
     * @throws IndexOutOfBoundsException if the index is out of bounds for the Programme list
     */
    public Programme getProgramme(int index){
        if (index == NULL_INTEGER){
            index = currentActiveProgramme;
        }

        if (index < 0 || index >= programmeList.size()) {
            logger.log(Level.WARNING, "Invalid index: {0} for getProgramme()", index);
            throw ProgrammeExceptions.doesNotExist("programme");
        }

        logger.log(Level.INFO, "Retrieving programme at index {0}: {1}", new Object[]{index, programmeList.get(index)});
        return programmeList.get(index);
    }

    /**
     * Retrieves the current active Programme index.
     *
     * @return the current active Programme index, or {@code NULL_INTEGER} if no Programme is active.
     */
    public int getCurrentActiveProgramme(){
        logger.log(Level.INFO, "Retrieving index of current program: {1}", new Object[]{currentActiveProgramme});
        return currentActiveProgramme;
    }

    /**
     * Sets a Programme at the specified index as the current active Programme.
     *
     * @param startIndex the index of the Programme to start
     * @return the Programme that was started
     * @throws IndexOutOfBoundsException if the startIndex is out of bounds for the Programme list
     */
    public Programme startProgramme(int startIndex) {
        if (programmeList.isEmpty()){
            deactivateCurrentProgramme();
            //currentActiveProgramme = NULL_INTEGER;
            logger.log(Level.WARNING, "Attempted to start a programme but the list is empty");
            throw ProgrammeExceptions.programmeListEmpty();
        }

        if (startIndex < 0 || startIndex >= programmeList.size()) {
            logger.log(Level.WARNING, "Invalid index: {0} for startProgramme()", startIndex);
            throw ProgrammeExceptions.doesNotExist("programme");
        }

        if (currentActiveProgramme == startIndex) {
            throw ProgrammeExceptions.programmeAlreadyActive(startIndex);
        }

        currentActiveProgramme = startIndex;
        Programme activeProgramme = programmeList.get(currentActiveProgramme);
        logger.log(Level.INFO, "Started programme at index {0}: {1}", new Object[]{startIndex, activeProgramme});
        return activeProgramme;
    }

    /**
     * Returns a string representation of the Programme list, indicating the active Programme.
     *
     * @return a string representation of the Programme list
     */
    @Override
    public String toString() {
        if (programmeList.isEmpty()){
            return "No programmes found.";
        }

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < programmeList.size(); i++) {
            Programme programme = programmeList.get(i);
            str.append(i + 1).append(". ").append(programme.getProgrammeName());
            if (i == currentActiveProgramme) {
                str.append(" -- Active");
            }
            str.append("\n");
        }
        return str.toString();
    }

}
