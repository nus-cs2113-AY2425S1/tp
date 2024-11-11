# Project Portfolio Page: ExchangeCourseMapper

By: Joe Tien You (@louisjoety)

## 1. Overview:
ExchangeCourseMapper is a tool to allow users to plan their exchange course mapping.
It includes features to list partner universities, their courses and filtering of courses. 
There is also a tracker that allows users to add and delete mappings, display the saved courses and 
compare between two schools.

## 2. Code contributed:
I developed key features for the Information Checker feature, including `ObtainContactsCommand` and `ListSchoolsCommand`
, and created the `Command` parent class to enable OOP-based extensions. I also wrote user and developer guides to 
support usability and team collaboration. Check out my contributions on the [TP Dashboard](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=louisjoety&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false).

## 3. Enhancements implemented:

### a. Command Class | Check Information Command Class | Personal Tracker Command Class
* Abstract parent classes to allow child classes to be implemented through over-riding methods
* This is essential for ease of debugging and task allocation

### b. Obtain Contacts Command Class | School Contact Validator Class
* Updated Database with new contact number and email information
* Managed controls to allow users to retrieve either email or number
* J-unit test, assertions and logging were done to gracefully handle errors
* JavaDocs for non-trivial methods were also written

### c. List Schools Command Class
* Updated Database with school names and courses available
* Managed controls to allow users to obtain a list of schools available in the database
* J-unit test, assertions and logging were done to gracefully handle errors
* JavaDocs for non-trivial methods were also written

### d. Miscellaneous
* Implemented starting points for 4 major components: `Parser`, `UI`, `Commands`, `Storage`
* Trivial features: Creating `Assertions`, `Logs` file and getting rid of magic strings
* J-unit test for `Parser` Class
* Help team do QA to obtain bugs around code namely in more complex commands

## 4. User Guide:
* Documented the FAQ section and the Command Summary
* Documented List Schools Function and Obtains Contacts function 

## 5. Developer Guide:
* Added the directory for the DG 
* Documented the NFR, product scope, acknowledgements section
* Design Section: 
  * Architecture Diagram, Partial Class Diagram of `Commands`, Example of Sequence Diagram 
* Implementation Section: 
  * General JSON Processing Section, List Schools Section, Obtain Contacts Section

## 6. Contributions to team-based tasks:
* Enabled assertions in `build.gradle` to allow team to do error handling
* Established the project file structure and renamed files to match project
* Maintained the issue tracker 
* Managed the release of v1.0
* Requested usage of 3rd party libraries in course forum [here](https://github.com/nus-cs2113-AY2425S1/forum/issues/28)

## 7. Review/mentoring contributions:
* GitHub code reviews and comments across multiple PRs, listed are the **notable** ones:
  [#26](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/26),
  [#78](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/78),
  [#84](https://github.com/AY2425S1-CS2113-W10-2/tp/pull/84)
* Clarified informal queries raised by my team about IntelliJ/Git/Java etc. on a day-to-day basis

## 8. Contributions beyond the project team:
* PR review for team CS2113-T10-2 [here](https://github.com/nus-cs2113-AY2425S1/tp/pull/11)
