package core;

import programme.Day;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

public class History {
    private HashMap<Date, Day> history;

    public void logDay(Day completed, LocalDateTime date) {

    }

    @Override
    public String toString() {
        return "History";
    }
}
