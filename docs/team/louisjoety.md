# Project Portfolio Page: ExchangeCourseMapper

By: Joe Tien You (@louisjoety)

## 1. Overview:
ExchangeCourseMapper is a convenient tool to allow users to plan their exchange course mapping.
It includes features to list the partner university available, the courses it offers and allows for the filtering of
courses. There is also a personalised tracker that allows users to add and delete course mapping, list out the mapped
courses and compare the course mapping between two schools.

### 2. Code contributed:
I played a key role in developing my team's Information Checker feature, specifically the `ObtainContactsCommand` and 
`ListSchoolsCommand`. I also created the `Command` parent class, which serves as the backbone of the application, 
allowing for easy extension to other child classes in line with object-oriented programming (OOP) standards. 
Additionally, I crafted detailed user and developer guides to ensure a smooth experience for both end-users and 
my team of developers.

Check out my contributions on the [TP Dashboard](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=louisjoety&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false).

## 3. Enhancements implemented:

### Command | CheckInformationCommand | PersonalTrackerCommand
* Abstract parent classes to allow child classes to be implemented through over-riding methods
* This is essential for ease of debugging and task allocation

### Obtain Contacts Command Class
* Updated Database with new contact number and email information
* Managed controls to allow users to retrieve either email or number
* J-unit test, assertions and logging were done to gracefully handle errors
* JavaDocs for non-trivial methods were also written

### List Schools Command Class
* Updated Database with school names and courses available
* Managed controls to allow users to obtain a list of schools available in the database
* J-unit test, assertions and logging were done to gracefully handle errors
* JavaDocs for non-trivial methods were also written

### Miscellaneous
* Implemented starting points for 4 major components: `Parser`, `UI`, `Commands`, `Storage`
* Trivial features: Creating `Assertions`, `Logs` file and getting rid of magic strings

### Refactoring
* General refactoring of code to ensure they follow SLAP on 
* Help team do QA to obtain bugs around code namely in storage and more complex commands

## 4. User Guide:
* Documented the FAQ section 
* Documented List Schools Function
* Documented Obtains Contacts function 

## 5. Developer Guide:
* Added the directory for the DG 
* Documented the NFR
* Documented the product scope
* Documented the acknowledgements section
* Design Section: 
  * Architecture section - Architecture Diagram
  * Partial Class Diagram of `Commands`
  * Flow of an example of Sequence Diagram 
* Implementation Section: 
  * General JSON Processing Section
  * List Schools Section
  * Obtain Contacts Section

## 6. Contributions to team-based tasks:
* Enabled assertions in `build.gradle` to allow team to do error handling
* Established the starting project's file structure and renamed files to align with the project name.
* Maintained the issue tracker 
* Managed the release of v1.0
* Requested usage of 3rd party libraries in course forum
  * https://github.com/nus-cs2113-AY2425S1/forum/issues/28

## 7. Review/mentoring contributions:
* GitHub code reviews and comments across multiple PRs, listed are the **notable** ones:
  * https://github.com/AY2425S1-CS2113-W10-2/tp/pull/26
  * https://github.com/AY2425S1-CS2113-W10-2/tp/pull/78
  * https://github.com/AY2425S1-CS2113-W10-2/tp/pull/84
* Clarified informal queries raised by my team about IntelliJ/Git/Java etc. on a day-to-day basis

## 8. Contributions beyond the project team:
* PR review for team CS2113-T10-2
  * https://github.com/nus-cs2113-AY2425S1/tp/pull/11
