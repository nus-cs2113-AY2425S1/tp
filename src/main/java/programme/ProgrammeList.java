package programme;

import java.util.ArrayList;

public class ProgrammeList {

    //private ArrayList<ArrayList<ArrayList<Exercise>>> programmeList;
    private ArrayList<Programme> programmeList;
    int currentActiveProgramme;

    public ProgrammeList() {
        programmeList = new ArrayList<>();
    }

    public void insertProgramme(String programmeName, ArrayList<ArrayList<Exercise>> contents) {
        ArrayList<Day> days = new ArrayList<>();
        for (ArrayList<Exercise> content: contents) {
            Day day = new Day(content);
            days.add(day);
        }
        Programme programme = new Programme(days);
        programmeList.add(programme);
        //saveTask();
        int programCount = programmeList.size();

        System.out.println("Got it, I have inserted this program: " + programmeName);
        System.out.println("Program count: " + programCount);
    }

    public void deleteProgram(int index){
        if (programmeList.size() < index){
            System.out.println("invaid index");
        }
        programmeList.remove(index - 1);
        //saveTasks();
    }

    public Programme getProgramme(int index){
        return programmeList.get(index);
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        for (Programme programme : programmeList) {
            str.append(programme.toString()).append("\n");
        }
        return str.toString();
    }

    public Programme startProgramme(int startIndex) {
        currentActiveProgramme = startIndex;
        return programmeList.get(currentActiveProgramme);
    }

    // getDay receives progIndex, dayIndex and returns the specified Day object
    // if progIndex is null, assume they want to use the active programme instead
    public Day getDay(int progIndex, int dayIndex) {
        Programme progContent = programmeList.get(progIndex);
        return progContent.getDay(dayIndex);
    }
}
