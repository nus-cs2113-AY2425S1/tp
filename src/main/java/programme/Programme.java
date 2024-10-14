package programme;

import java.util.ArrayList;

public class Programme {
    private String programmeName;
    private ArrayList<Day> dayList;

    public Programme(String programmeName, ArrayList<Day> dayList) {
        this.programmeName = programmeName;
        this.dayList = dayList;
    }

    public Day getDay(int index) {
        return dayList.get(index);
    }

    public void insertDay(String dayName, ArrayList<Exercise> exercises) {
        Day day = new Day(dayName, exercises);
        dayList.add(day);
    }

    public void deleteDay(int index){
        if (dayList.size() < index){
            System.out.println("invalid index");
        }
        dayList.remove(index - 1);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(programmeName).append("\n\n");

        for (int i = 0; i < dayList.size(); i++) {
            str.append("Day ").append(i+1).append(": ").append(dayList.get(i));
        }

        return str.toString();
    }
}
