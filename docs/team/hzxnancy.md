# Project Portfolio Page: ExchangeCourseMapper

By: Han Zixuan Nancy (@hzxnancy)

## Product Overview

ExchangeCourseMapper allows users to plan their course mapping by listing the universities of interest, 
along with the specific courses and subject codes offered by each school. Users can quickly filter by NUS-coded 
modules or partner universities (PU) when viewing the relevant options.

## Code Contribution

Click here to view my code contributions: [Nancy's TP Code Dashboard](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=hzx&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20&tabOpen=true&tabType=authorship&tabAuthor=hzxnancy&tabRepo=AY2425S1-CS2113-W10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

## Enhancement Implemented

### Add Course Mapping -`AddCoursesCommand` Class 

+ Using the `add NUS_COURSE_CODE /pu PARTNER_UNIVERSITY /coursepu PU_COURSE_CODE` format, users could add their desired 
  course mapping into `myList.json` file for storage.
  [#31](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/31)
+ Parse user's inputs to extract relevant information before validating course mapping in `CourseValidator` class.
  [#31](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/31)
+ Throw exceptions when there are missing inputs and keywords. 
+ Implement assertions, exceptions, and JUnit tests to handle and test error cases. 
 [#36](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/36), 
 [#59](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/59)
+ Add Javadocs and logging for better documentation.
 [#147](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/147)

### Course Validation -`CourseValidator` Class

+ Validate user's course mapping in the `AddCoursesCommand` class to ensure it is correct and restricted to universities in Oceania.
  [#83](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/83)
+ Check the user's course mapping against `database.json`. Throw relevant exceptions if the course mapping is invalid. 
+ Implement assertions, exceptions, and JUnit tests to handle and test error cases. 
  [#106](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/106)
+ Add Javadocs and logging for better documentation.
  [#147](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/147)

### Course Validation -`Course` Class

+ Abstract variables and methods related to course mapping. 
 [#75](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/75)
+ Enhance OOP through abstraction and encapsulation.
+ `Course` class is subsequently used in `AddCoursesCommand` and `Storage` classes.

## User Guide Contribution

+ Add documentation for add course mapping feature `add` 
  [#194](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/194)
+ Add command summary for all features found in the application
+ Add the directory for the user guide

## Developer Guide Contribution

+ Add documentation for the add course mapping implementation, including a detailed explanation of the `AddCourseCommand`and `CourseValidator` classes. [#112](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/112)
+ Create a sequence diagram of AddCoursesCommand and CourseValidator respectively.[#112](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/112), 
[#208](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/208)
+ Create a class diagram for the `CourseValidator` class. 
 [#165](https://github.com/AY2425S1-CS2113-W10-2/tp/issues/165)
+ Update the glossary with clarifications of valid terms. 

## Contribution to team-based tasks
+ Maintain the issue tracker.
+ Add Course class to enhance the modularity and reusability of course mapping across the project.
+ Document command summary in the user guide and glossary for developer guide.
+ Add a 'Things to Note' section in the User Guide to help guide user input and prevent unnecessary bugs.

## Review / mentoring contributions

PR Reviews with non-trivial comments: 
 [#164](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/164),
[#110](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/110), 
[#220](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/220),
[#243](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/243).

Actively participates in weekly meeting and clarify doubts from my teammates on a regular basis.

## Contribution beyond the project team
Provided useful feedback for FinanceBuddy Developer Guide: [Comments Given](https://github.com/nus-cs2113-AY2425S1/tp/pull/25#pullrequestreview-2403417754)





