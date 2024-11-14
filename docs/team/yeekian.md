# Lim Yee Kian's - Project Portfolio Page


## Project: TutorLink
TutorLink is a desktop CLI application designed to help
University professors better manage the grades of students
reading their course.

TutorLink keeps a running tally of grades (GPA, overall score) of each
`Student` by tagging `Components` to each student via a `Grade` object.


### Summary of Contributions

- **New Feature:** Added a parser to decipher commands and command arguments from user input strings
    - What it does: Given a user input string, the parser identifies the command that the user is trying to execute and returns the command object. The parser will also be able to extract the input arguments for the command from the rest of the string and store them in a HashMap
    - Justification: This feature allows the sense-making of user input data. It provides a bridge between the user and the other features of the app.


- **New Feature:** `add_grade` and `delete_grade` commands:
    - What it does: `add_grade` command allows the user to add grades for individuals for individual graded assessment components one at a time. Using the `delete_grade` command, added grades can also be individually deleted for a specific student and his/her component.
    - Justification: This feature improves the product significantly because a user needs to be able to record and remove scores of assessment components, such as assignments, for students. This way, the user will be able to keep a record of these grades and use them to calculate their final grade. 
    - Credits: While the processing of input user arguments and the handling of errors are done on my own, the method to add a Grade object into the list of grades, GradeList, was created by @TrungBui32.


- **New Feature:** `add_component` command:
    - What it does: Allows the user to add new assessment components.
    - Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
    - Highlights: The command checks for duplicates and prevents additional components from being added.


- **Improved Feature:** `list_grade` command:
  - What it does: Prints a list of all the grades of all students and their individual final percentage points.
  - Contribution: 
    - Re-designed output list to be more user-friendly and print the final percentage points for each student.
    - Provided 2 options for the command: 
      1. Print grade for all students
      2. Print grade for specific student using the student's MATRIC_NUMBER.
  - Justification: This improved feature ensures that grades are presented in a more informative and cleaner manner.


- **Code contributed:** [RepoSense link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=yeekian&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)


- **Project management:**
  - Assisted with release [`v1.0`](https://github.com/AY2425S1-CS2113-W13-4/tp/releases/tag/v1.0) and the
    re-scoping of project scope in `v2.0`.
  - Managed the issuing of issues


- **Enhancements to existing features:**
  - Wrote tests for `Parser`, `AddGradeCommand`, `DeleteGradeCommand`, and `AddComponentCommand`


- **Documentation:**
  - **User Guide:**
    - Authored the initial user guide draft, including documentation for all basic features [#94](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/94).
  - **Developer Guide:**
    - Documented implementation details for `AddGradeCommand` and `DeleteGradeCommand` features [#106](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/106).
    - Wrote appendices: Product Scope, User Stories, Non-Functional Requirements, Glossary, and Instructions for Manual Testing.

- **Community:**
  - PRs reviewed (with non-trivial review comments): [#25](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/25), [#38](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/38), [#83](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/83), [#101](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/101)


- **Tools:**
  - Set up logger to log messages during execution [#29](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/29)
