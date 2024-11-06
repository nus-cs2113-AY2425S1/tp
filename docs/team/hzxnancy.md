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

+ Using the `add NUS_COURSE_CODE /pu PARTNER_UNIVERSITY /coursepu PU_COURSE_CODE` format, users could add their desired course mapping into `myList.json` file for storage.
+ Parses user's input to extract relevant information before validating course mapping in `CourseValidator` class.  
+ Throws exceptions when there are missing inputs and keywords. 
+ Implement assertions, exceptions, and JUnit tests to handle and test error cases.
+ Added Javadocs and logging for better documentation.

### Course Validation -`CourseValidator` Class

+ Validates the user's course mapping in the `AddCoursesCommand` class to ensure it is correct and restricted to universities in Oceania.
+ Check the user's course mapping against our database.json. Throw relevant exceptions if the course mapping is invalid. 
+ Implement assertions, exceptions, and JUnit tests to handle and test error cases.
+ Added Javadocs and logging for better documentation.

### Course Validation -`Course` Class

+ Abstract variables and methods related to course mapping. 
+ Enhance OOP through abstraction and encapsulation.
+ `Course` class is subsequently used in `AddCoursesCommand` and `Storage` classes.

## User Guide Contribution

+ Added documentation for add course mapping feature `add`
+ Added command summary for all features found in the application
+ Added the directory for the user guide

## Developer Guide Contribution

+ Add documentation for the add course mapping implementation, including a detailed explanation of the `AddCourseCommand`and `CourseValidator` classes.  [#112](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/112)
+ Create a sequence diagram of AddCoursesCommand and CourseValidator.  [#112](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/112)
+ Create a class diagram for the `CourseValidator` class. [#165](https://github.com/AY2425S1-CS2113-W10-2/tp/issues/165)
+ Update the glossary with clarifications of valid terms. 

## Contribution to team-based tasks
+ Maintaining the issue tracker
+ Add a Course class to enhance the modularity and reusability of course mapping across the project
+ Documenting command summary in the user guide and glossary for developer guide
## Review/mentoring contributions

PR Reviews with non-trivial comments: 
 [#164](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/164),
[#110](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/110), 
[#168](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/168)

Actively participates in weekly meeting and clarify doubts from my teammates on a regular basis

## Contribution beyond the project team
Provided useful feedback for FinanceBuddy Developer Guide: [Comments Given](https://github.com/nus-cs2113-AY2425S1/tp/pull/25#pullrequestreview-2403417754)





