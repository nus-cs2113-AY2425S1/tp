# Marcus Wong - Project Portfolio Page

## Overview
FitTrackCLI is command-line-based chatbot to help users manage their NAPFA related exercises and goals.
It lets users record and track training sessions, calculate their NAPFA scores and awards and set fitness goals.

## Summary of Contributions

### Code Contributed
https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20&tabOpen=true&tabType=zoom&zA=TheDinos&zR=AY2425S1-CS2113-W13-2%2Ftp%5Bmaster%5D&zACS=143.81368136813683&zS=2024-09-20&zFS=&zU=2024-11-01&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false

### Enhancements implemented
| Class                        | Functionality                                                                                                                                                           |
|------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| TrainingSession              | Manages a user's fitness session, tracking performance across various exercises, calculating points, and determining the award level based on total and minimum points. |
| WalkAndRunCalculator         | Calculates the number of points for the 2.4km run given the gender, age and timing of the user.                                                                         |
| ShuttleRunCalculator         | Calculates the number of points for the shuttle run given the gender, age and timing of the user.                                                                       |
| WalkAndRunStation            | Tracks and formats a user's 2.4km timing, calculates their points based on performance, and provides exercise information.                                              |
| ShuttleRunStation            | Tracks and formats a user's shuttle run timing, calculates their points based on performance, and provides exercise information.                                        |
| GraphBase                    | Provides helper functions to both GraphPerformance and GraphPoints classes.                                                                                             |
| GraphPerformance             | Generates visual representations of exercise performance (timing, reps, distance) across multiple training sessions.                                                    |
| GraphPoints                  | Visualizes the total points or points for a specific exercise across multiple training sessions.                                                                        |
| GraphPerformanceRepsDistance | Visualize reps or distance over time for rep/distance-based exercises.                                                                                                  |


| Function                                            | Purpose                                                                                                                             |
|-----------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------|
| Edit Exercise performance                           | Updates the performance of a specified exercise in the training session based on user input and recalculates the associated points. |
| View training session                               | Displays all exercise data for the session, including total points and the overall award achieved.                                  |
| Get Points for time-based exercise                  | Calculates and returns the points based on the user's time, age, and gender, or returns the default points if no valid time is set. | 
| Graph total points across training sessions         | Allows users to visualise overall progress in points across different training sessions.                                            |
| Graph exercise points across training sessions      | Allows users to visualise exercise specific progress in points for a specific exercise across different training sessions.          | 
| Graph exercise performance across training sessions | Allows users to visualise exercise performance across training sessions.                                                            |
| Generate Graph for distance/rep exercises           | Allows users to visualise the number of reps or distance in a graph for rep-based or distance-based exercises                       |                                                                                                                            |


### Contributions to the UG
Documented and contributed to the following portions: Introduction, User configuration, Table for valid exercise inputs, 
Edit Exercise, Graph Points.

### Contributions to the DG
Documented Edit Exercise section and its sequence diagram.
Documented Visualisation section and its sequence diagram. (Up till GraphPerformanceTime)
Created UML Diagrams for TrainingSession instantiation and add command.

### Contributions to Team-based tasks
Set up the Github team organisation and repository, updated timeline for v2.0, released the jar file for v1.0,
maintaining issue tracker.

### Review/mentoring contributions
Gave comments on PR of teammates, reviewed, approved and merged PRs.
