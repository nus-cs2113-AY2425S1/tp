package programme;

import java.util.ArrayList;

public class ProgrammeList {

    //private ArrayList<ArrayList<ArrayList<Exercise>>> programmeList;
    int currentActiveProgramme;
    private ArrayList<Programme> programmeList;

    public ProgrammeList() {
        programmeList = new ArrayList<>();
    }

    public Programme insertProgramme(String programmeName, ArrayList<ArrayList<Exercise>> contents) {
        ArrayList<Day> days = new ArrayList<>();
        for (ArrayList<Exercise> content: contents) {
            Day day = new Day(content);
            days.add(day);
        }
        Programme programmeToAdd = new Programme(programmeName, days);
        programmeList.add(programmeToAdd);
        return programmeToAdd;
        //saveTask();
    }

    public Programme deleteProgram(int index){
        if (programmeList.size() < index){
            System.out.println("invaid index");
        }
        Programme programmeToDelete = programmeList.get(index -1);
        programmeList.remove(index - 1);
        return programmeToDelete;
        //saveTasks();
    }

    public Programme getProgramme(int index){
        return programmeList.get(index);
    }

    public Programme startProgramme(int startIndex) {
        currentActiveProgramme = startIndex;
        return programmeList.get(currentActiveProgramme);
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        for (Programme programme : programmeList) {
            str.append(programme.toString()).append("\n");
        }
        return str.toString();
    }

    // getDay receives progIndex, dayIndex and returns the specified Day object
    // if progIndex is null, assume they want to use the active programme instead
    public Day getDay(int progIndex, int dayIndex) {
        Programme progContent = programmeList.get(progIndex);
        return progContent.getDay(dayIndex);
    }

    //if no progIndex is given
    public Day getDay(int dayIndex) {
        Programme progContent = programmeList.get(currentActiveProgramme);
        return progContent.getDay(dayIndex);
    }
}
