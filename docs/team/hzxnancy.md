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
{To be added}

## Developer Guide Contribution

+ Add documentation for the add course mapping implementation, including a detailed explanation of the `AddCourseCommand` and `CourseValidator` classes. 
+ Create a sequence diagram of adding a course mapping, which includes the following classes: 
    1. AddCoursesCommand
    2. CourseValidator
    3. Command
    4. Course
    5. Storage
+ Create a class diagram for the `CourseValidator` class.

## Contribution to team-based tasks

## Review/mentoring contributions

## Contributions to the Developer Guide (Extracts):

## Contributions to the User Guide (Extracts):




