package seedu.duke.data.task;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private double completionRate;

    /**
     * Creates a TaskList object with an empty ArrayList of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        completionRate = 1.0;
    }

    /**
     * Creates a TaskList object with an existing ArrayList of tasks.
     *
     * @param tasks ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.completionRate = calCompletionRate();
    }

    /**
     * Sets the completion rate of the TaskList.
     *
     * @param completionRate Completion rate to be set.
     */
    public void setCompletionRate(double completionRate) {
        this.completionRate = completionRate;
    }

    /**
     * Calculates the completion rate of the TaskList.
     *
     * @return Completion rate of the TaskList as double.
     */
    public double calCompletionRate() {
        int done = 0;
        double rate = 0.0;
        for (Task task : tasks) {
            if (task.isDone()) {
                done++;
            }
        }

        if (getSize() == 0) {
            rate = 1.0;
        } else {
            rate = (double) done / getSize();
        }
        return rate;
    }

    /**
     * Gets the completion rate of the TaskList.
     *
     * @return Completion rate of the TaskList.
     */
    public double getCompletionRate() {
        return completionRate;
    }

    /**
     * Converts the completion rate to a string.
     *
     * @return Completion rate as a string.
     */
    public String completionRatePercentageToString() {
        return String.format("%.2f", completionRate * 100) + "%";
    }

    public void addTask(String description) {
        Task task = new Task(description);
        tasks.add(task);
        setCompletionRate(calCompletionRate());
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task Task to be added.
     * @throws DuplicateTaskException If the task is already in the TaskList.
     */
    public void addTask(Task task) throws DuplicateTaskException {
        if (contains(task)) {
            throw new DuplicateTaskException();
        }
        tasks.add(task);
        setCompletionRate(calCompletionRate());
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param index Index of the task to be deleted.
     * @throws TaskNotFoundException If the task is not in the TaskList.
     */
    public void deleteTask(int index) throws TaskNotFoundException {
        if (!contains(index)) {
            throw new TaskNotFoundException();
        }
        tasks.remove(index);
        setCompletionRate(calCompletionRate());
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param task Task to be deleted.
     * @throws TaskNotFoundException If the task is not in the TaskList.
     */
    public void deleteTask(Task task) throws TaskNotFoundException {
        if (!contains(task)) {
            throw new TaskNotFoundException();
        }
        tasks.remove(task);
        setCompletionRate(calCompletionRate());
    }

    /**
     * Finds tasks in the TaskList that contain the keyword.
     *
     * @param keyword Keyword to search for.
     * @return ArrayList of tasks that contain the keyword.
     * @throws TaskNotFoundException If no tasks contain the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) throws TaskNotFoundException {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase()) ||
                    task.getTag().toLowerCase().contains(keyword.toLowerCase())) { // change it so it also find tag
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            throw new TaskNotFoundException();
        }
        return matchingTasks;
    }

    /**
     * Checks if the TaskList contains a task.
     *
     * @param task Task to be checked.
     * @return True if the TaskList contains the task, false otherwise.
     */
    public boolean contains(Task task) {
        return tasks.contains(task);
    }

    /**
     * Checks if the TaskList contains a task.
     *
     * @param index Index of the task to be checked.
     * @return True if the TaskList contains the task, false otherwise.
     */
    public boolean contains(int index) {
        return index >= 0 && index < getSize();
    }

    /**
     * Gets a task from the TaskList.
     *
     * @param index Index of the task to be retrieved.
     * @return Task object.
     * @throws TaskNotFoundException If the task is not in the TaskList.
     */
    public Task getTask(int index) throws TaskNotFoundException {
        if (!contains(index)) {
            throw new TaskNotFoundException();
        }
        return tasks.get(index);
    }

    /**
     * Gets the number of tasks in the TaskList.
     *
     * @return Number of tasks in the TaskList.
     */
    @JsonIgnore
    public int getSize() {
        return tasks.size();
    }

    /**
     * Marks a task as done.
     *
     * @param index Index of the task to be marked as done.
     * @throws TaskNotFoundException         If the task is not in the TaskList.
     * @throws DuplicateMarkingTaskException If the task is already marked as done.
     */
    public void markAsDone(int index) throws TaskNotFoundException, DuplicateMarkingTaskException {
        if (!contains(index)) {
            throw new TaskNotFoundException();
        }
        if (tasks.get(index).isDone()) {
            throw new DuplicateMarkingTaskException();
        }
        tasks.get(index).markAsDone();
        setCompletionRate(calCompletionRate());
    }

    /**
     * Marks a task as undone.
     *
     * @param index Index of the task to be marked as undone.
     * @throws TaskNotFoundException         If the task is not in the TaskList.
     * @throws DuplicateMarkingTaskException If the task is already marked as
     *                                       undone.
     */
    public void markAsUndone(int index) throws TaskNotFoundException, DuplicateMarkingTaskException {
        if (!contains(index)) {
            throw new TaskNotFoundException();
        }
        if (!tasks.get(index).isDone()) {
            throw new DuplicateMarkingTaskException();
        }
        tasks.get(index).markAsUndone();
        setCompletionRate(calCompletionRate());
    }

    /**
     * Gets the ArrayList of tasks in the TaskList.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the string representation of the TaskList.
     *
     * @return The String representation of the TaskList.
     */
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

    /**
     * Prints the TaskList.
     */
    public void printList() {
        System.out.println(this);
    }

    /**
     * Returns the number of completed tasks in the TaskList.
     *
     * @return number of completed tasks.
     */
    @JsonIgnore
    public int getCompletedTaskCount() {
        int completedCount = 0;
        for (Task task : tasks) {
            if (task.isDone()) {
                completedCount++;
            }
        }
        return completedCount;
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

    public static class DuplicateMarkingTaskException extends Exception {
        public DuplicateMarkingTaskException() {
            super("Input task is already marked/unmarked.");
        }
    }
}
