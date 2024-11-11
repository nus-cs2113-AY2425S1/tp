# Project Portfolio Page: ExchangeCourseMapper

By: Chiew Chun Jia (@chewycj)

## 1. Overview
ExchangeCourseMapper is a convenient CLI tool that aids users in their Student Exchange Programme (SEP) planning.
It has features for users to check information on the partner universities and their courses, 
and manage a personal tracker that saves potential course mappings the user would like to consider.

## 2. Summary of Contributions
### Code contributed:
Check out my contributions to ExchangeCourseMapper on the [TP Dashboard](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=chewycj&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)!

### Enhancements Implemented:
#### PersonalTrackerCommand
Abstract parent class for child classes of the Personal Tracker feature.

#### Filter Courses Command Class
Implemented the `FilterCoursesCommand` class to allow users to filter out Partner University Courses that can be
mapped to a specific NUS course they plan to take during exchange.

#### NUS Course Code Validator Class
Implemented the `NusCourseCodeValidator` class to check if the NUS course code input for the `FilterCoursesCommand`
follows a valid NUS SoC course code format.

#### Delete Courses Command Class
Implemented the `DeleteCoursesCommand` class to allow users to delete a course mapping plan using its index from
their saved list in the Storage class whenever they find that the plan is not suitable anymore.
The list index input from the user will be further checked to ensure it is a valid index.

#### Miscellaneous
* J-unit test, assertions and logging for all classes were done to gracefully handle errors.
* Refactored code over the course of the project to follow SLAP.
* Created the `constants` package to store named constants (`Messages`, `Commands`, `JsonKey` and `Regex`)

### Contributions to User Guide:
* Documented Filter Courses and Delete Courses Functions

### Contributions to Developer Guide:
* Design and Implementation Section
  * Made config files for UML diagrams to follow standard notations, and
    class diagrams of `Commands` structure: `Command` class inheritance, `CheckInformationCommand` and
    `PersonalTrackerCommand`, and sequence diagrams for FilterCourses and DeleteCourses commands.
  * Documented the implementation of FilterCourses and DeleteCourses commands.

* Instructions for manual testing Section
  * Documented test cases for testers to follow for the commands inheriting from `CheckInformationCommand`

### Contributions to team-based tasks:
* Set up the GitHub team organisation, tP repository, and issue tracker.
* Provided the idea of a CLI application to help with Student Exchange Programme planning.
* Added the database as a resource to Java in order to package it with the JAR.
* Creation of the `constants` package, for the use of named constants in our team code.
* Actively participates in weekly meetings to clarify doubts and set goals together.

### Review/mentoring contributions:
* GitHub code reviews for multiple PRs, learning together with the team: 
  Sequence diagrams: [#72](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/72),
  JUnit tests: [#109](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/109),
  SLAP: [#125](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/125)
* Guided team members through checkstyle errors during the CI process.

### Contributions beyond the project team:
[Peer-reviewed](https://github.com/nus-cs2113-AY2425S1/tp/pull/9#pullrequestreview-2403433893) the DG done by team CS2113-T10-3 for their application MangaTantou.
