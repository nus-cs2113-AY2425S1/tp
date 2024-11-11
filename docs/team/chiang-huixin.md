# Project Portfolio Page: ExchangeCourseMapper

By: Chiang Hui Xin (@Chiang-HuiXin)

## 1. Overview
ExchangeCourseMapper is a convenient tool to allow users to plan their exchange course mapping.
It includes features to list the partner university available, the courses it offers and allows for the filtering of
courses. There is also a personalised tracker that allows users to add and delete course mapping, list out the mapped
courses and compare the course mapping between two schools.

## 2. Summary of Contributions

### Code contributed:

This is a link to the code I have contributed: [TP dashboard](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=chiang-huixin&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=test-code&tabOpen=true&tabType=authorship&tabAuthor=Chiang-HuiXin&tabRepo=AY2425S1-CS2113-W10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancement Implemented:

#### Database ####
- Add course details for The University of Western Australia

#### Features ####

- Implemented JUnit test for the classes mentioned below.
- Implemented assertions, loggings and exceptions for error handling.
- Implemented Javadoc for documentation.
  1. #### ListUniCoursesCommand ####
     - Implemented the `ListUniCoursesCommand` class to allow user to list out the courses available for a specific school

  2. #### HelpCommand #####
     - Implemented the `HelpCommand` class to allow users to ask for help when in doubt of the features.

  3. #### FindCoursesCommand ####
     - Implemented the `FindCoursesCommand` class to allow users to search for specific NUS courses in their Personalised
       Tracker.

## 3. Contributions to UG:
- Documented List University Courses command
- Documented Help Command
- Documented Find Courses Command

## 4. Contribution to DG:
#### Class Diagram section ####
- Implemented `Parser` class diagram

#### Implementation section ####
* `ListUniCoursesCommand` | `HelpCommand` | `FindCoursesCommand`
- Documented the overview, how the feature is implemented, why it is implemented that way and drew the sequence diagram for all three features

#### Manual Testing Section
- Documented test cases for features `FindCoursesCommand`, `AddCoursesCommand` and `DeleteCoursesCommand`

## 5. Contribution to team-based tasks
- Reviewed PRs to ensure adherence to code quality and standards
- Maintained GitHub issue tracker

## 6. Review/mentoring contributions ###
* GitHub code review and comments:
* [#84](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/84) | [#100](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/100) | [#168](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/168)

## 7. Contributions beyond the project team:
* Peer review for team CS2113-T11-1. Here are some of the comments:
* [PR1](https://github.com/nus-cs2113-AY2425S1/tp/pull/15/files#diff-94fa36935d6c1f7219b9f9db36e467da385cadbf636e14fde5c37332bbc6a834) | [PR2](https://github.com/nus-cs2113-AY2425S1/tp/pull/15/files#diff-94fa36935d6c1f7219b9f9db36e467da385cadbf636e14fde5c37332bbc6a834) | [PR3](https://github.com/nus-cs2113-AY2425S1/tp/pull/15/files#diff-517de6c5ce4c9a799a46c96b126323892da4d8abb5c4f7cfc13251da82695972)