# Project Portfolio Page: ExchangeCourseMapper

By: Chiang Hui Xin (@Chiang-HuiXin)

## 1. Overview
ExchangeCourseMapper is a convenient tool to allow users to plan their exchange course mapping.
It includes features to list the partner university available, the courses it offers and allows for the filtering of
courses. There is also a personalised tracker that allows users to add and delete course mapping, list out the mapped
courses and compare the course mapping between two schools.

## 2. Summary of Contributions

### Code contributed:
I contributed mainly to the Information Checker part and developed the `ListUniCoursesCommad` class and `HelpCommand`
class which both extends from `CheckInformationCommand` class

This is a link to the code I have contributed: [TP dashboard](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=chiang-huixin&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=test-code&tabOpen=true&tabType=authorship&tabAuthor=Chiang-HuiXin&tabRepo=AY2425S1-CS2113-W10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancement Implemented:

#### Database ####
- Add course details for The University of Western Australia

#### ListUniCoursesCommand ####
- Implemented the `ListUniCoursesCommand` class to allow user to list out the courses available for a
  specific school
- Implemented JUnit test for `ListUniCoursesCommand` class
- Implemented assertions, loggings and exceptions for error handling
- Added Javadoc for documentation

#### HelpCommand #####
- Implemented the `HelpCommand` class to allow users to ask for help when in doubt of the features.
- Implemented JUnit test for `HelpCommand`
- Implemented assertions, loggings and exceptions for error handling
- Added Javadoc for documentation

## Contributions to UG:

## Contribution to DG:
#### Class Diagram section ####
- Implemented `Parser` class diagram

#### Implementation section ####
1. ListUniCoursesCommand
- Written overview, how the feature is implemented, why it is implemented that way
- Sequence diagram for `ListUniCoursesCommand`

2. HelpCommand
- Written overview, how the feature is implemented, why it is implemented that way
- Sequence diagram for `HelpCommand`

## Review/mentoring contributions ###
* Github code review and comments:
* https://github.com/AY2425S1-CS2113-W10-2/tp/pull/100
* https://github.com/AY2425S1-CS2113-W10-2/tp/pull/84
* https://github.com/AY2425S1-CS2113-W10-2/tp/pull/168

## Contributions beyond the project team:
* Peer review for team CS2113-T11-1
* https://github.com/nus-cs2113-AY2425S1/tp/pull/15/files#diff-94fa36935d6c1f7219b9f9db36e467da385cadbf636e14fde5c37332bbc6a834
* https://github.com/nus-cs2113-AY2425S1/tp/pull/15/files#diff-94fa36935d6c1f7219b9f9db36e467da385cadbf636e14fde5c37332bbc6a834
* https://github.com/nus-cs2113-AY2425S1/tp/pull/15/files#diff-517de6c5ce4c9a799a46c96b126323892da4d8abb5c4f7cfc13251da82695972