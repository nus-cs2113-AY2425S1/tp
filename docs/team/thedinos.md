# Marcus Wong - Project Portfolio Page

## Overview
FitTrackCLI is command-line-based chatbot to help users manage their NAPFA related exercises and goals.
It lets users record and track training sessions, calculate their NAPFA scores and awards, set NAPFA reminders and set fitness goals.

## Summary of Contributions

### Code Contributed
https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20&tabOpen=true&tabType=zoom&zA=TheDinos&zR=AY2425S1-CS2113-W13-2%2Ftp%5Bmaster%5D&zACS=143.81368136813683&zS=2024-09-20&zFS=&zU=2024-11-01&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false

### Enhancements implemented
| Class                | Functionality                                                                                                                                                           |
|----------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| TrainingSession      | Manages a user's fitness session, tracking performance across various exercises, calculating points, and determining the award level based on total and minimum points. |
| WalkAndRunCalculator | Calculates the number of points for the 2.4km run given the gender, age and timing of the user.                                                                         |
| ShuttleRunCalculator | Calculates the number of points for the shuttle run given the gender, age and timing of the user.                                                                       |
| WalkAndRunStation    | Tracks and formats a user's 2.4km timing, calculates their points based on performance, and provides exercise information.                                              |
| ShuttleRunStation    | Tracks and formats a user's shuttle run timing, calculates their points based on performance, and provides exercise information.                                        |


| Function                           | Purpose                                                                                                                             |
|------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------|
| Edit Exercise performance          | Updates the performance of a specified exercise in the training session based on user input and recalculates the associated points. |
| View training session              | Displays all exercise data for the session, including total points and the overall award achieved.                                  |
| Get Points for time-based exercise | Calculates and returns the points based on the user's time, age, and gender, or returns the default points if no valid time is set. | |

### Contributions to the UG
Documented the following portions: User configuration and Introduction.

### Contributions to the DG
UML Diagrams created: Class diagram for TrainingSession instantiation, Sequence diagram for editExercise() in TrainingSession class, Class diagram for TrainingSession after use of editExercise()
Documented: Edit Exercise Feature

### Contributions to Team-based tasks
Set up the Github team organisation and repository, updated timeline for v2.0, released the jar file for v1.0.

### Review/mentoring contributions
Gave comments on PR of teammates, reviewed, approved and merged PRs.
