package core;

import programme.ProgrammeList;

public class DataWrapper {
    private ProgrammeList programmeList;
    private History history;

    // Constructor
    public DataWrapper(ProgrammeList programmeList, History history) {
        this.programmeList = programmeList;
        this.history = history;
    }

    // Getter for programmeList
    public ProgrammeList getProgrammeList() {
        return programmeList;
    }

    // Setter for programmeList
    public void setProgrammeList(ProgrammeList programmeList) {
        this.programmeList = programmeList;
    }

    // Getter for history
    public History getHistory() {
        return history;
    }

    // Setter for history
    public void setHistory(History history) {
        this.history = history;
    }
}
