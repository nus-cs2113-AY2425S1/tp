package TutorLink.list;

import TutorLink.list.List;
import tutorlink.assignment.Assignment;

import java.util.ArrayList;
import java.util.Arrays;

public class AssignmentList extends List {
    private int numberOfAssignments;
    ArrayList<Assignment> assignmentArrayList;

    public AssignmentList() {
        this.numberOfAssignments = 0;
        this.assignmentArrayList = new ArrayList<>();
    }

    public void deleteAssignment(Assignment assignment){
        assignmentArrayList.remove(assignment);
    }

    public void addAssignment(Assignment assignment){
        assignmentArrayList.add(assignment);
    }
}
