# Project Portfolio Page: ExchangeCourseMapper

By: Chiew Chun Jia (@chewycj)

## 1. Overview
ExchangeCourseMapper is a convenient CLI tool that aids users in their Student Exchange Programme (SEP) planning.
It has two main features: One for users to check information on the partner universities and their courses, 
the second to manage a personal tracker that saves potential course mappings the user would like to consider. These 
features go hand in hand to help users manage with the amount of information required in SEP planning.


## 2. Summary of Contributions
### Code contributed:
I developed my team's `PersonalTrackerCommand` class, an abstract class that will be extended by the Personal Tracker commands,
as well as an Information Checker feature, the `FilterCoursesCommand`, and a Personal Tracker feature,
the `DeleteCoursesCommand`, as well as factored out constants into the `constants` package.

Check out my contributions to ExchangeCourseMapper on the [TP Dashboard](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=chewycj&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)!

#### JSON file
* Add course details for The University of Melbourne.

#### PersonalTrackerCommand
* Abstract parent class to allow child classes for the Personal Tracker features to be implemented through over-riding methods

#### Filter Courses Command Class
* Implemented the `FilterCoursesCommand` class to allow users to filter out Partner University Courses that can be
  mapped to a specific NUS course they plan to take during exchange.
* The user input format is `filter NUS_COURSE_CODE`, and the program, depending on whether there are available mappings,
  will print to the command line interface the name of the Partner University and the course it is offering that can be 
  mapped to `NUS_COURSE_CODE`.

#### Nus Course Code Validator Class
* Implemented the `NusCourseCodeValidator` class to check if the NUS course code input for the `FilterCoursesCommand`
  follows a valid NUS SoC course code format

#### Delete Courses Command Class
* Implemented the `DeleteCoursesCommand` class to allow users to delete a course mapping plan from
  their saved list in the Storage class whenever they find that the plan is not suitable anymore.
* The user input format is `delete LIST_INDEX`, `LIST_INDEX` will be further checked to ensure it is a valid index,
  invalid indexes such as negative indexes, non-integers, or indexes larger than the list size will throw 
  an exception, which will be caught in the main code.

#### Miscellaneous
* J-unit test, assertions and logging for all classes were done to gracefully handle errors
* Refactored code over the course of the project to follow SLAP
* Started the use of named constants by creating the `constants` package, with the `Messages`, `Commands`, `JsonKey` 
  and `Regex` files that stores the constants used throughout the code base

## 3. User Guide
* Documented Filter Courses Function
* Documented Delete Courses Function

## 4. Developer Guide

### Design Section
* Made config files for UML diagrams (Class and Sequence) to follow standard notations
* Class Diagram of `Commands` structure: `Command` class inheritance, `CheckInformationCommand` and
  `PersonalTrackerCommand`

### Implementation Section
* Documented the overview for the FilterCourses and DeleteCourses commands, as well as how the features are implemented.
* Filter Courses Sequence Diagram
* Delete Courses Sequence Diagram

### Instructions for manual testing
* Documented test cases for testers to follow for the `CheckInformationCommands`
* Included notes for testers that could guide them through the testing

## 5. Contributions to team-based tasks
* Set up the GitHub team organisation, tP repository, and issue tracker, while discussing with the team through a call.
* Provided the idea of a CLI application to help with Student Exchange Programme planning
* Added the database as a resource to Java in order to package it with the JAR.
* Creation of the `constants` package, for the use of named constants in our team code.
* Maintained the issue tracker alongside the team.

## 6. Review/mentoring contributions
* GitHub code reviews and comments for multiple PRs, learning together with the team:
    * Discussion on sequence diagrams: [#72](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/72)
    * Discussion on JUnit tests: [#109](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/109)
    * Discussion on SLAP: [#125](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/125)
* Guided team members through checkstyle errors during the CI process

## 7. Contributions beyond the project team:
[Peer-reviewed](https://github.com/nus-cs2113-AY2425S1/tp/pull/9#pullrequestreview-2403433893) the DG done by team CS2113-T10-3 for their application MangaTantou.
