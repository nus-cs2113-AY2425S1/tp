# Ng Chee Kiang - Project Portfolio Page

## Overview
FitTrackCLI is command-line-based chatbot to help users manage their NAPFA related exercises and goals.
It lets users record and track training sessions, calculate their NAPFA scores and awards and set fitness goals.

## Summary of Contributions

### Code contributed: [(link)](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20&tabOpen=true&tabType=authorship&tabAuthor=CheeKiangg&tabRepo=AY2425S1-CS2113-W13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


### Enhancements implemented:
I implemented the following and also implemented all the Junit testings under my class.

| **Class**                     | **Purpose**                                                                                                                         |
|-------------------------------|-------------------------------------------------------------------------------------------------------------------------------------|
| `ExerciseStation` (abstract)  | Base class for different exercise stations, providing common structure and methods for tracking performance and calculating points. |
| `SitUpStation`                | Tracks and formats user's sit up reps and provides exercise information.                                                            |
| `PullUpStation`               | Tracks and formats user's pull up reps and provides exercise information.                                                           |
| `StandingBroadJumpStation`    | Tracks and formats user's jump distance and provides exercise information.                                                          |
| `WalkAndRunStation`           | Tracks and formats user's 2.4km run time and provides exercise information.                                                         |
| `ShuttleRunStation`           | Tracks and formats user's shuttle run time and provides exercise information.                                                       |
| `SitAndReachStation`          | Tracks and formats user's sit and reach length and provides exercise information.                                                   |
| `Calculation` (abstract)      | Base class for different exercise calculators, providing structure for calculating points based on performance and user attributes. |
| `SitUpCalculator`             | Calculates points for the sit-up based on age, gender and reps.                                                                     |
| `PullUpCalculator`            | Calculates points for the pull-up based on age, gender and reps.                                                                    |
| `StandingBroadJumpCalculator` | Calculates points for the standing broad jump based on age, gender and distance.                                                    |
| `SitAndReachCalculator`       | Calculates points for the sit and reach station based on age, gender and length.                                                    |
| `LookUpKey`                   | indexing user attributes (such as age and gender) within lookup tables for point calculation.                                       |
| `InvalidAgeException`         | Custom exception class for handling invalid age entries.                                                                            |
| `GraphPerformanceTime`        | Displays a graph of normalized time performance against session index for time-based exercises.                                     |

## Documentation:
### User Guide:
Documented Performance Graph for time based and non-time based station section, cheatsheet table section and FAQ section. 

### Developer Guide:
Documented setting up,getting started section, add Training Session section and its sequence diagram,
Points Calculation and its sequence diagram, as well as GraphPerformanceTime Class and its sequence diagram.

## Project Management:
### Contributions to team-based tasks
Code cleaning and enhancement and maintenance of issue tracker.

### Review/mentoring Contribution:
Responsible for reviewing and providing feedback on 36 PRs.