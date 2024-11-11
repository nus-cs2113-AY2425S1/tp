# Ayushi Yadav - Project Portfolio Page

## Overview
FitTrackCLI is a command-line chatbot designed to help users manage their NAPFA-related exercises
and goals. It allows users to record and track training sessions, set and monitor fitness goals,
and manage food and water intake.

## Summary of Contributions

### Code Contributed:
I contributed to multiple core packages in the FitTrackCLI system, collaborating with Avjay on several modules. The contributions included creating and enhancing key features for managing fitness goals, food/water intake, training sessions, mood logs, and reminders.

#### Major Contributions:

- Fitness Goal Package: Implemented the AddFitnessGoal, DeleteFitnessGoal, and Goal classes to
  manage user fitness goals, ensuring proper functionality and integration with other components.
- Health Profile: Developed the FoodEntry, FoodWaterIntake, and WaterEntry classes in collaboration
  with Avjay, enabling users to track their nutrition and hydration.
- Mood Logs: Added mood tracking functionality to the TrainingSession class, allowing users to
  log their emotional and physical states during training sessions.
- Code Quality: Enhanced code readability and maintainability by adding comments to several key
  modules, including:
  - Fitness Goal package (AddFitnessGoal, DeleteFitnessGoal, Goal)
  - Health Profile package (FoodEntry, FoodWaterIntake, WaterEntry)
  - MoodLog functionality in TrainingSession
  - Calculator package (Calculator, PullUpCalculator, ShuttleRunCalculator, SitAndReachCalculator,
    SitUpCalculator, StandingBroadJumpCalculator, WalkAndRunCalculator)
  - Reminder package (Reminder)
  - User (User)
  - Storage (Storage, Saveable)
  - Parser (Parser)
  - FitTrack

### Enhancements Implemented:

| Command             | Format                              | Example                            |
|---------------------|-------------------------------------|------------------------------------|
| **add-goal**        | `add-goal GOAL_NAME DEADLINE`       | `add-goal run 12/12/2024 14:00:00` |
| **delete-goal**     | `delete-goal GOAL_INDEX`            | `delete-goal 1`                    |
| **list-goal**       | `list-goal`                         | `list-goal`                        |
| **add-water**       | `add-water`                         | `add-water 500`                    |
| **delete-water**    | `delete-water`                      | `delete-water 1`                   |
| **list-water**      | `list-water`                        | `list-water`                       |
| **add-food**        | `add-food`                          | `add-food apple 100`               |
| **delete-food**     | `delete-food`                       | `delete-food 1`                    |
| **list-food**       | `list-food`                         | `list-food`                        |
| **list-intake**     | `list-intake`                       | `list-intake`                      |


### Documentation:
#### User Guide: Documented the commands and usage for water/food intake management, fitness goals, and mood
logs. Detailed the format, purpose, example commands, and expected output for each feature.

#### Developer Guide: In the Developer Guide, I contributed detailed documentation on the following key
features of the FitTrackCLI project. Additionally, I played a role in enhancing the main class
diagram to provide better clarity on the system’s architecture and module interactions.

### Project Management Contributions:
As part of the project management efforts, I actively contributed to the team's workflow and
overall organization. My contributions include:
- Issue Tracking: I consistently uploaded issues and worked on resolving them as they arose,
  ensuring that tasks were clearly defined and efficiently handled.
- Pull Request Management: I meticulously ensured that my pull requests were reviewed and acted
  quickly if any problems arose. This helped maintain code quality and fostered a collaborative
  development process.
- Maintaining Communication: I actively maintained communication channels, ensuring that the team
  stayed aligned and on track. I encouraged team members to keep open lines of communication, whether
  regarding challenges or progress, which led to better teamwork and faster problem resolution.
  These efforts contributed to smooth project progress and a collaborative environment, ultimately
  ensuring the timely completion of our tasks and the overall success of the project.

