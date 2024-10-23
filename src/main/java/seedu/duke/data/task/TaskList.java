package seedu.duke.data.task;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TaskList {
    private final ArrayList<Task> tasks;
    private double completionRate;
    /**
     * Creates a TaskList object with an empty ArrayList of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        completionRate = 0;
    }

    /**
     * Creates a TaskList object with an existing ArrayList of tasks.
     *
     * @param tasks ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        setCompletionRate(calCompletionRate());
    }
    
    public void setCompletionRate(double completionRate) {
        this.completionRate = completionRate;
    }

    public double calCompletionRate() {
        int done = 0;
        for (Task task : tasks) {
            if (task.isDone()) {
                done++;
            }
        }
        
        if(getSize() == 0) {
            completionRate = 1.0;
        } else {
            completionRate = (double) done / getSize();   
        } 
        return completionRate;
    }

    public String getCompletionRate() {
        return String.format("%.2f", completionRate * 100) + "%";
    }

    public void addTask(String description) {
        Task task = new Task(description);
        tasks.add(task);
        setCompletionRate(calCompletionRate());;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task Task to be added.
     * @throws DuplicateTaskException If the task is already in the TaskList.
     */
    public void addTask(Task task) throws DuplicateTaskException {
        if(contains(task)) {
            throw new DuplicateTaskException();
        }
        tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param index Index of the task to be deleted.
     * @throws TaskNotFoundException If the task is not in the TaskList.
     */
    public void deleteTask(int index) throws TaskNotFoundException {
        if(!contains(index)) {
            throw new TaskNotFoundException();
        }
        tasks.remove(index);
        setCompletionRate(calCompletionRate());;
    }

    public void deleteTask(Task task) throws TaskNotFoundException {
        if(!contains(task)) {
            throw new TaskNotFoundException();
        }
        tasks.remove(task);
        setCompletionRate(calCompletionRate());;
    }

    public TaskList findTasks(String keyword) throws DuplicateTaskException {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks;
    }

    public boolean contains(Task task) {
        return tasks.contains(task);
    }

    public boolean contains(int index) {
        return index >= 0 && index < getSize();
    }

    /**
     * Gets a task from the TaskList.
     * @param index Index of the task to be retrieved.
     * @return Task object.
     * @throws TaskNotFoundException If the task is not in the TaskList.
     */
    public Task getTask(int index) throws TaskNotFoundException {
        if(!contains(index)) {
            throw new TaskNotFoundException();
        }
        return tasks.get(index);
    }

    @JsonIgnore
    public int getSize() {
        return tasks.size();
    }

    public void markAsDone(int index) throws TaskNotFoundException {
        if(!contains(index)) {
            throw new TaskNotFoundException();
        }
        tasks.get(index).markAsDone();
        setCompletionRate(calCompletionRate());;
    }

    public void markAsUndone(int index) throws TaskNotFoundException {
        if(!contains(index)) {
            throw new TaskNotFoundException();
        }
        tasks.get(index).markAsUndone();
        setCompletionRate(calCompletionRate());;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Catch tasks is empty
        if (tasks.isEmpty()) {
            return "";
        }

        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    public void printList() {
        System.out.println(this);
    }



    public static class TaskNotFoundException extends Exception {
        public TaskNotFoundException() {
            super("Input task is not found in the list.");
        }
    }

    public static class DuplicateTaskException extends Exception {
        public DuplicateTaskException() {
            super("Input task is already in the list.");
        }
    }
}
