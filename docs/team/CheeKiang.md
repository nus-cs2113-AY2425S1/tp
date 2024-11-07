# Ng Chee Kiang - Project Portfolio Page

## Overview
FitTrackCLI is command-line-based chatbot to help users manage their NAPFA related exercises and goals.
It lets users record and track training sessions, calculate their NAPFA scores and awards and set fitness goals.

## Summary of Contributions

### Code contribution (Team and Individual):
https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20&tabOpen=true&tabType=authorship&tabAuthor=CheeKiangg&tabRepo=AY2425S1-CS2113-W13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false

### Enhancements implemented:
I implemented the following classes:

| **Class**                  | **Purpose**                                                                                                                                             |
|----------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|
| `ExerciseStation` (abstract) | Base class for different exercise stations, providing common structure and methods for tracking performance and calculating points.                     |
| `SitUpStation`             | Represents the sit-up station, calculates points based on user performance and integrates with `SitUpCalculator`.                                       |
| `PullUpStation`            | Represents the pull-up station, calculates points based on user performance and integrates with `PullUpCalculator`.                                     |
| `StandingBroadJumpStation` | Represents the standing broad jump station, calculates points based on user performance and integrates with `StandingBroadJumpCalculator`.              |
| `WalkAndRunStation`        | Represents the walk and run station, calculates points based on user performance in time-based format.                                                  |
| `ShuttleRunStation`        | Represents the shuttle run station, calculates points based on user performance and integrates with `ShuttleRunCalculator`.                             |
| `SitAndReachStation`       | Represents the sit and reach station, calculates points based on user performance and integrates with `SitAndReachCalculator`.                          |
| `Calculation` (abstract)   | Base class for different calculators, providing structure for calculating points based on performance and user attributes.                              |
| `SitUpCalculator`          | Calculates points for the sit-up station based on user age, gender, and performance (number of reps).                                                   |
| `PullUpCalculator`         | Calculates points for the pull-up station based on user age, gender, and performance (number of reps).                                                  |
| `StandingBroadJumpCalculator` | Calculates points for the standing broad jump station based on user age, gender, and performance (distance).                                            |
| `SitAndReachCalculator`    | Calculates points for the sit and reach station based on user age, gender, and performance (distance reached).                                          |
| `LookUpKey`                | Key class used for efficiently indexing user attributes (such as age and gender) within lookup tables for point calculation.                            |
| `InvalidAgeException`      | Custom exception class for handling invalid age entries during point calculations, ensuring age inputs fall within the supported range.                 |
| **Unit Tests**             | unit tests for `SitUpStation`, `PullUpStation`, `StandingBroadJumpStation`, `WalkAndRunStation`, `ShuttleRunStation`, and `SitAndReachStation` classes. |
| `GraphPerformanceTime`      | Displays a graph of normalized time performance against session index for Shuttle Run station and Walk and Run station.                                 |


## Documentation:
### User Guide:
Documented the following features: Display Performance Graph for time based and non-time based station
Cheatsheet table at the end for user's quick reference

### Developer Guide:
Documented setting up,getting started section.

Documented Add Training Session section and its sequence diagram.

Documented Points Calculation and its sequence diagram.

Documented GraphPerformanceTime Class and its sequence diagram.

## Project Management:
### Review/mentoring Contribution:
Responsible for reviewing and providing feedback on numerous PRs.

