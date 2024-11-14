# Ethan Chua's Project Portfolio Page

## Project: TutorLink
TutorLink is a desktop CLI application designed to help
University professors better manage the grades of students 
reading their course. 

TutorLink keeps a running tally of grades (GPA, overall score) of each 
`Student` by tagging `Components` to each student via a `Grade` object.

Basic `CRUD` operations are supported. 

TutorLink thus offers a lightweight CLI-friendly course management experience. 

### Summary of Contributions

- **New Feature:** `add_student` command:
    -  What it does: Allows professors to add a student (with parameters `name` and `matriculation number`)
       to the registry of students in the application.
    - Justification: This feature allows professors to input all students in their course into the application.
    - Highlights: Implementing input validation for entering matriculation number (checking that matric number complies
with given formatting).
- **New Feature:** `delete_student` command: 
    - What it does: This feature searches for and deletes students from TutorLink.
    - Justification: This feature significantly improves the product by allowing professors to remove students who have withdrawn
      or dropped the course.
    - Highlights: Remove all grades associated with the student when executed. This is because a `Grade` object is only 
  well-defined when it is tagged to a corresponding `Student` object; removing the latter should automatically remove the former.
  
- **New Feature:** `find_student` command: 
    - What it does: Allows professors to search for students using either `matric number` or `name`.
    - Justification: This feature allows professors to quickly search for students in their course.
- **New Feature:** `list_student` command: 
    - What it does: Displays a list of all students in the course. 
    - Justification: This feature improves the product significantly as professors receive key information about their students 
  such as their `matric number` and, more importantly, their current percentage score. 
    - Highlights: We faced challenges devising a suitable method to store and update the percentage score that would hold 
  across all other operations that could affect it, i.e `add_grade`, `delete_grade` etc.
    - Credits: Special thanks to @ThienDuc3112 and @yeekian for making this feature possible through their work in the
  `add_grade` and `delete_grade` command. 

- **New Feature:** `delete_component` command: 
    - What it does: Allows professors to delete a component (exam, assignment, class part etc) from the app
    - Justification: This feature allows professors to remove components that are entered incorrectly or are 
no longer part of the course.
    - Highlights: Similar to `delete_student`, `delete_component` removes all grades tagged to the specific component upon
  execution. This is because a `Grade` object is only well-defined when it is tagged to a corresponding `Component` object; removing the latter should automatically remove the former.


- **Code Contribution**: [RepoSense](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=rcpilot1604&breakdown=true)

- Project Management: 
    - Managed `V1.0`. Set up project structure for team [#16](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/16),
[#17](https:Or//github.com/AY2425S1-CS2113-W13-4/tp/pull/17)
    - Setup GitHub Organization and Repo for team
    - Conducted bug triaging for `PE-D`
- Enhancement to existing features: 
    - Modified `delete_student` and `delete_component` commands to delete all matching grades: [#81](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/81)
    - Implemented major restructuring/refactoring to codebase: [#25](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/25),
[#52](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/52), 
[#86](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/86)
    - Improved error messages to the user [#165](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/219), [#204](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/204), [#205](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/205)
    - Wrote tests for `GradeList` and `DeleteComponentCommand` 
- **Documentation**:
    - User Guide
      - Added documentation for features implemented
      - Added documentation for input validation [#101](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/101)
      - Added notes for commands to UG [#214](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/214), [#217](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/217)
    - Developer Guide
      - Created Overarching Class Diagram: [#36](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/36)
      - Created Class and Sequence Diagram for Architecture: [#65](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/65/files)
      - Created Sequence Diagram for overall logic flow and standardized colour template for DG: [#99](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/99)
    - Community
      - PRs reviewed with nontrivial comments: [#17](https://github.com/nus-cs2113-AY2425S1/tp/pull/17), [#83](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/83), [#198](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/198), [#200](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/200) among others