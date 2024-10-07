package programme;

import java.util.ArrayList;

public class Programme {
    private String programmeName;
    private ArrayList<Day> dayList;

    public Programme(String programmeName, ArrayList<Day> dayList) {
        this.programmeName = programmeName;
        this.dayList = dayList;
    }

    public Programme(ArrayList<Day> dayList) {
        this.dayList = dayList;
    }

    public Day getDay(int index) {
        return dayList.get(index);  // This uses ArrayList.get()
    }


    public void insertDay(String dayName, ArrayList<Exercise> exercises) {
        Day day = new Day(dayName, exercises);
        dayList.add(day);
        //saveTask();
        int dayCount = dayList.size();

        System.out.println("Got it, I have inserted this program: " + dayName);
        System.out.println("Program count: " + dayCount);
    }

    public void deleteDay(int index){
        if (dayList.size() < index){
            System.out.println("invaid index");
        }
        dayList.remove(index - 1);
        //saveTasks();
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(programmeName).append("\n");
        for (Day day : dayList) {
            str.append("       - ").append(day.toString()).append("\n");
        }
        return str.toString();
    }
}


