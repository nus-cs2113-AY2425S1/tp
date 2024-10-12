package core;

import programme.Day;

import java.time.LocalDateTime;
import java.util.HashMap;

public class History {
    private HashMap<LocalDateTime, Day> history;

    public void logDay(Day completed, LocalDateTime date) {

    }

    @Override
    public String toString() {
        return "History";
    }
}
