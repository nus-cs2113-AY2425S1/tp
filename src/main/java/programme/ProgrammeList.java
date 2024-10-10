package programme;

import java.util.ArrayList;
import com.google.gson.Gson;

public class ProgrammeList {

    int currentActiveProgramme;
    private ArrayList<Programme> programmeList;

    public ProgrammeList() {
        programmeList = new ArrayList<>();
    }

    public Programme insertProgramme(String programmeName, ArrayList<Day> days) {
        Programme programmeToAdd = new Programme(programmeName, days);
        programmeList.add(programmeToAdd);
        return programmeToAdd;
    }

    public Programme deleteProgram(int index){
        if (programmeList.size() < index){
            System.out.println("invalid index");
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

    public Day getDay(int progIndex, int dayIndex) {
        if (dayIndex  == -1) {
            dayIndex = currentActiveProgramme;
        }
        Programme progContent = programmeList.get(progIndex);
        return progContent.getDay(dayIndex);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < programmeList.size(); i++) {
            Programme programme = programmeList.get(i);
            if (i == currentActiveProgramme) {
                str.append("*Active* ");
            }
            str.append(programme);
        }
        return str.toString();
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static ProgrammeList fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, ProgrammeList.class);
    }
}
