package fittrack.fitnessgoal;

import java.time.LocalDateTime;

public class Goal {

  private String description;
  private LocalDateTime deadline;

  // Constructor
  public Goal(String description, LocalDateTime deadline) {
    this.description = description;
    this.deadline = deadline;
  }

  // Getter for the goal description
  public String getDescription() {
    return description;
  }

  // Getter for the goal deadline
  public LocalDateTime getDeadline() {
    return deadline;
  }

  // Optionally, you might want to override the toString() method for easy printing
  @Override
  public String toString() {
    return "Goal: " + description + (deadline != null ? ", Deadline: " + deadline : "");
  }
}
