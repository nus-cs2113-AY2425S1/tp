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
    }

    public Programme deleteProgram(int index){
        if (programmeList.size() < index){
            System.out.println("invaid index");
        }
        Programme programmeToDelete = programmeList.get(index -1);
        programmeList.remove(index - 1);
        return programmeToDelete;
    }

    public Programme getProgramme(int index){
        return programmeList.get(index);
    }

    public Programme startProgramme(int startIndex) {
        currentActiveProgramme = startIndex;
        return programmeList.get(currentActiveProgramme);
    }

    public Day getDay(int dayIndex, int progIndex) {
        Programme progContent = programmeList.get(progIndex);
        return progContent.getDay(dayIndex);
    }

    public Day getDay(int dayIndex) {
        Programme progContent = programmeList.get(currentActiveProgramme);
        return progContent.getDay(dayIndex);
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < programmeList.size(); i++) {
            if (i == currentActiveProgramme){
                str.append("*");
            }
            Programme programme = programmeList.get(i);
            str.append(programme.toString()).append("\n");
        }
        return str.toString();
    }
}
